package com.goosebumpanalytics.biomer;

import java.util.*;

public class Molecule{

	/** all adding and subtracting of strands, residues, and atoms are "top down".
	 * That is, one should add an atom to a molecule-strand-residue, or the like.
	 * So, adding a strand, residue, or atom, should be operated on a MOLECULE,
	 * which, in turn will propagate down and update the other classes. E.g.,
	 * if I want to add an atom, I call m.addAtom( stuff ), which will then 
	 * update the local arrays, then call strand.addAtom() which will update
	 * the strand info, then call residue.addAtom() which will update the
	 * residue info.
	 */

	String	name;
	int	numberOfStrands;
	int	numberOfResidues;
	int	numberOfAtoms;
	int	numberOfAtomsx3;
	Strand	strand[];
	Residue	residue[];
	Atom	atom[];
	float	actualCoordinates[];
	int	screenCoordinates[];
	int	connected[][];
	int	connected12[];
	int	connected13[];
	int	connected14[];
	int	nonBonded[];
	int	numberOf12Connections = 0;
	int	numberOf13Connections = 0;
	int	numberOf14Connections = 0;
	int	numberNonBonded = 0;
	int 	bonds;
	boolean	bondedMatrix[][];
	boolean inStack[], done;
	boolean	typeSet = false;
	boolean saved = true;
	double	gradient[];
	Matrix3D mat;

	public Molecule(){
		numberOfStrands = 0;
		numberOfResidues = 0;
		numberOfAtoms = 0;
		numberOfAtomsx3 = 0;
		strand = new Strand[ 1 ];
		residue = new Residue[ 1 ];
		residue[ 0 ] = new Residue();
		strand[ 0 ] = new Strand( residue[ 0 ] );
		atom = new Atom[ 1 ];
		actualCoordinates = new float[ 1 ];
		screenCoordinates = new int[ 1 ];
		name = "Untitled";
		mat = new Matrix3D();
	}

	public String toString(){
		System.out.println("name = "+name );
		System.out.println("numberOfStrands = "+numberOfStrands );
		for ( int i = 0; i < numberOfStrands; i++ )
			System.out.println( "\t" + i + ": " + strand[ i ].name );	
		System.out.println("numberOfResidues = "+numberOfResidues );
		for ( int i = 0; i < numberOfResidues; i++ )
			System.out.println( "\t" + i + ": " + residue[ i ].name );	
		System.out.println("numberOfAtoms = "+numberOfAtoms );
		for( int i = 0; i < numberOfAtoms; i++ ){
			System.out.print( "("+i+")"+atom[i].name + " : " +
					actualCoordinates[ i*3 ] + ", " + 
					actualCoordinates[ i*3+1 ] + ", " + 
					actualCoordinates[ i*3+2 ] );
			System.out.print( " screen: " +
					screenCoordinates[ i*3 ] + ", " + 
					screenCoordinates[ i*3+1 ] + ", " + 
					screenCoordinates[ i*3+2 ] );
			System.out.print( "; bonded to: " );
			for ( int j = 0; j < atom[ i ].numberOfBonds; j++ )
				System.out.print( atom[ i ].bond[ j ] + ", " );
			System.out.println();
		}
		return("Dumped molecule: "+name );
	}

	/** adds a new strand with name "strandName" to molecule and updates array. 
	 *  The strand number is just the next number in the strand array
	 */	
	public int addStrand( String strandName ){
		typeSet = false;
		Strand largerStrandArray[] = new Strand[ numberOfStrands + 1 ];
		System.arraycopy( strand, 0, largerStrandArray, 0, strand.length );
		strand = largerStrandArray;
		strand[ numberOfStrands ] = new Strand( strandName );
		strand[ numberOfStrands ].number = numberOfStrands;
		return( numberOfStrands++ );
	}

	/** adds strand ( above ) - if no name, call it "strandnum" */
	public int addStrand(){
		return( addStrand( String.valueOf( numberOfStrands ) ) );
	}

	/** add residue to strand and call method ( below ) to update array */
	public int addResidue( int strandNumber, Residue newResidue ){
		if ( strandNumber >= numberOfStrands ) 
			addStrand();
		strand[ strandNumber ].addResidue( newResidue );
		return( addResidue( newResidue ) );
	}

	/** internal method which adds a residue and all atoms to the molecule */
	private int addResidue( Residue newResidue ){
		typeSet = false;
		int newAtomNumber = numberOfAtoms + newResidue.numberOfAtoms;
		int newAtomNumberx3 = newAtomNumber * 3;

		/* handle residue stuff */
		Residue largerResidueArray[] = new Residue[ numberOfResidues + 1 ];
		System.arraycopy( residue, 0, largerResidueArray, 0, residue.length );
		newResidue.totalResidueNumber = numberOfResidues;
		largerResidueArray[ numberOfResidues ] = newResidue;
		residue = largerResidueArray;

		/* handle atom stuff */
		Atom largerAtomArray[] = new Atom[ newAtomNumber ];
		System.arraycopy( atom, 0, largerAtomArray, 0, atom.length );
		atom = largerAtomArray;

		/* handle coordinate stuff ( atom positions ) */
		float largerCoordinateArray[] = new float[ newAtomNumberx3 ];	
		System.arraycopy( actualCoordinates, 0, largerCoordinateArray, 0,
				actualCoordinates.length );
		actualCoordinates = largerCoordinateArray;

		/* handle screen coordinate stuff */
		int largerScreenCoordinateArray[] = new int[ newAtomNumberx3 ];
		System.arraycopy( screenCoordinates, 0, largerScreenCoordinateArray,
				0, screenCoordinates.length );
		screenCoordinates = largerScreenCoordinateArray;

		for ( int i = numberOfAtoms, j = 0, ix3=0, jx3 = 0; i < newAtomNumber; i++ ){
			atom[ i ] = newResidue.atom[ j ];	
			atom[ i ].strandNumber = newResidue.strandNumber;
			atom[ i ].moleculeAtomNumber = i;
			atom[ i ].totalResidueNumber = numberOfResidues;
			for ( int k = 0; k < atom[ i ].numberOfBonds; k++ )
				atom[ i ].bond[ k ] += numberOfAtoms;
			ix3 = i * 3;
			jx3 = j * 3;
			actualCoordinates[ ix3 ] = atom[ i ].coord[ 0 ] = 
					newResidue.actualCoordinates[ jx3 ];
			actualCoordinates[ ix3+1 ] = atom[ i ].coord[ 1 ] = 
					newResidue.actualCoordinates[ jx3+1 ];
			actualCoordinates[ ix3+2 ] = atom[ i ].coord[ 2 ] = 
					newResidue.actualCoordinates[ jx3+2 ];
			j++;
		}
		numberOfAtoms = newAtomNumber;
		numberOfAtomsx3 = numberOfAtoms * 3;
		return( numberOfResidues++ );
	}

	/** adds newAtom to specified strand and residue */
	public int addAtom( int strandNumber, int strandResidueNumber, Atom newAtom ){
		/* update totalResidueNumber field */
		newAtom.totalResidueNumber = 
				strand[ strandNumber ].residue[ strandResidueNumber ].totalResidueNumber;
		/* strand.addAtom() updates strandNumber and strandResidueNumber fields */
		strand[ strandNumber ].addAtom( strandResidueNumber, newAtom );
		return( addAtom( newAtom ) );
	}

	/** adds newAtom to specified  residue */
	public int addAtom( int totalResidueNumber, Atom newAtom ){
		/* update totalResidueNumber field */
		newAtom.totalResidueNumber = totalResidueNumber;
		/* strand.addAtom() updates strandNumber and strandResidueNumber fields */
		strand[ residue[ totalResidueNumber ].strandNumber ].addAtom( 
				residue[ totalResidueNumber ].strandResidueNumber, newAtom );
		return( addAtom( newAtom ) );
	}

	/** internal method to add newAtom and update arrays */
	private int addAtom( Atom newAtom ){
		int newAtomNumber = numberOfAtoms + 1;
		int newAtomNumberx3 = newAtomNumber * 3;
		typeSet = false;

		Atom largerAtomArray[] = new Atom[ newAtomNumber ];
		System.arraycopy( atom, 0, largerAtomArray, 0, atom.length );
		largerAtomArray[ numberOfAtoms ] = newAtom;
		atom = largerAtomArray;

		/* handle coordinate stuff ( atom positions ) */
		float largerCoordinateArray[] = new float[ newAtomNumberx3 ];	
		System.arraycopy( actualCoordinates, 0, largerCoordinateArray, 0,
				actualCoordinates.length );
		actualCoordinates = largerCoordinateArray;

		/* handle screen coordinate stuff */
		int largerScreenCoordinateArray[] = new int[ newAtomNumberx3 ];
		System.arraycopy( screenCoordinates, 0, largerScreenCoordinateArray,
				0, screenCoordinates.length );
		screenCoordinates = largerScreenCoordinateArray;

		//		for ( int k = 0; k < newAtom.numberOfBonds; k++ )
		//			newAtom.bond[ k ] += numberOfAtoms;
		actualCoordinates[ newAtomNumberx3 - 3 ] = newAtom.coord[ 0 ];
		actualCoordinates[ newAtomNumberx3 - 2 ] = newAtom.coord[ 1 ];
		actualCoordinates[ newAtomNumberx3 - 1 ] = newAtom.coord[ 2 ];

		newAtom.moleculeAtomNumber = numberOfAtoms;
		numberOfAtomsx3 = ( numberOfAtoms + 1 ) * 3;
		return( numberOfAtoms++ );
	}

	/** deletes specified strand and all residue, atom information in it */
	public int deleteStrand( int strandNumber ){
		typeSet = false;
		int newAtomNumber = numberOfAtoms - strand[ strandNumber ].numberOfAtoms;
		int newAtomNumberx3 = newAtomNumber * 3;
		int newResidueNumber = numberOfResidues - strand[ strandNumber ].numberOfResidues;

		for( int i = strandNumber; i < numberOfStrands; i++ )
			strand[ i ].number -= 1;

		Atom smallerAtomArray[] = new Atom[ newAtomNumber ];
		int smallerScreenCoordinateArray[] = new int[ newAtomNumberx3 ];
		float smallerActualCoordinateArray[] = new float[ newAtomNumberx3 ];
		Residue smallerResidueArray[] = new Residue[ newResidueNumber ];
		Strand smallerStrandArray[] = new Strand[ numberOfStrands - 1 ];

		/* delete all strand atoms and atom info from Molecule arrays */
		for ( int i = 0, j = 0, jx3 = 0, ix3 = 0; i < numberOfAtoms; i++ ){
			if ( ! ( atom[ i ].strandNumber == strandNumber ) ){
				ix3 = i * 3;
				jx3 = j * 3;
				smallerActualCoordinateArray[ jx3 ] = actualCoordinates[ ix3 ];
				smallerActualCoordinateArray[ jx3+1 ] = actualCoordinates[ ix3+1 ];
				smallerActualCoordinateArray[ jx3+2 ] = actualCoordinates[ ix3+2 ];
				smallerScreenCoordinateArray[ jx3 ] = screenCoordinates[ ix3 ];
				smallerScreenCoordinateArray[ jx3+1 ] = screenCoordinates[ ix3+1 ];
				smallerScreenCoordinateArray[ jx3+2 ] = screenCoordinates[ ix3+2 ];
				smallerAtomArray[ j++ ] = atom[ i ];
			}
		}
		atom = smallerAtomArray;
		actualCoordinates = smallerActualCoordinateArray;
		screenCoordinates = smallerScreenCoordinateArray;

		/* update residue array */
		for ( int i = 0, j = 0; i < numberOfResidues; i++ ){
			if ( ! ( residue[ i ].strandNumber == strandNumber ) )
				smallerResidueArray[ j++ ] = residue[ i ];
		}
		residue = smallerResidueArray;

		numberOfAtoms = newAtomNumber;
		numberOfAtomsx3 = numberOfAtoms * 3;
		numberOfResidues = newResidueNumber;
		System.arraycopy( strand, 0, smallerStrandArray, 0, strandNumber );
		if ( strandNumber < numberOfStrands - 1 )
			System.arraycopy( strand, strandNumber + 1, smallerStrandArray,
					strandNumber, strand.length - strandNumber - 1 );
		return( --numberOfStrands );
	}

	/** deletes specified residue and all atom information in it */
	public int deleteResidue( int totalResidueNumber ){
		typeSet = false;
		int newAtomNumber = numberOfAtoms - residue[ totalResidueNumber ].numberOfAtoms;
		int newAtomNumberx3 = newAtomNumber * 3;

		Atom smallerAtomArray[] = new Atom[ newAtomNumber ];
		int smallerScreenCoordinateArray[] = new int[ newAtomNumberx3 ];
		float smallerActualCoordinateArray[] = new float[ newAtomNumberx3 ];
		Residue smallerResidueArray[] = new Residue[ numberOfResidues - 1 ];

		/* delete all residue atoms and atom info from Molecule arrays */
		for ( int i = 0, j = 0, jx3 = 0, ix3 = 0; i < numberOfAtoms; i++ ){
			if ( ! ( atom[ i ].totalResidueNumber == totalResidueNumber ) ){
				ix3 = i * 3;
				jx3 = j * 3;
				smallerActualCoordinateArray[ jx3 ] = actualCoordinates[ ix3 ];
				smallerActualCoordinateArray[ jx3+1 ] = actualCoordinates[ ix3+1 ];
				smallerActualCoordinateArray[ jx3+2 ] = actualCoordinates[ ix3+2 ];
				smallerScreenCoordinateArray[ jx3 ] = screenCoordinates[ ix3 ];
				smallerScreenCoordinateArray[ jx3+1 ] = screenCoordinates[ ix3+1 ];
				smallerScreenCoordinateArray[ jx3+2 ] = screenCoordinates[ ix3+2 ];
				smallerAtomArray[ j++ ] = atom[ i ];
			}
		}
		atom = smallerAtomArray;
		actualCoordinates = smallerActualCoordinateArray;
		screenCoordinates = smallerScreenCoordinateArray;

		numberOfAtoms = newAtomNumber;
		numberOfAtomsx3 = numberOfAtoms * 3;

		strand[ residue[ totalResidueNumber ].strandNumber ].deleteResidue(
				residue[ totalResidueNumber ].strandResidueNumber );

		System.arraycopy( residue, 0, smallerResidueArray, 0, totalResidueNumber );
		if ( totalResidueNumber < numberOfResidues - 1 )
			System.arraycopy( residue, totalResidueNumber + 1, smallerResidueArray, 
					totalResidueNumber, residue.length - totalResidueNumber - 1 );
		return( --numberOfResidues );
	}

	/** calls deleteAtom( int ) below */
	public int deleteAtom( String atomName, int totalResidueNumber ){
		for ( int i = 0; i < residue[ totalResidueNumber ].numberOfAtoms; i++ )
			if ( residue[ totalResidueNumber ].atom[ i ].name.equals( atomName ) )
				return( deleteAtom( residue[ totalResidueNumber ].atom[ 
				                                                       i ].moleculeAtomNumber ) );
		return( 0 );
	}

	/** calls deleteAtom( int ) below */
	public int deleteAtom( int strandNumber, int strandResidueNumber, int residueAtomNumber ){
		return( deleteAtom( strand[ strandNumber ].residue[ strandResidueNumber ].atom[
		                                                                               residueAtomNumber ].moleculeAtomNumber ) );
	}

	/** calls deleteAtom( int ) below */
	public int deleteAtom( int totalResidueNumber, int residueAtomNumber ){
		return( deleteAtom( residue[ totalResidueNumber ].strandNumber,
				residue[ totalResidueNumber ].strandResidueNumber, residueAtomNumber ) );
	}

	/** deletes atom from atom array and updates numberOfAtoms */
	public int deleteAtom( int totalAtomNumber ){
		typeSet = false;
		Atom atomToDelete = atom[ totalAtomNumber ];

		/* delete atom from strand ( which deletes atom from residue ) */
		strand[ atomToDelete.strandNumber ].deleteAtom( atomToDelete.strandResidueNumber,
				atomToDelete.residueAtomNumber );

		int newAtomNumber = numberOfAtoms - 1;
		int newAtomNumberx3 = newAtomNumber * 3;
		int totalAtomNumberx3 = totalAtomNumber * 3;

		/* remove references from other atoms */
		for ( int i = 0; i < numberOfAtoms; i++ ){
			for( int j = 0; j < atom[ i ].numberOfBonds; j++ ){
				if ( atom[ i ].bond[ j ] == totalAtomNumber ){
					atom[ i ].deleteBond( j );
					j--;
				}
				else if ( atom[ i ].bond[ j ] > totalAtomNumber ){
					atom[ i ].bond[ j ] -= 1;	
				}
			}
			if ( i > totalAtomNumber )
				atom[ i ].moleculeAtomNumber -= 1;
		}

		/* update atom array */
		Atom smallerAtomArray[] = new Atom[ numberOfAtoms - 1 ];
		System.arraycopy( atom, 0, smallerAtomArray, 0, totalAtomNumber );
		if ( totalAtomNumber < newAtomNumber )
			System.arraycopy( atom, totalAtomNumber + 1, smallerAtomArray, 
					totalAtomNumber, atom.length - totalAtomNumber - 1 );
		atom = smallerAtomArray;

		/* handle coordinate stuff ( atom positions ) */
		float smallerCoordinateArray[] = new float[ newAtomNumberx3 ];
		System.arraycopy( actualCoordinates, 0, smallerCoordinateArray, 0,
				totalAtomNumberx3 );
		if ( totalAtomNumber < newAtomNumber )
			System.arraycopy( actualCoordinates, totalAtomNumberx3+3, 
					smallerCoordinateArray, totalAtomNumberx3, newAtomNumberx3 - 
					totalAtomNumberx3 ); 
		actualCoordinates = smallerCoordinateArray;
		/* handle screen coordinate stuff */
		int smallerScreenCoordinateArray[] = new int[ newAtomNumberx3 ];
		System.arraycopy( screenCoordinates, 0, smallerScreenCoordinateArray,
				0, totalAtomNumberx3 );
		if ( totalAtomNumber < newAtomNumber )
			System.arraycopy( screenCoordinates, totalAtomNumberx3+3, 
					smallerScreenCoordinateArray, 0, newAtomNumberx3 - totalAtomNumberx3);
		screenCoordinates = smallerScreenCoordinateArray;

		numberOfAtoms = newAtomNumber;	
		numberOfAtomsx3 = numberOfAtoms * 3;
		return( numberOfAtoms );
	}

	/** since we are doing rigid body transformations, we keep the actualCoordinates
	 *  array updated, and do NOT update the atom's x, y, and z fields ( done for
	 *  performace reasons.  Eventually, we will need these fields to be up to 
	 *  date ( writing a PDB file, etc. ) and that is when this method is called.
	 */
	public void resolveCoordinates(){
		int ix3 = -3;
		for ( int i = 0; i < numberOfAtoms; i++ ){
			ix3 += 3;
			atom[ i ].coord[ 0 ] = actualCoordinates[ ix3 ];
			atom[ i ].coord[ 1 ] = actualCoordinates[ ix3 + 1 ];
			atom[ i ].coord[ 2 ] = actualCoordinates[ ix3 + 2 ];
		}
	}

	/** since we are doing rigid body transformations, we keep the actualCoordinates
	 *  array updated, and do NOT update the atom's x, y, and z fields ( done for
	 *  performace reasons.  Eventually, we will need these fields to be up to 
	 *  date ( writing a PDB file, etc. ) and that is when this method is called.
	 */
	public void resolveCoordinates( int atomNumber ){
		int atomNumberx3 = atomNumber * 3;
		atom[ atomNumber ].coord[ 0 ] = actualCoordinates[ atomNumberx3 ];
		atom[ atomNumber ].coord[ 1 ] = actualCoordinates[ atomNumberx3 + 1 ];
		atom[ atomNumber ].coord[ 2 ] = actualCoordinates[ atomNumberx3 + 2 ];
	}

	/** update the Molecule's atom array after we change the position of a single atom */
	public void updateCoordinates( Atom updateAtom ){
		int atomNumber = updateAtom.moleculeAtomNumber;
		int atomNumberx3 = atomNumber * 3;
		actualCoordinates[ atomNumberx3 ] = updateAtom.coord[ 0 ];	
		actualCoordinates[ atomNumberx3 + 1 ] = updateAtom.coord[ 1 ];	
		actualCoordinates[ atomNumberx3 + 2 ] = updateAtom.coord[ 2 ];	
	}

	/** update the Molecule's atom array after we change the position of a single atom */
	public void updateCoordinates( int atomNumber ){
		Atom updateAtom = atom[ atomNumber ];
		int atomNumberx3 = atomNumber * 3;
		actualCoordinates[ atomNumberx3 ] = updateAtom.coord[ 0 ];	
		actualCoordinates[ atomNumberx3 + 1 ] = updateAtom.coord[ 1 ];	
		actualCoordinates[ atomNumberx3 + 2 ] = updateAtom.coord[ 2 ];	
	}

	public double mass(){
		double totalMass = 0;
		Atom thisAtom;
		for ( int i = 0; i < numberOfAtoms; i++ ){
			thisAtom = atom[ i ];
			if ( thisAtom.selected )
				totalMass += thisAtom.mass;
		}
		return( totalMass );
	}

	public double charge( int natoms[] ){
		double totalCharge = 0;
		int atomCounter = 0;
		Atom thisAtom;
		for ( int i = 0; i < numberOfAtoms; i++ ){
			thisAtom = atom[ i ];
			if ( thisAtom.selected ){
				totalCharge += thisAtom.charge;
				atomCounter++;
			}
		}
		natoms[ 0 ] = atomCounter;
		return( totalCharge );
	}

	/** add a bond from atomA of residueA to atomB of residueB, where residueA is an
	 * integer indicating the totalResidueNumber of the first residue, atomA is the
	 * name of the atom in the residue to be bonded to the corresponding B components 
	 */
	public void connectResidues( int residueA, String atomA, int residueB, String atomB ){
		for( int i = 0; i < residue[ residueA ].numberOfAtoms; i++ ){
			if ( residue[ residueA ].atom[ i ].name.equals( atomA ) ){
				for( int j = 0; j < residue[ residueB ].numberOfAtoms; j++ ){
					if ( residue[ residueB ].atom[ j ].name.equals( atomB ) ){
						residue[ residueA ].atom[ i ].addBond(
								residue[ residueB ].atom[ j ].moleculeAtomNumber );
					}
				}
			}
		}
	}

	public void addFragment( int bondAtom, int fragAtom1, int fragAtom2, int atomList[], 
			int numberOfFragmentAtoms ){
		resolveCoordinates( bondAtom );
		resolveCoordinates( fragAtom1 );
		if ( fragAtom2 != -1 ){
			resolveCoordinates( fragAtom2 );
		}
		Atom f1 = atom[ fragAtom1 ];
		connected = new int[ numberOfAtoms ][ 1 ];
		connected12 = new int[ 1 ];
		numberOf12Connections = 0;
		nonBonded = new int[ 200 ];
		bondedMatrix = new boolean[ numberOfAtoms ][ numberOfAtoms ];
		numberNonBonded = 0;
		for ( int i = 0; i < numberOfAtoms; i++ ){
			for ( int j = i + 1; j < numberOfAtoms; j++ ){
				if ( isConnected( i, j ) ){
					connect12( i, j );
					connect( i, j );
					connect( j, i );
				}
			}
		}
		int numberOfBondConnections = connected[ bondAtom ][ 0 ];
		if ( numberOfBondConnections == 0 )
			return;	
		else if ( numberOfBondConnections == 1 ){
			int atom1 = connected[ bondAtom ][ 1 ];
			setAngle( atom1, bondAtom, fragAtom1, 109.5, atomList, 
					numberOfFragmentAtoms );
			if ( fragAtom2 != -1 ){
				setTorsion( atom1, bondAtom, fragAtom1, fragAtom2, 
						180, atomList, numberOfFragmentAtoms );
			}
			return;
		}
		else if ( numberOfBondConnections == 2 ){
			int connection1 = connected[ bondAtom ][ 1 ];
			Atom c1 = atom[ connection1 ];
			int connection2 = connected[ bondAtom ][ 2 ];
			Atom c2 = atom[ connection2 ];	
			Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
			addAtom( atom[ bondAtom ].totalResidueNumber, pseudo );
			int pseudoNumber = pseudo.moleculeAtomNumber;
			resolveCoordinates( connection1 );
			resolveCoordinates( connection2 );
			resolveCoordinates( bondAtom );             // ? may be bad
			double angle = c2.angle( c1, atom[ bondAtom ] );
			if ( Math.abs( angle - 180 ) < 1.0e-4 ){
				setAngle( connection1, bondAtom, fragAtom1, 90.0, atomList, 
						numberOfFragmentAtoms );
				pseudo.coord[ 0 ] = (float)(
						( c1.coord[ 0 ] + f1.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)(
						( c1.coord[ 1 ] + f1.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)(
						( c1.coord[ 2 ] + f1.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				setTorsion( pseudoNumber, connection1, bondAtom,
						fragAtom1, 90, atomList, numberOfFragmentAtoms );
			}
			else{
				pseudo.coord[ 0 ] = (float)(
						( c1.coord[ 0 ] + c2.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)(
						( c1.coord[ 1 ] + c2.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)(
						( c1.coord[ 2 ] + c2.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				setAngle( pseudoNumber, bondAtom, fragAtom1, 125.25,
						atomList, numberOfFragmentAtoms );
				setTorsion( connection1, pseudoNumber, bondAtom,
						fragAtom1, 90, atomList, numberOfFragmentAtoms );
			}
			if ( fragAtom2 != -1 ){
				setTorsion( connection1, bondAtom, fragAtom1, fragAtom2, 
						180, atomList, numberOfFragmentAtoms );
			}
			deleteAtom( pseudoNumber );
		}
		else if ( numberOfBondConnections == 3 ){   // Carbon
			int connection1 = connected[ bondAtom ][ 1 ];
			int connection2 = connected[ bondAtom ][ 2 ];
			int connection3 = connected[ bondAtom ][ 3 ];
			resolveCoordinates( connection1 );
			resolveCoordinates( connection2 );
			resolveCoordinates( connection3 );
			resolveCoordinates( bondAtom );
			Atom c1 = atom[ connection1 ];
			Atom c2 = atom[ connection2 ];
			Atom c3 = atom[ connection3 ];
			setAngle( connection1, bondAtom, fragAtom1, 90.0, atomList, 
					numberOfFragmentAtoms );
			Atom a = c1;
			Atom b = c2;
			Atom c = c3;
			int aNumber = a.moleculeAtomNumber;
			Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
			addAtom( atom[ bondAtom ].totalResidueNumber, pseudo );
			int pseudoNumber = pseudo.moleculeAtomNumber;
			double angleA = c2.angle( c1, atom[ bondAtom ] );
			double angleB = c3.angle( c1, atom[ bondAtom ] );
			double angleC = c3.angle( c2, atom[ bondAtom ] );
			double absAngleA = Math.abs( angleA );
			double absAngleB = Math.abs( angleB );
			double absAngleC = Math.abs( angleC );
			if ( ( 180 - absAngleA > 1.0e-5 ) && ( absAngleA > 1.0e-5 ) ){
				a = c2;
				b = c1;
				c = c3;
			}
			else if ( ( 180 - absAngleB > 1.0e-5 ) && ( absAngleB > 1.0e-5 ) ){
				a = c3;
				b = c1;
				c = c2;
			}
			else if ( ( 180 - absAngleC > 1.0e-5 ) && ( absAngleC > 1.0e-5 ) ){
				a = c3;
				b = c2;
				c = c1;
			}
			else{
				deleteAtom( pseudoNumber );
				return;
			}
			if ( ( absAngleA < absAngleB ) && ( absAngleA < absAngleC ) ){
				a = c1;
				b = c2;
				c = c3;
			}
			else if ( ( absAngleB < absAngleA ) && ( absAngleB < absAngleC ) ){
				a = c1;
				b = c3;
				c = c2;
			}
			else if ( ( absAngleC < absAngleA ) && ( absAngleC < absAngleB ) ){
				a = c3;
				b = c2;
				c = c1;
			}
			aNumber = a.moleculeAtomNumber;
			pseudo.coord[ 0 ] = (float)(
					( a.coord[ 0 ] + b.coord[ 0 ] ) / 2.0 );
			pseudo.coord[ 1 ] = (float)(
					( a.coord[ 1 ] + b.coord[ 1 ] ) / 2.0 );
			pseudo.coord[ 2 ] = (float)(
					( a.coord[ 2 ] + b.coord[ 2 ] ) / 2.0 );
			updateCoordinates( pseudoNumber );
			double angleD = c.angle( pseudo, atom[ bondAtom ] );
			double absAngleD = Math.abs( angleD );
			if ( ( 180 - absAngleD > 1 ) && ( absAngleD > 1 ) ){
				setAngle( pseudoNumber, bondAtom, fragAtom1, 125.25,
						atomList, numberOfFragmentAtoms );
				setTorsion( aNumber, pseudoNumber, bondAtom,
						fragAtom1, 90, atomList, numberOfFragmentAtoms );
				if ( fragAtom2 != -1 ){
					setTorsion( connection1, bondAtom, fragAtom1, fragAtom2, 
							180, atomList, numberOfFragmentAtoms );
				}
				deleteAtom( pseudoNumber );
				return;
			}
			else if ( absAngleD < 1 ){
				setAngle( pseudoNumber, bondAtom, fragAtom1, 125.25,
						atomList, numberOfFragmentAtoms );
				if ( fragAtom2 != -1 ){
					setTorsion( connection1, bondAtom, fragAtom1, fragAtom2, 
							180, atomList, numberOfFragmentAtoms );
				}
				deleteAtom( pseudoNumber );
				return;
			}
			pseudo.coord[ 0 ] = (float)(
					( a.coord[ 0 ] + b.coord[ 0 ] + c.coord[ 0 ] ) / 2.0 );
			pseudo.coord[ 1 ] = (float)(
					( a.coord[ 1 ] + b.coord[ 1 ] + c.coord[ 1 ] ) / 2.0 );
			pseudo.coord[ 2 ] = (float)(
					( a.coord[ 2 ] + b.coord[ 2 ] + c.coord[ 2 ] ) / 2.0 );
			updateCoordinates( pseudoNumber );
			setBondDistance( bondAtom, pseudoNumber, 1.5, atomList, 
					numberOfFragmentAtoms );
			if ( fragAtom2 != -1 ){
				setTorsion( connection1, bondAtom, fragAtom1, fragAtom2, 
						180, atomList, numberOfFragmentAtoms );
			}
			deleteAtom( pseudoNumber );
		}
	}

	public void addHydrogens(){
		int numberOfHydrogens = 0;
		establishConnectivity( true );
		int startingAtoms = numberOfAtoms;
		for ( int i = 0; i < startingAtoms; i++ ){
			Atom atomA = atom[ i ];
			switch( atomA.elementNumber ){
			case 6:    // Carbon
				numberOfHydrogens = 4 - connected[ i ][ 0 ];
				break;
			case 7:	   // Nitrogen
				numberOfHydrogens = 3 - connected[ i ][ 0 ];
				break;
			case 8:	   // Oxygen
				numberOfHydrogens = 2 - connected[ i ][ 0 ];
			}
			if ( numberOfHydrogens > 0 )
				addHydrogen( i, numberOfHydrogens );
			numberOfHydrogens = 0;
		}
	}

	public void addHydrogen( int atomNumber, int numberOfHydrogens ){
		int numberOfConnections = connected[ atomNumber ][ 0 ];
		if ( numberOfHydrogens == 4 ){  // this is Carbon - going to be methane
			Atom h1 = new Atom( "H", 0f, 0f, 0f );
			Atom h2 = new Atom( "H", 0f, 0f, 0f );
			Atom h3 = new Atom( "H", 0f, 0f, 0f );
			Atom h4 = new Atom( "H", 0f, 0f, 0f );
			Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
			addAtom( atom[ atomNumber ].totalResidueNumber, h1 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h2 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h3 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h4 );
			addAtom( atom[ atomNumber ].totalResidueNumber, pseudo );
			int h1Number = h1.moleculeAtomNumber;
			int h2Number = h2.moleculeAtomNumber;
			int h3Number = h3.moleculeAtomNumber;
			int h4Number = h4.moleculeAtomNumber;
			int pseudoNumber = pseudo.moleculeAtomNumber;
			atom[ atomNumber ].addBond( h1Number, h2Number, h3Number, h4Number );
			setBondDistance( atomNumber, h1Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h2Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h3Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h4Number, 1.09, null, 0 );
			setAngle( h1Number, atomNumber, h2Number, 109.5, null, 0 );
			pseudo.coord[ 0 ] = (float)( ( h1.coord[ 0 ] + h2.coord[ 0 ] ) / 2.0 );
			pseudo.coord[ 1 ] = (float)( ( h1.coord[ 1 ] + h2.coord[ 1 ] ) / 2.0 );
			pseudo.coord[ 2 ] = (float)( ( h1.coord[ 2 ] + h2.coord[ 2 ] ) / 2.0 );
			updateCoordinates( pseudoNumber );
			setAngle( pseudoNumber, atomNumber, h3Number, 125.25, null, 0 );
			setAngle( pseudoNumber, atomNumber, h4Number, 125.25, null, 0 );
			setTorsion( h1Number, pseudoNumber, atomNumber, h3Number, 90, null, 0 );
			setTorsion( h1Number, pseudoNumber, atomNumber, h4Number, -90, null, 0 );
			deleteAtom( pseudoNumber );
		}
		else if ( numberOfHydrogens == 3 ){  // Carbon or Nitrogen
			Atom h1 = new Atom( "H1", 0f, 0f, 0f );
			Atom h2 = new Atom( "H2", 0f, 0f, 0f );
			Atom h3 = new Atom( "H3", 0f, 0f, 0f );
			Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
			addAtom( atom[ atomNumber ].totalResidueNumber, h1 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h2 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h3 );
			addAtom( atom[ atomNumber ].totalResidueNumber, pseudo );
			int h1Number = h1.moleculeAtomNumber;
			int h2Number = h2.moleculeAtomNumber;
			int h3Number = h3.moleculeAtomNumber;
			int pseudoNumber = pseudo.moleculeAtomNumber;
			atom[ atomNumber ].addBond( h1Number, h2Number, h3Number );
			setBondDistance( atomNumber, h1Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h2Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h3Number, 1.09, null, 0 );
			if ( numberOfConnections == 1 ){	// Carbon
				if ( sp( atomNumber ) != 1 ){
					int connection1 = connected[ atomNumber ][ 1 ];	
					Atom c1 = atom[ connection1 ];
					setAngle( connection1, atomNumber, h1Number, 109.5, 
							null, 0 );
					setAngle( connection1, atomNumber, h2Number, 109.5, 
							null, 0 );
					setAngle( connection1, atomNumber, h3Number, 109.5, 
							null, 0 );
					pseudo.coord[ 0 ] = (float)( 
							( h1.coord[ 0 ] + c1.coord[ 0 ] ) / 2.0 );
					pseudo.coord[ 1 ] = (float)( 
							( h1.coord[ 1 ] + c1.coord[ 1 ] ) / 2.0 );
					pseudo.coord[ 2 ] = (float)( 
							( h1.coord[ 2 ] + c1.coord[ 2 ] ) / 2.0 );
					updateCoordinates( pseudoNumber );
					setTorsion( pseudoNumber, connection1, atomNumber, 
							h2Number, 120, null, 0 );
					setTorsion( pseudoNumber, connection1, atomNumber, 
							h3Number, 240, null, 0 );
				}  // there must be a triple bond
				else{
					int connection1 = connected[ atomNumber ][ 1 ];	
					Atom c1 = atom[ connection1 ];
					h1.setBondDistance( this, c1, atom[ atomNumber ], 1.09 );
					deleteAtom( h2Number );
					deleteAtom( h3.moleculeAtomNumber );
				}
			}
			else if ( numberOfConnections == 0 ){	// Nitrogen
				setAngle( h1Number, atomNumber, h2Number, 109.5, null, 0 );
				setAngle( h1Number, atomNumber, h3Number, 109.5, null, 0 );
				pseudo.coord[ 0 ] = (float)( ( h1.coord[ 0 ] + h2.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)( ( h1.coord[ 1 ] + h2.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)( ( h1.coord[ 2 ] + h2.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				setTorsion( pseudoNumber, h1Number, atomNumber, h3Number, 120, 
						null, 0 );
			}
			deleteAtom( pseudo.moleculeAtomNumber );
		}
		else if ( numberOfHydrogens == 2 ){
			Atom h1 = new Atom( "H1", 0f, 0f, 0f );
			Atom h2 = new Atom( "H2", 0f, 0f, 0f );
			Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
			addAtom( atom[ atomNumber ].totalResidueNumber, h1 );
			addAtom( atom[ atomNumber ].totalResidueNumber, h2 );
			addAtom( atom[ atomNumber ].totalResidueNumber, pseudo );
			int h1Number = h1.moleculeAtomNumber;
			int h2Number = h2.moleculeAtomNumber;
			int pseudoNumber = pseudo.moleculeAtomNumber;
			atom[ atomNumber ].addBond( h1Number, h2Number );
			setBondDistance( atomNumber, h1Number, 1.09, null, 0 );
			setBondDistance( atomNumber, h2Number, 1.09, null, 0 );
			if ( numberOfConnections == 2 ){	// Carbon
				int connection1 = connected[ atomNumber ][ 1 ];	
				Atom c1 = atom[ connection1 ];
				int connection2 = connected[ atomNumber ][ 2 ];	
				Atom c2 = atom[ connection2 ];
				resolveCoordinates( connection1 );
				resolveCoordinates( connection2 );
				resolveCoordinates( atomNumber );
				double angle = c2.angle( c1, atom[ atomNumber ] );
				if ( Math.abs( angle - 180 ) < 1.0e-4 ){
					setAngle( connection1, atomNumber, h1Number, 90.0, null, 0 );
					setAngle( connection1, atomNumber, h2Number, 90.0, null, 0 );
					pseudo.coord[ 0 ] = (float)( 
							( c1.coord[ 0 ] + h1.coord[ 0 ] ) / 2.0 );
					pseudo.coord[ 1 ] = (float)( 
							( c1.coord[ 1 ] + h1.coord[ 1 ] ) / 2.0 );
					pseudo.coord[ 2 ] = (float)( 
							( c1.coord[ 2 ] + h1.coord[ 2 ] ) / 2.0 );
					updateCoordinates( pseudoNumber );
					setTorsion( pseudoNumber, connection1, atomNumber, 
							h1Number, 90, null, 0 );
					setTorsion( pseudoNumber, connection1, atomNumber, 
							h2Number, -90, null, 0 );
				}
				else{
					pseudo.coord[ 0 ] = (float)( 
							( c1.coord[ 0 ] + c2.coord[ 0 ] ) / 2.0 );
					pseudo.coord[ 1 ] = (float)( 
							( c1.coord[ 1 ] + c2.coord[ 1 ] ) / 2.0 );
					pseudo.coord[ 2 ] = (float)( 
							( c1.coord[ 2 ] + c2.coord[ 2 ] ) / 2.0 );
					updateCoordinates( pseudoNumber );
					setAngle( pseudoNumber, atomNumber, h1Number, 125.25, 
							null, 0 );
					setAngle( pseudoNumber, atomNumber, h2Number, 125.25, 
							null, 0 );
					setTorsion( connection1, pseudoNumber, atomNumber, 
							h1Number, 90, null, 0 );
					setTorsion( connection1, pseudoNumber, atomNumber, 
							h2Number, -90, null, 0 );
				}
			}
			else if ( numberOfConnections == 1 ){	// Nitrogen
				int connection1 = connected[ atomNumber ][ 1 ];	
				Atom c1 = atom[ connection1 ];
				setAngle( connection1, atomNumber, h1Number, 109.5, null, 0 );
				setAngle( connection1, atomNumber, h2Number, 109.5, null, 0 );
				pseudo.coord[ 0 ] = (float)( ( h1.coord[ 0 ] + c1.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)( ( h1.coord[ 1 ] + c1.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)( ( h1.coord[ 2 ] + c1.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				setTorsion( pseudoNumber, connection1, atomNumber, h2Number, 120, 
						null, 0 );
			}
			else if ( numberOfConnections == 0 ){	// Oxygen
				setBondDistance( atomNumber, h1Number, .9572, null, 0 );
				setBondDistance( atomNumber, h2Number, .9572, null, 0 );
				setAngle( h1Number, atomNumber, h2Number, 104.5, null, 0 );
			}
			deleteAtom( pseudoNumber );
		}
		else if ( numberOfHydrogens == 1 ){
			int connection1 = connected[ atomNumber ][ 1 ];	
			Atom c1 = atom[ connection1 ];
			Atom h1 = new Atom( "H", 0f, 0f, 0f );
			addAtom( atom[ atomNumber ].totalResidueNumber, h1 );
			int h1Number = h1.moleculeAtomNumber;
			atom[ atomNumber ].addBond( h1Number );
			setBondDistance( atomNumber, h1Number, 1.09, null, 0 );
			resolveCoordinates( connection1 );
			if ( numberOfConnections == 1 ){	// Oxygen
				setAngle( connection1, atomNumber, h1Number, 109.5, null, 0 );
			}
			else if ( numberOfConnections == 2 ){ 	// Nitrogen
				int connection2 = connected[ atomNumber ][ 2 ];
				Atom c2 = atom[ connection2 ];
				Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
				addAtom( atom[ atomNumber ].totalResidueNumber, pseudo );
				int pseudoNumber = pseudo.moleculeAtomNumber;
				resolveCoordinates( connection2 );
				resolveCoordinates( atomNumber );
				double angle = c2.angle( c1, atom[ atomNumber ] );
				if ( Math.abs( angle - 180 ) < 1.0e-4 ){
					setAngle( connection1, atomNumber, h1Number, 90.0, null, 0 );
					pseudo.coord[ 0 ] = (float)( 
							( c1.coord[ 0 ] + h1.coord[ 0 ] ) / 2.0 );
					pseudo.coord[ 1 ] = (float)( 
							( c1.coord[ 1 ] + h1.coord[ 1 ] ) / 2.0 );
					pseudo.coord[ 2 ] = (float)( 
							( c1.coord[ 2 ] + h1.coord[ 2 ] ) / 2.0 );
					updateCoordinates( pseudoNumber );
					setTorsion( pseudoNumber, connection1, atomNumber, 
							h1Number, 90, null, 0 );
				}
				else{
					pseudo.coord[ 0 ] = (float)( 
							( c1.coord[ 0 ] + c2.coord[ 0 ] ) / 2.0 );
					pseudo.coord[ 1 ] = (float)( 
							( c1.coord[ 1 ] + c2.coord[ 1 ] ) / 2.0 );
					pseudo.coord[ 2 ] = (float)( 
							( c1.coord[ 2 ] + c2.coord[ 2 ] ) / 2.0 );
					updateCoordinates( pseudoNumber );
					setAngle( pseudoNumber, atomNumber, h1Number, 125.25, 
							null, 0 );
					setTorsion( connection1, pseudoNumber, atomNumber, 
							h1Number, 90, null, 0 );
				}
				deleteAtom( pseudoNumber );
			}
			else if ( numberOfConnections == 3 ){ 	// Carbon
				setAngle( connection1, atomNumber, h1Number, 90.0, null, 0 );
				int connection2 = connected[ atomNumber ][ 2 ];
				Atom c2 = atom[ connection2 ];
				int connection3 = connected[ atomNumber ][ 3 ];
				Atom c3 = atom[ connection3 ];
				Atom a = c1;
				Atom b = c2;
				Atom c = c3;
				int aNumber = a.moleculeAtomNumber;
				Atom pseudo = new Atom( "pseudo", 0f, 0f, 0f );
				addAtom( atom[ atomNumber ].totalResidueNumber, pseudo );
				int pseudoNumber = pseudo.moleculeAtomNumber;
				resolveCoordinates( connection2 );
				resolveCoordinates( connection3 );
				resolveCoordinates( atomNumber );
				double angleA = c2.angle( c1, atom[ atomNumber ] );
				double angleB = c3.angle( c1, atom[ atomNumber ] );
				double angleC = c3.angle( c2, atom[ atomNumber ] );
				double absAngleA = Math.abs( angleA );
				double absAngleB = Math.abs( angleB );
				double absAngleC = Math.abs( angleC );
				if ( ( 180 - absAngleA > 1.0e-5 ) && ( absAngleA > 1.0e-5 ) ){
					a = c2;
					b = c1;
					c = c3;
				}
				else if ( ( 180 - absAngleB > 1.0e-5 ) && ( absAngleB > 1.0e-5 ) ){
					a = c3;
					b = c1;
					c = c2;
				}
				else if ( ( 180 - absAngleC > 1.0e-5 ) && ( absAngleC > 1.0e-5 ) ){
					a = c3;
					b = c2;
					c = c1;
				}
				else{
					deleteAtom( pseudoNumber );
					return;	
				}
				aNumber = a.moleculeAtomNumber;
				pseudo.coord[ 0 ] = (float)( 
						( a.coord[ 0 ] + b.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)( 
						( a.coord[ 1 ] + b.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)( 
						( a.coord[ 2 ] + b.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				double angleD = c.angle( pseudo, atom[ atomNumber ] );
				double absAngleD = Math.abs( angleD );
				if ( 180 - absAngleD < 1 ){
					setTorsion( aNumber, pseudoNumber, atomNumber, 
							h1Number, 90, null, 0 );
					deleteAtom( pseudoNumber );
					return;
				}
				else if ( absAngleD < 1 ){
					setAngle( pseudoNumber, atomNumber, h1Number, 109.5, 
							null, 0 );
					deleteAtom( pseudoNumber );
					return;
				}
				pseudo.coord[ 0 ] = (float)( 
						( a.coord[ 0 ] + b.coord[ 0 ] + c.coord[ 0 ] ) / 2.0 );
				pseudo.coord[ 1 ] = (float)( 
						( a.coord[ 1 ] + b.coord[ 1 ] + c.coord[ 1 ] ) / 2.0 );
				pseudo.coord[ 2 ] = (float)( 
						( a.coord[ 2 ] + b.coord[ 2 ] + c.coord[ 2 ] ) / 2.0 );
				updateCoordinates( pseudoNumber );
				h1.setBondDistance( this, pseudo, atom[ atomNumber ], -1.09 );
				deleteAtom( pseudoNumber );
			}
		}
	}	

	public void setAngle( int a1num, int a2num, int a3num, double angle, int atomList[],
			int numberOfElements ){
		resolveCoordinates( a1num );  // update a1 coords
		resolveCoordinates( a2num );  // update a2 coords
		resolveCoordinates( a3num );  // update a3 coords
		Atom a1 = atom[ a1num ];  
		Atom a2 = atom[ a2num ];
		Atom a3 = atom[ a3num ];
		Atom pseudoAtom1 = new Atom( "a2", a1.coord[ 0 ] - a2.coord[ 0 ],
				a1.coord[ 1 ] - a2.coord[ 1 ], a1.coord[ 2 ] - a2.coord[ 2 ] );
		double originalAngle = a3.angle( a1, a2 );
		int atomListLength = 0;
		int numberOfCoordinates = 0;
		int atomNumber;
		if ( numberOfElements == 0 ){
			atomListLength = 0;
			numberOfCoordinates = 1;
		}
		else{
			atomListLength = numberOfElements;
			numberOfCoordinates = atomListLength + 1;
		}
		int numberOfCoordinatesx3 = numberOfCoordinates * 3;
		// array of atom numbers to be rotated
		float temporaryCoordinates[] = new float[ numberOfCoordinatesx3 ];
		// store atom 4 as first entry in array
		temporaryCoordinates[ 0 ] = a3.coord[ 0 ] - a2.coord[ 0 ];
		temporaryCoordinates[ 1 ] = a3.coord[ 1 ] - a2.coord[ 1 ];
		temporaryCoordinates[ 2 ] = a3.coord[ 2 ] - a2.coord[ 2 ];
		// translate all other atoms by -a3
		for ( int i = 0, ix3, atx3; i < atomListLength; i++ ){
			atomNumber = atomList[ i ];	
			if ( ( atomNumber == a1num ) || ( atomNumber == a2num ) || 
					( atomNumber == a3num ) )
				continue;
			atx3 = atomNumber * 3;   
			ix3 = i * 3;
			temporaryCoordinates[ ix3 + 3] = actualCoordinates[ atx3 ] - 
					a2.coord[ 0 ];
			temporaryCoordinates[ ix3 + 4 ] = actualCoordinates[ atx3 + 1 ] - 
					a2.coord[ 1 ];
			temporaryCoordinates[ ix3 + 5 ] = actualCoordinates[ atx3 + 2 ] - 
					a2.coord[ 2 ];
		} // get angle info from atom2
		// rotate a1 to 1,0,0
		Matrix3D atom1Matrix = new Matrix3D();
		atom1Matrix.unit();
		double a1YAngle = pseudoAtom1.angleAboutYAxis();
		atom1Matrix.yrot( -a1YAngle );
		atom1Matrix.transform( pseudoAtom1.coord, 1 );
		double a1ZAngle = pseudoAtom1.angleAboutZAxis();
		atom1Matrix.zrot( 180.0 - a1ZAngle );

		Matrix3D rotationMatrix = new Matrix3D();
		rotationMatrix.unit();
		rotationMatrix.yrot( -a1YAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.zrot( 180.0 - a1ZAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		pseudoAtom1.coord[ 0 ] = temporaryCoordinates[ 0 ];
		pseudoAtom1.coord[ 1 ] = temporaryCoordinates[ 1 ];
		pseudoAtom1.coord[ 2 ] = temporaryCoordinates[ 2 ];
		double a3XAngle = pseudoAtom1.angleAboutXAxis();
		rotationMatrix.unit();
		rotationMatrix.xrot( a3XAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.yrot( originalAngle - angle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.zrot( a1ZAngle - 180.0 );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.yrot( a1YAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates ); 
		rotationMatrix.unit(); 
		rotationMatrix.translate( a2.coord[ 0 ], a2.coord[ 1 ], a2.coord[ 2 ] );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		for ( int i = 0, ix3, atx3; i < atomListLength; i++ ){
			atomNumber = atomList[ i ];	
			if ( ( atomNumber == a1num ) || ( atomNumber == a2num ) || 
					( atomNumber == a3num ) )
				continue;
			atx3 = atomNumber * 3;
			ix3 = i * 3;
			actualCoordinates[ atx3 ] = temporaryCoordinates[ ix3 + 3];
			actualCoordinates[ atx3 + 1 ] = temporaryCoordinates[ ix3 + 4 ];
			actualCoordinates[ atx3 + 2 ] = temporaryCoordinates[ ix3 + 5 ];
			resolveCoordinates( atomNumber );
		}
		int a3x3 = a3num * 3;
		actualCoordinates[ a3x3 ] = temporaryCoordinates[ 0 ];
		actualCoordinates[ a3x3 + 1 ] = temporaryCoordinates[ 1 ]; 
		actualCoordinates[ a3x3 + 2 ] = temporaryCoordinates[ 2 ]; 
		resolveCoordinates( a3num );
	}


	public void setTorsion( int a1num, int a2num, int a3num, int a4num, double torsion,
			int atomList[], int numberOfElements ){
		resolveCoordinates( a1num );  // update a1 coords
		resolveCoordinates( a2num );  // update a2 coords
		resolveCoordinates( a3num );  // update a3 coords
		resolveCoordinates( a4num );  // update a4 coords
		Atom a1 = atom[ a1num ];  
		Atom a2 = atom[ a2num ];
		Atom a3 = atom[ a3num ];
		Atom a4 = atom[ a4num ];
		Atom pseudoAtom2 = new Atom( "a2", a2.coord[ 0 ] - a3.coord[ 0 ],
				a2.coord[ 1 ] - a3.coord[ 1 ], a2.coord[ 2 ] - a3.coord[ 2 ] );
		double originalTorsion = a4.torsion( a1, a2, a3 );
		int numberOfCoordinates = 0;
		int atomListLength = 0;
		int atomNumber;
		if ( numberOfElements == 0 ){
			atomListLength = 0;
			numberOfCoordinates = 1;
		}
		else{
			atomListLength = numberOfElements;
			numberOfCoordinates = atomListLength + 1;
		}
		int numberOfCoordinatesx3 = numberOfCoordinates * 3;
		// array of atom numbers to be rotated
		float temporaryCoordinates[] = new float[ numberOfCoordinatesx3 ];
		// store atom 4 as first entry in array
		temporaryCoordinates[ 0 ] = a4.coord[ 0 ] - a3.coord[ 0 ];
		temporaryCoordinates[ 1 ] = a4.coord[ 1 ] - a3.coord[ 1 ];
		temporaryCoordinates[ 2 ] = a4.coord[ 2 ] - a3.coord[ 2 ];
		// translate all other atoms by -a3
		for ( int i = 0, ix3, atx3; i < atomListLength; i++ ){
			atomNumber = atomList[ i ];	
			if ( ( atomNumber == a1num ) || ( atomNumber == a2num ) || 
					( atomNumber == a3num ) || ( atomNumber == a4num ) )
				continue;
			atx3 = atomNumber * 3;   
			ix3 = i * 3;
			temporaryCoordinates[ ix3 + 3] = actualCoordinates[ atx3 ] - 
					a3.coord[ 0 ];
			temporaryCoordinates[ ix3 + 4 ] = actualCoordinates[ atx3 + 1 ] - 
					a3.coord[ 1 ];
			temporaryCoordinates[ ix3 + 5 ] = actualCoordinates[ atx3 + 2 ] - 
					a3.coord[ 2 ];
		} // get angle info from atom2
		Matrix3D atom2Matrix = new Matrix3D();
		atom2Matrix.unit();
		double a2YAngle = pseudoAtom2.angleAboutYAxis();
		atom2Matrix.yrot( -a2YAngle );
		atom2Matrix.transform( pseudoAtom2.coord, 1 );
		atom2Matrix.unit();
		double a2ZAngle = pseudoAtom2.angleAboutZAxis();
		Matrix3D rotationMatrix = new Matrix3D();  // lets rumble
		rotationMatrix.unit();
		rotationMatrix.yrot( -a2YAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.zrot( 180.0 - a2ZAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.xrot( originalTorsion - torsion );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.zrot( a2ZAngle - 180.0 );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.yrot( a2YAngle );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		rotationMatrix.unit();
		rotationMatrix.translate( a3.coord[ 0 ], a3.coord[ 1 ], a3.coord[ 2 ] );
		rotationMatrix.transform( temporaryCoordinates, numberOfCoordinates );
		for ( int i = 0, ix3, atx3; i < atomListLength; i++ ){
			atomNumber = atomList[ i ];	
			if ( ( atomNumber == a1num ) || ( atomNumber == a2num ) || 
					( atomNumber == a3num ) || ( atomNumber == a4num ) )
				continue;
			atx3 = atomNumber * 3;
			ix3 = i * 3;
			actualCoordinates[ atx3 ] = temporaryCoordinates[ ix3 + 3];
			actualCoordinates[ atx3 + 1 ] = temporaryCoordinates[ ix3 + 4 ];
			actualCoordinates[ atx3 + 2 ] = temporaryCoordinates[ ix3 + 5 ];
			resolveCoordinates( atomNumber );
		}
		int a4x3 = a4num * 3;
		actualCoordinates[ a4x3 ] = temporaryCoordinates[ 0 ];
		actualCoordinates[ a4x3 + 1 ] = temporaryCoordinates[ 1 ]; 
		actualCoordinates[ a4x3 + 2 ] = temporaryCoordinates[ 2 ]; 
		resolveCoordinates( a4num );
	}

	public void setBondDistance( int atom1, int atom2, double bondLength, int atomList[], 
			int numberOfElements ){
		resolveCoordinates( atom1 );
		resolveCoordinates( atom2 );
		Atom a1 = atom[ atom1 ];  
		Atom a2 = atom[ atom2 ];
		int atomListLength = 0;
		int atomNumber;
		if ( numberOfElements == 0 ){
			atomListLength = 0;
		}
		else{
			atomListLength = numberOfElements;
		}
		// translate all other atoms by factor
		double coord0 = a1.coord[ 0 ] - a2.coord[ 0 ] - (float)bondLength;
		double coord1 = a1.coord[ 1 ] - a2.coord[ 1 ];
		double coord2 = a1.coord[ 2 ] - a2.coord[ 2 ];
		/*                double lengthFactor = ( originalDistance - bondLength ) / a2.distance( a1 );
                float a2coord0 = (float)( coord0 * lengthFactor + a2.coord[ 0 ] );
                float a2coord1 = (float)( coord1 * lengthFactor + a2.coord[ 1 ] );
                float a2coord2 = (float)( coord2 * lengthFactor + a2.coord[ 2 ] );
                coord0 = (float)( coord0 * lengthFactor );
                coord1 = (float)( coord1 * lengthFactor );
                coord2 = (float)( coord2 * lengthFactor ); */
		for ( int i = 0, atx3; i < atomListLength; i++ ){
			atomNumber = atomList[ i ];	
			if ( ( atomNumber == atom1 ) || ( atomNumber == atom2 ) )
				continue;
			atx3 = atomList[ i ] * 3;   
			actualCoordinates[ atx3 ] += (float)coord0;
			actualCoordinates[ atx3 + 1 ] += (float)coord1;
			actualCoordinates[ atx3 + 2 ] += (float)coord2;
			resolveCoordinates( atomList[ i ] );
		} // get angle info from atom2
		int atom2x3 = atom2 * 3;
		actualCoordinates[ atom2x3 ] += (float)coord0;
		actualCoordinates[ atom2x3 + 1 ] += (float)coord1;
		actualCoordinates[ atom2x3 + 2 ] += (float)coord2;
		resolveCoordinates( atom2 );
	}

	/** centers the molecule in terms of its coordinates */
	public void center(){
		float xCenter = 0.0f, yCenter = 0.0f, zCenter = 0.0f;
		numberOfAtomsx3 = numberOfAtoms * 3;
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			xCenter += actualCoordinates[ i ];
			yCenter += actualCoordinates[ i + 1 ];
			zCenter += actualCoordinates[ i + 2 ];
		}
		xCenter /= numberOfAtoms;	
		yCenter /= numberOfAtoms;	
		zCenter /= numberOfAtoms;	
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			actualCoordinates[ i ] -= xCenter;
			actualCoordinates[ i + 1 ] -= yCenter;
			actualCoordinates[ i + 2 ] -= zCenter;
		}
	}

	public int sp( int atomNumber ){
		int spHybrid = 0;
		int totalBonds = connected[ atomNumber ][ 0 ];
		if ( atom[ atomNumber ].numberOfTripleBonds >= 1 )
			return( 1 );
		else if ( atom[ atomNumber ].numberOfDoubleBonds >= 2 )
			return( 1 );
		else if ( atom[ atomNumber ].numberOfDoubleBonds == 1 )
			spHybrid += 2;
		for ( int i = 1; i < totalBonds; i++ ){
			int bondedToAtomNumber = connected[ atomNumber ][ i ];
			if ( atom[ bondedToAtomNumber ].numberOfTripleBonds >= 1 )
				return( 1 );
			else if ( atom[ bondedToAtomNumber ].doubleBondedTo( atomNumber ) )
				spHybrid += 2;

		}
		if ( ( spHybrid == 2 ) && ( totalBonds == 3 ) )
			return( 2 );
		else if ( ( spHybrid == 4 ) && ( totalBonds == 2 ) )
			return( 1 );
		return ( totalBonds - 1 );
	}

	public boolean isConnectedTo( int atom1, int atom2[], int elementNumber, 
			int numberOfConnections[], int typeOfBonds[] ){
		int numberMatching = 0;
		for ( int i = 1; i < connected[ atom1 ][ 0 ]; i++ ){
			if ( atom[ connected[ atom1 ][ i ] ].elementNumber == elementNumber ){
				atom2[ numberMatching ] = connected[ atom1 ][ i ];
				if ( atom[ connected[ atom1 ][ i ] ].doubleBondedTo( atom1 ) )
					typeOfBonds[ numberMatching++ ] = 2;
				else if ( atom[ atom1 ].doubleBondedTo( connected[ atom1 ][ i ] ) )
					typeOfBonds[ numberMatching++ ] = 2;
				else if ( atom[ atom1 ].tripleBondedTo( connected[ atom1 ][ i ] ) )
					typeOfBonds[ numberMatching++ ] = 3;
				else if ( atom[ connected[ atom1 ][ i ] ].tripleBondedTo( atom1 ) )
					typeOfBonds[ numberMatching++ ] = 3;
				else
					typeOfBonds[ numberMatching++ ] = 1;
			}
			numberOfConnections[ 0 ] = numberMatching;
			if ( numberMatching > 4 )
				return( true );
		}
		if ( numberMatching > 0 )
			return ( true );
		return ( false );
	}

	public void establishConnectivity( boolean just12 ){
		// make lists of 1-2, 1-3, 1-4 connectivities for refinement
		connected = new int[ numberOfAtoms ][ 1 ];
		connected12 = new int[ 1 ];
		numberOf12Connections = 0;
		connected13 = new int[ 1 ];
		numberOf13Connections = 0;
		connected14 = new int[ 1 ];
		numberOf14Connections = 0;
		nonBonded = new int[ 200 ];
		bondedMatrix = new boolean[ numberOfAtoms ][ numberOfAtoms ];
		numberNonBonded = 0;

		/* list for all 1-2 connections */
		for ( int i = 0; i < numberOfAtoms; i++ ){
			for ( int j = i + 1; j < numberOfAtoms; j++ ){
				if ( isConnected( i, j ) ){
					connect12( i, j );
					connect( i, j );
					connect( j, i );
				}
			}
		}

		if ( just12 )
			return;

		/* list for all 1-3 connections */
		int numberOf12Connectionsx2 = numberOf12Connections * 2 - 1;
		for ( int i = 0; i < numberOf12Connectionsx2; i = i + 2 ){
			int atomi = connected12[ i ];
			int atomj = connected12[ i + 1];
			for ( int k = 0; k < numberOfAtoms; k++ ){
				if ( ( k !=  atomi ) && ( k != atomj ) ){
					if ( isConnected( atomi, k ) )
						connect13( k, atomi, atomj );
					if ( isConnected( atomj, k ) )
						connect13( atomi, atomj, k );
				}
			}
		}

		/* list for all 1-4 connections */
		int numberOf13Connectionsx3 = numberOf13Connections * 3 - 2;
		for ( int i = 0; i < numberOf13Connectionsx3; i = i + 3 ){
			int atomi = connected13[ i ];
			int atomj = connected13[ i + 1];
			int atomk = connected13[ i + 2 ];
			for ( int l = 0; l < numberOfAtoms; l++ ){
				if ( ( l !=  atomi ) && ( l != atomj ) && ( l != atomk ) ){
					if ( isConnected( atomi, l ) )
						connect14( l, atomi, atomj, atomk );
					if ( isConnected( atomk, l ) )
						connect14( atomi, atomj, atomk, l );
				}
			}
		}

		/* list for all nonbonded connections */
		for ( int i = 0; i < numberOfAtoms; i++ ){
			for ( int j = i + 1; j < numberOfAtoms; j++ ){
				if ( ! ( bondedMatrix[ i ][ j ] ) ){
					numberNonBonded++;
					int numberOfNBConnectionsx2 = numberNonBonded * 2;
					if ( numberOfNBConnectionsx2 >= nonBonded.length ){
						int largerConnectArray[] = new 
								int[ numberOfNBConnectionsx2 + 200 ];
						System.arraycopy( nonBonded, 0, 
								largerConnectArray, 0, nonBonded.length );
						nonBonded = largerConnectArray;
					}
					nonBonded[ numberOfNBConnectionsx2 - 2 ] = i;
					nonBonded[ numberOfNBConnectionsx2 - 1 ] = j;
				}
			}
		}
	}

	public void updateNonBondedMatrix( int atom1, int atom2 ){
		if ( atom1 > atom2 ){
			int temp = atom1;
			atom1 = atom2;
			atom2 = temp;
		}
		bondedMatrix[ atom1 ][ atom2 ] = true;
	}

	public void connect( int atom1, int atom2 ){
		int numberOfConnections = connected[ atom1 ][ 0 ] + 1;
		int largerConnectArray[] = new int[ numberOfConnections + 1 ];
		System.arraycopy( connected[ atom1 ], 0, largerConnectArray, 0, 
				numberOfConnections );
		largerConnectArray[ 0 ] = numberOfConnections;
		largerConnectArray[ numberOfConnections ] = atom2;
		connected[ atom1 ] = largerConnectArray;
	}

	public boolean isConnected( int atomA, String expr, boolean initialize ){
		if ( initialize ){
			inStack = new boolean[ numberOfAtoms ];	
			for( int i = 0; i < numberOfAtoms; i++ ){
				inStack[ i ] = false;
			}
		}
		inStack[ atomA ] = true;
		if ( expr == null ) 
			return true;
		int numberOfConnections = connected[ atomA ][ 0 ];
		String rootAtom = expr, restOfExpression = expr, firstChar, lastChar;
		int bracket = 0, snip = 0, multiplicity = 1, bondOrder = 0;
		int index = expr.indexOf( "(" );
		if ( ( index > 0 ) || ( index == -1 ) ){  // find the new root atom
			if ( index > 0 ){
				rootAtom = expr.substring( 0, index );  // "N", e.g.
				restOfExpression = expr.substring( index ); // "(C)(C)", e.g.
			}
			else
				restOfExpression = null;
			firstChar = rootAtom.substring( 0, 1 );
			lastChar = rootAtom.substring( rootAtom.length() - 1 );
			if ( firstChar.length() > 0 ){
				if ( firstChar.equals( "-" ) ){
					bondOrder = 1;
					rootAtom = rootAtom.substring( 1 );
				}
				else if ( firstChar.equals( "=" ) ){
					bondOrder = 2;
					rootAtom = rootAtom.substring( 1 );
				}
				else if ( firstChar.equals( "~" ) ){
					bondOrder = 2;
					rootAtom = rootAtom.substring( 1 );
				}
				else if ( firstChar.equals( "#" ) ){
					bondOrder = 3;
					rootAtom = rootAtom.substring( 1 );
				}
			}
			if ( lastChar.length() > 0 ){
				if ( lastChar.equals( "2" ) ){
					multiplicity = 2;
					rootAtom = rootAtom.substring( 0, rootAtom.length() - 1 );
				}
				else if ( lastChar.equals( "3" ) ){
					multiplicity = 3;
					rootAtom = rootAtom.substring( 0, rootAtom.length() - 1 );
				}
				else if ( lastChar.equals( "4" ) ){
					multiplicity = 4;
					rootAtom = rootAtom.substring( 0, rootAtom.length() - 1 );
				}
			}
			if ( multiplicity > numberOfConnectedTypes( atomA, rootAtom ) )
				return false;
			for ( int i = 1; i <= numberOfConnections; i++ ){
				int atomB = connected[ atomA ][ i ];
				if ( inStack[ atomB ] )
					continue;
				if ( ( atom[ atomB ].elementType.equals( rootAtom ) ) ||
						( rootAtom.equals( "*" ) ) ){
					if ( isConnected( atomB, restOfExpression, false ) ){
						if ( bondOrder == 0 )
							return true;
						else if ( bondOrder == 3 ){
							if ( ( tripleBonded( atomA, atomB ) ) || 
									( sp( atomA ) == 1 ) ||
									( sp( atomB ) == 1 ) )
								return true;
						}
						else if ( bondOrder == 2 ){
							if ( ( doubleBonded( atomA, atomB ) ) 
									|| ( sp( atomA ) == 2 ) 
									|| ( sp( atomB ) == 2 ) )
								return true;
						}
						else if ( bondOrder == 1 ){
							if ( sp( atomA ) + sp( atomB ) >= 4 )
								return true;
						}
					}
				}
			}
			return false;
		} // the above loop captures "(C)(C)" and "C"
		Stack stack = new Stack();
		/* below, push (C)(C) onto stack */
		for ( int i = 0; i < restOfExpression.length(); i++ ){
			char sub = restOfExpression.charAt( i );
			if ( sub == '(' ){
				if ( bracket++ == 0 )
					snip = i + 1;
			}
			else if ( sub == ')' ){
				if ( --bracket == 0 )
					stack.push( expr.substring( snip, i ) );
			}
		}
		while ( ! ( stack.empty() ) ){
			String newToken = ( String )stack.pop();
			if ( ! ( isConnected( atomA, newToken, false ) ) )
				return false;
		}
		return true;
	}

	public int numberOfConnectedTypes( int atomA, String name ){
		int numberOfConnections = connected[ atomA ][ 0 ];
		int numberMatching = 0;
		for ( int i = 1; i <= numberOfConnections; i++ ){
			int atomB = connected[ atomA ][ i ];
			if ( ( atom[ atomB ].elementType.equals( name ) ) ||
					( name.equals( "*" ) ) )
				numberMatching++;
		}
		return( numberMatching );
	}

	public boolean isConnected( int atom1, int atom2 ){
		if ( atom[ atom1 ].bondedTo( atom2 ) )
			return ( true );
		else if ( atom[ atom2 ].bondedTo( atom1 ) )
			return ( true );
		return( false );
	}

	public boolean doubleBonded( int atom1, int atom2 ){
		if ( ( atom[ atom1 ].doubleBondedTo( atom2 ) ) ||
				( atom[ atom2 ].doubleBondedTo( atom1 ) ) )
			return true;
		return false;
	}

	public boolean tripleBonded( int atom1, int atom2 ){
		if ( ( atom[ atom1 ].tripleBondedTo( atom2 ) ) ||
				( atom[ atom2 ].tripleBondedTo( atom1 ) ) )
			return true;
		return false;
	}

	public void connect12( int atom1, int atom2 ){
		numberOf12Connections++;
		updateNonBondedMatrix( atom1, atom2 );
		int numberOf12Connectionsx2 = numberOf12Connections * 2;
		int largerConnectArray[] = new int[ numberOf12Connectionsx2 ];
		System.arraycopy( connected12, 0, largerConnectArray, 0, 
				connected12.length );
		if ( atom1 > atom2 ){
			int temp = atom2;
			atom2 = atom1;
			atom1 = temp;
		}
		largerConnectArray[ numberOf12Connectionsx2 - 2 ] = atom1;
		largerConnectArray[ numberOf12Connectionsx2 - 1 ] = atom2;
		connected12 = largerConnectArray;
	}

	public void connect13( int atom1, int atom2, int atom3 ){
		if ( atom1 > atom3 ){
			int temp = atom3;
			atom3 = atom1;
			atom1 = temp;
		}  // check for duplicate entries ( below )
		updateNonBondedMatrix( atom1, atom2 );
		updateNonBondedMatrix( atom2, atom3 );
		updateNonBondedMatrix( atom1, atom3 );
		for ( int i = 0; i < numberOf13Connections; i++ ){
			int ix3 = i * 3;
			if ( ( connected13[ ix3 ] == atom1 ) && 
					( connected13[ ix3 + 1 ] == atom2 ) &&
					( connected13[ ix3 + 2 ] == atom3 ) )
				return;
		}
		numberOf13Connections++;
		int numberOf13Connectionsx3 = numberOf13Connections * 3;
		int largerConnectArray[] = new int[ numberOf13Connectionsx3 ];
		System.arraycopy( connected13, 0, largerConnectArray, 0, 
				connected13.length );
		largerConnectArray[ numberOf13Connectionsx3 - 3 ] = atom1;
		largerConnectArray[ numberOf13Connectionsx3 - 2 ] = atom2;
		largerConnectArray[ numberOf13Connectionsx3 - 1 ] = atom3;
		connected13 = largerConnectArray;
	}

	public void connect14( int atom1, int atom2, int atom3, int atom4 ){
		if ( atom1 > atom4 ){
			int temp = atom4;
			atom4 = atom1;
			atom1 = temp;
			temp = atom3;
			atom3 = atom2;
			atom2 = temp;
		}
		updateNonBondedMatrix( atom1, atom2 );
		updateNonBondedMatrix( atom1, atom3 );
		updateNonBondedMatrix( atom1, atom4 );
		updateNonBondedMatrix( atom2, atom3 );
		updateNonBondedMatrix( atom2, atom4 );
		updateNonBondedMatrix( atom3, atom4 );
		for ( int i = 0; i < numberOf14Connections; i++ ){
			int ix4 = i * 4;
			if ( ( connected14[ ix4 ] == atom1 ) && 
					( connected14[ ix4 + 1 ] == atom2 ) &&
					( connected14[ ix4 + 2 ] == atom3 ) &&
					( connected14[ ix4 + 3 ] == atom4 ) )
				return;
		}
		numberOf14Connections++;
		int numberOf14Connectionsx4 = numberOf14Connections * 4;
		int largerConnectArray[] = new int[ numberOf14Connectionsx4 ];
		System.arraycopy( connected14, 0, largerConnectArray, 0, 
				connected14.length );
		largerConnectArray[ numberOf14Connectionsx4 - 4 ] = atom1;
		largerConnectArray[ numberOf14Connectionsx4 - 3 ] = atom2;
		largerConnectArray[ numberOf14Connectionsx4 - 2 ] = atom3;
		largerConnectArray[ numberOf14Connectionsx4 - 1 ] = atom4;
		connected14 = largerConnectArray;
	}

	public boolean inRing( int currentAtom, int rootAtom, int ringSize, boolean initialize ){
		if ( initialize ){
			inStack = new boolean[ numberOfAtoms ];	
			for ( int i = 0; i < numberOfAtoms; i++ )
				inStack[ i ] = false;
			done = false;
			bonds = 0;
		}
		else 
			inStack[ currentAtom ] = true;
		if ( done )
			return true;
		else if ( ( currentAtom == rootAtom ) && ( bonds == ringSize ) )
			return true;
		else if ( ( currentAtom == rootAtom ) && ( bonds > 2 ) && ( ringSize < 3 ) )
			return true;
		if ( bonds < ringSize ){
			int numberOfConnections = connected[ currentAtom ][ 0 ];
			for ( int i = 1; i <= numberOfConnections; i++ ){
				int newAtom = connected[ currentAtom ][ i ];
				if ( ! ( inStack[ newAtom ] ) ){
					bonds++;
					done = inRing( newAtom, rootAtom, ringSize, false );
				}
				if ( done )
					return true;
			}
		}
		inStack[ currentAtom ] = false;
		bonds--;
		return( false );
	}

	public int getMoleculeAtomNumber( String atomName, int totalResidueNumber ){
		for ( int i = 0; i < residue[ totalResidueNumber ].numberOfAtoms; i++ )
			if ( residue[ totalResidueNumber ].atom[ i ].name.equals( atomName ) )
				return( residue[ totalResidueNumber ].atom[ i ].moleculeAtomNumber );
		return -1;
	}

	public void hideHydrogens(){
		for ( int i = 0; i < numberOfAtoms; i++ ){
			if ( atom[ i ].name.startsWith( "H" ) ){
				atom[ i ].render = -1;
			}
		}
	}

	public void showHydrogens(){
		for ( int i = 0; i < numberOfAtoms; i++ ){
			if ( atom[ i ].name.startsWith( "H" ) ){
				atom[ i ].render = 0;
			}
		}
	}

	/** Turns the given residue into it's enantiomer */
	public void mirror( int residueNumber ){
		int startingNumber = residue[ residueNumber ].atom[ 0 ].moleculeAtomNumber;
		for ( int i = startingNumber, ix3; i < startingNumber + 
				residue[ residueNumber ].numberOfAtoms; i++ ){
			ix3 = i * 3 + 2;
			actualCoordinates[ ix3 ] = -actualCoordinates[ ix3 ];
			resolveCoordinates( i );
		}
	}

	public void invertChirality(){
		int ix3p2 = -1;
		for ( int i = 0; i < numberOfAtoms; i++ ){
			ix3p2 += 3;
			actualCoordinates[ ix3p2 ] = -actualCoordinates[ ix3p2 ];
		}
		resolveCoordinates();
	}

}
