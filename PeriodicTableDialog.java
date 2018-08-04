package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.lang.*;

public class PeriodicTableDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	MoleculeCanvas          c;
	Molecule                m;
	Frame			chemStructFrame;
	double			lastOmega = 180, lastPsi = 0, lastPhi = 0;
	boolean 		alpha = true, capped = false;
	Button bH, bHe, bLi, bBe, bB, bC, bN, bO, bF, bNe, bNa, bMg, bAl, bSi, bP, bS, bCl;
	Button bAr, bK, bCa, bSc, bTi, bV, bCr, bMn, bFe, bCo, bNi, bCu, bZn, bGa, bGe, bAs;
	Button bSe, bBr, bKr, bRb, bSr, bY, bZr, bNb, bMo, bTc, bRu, bRh, bPd, bAg, bCd, bIn;
	Button bSn, bSb, bTe, bI, bXe, bCs, bBa, bLa, bHf, bTa, bW, bRe, bOs, bIr, bPt, bAu;
	Button bHg, bTl, bPb, bBi, bPo, bAt, bRn, bFr, bAc, bUnq, bUnp, bUnh, bUns, bUno;
	Button bUne, bCe, bPr, bNd, bPm, bSm, bEu, bGd, bTb, bDy, bHo, bEr, bTm, bYb, bLu;
	Button bTh, bPa, bU, bNp, bPu, bAm, bCm, bBk, bCf, bEs, bFm, bMd, bNo, bLr;
	Button lastButton, bRa;

	PeriodicTableDialog( Molecule m, MoleculeCanvas c, Frame chemStructFrame ){
		super( chemStructFrame, "eBiomer : Periodic Table", false );
		setResizable( false );
		this.m = m;
		this.chemStructFrame = chemStructFrame;
		this.c = c;

		Panel p = new Panel();
		Button b;
		GridLayout gridLayout = new GridLayout( 9, 18 );
		setLayout( gridLayout );
		bH = new Button( "H" );
		bH.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bH );
		for( int i = 0; i < 16; i++ ){
			b = new Button();
			b.setEnabled(false);
			b.show( false );
			b.hide();
			add( b );
		}
		bHe = new Button( "He" );
		bHe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bHe );
		bLi = new Button( "Li" );
		bLi.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bLi );
		bBe = new Button( "Be" );
		bBe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bBe );
		for( int i = 0; i < 10; i++ ){
			b = new Button();
			b.disable();
			b.hide();
			add( b );
		}
		bB = new Button( "B" );
		bB.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bB );
		bC = new Button( "C" );
		bC.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bC );
		lastButton = bC;
		bN = new Button( "N" );
		bN.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bN );
		bO = new Button( "O" );
		bO.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bO );
		bF = new Button( "F" );
		bF.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bF );
		bNe = new Button( "Ne" );
		bNe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNe );
		bNa = new Button( "Na" );
		bNa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNa );
		bMg = new Button( "Mg" );
		bMg.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bMg );
		for( int i = 0; i < 10; i++ ){
			b = new Button();
			b.disable();
			b.hide();
			add( b );
		}
		bAl = new Button( "Al" );
		bAl.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAl );
		bSi = new Button( "Si" );
		bSi.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSi );
		bP = new Button( "P" );
		bP.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bP );
		bS = new Button( "S" );
		bS.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bS );
		bCl = new Button( "Cl" );
		bCl.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCl );
		bAr = new Button( "Ar" );
		bAr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAr );
		bK = new Button( "K" );
		bK.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bK );
		bCa = new Button( "Ca" );
		bCa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCa );
		bSc = new Button( "Sc" );
		bSc.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSc );
		bTi = new Button( "Ti" );
		bTi.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTi );
		bV = new Button( "V" );
		bV.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bV );
		bCr = new Button( "Cr" );
		bCr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCr );
		bMn = new Button( "Mn" );
		bMn.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bMn );
		bFe = new Button( "Fe" );
		bFe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bFe );
		bCo = new Button( "Co" );
		bCo.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCo );
		bNi = new Button( "Ni" );
		bNi.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNi );
		bCu = new Button( "Cu" );
		bCu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCu );
		bZn = new Button( "Zn" );
		bZn.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bZn );
		bGa = new Button( "Ga" );
		bGa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bGa );
		bGe = new Button( "Ge" );
		bGe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bGe );
		bAs = new Button( "As" );
		bAs.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAs );
		bSe = new Button( "Se" );
		bSe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSe );
		bBr = new Button( "Br" );
		bBr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bBr );
		bKr = new Button( "Kr" );
		bKr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bKr );
		bRb = new Button( "Rb" );
		bRb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRb );
		bSr = new Button( "Sr" );
		bSr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSr );
		bY = new Button( "Y" );
		bY.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bY );
		bZr = new Button( "Zr" );
		bZr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bZr );
		bNb = new Button( "Nb" );
		bNb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNb );
		bMo = new Button( "Mo" );
		bMo.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bMo );
		bTc = new Button( "Tc" );
		bTc.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTc );
		bRu = new Button( "Ru" );
		bRu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRu );
		bRh = new Button( "Rh" );
		bRh.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRh );
		bPd = new Button( "Pd" );
		bPd.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPd );
		bAg = new Button( "Ag" );
		bAg.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAg );
		bCd = new Button( "Cd" );
		bCd.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCd );
		bIn = new Button( "In" );
		bIn.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bIn );
		bSn = new Button( "Sn" );
		bSn.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSn );
		bSb = new Button( "Sb" );
		bSb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSb );
		bTe = new Button( "Te" );
		bTe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTe );
		bI = new Button( "I" );
		bI.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bI );
		bXe = new Button( "Xe" );
		bXe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bXe );
		bCs = new Button( "Cs" );
		bCs.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCs );
		bBa = new Button( "Ba" );
		bBa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bBa );
		bLa = new Button( "La" );
		bLa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bLa );
		bHf = new Button( "Hf" );
		bHf.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bHf );
		bTa = new Button( "Ta" );
		bTa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTa );
		bW = new Button( "W" );
		bW.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bW );
		bRe = new Button( "Re" );
		bRe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRe );
		bOs = new Button( "Os" );
		bOs.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bOs );
		bIr = new Button( "Ir" );
		bIr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bIr );
		bPt = new Button( "Pt" );
		bPt.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPt );
		bAu = new Button( "Au" );
		bAu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAu );
		bHg = new Button( "Hg" );
		bHg.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bHg );
		bTl = new Button( "Tl" );
		bTl.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTl );
		bPb = new Button( "Pb" );
		bPb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPb );
		bBi = new Button( "Bi" );
		bBi.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bBi );
		bPo = new Button( "Po" );
		bPo.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPo );
		bAt = new Button( "At" );
		bAt.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAt );
		bRn = new Button( "Rn" );
		bRn.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRn );
		bFr = new Button( "Fr" );
		bFr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bFr );
		bRa = new Button( "Ra" );
		bRa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bRa );
		bAc = new Button( "Ac" );
		bAc.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAc );
		bUnq = new Button( "Unq" );
		bUnq.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUnq );
		bUnp = new Button( "Unp" );
		bUnp.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUnp );
		bUnh = new Button( "Unh" );
		bUnh.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUnh );
		bUns = new Button( "Uns" );
		bUns.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUns );
		bUno = new Button( "Uno" );
		bUno.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUno );
		bUne = new Button( "Une" );
		bUne.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bUne );
		for( int i = 0; i < 13; i++ ){
			b = new Button();
			b.setEnabled(false);
			b.hide();
			add( b );
		}
		bCe = new Button( "Ce" );
		bCe.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCe );
		bPr = new Button( "Pr" );
		bPr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPr );
		bNd = new Button( "Nd" );
		bNd.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNd );
		bPm = new Button( "Pm" );
		bPm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPm );
		bSm = new Button( "Sm" );
		bSm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bSm );
		bEu = new Button( "Eu" );
		bEu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bEu );
		bGd = new Button( "Gd" );
		bGd.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bGd );
		bTb = new Button( "Tb" );
		bTb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTb );
		bDy = new Button( "Dy" );
		bDy.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bDy );
		bHo = new Button( "Ho" );
		bHo.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bHo );
		bEr = new Button( "Er" );
		bEr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bEr );
		bTm = new Button( "Tm" );
		bTm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTm );
		bYb = new Button( "Yb" );
		bYb.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bYb );
		bLu = new Button( "Lu" );
		bLu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bLu );
		for( int i = 0; i < 4; i++ ){
			b = new Button();
			b.setEnabled(false);
			b.hide();
			add( b );
		}
		bTh = new Button( "Th" );
		bTh.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bTh );
		bPa = new Button( "Pa" );
		bPa.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPa );
		bU = new Button( "U" );
		bU.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bU );
		bNp = new Button( "Np" );
		bNp.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNp );
		bPu = new Button( "Pu" );
		bPu.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bPu );
		bAm = new Button( "Am" );
		bAm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bAm );
		bCm = new Button( "Cm" );
		bCm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCm );
		bBk = new Button( "Bk" );
		bBk.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bBk );
		bCf = new Button( "Cf" );
		bCf.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bCf );
		bEs = new Button( "Es" );
		bEs.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bEs );
		bFm = new Button( "Fm" );
		bFm.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bFm );
		bMd = new Button( "Md" );
		bMd.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bMd );
		bNo = new Button( "No" );
		bNo.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bNo );
		bLr = new Button( "Lr" );
		bLr.setFont( new Font( "Helvetica", Font.BOLD, 12 ) );
		add( bLr );

		reshape( 300, 300, 500, 250 );
	}

	public boolean handleEvent( Event event ){
		if ( event.id == Event.WINDOW_DESTROY ){                        
			dispose();
			return true;
		}
		else
			return super.handleEvent( event );
	}

	public boolean action( Event event, Object what ){
		if ( what == "H" ){
			lastButton.setBackground( Color.lightGray );
			bH.setBackground( Color.red );
			lastButton = bH;
		}
		else if ( what == "He" ){
			lastButton.setBackground( Color.lightGray );
			bHe.setBackground( Color.red );
			lastButton = bHe;
		}
		else if ( what == "Li" ){
			lastButton.setBackground( Color.lightGray );
			bLi.setBackground( Color.red );
			lastButton = bLi;
		}
		else if ( what == "Be" ){
			lastButton.setBackground( Color.lightGray );
			bBe.setBackground( Color.red );
			lastButton = bBe;
		}
		else if ( what == "B" ){
			lastButton.setBackground( Color.lightGray );
			bB.setBackground( Color.red );
			lastButton = bB;
		}
		else if ( what == "C" ){
			lastButton.setBackground( Color.lightGray );
			bC.setBackground( Color.red );
			lastButton = bC;
		}
		else if ( what == "N" ){
			lastButton.setBackground( Color.lightGray );
			bN.setBackground( Color.red );
			lastButton = bN;
		}
		else if ( what == "O" ){
			lastButton.setBackground( Color.lightGray );
			bO.setBackground( Color.red );
			lastButton = bO;
		}
		else if ( what == "F" ){
			lastButton.setBackground( Color.lightGray );
			bF.setBackground( Color.red );
			lastButton = bF;
		}
		else if ( what == "Ne" ){
			lastButton.setBackground( Color.lightGray );
			bNe.setBackground( Color.red );
			lastButton = bNe;
		}
		else if ( what == "Na" ){
			lastButton.setBackground( Color.lightGray );
			bNa.setBackground( Color.red );
			lastButton = bNa;
		}
		else if ( what == "Mg" ){
			lastButton.setBackground( Color.lightGray );
			bMg.setBackground( Color.red );
			lastButton = bMg;
		}
		else if ( what == "Al" ){
			lastButton.setBackground( Color.lightGray );
			bAl.setBackground( Color.red );
			lastButton = bAl;
		}
		else if ( what == "Si" ){
			lastButton.setBackground( Color.lightGray );
			bSi.setBackground( Color.red );
			lastButton = bSi;
		}
		else if ( what == "P" ){
			lastButton.setBackground( Color.lightGray );
			bP.setBackground( Color.red );
			lastButton = bP;
		}
		else if ( what == "S" ){
			lastButton.setBackground( Color.lightGray );
			bS.setBackground( Color.red );
			lastButton = bS;
		}
		else if ( what == "Cl" ){
			lastButton.setBackground( Color.lightGray );
			bCl.setBackground( Color.red );
			lastButton = bCl;
		}
		else if ( what == "Ar" ){
			lastButton.setBackground( Color.lightGray );
			bAr.setBackground( Color.red );
			lastButton = bAr;
		}
		else if ( what == "K" ){
			lastButton.setBackground( Color.lightGray );
			bK.setBackground( Color.red );
			lastButton = bK;
		}
		else if ( what == "Ca" ){
			lastButton.setBackground( Color.lightGray );
			bCa.setBackground( Color.red );
			lastButton = bCa;
		}
		else if ( what == "Sc" ){
			lastButton.setBackground( Color.lightGray );
			bSc.setBackground( Color.red );
			lastButton = bSc;
		}
		else if ( what == "Ti" ){
			lastButton.setBackground( Color.lightGray );
			bTi.setBackground( Color.red );
			lastButton = bTi;
		}
		else if ( what == "V" ){
			lastButton.setBackground( Color.lightGray );
			bV.setBackground( Color.red );
			lastButton = bV;
		}
		else if ( what == "Cr" ){
			lastButton.setBackground( Color.lightGray );
			bCr.setBackground( Color.red );
			lastButton = bCr;
		}
		else if ( what == "Mn" ){
			lastButton.setBackground( Color.lightGray );
			bMn.setBackground( Color.red );
			lastButton = bMn;
		}
		else if ( what == "Fe" ){
			lastButton.setBackground( Color.lightGray );
			bFe.setBackground( Color.red );
			lastButton = bFe;
		}
		else if ( what == "Co" ){
			lastButton.setBackground( Color.lightGray );
			bCo.setBackground( Color.red );
			lastButton = bCo;
		}
		else if ( what == "Ni" ){
			lastButton.setBackground( Color.lightGray );
			bNi.setBackground( Color.red );
			lastButton = bNi;
		}
		else if ( what == "Cu" ){
			lastButton.setBackground( Color.lightGray );
			bCu.setBackground( Color.red );
			lastButton = bCu;
		}
		else if ( what == "Zn" ){
			lastButton.setBackground( Color.lightGray );
			bZn.setBackground( Color.red );
			lastButton = bZn;
		}
		else if ( what == "Ga" ){
			lastButton.setBackground( Color.lightGray );
			bGa.setBackground( Color.red );
			lastButton = bGa;
		}
		else if ( what == "Ge" ){
			lastButton.setBackground( Color.lightGray );
			bGe.setBackground( Color.red );
			lastButton = bGe;
		}
		else if ( what == "As" ){
			lastButton.setBackground( Color.lightGray );
			bAs.setBackground( Color.red );
			lastButton = bAs;
		}
		else if ( what == "Se" ){
			lastButton.setBackground( Color.lightGray );
			bSe.setBackground( Color.red );
			lastButton = bSe;
		}
		else if ( what == "Br" ){
			lastButton.setBackground( Color.lightGray );
			bBr.setBackground( Color.red );
			lastButton = bBr;
		}
		else if ( what == "Kr" ){
			lastButton.setBackground( Color.lightGray );
			bKr.setBackground( Color.red );
			lastButton = bKr;
		}
		else if ( what == "Rb" ){
			lastButton.setBackground( Color.lightGray );
			bRb.setBackground( Color.red );
			lastButton = bRb;
		}
		else if ( what == "Sr" ){
			lastButton.setBackground( Color.lightGray );
			bSr.setBackground( Color.red );
			lastButton = bSr;
		}
		else if ( what == "Y" ){
			lastButton.setBackground( Color.lightGray );
			bY.setBackground( Color.red );
			lastButton = bY;
		}
		else if ( what == "Zr" ){
			lastButton.setBackground( Color.lightGray );
			bZr.setBackground( Color.red );
			lastButton = bZr;
		}
		else if ( what == "Nb" ){
			lastButton.setBackground( Color.lightGray );
			bNb.setBackground( Color.red );
			lastButton = bNb;
		}
		else if ( what == "Mo" ){
			lastButton.setBackground( Color.lightGray );
			bMo.setBackground( Color.red );
			lastButton = bMo;
		}
		else if ( what == "Tc" ){
			lastButton.setBackground( Color.lightGray );
			bTc.setBackground( Color.red );
			lastButton = bTc;
		}
		else if ( what == "Ru" ){
			lastButton.setBackground( Color.lightGray );
			bRu.setBackground( Color.red );
			lastButton = bRu;
		}
		else if ( what == "Rh" ){
			lastButton.setBackground( Color.lightGray );
			bRh.setBackground( Color.red );
			lastButton = bRh;
		}
		else if ( what == "Pd" ){
			lastButton.setBackground( Color.lightGray );
			bPd.setBackground( Color.red );
			lastButton = bPd;
		}
		else if ( what == "Ag" ){
			lastButton.setBackground( Color.lightGray );
			bAg.setBackground( Color.red );
			lastButton = bAg;
		}
		else if ( what == "Cd" ){
			lastButton.setBackground( Color.lightGray );
			bCd.setBackground( Color.red );
			lastButton = bCd;
		}
		else if ( what == "In" ){
			lastButton.setBackground( Color.lightGray );
			bIn.setBackground( Color.red );
			lastButton = bIn;
		}
		else if ( what == "Sn" ){
			lastButton.setBackground( Color.lightGray );
			bSn.setBackground( Color.red );
			lastButton = bSn;
		}
		else if ( what == "Sb" ){
			lastButton.setBackground( Color.lightGray );
			bSb.setBackground( Color.red );
			lastButton = bSb;
		}
		else if ( what == "Te" ){
			lastButton.setBackground( Color.lightGray );
			bTe.setBackground( Color.red );
			lastButton = bTe;
		}
		else if ( what == "I" ){
			lastButton.setBackground( Color.lightGray );
			bI.setBackground( Color.red );
			lastButton = bI;
		}
		else if ( what == "Xe" ){
			lastButton.setBackground( Color.lightGray );
			bXe.setBackground( Color.red );
			lastButton = bXe;
		}
		else if ( what == "Cs" ){
			lastButton.setBackground( Color.lightGray );
			bCs.setBackground( Color.red );
			lastButton = bCs;
		}
		else if ( what == "Ba" ){
			lastButton.setBackground( Color.lightGray );
			bBa.setBackground( Color.red );
			lastButton = bBa;
		}
		else if ( what == "La" ){
			lastButton.setBackground( Color.lightGray );
			bLa.setBackground( Color.red );
			lastButton = bLa;
		}
		else if ( what == "Hf" ){
			lastButton.setBackground( Color.lightGray );
			bHf.setBackground( Color.red );
			lastButton = bHf;
		}
		else if ( what == "Ta" ){
			lastButton.setBackground( Color.lightGray );
			bTa.setBackground( Color.red );
			lastButton = bTa;
		}
		else if ( what == "W" ){
			lastButton.setBackground( Color.lightGray );
			bW.setBackground( Color.red );
			lastButton = bW;
		}
		else if ( what == "Re" ){
			lastButton.setBackground( Color.lightGray );
			bRe.setBackground( Color.red );
			lastButton = bRe;
		}
		else if ( what == "Os" ){
			lastButton.setBackground( Color.lightGray );
			bOs.setBackground( Color.red );
			lastButton = bOs;
		}
		else if ( what == "Ir" ){
			lastButton.setBackground( Color.lightGray );
			bIr.setBackground( Color.red );
			lastButton = bIr;
		}
		else if ( what == "Pt" ){
			lastButton.setBackground( Color.lightGray );
			bPt.setBackground( Color.red );
			lastButton = bPt;
		}
		else if ( what == "Au" ){
			lastButton.setBackground( Color.lightGray );
			bAu.setBackground( Color.red );
			lastButton = bAu;
		}
		else if ( what == "Hg" ){
			lastButton.setBackground( Color.lightGray );
			bHg.setBackground( Color.red );
			lastButton = bHg;
		}
		else if ( what == "Tl" ){
			lastButton.setBackground( Color.lightGray );
			bTl.setBackground( Color.red );
			lastButton = bTl;
		}
		else if ( what == "Pb" ){
			lastButton.setBackground( Color.lightGray );
			bPb.setBackground( Color.red );
			lastButton = bPb;
		}
		else if ( what == "Bi" ){
			lastButton.setBackground( Color.lightGray );
			bBi.setBackground( Color.red );
			lastButton = bBi;
		}
		else if ( what == "Po" ){
			lastButton.setBackground( Color.lightGray );
			bPo.setBackground( Color.red );
			lastButton = bPo;
		}
		else if ( what == "At" ){
			lastButton.setBackground( Color.lightGray );
			bAt.setBackground( Color.red );
			lastButton = bAt;
		}
		else if ( what == "Rn" ){
			lastButton.setBackground( Color.lightGray );
			bRn.setBackground( Color.red );
			lastButton = bRn;
		}
		else if ( what == "Fr" ){
			lastButton.setBackground( Color.lightGray );
			bFr.setBackground( Color.red );
			lastButton = bFr;
		}
		else if ( what == "Ra" ){
			lastButton.setBackground( Color.lightGray );
			bRa.setBackground( Color.red );
			lastButton = bRa;
		}
		else if ( what == "Ac" ){
			lastButton.setBackground( Color.lightGray );
			bAc.setBackground( Color.red );
			lastButton = bAc;
		}
		else if ( what == "Unq" ){
			lastButton.setBackground( Color.lightGray );
			bUnq.setBackground( Color.red );
			lastButton = bUnq;
		}
		else if ( what == "Unp" ){
			lastButton.setBackground( Color.lightGray );
			bUnp.setBackground( Color.red );
			lastButton = bUnp;
		}
		else if ( what == "Unh" ){
			lastButton.setBackground( Color.lightGray );
			bUnh.setBackground( Color.red );
			lastButton = bUnh;
		}
		else if ( what == "Uns" ){
			lastButton.setBackground( Color.lightGray );
			bUns.setBackground( Color.red );
			lastButton = bUns;
		}
		else if ( what == "Uno" ){
			lastButton.setBackground( Color.lightGray );
			bUno.setBackground( Color.red );
			lastButton = bUno;
		}
		else if ( what == "Une" ){
			lastButton.setBackground( Color.lightGray );
			bUne.setBackground( Color.red );
			lastButton = bUne;
		}
		else if ( what == "Ce" ){
			lastButton.setBackground( Color.lightGray );
			bCe.setBackground( Color.red );
			lastButton = bCe;
		}
		else if ( what == "Pr" ){
			lastButton.setBackground( Color.lightGray );
			bPr.setBackground( Color.red );
			lastButton = bPr;
		}
		else if ( what == "Nd" ){
			lastButton.setBackground( Color.lightGray );
			bNd.setBackground( Color.red );
			lastButton = bNd;
		}
		else if ( what == "Pm" ){
			lastButton.setBackground( Color.lightGray );
			bPm.setBackground( Color.red );
			lastButton = bPm;
		}
		else if ( what == "Sm" ){
			lastButton.setBackground( Color.lightGray );
			bSm.setBackground( Color.red );
			lastButton = bSm;
		}
		else if ( what == "Eu" ){
			lastButton.setBackground( Color.lightGray );
			bEu.setBackground( Color.red );
			lastButton = bEu;
		}
		else if ( what == "Gd" ){
			lastButton.setBackground( Color.lightGray );
			bGd.setBackground( Color.red );
			lastButton = bGd;
		}
		else if ( what == "Tb" ){
			lastButton.setBackground( Color.lightGray );
			bTb.setBackground( Color.red );
			lastButton = bTb;
		}
		else if ( what == "Dy" ){
			lastButton.setBackground( Color.lightGray );
			bDy.setBackground( Color.red );
			lastButton = bDy;
		}
		else if ( what == "Ho" ){
			lastButton.setBackground( Color.lightGray );
			bHo.setBackground( Color.red );
			lastButton = bHo;
		}
		else if ( what == "Er" ){
			lastButton.setBackground( Color.lightGray );
			bEr.setBackground( Color.red );
			lastButton = bEr;
		}
		else if ( what == "Tm" ){
			lastButton.setBackground( Color.lightGray );
			bTm.setBackground( Color.red );
			lastButton = bTm;
		}
		else if ( what == "Yb" ){
			lastButton.setBackground( Color.lightGray );
			bYb.setBackground( Color.red );
			lastButton = bYb;
		}
		else if ( what == "Lu" ){
			lastButton.setBackground( Color.lightGray );
			bLu.setBackground( Color.red );
			lastButton = bLu;
		}
		else if ( what == "Th" ){
			lastButton.setBackground( Color.lightGray );
			bTh.setBackground( Color.red );
			lastButton = bTh;
		}
		else if ( what == "Pa" ){
			lastButton.setBackground( Color.lightGray );
			bPa.setBackground( Color.red );
			lastButton = bPa;
		}
		else if ( what == "U" ){
			lastButton.setBackground( Color.lightGray );
			bU.setBackground( Color.red );
			lastButton = bU;
		}
		else if ( what == "Np" ){
			lastButton.setBackground( Color.lightGray );
			bNp.setBackground( Color.red );
			lastButton = bNp;
		}
		else if ( what == "Pu" ){
			lastButton.setBackground( Color.lightGray );
			bPu.setBackground( Color.red );
			lastButton = bPu;
		}
		else if ( what == "Am" ){
			lastButton.setBackground( Color.lightGray );
			bAm.setBackground( Color.red );
			lastButton = bAm;
		}
		else if ( what == "Cm" ){
			lastButton.setBackground( Color.lightGray );
			bCm.setBackground( Color.red );
			lastButton = bCm;
		}
		else if ( what == "Bk" ){
			lastButton.setBackground( Color.lightGray );
			bBk.setBackground( Color.red );
			lastButton = bBk;
		}
		else if ( what == "Cf" ){
			lastButton.setBackground( Color.lightGray );
			bCf.setBackground( Color.red );
			lastButton = bCf;
		}
		else if ( what == "Es" ){
			lastButton.setBackground( Color.lightGray );
			bEs.setBackground( Color.red );
			lastButton = bEs;
		}
		else if ( what == "Fm" ){
			lastButton.setBackground( Color.lightGray );
			bFm.setBackground( Color.red );
			lastButton = bFm;
		}
		else if ( what == "Md" ){
			lastButton.setBackground( Color.lightGray );
			bMd.setBackground( Color.red );
			lastButton = bMd;
		}
		else if ( what == "No" ){
			lastButton.setBackground( Color.lightGray );
			bNo.setBackground( Color.red );
			lastButton = bNo;
		}
		else if ( what == "Lr" ){
			lastButton.setBackground( Color.lightGray );
			bLr.setBackground( Color.red );
			lastButton = bLr;
		}
		c.currentAtomName = what.toString();
		return( true );
	}


}

