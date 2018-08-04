package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.lang.*;

public class PolySaccharideDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	MoleculeCanvas          c;
	Molecule                m;
	PolySaccharideResidue	PSR;
	Checkbox		LisomerCheckbox, DisomerCheckbox, alphaAnomerCheckbox;
	Checkbox		betaAnomerCheckbox;
	CheckboxGroup		isomerCheckboxGroup, anomerCheckboxGroup;
	Choice			connectivityChoice;
	TextField		phiTextField, psiTextField, omegaTextField;
	Frame			chemStructFrame;
	int			buffer = 0, bufferCounter = 0, lastResidueNumber = -1;
	int			O1 = -1, O2 = -1, O3 = -1, O4 = -1, O6 = -1, lastO1 = -1;
	int			C1 = -1, C2 = -1, C3 = -1, C4 = -6, C6 = -1, lastC1 = -1;
	int			H1 = -1, H2 = -1, H3 = -1, H4 = -6, H6 = -1, lastO = -1;
	int			C5 = -1, O = -1, O5 = -1, HO1 = -1, HO2 = -1, HO3 = -1;
	int			HO4 = -1, HO5 = -1, HO6 = -1, lastHO1 = -1, H61 = -1, H62 = -1;
	int			lastC2 = -1;
	int			omegaArray[];
	double			phi = 180, psi = 180, omega = 180;
	ErrorDialog		error;
	String			residueName = "L", fullResidueName = "L";
	double			lastOmega = 180, lastPsi = 0, lastPhi = 0;
	boolean 		alpha = true, capped = false;

	PolySaccharideDialog( Molecule m, MoleculeCanvas c, Frame chemStructFrame ){
		super( chemStructFrame, "eBiomer : Build Polysaccharide", true );
		setResizable( false );
		this.m = m;
		this.chemStructFrame = chemStructFrame;
		this.c = c;

		PSR = new PolySaccharideResidue();

		layoutConstraint = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout( gridBagLayout );

		Panel p = new Panel();
		Button b;
		GridLayout gridLayout = new GridLayout( 7, 2 );
		p.setLayout( gridLayout );
		b = new Button( "Allose" );
		p.add( b );
		b = new Button( "Altrose" );
		p.add( b );
		b = new Button( "Arabinose" );
		p.add( b );
		b = new Button( "Fructose" );
		p.add( b );
		b = new Button( "Galactose" );
		p.add( b );
		b = new Button( "Glucose" );
		p.add( b );
		b = new Button( "Gulose" );
		p.add( b );
		b = new Button( "Idose" );
		p.add( b );
		b = new Button( "Lyxose" );
		p.add( b );
		b = new Button( "Mannose" );
		p.add( b );
		b = new Button( "Ribose" );
		p.add( b );
		b = new Button( "Talose" );
		p.add( b );
		b = new Button( "Xylose" );
		p.add( b );
		layoutConstraint.gridheight = GridBagConstraints.REMAINDER;
		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( p, 0, 0, 0 );
		layoutConstraint.gridheight = 1;
		layoutConstraint.fill = GridBagConstraints.NONE;

		Label label = new Label( "Connectivity", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 1, 0, 0 );

		connectivityChoice = new Choice();
		connectivityChoice.addItem( "O1 - C1" );
		connectivityChoice.addItem( "O1 - C2" );
		connectivityChoice.addItem( "O1 - C3" );
		connectivityChoice.addItem( "O1 - C4" );
		connectivityChoice.addItem( "O1 - C5" );
		connectivityChoice.addItem( "O1 - C6" );
		connectivityChoice.select( 3 );

		add( connectivityChoice, 1, 1, 0 );

		label = new Label( "Anomer", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 1, 2, 0 );

		p = new Panel();
		anomerCheckboxGroup = new CheckboxGroup();
		alphaAnomerCheckbox = new Checkbox( "alpha", anomerCheckboxGroup, true );
		p.add( alphaAnomerCheckbox );
		betaAnomerCheckbox = new Checkbox( "beta", anomerCheckboxGroup, false );
		p.add( betaAnomerCheckbox );
		add( p, 1, 3, 0  );	

		label = new Label( "Isomer", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 1, 4, 0 );

		p = new Panel();
		isomerCheckboxGroup = new CheckboxGroup();
		LisomerCheckbox = new Checkbox( "L", isomerCheckboxGroup, false );
		p.add( LisomerCheckbox );
		DisomerCheckbox = new Checkbox( "D", isomerCheckboxGroup, true );
		p.add( DisomerCheckbox );
		add( p, 1, 5, 0  );	

		label = new Label( "Conformation", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.BOLD, 14 ) );
		add( label, 2, 0, 0 );

		p = new Panel();
		label = new Label( "Phi", Label.LEFT );                
		p.add( label );
		phiTextField = new TextField( "180.0", 8 );
		phiTextField.setEditable( true );
		p.add( phiTextField );
		add( p, 2, 1, 1 );

		p = new Panel();
		label = new Label( "Psi", Label.LEFT );                
		p.add( label );
		psiTextField = new TextField( "180.0", 8 );
		psiTextField.setEditable( true );
		p.add( psiTextField );
		add( p, 2, 2, 1 );

		p = new Panel();
		label = new Label( "Omega", Label.LEFT );                
		p.add( label );
		omegaTextField = new TextField( "180.0", 8 );
		omegaTextField.setEditable( false );
		p.add( omegaTextField );
		add( p, 2, 3, 1 );

		layoutConstraint.fill = GridBagConstraints.BOTH;
		b = new Button( "Cancel");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 2, 4, 0 );

		b = new Button( "Done");
		b.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		add( b, 2, 5, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;


		reshape( 200, 200, 460, 240 );
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
			m.center();
			c.displayMolecule( m );
			dispose();
			return true;
		}
		else if ( ( what == "O1 - C5" ) || ( what == "O1 - C6" ) ){
			omegaTextField.setEditable( true );
			return true;
		}
		else if ( ( what == "O1 - C1" ) || ( what == "O1 - C2" ) || ( what == "O1 - C3" ) ||
				( what == "O1 - C4" ) ){
			omegaTextField.setEditable( false );
			return true;
		}
		else if ( ( what == "Allose" ) || ( what == "Altrose" ) || ( what == "Glucose" ) || 
				( what == "Mannose" ) || ( what == "Gulose" ) || ( what == "Idose" ) || 
				( what == "Galactose" ) || ( what == "Talose" ) || ( what == "Fructose" ) ){
			residueName = what.toString();
			if ( capped ){
				error = new ErrorDialog( chemStructFrame, "You can no longer add to the polysaccharide since the last residue was linked to O1.  The O1-C1 bond acts as a cap to the chain.", "Suggestion: Use O1-C1 only for disaccharides or to cap a chain." );
				error.show();
				return true;
			}
			if ( connectivityChoice.getSelectedIndex() == 4 ){
				error = new ErrorDialog( chemStructFrame, "You chose the O1-C5 linkage, yet there is no O5 in the sugar you selected", "Suggestion: For the O1-C5 linkage, use Arabinose, Lyxose, Ribose, or Xylose." );
				error.show();
				return true;
			}
			if ( getTorsionAngles() ){
				if ( anomerCheckboxGroup.getCurrent() == alphaAnomerCheckbox )
					alpha = true;
				else
					alpha = false;
				Residue newResidue = PSR.getResidue( residueName, alpha );
				m.addResidue( 0, newResidue );
				if ( isomerCheckboxGroup.getCurrent() == LisomerCheckbox ){
					m.mirror( newResidue.totalResidueNumber );
				}
				int omegaArrayCounter = 0;
				omegaArray = new int[ newResidue.numberOfAtoms ];
				for ( int i = 0; i < newResidue.numberOfAtoms; i++ ){
					String name = newResidue.atom[ i ].name;
					int number = newResidue.atom[ i ].moleculeAtomNumber;
					if ( name.equals( "O1" ) ){
						O1 = number;
					}
					else if ( name.equals( "O2" ) ){
						O2 = number;
					}
					else if ( name.equals( "O3" ) ){
						O3 = number;
					}
					else if ( name.equals( "O4" ) ){
						O4 = number;
					}
					else if ( name.equals( "O5" ) ){
						O5 = number;
					}
					else if ( name.equals( "O" ) ){
						O = number;
					}
					else if ( name.equals( "O6" ) ){
						O6 = number;
					}
					else if ( name.equals( "HO1" ) ){
						HO1 = number;
					}
					else if ( name.equals( "HO2" ) ){
						HO2 = number;
					}
					else if ( name.equals( "HO3" ) ){
						HO3 = number;
					}
					else if ( name.equals( "HO4" ) ){
						HO4 = number;
					}
					else if ( name.equals( "HO6" ) ){
						HO6 = number;
					}
					else if ( name.equals( "C1" ) ){
						C1 = number;
					}
					else if ( name.equals( "C2" ) ){
						C2 = number;
					}
					else if ( name.equals( "C3" ) ){
						C3 = number;
					}
					else if ( name.equals( "C4" ) ){
						C4 = number;
					}
					else if ( name.equals( "C5" ) ){
						C5 = number;
					}
					else if ( name.equals( "C6" ) ){
						C6 = number;
					}
					else if ( name.equals( "H1" ) ){
						H1 = number;
					}
					else if ( name.equals( "H2" ) ){
						H2 = number;
					}
					else if ( name.equals( "H3" ) ){
						H3 = number;
					}
					else if ( name.equals( "H4" ) ){
						H4 = number;
					}
					else if ( name.equals( "H6" ) ){
						H6 = number;
					}
					else if ( name.equals( "H61" ) ){
						H61 = number;
					}
					else if ( name.equals( "H61" ) ){
						H62 = number;
					}
					omegaArray[ omegaArrayCounter++ ] = number;
				}
				int connectTo = C4;
				int connect2 = C3;
				int become = O3;
				String becomeString = "O3";
				String removeH = "HO3";
				if ( lastResidueNumber > -1 ){
					switch( connectivityChoice.getSelectedIndex() ){
					case 0:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C1" );
						connectTo = C1;
						connect2 = O;
						become = O1;
						becomeString = "O1";
						removeH = "HO1";
						capped = true;
						break;
					case 1:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C2" );
						connectTo = C2;
						connect2 = C1;
						become = O2;
						becomeString = "O2";
						removeH = "HO2";
						break;
					case 2:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C3" );
						connectTo = C3;
						connect2 = C2;
						become = O3;
						becomeString = "O3";
						removeH = "HO3";
						break;
					case 3:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C4" );
						connectTo = C4;
						connect2 = C3;
						become = O4;
						becomeString = "O4";
						removeH = "HO4";
						break;
					case 4:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C5" );
						connectTo = C5;
						connect2 = C4;
						become = O5;
						becomeString = "O5";
						removeH = "H5";
						break;
					case 5:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C6" );
						connectTo = C6;
						connect2 = C5;
						become = O6;
						becomeString = "O6";
						removeH = "H6";
					}
					lastO1 = m.getMoleculeAtomNumber( "O1", 
							newResidue.totalResidueNumber - 1 );
					lastC1 = m.getMoleculeAtomNumber( "C1", 
							newResidue.totalResidueNumber - 1 );
					lastO = m.getMoleculeAtomNumber( "O", 
							newResidue.totalResidueNumber - 1 );
					m.setBondDistance( lastO1, become, 0.0, 
							omegaArray, newResidue.numberOfAtoms );
					m.setAngle( lastC1, lastO1, connectTo, 109.5, 
							omegaArray, newResidue.numberOfAtoms ); 
					m.setTorsion( lastO, lastC1, lastO1, connectTo, lastPhi, 
							omegaArray, newResidue.numberOfAtoms );  
					m.setTorsion( lastC1, lastO1, connectTo, connect2, psi, 
							omegaArray, newResidue.numberOfAtoms );
					if ( connectTo == C6 ){
						m.setTorsion( lastO1, connectTo, connect2, C4, 
								omega, omegaArray, omegaArrayCounter );
						fixH6BondAngles( lastResidueNumber, 
								lastResidueNumber + 1 );
					}
					m.deleteAtom( removeH, newResidue.totalResidueNumber );
					m.deleteAtom( becomeString, newResidue.totalResidueNumber );
					m.deleteAtom( "HO1", newResidue.totalResidueNumber - 1 );
				}
				lastResidueNumber = newResidue.totalResidueNumber;
				lastOmega = omega;
				lastPhi = phi;
				lastPsi = psi;
				c.displayMolecule( m );
			}
			return true;
		}
		else if ( ( what == "Arabinose" ) || ( what == "Lyxose" ) || ( what == "Ribose" ) || 
				( what == "Xylose" ) ){
			residueName = what.toString();
			if ( capped ){
				error = new ErrorDialog( chemStructFrame, "You can no longer add to the polysaccharide since the last residue was linked to O1.  The O1-C1 bond acts as a cap to the chain.", "Suggestion: Use O1-C1 only for disaccharides or to cap a chain." );
				error.show();
				return true;
			}
			if ( connectivityChoice.getSelectedIndex() == 3 ){
				error = new ErrorDialog( chemStructFrame, "You chose the O1-C4 linkage, yet there is no O4 in the sugar you selected", "Suggestion: For the O1-C4 linkage, do not use Arabinose, Lyxose, Ribose, or Xylose." );
				error.show();
				return true;
			}
			else if ( connectivityChoice.getSelectedIndex() == 5 ){
				error = new ErrorDialog( chemStructFrame, "You chose the O1-C6 linkage, yet there is no C6 in the sugar you selected", "Suggestion: For the O1-C6 linkage, do not use Arabinose, Lyxose, Ribose, or Xylose." );
				error.show();
				return true;
			}
			if ( getTorsionAngles() ){
				if ( anomerCheckboxGroup.getCurrent() == alphaAnomerCheckbox )
					alpha = true;
				else
					alpha = false;
				Residue newResidue = PSR.getResidue( residueName, alpha );
				m.addResidue( 0, newResidue );
				if ( isomerCheckboxGroup.getCurrent() == LisomerCheckbox ){
					m.mirror( newResidue.totalResidueNumber );
				}
				int omegaArrayCounter = 0;
				omegaArray = new int[ newResidue.numberOfAtoms ];
				for ( int i = 0; i < newResidue.numberOfAtoms; i++ ){
					String name = newResidue.atom[ i ].name;
					int number = newResidue.atom[ i ].moleculeAtomNumber;
					if ( name.equals( "O1" ) ){
						O1 = number;
					}
					else if ( name.equals( "O2" ) ){
						O2 = number;
					}
					else if ( name.equals( "O3" ) ){
						O3 = number;
					}
					else if ( name.equals( "O4" ) ){
						O4 = number;
					}
					else if ( name.equals( "O5" ) ){
						O5 = number;
					}
					else if ( name.equals( "O" ) ){
						O = number;
					}
					else if ( name.equals( "O6" ) ){
						O6 = number;
					}
					else if ( name.equals( "HO1" ) ){
						HO1 = number;
					}
					else if ( name.equals( "HO2" ) ){
						HO2 = number;
					}
					else if ( name.equals( "HO3" ) ){
						HO3 = number;
					}
					else if ( name.equals( "HO4" ) ){
						HO4 = number;
					}
					else if ( name.equals( "HO6" ) ){
						HO6 = number;
					}
					else if ( name.equals( "C1" ) ){
						C1 = number;
					}
					else if ( name.equals( "C2" ) ){
						C2 = number;
					}
					else if ( name.equals( "C3" ) ){
						C3 = number;
					}
					else if ( name.equals( "C4" ) ){
						C4 = number;
					}
					else if ( name.equals( "C5" ) ){
						C5 = number;
					}
					else if ( name.equals( "C6" ) ){
						C6 = number;
					}
					else if ( name.equals( "H1" ) ){
						H1 = number;
					}
					else if ( name.equals( "H2" ) ){
						H2 = number;
					}
					else if ( name.equals( "H3" ) ){
						H3 = number;
					}
					else if ( name.equals( "H4" ) ){
						H4 = number;
					}
					else if ( name.equals( "H6" ) ){
						H6 = number;
					}
					else if ( name.equals( "H61" ) ){
						H61 = number;
					}
					else if ( name.equals( "H61" ) ){
						H62 = number;
					}
					omegaArray[ omegaArrayCounter++ ] = number;
				}
				int connectTo = C4;
				int connect2 = C3;
				int become = O3;
				String becomeString = "O3";
				String removeH = "HO3";
				if ( lastResidueNumber > -1 ){
					switch( connectivityChoice.getSelectedIndex() ){
					case 0:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C1" );
						connectTo = C1;
						connect2 = O;
						become = O1;
						becomeString = "O1";
						removeH = "HO1";
						capped = true;
						break;
					case 1:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C2" );
						connectTo = C2;
						connect2 = C1;
						become = O2;
						becomeString = "O2";
						removeH = "HO2";
						break;
					case 2:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C3" );
						connectTo = C3;
						connect2 = C2;
						become = O3;
						becomeString = "O3";
						removeH = "HO3";
						break;
					case 3:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C4" );
						connectTo = C4;
						connect2 = C3;
						become = O4;
						becomeString = "O4";
						removeH = "HO4";
						break;
					case 4:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C5" );
						connectTo = C5;
						connect2 = C4;
						become = O5;
						becomeString = "O5";
						removeH = "H5";
						break;
					case 5:
						m.connectResidues( lastResidueNumber, "O1", 
								newResidue.totalResidueNumber, "C6" );
						connectTo = C6;
						connect2 = C5;
						become = O6;
						becomeString = "O6";
						removeH = "H6";
					}
					lastO1 = m.getMoleculeAtomNumber( "O1", 
							newResidue.totalResidueNumber - 1 );
					lastC1 = m.getMoleculeAtomNumber( "C1", 
							newResidue.totalResidueNumber - 1 );
					lastO = m.getMoleculeAtomNumber( "O", 
							newResidue.totalResidueNumber - 1 );
					m.setBondDistance( lastO1, become, 0.0, 
							omegaArray, newResidue.numberOfAtoms );
					m.setAngle( lastC1, lastO1, connectTo, 109.5, 
							omegaArray, newResidue.numberOfAtoms ); 
					m.setTorsion( lastO, lastC1, lastO1, connectTo, lastPhi, 
							omegaArray, newResidue.numberOfAtoms );  
					m.setTorsion( lastC1, lastO1, connectTo, connect2, psi, 
							omegaArray, newResidue.numberOfAtoms );
					if ( connectTo == C6 ){
						m.setTorsion( lastO1, connectTo, connect2, C4, 
								omega, omegaArray, omegaArrayCounter );
						fixH6BondAngles( lastResidueNumber, 
								lastResidueNumber + 1 );
					}
					m.deleteAtom( removeH, newResidue.totalResidueNumber );
					m.deleteAtom( becomeString, newResidue.totalResidueNumber );
					m.deleteAtom( "HO1", newResidue.totalResidueNumber - 1 );
				}
				lastResidueNumber = newResidue.totalResidueNumber;
				lastOmega = omega;
				lastPhi = phi;
				lastPsi = psi;
				c.displayMolecule( m );
			}
			return true;
		}
		return false;
	}

	public void fixH5BondAngles( int previousResidueNumber, int currentResidueNumber ){
		Residue lastResidue = m.residue[ previousResidueNumber ];
		Residue currentResidue = m.residue[ currentResidueNumber ];
		ErrorDialog error;
		Atom aH51 = null, aH52 = null, aLastO1 = null, aC4 = null, aC5 = null;
		Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
		for( int i = 0; i < lastResidue.numberOfAtoms; i++ ){
			if ( lastResidue.atom[ i ].name.equals( "O1" ) )
				aLastO1 = lastResidue.atom[ i ];
		}
		for( int i = 0; i < currentResidue.numberOfAtoms; i++ ){
			if ( currentResidue.atom[ i ].name.equals( "H51" ) )
				aH51 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "H52" ) )
				aH52 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "C4" ) )
				aC4 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "C5" ) )
				aC5 = currentResidue.atom[ i ];
		}
		if ( ( aLastO1 == null ) || ( aH51 == null ) || ( aH52 == null ) || ( aC4 == null ) ||
				( aC5 == null ) ){
			error = new ErrorDialog( chemStructFrame, "Error fixing H5(1/2) bond angles.",
					"Internal error." );
			error.show();
			return;
		}
		m.resolveCoordinates( aLastO1.moleculeAtomNumber );
		m.resolveCoordinates( aC5.moleculeAtomNumber );
		pseudo.coord[ 0 ] = (float)( ( aLastO1.coord[ 0 ] + aC4.coord[ 0 ] ) / 2.0 );
		pseudo.coord[ 1 ] = (float)( ( aLastO1.coord[ 1 ] + aC4.coord[ 1 ] ) / 2.0 );
		pseudo.coord[ 2 ] = (float)( ( aLastO1.coord[ 2 ] + aC4.coord[ 2 ] ) / 2.0 );
		m.addAtom( aC4.totalResidueNumber, pseudo );
		aH51.setAngle( m, pseudo, aC5, 125.25 );
		aH51.setTorsion( m, aLastO1, pseudo, aC5, 90.0 );
		aH52.setAngle( m, pseudo, aC5, 125.25 );
		aH52.setTorsion( m, aLastO1, pseudo, aC5, -90.0 );
		m.deleteAtom( pseudo.moleculeAtomNumber );	
	}

	public void fixH6BondAngles( int previousResidueNumber, int currentResidueNumber ){
		Residue lastResidue = m.residue[ previousResidueNumber ];
		Residue currentResidue = m.residue[ currentResidueNumber ];
		ErrorDialog error;
		Atom aH61 = null, aH62 = null, aLastO1 = null, aC6 = null, aC5 = null;
		Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
		for( int i = 0; i < lastResidue.numberOfAtoms; i++ ){
			if ( lastResidue.atom[ i ].name.equals( "O1" ) )
				aLastO1 = lastResidue.atom[ i ];
		}
		for( int i = 0; i < currentResidue.numberOfAtoms; i++ ){
			if ( currentResidue.atom[ i ].name.equals( "H61" ) )
				aH61 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "H62" ) )
				aH62 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "C6" ) )
				aC6 = currentResidue.atom[ i ];
			else if ( currentResidue.atom[ i ].name.equals( "C5" ) )
				aC5 = currentResidue.atom[ i ];
		}
		if ( ( aLastO1 == null ) || ( aH61 == null ) || ( aH62 == null ) || ( aC6 == null ) ||
				( aC5 == null ) ){
			error = new ErrorDialog( chemStructFrame, "Error fixing H6(1/2) bond angles.",
					"Internal error." );
			error.show();
			return;
		}
		m.resolveCoordinates( aLastO1.moleculeAtomNumber );
		m.resolveCoordinates( aC5.moleculeAtomNumber );
		pseudo.coord[ 0 ] = (float)( ( aLastO1.coord[ 0 ] + aC5.coord[ 0 ] ) / 2.0 );
		pseudo.coord[ 1 ] = (float)( ( aLastO1.coord[ 1 ] + aC5.coord[ 1 ] ) / 2.0 );
		pseudo.coord[ 2 ] = (float)( ( aLastO1.coord[ 2 ] + aC5.coord[ 2 ] ) / 2.0 );
		m.addAtom( aC6.totalResidueNumber, pseudo );
		aH61.setAngle( m, pseudo, aC6, 125.25 );
		aH61.setTorsion( m, aLastO1, pseudo, aC6, 90.0 );
		aH62.setAngle( m, pseudo, aC6, 125.25 );
		aH62.setTorsion( m, aLastO1, pseudo, aC6, -90.0 );
		m.deleteAtom( pseudo.moleculeAtomNumber );	
	}

	public boolean getTorsionAngles(){
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
		return( true );
	}


}

