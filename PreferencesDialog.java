package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.lang.*;

public class PreferencesDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	Checkbox		chargeCheckbox, nameCheckbox, typeCheckbox, numberCheckbox;
	Checkbox		elementCheckbox;
	Choice			backgroundColorChoice, textColorChoice;
	Frame			chemStructFrame;
	ErrorDialog		error;
	MoleculeCanvas		c;

	PreferencesDialog( MoleculeCanvas c, Frame chemStructFrame ){
		super( chemStructFrame, "eBiomer : Preferences", true );
		setResizable( false );
		this.chemStructFrame = chemStructFrame;
		this.c = c;

		layoutConstraint = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout( gridBagLayout );

		Panel p = new Panel();
		Button b;

		Label label = new Label( "Background Color", Label.CENTER );
		p.add( label );

		backgroundColorChoice = new Choice();
		backgroundColorChoice.addItem( "black" );
		backgroundColorChoice.addItem( "blue" );
		backgroundColorChoice.addItem( "cyan" );
		backgroundColorChoice.addItem( "dark gray" );
		backgroundColorChoice.addItem( "gray" );
		backgroundColorChoice.addItem( "green" );
		backgroundColorChoice.addItem( "light gray" );
		backgroundColorChoice.addItem( "magenta" );
		backgroundColorChoice.addItem( "orange" );
		backgroundColorChoice.addItem( "pink" );
		backgroundColorChoice.addItem( "red" );
		backgroundColorChoice.addItem( "white" );
		backgroundColorChoice.addItem( "yellow" );
		backgroundColorChoice.select( 0 );

		p.add( backgroundColorChoice );

		add( p, 0, 0, -1 );

		p = new Panel();
		label = new Label( "Text Color", Label.CENTER );
		p.add( label );

		textColorChoice = new Choice();
		textColorChoice.addItem( "black" );
		textColorChoice.addItem( "blue" );
		textColorChoice.addItem( "cyan" );
		textColorChoice.addItem( "dark gray" );
		textColorChoice.addItem( "gray" );
		textColorChoice.addItem( "green" );
		textColorChoice.addItem( "light gray" );
		textColorChoice.addItem( "magenta" );
		textColorChoice.addItem( "orange" );
		textColorChoice.addItem( "pink" );
		textColorChoice.addItem( "red" );
		textColorChoice.addItem( "white" );
		textColorChoice.addItem( "yellow" );
		textColorChoice.select( 11 );

		p.add( textColorChoice );

		add( p, 0, 1, -1 );

		p = new Panel();

		label = new Label( "Atom Labels", Label.CENTER );
		p.add( label );

		Panel subPanel = new Panel();
		GridLayout gridLayout = new GridLayout( 5, 1 );
		subPanel.setLayout( gridLayout );

		chargeCheckbox = new Checkbox( "Charge", false );
		subPanel.add( chargeCheckbox );
		nameCheckbox = new Checkbox( "Name", true );
		subPanel.add( nameCheckbox );
		typeCheckbox = new Checkbox( "Type", false );
		subPanel.add( typeCheckbox );
		numberCheckbox = new Checkbox( "Number", true );
		subPanel.add( numberCheckbox );
		elementCheckbox = new Checkbox( "Element", true );
		subPanel.add( elementCheckbox );
		if ( c.chargeLabel )
			chargeCheckbox.setState( true );
		else
			chargeCheckbox.setState( false );
		if ( c.nameLabel )
			nameCheckbox.setState( true );
		else
			nameCheckbox.setState( false );
		if ( c.typeLabel )
			typeCheckbox.setState( true );
		else
			typeCheckbox.setState( false );
		if ( c.numberLabel )
			numberCheckbox.setState( true );
		else
			numberCheckbox.setState( false );
		if ( c.elementLabel )
			elementCheckbox.setState( true );
		else
			elementCheckbox.setState( false );

		p.add( subPanel );

		add( p, 0, 2, -1 );

		p = new Panel();
		b = new Button( "Cancel");
		p.add( b );

		b = new Button( "Done");
		p.add( b );
		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( p, 0, 3, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;


		reshape( 300, 300, 450, 250 );
	}

	public void add( Component c, int x, int y, int z ){
		layoutConstraint.gridx = x;
		layoutConstraint.gridy = y;
		if ( z == -1 )
			layoutConstraint.anchor = GridBagConstraints.WEST;
		else if ( z == 0 )
			layoutConstraint.anchor = GridBagConstraints.CENTER;
		else
			layoutConstraint.anchor = GridBagConstraints.EAST;
		gridBagLayout.setConstraints( c, layoutConstraint );
		add( c );
		return;
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
		if ( what == "Cancel" ){
			dispose();
			return true;
		}
		else if ( what == "Done" ){
			c.backgroundColor = getColor( backgroundColorChoice.getSelectedItem() );
			c.setBackground( c.backgroundColor );
			c.textColor = getColor( textColorChoice.getSelectedItem() );
			c.nameLabel = nameCheckbox.getState();
			c.elementLabel = elementCheckbox.getState();
			c.numberLabel = numberCheckbox.getState();
			c.typeLabel = typeCheckbox.getState();
			c.chargeLabel = chargeCheckbox.getState();
			c.repaint();
			dispose();
			return true;
		}
		return false;
	}

	public Color getColor( String color ){
		if ( color.equals( "black" ) )
			return ( Color.black );
		else if ( color.equals( "blue" ) )
			return ( Color.blue );
		else if ( color.equals( "cyan" ) )
			return ( Color.cyan );
		else if ( color.equals( "dark gray" ) )
			return ( Color.darkGray );
		else if ( color.equals( "green" ) )
			return ( Color.green );
		else if ( color.equals( "light gray" ) )
			return ( Color.lightGray );
		else if ( color.equals( "magenta" ) )
			return ( Color.magenta );
		else if ( color.equals( "orange" ) )
			return ( Color.orange );
		else if ( color.equals( "pink" ) )
			return ( Color.pink );
		else if ( color.equals( "red" ) )
			return ( Color.red );
		else if ( color.equals( "white" ) )
			return ( Color.white );
		else if ( color.equals( "yellow" ) )
			return ( Color.yellow );
		return( Color.black );
	}
}
