package com.goosebumpanalytics.biomer;

import java.awt.*;

public class NucleicAcidDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints	layoutConstraint;
	GridBagLayout		layout;
	MoleculeCanvas		c;
	Molecule		m;
	Residue 		lastAntiResidue = null, lastSenseResidue = null;
	final double		R2D = 57.29577951308232090712;
	final double		D2R = Math.PI / 180.0, DELTA = 0.0005;
	NucleicAcidResidue 	NAR;
	Button			atButton, taButton, customBasepairButton;
	Choice			dnaChoice, strandChoice, puckerChoice, naChoice;
	final float		AT_SEP = 10.44f, CG_SEP = 10.72f, ADE_ANGLE = 33.8f;
	final float		CYT_ANGLE = 34.3f, GUA_ANGLE = 35.6f, THY_ANGLE = 32.6f;
	TextField		alphaTextField, betaTextField, gammaTextField, deltaTextField;
	TextField		epsilonTextField, zetaTextField, chiTextField;
	TextField		nu0TextField, nu1TextField, nu2TextField, nu3TextField; 
	TextField		nu4TextField, numaxTextField, tipTextField, inclinationTextField;
	TextField		openingTextField, propellerTwistTextField, buckleTextField; 
	TextField		twistTextField, rollTextField, tiltTextField, chainTextField;
	TextField		dxTextField, dyTextField, shearTextField, stretchTextField; 
	TextField		staggerTextField, shiftTextField, slideTextField, riseTextField;
	String			customBondingChoice = "Watson Crick", custom5Name = "ADE"; 
	String			custom3Name = "ADE", customBasepairName = "ADE-(ADE)";
	float			alpha, beta, gamma, delta, epsilon, zeta, chi, nu0, nu1;
	float			nu2, nu3, nu4, tip, inclination, opening, propellerTwist;	
	float			buckle, twist, roll, tilt, Dx, Dy, shear, stretch, stagger;
	float			shift, slide, rise, pseudorotation, nuMax;
	float			totalRise = 0, previousTip = 0, previousInclination = 0;
	float			previousDy = 0, previousDx = 0;
	float			totalTwist = 0, xOffset = 2.485f;
	float			slideVector[] = new float[ 3 ];
	float			sugarPucker = 20.0f;
	Checkbox		counterIonsCheckbox;
	Container 		parent;
	Choice 			newdnaChoice, newLeftBasePairChoice, newRightBasePairChoice;
	Choice			leftBasepairChoice, rightBasepairChoice, hbondingChoice;
	Choice			buildChoice;
	Component 		comp;
	Panel			p;
	Label			label;
	Frame			BiomerFrame;
	int			custom5Value = 0, custom3Value = 0;
	int			zdnaSwitch = 0;
	boolean			capped3End = false, capped5End = false, oddBasepair = true;
	boolean			zform = false;
	ErrorDialog		error;

	NucleicAcidDialog( Molecule m, MoleculeCanvas c, Frame BiomerFrame ){
		super( BiomerFrame, "eBiomer : Build Nucleic Acid", true );
		setResizable( false );
		this.m = m;
		this.BiomerFrame = BiomerFrame;
		this.c = c;

		Button		b;
		Checkbox	CB;
		int 		gridYCounter = 0;

		NAR = new NucleicAcidResidue();

		slideVector[ 0 ] = 0.0f;
		slideVector[ 1 ] = 0.0f;
		slideVector[ 2 ] = 0.0f;

		layoutConstraint = new GridBagConstraints();
		layout = new GridBagLayout();
		setLayout( layout );

		layoutConstraint.fill = GridBagConstraints.HORIZONTAL;

		naChoice = new Choice();
		naChoice.addItem("DNA");
		naChoice.addItem("RNA");
		add( naChoice, 0, gridYCounter++, 0 );

		dnaChoice = new Choice();
		dnaChoice.addItem("a-form");
		dnaChoice.addItem("a'-form");
		dnaChoice.addItem("b-form");
		dnaChoice.addItem("b'-form");
		dnaChoice.addItem("c-form");
		dnaChoice.addItem("c'-form");
		dnaChoice.addItem("c''-form");
		dnaChoice.addItem("d-form");
		dnaChoice.addItem("e-form");
		dnaChoice.addItem("t-form");
		dnaChoice.addItem("z-form");
		dnaChoice.addItem("custom");
		dnaChoice.select("b-form");
		add( dnaChoice, 0, gridYCounter++, 0 );

		buildChoice = new Choice();
		buildChoice.addItem("Build 5' to 3'");
		buildChoice.addItem("Build 3' to 5'");
		add( buildChoice, 0, gridYCounter++, 0 );

		strandChoice = new Choice();
		strandChoice.addItem("Single Stranded");
		strandChoice.addItem("Double Stranded");
		strandChoice.select("Double Stranded");
		add( strandChoice, 0, gridYCounter++, 0 );

		CB = new Checkbox( "Minimize Backbone" );
		CB.setState( false );
		CB.disable();
		add( CB, 0, gridYCounter++, 0  );

		counterIonsCheckbox = new Checkbox( "Add Counter Ions" );
		counterIonsCheckbox.setState( false );
		add( counterIonsCheckbox, 0, gridYCounter++, 0  );

		chainTextField = new TextField( 9 );
		chainTextField.setEditable( false );
		add( chainTextField, 0, gridYCounter++, 0 );

		layoutConstraint.fill = GridBagConstraints.BOTH;
		b = new Button( "Help" );
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 0, gridYCounter++, 0 );
		b = new Button( "Cancel" );
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 0, gridYCounter++, 0 );
		b = new Button( "Done" );
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 0, gridYCounter++, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		gridYCounter = 0;

		/*		hbondingChoice = new Choice();
		hbondingChoice.addItem("Watson-Crick");
		hbondingChoice.addItem("(R) Watson-Crick");
		hbondingChoice.addItem("Hoogsteen");
		hbondingChoice.addItem("(R) Hoogsteen");
		hbondingChoice.addItem("pur-pur I");
		hbondingChoice.addItem("pur-pur II");
		hbondingChoice.addItem("pur-pur III");
		hbondingChoice.addItem("pur-pur IV");
		hbondingChoice.addItem("pur-pur V");
		hbondingChoice.addItem("(R) pur-pur V");
		hbondingChoice.addItem("pur-pur VI");
		hbondingChoice.addItem("(R) pur-pur VI");
		hbondingChoice.addItem("pur-pur VII");
		hbondingChoice.addItem("(R) pur-pur VII");
		hbondingChoice.addItem("pur-pur VIII");
		hbondingChoice.addItem("pur-pur IX");
		hbondingChoice.addItem("pur-pur X");
		hbondingChoice.addItem("pur-pur XI");
		hbondingChoice.addItem("pyr-pyr XII");
		hbondingChoice.addItem("pyr-pyr XIII");
		hbondingChoice.addItem("pyr-pyr XIV");
		hbondingChoice.addItem("pyr-pyr XV");
		hbondingChoice.addItem("pyr-pyr XVI");
		hbondingChoice.addItem("(R) pyr-pyr XVI");
		hbondingChoice.addItem("pyr-pyr XVII");
		hbondingChoice.addItem("pyr-pyr XVIII");
		hbondingChoice.addItem("pur-pyr XXV");
		hbondingChoice.addItem("pur-pyr XXVI");
		hbondingChoice.addItem("pur-pyr XXVII");
		hbondingChoice.addItem("pur-pyr XXVIII");
		add( hbondingChoice, 0, gridYCounter++, 0 );
		 */
		gridYCounter = 0;

		label = new Label( "Rotational", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 1, gridYCounter++, 0 );	
		label = new Label( "Helical Parameters", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 1, gridYCounter++, 0 );	

		p = new Panel();
		label = new Label( "tip", Label.LEFT );
		p.add( label );	
		tipTextField = new TextField( "0.0", 5 );
		tipTextField.setEditable( false );
		p.add( tipTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "inclination", Label.LEFT );
		p.add( label );	
		inclinationTextField = new TextField( "2.4", 5 );
		inclinationTextField.setEditable( false );
		p.add( inclinationTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "opening", Label.LEFT );
		p.add( label );	
		openingTextField = new TextField( "0.0", 5 );
		openingTextField.setEditable( false );
		p.add( openingTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "Propeller twist", Label.LEFT );
		p.add( label );	
		propellerTwistTextField = new TextField( "-11.1", 5 );
		propellerTwistTextField.setEditable( false );
		p.add( propellerTwistTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "buckle", Label.LEFT );
		p.add( label );	
		buckleTextField = new TextField( "-0.2", 5 );
		buckleTextField.setEditable( false );
		p.add( buckleTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "twist", Label.LEFT );
		p.add( label );	
		twistTextField = new TextField( "36.1", 5 );
		twistTextField.setEditable( false );
		p.add( twistTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "roll", Label.LEFT );
		p.add( label );	
		rollTextField = new TextField( "0.6", 5 );
		rollTextField.setEditable( false );
		p.add( rollTextField );
		add( p, 1, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "tilt", Label.LEFT );
		p.add( label );	
		tiltTextField = new TextField( "0.0", 5 );
		tiltTextField.setEditable( false );
		p.add( tiltTextField );
		add( p, 1, gridYCounter++, 1 );

		gridYCounter = 0;

		label = new Label( "Translational", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 2, gridYCounter++, 0 );	
		label = new Label( "Helical Parameters", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 2, gridYCounter++, 0 );	

		p = new Panel();
		label = new Label( "X-Displacement", Label.LEFT );
		p.add( label );	
		dxTextField = new TextField( "0.0", 5 );
		dxTextField.setEditable( false );
		p.add( dxTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "Y-Displacement", Label.LEFT );
		p.add( label );	
		dyTextField = new TextField( "0.0", 5 );
		dyTextField.setEditable( false );
		p.add( dyTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "shear (Sx)", Label.LEFT );
		p.add( label );	
		shearTextField = new TextField( "0.0", 5 );
		shearTextField.setEditable( false );
		p.add( shearTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "stretch (Sy)", Label.LEFT );
		p.add( label );	
		stretchTextField = new TextField( "0.0", 5 );
		stretchTextField.setEditable( false );
		p.add( stretchTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "stagger (Sz)", Label.LEFT );
		p.add( label );	
		staggerTextField = new TextField( "0.0", 5 );
		staggerTextField.setEditable( false );
		p.add( staggerTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "shift (Dx)", Label.LEFT );
		p.add( label );	
		shiftTextField = new TextField( "0.0", 5 );
		shiftTextField.setEditable( false );
		p.add( shiftTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "slide (Dy)", Label.LEFT );
		p.add( label );	
		slideTextField = new TextField( "0.0", 5 );
		slideTextField.setEditable( false );
		p.add( slideTextField );
		add( p, 2, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "rise (Dz)", Label.LEFT );
		p.add( label );	
		riseTextField = new TextField( "3.36", 5 );
		riseTextField.setEditable( false );
		p.add( riseTextField );
		add( p, 2, gridYCounter++, 1 );

		gridYCounter = 0;

		label = new Label( "Sugar Pucker", Label.LEFT );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 3, gridYCounter++, 0 );	

		puckerChoice = new Choice();
		puckerChoice.addItem("C3'-endo");
		puckerChoice.addItem("C4'-exo");
		puckerChoice.addItem("O4'-endo");
		puckerChoice.addItem("C1'-exo");
		puckerChoice.addItem("C2'-endo");
		puckerChoice.addItem("C3'-exo");
		puckerChoice.addItem("C4'-endo");
		puckerChoice.addItem("O4'-exo");
		puckerChoice.addItem("C1'-endo");
		puckerChoice.addItem("C2'-exo");
		//		puckerChoice.addItem("Custom");
		puckerChoice.select("C2'-endo");
		add( puckerChoice, 3, gridYCounter++, 0 );


		label = new Label( "Torsion Angles", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 3, gridYCounter++, 0 );

		p = new Panel();
		label = new Label( "alpha", Label.LEFT );
		p.add( label );	
		alphaTextField = new TextField( "-41.0", 5 );
		alphaTextField.setEditable( false );
		p.add( alphaTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "beta", Label.LEFT );
		p.add( label );	
		betaTextField = new TextField( "136.0", 5 );
		betaTextField.setEditable( false );
		p.add( betaTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "gamma", Label.LEFT );
		p.add( label );	
		gammaTextField = new TextField( "38.0", 5 );
		gammaTextField.setEditable( false );
		p.add( gammaTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "delta", Label.LEFT );
		p.add( label );	
		deltaTextField = new TextField( "139.0", 5 );
		deltaTextField.setEditable( false );
		p.add( deltaTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "epsilon", Label.LEFT );
		p.add( label );	
		epsilonTextField = new TextField( "-133.0", 5 );
		epsilonTextField.setEditable( false );
		p.add( epsilonTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "zeta", Label.LEFT );
		p.add( label );	
		zetaTextField = new TextField( "-157.0", 5 );
		zetaTextField.setEditable( false );
		p.add( zetaTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "chi", Label.LEFT );
		p.add( label );	
		chiTextField = new TextField( "-102.0", 5 );
		chiTextField.setEditable( false );
		p.add( chiTextField );
		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "Max", Label.RIGHT );
		p.add( label );	
		numaxTextField = new TextField( "40.0", 5 );
		numaxTextField.setEditable( false );
		p.add( numaxTextField );
		//		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "nu0", Label.RIGHT );
		p.add( label );	
		nu0TextField = new TextField( "-38.04", 5 );
		nu0TextField.setEditable( false );
		p.add( nu0TextField );
		//		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "nu1", Label.RIGHT );
		p.add( label );	
		nu1TextField = new TextField( "23.51", 5 );
		nu1TextField.setEditable( false );
		p.add( nu1TextField );
		//		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "nu2", Label.RIGHT );
		p.add( label );	
		nu2TextField = new TextField( "0.00", 5 );
		nu2TextField.setEditable( false );
		p.add( nu2TextField );
		//		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "nu3", Label.RIGHT );
		p.add( label );	
		nu3TextField = new TextField( "-23.51", 5 );
		nu3TextField.setEditable( false );
		p.add( nu3TextField );
		//		add( p, 3, gridYCounter++, 1 );

		p = new Panel();
		label = new Label( "nu4", Label.RIGHT );
		p.add( label );	
		nu4TextField = new TextField( "38.04", 5 );
		nu4TextField.setEditable( false );
		p.add( nu4TextField );
		//		add( p, 3, gridYCounter++, 1 );

		gridYCounter = 0;

		label = new Label( "Add Base (Pair)", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 4, gridYCounter++, 0 );
		atButton = new Button( "A - (T)");
		atButton.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( atButton, 4, gridYCounter++, 0 );
		b = new Button( "C - (G)");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 4, gridYCounter++, 0 );
		b = new Button( "G - (C)");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 4, gridYCounter++, 0 );
		taButton = new Button( "T - (A)");
		taButton.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( taButton, 4, gridYCounter++, 0 );
		b = new Button( "5'-CAP");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 4, gridYCounter++, 0 );
		b = new Button( "3'-CAP");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 4, gridYCounter++, 0 );

		label = new Label( "Custom Base (Pair):", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 4, gridYCounter++, 0 );

		p = new Panel();
		//		label = new Label( "5':", Label.LEFT );
		//		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		//		p.add( label );
		leftBasepairChoice = new Choice();
		leftBasepairChoice.addItem("ADE");
		leftBasepairChoice.addItem("CYT");
		leftBasepairChoice.addItem("GUA");
		leftBasepairChoice.addItem("THY");
		leftBasepairChoice.addItem("URA");
		leftBasepairChoice.addItem("1MA");
		leftBasepairChoice.addItem("1MG");
		leftBasepairChoice.addItem("2MG");
		leftBasepairChoice.addItem("5MC");
		leftBasepairChoice.addItem("7MG");
		leftBasepairChoice.addItem("H2U");
		leftBasepairChoice.addItem(" I ");
		leftBasepairChoice.addItem("M2G");
		leftBasepairChoice.addItem("OMC");
		leftBasepairChoice.addItem("OMG");
		leftBasepairChoice.addItem("PSU");
		leftBasepairChoice.addItem(" Y ");
		p.add( leftBasepairChoice );
		label = new Label( "-", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		p.add( label );
		rightBasepairChoice = new Choice();
		rightBasepairChoice.addItem("ADE");
		rightBasepairChoice.addItem("CYT");
		rightBasepairChoice.addItem("GUA");
		rightBasepairChoice.addItem("THY");
		rightBasepairChoice.addItem("URA");
		rightBasepairChoice.addItem("1MA");
		rightBasepairChoice.addItem("1MG");
		rightBasepairChoice.addItem("2MG");
		rightBasepairChoice.addItem("5MC");
		rightBasepairChoice.addItem("7MG");
		rightBasepairChoice.addItem("H2U");
		rightBasepairChoice.addItem(" I ");
		rightBasepairChoice.addItem("M2G");
		rightBasepairChoice.addItem("OMC");
		rightBasepairChoice.addItem("OMG");
		rightBasepairChoice.addItem("PSU");
		rightBasepairChoice.addItem(" Y ");
		p.add( rightBasepairChoice );
		add( p, 4, gridYCounter++, 0 );
		customBasepairButton = new Button( "ADE-(ADE)");
		customBasepairButton.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( customBasepairButton, 4, gridYCounter++, 0 );

		action( null, "b-form" );
		reshape( 50, 50, 840, 430 );
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
		layout.setConstraints( c, layoutConstraint );
		add( c );
		return;
	}

	public boolean getParameterStatus(){
		ErrorDialog error;
		if ( ( capped3End ) && ( buildChoice.getSelectedItem() == "Build 5' to 3'" ) ){
			error = new ErrorDialog( BiomerFrame, "The 3' end of the polynucleotide is already capped.  You cannot add more residues once you have capped the end.", "Suggestion: Don't cap the ends of the polynucleotide until you have finished building the entire sequence." );
			error.show();
		}
		if ( ( capped5End ) && ( buildChoice.getSelectedItem() == "Build 3' to 5'" ) ){
			error = new ErrorDialog( BiomerFrame, "The 5' end of the polynucleotide is already capped.  You cannot add more residues once you have capped the end.", "Suggestion: Don't cap the ends of the polynucleotide until you have finished building the entire sequence." );
			error.show();
		}
		/*		try{
			alpha = new Float( alphaTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'alpha' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			return( false );
		}*/
		try{
			beta = new Float( betaTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'beta' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			gamma = new Float( gammaTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'gamma' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			delta = new Float( deltaTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'delta' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		/*		try{
			epsilon = new Float( epsilonTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'epsilon' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			zeta = new Float( zetaTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'zeta' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}*/
		try{
			chi = new Float( chiTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'chi' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		//		nu0 = new Float( nu0TextField.getText() ).floatValue();
		//		nu1 = new Float( nu1TextField.getText() ).floatValue();
		//		nu2 = new Float( nu2TextField.getText() ).floatValue();
		//		nu3 = new Float( nu3TextField.getText() ).floatValue();
		//		nu4 = new Float( nu4TextField.getText() ).floatValue();    
		try{
			tip = new Float( tipTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'tip' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			inclination = new Float( inclinationTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'inclination' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			opening = new Float( openingTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'opening' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			propellerTwist = new Float( propellerTwistTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'propeller twist' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			buckle = new Float( buckleTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'buckle' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			twist = new Float( twistTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'twist' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			roll = new Float( rollTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'roll' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			tilt = new Float( tiltTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'tilt' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			Dx = new Float( dxTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'dx' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			Dy = new Float( dyTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'dy' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			shear = new Float( shearTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'shear' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			stagger = new Float( staggerTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'stagger' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			stretch = new Float( stretchTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'stretch' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			shift = new Float( shiftTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'shift' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			slide = new Float( slideTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'slide' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		try{
			rise = new Float( riseTextField.getText() ).floatValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for 'rise' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
			error.show();
			return( false );
		}
		if ( tip == 0 ){
			tip = roll + previousTip;
		}
		else if ( ( roll != 0 ) && ( roll != tip - previousTip ) ){
			error = new ErrorDialog( BiomerFrame, "Your choices for tip and roll do not correlate.  Roll should equal the difference of tip and the previous tip.", "This check is performed only when tip and roll are both set to nonzero values.  Suggestion:  make either roll = 0 and control the rotation about the y-axis using only tip or make tip = 0 and control the rotation with roll." );
			error.show();
			return false;
		}
		if ( inclination == 0.0 ){
			inclination = tilt + previousInclination;
		}
		else if ( ( tilt != 0 ) && ( tilt != inclination - previousInclination ) ){
			error = new ErrorDialog( BiomerFrame, "Your choices for tilt and inclination do not correlate.  Tilt should equal the difference of inclination and the previous inclination.", "This check is performed only when tilt and inclination are both set to nonzero values.  Suggestion:  make either tilt = 0 and control the rotation about the x-axis using only inclination or make inclination = 0 and control the rotation with tilt." );
			error.show();
			return false;
		}
		if ( Dy == 0.0 ){
			Dy = slide + previousDy;	
		}
		else if ( ( slide != 0 ) && ( slide != Dy - previousDy ) ){
			error = new ErrorDialog( BiomerFrame, "Your choices for dy and slide do not correlate.  Slide should equal the difference of dy and the previous dy.", "This check is performed only when dy and slide are both set to nonzero values.  Suggestion:  make either dy = 0 and control the translation along the y-axis using only slide or make slide = 0 and control the translation with dy." );
			error.show();
			return false;
		}
		if ( Dx == 0.0 ){
			Dx = shift + previousDx;	
		}
		else if ( ( shift != 0 ) && ( shift != Dx - previousDx ) ){
			error = new ErrorDialog( BiomerFrame, "Your choices for dx and shift do not correlate.  Shift should equal the difference of dx and the previous dx.", "This check is performed only when dx and shift are both set to nonzero values.  Suggestion:  make either dx = 0 and control the translation along the x-axis using only shift or make shift = 0 and control the translation with dx." );
			error.show();
			return false;
		}
		previousTip = tip;
		previousInclination = inclination;
		previousDx = Dx;
		previousDy = Dy;
		totalRise += rise;
		totalTwist -= twist;
		return true;
	}

	public void disableSugarText(){
		numaxTextField.setEditable( false );
		nu0TextField.setEditable( false );
		nu1TextField.setEditable( false );
		nu2TextField.setEditable( false );
		nu3TextField.setEditable( false );
		nu4TextField.setEditable( false );
	}

	public void enableSugarText(){
		numaxTextField.setEditable( true );
		nu0TextField.setEditable( true );
		nu1TextField.setEditable( true );
		nu2TextField.setEditable( true );
		nu3TextField.setEditable( true );
		nu4TextField.setEditable( true );
	} 

	public void disableText(){
		alphaTextField.setEditable( false );
		betaTextField.setEditable( false );
		gammaTextField.setEditable( false );
		deltaTextField.setEditable( false );
		epsilonTextField.setEditable( false );
		zetaTextField.setEditable( false );
		chiTextField.setEditable( false );
		tipTextField.setEditable( false );
		inclinationTextField.setEditable( false );
		openingTextField.setEditable( false );
		propellerTwistTextField.setEditable( false );
		buckleTextField.setEditable( false );
		twistTextField.setEditable( false );
		rollTextField.setEditable( false );
		tiltTextField.setEditable( false );
		dxTextField.setEditable( false );
		dyTextField.setEditable( false );
		shearTextField.setEditable( false );
		stretchTextField.setEditable( false );
		staggerTextField.setEditable( false );
		shiftTextField.setEditable( false );
		slideTextField.setEditable( false );
		riseTextField.setEditable( false );
	}

	public void enableText(){
		//alphaTextField.setEditable( true );
		betaTextField.setEditable( true );
		gammaTextField.setEditable( true );
		deltaTextField.setEditable( true );
		//epsilonTextField.setEditable( true );
		//zetaTextField.setEditable( true );
		chiTextField.setEditable( true );
		tipTextField.setEditable( true );
		inclinationTextField.setEditable( true );
		openingTextField.setEditable( true );
		propellerTwistTextField.setEditable( true );
		buckleTextField.setEditable( true );
		twistTextField.setEditable( true );
		rollTextField.setEditable( true );
		tiltTextField.setEditable( true );
		dxTextField.setEditable( true );
		dyTextField.setEditable( true );
		shearTextField.setEditable( true );
		stretchTextField.setEditable( true );
		staggerTextField.setEditable( true );
		shiftTextField.setEditable( true );
		slideTextField.setEditable( true );
		riseTextField.setEditable( true );
	}

	public void makeBasepair( int sense, int anti, float senseAngle, 
			float antiAngle, float separation ){
		boolean fiveToThree = false;
		Residue sres = NAR.getResidue( anti );
		Residue ares = NAR.getResidue( sense );
		if ( buildChoice.getSelectedItem().equals( "Build 5' to 3'" ) ){
			fiveToThree = true;
			sres = NAR.getResidue( sense );
			ares = NAR.getResidue( anti );
		}
		if ( dnaChoice.getSelectedItem().equals( "z-form" ) ){
			zform = true;
			Residue temp = ares;
			ares = sres;
			sres = temp;
		}
		else
			zform = false;
		sres.mat.unit();
		ares.mat.unit();  /* transform to helical axis */
		ares.mat.xrot( 180.0 );
		sres.mat.zrot( senseAngle );
		ares.mat.zrot( -antiAngle );
		sres.mat.translate( 0.0f, -(separation/2.0f), 0.0f );  
		ares.mat.translate( 0.0f, (separation/2.0f), 0.0f );  
		sres.mat.transform( sres.actualCoordinates, sres.numberOfAtoms );
		ares.mat.transform( ares.actualCoordinates, ares.numberOfAtoms );

		sres.mat.unit();
		ares.mat.unit(); 
		sres.mat.zrot( -opening/2.0f );
		ares.mat.zrot( opening/2.0f ); 
		sres.mat.transform( sres.actualCoordinates, sres.numberOfAtoms );
		ares.mat.transform( ares.actualCoordinates, ares.numberOfAtoms );

		sres.mat.unit();
		ares.mat.unit(); 
		sres.mat.translate( -xOffset, -stretch, 0.0f );  
		ares.mat.translate( -xOffset, stretch, 0.0f ); 
		sres.mat.transform( sres.actualCoordinates, sres.numberOfAtoms );
		ares.mat.transform( ares.actualCoordinates, ares.numberOfAtoms );

		sres.mat.unit();
		ares.mat.unit(); 
		sres.mat.yrot( -tip + (propellerTwist/2.0f) );
		ares.mat.yrot( -tip - (propellerTwist/2.0f) );
		sres.mat.xrot( -inclination + buckle );
		ares.mat.xrot( -inclination - buckle );    
		sres.mat.translate( Dx - shear, 0.0f, stagger - totalRise );
		ares.mat.translate( Dx + shear, 0.0f, -stagger - totalRise ); 
		sres.mat.zrot( totalTwist );
		ares.mat.zrot( totalTwist );   

		sres.mat.transform( sres.actualCoordinates, sres.numberOfAtoms );
		ares.mat.transform( ares.actualCoordinates, ares.numberOfAtoms );  

		float sresC1P[] = new float[ 2 ];
		float aresC1P[] = new float[ 2 ];
		for ( int i = 0; i < sres.numberOfAtoms; i++ ){
			String name = sres.atom[ i ].name;
			if ( name.equals( "C1'" ) ){
				int ix3 = i * 3;
				sresC1P[ 0 ] = sres.actualCoordinates[ ix3 ];
				sresC1P[ 1 ] = sres.actualCoordinates[ ix3 + 1 ];
				break;
			}
		}
		for ( int i = 0; i < ares.numberOfAtoms; i++ ){
			String name = ares.atom[ i ].name;
			if ( name.equals( "C1'" ) ){
				int ix3 = i * 3;
				aresC1P[ 0 ] = ares.actualCoordinates[ ix3 ];
				aresC1P[ 1 ] = ares.actualCoordinates[ ix3 + 1 ];
				break;
			}
		}

		slideVector[ 0 ] = sresC1P[ 0 ] - aresC1P[ 0 ];
		slideVector[ 1 ] = sresC1P[ 1 ] - aresC1P[ 1 ];
		slideVector[ 2 ] = 0.0f;

		float slideVectorLength = (float)Math.sqrt( slideVector[ 0 ] * slideVector[ 0 ] +
				slideVector[ 1 ] * slideVector[ 1 ] );
		slideVector[ 0 ] = Dy * slideVector[ 0 ] / slideVectorLength;
		slideVector[ 1 ] = Dy * slideVector[ 1 ] / slideVectorLength;

		sres.mat.unit();
		ares.mat.unit(); 
		sres.mat.translate( slideVector[ 0 ], slideVector[ 1 ], 0.0f );
		ares.mat.translate( slideVector[ 0 ], slideVector[ 1 ], 0.0f );
		sres.mat.transform( sres.actualCoordinates, sres.numberOfAtoms );
		ares.mat.transform( ares.actualCoordinates, ares.numberOfAtoms );  

		if ( strandChoice.getSelectedItem() == "Single Stranded" ){
			if ( fiveToThree ){
				m.addResidue( 0, sres );
				setTorsionAngles( sres, true );
				if ( lastSenseResidue != null ){
					int residueDistance = Math.abs( sres.strandResidueNumber -
							lastSenseResidue.strandResidueNumber );
					if ( residueDistance == 1 ){
						if ( zform ){
							m.connectResidues( 
									lastSenseResidue.totalResidueNumber,
									"P", sres.totalResidueNumber, "O3'" );
							fixBackbone( sres.totalResidueNumber,
									lastSenseResidue.totalResidueNumber );
						}
						else{
							m.connectResidues( 
									lastSenseResidue.totalResidueNumber,
									"O3'", sres.totalResidueNumber, "P" );
							fixBackbone( lastSenseResidue.totalResidueNumber,
									sres.totalResidueNumber );
						}
					}
				}
				if ( counterIonsCheckbox.getState() == true )
					NAR.addCounterIons( m, BiomerFrame, sres );
			}
			else{
				m.addResidue( 0, ares );
				setTorsionAngles( ares, false );
				if ( lastAntiResidue != null ){
					int residueDistance = Math.abs( ares.strandResidueNumber -
							lastAntiResidue.strandResidueNumber );
					if ( residueDistance == 1 ){
						if ( zform ){
							m.connectResidues( 
									lastAntiResidue.totalResidueNumber,
									"O3'", ares.totalResidueNumber, "P" );
							fixBackbone( lastAntiResidue.totalResidueNumber, 
									ares.totalResidueNumber );
						}
						else{
							m.connectResidues( 
									lastAntiResidue.totalResidueNumber,
									"P", ares.totalResidueNumber, "O3'" );
							fixBackbone( ares.totalResidueNumber, 
									lastAntiResidue.totalResidueNumber );
						}
					}
				}
				if ( counterIonsCheckbox.getState() == true )
					NAR.addCounterIons( m, BiomerFrame, ares );
			}
		}
		else{
			m.addResidue( 0, sres );
			setTorsionAngles( sres, true );
			if ( counterIonsCheckbox.getState() == true )
				NAR.addCounterIons( m, BiomerFrame, sres );
			m.addResidue( 1, ares );
			setTorsionAngles( ares, false );
			if ( counterIonsCheckbox.getState() == true )
				NAR.addCounterIons( m, BiomerFrame, ares );
			if ( lastSenseResidue != null ){
				int residueDistance = Math.abs( sres.strandResidueNumber -
						lastSenseResidue.strandResidueNumber );
				if ( residueDistance == 1 ){
					if ( zform ){
						m.connectResidues( lastSenseResidue.totalResidueNumber,
								"P", sres.totalResidueNumber, "O3'" );
						fixBackbone( sres.totalResidueNumber,
								lastSenseResidue.totalResidueNumber );
					}
					else{
						m.connectResidues( lastSenseResidue.totalResidueNumber,
								"O3'", sres.totalResidueNumber, "P" );
						fixBackbone( lastSenseResidue.totalResidueNumber,
								sres.totalResidueNumber );
					}
				}
			}
			if ( lastAntiResidue != null ){
				int residueDistance = Math.abs( ares.strandResidueNumber -
						lastAntiResidue.strandResidueNumber );
				if ( residueDistance == 1 ){
					if ( zform ){
						m.connectResidues( lastAntiResidue.totalResidueNumber,
								"O3'", ares.totalResidueNumber, "P" );
						fixBackbone( lastAntiResidue.totalResidueNumber,
								ares.totalResidueNumber );
					}
					else{
						m.connectResidues( lastAntiResidue.totalResidueNumber,
								"P", ares.totalResidueNumber, "O3'" );
						fixBackbone( ares.totalResidueNumber,
								lastAntiResidue.totalResidueNumber );
					}
				}
			}
		}
		lastSenseResidue = sres;
		lastAntiResidue = ares;
		if ( oddBasepair )
			oddBasepair = false;
		else	
			oddBasepair = true;
	}

	public void setTorsionAngles( Residue residue, boolean sense ){
		int P = -1, O5P = -1, C5P = -1;
		int C4P = -1, O4P = -1, C1P = -1, H1P = -1, N9 = -1, C2 = -1;
		int C4 = -1, C3P = -1, C2P = -1;
		int N1 = -1, O3P = -1;
		int chiArray[] = new int[ 20 ];
		int nu1Array[] = new int[ 20 ];
		int nu2Array[] = new int[ 20 ];
		int C1PArray[] = new int[ 20 ];
		int C2PArray[] = new int[ 20 ];
		int C3PArray[] = new int[ 20 ];
		int C4PArray[] = new int[ 20 ];
		int O4PArray[] = new int[ 20 ];
		int alphaArray[] = new int[ 20 ];
		int deltaArray1[] = new int[ 20 ];
		int deltaArray2[] = new int[ 20 ];
		int gammaArray[] = new int[ 20 ];
		int betaArray[] = new int[ 20 ];
		int chiArrayCounter = 0, nu1ArrayCounter = 0, nu2ArrayCounter = 0;
		int C1PArrayCounter = 0, C2PArrayCounter = 0, C3PArrayCounter = 0;
		int C4PArrayCounter = 0, O4PArrayCounter = 0, deltaArray1Counter = 0;
		int deltaArray2Counter = 0, gammaArrayCounter = 0, betaArrayCounter = 0;
		int alphaArrayCounter = 0;
		for ( int i = 0; i < residue.numberOfAtoms; i++ ){
			String name = residue.atom[ i ].name;
			int number = residue.atom[ i ].moleculeAtomNumber;
			if ( ( name.indexOf( "'" ) != -1 ) || ( name.indexOf( "T" ) != -1 ) ||
					( name.indexOf( "P" ) != -1 ) || ( name.indexOf( "HO2" ) != -1 ) ){
				if ( name.equals( "P" ) ){
					P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
					gammaArray[ gammaArrayCounter++ ] = number;
				}
				else if ( name.equals( "O1P" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
					gammaArray[ gammaArrayCounter++ ] = number;
					betaArray[ betaArrayCounter++ ] = number;
					alphaArray[ alphaArrayCounter++ ] = number;
				}
				else if ( name.equals( "O2P" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
					gammaArray[ gammaArrayCounter++ ] = number;
					betaArray[ betaArrayCounter++ ] = number;
					alphaArray[ alphaArrayCounter++ ] = number;
				}
				else if ( name.equals( "O5'" ) ){
					O5P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
				}
				else if ( name.equals( "C5'" ) ){
					C5P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
				}
				else if ( name.equals( "H5'1" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
					gammaArray[ gammaArrayCounter++ ] = number;
				}
				else if ( name.equals( "H5'2" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
					gammaArray[ gammaArrayCounter++ ] = number;
				}
				else if ( name.equals( "C4'" ) ){
					C4P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
				}
				else if ( name.equals( "H4'" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					nu2Array[ nu2ArrayCounter++ ] = number;
					C4PArray[ C4PArrayCounter++ ] = number;
					deltaArray2[ deltaArray2Counter++ ] = number;
				}
				else if ( name.equals( "O4'" ) ){
					O4P = number;
				}
				else if ( name.equals( "C1'" ) ){
					C1P = number;
					C1PArray[ C1PArrayCounter++ ] = number;
				}
				else if ( name.equals( "H1'" ) ){
					H1P = number;
					C1PArray[ C1PArrayCounter++ ] = number;
					if ( zform )
						chiArray[ chiArrayCounter++ ] = number;
				}
				else if ( name.equals( "C3'" ) ){
					C3P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					C3PArray[ C3PArrayCounter++ ] = number;
				}
				else if ( name.equals( "H3'" ) ){
					nu1Array[ nu1ArrayCounter++ ] = number;
					C3PArray[ C3PArrayCounter++ ] = number;
					deltaArray1[ deltaArray1Counter++ ] = number;
				}
				else if ( name.equals( "C2'" ) ){
					C2P = number;
					C2PArray[ C2PArrayCounter++ ] = number;
				}
				else if ( name.equals( "H2'1" ) ){
					C2PArray[ C2PArrayCounter++ ] = number;
				}
				else if ( name.equals( "H2'2" ) ){
					C2PArray[ C2PArrayCounter++ ] = number;
				}
				else if ( name.equals( "O2'" ) ){
					C2PArray[ C2PArrayCounter++ ] = number;
				}
				else if ( name.equals( "HO2" ) ){
					C2PArray[ C2PArrayCounter++ ] = number;
				}
				else if ( name.equals( "O3'" ) ){
					O3P = number;
					nu1Array[ nu1ArrayCounter++ ] = number;
					C3PArray[ C3PArrayCounter++ ] = number;
				}
				if ( !( name.equals( "H1'" ) ) )
					chiArray[ chiArrayCounter++ ] = number;
			}
			else{
				if ( name.equals( "N1" ) ){
					N1 = residue.atom[ i ].moleculeAtomNumber;
				}
				else if ( name.equals( "N9" ) ){
					N9 = residue.atom[ i ].moleculeAtomNumber;
				}
				else if ( name.equals( "C2" ) ){
					C2 = residue.atom[ i ].moleculeAtomNumber;
				}
				else if ( name.equals( "C4" ) ){
					C4 = residue.atom[ i ].moleculeAtomNumber;
				}
			}
		}
		m.setTorsion( O4P, C1P, C2P, C3P, 0, nu1Array, nu1ArrayCounter );
		m.setTorsion( C1P, C2P, C3P, C4P, 0, nu2Array, nu2ArrayCounter );
		Atom C1PAtom = m.atom[ C1P ];
		Atom C2PAtom = m.atom[ C2P ];
		Atom C3PAtom = m.atom[ C3P ];
		Atom C4PAtom = m.atom[ C4P ];
		Atom O4PAtom = m.atom[ O4P ];
		Atom pseudoAtom = new Atom( "pseudo", 0.0f, 0.0f, 0.0f );
		m.addAtom( C1PAtom.totalResidueNumber, pseudoAtom );
		int pseudoNum = pseudoAtom.moleculeAtomNumber;
		if ( zform ){
			if ( oddBasepair ){
				if ( sense )
					puckerChoice.select( 4 );
				else
					puckerChoice.select( 3 );
			}
			else{
				if ( sense )
					puckerChoice.select( 3 );
				else
					puckerChoice.select( 4 );
			}
		}
		switch( puckerChoice.getSelectedIndex() ){
		case 0:		// C3'-endo
			pseudoAtom.coord[ 0 ] = (float)( ( O4PAtom.coord[ 0 ] + 
					C1PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( O4PAtom.coord[ 1 ] + 
					C1PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( O4PAtom.coord[ 2 ] + 
					C1PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C2P, C1P, pseudoNum, C3P, sugarPucker, C3PArray, 
					C3PArrayCounter );
			break;
		case 1:		// C4'-exo
			pseudoAtom.coord[ 0 ] = (float)( ( C2PAtom.coord[ 0 ] + 
					C1PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C2PAtom.coord[ 1 ] + 
					C1PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C2PAtom.coord[ 2 ] + 
					C1PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C3P, C2P, pseudoNum, C4P, -sugarPucker, C4PArray, 
					C4PArrayCounter );
			break;
		case 2:		// O4'-endo
			pseudoAtom.coord[ 0 ] = (float)( ( C3PAtom.coord[ 0 ] + 
					C2PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C3PAtom.coord[ 1 ] + 
					C2PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C3PAtom.coord[ 2 ] + 
					C2PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C4P, C3P, pseudoNum, O4P, sugarPucker, O4PArray, 
					O4PArrayCounter );
			break;
		case 3:		// C1'-exo
			pseudoAtom.coord[ 0 ] = (float)( ( C4PAtom.coord[ 0 ] + 
					C3PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C4PAtom.coord[ 1 ] + 
					C3PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C4PAtom.coord[ 2 ] + 
					C3PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C2P, C3P, pseudoNum, C1P, sugarPucker, C1PArray, 
					C1PArrayCounter );
			break;
		case 4:		// C2'-endo
			pseudoAtom.coord[ 0 ] = (float)( ( O4PAtom.coord[ 0 ] + 
					C4PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( O4PAtom.coord[ 1 ] + 
					C4PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( O4PAtom.coord[ 2 ] + 
					C4PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C3P, C4P, pseudoNum, C2P, -sugarPucker, C2PArray, 
					C2PArrayCounter );
			break;
		case 5:		// C3'-exo
			pseudoAtom.coord[ 0 ] = (float)( ( O4PAtom.coord[ 0 ] + 
					C1PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( O4PAtom.coord[ 1 ] + 
					C1PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( O4PAtom.coord[ 2 ] + 
					C1PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C2P, C1P, pseudoNum, C3P, -sugarPucker, C3PArray, 
					C3PArrayCounter );
			break;
		case 6:		// C4'-endo
			pseudoAtom.coord[ 0 ] = (float)( ( C2PAtom.coord[ 0 ] + 
					C1PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C2PAtom.coord[ 1 ] + 
					C1PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C2PAtom.coord[ 2 ] + 
					C1PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C3P, C2P, pseudoNum, C4P, sugarPucker, C4PArray, 
					C4PArrayCounter );
			break;
		case 7:		// O4'-exo
			pseudoAtom.coord[ 0 ] = (float)( ( C3PAtom.coord[ 0 ] + 
					C2PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C3PAtom.coord[ 1 ] + 
					C2PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C3PAtom.coord[ 2 ] + 
					C2PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C4P, C3P, pseudoNum, O4P, -sugarPucker, O4PArray, 
					C4PArrayCounter );
			break;
		case 8:		// C1'-endo
			pseudoAtom.coord[ 0 ] = (float)( ( C4PAtom.coord[ 0 ] + 
					C3PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( C4PAtom.coord[ 1 ] + 
					C3PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( C4PAtom.coord[ 2 ] + 
					C3PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C2P, C3P, pseudoNum, C1P, -sugarPucker, C1PArray, 
					C1PArrayCounter );
			break;
		case 9:		// C2'-exo
			pseudoAtom.coord[ 0 ] = (float)( ( O4PAtom.coord[ 0 ] + 
					C4PAtom.coord[ 0 ] ) / 2.0 );
			pseudoAtom.coord[ 1 ] = (float)( ( O4PAtom.coord[ 1 ] + 
					C4PAtom.coord[ 1 ] ) / 2.0 );
			pseudoAtom.coord[ 2 ] = (float)( ( O4PAtom.coord[ 2 ] + 
					C4PAtom.coord[ 2 ] ) / 2.0 );
			m.updateCoordinates( pseudoAtom );
			m.setTorsion( C3P, C4P, pseudoNum, C2P, sugarPucker, C2PArray, 
					C2PArrayCounter );
			break;
		}
		m.deleteAtom( pseudoAtom.moleculeAtomNumber );
		// set chi
		if ( zform ){
			if ( oddBasepair ){
				if ( sense ){
					chi = 68;
					beta = 160;
					gamma = 150;
					delta = 140;
				}
				else{
					chi = -159;
					beta = -139;
					gamma = -30;
					delta = 138;
				}
			}
			else{
				if ( sense ){
					chi = -159;
					beta = -139;
					gamma = -30;
					delta = 138;
				}
				else{
					chi = 68;
					beta = 160;
					gamma = 150;
					delta = 140;
				}
			}
		}
		if ( ( C4 >= 0 ) && ( N9 >= 0 ) ){
			m.setTorsion( C4, N9, C1P, O4P, chi, chiArray, chiArrayCounter );
			if ( zform ) 
				m.setTorsion( C1P, C2P, C3P, O3P, 50.0, null, 0 );
			else 
				m.setTorsion( H1P, C1P, C3P, O3P, 0, null, 0 );
		}
		else if ( ( C2 >= 0 ) && ( N1 >= 0 ) ){
			m.setTorsion( C2, N1, C1P, O4P, chi, chiArray, chiArrayCounter );
			if ( zform ) 
				m.setTorsion( C1P, C2P, C3P, O3P, 50.0, null, 0 );
			else
				m.setTorsion( H1P, C1P, C3P, O3P, 0, null, 0 );
		}
		//		m.setTorsion( C5P, C4P, C3P, O3P, delta, deltaArray1, deltaArray1Counter );
		m.setTorsion( O3P, C3P, C4P, C5P, delta, deltaArray2, deltaArray2Counter );
		m.setTorsion( C3P, C4P, C5P, O5P, gamma, gammaArray, gammaArrayCounter );
		m.setTorsion( C4P, C5P, O5P, P, beta, betaArray, betaArrayCounter );     
		//		m.setTorsion( C5P, O5P, P, O1P, alpha, alphaArray, betaArrayCounter );     
	}

	public void fixBackbone( int previousResidueNumber, int currentResidueNumber ){	
		Residue lastResidue = m.residue[ previousResidueNumber ];
		Residue currentResidue = m.residue[ currentResidueNumber ];
		ErrorDialog error;
		Atom O3P = null, O5P = null, O1P = null, O2P = null, P = null;
		Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
		for( int i = 0; i < lastResidue.numberOfAtoms; i++ ){
			if ( lastResidue.atom[ i ].name.equals( "O3'" ) )
				O3P = lastResidue.atom[ i ];
		}
		for( int i = 0; i < currentResidue.numberOfAtoms; i++ ){
			if ( currentResidue.atom[ i ].name.equals( "O1P" ) )
				O1P = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "O2P" ) )
				O2P = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "P" ) )
				P = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "O5'" ) )
				O5P = currentResidue.atom[ i ];
		}
		if ( ( O3P == null ) || ( O1P == null ) || ( O2P == null ) || ( P == null ) ||
				( O5P == null ) ){
			error = new ErrorDialog( BiomerFrame, "Error fixing backbone", 
					"Internal error." );
			error.show();
			return;
		}
		m.resolveCoordinates( O3P.moleculeAtomNumber );
		m.resolveCoordinates( O5P.moleculeAtomNumber );
		pseudo.coord[ 0 ] = (float)( ( O3P.coord[ 0 ] + O5P.coord[ 0 ] ) / 2.0 );
		pseudo.coord[ 1 ] = (float)( ( O3P.coord[ 1 ] + O5P.coord[ 1 ] ) / 2.0 );
		pseudo.coord[ 2 ] = (float)( ( O3P.coord[ 2 ] + O5P.coord[ 2 ] ) / 2.0 );
		m.addAtom( P.totalResidueNumber, pseudo );
		O1P.setAngle( m, pseudo, P, 122.5 );
		O1P.setTorsion( m, O3P, pseudo, P, 90.0 );
		O2P.setAngle( m, pseudo, P, 122.5 );
		O2P.setTorsion( m, O3P, pseudo, P, -90.0 );
		m.deleteAtom( pseudo.moleculeAtomNumber );
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
		if ( what == "DNA" ){
			atButton.setLabel( "A - (T)" );
			taButton.setLabel( "T - (A)" );
			dnaChoice.select( "a-form" );
			return action( event, "a-form" );
			/*			parent = dnaChoice.getParent();
			parent.remove( dnaChoice );
			newdnaChoice = new Choice();
			newdnaChoice.addItem("a-form");
			newdnaChoice.addItem("b-form");
			newdnaChoice.addItem("b'-form");
			newdnaChoice.addItem("c-form");
			newdnaChoice.addItem("c'-form");
			newdnaChoice.addItem("c''-form");
			newdnaChoice.addItem("d-form");
			newdnaChoice.addItem("e-form");
			newdnaChoice.addItem("t-form");
			newdnaChoice.addItem("z-form");
			newdnaChoice.addItem("custom");
			newdnaChoice.select("b-form");
			add( newdnaChoice, 1, 1, 0 );
			parent.remove( leftBasepairChoice );
			parent.remove( rightBasepairChoice );
			p = new Panel();
			label = new Label( "5':", Label.RIGHT );
			label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
			p.add( label );
			newLeftBasePairChoice = new Choice();
			newLeftBasePairChoice.addItem("ADE");
			newLeftBasePairChoice.addItem("CYT");
			newLeftBasePairChoice.addItem("GUA");
			newLeftBasePairChoice.addItem("THY");
			p.add( newLeftBasePairChoice );
			label = new Label( "(3'):", Label.RIGHT );
			label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
			p.add( label );
			newRightBasePairChoice = new Choice();
			newRightBasePairChoice.addItem("ADE");
			newRightBasePairChoice.addItem("CYT");
			newRightBasePairChoice.addItem("GUA");
			newRightBasePairChoice.addItem("THY");
			p.add( newRightBasePairChoice );
			add( p, 0, 12, 0 );
			leftBasepairChoice = newLeftBasePairChoice;
			rightBasepairChoice = newRightBasePairChoice;
			dnaChoice = newdnaChoice;
			//show();  
			if ( dnaChoice.getSelectedItem() == "a'-form" ){
				dnaChoice.select( "a-form" );
				return action( event, "a-form" );
			}
			return true;  */
		}
		else if ( what == "RNA" ){
			atButton.setLabel( "A - (U)");
			taButton.setLabel( "U - (A)");
			dnaChoice.select( "a-form" );
			return action( event, "a-form" );
			/*			parent = dnaChoice.getParent();
			parent.remove( dnaChoice );
			newdnaChoice = new Choice();
			newdnaChoice.addItem("a-form");
			newdnaChoice.addItem("a'-form");
			newdnaChoice.addItem("custom");
			newdnaChoice.select("a-form");
			add( newdnaChoice, 1, 1, 0 );
			parent.remove( leftBasepairChoice );
			parent.remove( rightBasepairChoice );
			p = new Panel();
			label = new Label( "5':", Label.RIGHT );
			label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
			p.add( label );
			newLeftBasePairChoice = new Choice();
			newLeftBasePairChoice.addItem("ADE");
			newLeftBasePairChoice.addItem("CYT");
			newLeftBasePairChoice.addItem("GUA");
			newLeftBasePairChoice.addItem("URA");
			newLeftBasePairChoice.addItem("1MA");
			newLeftBasePairChoice.addItem("1MG");
			newLeftBasePairChoice.addItem("2MG");
			newLeftBasePairChoice.addItem("5MC");
			newLeftBasePairChoice.addItem("7MG");
			newLeftBasePairChoice.addItem("H2U");
			newLeftBasePairChoice.addItem(" I ");
			newLeftBasePairChoice.addItem("M2G");
			newLeftBasePairChoice.addItem("OMC");
			newLeftBasePairChoice.addItem("OMG");
			newLeftBasePairChoice.addItem("PSU");
			newLeftBasePairChoice.addItem(" Y ");
			p.add( newLeftBasePairChoice );
			label = new Label( "(3'):", Label.RIGHT );
			label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
			p.add( label );
			newRightBasePairChoice = new Choice();
			newRightBasePairChoice.addItem("ADE");
			newRightBasePairChoice.addItem("CYT");
			newRightBasePairChoice.addItem("GUA");
			newRightBasePairChoice.addItem("URA");
			newRightBasePairChoice.addItem("1MA");
			newRightBasePairChoice.addItem("1MG");
			newRightBasePairChoice.addItem("2MG");
			newRightBasePairChoice.addItem("5MC");
			newRightBasePairChoice.addItem("7MG");
			newRightBasePairChoice.addItem("H2U");
			newRightBasePairChoice.addItem(" I ");
			newRightBasePairChoice.addItem("M2G");
			newRightBasePairChoice.addItem("OMC");
			newRightBasePairChoice.addItem("OMG");
			newRightBasePairChoice.addItem("PSU");
			newRightBasePairChoice.addItem(" Y ");
			p.add( newRightBasePairChoice );
			add( p, 0, 12, 0 );
			leftBasepairChoice = newLeftBasePairChoice;
			rightBasepairChoice = newRightBasePairChoice;
			dnaChoice = newdnaChoice;  
			//show(); 
			if ( ( dnaChoice.getSelectedItem() != "a-form" ) && 
				( dnaChoice.getSelectedItem() != "a'-form" ) ){
				dnaChoice.select( "a-form" );
				return action( event, "a-form" );
			}
			return true; */
		}
		else if ( ( what == "a-form" ) && ( naChoice.getSelectedItem() == "DNA" ) ){
			disableText();
			puckerChoice.select("C3'-endo");
			alphaTextField.setText("130.15");
			betaTextField.setText("-158.0");
			gammaTextField.setText("109.0");
			deltaTextField.setText("127.0");
			epsilonTextField.setText("-59.05");
			zetaTextField.setText("130.15");
			chiTextField.setText("-154.0");
			tipTextField.setText("11.0");
			inclinationTextField.setText("20.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("-8.3");
			buckleTextField.setText("-2.4");
			twistTextField.setText("32.7");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("-4.1");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("2.56");
			return true;
		}
		else if ( ( what == "a-form" ) && ( naChoice.getSelectedItem() == "RNA" ) ){
			disableText();
			puckerChoice.select("C3'-endo");
			alphaTextField.setText("-160.9");
			betaTextField.setText("-158.0");
			gammaTextField.setText("109.0");
			deltaTextField.setText("127.0");
			epsilonTextField.setText("-133.4");
			zetaTextField.setText("-154.0");
			chiTextField.setText("-158.0");
			tipTextField.setText("0.0");
			inclinationTextField.setText("17.5");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("0.0");
			buckleTextField.setText("0.0");
			twistTextField.setText("32.7");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("-4.4");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("2.8");
			return true;
		}
		else if ( what == "a'-form" ){
			if ( naChoice.getSelectedItem() == "DNA" ) return action( event, "DNA" );
			disableText();
			puckerChoice.select("C3'-endo");
			alphaTextField.setText("-152.0");
			betaTextField.setText("-158.0");
			gammaTextField.setText("109.0");
			deltaTextField.setText("127.0");
			epsilonTextField.setText("-134.7");
			zetaTextField.setText("-157.4");
			chiTextField.setText("-158.0");
			tipTextField.setText("0.0");
			inclinationTextField.setText("10.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("0.0");
			buckleTextField.setText("0.0");
			twistTextField.setText("30.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("-4.4");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.0");
			return true;
		}
		else if ( what == "b-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C2'-endo");
			alphaTextField.setText("-149.7");
			betaTextField.setText("-80.0");
			gammaTextField.setText("50.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("58.13");
			zetaTextField.setText("-6.4");
			chiTextField.setText("-102.0");
			tipTextField.setText("0.6");
			inclinationTextField.setText("-5.9");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("-11.1");
			buckleTextField.setText("-0.2");
			twistTextField.setText("36.1");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("0.14");
			dyTextField.setText("0.1");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.36");
			return true;
		}
		else if ( what == "b'-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("-141.8");
			betaTextField.setText("-80.0");
			gammaTextField.setText("50.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("80.28");
			zetaTextField.setText("-22.73");
			chiTextField.setText("-96.0");
			tipTextField.setText("0.0");
			inclinationTextField.setText("-7.9");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("-1.0");
			buckleTextField.setText("-0.2");
			twistTextField.setText("36.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("0.02");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.29");
			return true;
		}
		else if ( what == "c-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("49.78");
			betaTextField.setText("90.0");
			gammaTextField.setText("-10.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("-147.7");
			zetaTextField.setText("163.8");
			chiTextField.setText("-97.0");
			tipTextField.setText("0.6");
			inclinationTextField.setText("-8.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("1.0");
			buckleTextField.setText("-0.2");
			twistTextField.setText("38.6");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.0");
			dyTextField.setText("0.4");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.31");
			return true;
		}
		else if ( what == "c'-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("49.60");
			betaTextField.setText("90.0");
			gammaTextField.setText("-10.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("-142.9");
			zetaTextField.setText("160.4");
			chiTextField.setText("-97.0");
			tipTextField.setText("0.6");
			inclinationTextField.setText("-8.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("1.0");
			buckleTextField.setText("-0.2");
			twistTextField.setText("40.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.0");
			dyTextField.setText("0.4");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.28");
			return true;
		}
		else if ( what == "c''-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("47.76");
			betaTextField.setText("90.0");
			gammaTextField.setText("-10.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("-138.7");
			zetaTextField.setText("156.4");
			chiTextField.setText("-97.0");
			tipTextField.setText("0.6");
			inclinationTextField.setText("-8.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("1.0");
			buckleTextField.setText("-0.2");
			twistTextField.setText("40.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.0");
			dyTextField.setText("0.4");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.23");
			return true;
		}
		else if ( what == "d-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("-125.9");
			betaTextField.setText("-120.0");
			gammaTextField.setText("117.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("146.2");
			zetaTextField.setText("-128.5");
			chiTextField.setText("-110.0");
			tipTextField.setText("-16.0");
			inclinationTextField.setText("-1.5");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("-10.0");
			buckleTextField.setText("0.0");
			twistTextField.setText("45.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.8");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.03");
			return true;
		}
		else if ( what == "e-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C3'-exo");
			alphaTextField.setText("-157.3");
			betaTextField.setText("-80.0");
			gammaTextField.setText("90.0");
			deltaTextField.setText("139.0");
			epsilonTextField.setText("77.4");
			zetaTextField.setText("-47.9");
			chiTextField.setText("-97.0");
			tipTextField.setText("0.0");
			inclinationTextField.setText("0.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("1.0");
			buckleTextField.setText("-0.2");
			twistTextField.setText("48.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.0");
			dyTextField.setText("0.4");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.04");
			return true;
		}
		else if ( what == "t-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			disableText();
			puckerChoice.select("C2'-endo");
			alphaTextField.setText("-135.4");
			betaTextField.setText("-90.0");
			gammaTextField.setText("107.0");
			deltaTextField.setText("120.0");
			epsilonTextField.setText("61.0");
			zetaTextField.setText("-60.6");
			chiTextField.setText("-97.0");
			tipTextField.setText("0.0");
			inclinationTextField.setText("-6.0");
			openingTextField.setText("0.0");
			propellerTwistTextField.setText("4.0");
			buckleTextField.setText("0.0");
			twistTextField.setText("45.0");
			rollTextField.setText("0.0");
			tiltTextField.setText("0.0");
			dxTextField.setText("1.43");
			dyTextField.setText("0.0");
			shearTextField.setText("0.0");
			stretchTextField.setText("0.0");
			staggerTextField.setText("0.0");
			shiftTextField.setText("0.0");
			slideTextField.setText("0.0");
			riseTextField.setText("3.40");
			return true;
		}
		else if ( what == "z-form" ){
			if ( naChoice.getSelectedItem() == "RNA" ) return action( event, "RNA" );
			zdna2();
			return true;
		}
		else if ( what == "custom" ){
			enableText();
			return true;
		}
		else if ( what == "C3'-endo" ){
			pseudorotation = 18.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C4'-exo" ){
			pseudorotation = 54.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "O4'-endo" ){
			pseudorotation = 90.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C1'-exo" ){
			pseudorotation = 126.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C2'-endo" ){
			pseudorotation = 162.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C3'-exo" ){
			pseudorotation = 198.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C4'-endo" ){
			pseudorotation = 234.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "O4'-exo" ){
			pseudorotation = 270.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C1'-endo" ){
			pseudorotation = 306.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		}
		else if ( what == "C2'-exo" ){
			pseudorotation = 342.0f;
			nuMax = new Float( numaxTextField.getText() ).floatValue();
			nu0 = (float)(nuMax * Math.cos( D2R * ( pseudorotation ) ) );
			if ( Math.abs( nu0 ) < 1e-7 ) nu0 = 0;
			nu1 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 ) ) );
			if ( Math.abs( nu1 ) < 1e-7 ) nu1 = 0;
			nu2 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 2 ) ) );
			if ( Math.abs( nu2 ) < 1e-7 ) nu2 = 0;
			nu3 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 3 ) ) );
			if ( Math.abs( nu3 ) < 1e-7 ) nu3 = 0;
			nu4 = (float)(nuMax * Math.cos( D2R * ( pseudorotation + 144 * 4 ) ) );
			if ( Math.abs( nu4 ) < 1e-7 ) nu4 = 0;
			nu0TextField.setText( new Float( nu0 ).toString() );
			nu1TextField.setText( new Float( nu1 ).toString() );
			nu2TextField.setText( new Float( nu2 ).toString() );
			nu3TextField.setText( new Float( nu3 ).toString() );
			nu4TextField.setText( new Float( nu4 ).toString() );
			disableSugarText();
			return true;
		} 
		/*		else if ( what == "Custom" ){
			enableSugarText();
			if ( dnaChoice.getSelectedItem() == "z-form" )
				changeZdnaParameters();
			return true;
		}  */
		else if ( what == "A - (T)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "A" );
				if ( naChoice.getSelectedItem() == "DNA" )
					makeBasepair( NAR.dADE, NAR.dTHY, ADE_ANGLE, 
							THY_ANGLE, AT_SEP );
				else
					makeBasepair( NAR.rADE, NAR.rTHY, ADE_ANGLE, 
							THY_ANGLE, AT_SEP );
				c.displayMolecule( m );
				if ( dnaChoice.getSelectedItem() == "z-form" )
					changeZdnaParameters();
			}
			return true;
		}
		else if ( what == "A - (U)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "A" );
				if ( naChoice.getSelectedItem() == "RNA" )
					makeBasepair( NAR.rADE, NAR.rURA, ADE_ANGLE, 
							THY_ANGLE, AT_SEP );
				else
					makeBasepair( NAR.dADE, NAR.dURA, ADE_ANGLE, 
							THY_ANGLE, AT_SEP );
				c.displayMolecule( m );
			}
			return true;
		}
		else if ( what == "C - (G)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "C" );
				if ( naChoice.getSelectedItem() == "DNA" ){
					makeBasepair( NAR.dCYT, NAR.dGUA, CYT_ANGLE, 
							GUA_ANGLE, CG_SEP );
				}
				else
					makeBasepair( NAR.rCYT, NAR.rGUA, CYT_ANGLE, 
							GUA_ANGLE, CG_SEP );
				if ( dnaChoice.getSelectedItem() == "z-form" )
					changeZdnaParameters();
				c.displayMolecule( m );
			}
			return true;
		}
		else if ( what == "G - (C)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "G" );
				if ( naChoice.getSelectedItem() == "DNA" ){
					makeBasepair( NAR.dGUA, NAR.dCYT, GUA_ANGLE, 
							CYT_ANGLE, CG_SEP );
				}
				else
					makeBasepair( NAR.rGUA, NAR.rCYT, GUA_ANGLE, 
							CYT_ANGLE, CG_SEP );
				if ( dnaChoice.getSelectedItem() == "z-form" )
					changeZdnaParameters();
				c.displayMolecule( m );
			}
			return true;
		}
		else if ( what == "T - (A)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "T" );
				if ( naChoice.getSelectedItem() == "DNA" )
					makeBasepair( NAR.dTHY, NAR.dADE, THY_ANGLE, 
							ADE_ANGLE, AT_SEP );
				else 
					makeBasepair( NAR.rTHY, NAR.rADE, THY_ANGLE, 
							ADE_ANGLE, AT_SEP );
				c.displayMolecule( m );
				if ( dnaChoice.getSelectedItem() == "z-form" )
					changeZdnaParameters();
				return true;
			}
		}
		else if ( what == "U - (A)" ){
			if ( getParameterStatus() ){
				chainTextField.setText( chainTextField.getText() + "U" );
				if ( naChoice.getSelectedItem() == "RNA" )
					makeBasepair( NAR.rURA, NAR.rADE, THY_ANGLE, 
							ADE_ANGLE, AT_SEP );
				else
					makeBasepair( NAR.dURA, NAR.dADE, THY_ANGLE, 
							ADE_ANGLE, AT_SEP );
				c.displayMolecule( m );
			}
			return true;
		}
		else if ( what == "3'-CAP" ){
			boolean fiveToThree = false;
			boolean doubleStranded = false;
			if ( buildChoice.getSelectedItem() == "Build 5' to 3'" )
				fiveToThree = true;
			if ( strandChoice.getSelectedItem() == "Double Stranded" )
				doubleStranded = true;
			if( NAR.cap( m, BiomerFrame, fiveToThree, doubleStranded, false ) )
				capped3End = true;
			return true;
		}
		else if ( what == "5'-CAP" ){
			boolean fiveToThree = false;
			boolean doubleStranded = false;
			capped5End = true;
			if ( buildChoice.getSelectedItem() == "Build 5' to 3'" )
				fiveToThree = true;
			if ( strandChoice.getSelectedItem() == "Double Stranded" )
				doubleStranded = true;
			if( NAR.cap( m, BiomerFrame, fiveToThree, doubleStranded, true ) )
				capped5End = true;
			return true;
		}
		else if ( what == "Done" ){
			if ( ( m.name == null ) || ( m.name.equals( "Untitled" ) ) ){
				m.name = naChoice.getSelectedItem();
			}
			m.center();
			c.displayMolecule( m );
			dispose();
			//			m.dump();
			return true;
		}
		else if ( what == "Help" ){
			error = new ErrorDialog( BiomerFrame, "The Help option is not available in this version.", "Please check FAQs until this feature is implemented." );
			error.show();
			return true;
		}
		else if ( what == "Cancel" ){
			m = new Molecule();
			c.displayMolecule( m );
			dispose();
			return true;
		}
		else if ( leftBasepairChoice.getSelectedItem() != custom5Name ){
			custom5Name = leftBasepairChoice.getSelectedItem();
			custom5Value = getBaseValue( custom5Name );
			customBasepairName = custom5Name + "-(" + custom3Name + ")";
			customBasepairButton.setLabel( customBasepairName );
			return true;
		} 
		else if ( rightBasepairChoice.getSelectedItem() != custom3Name ){
			custom3Name = rightBasepairChoice.getSelectedItem();
			custom3Value = getBaseValue( custom3Name );
			customBasepairName = custom5Name + "-(" + custom3Name + ")";
			customBasepairButton.setLabel( customBasepairName );
			return true;
		}
		else if ( what == customBasepairName ){
			if ( getParameterStatus() ){
				if ( custom5Name.equals( "ADE" ) )
					chainTextField.setText( chainTextField.getText() + "A" ); 
				else if ( custom5Name.equals( "CYT" ) )
					chainTextField.setText( chainTextField.getText() + "C" ); 
				else if ( custom5Name.equals( "GUA" ) )
					chainTextField.setText( chainTextField.getText() + "G" ); 
				else if ( custom5Name.equals( "THY" ) )
					chainTextField.setText( chainTextField.getText() + "T" ); 
				else if ( custom5Name.equals( "URA" ) )
					chainTextField.setText( chainTextField.getText() + "U" ); 
				else if ( custom5Name.equals( " I " ) )
					chainTextField.setText( chainTextField.getText() + "I" ); 
				else if ( custom5Name.equals( " Y " ) )
					chainTextField.setText( chainTextField.getText() + "Y" ); 
				else
					chainTextField.setText( chainTextField.getText() + "(" + 
							custom5Name.trim() + ")" );
				makeBasepair( custom5Value, custom3Value, THY_ANGLE, 
						ADE_ANGLE, AT_SEP );
				c.displayMolecule( m );
			}
			return true;
		}

		return false;
	}

	public void changeZdnaParameters(){
		if ( zdnaSwitch == 0 ){
			zdna1();
			zdnaSwitch = 1;
		}
		else{
			zdna2();
			zdnaSwitch = 0;
		}
	}

	public void zdna1(){
		disableText();
		puckerChoice.select("C3'-endo");
		alphaTextField.setText("-176.2");
		betaTextField.setText("-139.0");
		gammaTextField.setText("-30.0");
		deltaTextField.setText("138.0");
		epsilonTextField.setText("4.92");
		zetaTextField.setText("-65.57");
		chiTextField.setText("-159.0");
		tipTextField.setText("2.9");
		inclinationTextField.setText("-6.2");
		openingTextField.setText("0.0");
		propellerTwistTextField.setText("-1.3");
		buckleTextField.setText("-6.2");
		twistTextField.setText("-9.4");
		rollTextField.setText("0.0");
		tiltTextField.setText("0.0");
		dxTextField.setText("3.0");
		dyTextField.setText("-2.3");
		shearTextField.setText("0.0");
		stretchTextField.setText("0.0");
		staggerTextField.setText("0.0");
		shiftTextField.setText("0.0");
		slideTextField.setText("0.0");
		riseTextField.setText("3.92");
	}

	public void zdna2(){
		disableText();
		puckerChoice.select("C3'-endo");
		alphaTextField.setText("164.57");
		betaTextField.setText("160.0");
		gammaTextField.setText("150.0");
		deltaTextField.setText("140.0");
		epsilonTextField.setText("-142.1");
		zetaTextField.setText("8.55");
		chiTextField.setText("68.0");
		tipTextField.setText("-2.9");
		inclinationTextField.setText("-6.2");
		openingTextField.setText("0.0");
		propellerTwistTextField.setText("-1.3");
		buckleTextField.setText("6.2");
		twistTextField.setText("-50.6");
		rollTextField.setText("0.0");
		tiltTextField.setText("0.0");
		dxTextField.setText("3.0");
		dyTextField.setText("2.3");
		shearTextField.setText("0.0");
		stretchTextField.setText("0.0");
		staggerTextField.setText("0.0");
		shiftTextField.setText("0.0");
		slideTextField.setText("0.0");
		riseTextField.setText("3.51");
	}

	public int getBaseValue( String name ){
		if ( naChoice.getSelectedItem() == "DNA" ){
			if ( name.equals( "ADE" ) ){
				return( NAR.dADE );
			}
			else if ( name.equals( "CYT" ) ){
				return( NAR.dCYT );
			}
			else if ( name.equals( "GUA" ) ){
				return( NAR.dGUA );
			}
			else if ( name.equals( "THY" ) ){
				return( NAR.dTHY );
			}
			else if ( name.equals( "URA" ) ){
				return( NAR.dURA );
			}
			else if ( name.equals( "1MA" ) ){
				return( NAR.d1MA );
			}
			else if ( name.equals( "1MG" ) ){
				return( NAR.d1MG );
			}
			else if ( name.equals( "2MG" ) ){
				return( NAR.d2MG );
			}
			else if ( name.equals( "5MC" ) ){
				return( NAR.d5MC );
			}
			else if ( name.equals( "7MG" ) ){
				return( NAR.d7MG );
			}
			else if ( name.equals( "H2U" ) ){
				return( NAR.dH2U );
			}
			else if ( name.equals( " I " ) ){
				return( NAR.dI );
			}
			else if ( name.equals( "M2G" ) ){
				return( NAR.dM2G );
			}
			else if ( name.equals( "OMC" ) ){
				return( NAR.dOMC );
			}
			else if ( name.equals( "OMG" ) ){
				return( NAR.dOMG );
			}
			else if ( name.equals( "PSU" ) ){
				return( NAR.dPSU );
			}
			else if ( name.equals( " Y " ) ){
				return( NAR.dY );
			}
		}	
		else{
			if ( name.equals( "ADE" ) ){
				return( NAR.rADE );
			}
			else if ( name.equals( "CYT" ) ){
				return( NAR.rCYT );
			}
			else if ( name.equals( "GUA" ) ){
				return( NAR.rGUA );
			}
			else if ( name.equals( "THY" ) ){
				return( NAR.rTHY );
			}
			else if ( name.equals( "URA" ) ){
				return( NAR.rURA );
			}
			else if ( name.equals( "1MA" ) ){
				return( NAR.r1MA );
			}
			else if ( name.equals( "1MG" ) ){
				return( NAR.r1MG );
			}
			else if ( name.equals( "2MG" ) ){
				return( NAR.r2MG );
			}
			else if ( name.equals( "5MC" ) ){
				return( NAR.r5MC );
			}
			else if ( name.equals( "7MG" ) ){
				return( NAR.r7MG );
			}
			else if ( name.equals( "H2U" ) ){
				return( NAR.rH2U );
			}
			else if ( name.equals( " I " ) ){
				return( NAR.rI );
			}
			else if ( name.equals( "M2G" ) ){
				return( NAR.rM2G );
			}
			else if ( name.equals( "OMC" ) ){
				return( NAR.rOMC );
			}
			else if ( name.equals( "OMG" ) ){
				return( NAR.rOMG );
			}
			else if ( name.equals( "PSU" ) ){
				return( NAR.rPSU );
			}
			else if ( name.equals( " Y " ) ){
				return( NAR.rY );
			}
		}	
		return( NAR.dADE );
	}

}
