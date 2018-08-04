package com.goosebumpanalytics.biomer;

import java.awt.*;

public class PolyPeptideDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	MoleculeCanvas          c;
	Molecule                m;
	final double            R2D = 57.29577951308232090712;
	final double            D2R = Math.PI / 180.0, DELTA = 0.0005;
	PolyPeptideResidue      PPD;
	Checkbox		LisomerCheckbox, DisomerCheckbox;
	CheckboxGroup		isomerCheckboxGroup;
	Choice			conformationChoice;
	TextField		phiTextField, psiTextField, omegaTextField;
	Frame			chemStructFrame;
	int			buffer = 0, bufferCounter = 0, lastResidueNumber = -1;
	int			CA = -1, C = -1, N = -1, lastCA = -1, lastC = -1, lastN = -1;
	int			O = -1, lastO = -1, H = -1, CH3 = -1, lastCH3 = -1;
	int			phiArray[], psiArray[], omegaArray[];
	double			phiBuffer[], psiBuffer[], omegaBuffer[];
	double			phi = -57, psi = 47, omega = 180;
	ErrorDialog		error;
	String			residueName = "L", fullResidueName = "L";
	double			lastOmega = 180, lastPsi = 0;
	boolean 		NCapped = false, CCapped = false, zwitterionic = false;

	PolyPeptideDialog( Molecule m, MoleculeCanvas c, Frame chemStructFrame ){
		super( chemStructFrame, "eBiomer : Build Polypeptide", true );
		//setResizable( false );
		this.m = m;
		this.chemStructFrame = chemStructFrame;
		this.c = c;

		PPD = new PolyPeptideResidue();

		layoutConstraint = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout( gridBagLayout );

		Panel p = new Panel();
		Button b;
		GridLayout gridLayout = new GridLayout( 6, 4 );
		p.setLayout( gridLayout );
		b = new Button( "Ala" );
		p.add( b );
		b = new Button( "Arg" );
		p.add( b );
		b = new Button( "Asn" );
		p.add( b );
		b = new Button( "Asp" );
		p.add( b );
		b = new Button( "Cys" );
		p.add( b );
		b = new Button( "Cyx" );
		p.add( b );
		b = new Button( "Gln" );
		p.add( b );
		b = new Button( "Glu" );
		p.add( b );
		b = new Button( "Gly" );
		p.add( b );
		b = new Button( "Hid" );
		p.add( b );
		b = new Button( "Hie" );
		p.add( b );
		b = new Button( "Hip" );
		p.add( b );
		b = new Button( "His" );
		p.add( b );
		b = new Button( "Ile" );
		p.add( b );
		b = new Button( "Leu" );
		p.add( b );
		b = new Button( "Lys" );
		p.add( b );
		b = new Button( "Met" );
		p.add( b );
		b = new Button( "Phe" );
		p.add( b );
		b = new Button( "Pro" );
		p.add( b );
		b = new Button( "Ser" );
		p.add( b );
		b = new Button( "Thr" );
		p.add( b );
		b = new Button( "Tyr" );
		p.add( b );
		b = new Button( "Trp" );
		p.add( b );
		b = new Button( "Val" );
		p.add( b );
		layoutConstraint.gridheight = GridBagConstraints.REMAINDER;
		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( p, 0, 0, 0 );
		layoutConstraint.gridheight = 1;

		layoutConstraint.fill = GridBagConstraints.BOTH;
		b = new Button( "Ace ( N-cap )" );
		//                b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 1, 0, 0 );

		b = new Button( "Nme ( C-cap )" );
		//               b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 1, 1, 0 );

		b = new Button( "Zwitterion");
		//              b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 1, 2, 0 );

		b = new Button( "Undo Zwit.");
		//             b.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( b, 1, 3, 0 );

		b = new Button( "Cancel");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 1, 4, 0 );

		b = new Button( "Done");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 1, 5, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		Label label = new Label( "Conformation", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 2, 0, 0 );

		conformationChoice = new Choice();
		conformationChoice.addItem( "3-10 Helix" );
		conformationChoice.addItem( "Alpha Helix" );
		conformationChoice.addItem( "Alpha Helix (LH)" );
		conformationChoice.addItem( "Beta Sheet (anti-prl)" );
		conformationChoice.addItem( "Beta Sheet (parallel)" );
		conformationChoice.addItem( "Beta 1 Turn" );
		conformationChoice.addItem( "Beta 1' Turn" );
		conformationChoice.addItem( "Beta 2 Turn" );
		conformationChoice.addItem( "Beta 2' Turn" );
		conformationChoice.addItem( "Beta 3 Turn" );
		conformationChoice.addItem( "Beta 3' Turn" );
		conformationChoice.addItem( "Beta 5 Turn" );
		conformationChoice.addItem( "Beta 5' Turn" );
		conformationChoice.addItem( "Beta 6a Turn" );
		conformationChoice.addItem( "Beta 6b Turn" );
		conformationChoice.addItem( "Beta 8 Turn" );
		conformationChoice.addItem( "Extended" );
		conformationChoice.addItem( "Gamma Turn" );
		conformationChoice.addItem( "Gamma Turn (rev)" );
		conformationChoice.addItem( "Omega Helix" );
		conformationChoice.addItem( "Pi Helix" );
		conformationChoice.addItem( "Polyglycine II" );
		conformationChoice.addItem( "Polyproline I" );
		conformationChoice.addItem( "Polyproline II" );
		conformationChoice.addItem( "Other" );
		conformationChoice.select( 16 );

		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( conformationChoice, 2, 1, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		p = new Panel();
		label = new Label( "Phi", Label.LEFT );                
		p.add( label );
		phiTextField = new TextField( "-58.0", 8 );
		phiTextField.setEditable( false );
		p.add( phiTextField );
		add( p, 2, 2, 1 );

		p = new Panel();
		label = new Label( "Psi", Label.LEFT );                
		p.add( label );
		psiTextField = new TextField( "-47.0", 8 );
		psiTextField.setEditable( false );
		p.add( psiTextField );
		add( p, 2, 3, 1 );

		p = new Panel();
		label = new Label( "Omega", Label.LEFT );                
		p.add( label );
		omegaTextField = new TextField( "180.0", 8 );
		omegaTextField.setEditable( false );
		p.add( omegaTextField );
		add( p, 2, 4, 1 );

		p = new Panel();
		label = new Label( "Isomer", Label.LEFT );                
		p.add( label );
		isomerCheckboxGroup = new CheckboxGroup();
		LisomerCheckbox = new Checkbox( "L", isomerCheckboxGroup, true );
		p.add( LisomerCheckbox );
		DisomerCheckbox = new Checkbox( "D", isomerCheckboxGroup, false );
		p.add( DisomerCheckbox );
		add( p, 2, 5, 1  );	

		reshape( 300, 300, 440, 250 );
		action( null, "Extended" );
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
		if ( what == "3-10 Helix" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-49.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-26.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}	
		else if ( what == "Alpha Helix" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Alpha Helix (LH)" ){
			phiTextField.setEditable( false );
			phiTextField.setText("57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Beta Sheet (anti-prl)" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-139.0");
			psiTextField.setEditable( false );
			psiTextField.setText("135.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("-178.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}	
		else if ( what == "Beta Sheet (parallel)" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-119.0");
			psiTextField.setEditable( false );
			psiTextField.setText("113.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("-178.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}	
		else if ( what == "Omega Helix" ){
			phiTextField.setEditable( false );
			phiTextField.setText("64.0");
			psiTextField.setEditable( false );
			psiTextField.setText("55.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Pi Helix" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-70.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Polyglycine II" ){
			phiTextField.setEditable( false );
			phiTextField.setText("80.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-150.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Polyglycine II (LH)" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-80.0");
			psiTextField.setEditable( false );
			psiTextField.setText("150.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Polyproline I" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-83.0");
			psiTextField.setEditable( false );
			psiTextField.setText("158.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("0.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Polyproline II" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-80.0");
			psiTextField.setEditable( false );
			psiTextField.setText("150.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Other" ){
			phiTextField.setEditable( true );
			psiTextField.setEditable( true );
			omegaTextField.setEditable( true );
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}
		else if ( what == "Extended" ){
			phiTextField.setEditable( false );
			phiTextField.setText("180.0");
			psiTextField.setEditable( false );
			psiTextField.setText("180.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.white );
			bufferCounter = 0;
			buffer = 0;
			return true;
		}	
		else if ( what == "Gamma Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("180.0");
			psiTextField.setEditable( false );
			psiTextField.setText("180.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 3;

			phiBuffer = new double[ 3 ];
			psiBuffer = new double[ 3 ];
			omegaBuffer = new double[ 3 ];

			phiBuffer[ 0 ] = 180.0;
			phiBuffer[ 1 ] = 75.0;
			phiBuffer[ 2 ] = 180.0;

			psiBuffer[ 0 ] = 180.0;
			psiBuffer[ 1 ] = -65.0;
			psiBuffer[ 2 ] = 180.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			return true;
		}	
		else if ( what == "Gamma Turn (rev)" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 3;

			phiBuffer = new double[ 3 ];
			psiBuffer = new double[ 3 ];
			omegaBuffer = new double[ 3 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -75.0;
			phiBuffer[ 2 ] = 180.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 65.0;
			psiBuffer[ 2 ] = 180.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 1 Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -60.0;
			phiBuffer[ 2 ] = -90.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = -30.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 2 Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -60.0;
			phiBuffer[ 2 ] = 80.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 120.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 3 Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -60.0;
			phiBuffer[ 2 ] = -60.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = -30.0;
			psiBuffer[ 2 ] = -30.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 5 Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -80.0;
			phiBuffer[ 2 ] = -80.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 80.0;
			psiBuffer[ 2 ] = 80.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 6a Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -60.0;
			phiBuffer[ 2 ] = -90.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 120.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 6b Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -120.0;
			phiBuffer[ 2 ] = -60.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 120.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 8 Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = -60.0;
			phiBuffer[ 2 ] = -120.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = -30.0;
			psiBuffer[ 2 ] = 120.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 1' Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = 60.0;
			phiBuffer[ 2 ] = 90.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 30.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 2' Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = 60.0;
			phiBuffer[ 2 ] = -80.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = -120.0;
			psiBuffer[ 2 ] = 0.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 3' Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = 60.0;
			phiBuffer[ 2 ] = 60.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = 30.0;
			psiBuffer[ 2 ] = 30.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Beta 5' Turn" ){
			phiTextField.setEditable( false );
			phiTextField.setText("-57.0");
			psiTextField.setEditable( false );
			psiTextField.setText("-47.0");
			omegaTextField.setEditable( false );
			omegaTextField.setText("180.0");
			conformationChoice.setBackground( Color.lightGray );

			bufferCounter = 0;
			buffer = 4;

			phiBuffer = new double[ 4 ];
			psiBuffer = new double[ 4 ];
			omegaBuffer = new double[ 4 ];

			phiBuffer[ 0 ] = -57.0;
			phiBuffer[ 1 ] = 80.0;
			phiBuffer[ 2 ] = 80.0;
			phiBuffer[ 3 ] = -57.0;

			psiBuffer[ 0 ] = -47.0;
			psiBuffer[ 1 ] = -80.0;
			psiBuffer[ 2 ] = -80.0;
			psiBuffer[ 3 ] = -47.0;

			omegaBuffer[ 0 ] = 180.0;
			omegaBuffer[ 1 ] = 180.0;
			omegaBuffer[ 2 ] = 180.0;
			omegaBuffer[ 3 ] = 180.0;
			return true;
		}	
		else if ( what == "Zwitterion" ){
			if ( lastResidueNumber == -1 ){
				error = new ErrorDialog( chemStructFrame, "You must first create a polypeptide before you make it ionic.", "Suggestion:  Try making the polypeptide a zwitterion as the last step." );
				error.show();
				return true;
			}
			if ( zwitterionic )
				return true;
			// do first residue
			int firstN = -1, firstH = -1, firstC = -1, firstHA1 = -1, firstCA = -1;
			int firstCH3 = -1, firstH1 = -1, firstO = -1;
			for ( int i = 0; i < m.residue[ 0 ].numberOfAtoms; i++ ){
				String name = m.residue[ 0 ].atom[ i ].name;
				int number = m.residue[ 0 ].atom[ i ].moleculeAtomNumber;
				if ( name.equals( "N" ) )
					firstN = number;
				else if ( name.equals( "H" ) )
					firstH = number;
				else if ( name.equals( "CA" ) )
					firstCA = number;
				else if ( name.equals( "C" ) )
					firstC = number;
				else if ( name.equals( "CH3" ) )
					firstCH3 = number;
				else if ( name.equals( "HA1" ) )
					firstHA1 = number;
			}
			if ( ( firstN != -1 ) && ( firstH != -1 ) && ( firstCA != -1 ) ){
				m.resolveCoordinates( firstH );
				m.atom[ firstH ].name = "1H";
				Atom H2 = new Atom( "2H", m.atom[ firstH ].coord[ 0 ], 
						m.atom[ firstH ].coord[ 1 ], m.atom[ firstH ].coord[ 2 ] );
				m.addAtom( m.atom[firstN].totalResidueNumber, H2 );
				int H2Num = H2.moleculeAtomNumber;
				Atom H3 = new Atom( "3H", m.atom[ firstH ].coord[ 0 ], 
						m.atom[ firstH ].coord[ 1 ], m.atom[ firstH ].coord[ 2 ] );
				m.addAtom( m.atom[firstN].totalResidueNumber, H3 );
				int H3Num = H3.moleculeAtomNumber;
				if ( firstC != -1 ){
					double originalTorsion = m.atom[ firstH ].torsion( m.atom[
					                                                          firstC ], m.atom[ firstCA ], m.atom[ firstN ] );
					m.setTorsion( firstC, firstCA, firstN, H2Num, 120 +
							originalTorsion, null, 0 );
					m.setTorsion( firstC, firstCA, firstN, H3Num, 240 +
							originalTorsion, null, 0 );
				}
				else if ( firstHA1 != -1 ){
					double originalTorsion = m.atom[ firstH ].torsion( m.atom[
					                                                          firstHA1 ], m.atom[ firstCA ], m.atom[ firstN ] );
					m.setTorsion( firstHA1, firstCA, firstN, H2Num, 120 +
							originalTorsion, null, 0 );
					m.setTorsion( firstHA1, firstCA, firstN, H3Num, 240 +
							originalTorsion, null, 0 );
				}
				m.atom[ firstN ].addBond( H2Num, H3Num );
			}
			// now do C-cap
			for ( int i = 0; i < m.residue[ lastResidueNumber ].numberOfAtoms; i++ ){
				String name = m.residue[ lastResidueNumber ].atom[ i ].name;
				int number = m.residue[ lastResidueNumber ].atom[ i 
				                                                  ].moleculeAtomNumber;
				if ( name.equals( "N" ) )
					firstN = number;
				else if ( name.equals( "O" ) )
					firstO = number;
				else if ( name.equals( "CA" ) )
					firstCA = number;
				else if ( name.equals( "C" ) )
					firstC = number;
				else if ( name.equals( "CH3" ) )
					firstCH3 = number;
				else if ( name.equals( "HA1" ) )
					firstHA1 = number;
			}
			if ( ( firstCA != -1 ) && ( firstC != -1 ) && ( firstO != -1 ) ){
				m.resolveCoordinates( firstO );
				Atom OXT = new Atom( "OXT", m.atom[ firstO ].coord[ 0 ], 
						m.atom[ firstO ].coord[ 1 ], m.atom[ firstO ].coord[ 2 ] );
				m.addAtom( m.atom[firstC].totalResidueNumber, OXT );
				int OXTNum = OXT.moleculeAtomNumber;
				if ( firstN != -1 ){
					double originalTorsion = m.atom[ firstO ].torsion( m.atom[
					                                                          firstN ], m.atom[ firstCA ], m.atom[ firstC ] );
					m.setTorsion( firstN, firstCA, firstC, OXTNum, 180 +
							originalTorsion, null, 0 );
				}
				else if ( ( firstH1 != -1 ) && ( firstCH3 != -1 ) ){
					double originalTorsion = m.atom[ firstO ].torsion( m.atom[
					                                                          firstH1 ], m.atom[ firstCH3 ], m.atom[ firstC ] );
					m.setTorsion( firstH1, firstCH3, firstC, OXTNum, 180 +
							originalTorsion, null, 0 );
				}
				m.atom[ firstC ].addBond( OXTNum );
			}
			zwitterionic = true;
			c.displayMolecule( m );
			return true;
		}
		else if ( what == "Undo Zwit." ){
			if ( lastResidueNumber == -1 ){
				error = new ErrorDialog( chemStructFrame, "You must first create a polypeptide before you make undo the ionic ends.", "Suggestion:  Build a polypeptide and make it a zwitterion before pressing the Undo button." );
				error.show();
				return true;
			}
			if ( !( zwitterionic ) ){
				return true;
			}
			int firstN = -1, firstH1 = -1, firstH2 = -1, firstH3 = -1, firstOXT = -1;
			for ( int i = 0; i < m.residue[ 0 ].numberOfAtoms; i++ ){
				String name = m.residue[ 0 ].atom[ i ].name;
				int number = m.residue[ 0 ].atom[ i ].moleculeAtomNumber;
				if ( name.equals( "N" ) )
					firstN = number;
				else if ( name.equals( "1H" ) )
					firstH1 = number;
				else if ( name.equals( "2H" ) )
					firstH2 = number;
				else if ( name.equals( "3H" ) )
					firstH3 = number;
			}
			if ( ( firstN != -1 ) && ( firstH1 != -1 ) && ( firstH2 != -1 ) &&
					( firstH3 != -1 ) ){
				m.deleteAtom( m.getMoleculeAtomNumber( "2H", 0 ) );
				m.deleteAtom( m.getMoleculeAtomNumber( "3H", 0 ) );
				m.atom[ m.getMoleculeAtomNumber( "1H", 0 ) ].name = "H";
			}
			for ( int i = 0; i < m.residue[ lastResidueNumber ].numberOfAtoms; i++ ){
				String name = m.residue[ lastResidueNumber ].atom[ i ].name;
				int number=m.residue[lastResidueNumber].atom[i].moleculeAtomNumber;
				if ( name.equals( "OXT" ) )
					firstOXT = number;
			}
			if ( firstOXT != -1 ){
				m.deleteAtom( m.getMoleculeAtomNumber( "OXT", lastResidueNumber ) );
			}

			zwitterionic = false;
			c.displayMolecule( m );
			return true;
		}
		else if ( what == "Cancel" ){
			dispose();
			return true;
		}
		else if ( what == "Done" ){
			m.center();
			c.displayMolecule( m );
			dispose();
			return true;
		}
		else if ( ( what == "Ala" ) || ( what == "Arg" ) || ( what == "Asn" ) || 
				( what == "Asp" ) || ( what == "Cys" ) || ( what == "Cyx" ) || 
				( what == "Gln" ) || ( what == "Gly" ) || ( what == "Glu" ) || 
				( what == "Ser" ) || ( what == "Thr" ) || ( what == "Leu" ) || 
				( what == "Ile" ) || ( what == "Val" ) || ( what == "Hid" ) || 
				( what == "Hie" ) || ( what == "Hip" ) || ( what == "His" ) || 
				( what == "Trp" ) || ( what == "Phe" ) || ( what == "Tyr" ) || 
				( what == "Pro" ) || ( what == "Met" ) || ( what == "Lys" ) ||
				( what == "Ace ( N-cap )" ) || ( what == "Nme ( C-cap )" ) ){
			if ( CCapped ){
				error = new ErrorDialog( chemStructFrame, "The polypeptide sequence has a C-terminating residue ( Nme ).  You can no longer add residues to this polypeptide", "Suggestion:  Do not add Nme until the end." );
				error.show();
				return true;
			}
			fullResidueName = what.toString().toLowerCase();
			if ( what == "Ace ( N-cap )" ){
				fullResidueName = "ace";
				if ( lastResidueNumber != -1 ){
					error = new ErrorDialog( chemStructFrame, "Ace is a N-terminating residue.  It can only be added as the first residue in a polypeptide", "Suggestion:  Forget about Acetyl.  There are plenty of other residues." );
					error.show();
					return true;
				}
			}
			else if ( what == "Nme ( C-cap )" ){
				CCapped = true;
				fullResidueName = "nme";
			}
			if ( getTorsionAngles() ){
				if ( isomerCheckboxGroup.getCurrent() == LisomerCheckbox )
					residueName = "L";
				else
					residueName = "D";
				residueName += fullResidueName;
				Residue newResidue = PPD.getResidue( residueName );
				m.addResidue( 0, newResidue );
				phiArray = new int[ newResidue.numberOfAtoms ];
				psiArray = new int[ newResidue.numberOfAtoms + 1 ];
				omegaArray = new int[ newResidue.numberOfAtoms ];
				int omegaArrayCounter = 0;
				int phiArrayCounter = 0;
				if ( lastResidueNumber > -1 )
					m.connectResidues( lastResidueNumber, "C", 
							newResidue.totalResidueNumber, "N" );
				for ( int i = 0; i < newResidue.numberOfAtoms; i++ ){
					String name = newResidue.atom[ i ].name;
					int number = newResidue.atom[ i ].moleculeAtomNumber;
					if ( name.equals( "C" ) ){
						phiArray[ phiArrayCounter++ ] = number;
						C = number;
					}
					else if ( name.equals( "N" ) ){
						N = number;
					}
					else if ( name.equals( "CA" ) ){
						CA = number;
					}
					else if ( name.equals( "CH3" ) ){
						CH3 = number;
					}
					else if ( name.equals( "O" ) ){
						phiArray[ phiArrayCounter++ ] = number;
						O = number;
					}
					else if ( name.equals( "H" ) ){
						phiArray[ phiArrayCounter++ ] = number;
						H = number;
					}
					else
						phiArray[ phiArrayCounter++ ] = number;
					omegaArray[ omegaArrayCounter++ ] = number;
				}
				if ( lastResidueNumber > -1 ){
					m.setBondDistance( lastC, N, 1.47, 
							omegaArray, newResidue.numberOfAtoms );
					if ( lastN > -1 ){
						m.setAngle( lastCA, lastC, N, 111.2, 
								omegaArray, newResidue.numberOfAtoms ); 
						m.setAngle( lastC, N, CA, 121.9, 
								omegaArray, newResidue.numberOfAtoms ); 
						m.setTorsion( lastN, lastCA, lastC, N, lastPsi, 
								omegaArray, omegaArrayCounter );
						if ( !CCapped ){
							m.setTorsion( lastCA, lastC, N, C, lastOmega, 
									omegaArray, omegaArrayCounter );
						}
					}
					if ( !CCapped ){
						m.setTorsion( lastC, N, CA, C, phi, 
								phiArray, phiArrayCounter );  
					}
					// The following fixes the C=O bond angle
					Atom pseudoAtom = new Atom( "pseudo", 0.0f, 0.0f, 0.0f );
					m.addAtom( m.atom[CA].totalResidueNumber, pseudoAtom );
					int pseudoNum = pseudoAtom.moleculeAtomNumber;
					if ( lastCA > -1 ){
						pseudoAtom.coord[ 0 ] = (float)( ( 
								m.atom[lastCA].coord[ 0 ] + 
								m.atom[N].coord[ 0 ] ) / 2.0 );
						pseudoAtom.coord[ 1 ] = (float)( ( 
								m.atom[lastCA].coord[ 1 ] + 
								m.atom[N].coord[ 1 ] ) / 2.0 );
						pseudoAtom.coord[ 2 ] = (float)( ( 
								m.atom[lastCA].coord[ 2 ] +
								m.atom[N].coord[ 2 ] ) / 2.0 );
					}
					else{
						pseudoAtom.coord[ 0 ] = (float)( ( 
								m.atom[lastCH3].coord[ 0 ] + 
								m.atom[N].coord[ 0 ] ) / 2.0 );
						pseudoAtom.coord[ 1 ] = (float)( ( 
								m.atom[lastCH3].coord[ 1 ] + 
								m.atom[N].coord[ 1 ] ) / 2.0 );
						pseudoAtom.coord[ 2 ] = (float)( ( 
								m.atom[lastCH3].coord[ 2 ] +
								m.atom[N].coord[ 2 ] ) / 2.0 );
					}
					m.updateCoordinates( pseudoAtom );
					m.setAngle( pseudoNum, lastC, lastO, 180, null, 0 );
					// The following fixes the N-H bond angle
					if ( what != "Pro" ){
						pseudoAtom.coord[ 0 ] = (float)( ( m.atom[CA].coord[ 
						                                                    0 ] + m.atom[lastC].coord[ 0 ] ) / 2.0 );
						pseudoAtom.coord[ 1 ] = (float)( ( m.atom[CA].coord[ 
						                                                    1 ] + m.atom[lastC].coord[ 1 ] ) / 2.0 );
						pseudoAtom.coord[ 2 ] = (float)( ( m.atom[CA].coord[ 
						                                                    2 ] + m.atom[lastC].coord[ 2 ] ) / 2.0 );
						m.updateCoordinates( pseudoAtom );
						m.setAngle( pseudoNum, N, H, 180, null, 0 );
					}
					m.deleteAtom( pseudoNum ); 
				}
				lastC = C;
				lastN = N;
				lastCA = CA;
				lastO = O;
				lastCH3 = CH3;
				lastResidueNumber = newResidue.totalResidueNumber;
				lastOmega = omega;
				lastPsi = psi;
				c.displayMolecule( m );
			}
			return true;
		}
		return false;
	}

	public boolean getTorsionAngles(){
		if ( buffer == 0 ){
			try{
				phi = new Double( phiTextField.getText() ).doubleValue();
			}
			catch ( NumberFormatException e ){
				error = new ErrorDialog( chemStructFrame, "The value for 'phi' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
				error.show();
				return( false );
			}
			try{
				psi = new Double( psiTextField.getText() ).doubleValue();
			}
			catch ( NumberFormatException e ){
				error = new ErrorDialog( chemStructFrame, "The value for 'psi' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
				error.show();
				return( false );
			}
			try{
				omega = new Double( omegaTextField.getText() ).doubleValue();
			}
			catch ( NumberFormatException e ){
				error = new ErrorDialog( chemStructFrame, "The value for 'omega' must be a number.", "Suggestion: See if you put an O ( oh ) in instead of a 0 ( zero ) or l ( ell ) instead of 1 ( one )." );
				error.show();
				return( false );
			}
			bufferCounter++;
			return( true );
		}
		if ( bufferCounter < buffer ){
			phi = phiBuffer[ bufferCounter ];
			psi = psiBuffer[ bufferCounter ];
			omega = omegaBuffer[ bufferCounter ];
			if ( bufferCounter == buffer - 1 ){
				phiTextField.setText( new Float( (float)phiBuffer[ 0 ] 
						).toString() );
				psiTextField.setText( new Float( (float)psiBuffer[ 0 ] 
						).toString() );
				omegaTextField.setText( new Float( (float)omegaBuffer[ 0 ] 
						).toString() );
				conformationChoice.setBackground( Color.white );
				bufferCounter = 0;
			}
			else{	
				phiTextField.setText( new Float( (float)phiBuffer[ 
				                                                  bufferCounter+1 ] ).toString() );
				psiTextField.setText( new Float( (float)psiBuffer[ 
				                                                  bufferCounter+1 ] ).toString() );
				omegaTextField.setText( new Float( (float)omegaBuffer[ 
				                                                      bufferCounter+1 ] ).toString() );
				conformationChoice.setBackground( Color.lightGray );
				bufferCounter++;
			}
			return( true );
		}
		return( false );
	}


}

