package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.io.InputStream;
import java.net.*;

public class SubImageButton extends ImageButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MoleculeCanvas c;
	boolean doubleclick = false;
	boolean mouseIsDown = false;
	boolean buttonIsDown = false;
	boolean LabelButton = false;
	static boolean LabelButtonDown = false;
	static SubImageButton LastButton;
	Graphics image;
	Event E;
	int X, Y;

	public SubImageButton( MoleculeCanvas c, InputStream is, String buttonName ){
		super( is, buttonName );
		this.c = c;
	}

	public SubImageButton( MoleculeCanvas c, URL url, String string ){
		super( url, string );
		this.c = c;
	}

	public boolean mouseUp(Event event, int x, int y) {
		E = event;
		X = x;
		Y = y;
		image = getGraphics();
		mouseIsDown = false;
		if ( getImageString().equals( "images/labels.gif" ) )
			LabelButton = true;
		else
			LabelButton = false;
		if (inside(x,y)) {
			if ( ( LastButton != null ) && ( LastButton != this ) ){
				if ( ! LabelButton ){
					LastButton.paint(LastButton.image);
					buttonIsDown = true;
					LastButton = this;
				}
				else{  // this is the Label button		
					if ( LabelButtonDown ){  // draw up
						paint(image);
						LabelButtonDown = false;
						buttonIsDown = false;
					}
					else {		// draw down
						super.mouseDown( event, x, y );
						LabelButtonDown = true;
						buttonIsDown = true;
					}
				}
			}
			else if ( ( LastButton == null ) && ( LabelButton ) ){
				LabelButtonDown = true;
				buttonIsDown = true;
			}
			else if ( LastButton == this ){		// same button
				if ( LastButton.buttonIsDown ){
					paint(image);
					buttonIsDown = false;
				}
				else {
					super.mouseDown( event, x, y );
					buttonIsDown = true;
				}
				LastButton = this;
			}
			else{
				buttonIsDown = true;
				LastButton = this;
			}
			event.id = Event.ACTION_EVENT;
			event.arg = (Object)getImage();
			return(action(event, event.arg));
		} 
		else
			return(false);
	}

	public boolean mouseDown(Event event, int x, int y){
		mouseIsDown = true;	
		if ( event.clickCount == 2 ){
			doubleclick = true;
		}
		else
			doubleclick = false;
		super.mouseDown( event, x, y );
		return true;
	}

	public boolean mouseExit(Event event, int x, int y) {
		if (mouseIsDown)
			paint(getGraphics());
		return(true);
	}

	public boolean action(Event event, Object button){
		String imagename = getImageString();
		if ( ! imagename.equals( "images/labels.gif" ) ){
			c.ROTATE = false;
			c.UNROTATE = false;
			c.DRAG = false;
			c.CENTER = false;
			c.ZOOM = false;
			c.UNZOOM = false;
			c.POINT = false;
			c.ATOMLABEL = false;
			c.LASSO = false;
			c.BUILD = false;
			c.COORD = false;
			c.DIST = false;
			c.ANGLE = false;
			c.TORSION = false;
			c.STOP = false;
			c.DELETE = false;
		}
		if ( imagename.equals( "images/rotate.gif" ) &&
				doubleclick ){
			//c.UNROTATE = true;
			c.unrotate();
			c.repaint();
			if (buttonIsDown)
				c.ROTATE = true;
			else
				c.ROTATE = false;
			doubleclick = false;
			return true;
		}
		if ( imagename.equals( "images/rotate.gif" ) ){
			if (buttonIsDown)
				c.ROTATE = true;
			else
				c.ROTATE = false;
			return true;
		}
		else if ( imagename.equals( "images/drag.gif" ) &&
				doubleclick ){
			//c.CENTER = true;
			c.center();
			c.repaint();
			if (buttonIsDown)
				c.DRAG = true;
			else
				c.DRAG = false;
			doubleclick = false;
			return true;
		}
		else if ( imagename.equals( "images/drag.gif" ) ){
			if (buttonIsDown)
				c.DRAG = true;
			else
				c.DRAG = false;
			return true;
		}
		else if ( imagename.equals( "images/zoom.gif" ) &&
				doubleclick ){
			//c.UNZOOM = true;
			c.unzoom();
			c.repaint();
			if (buttonIsDown)
				c.ZOOM = true;
			else
				c.ZOOM = false;
			doubleclick = false;
			return true;
		}
		else if ( imagename.equals( "images/zoom.gif" ) ){
			if (buttonIsDown)
				c.ZOOM = true;
			else
				c.ZOOM = false;
			return true;
		}
		else if ( imagename.equals( "images/point.gif" ) ){
			if (buttonIsDown)
				c.POINT = true;
			else
				c.POINT = false;
			return true;
		}
		else if ( imagename.equals( "images/select.gif" ) ){
			if (buttonIsDown)
				c.ATOMLABEL = true;
			else{
				c.ATOMLABEL = false;
				c.repaint();
			}
			return true;
		}
		else if ( imagename.equals( "images/coord.gif" ) ){
			if (buttonIsDown){
				c.COORD = true;
				c.atom1 = null;
				c.atom2 = null;
				c.atom3 = null;
				c.atom4 = null;
			}
			else{
				c.COORD = false;
				c.repaint();
			}
			return true;
		}
		else if ( imagename.equals( "images/distance.gif" ) ){
			if (buttonIsDown){
				c.DIST = true;
				c.atom1 = null;
				c.atom2 = null;
				c.atom3 = null;
				c.atom4 = null;
			}
			else{
				c.DIST = false;
				c.repaint();
			}
			return true;
		}
		else if ( imagename.equals( "images/angle.gif" ) ){
			if (buttonIsDown){
				c.ANGLE = true;
				c.atom1 = null;
				c.atom2 = null;
				c.atom3 = null;
				c.atom4 = null;
			}
			else{
				c.ANGLE = false;
				c.repaint();
			}
			return true;
		}
		else if ( imagename.equals( "images/torsion.gif" ) ){
			if (buttonIsDown){
				c.TORSION = true;
				c.atom1 = null;
				c.atom2 = null;
				c.atom3 = null;
				c.atom4 = null;
			}
			else{
				c.TORSION = false;
				c.repaint();
			}
			return true;
		}
		else if ( imagename.equals( "images/labels.gif" ) ){
			if ( buttonIsDown )
				c.LABELS = true;
			else
				c.LABELS = false;
			c.repaint();
			return true;
		}
		else if ( imagename.equals( "images/lasso.gif" ) ){
			if ( buttonIsDown )
				c.LASSO = true;
			else
				c.LASSO = false;
			return true;
		}
		else if ( imagename.equals( "images/build.gif" ) ){
			if ( buttonIsDown )
				c.BUILD = true;
			else
				c.BUILD = false;
			return true;
		}
		else
			return( false );
	}
}
