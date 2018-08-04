package com.goosebumpanalytics.biomer;

public class Strand {

	String	name;
	int	numberOfResidues;
	int	numberOfAtoms;
	int	number;
	Residue	residue[];
	Matrix3D mat;

	/** initialize residue array */	
	Strand(){
		numberOfResidues = 0;
		numberOfAtoms = 0;
		number = 0;
		residue = new Residue[ 1 ];
		name = "";
	}

	Strand( Residue firstResidue ){
		numberOfResidues = 0;
		numberOfAtoms = 0;
		number = 0;
		residue = new Residue[ 1 ];
		residue[ 0 ] = firstResidue;
		name = "";
	}

	/** initialize residue array and give strand a name */
	Strand( String strandName ){
		numberOfResidues = 0;
		numberOfAtoms = 0;
		number = 0;
		residue = new Residue[ 1 ];
		name = strandName;
	}

	/** adds a residue to the strand */
	public int addResidue( Residue newResidue ){
		numberOfAtoms += newResidue.numberOfAtoms;

		/* handle residue stuff */
		Residue largerResidueArray[] = new Residue[ numberOfResidues + 1 ];
		System.arraycopy( residue, 0, largerResidueArray, 0, residue.length );
		residue = largerResidueArray;
		newResidue.strandResidueNumber = numberOfResidues;
		newResidue.strandNumber = number;
		for( int i = 0; i < newResidue.numberOfAtoms; i++ ){
			newResidue.atom[ i ].strandNumber = number;
			newResidue.atom[ i ].strandResidueNumber = numberOfResidues;
		}
		residue[ numberOfResidues ] = newResidue;

		return( numberOfResidues++ );
	}

	/** deletes a residue to the strand */
	public int deleteResidue( int residueNumber ){
		numberOfAtoms -= residue[ residueNumber ].numberOfAtoms;

		for( int i = residueNumber; i < numberOfResidues; i++ )
			residue[ i ].strandResidueNumber -= 1;
		Residue smallerResidueArray[] = new Residue[ numberOfResidues - 1 ];
		System.arraycopy( residue, 0, smallerResidueArray, 0, residueNumber );
		if ( residueNumber < numberOfResidues - 1 )
			System.arraycopy( residue, residueNumber + 1, smallerResidueArray,
					residueNumber, residue.length - residueNumber - 1 );

		return( --numberOfResidues );
	}

	/** adds an atom to the strand and to the specified residue */
	public int addAtom( int residueNumber, Atom newAtom ){
		newAtom.strandNumber = number;
		newAtom.strandResidueNumber = residueNumber;
		/* residue.addAtom() updates residueAtomNumber field */
		residue[ residueNumber ].addAtom( newAtom );
		return( numberOfAtoms++ );
	}

	/** deletes an atom from the strand */
	public int deleteAtom( int residueNumber, int atomNumber ){
		residue[ residueNumber ].deleteAtom( atomNumber );
		return( --numberOfAtoms );
	}
}	
