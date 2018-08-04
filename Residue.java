package com.goosebumpanalytics.biomer;

public class Residue{

	String	name;
	int	numberOfAtoms;
	int	totalResidueNumber;
	int	strandResidueNumber;
	int	strandNumber;
	Atom	atom[];
	float	actualCoordinates[];
	Matrix3D mat;

	/** initialize atom and actualCoordinate arrays with constructor */	
	public Residue(){
		numberOfAtoms = 0;
		totalResidueNumber = 0;
		strandResidueNumber = 0;
		strandNumber = 0;
		atom = new Atom[ 1 ];
		actualCoordinates = new float[ 1 ];
	}

	/** adds specified atom to this residue */
	public int addAtom( Atom newAtom ){
		int newAtomNumber = numberOfAtoms + 1;
		int newAtomNumberx3 = newAtomNumber * 3;

		Atom largerAtomArray[] = new Atom[ numberOfAtoms + 1 ];
		System.arraycopy( atom, 0, largerAtomArray, 0, atom.length );
		atom = largerAtomArray;
		atom[ numberOfAtoms ] = newAtom;	
		newAtom.residueAtomNumber = numberOfAtoms;
		newAtom.totalResidueNumber = totalResidueNumber;
		newAtom.strandResidueNumber = strandResidueNumber;
		newAtom.strandNumber = strandNumber;
		/* note: moleculeAtomNumber and strandAtomNumber NOT updated */

		/* handle coordinate stuff ( atom positions ) */
		float largerCoordinateArray[] = new float[ newAtomNumberx3 ];
		System.arraycopy( actualCoordinates, 0, largerCoordinateArray, 0,
				actualCoordinates.length );
		largerCoordinateArray[ newAtomNumberx3 - 3 ] = newAtom.coord[ 0 ];
		largerCoordinateArray[ newAtomNumberx3 - 2 ] = newAtom.coord[ 1 ];
		largerCoordinateArray[ newAtomNumberx3 - 1 ] = newAtom.coord[ 2 ];
		actualCoordinates = largerCoordinateArray;

		newAtom.residueAtomNumber = numberOfAtoms;
		return( numberOfAtoms++ );
	}

	/** removes specified atom from this residue */
	public int deleteAtom( int atomNumber ){
		int newAtomNumber = numberOfAtoms - 1;
		int newAtomNumberx3 = newAtomNumber * 3;
		int atomNumberx3 = atomNumber * 3;

		/* update Atom residue atom numbers */
		for ( int i = atomNumber + 1; i < numberOfAtoms; i++ )
			atom[ i ].residueAtomNumber -= 1;

		Atom smallerAtomArray[] = new Atom[ numberOfAtoms - 1 ];
		System.arraycopy( atom, 0, smallerAtomArray, 0, atomNumber );
		if ( atomNumber < newAtomNumber )
			System.arraycopy( atom, atomNumber + 1, smallerAtomArray,
					atomNumber, atom.length - atomNumber - 1 );
		atom = smallerAtomArray;

		/* handle coordinates */
		float smallerCoordinateArray[] = new float[ newAtomNumberx3 ];
		System.arraycopy( actualCoordinates, 0, smallerCoordinateArray, 0, 
				atomNumberx3 );
		if ( atomNumber < newAtomNumber )
			System.arraycopy( actualCoordinates, atomNumberx3+3, smallerCoordinateArray,
					atomNumberx3, newAtomNumberx3 - atomNumberx3 );
		actualCoordinates = smallerCoordinateArray;

		return( --numberOfAtoms );
	}

	public void getCenterOfMass( float com[] ){
		float x = 0, y = 0, z = 0;
		for ( int i = 0; i < numberOfAtoms; i = i + 3 ){
			x += actualCoordinates[ i ];
			y += actualCoordinates[ i + 1 ];	
			z += actualCoordinates[ i + 2 ];	
		}
		com[ 0 ] = ( float )( x / numberOfAtoms );
		com[ 1 ] = ( float )( y / numberOfAtoms );
		com[ 2 ] = ( float )( z / numberOfAtoms );
	}
}
