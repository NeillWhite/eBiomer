package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.lang.*;
import java.util.*;

public class ThreeButtonDialog extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String button1, button2, button3;
	int boxWidth = 300;
	int boxHeight = 240;
	int whichButton[];
	Frame frame;

	public ThreeButtonDialog( Frame frame, String errorMessage, String title, String button1, 
			String button2, String button3, int whichButton[] ){
		super( frame, "eBiomer : " + title, true );
		reshape( 300, 300, boxWidth, boxHeight );
		setResizable( false );
		this.frame = frame;
		this.button1 = button1;
		this.button2 = button2;
		this.button3 = button3;
		this.whichButton = whichButton;

		FontMetrics fontMetrics = getFontMetrics( getFont() );
		int stringWidth = fontMetrics.stringWidth( errorMessage );
		int numberOfLabels = stringWidth / boxWidth + 2;
		Label stringLabel[] = new Label[ numberOfLabels ];
		parseString( errorMessage, stringLabel );
		Panel labelPanel = new Panel();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		labelPanel.setLayout( gridBagLayout );
		for( int i = 0; i < numberOfLabels; i++ ){
			gridBagConstraints.gridy = i;
			if ( stringLabel[ i ] != null ){
				gridBagLayout.setConstraints( stringLabel[ i ], gridBagConstraints );
				labelPanel.add( stringLabel[ i ] );	 
			}
		}	
		Panel p = new Panel();
		Button Button1 = new Button( button1 );
		Button Button2 = new Button( button2 );
		Button Button3 = new Button( button3 );
		p.add( Button1 );
		p.add( Button2 );
		p.add( Button3 );
		BorderLayout layout = new BorderLayout();
		setLayout( layout );
		add( "Center", labelPanel );
		add( "South", p );
	}

	public void parseString( String string, Label label[] ){
		int labelLength = label.length - 1;
		int lineLength = string.length() / labelLength;
		int snipPoint = lineLength;
		int i = 0;
		int startSnip = 0;
		while( startSnip <= string.length() ){
			if ( snipPoint != string.length() )
				snipPoint = string.lastIndexOf( " ", snipPoint );
			if ( ( snipPoint == -1 ) || ( startSnip == -1 ) ) break;
			label[ i++ ] = new Label( string.substring( startSnip, snipPoint ),
					Label.LEFT );
			startSnip = snipPoint + 1;
			snipPoint = ( startSnip + lineLength < string.length() ) ?
					startSnip + lineLength : string.length();
		}
	}

	public boolean handleEvent( Event event ){
		if ( event.id == Event.WINDOW_DESTROY )
			return action( event, button3 );
		else
			return super.handleEvent( event );
	}

	public boolean action( Event event, Object what ){
		if ( what == button1 ){
			whichButton[ 0 ] = 1;
			dispose();
			return true;
		}
		else if ( what == button2 ){
			whichButton[ 0 ] = 2;
			dispose();
			return true;
		}
		else if ( what == button3 ){
			whichButton[ 0 ] = 3;
			dispose();
			return true;
		}
		return false;
	}
}
