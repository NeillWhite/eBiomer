package com.goosebumpanalytics.biomer;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;

public class About extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Frame f;

	About( Frame f ){
		super( f, "eBiomer: About", true );

		setShape( new Rectangle(300,300,400,260) );
		setResizable( false );
		this.f = f;

		GridBagLayout layout = new GridBagLayout();
		GridLayout gridLayout = new GridLayout( 3, 1 );
		setLayout( layout );
		GridBagConstraints layoutConstraint = new GridBagConstraints();
		Panel p = new Panel();
		p.setLayout( gridLayout );
		Label label = new Label( "eBiomer v1.0", Label.CENTER );
		p.add( label );
		label = new Label( "by", Label.CENTER );
		p.add( label );
		label = new Label( "Neill White, 1999, 2018", Label.CENTER );
		p.add( label );
		layoutConstraint.gridx = 0;
		layoutConstraint.gridy = 0;
		layout.setConstraints( p, layoutConstraint );
		add( p );
		Button b;
		b = new Button("OK");
		layoutConstraint.gridx = 0;
		layoutConstraint.gridy = 1;
		layoutConstraint.anchor = GridBagConstraints.SOUTH;
		layout.setConstraints( b, layoutConstraint );
		add( b );  
	}

	public boolean action(Event event,Object what) {
		if (what == "OK") {
			dispose();
			return true;
		}
		return false;
	}

}
