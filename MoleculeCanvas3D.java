package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.awt.image.ImageProducer;

public class MoleculeCanvas3D extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image backBuffer;
	MoleculeCanvas mc;

	MoleculeCanvas3D( MoleculeCanvas mc ){
		this.mc = mc;
	}

	public void prepareBackgroundBuffer(){
		ImageProducer prod = ShadeRef.imageProducer( mc.ww, mc.wh );
		ShadeRef.CPKColourAttrib( mc.m );
		backBuffer = createImage( prod );
	}

	public void paintSphere( int x, int y, int z, int rad, int col ){
		ShadeRef.DrawSphere( x, y, z, rad, col );
	}

	public void paintCylinder( int x1, int y1, int z1, int x2, int y2, int z2,
			int colorIndex1, int colorIndex2, int radius ){
		ShadeRef.DrawCylinder( x1, y1, z1, x2, y2, z2, colorIndex1,
				colorIndex2, radius );
	}

	public void setShiny( boolean on ){
		if ( on ) 
			ShadeRef.shiny = true;
		else
			ShadeRef.shiny = false;
	}	

	public void update( Graphics g ){
		paint( g );
	}

	public void paint( Graphics g ){
		g.drawImage( backBuffer, 0, 0, this );
	}
}
