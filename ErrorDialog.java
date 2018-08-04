package com.goosebumpanalytics.biomer;

import java.awt.*;

public class ErrorDialog extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String hint;
	int boxWidth = 360;
	int boxHeight = 240;
	Frame frame;

	public ErrorDialog( Frame frame, String errorMessage, String hint ){
		super( frame, "eBiomer: Error", true );
		setShape( new Rectangle( 300, 300, boxWidth, boxHeight ) );
		setResizable( false );
		this.hint = hint;
		this.frame = frame;

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
		Button okButton = new Button( "OK" );
		Button notOkButton = new Button( "Not OK" );
		p.add( okButton );
		p.add( notOkButton );
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
			return action( event, "OK" );
		else
			return super.handleEvent( event );
	}

	public boolean action( Event event, Object what ){
		if ( what == "OK" ){
			dispose();
			return true;
		}
		else if ( what == "Not OK" ){
			if ( hint != null ){
				dispose();
				ErrorDialog error = new ErrorDialog( frame, hint, null );
				error.show();
			}
			/*			FontMetrics fontMetrics = getFontMetrics( getFont() );
			int stringWidth = fontMetrics.stringWidth( hint );
			int numberOfLabels = stringWidth / boxWidth + 2;
			if ( numberOfLabels == 0 ) numberOfLabels = 1;
			Label stringLabel[] = new Label[ numberOfLabels ];
			parseString( hint, stringLabel );
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
					gridBagLayout.setConstraints( stringLabel[ i ], 
						gridBagConstraints );
					labelPanel.add( stringLabel[ i ] );	 
				}
			}	
			add( "Center", labelPanel );
			repaint();
			hide();
			show();  */
			return true;
		}
		return false;
	}
}
