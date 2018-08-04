package com.goosebumpanalytics.biomer;

/**
 * 
 * ShadeRef.java
 * Copyright (c) 1998 Peter McCluskey, all rights reserved.
 * based in part on the code from transfor.c, pixutils.c and render.c in
 * RasMol2 Molecular Graphics by Roger Sayle, August 1995, Version 2.6
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and other materials provided with the distribution.
 *
 * This software is provided "as is" and any express or implied warranties,
 * including, but not limited to, the implied warranties of merchantability
 * or fitness for any particular purpose are disclaimed. In no event shall
 * Peter McCluskey be liable for any direct, indirect, incidental, special,
 * exemplary, or consequential damages (including, but not limited to,
 * procurement of substitute goods or services; loss of use, data, or
 * profits; or business interruption) however caused and on any theory of
 * liability, whether in contract, strict liability, or tort (including
 * negligence or otherwise) arising in any way out of the use of this
 * software, even if advised of the possibility of such damage.
 */
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.*;
import java.util.Vector;

class RefCountColor
{
  int refcount;
  Color color;
  RefCountColor(int r,int g,int b)
  {
    color = new Color(r,g,b);
    refcount = 0;
  }
  RefCountColor()
  {
    color = null;
    refcount = 0;
  }
  public final int getRed(){ return color.getRed(); }
  public final int getGreen(){ return color.getGreen(); }
  public final int getBlue(){ return color.getBlue(); }
}

class ViewStruct extends Object
{
  public int fbuf[];
  public short dbuf[];
  public int xmax, ymax;
  public int yskip;
  public int size;

  ViewStruct(int high, int wide)
  {
    ymax = high;
    yskip = xmax = wide;
    int dx;
    if( (dx = xmax%4) != 0)
        xmax += 4-dx;
    
    size = xmax*ymax;
    fbuf = new int[size + 32];
    dbuf = new short[size + 32];
    int i;
    for(i = 0; i < size; ++i)
      dbuf[i] = -32000;
  }
}

class ArcEntry extends Object
{
  static final int ARCSIZE = 2048;
  short dx,dy,dz;
  short inten;
  int offset;

  ArcEntry()
  {
    dx = dy = dz = 0;
    inten = 0;
    offset = 0;
  }
}

public class ShadeRef
{
  int col;
  int shade;
  Color color;
  static ViewStruct view;
  static short LookUp[][];

  private static final double Ambient = 0.05;
  private static final boolean EIGHTBIT = false;
  private static final int DefaultColDepth = (EIGHTBIT ? 16 : 32);
  private static int ColourDepth = DefaultColDepth;
  private static int ColourMask = ColourDepth-1;
  private static final int LutSize = (EIGHTBIT ? 256 : 1024);
  private static int Lut[] = new int[LutSize];
  private static ColorModel colorModel;
  private static boolean ULut[] = new boolean[LutSize];
  private static int RLut[] = new int[LutSize];
  private static int GLut[] = new int[LutSize];
  private static int BLut[] = new int[LutSize];
  private static final int BackCol = 0;
  private static final int BoxCol = 1;
  private static final int LabelCol = 2;
  private static final int FirstCol = 3;
  private static int BackR,BackG,BackB;
  private static int LabR,LabG,LabB;
  private static int BoxR,BoxG,BoxB;
  private static boolean UseBackFade = false;
  private static int screenX, screenY, screenZ, staticColor, radius;

  public static long t1 = System.currentTimeMillis();

  static ArcEntry ArcAc[] = new ArcEntry[ArcEntry.ARCSIZE];
  static int ArcAcPtr = 0;
  static ArcEntry ArcDn[] = new ArcEntry[ArcEntry.ARCSIZE];
  static int ArcDnPtr = 0;
  static boolean shiny = false;

/* These values set the sizes of the sphere rendering
 * tables. The first value, maxrad, is the maximum
 * sphere radius and the second value is the table
 * size = (maxrad*(maxrad+1))/2 + 1
 */
  //#define MAXTABLE  32641
  private static final int MAXRAD = 255;
  private static int ColConst[] = new int[MAXRAD];

  private static final int MAXSHADE = 32;
  /*  private static ShadeRef ScaleRef[] = new ShadeRef[MAXSHADE]; */
  private static RefCountColor Shade[] = null;
  /*
  private static int MaskColour[MAXMASK];
  private static int MaskShade[MAXMASK];
  */
  private static int ScaleCount;
  private static int LastShade;

  private static double LastRX,LastRY,LastRZ;
  private static double Zoom;


  public static int Colour2Shade(int x){ return ((int)((x)-FirstCol)/ColourDepth); }
  public static int Shade2Colour(int x){ return ((x)*ColourDepth+FirstCol); }

  public ShadeRef(int cola,int shadea,int r,int g,int b)
  {
    color = new Color(r,g,b);
    col = cola;
    shade = shadea;
  }

  private static boolean MatchChar(char a,char b){ return (((a)=='#')||((a)==(b))); }
  private static final double RootSix = Math.sqrt(6);
  private static final int ColBits = 24;

  static void InitialiseTransform()
  {
    Shade = new RefCountColor[MAXSHADE];
    final boolean APPLEMAC = false;
    if(APPLEMAC)
      LastShade = Colour2Shade(LutSize-1);
    else
      LastShade = Colour2Shade(LutSize);
    int i;
    for( i=0; i<LastShade; i++ )
      Shade[i] = new RefCountColor();

    int rad;
    for( rad=0; rad<MAXRAD; rad++ )
    {
      int maxval = (int)(RootSix*rad)+4;
      ColConst[rad] = (ColourDepth<<ColBits)/maxval;
    }

    InitialiseTables();
    ResetColourMap();
  }

  private static int CPKMAX(){ return CPKShade.length; }

    private static final ShadeRef CPKShade[] = {
     new ShadeRef( 0, 0, 200, 200, 200 ),       /*  0 Light Grey   */
     new ShadeRef( 0, 0, 143, 143, 255 ),       /*  1 Sky Blue     */
     new ShadeRef( 0, 0, 240,   0,   0 ),       /*  2 Red          */
     new ShadeRef( 0, 0, 255, 200,  50 ),       /*  3 Yellow       */
     new ShadeRef( 0, 0, 255, 255, 255 ),       /*  4 White        */
     new ShadeRef( 0, 0, 255, 192, 203 ),       /*  5 Pink         */
     new ShadeRef( 0, 0, 218, 165,  32 ),       /*  6 Golden Rod   */
     new ShadeRef( 0, 0,   0,   0, 255 ),       /*  7 Blue         */
     new ShadeRef( 0, 0, 255, 165,   0 ),       /*  8 Orange       */
     new ShadeRef( 0, 0, 128, 128, 144 ),       /*  9 Dark Grey    */
     new ShadeRef( 0, 0, 165,  42,  42 ),       /* 10 Brown        */
     new ShadeRef( 0, 0, 160,  32, 240 ),       /* 11 Purple       */
     new ShadeRef( 0, 0, 255,  20, 147 ),       /* 12 Deep Pink    */
     new ShadeRef( 0, 0,   0, 255,   0 ),       /* 13 Green        */
     new ShadeRef( 0, 0, 178,  34,  34 ),       /* 14 Fire Brick   */
     new ShadeRef( 0, 0,  34, 139,  34 ) };     /* 15 Forest Green */

  private static final ShadeRef Shapely[] = {
     new ShadeRef(0, 0, 140, 255, 140 ),    /* ALA */
     new ShadeRef(0, 0, 255, 255, 255 ),    /* GLY */
     new ShadeRef(0, 0,  69,  94,  69 ),    /* LEU */
     new ShadeRef(0, 0, 255, 112,  66 ),    /* SER */
     new ShadeRef(0, 0, 255, 140, 255 ),    /* VAL */
     new ShadeRef(0, 0, 184,  76,   0 ),    /* THR */
     new ShadeRef(0, 0,  71,  71, 184 ),    /* LYS */
     new ShadeRef(0, 0, 160,   0,  66 ),    /* ASP */
     new ShadeRef(0, 0,   0,  76,   0 ),    /* ILE */
     new ShadeRef(0, 0, 255, 124, 112 ),    /* ASN */
     new ShadeRef(0, 0, 102,   0,   0 ),    /* GLU */
     new ShadeRef(0, 0,  82,  82,  82 ),    /* PRO */
     new ShadeRef(0, 0,   0,   0, 124 ),    /* ARG */
     new ShadeRef(0, 0,  83,  76,  66 ),    /* PHE */
     new ShadeRef(0, 0, 255,  76,  76 ),    /* GLN */
     new ShadeRef(0, 0, 140, 112,  76 ),    /* TYR */
     new ShadeRef(0, 0, 112, 112, 255 ),    /* HIS */
     new ShadeRef(0, 0, 255, 255, 112 ),    /* CYS */
     new ShadeRef(0, 0, 184, 160,  66 ),    /* MET */
     new ShadeRef(0, 0,  79,  70,   0 ),    /* TRP */

     new ShadeRef(0, 0, 255,   0, 255 ),    /* ASX */
     new ShadeRef(0, 0, 255,   0, 255 ),    /* GLX */
     new ShadeRef(0, 0, 255,   0, 255 ),    /* PCA */
     new ShadeRef(0, 0, 255,   0, 255 ),    /* HYP */

     new ShadeRef(0, 0, 160, 160, 255 ),    /*   A */
     new ShadeRef(0, 0, 255, 140,  75 ),    /*   C */
     new ShadeRef(0, 0, 255, 112, 112 ),    /*   G */
     new ShadeRef(0, 0, 160, 255, 160 ),    /*   T */

     new ShadeRef(0, 0, 184, 184, 184 ),    /* 28 -> BackBone */
     new ShadeRef(0, 0,  94,   0,  94 ),    /* 29 -> Special  */
     new ShadeRef(0, 0, 255,   0, 255 ) };  /* 30 -> Default  */

     
  private static final ShadeRef AminoShade[] = {
     new ShadeRef(0, 0, 230,  10,  10 ),    /*  0  ASP, GLU      */
     new ShadeRef(0, 0,  20,  90, 255 ),    /*  1  LYS, ARG      */
     new ShadeRef(0, 0, 130, 130, 210 ),    /*  2  HIS           */
     new ShadeRef(0, 0, 250, 150,   0 ),    /*  3  SER, THR      */
     new ShadeRef(0, 0,   0, 220, 220 ),    /*  4  ASN, GLN      */
     new ShadeRef(0, 0, 230, 230,   0 ),    /*  5  CYS, MET      */
     new ShadeRef(0, 0, 200, 200, 200 ),    /*  6  ALA           */
     new ShadeRef(0, 0, 235, 235, 235 ),    /*  7  GLY           */
     new ShadeRef(0, 0,  15, 130,  15 ),    /*  8  LEU, VAL, ILE */
     new ShadeRef(0, 0,  50,  50, 170 ),    /*  9  PHE, TYR      */
     new ShadeRef(0, 0, 180,  90, 180 ),    /* 10  TRP           */
     new ShadeRef(0, 0, 220, 150, 130 ),    /* 11  PRO, PCA, HYP */
     new ShadeRef(0, 0, 190, 160, 110 ) };  /* 12  Others        */

  private static final int AminoIndex[] = {
      6, /*ALA*/   7, /*GLY*/   8, /*LEU*/   3,  /*SER*/
      8, /*VAL*/   3, /*THR*/   1, /*LYS*/   0,  /*ASP*/
      8, /*ILE*/   4, /*ASN*/   0, /*GLU*/  11,  /*PRO*/
      1, /*ARG*/   9, /*PHE*/   4, /*GLN*/   9,  /*TYR*/
      2, /*HIS*/   5, /*CYS*/   5, /*MET*/  10,  /*TRP*/
      4, /*ASX*/   4, /*GLX*/  11, /*PCA*/  11   /*HYP*/
			  };

  private static final ShadeRef HBondShade[] = {
     new ShadeRef(0, 0, 255, 255, 255 ),    /* 0  Offset =  2   */
     new ShadeRef(0, 0, 255,   0, 255 ),    /* 1  Offset =  3   */
     new ShadeRef(0, 0, 255,   0,   0 ),    /* 2  Offset =  4   */
     new ShadeRef(0, 0, 255, 165,   0 ),    /* 3  Offset =  5   */
     new ShadeRef(0, 0,   0, 255, 255 ),    /* 4  Offset = -3   */
     new ShadeRef(0, 0,   0, 255,   0 ),    /* 5  Offset = -4   */
     new ShadeRef(0, 0, 255, 255,   0 ) };  /* 6  Others        */


  private static final ShadeRef StructShade[] = {
     new ShadeRef(0, 0, 255, 255, 255 ),    /* 0  Default     */
     new ShadeRef(0, 0, 255,   0, 128 ),    /* 1  Alpha Helix */
     new ShadeRef(0, 0, 255, 200,   0 ),    /* 2  Beta Sheet  */
     new ShadeRef(0, 0,  96, 128, 255 ) };  /* 3  Turn        */

  private static final ShadeRef PotentialShade[] = {
     new ShadeRef(0, 0, 255,   0,   0 ),    /* 0  Red     25 < V       */
     new ShadeRef(0, 0, 255, 165,   0 ),    /* 1  Orange  10 < V <  25 */
     new ShadeRef(0, 0, 255, 255,   0 ),    /* 2  Yellow   3 < V <  10 */
     new ShadeRef(0, 0,   0, 255,   0 ),    /* 3  Green    0 < V <   3 */
     new ShadeRef(0, 0,   0, 255, 255 ),    /* 4  Cyan    -3 < V <   0 */
     new ShadeRef(0, 0,   0,   0, 255 ),    /* 5  Blue   -10 < V <  -3 */
     new ShadeRef(0, 0, 160,  32, 240 ),    /* 6  Purple -25 < V < -10 */
     new ShadeRef(0, 0, 255, 255, 255 ) };  /* 7  White        V < -25 */

  public static void CPKColourAttrib( Molecule m )
  {
    int i;

    for( i=0; i<CPKMAX(); i++ )
	CPKShade[i].col = 0;
    if(Shade == null) InitialiseTransform();
    else ResetColourAttrib( m );

    for(i = 0; i < m.numberOfAtoms; ++i)
    {
        Atom ptr = m.atom[ i ];
	    ShadeRef ref = CPKShade[ ptr.cpkColor ];

	    if(ref.col == 0)
	    {   ref.shade = DefineShade(ref.color.getRed(),ref.color.getGreen(),ref.color.getBlue() );
		ref.col = Shade2Colour(ref.shade);
	    }
	    Shade[ref.shade].refcount++;
	    ptr.setColor(ref.color,ref.col);
    }
    DefineColourMap();
  }

  private static int DefineShade(int r, int g, int b )
  {
    int d,dr,dg,db;
    int dist,best;
    int i;

    /* Already defined! */
    for( i=0; i<LastShade; i++ )
        if( Shade[i].refcount != 0)
            if( (Shade[i].color.getRed()==r)
		&&(Shade[i].color.getGreen()==g)
		&&(Shade[i].color.getBlue()==b) )
                return(i);

    /* Allocate request */
    for( i=0; i<LastShade; i++ )
         if( Shade[i].refcount == 0)
         {
             Shade[i] = new RefCountColor(r,g,b);
             return(i);
         }

    System.err.println("Warning: Unable to allocate shade!");

    best = dist = 0;

    /* Nearest match */
    for( i=0; i<LastShade; i++ )
    {   dr = Shade[i].color.getRed() - r;
        dg = Shade[i].color.getGreen() - g;
        db = Shade[i].color.getBlue() - b;
        d = dr*dr + dg*dg + db*db;
        if( i == 0 || (d<dist) )
        {   dist = d;
            best = i;
        }
    }
    return( best );
  }

  private static void ResetColourMap()
  {
    int i;

    /*
    if(EIGHTBIT)
    {
    for( i=0; i<256; i++ )
        ULut[i] = false;
    } 

    SpecPower = 8;
    FakeSpecular = False;
    Ambient = DefaultAmbient;
    */
    UseBackFade = false;

    BackR = BackG = BackB = 0;
    BoxR = BoxG = BoxB = 255;
    LabR = LabG = LabB = 255;
    for( i=0; i<LastShade; i++ )
        Shade[i].refcount = 0;
    /*
    ScaleCount = 0;
    */
  }

  private static void ResetColourAttrib( Molecule m )
  {
    int i;
    for(i = 0; i < m.numberOfAtoms; ++i)
    {
        Atom ptr = m.atom[ i ];
	if ( ptr.getColorIndex() != 0 )
	{
	  int c = Colour2Shade(ptr.getColorIndex());
	  if(Shade[c].refcount > 0)
	    Shade[c].refcount--;
	}
    }
  }

  private static void InitialiseTables()
  {
    int i,rad;
    short ptr[];

    LookUp = new short[MAXRAD][MAXRAD];
    LookUp[0][0] = 0;
    LookUp[1][0] = 1;
    LookUp[1][1] = 0;
    
    for( rad=2; rad<MAXRAD; rad++ )
    { 
      LookUp[rad][0] = (short)rad;

      int root = rad-1;
      int root2 = root*root;

      int arg = rad*rad;
      for( i=1; i<rad; i++ )
      {   /* arg = rad*rad - i*i */
	arg -= (i<<1)-1;

            /* root = isqrt(arg)   */
	while( arg < root2 )
	{
	  root2 -= (root<<1)-1;
	  root--;
	}
            /* Thanks to James Crook */
	LookUp[rad][i] = (short)(((arg-root2)<i)? root : root+1);
      }
      
      LookUp[rad][rad] = 0;    
    }
  }

  private static void SetLutEntry(int i, int r, int g, int b)
  {
    ULut[i] = true;
    RLut[i] = r;
    GLut[i] = g;
    BLut[i] = b;

    Lut[i] = (((r<<8)|g)<<8 ) | b;
  }

  private static double Power(double x, int y)
  {
    double result = x;
    while( y>1 )
    {   if((y&1) != 0) { result *= x; y--; }
        else { result *= result; y>>=1; }
    }
    return( result );
  }

  private static void DefineColourMap()
  {
    double fade;
    double temp,inten;
    int col,r,g,b;
    int i,j,k;
    boolean DisplayMode = false;

    for( i=0; i<LutSize; i++ )
        ULut[i] = false;

    colorModel = new DirectColorModel(24,0xff0000,0xff00,0xff);

    if( !DisplayMode )
    {
      SetLutEntry(BackCol,BackR,BackG,BackB);
      SetLutEntry(LabelCol,LabR,LabG,LabB);
      SetLutEntry(BoxCol,BoxR,BoxG,BoxB);
    } else SetLutEntry(BackCol,80,80,80);


    double diffuse = 1.0 - Ambient;
    if( DisplayMode )
    {   for( i=0; i<ColourDepth; i++ )
        {   temp = (double)i/ColourMask;
            inten = diffuse*temp + Ambient;

            /* Unselected [40,40,255] */
            /* Selected   [255,160,0]  */
            r = (int)(255*inten);
            g = (int)(160*inten);
            b = (int)(40*inten);

            SetLutEntry( FirstCol+i, b, b, r );
            SetLutEntry( Shade2Colour(1)+i, r, g, 0 );
        }
    } else
        for( i=0; i<ColourDepth; i++ )
        {   temp = (double)i/ColourMask;
            inten = diffuse*temp + Ambient;
            fade = 1.0-inten;


            k = 0;
            if( shiny )
            {   temp = Power(temp,8);
                k = (int)(255*temp);
                temp = 1.0 - temp;
                inten *= temp;
                fade *= temp;
            }


            for( j=0; j<LastShade; j++ )
                if( Shade[j].refcount != 0)
                {   col = Shade2Colour(j);
                    if( UseBackFade )
                    {   temp = 1.0-inten;
                        r = (int)(Shade[j].getRed()*inten + fade*BackR); 
                        g = (int)(Shade[j].getGreen()*inten + fade*BackG);
                        b = (int)(Shade[j].getBlue()*inten + fade*BackB);
                    } else
                    {   r = (int)(Shade[j].getRed()*inten); 
                        g = (int)(Shade[j].getGreen()*inten);
                        b = (int)(Shade[j].getBlue()*inten);
                    }

                    if( shiny )
                    {   r += k;
                        g += k;
                        b += k;
                    }

                    SetLutEntry( col+i, r, g, b );
                }
        }
  }

  private static void UpdateLine(int wide, int dy, int col, int z, int rad,
				 int offset, int dxmin, int dxmax)
  {
    int dx = -wide;
    short tptr[] = LookUp[wide];
    int tindex = wide;
    int stdcol = Lut[col];
    int cc = ColConst[rad];
    int r = 0;
    while(wide == 1 && (long)(512+wide-dy)*(long)cc > (long)Integer.MAX_VALUE)
    {		// prevent overflow in low radius inten calc
      cc = ColConst[rad + ++r];
    }
    while(dx < dxmin && dx < 0)
    {
      --tindex;
      ++dx;
    }
    offset += dx;
    while(dx < 0 && dx < dxmax)
    {
      int dz = tptr[tindex--];
      short depth = (short)(dz + z);
      if(depth > view.dbuf[offset])
      {
 	view.dbuf[offset] = depth;
	int inten = dz+dz+dx-dy;
	if( inten>0 )
	{
	  inten = (inten*cc) >> ColBits;
	  try
	  {
	    view.fbuf[offset] = Lut[col+inten];
	  }catch(ArrayIndexOutOfBoundsException e)
	  {
	    System.err.println("ArrayIndexOutOfBoundsException " + inten + " " + (dz+dz+dx-dy) + "*" + cc + " r " + r);
	  }
	}
	else view.fbuf[offset] = stdcol;
      }
      ++dx;
      ++offset;
    }
    if(dx < dxmax)
    {
      while(dx < dxmin)
      {
	++tindex;
	++dx;
	++offset;
      }
      do
      {
	int dz = tptr[tindex++];
	short depth = (short)(dz + z);
	if(depth > view.dbuf[offset])
	{
	  view.dbuf[offset] = depth;
	  int inten = (dz)+(dz)+dx-dy;
	  if( inten>0 )
	  {
	    inten = (inten*cc) >> ColBits;
	    try
	    {
	      view.fbuf[offset] = Lut[col+inten];
	    }catch(ArrayIndexOutOfBoundsException e)
	    {
	      System.err.println("ArrayIndexOutOfBoundsException " + inten + " " + (dz+dz+dx-dy) + "*" + cc + " r " + r);
	    }
	  }
	  else view.fbuf[offset] = stdcol;
	}
	++dx;
	++offset;
      } while(dx <= wide && dx < dxmax);
    }
  }

  public static void DrawSphere(int x, int y, int z, int rad, int col)
  {
    int offset = (y-rad)*view.yskip + x;
    int fold = offset;
    int dy = -rad;
    int dxmax = view.xmax - x;
    int dxmin = -x;
    if(rad >= MAXRAD)
    {
      System.err.println("radius too big " + rad);
      return;
    }

    //System.err.println("offset " + offset + " y " + y + " x " + x + " rad " + rad + " t " + (((System.currentTimeMillis() - t1)/10)/100.0));
    while(dy < 0 && y + dy < view.ymax)
    {
      if(y + dy >= 0)
      {
	int wide = LookUp[rad][-dy];
	UpdateLine(wide, dy, col, z, rad, fold, dxmin, dxmax);
      }
      fold += view.yskip;
      dy++;
    }

    if(y + dy < view.ymax)
    do { 
      if(y + dy >= 0)
      {
	int wide = LookUp[rad][dy];
	UpdateLine(wide, dy, col, z, rad, fold, dxmin, dxmax);
      }
      fold += view.yskip;
      dy++;
    } while(dy <= rad && y + dy < view.ymax);
  }

  private static void DrawArcAc(int dbase,int fbase,int z,int c)
  {
    int i;
    for(i = 0; i < ArcAcPtr; ++i)
    {
      ArcEntry ptr = ArcAc[i];
      short depth = (short)(ptr.dz+z);
      int ix = dbase + ptr.offset;
      if(ix >= view.size) break;
      if(ix >= 0 && depth > view.dbuf[ix])
      {
	view.dbuf[dbase + ptr.offset] = depth;
	view.fbuf[fbase + ptr.offset] = Lut[ptr.inten+c];
      }
    }
  }

  private static void DrawArcDn(int dbase,int fbase,int z, int c)
  {
    int i;
    for(i = 0; i < ArcDnPtr; ++i)
    {
      ArcEntry ptr = ArcDn[i];
      short depth = (short)(ptr.dz+z);
      int ix = dbase + ptr.offset;
      if(ix >= view.size) break;
      if(ix >= 0 && depth > view.dbuf[ix])
      {
	view.dbuf[dbase + ptr.offset] = depth;
	view.fbuf[fbase + ptr.offset] = Lut[ptr.inten+c];
      }
    }
  }

  private static void DrawCylinderCaps(int x1,int y1,int z1,
				       int x2,int y2,int z2,
				       int c1,int c2,int rad)
  {
    int offset;
    int dx,dy,dz;

    int lx = x2-x1;
    int ly = y2-y1;

    int end = ly*view.yskip+lx;
    int temp = y1*view.yskip+x1;
    int fold = temp;
    int dold = temp;

    ArcAcPtr = 0;
    ArcDnPtr = 0;
    if(ArcAc[0] == null)
    {
      int i;
      for(i = 0; i < ArcEntry.ARCSIZE; ++i)
      {
	ArcAc[i] = new ArcEntry();
	ArcDn[i] = new ArcEntry();
      }
    }

    temp = -(rad*view.yskip);
    short wptr[] = LookUp[rad];
    for( dy= -rad; dy<=rad; dy++ )
    {
      int wide = wptr[Math.abs(dy)];

      short lptr[] = LookUp[wide];
      for( dx= -wide; dx<=wide; dx++ )
      {
	  dz = lptr[Math.abs(dx)];
	  int inten = dz + dz + dx + dy;
	  if( inten>0 )
          {
	    inten = (int)((inten*ColConst[rad])>>ColBits);
	  }else inten = 0;
	  offset = temp+dx;

	  if((x1+dx) >= 0 && (x1+dx) < view.xmax && (y1+dy) >= 0 && (y1+dy) < view.ymax)
          {
	    short depth = (short)(dz+z1);
	    if(depth > view.dbuf[dold + offset])
	    {
	      view.dbuf[dold + offset] = depth;
	      view.fbuf[fold + offset] = Lut[inten+c1];
	    }
	  }

	  if((x2+dx) >= 0 && (x2+dx) < view.xmax && (y2+dy) >= 0 && (y2+dy) < view.ymax)
          {
	    short depth = (short)(dz+z2);
	    if(depth > view.dbuf[dold + offset + end])
	    {
	      view.dbuf[dold + offset + end] = depth;
	      view.fbuf[fold + offset + end] = Lut[inten+c2];
	    }
	  }

		// an out of bounds exception here means an excessive radius
	  ArcAc[ArcAcPtr].offset = offset;
	  ArcAc[ArcAcPtr].inten = (short)inten;
	  ArcAc[ArcAcPtr].dx=(short)dx;
	  ArcAc[ArcAcPtr].dy=(short)dy;
	  ArcAc[ArcAcPtr].dz=(short)dz;
	  ArcAcPtr++;

	  ArcDn[ArcDnPtr].offset = offset;
	  ArcDn[ArcDnPtr].inten = (short)inten;
	  ArcDn[ArcDnPtr].dx=(short)dx;
	  ArcDn[ArcDnPtr].dy=(short)dy;
	  ArcDn[ArcDnPtr].dz=(short)dz;
	  ArcDnPtr++;
      }
      temp += view.yskip;
    }
  }

  public static void DrawCylinder(int x1,int y1,int z1, int x2,int y2,int z2,
				  int c1,int c2,int rad)
  {
    int dbase;
    int fbase;

    int zrate,zerr,ystep,err;
    int ix,iy,ax,ay;
    int lx,ly,lz;
    int mid,tmp;
    int temp;

    if(rad > 25)
    {
      System.err.println("bond radius " + rad + " too large; set to 25");
      rad = 25;
    }
    if(false)
      System.err.println(x1 + "," + y1 + " - " + x2 + "," + y2 + " colors "
		       + c1 + " " + c2 + " radius " + rad);
    /* Trivial Case */
    if( (x1==x2) && (y1==y2) )
    {   if( z1>z2 )
        {      DrawSphere(x1,y1,z1,rad,c1);
        } else DrawSphere(x2,y2,z2,rad,c2);
        return;
    }

    if( z1<z2 )
    {   tmp=x1; x1=x2; x2=tmp;
        tmp=y1; y1=y2; y2=tmp;
        tmp=z1; z1=z2; z2=tmp;
        tmp=c1; c1=c2; c2=tmp;
    }

    DrawCylinderCaps(x1,y1,z1,x2,y2,z2,c1,c2,rad);

    lx = x2-x1;
    ly = y2-y1;
    lz = z2-z1;

    if( ly>0 ) { ystep = view.yskip; ay = ly; iy = 1; }
    else {   ystep = -view.yskip; ay = -ly; iy = -1; }
    if( lx>0 ) { ax = lx; ix = 1; }
    else { ax = -lx; ix = -1; }
    zrate = lz/Math.max(ax,ay);

    temp = y1*view.yskip+x1;
    fbase = temp;
    dbase = temp;

    if( ax>ay )
    {   lz -= ax*zrate;
        zerr = err = -(ax>>1);

        if( c1 != c2 )
        {   mid = (x1+x2)>>1;
            while( x1!=mid )
            {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ax; z1--; }
                fbase+=ix; dbase+=ix; x1+=ix;
                if( (err+=ay)>0 )
                {
		  fbase+=ystep; dbase+=ystep; err-=ax;
		  if(y1 >= 0 && y1 < view.ymax)
		    DrawArcDn(dbase,fbase,z1,c1);
                }
		else if(y1 >= 0 && y1 < view.ymax)
		  DrawArcAc(dbase,fbase,z1,c1);
            }
        }

        while( x1!=x2 )
        {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ax; z1--; }
            fbase+=ix; dbase+=ix; x1+=ix;
            if( (err+=ay)>0 )
            {
	      fbase+=ystep; dbase+=ystep; err-=ax;
	      if(y1 >= 0 && y1 < view.ymax)
		DrawArcDn(dbase,fbase,z1,c2);
            }
	    else if(y1 >= 0 && y1 < view.ymax)
	      DrawArcAc(dbase,fbase,z1,c2);
        }
    } else /*ay>=ax*/
    {   lz -= ay*zrate;
        zerr = err = -(ay>>1);

        if( c1 != c2 )
        {   mid = (y1+y2)>>1;
            while( y1!=mid )
            {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ay; z1--; }
                fbase+=ystep; dbase+=ystep; y1+=iy;
                if( (err+=ax)>0 )
                {
		  fbase+=ix; dbase+=ix; err-=ay; 
		  if(y1 >= 0 && y1 < view.ymax)
		    DrawArcAc(dbase,fbase,z1,c1);
                }
		if(y1 >= 0 && y1 < view.ymax)
		  DrawArcDn(dbase,fbase,z1,c1);
            }
        }

        while( y1!=y2)
        {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ay; z1--; }
            fbase+=ystep; dbase+=ystep; y1+=iy;
            if( (err+=ax)>0 )
            {
	      fbase+=ix; dbase+=ix; err-=ay;
	      if(y1 >= 0 && y1 < view.ymax)
		DrawArcAc(dbase,fbase,z1,c2);
            }
	    else if(y1 >= 0 && y1 < view.ymax)
	      DrawArcDn(dbase,fbase,z1,c2);
        }
    }
  }

  public static void DrawLine(int x1,int y1,int z1, int x2,int y2,int z2,
			      int color, int width)
  {
    int dbase;
    int fbase;

    int zrate,zerr,ystep,err;
    int ix,iy,ax,ay;
    int lx,ly,lz;
    int mid,tmp;
    int temp;

    if(width > 25)
    {
      System.err.println("bond radius " + width + " too large; set to 25");
      width = 25;
    }
    /* Trivial Case */
    if( (x1==x2) && (y1==y2) )
    {   if( z1>z2 )
        {      DrawSphere(x1,y1,z1,width,color);
        } else DrawSphere(x2,y2,z2,width,color);
        return;
    }
    
    if( z1<z2 )
    {   tmp=x1; x1=x2; x2=tmp;
        tmp=y1; y1=y2; y2=tmp;
        tmp=z1; z1=z2; z2=tmp;
    }

    lx = x2-x1;
    ly = y2-y1;
    lz = z2-z1;

    if( ly>0 ) { ystep = view.yskip; ay = ly; iy = 1; }
    else {   ystep = -view.yskip; ay = -ly; iy = -1; }
    if( lx>0 ) { ax = lx; ix = 1; }
    else { ax = -lx; ix = -1; }
    zrate = lz/Math.max(ax,ay);

    temp = y1*view.yskip+x1;
    fbase = temp;
    dbase = temp;

    if( ax>ay )
    {   lz -= ax*zrate;
        zerr = err = -(ax>>1);

        while( x1!=x2 )
        {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ax; z1--; }
            fbase+=ix; dbase+=ix; x1+=ix;
	    if( (err+=ay)>0 )
	    {
	      fbase+=ystep; dbase+=ystep; err-=ax;
	      if(x1 >= 0 && x1 < view.xmax && fbase > 0 && fbase < view.fbuf.length)
		UpdateLine(1, y1, color, z1, width, fbase, 0, view.ymax);
	    }
	    else if(x1 >= 0 && x1 < view.xmax && fbase > 0 && fbase < view.fbuf.length)
	      UpdateLine(1, y1, color, z1, width, fbase, 0, view.ymax);
        }
    } else /*ay>=ax*/
    {   lz -= ay*zrate;
        zerr = err = -(ay>>1);

        while( y1!=y2)
        {   z1 += zrate;  if( (zerr-=lz)>0 ) { zerr-=ay; z1--; }
            fbase+=ystep; dbase+=ystep; y1+=iy;
	    if( (err+=ax)>0 )
	    {
	      fbase+=ix; dbase+=ix; err-=ay;
	      if(y1 >= 0 && y1 < view.ymax && fbase > 0 && fbase < view.fbuf.length)
		UpdateLine(1, y1, color, z1, width, fbase, 0, view.ymax);
	    }
	    else if(y1 >= 0 && y1 < view.ymax && fbase > 0 && fbase < view.fbuf.length)
	      UpdateLine(1, y1, color, z1, width, fbase, 0, view.ymax);
        }
    }
  }


  private static void Resize(int high, int wide)
  {
    if(view == null || view.xmax != wide || view.ymax != high)
    {
      view = new ViewStruct(high,wide);
    }
  }
  public static ImageProducer imageProducer(int xmax, int ymax)
  {
    InitialiseTransform();
    Resize(ymax, xmax);
    t1 = System.currentTimeMillis();
    return new MemoryImageSource(view.xmax, view.ymax, colorModel,
				 view.fbuf, 0, view.yskip);
  }

}
/*

void AminoColourAttrib()
{
    ShadeRef *ref;
    Chain __far *chain;
    Group __far *group;
    Atom __far *ptr;
    int i;

    if( !Database ) return;
    for( i=0; i<13; i++ )
	AminoShade[i].col = 0;
    ResetColourAttrib();

    ForEachAtom
	if( ptr->flag&SelectFlag )
	{   if( IsAmino(group->refno) )
	    {   ref = AminoShade + AminoIndex[group->refno];
	    } else ref = AminoShade+12;

	    if( !ref->col )
	    {   ref->shade = DefineShade( ref->r, ref->g, ref->b );
		ref->col = Shade2Colour(ref->shade);
	    }
	    Shade[ref->shade].refcount++;
	    ptr->col = ref->col;
	}
}


void ShapelyColourAttrib()
{
    ShadeRef *ref;
    Chain __far *chain;
    Group __far *group;
    Atom __far *ptr;
    int i;

    if( !Database ) return;
    for( i=0; i<30; i++ )
	Shapely[i].col = 0;
    ResetColourAttrib();

    ForEachAtom
	if( ptr->flag&SelectFlag )
	{   if( IsAminoNucleo(group->refno) )
	    {   ref = Shapely + group->refno;
	    } else ref = Shapely+30;

	    if( !ref->col )
	    {   ref->shade = DefineShade( ref->r, ref->g, ref->b );
		ref->col = Shade2Colour(ref->shade);
	    }
	    Shade[ref->shade].refcount++;
	    ptr->col = ref->col;
	}
}


void StructColourAttrib()
{
    ShadeRef *ref;
    Chain __far *chain;
    Group __far *group;
    Atom __far *ptr;
    int i;

    if( !Database )
	return;

    if( InfoHelixCount<0 )
	DetermineStructure(False);

    for( i=0; i<4; i++ )
	StructShade[i].col = 0;
    ResetColourAttrib();

    ForEachAtom
	if( ptr->flag&SelectFlag )
	{   if( group->struc & HelixFlag )
	    {   ref = StructShade+1;
	    } else if( group->struc & SheetFlag )
	    {   ref = StructShade+2;
	    } else if( group->struc & TurnFlag )
	    {   ref = StructShade+3;
	    } else ref = StructShade;

	    if( !ref->col )
	    {   ref->shade = DefineShade( ref->r, ref->g, ref->b );
		ref->col = Shade2Colour(ref->shade);
	    }
	    Shade[ref->shade].refcount++;
	    ptr->col = ref->col;
	}
}

int IsCPKColour(atom ptr )
{
    ShadeRef *cpk;
    ShadeDesc *col;

    cpk = CPKShade + Element[ptr->elemno].cpkcol;
    col = Shade + Colour2Shade(ptr->col);
    return( (col->r==cpk->r) && 
	    (col->g==cpk->g) && 
	    (col->b==cpk->b) );
}


int IsVDWRadius( ptr )
    Atom __far *ptr;
{
    int rad;

    if( ptr->flag & SphereFlag )
    {   rad = ElemVDWRadius( ptr->elemno );
        return( ptr->radius == rad );
    } else return( False );
}
*/
