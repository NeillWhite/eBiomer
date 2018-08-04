package com.goosebumpanalytics.biomer;

import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.BufferedInputStream;

/** The representation of a Chemical .pdb model */

class PDBChemModel {

	/** Create a Chemical model by parsing an input stream */
	PDBChemModel (Molecule m, InputStream is) throws Exception {
		int	modelAtomNumber = 0;
		int	residueNumber = 0;
		int	strandNumber = 0;
		String	atomName = "ZZZ", residueName = "ZZZ"; 
		String  lastResidueName = "ZZZ";
		float	x = 0f, y = 0f, z = 0f;
		Atom	newAtom;
		Residue newResidue = null;
		m.addStrand();

		StreamTokenizer st = new StreamTokenizer(new BufferedInputStream(is, 4000));
		st.eolIsSignificant(true);
		st.wordChars('*', '*');
		st.wordChars('\'', '\'');
		scan:
			while( true ){
				switch( st.nextToken() ){
				case StreamTokenizer.TT_EOF:
					break scan;
				case StreamTokenizer.TT_WORD:
					if ( st.sval.equals( "END" ) )
						break scan;
					else if ( st.sval.equals( "TER" ) ){
						m.addStrand();
						strandNumber++;
						lastResidueName = "ZZZ";
						residueNumber = 0;
						break;
					} // skip REMARK and other stuff ( next )
					else if ( ! ( ( st.sval.equals( "ATOM" ) ) || 
							( st.sval.equals( "HETATM" ) ) ) ){
						// if not ATOM or HETATM, blow line away
						while ( st.nextToken() != StreamTokenizer.TT_EOL );
						break;
					} // read the atom number 
					if ( st.nextToken() == StreamTokenizer.TT_NUMBER ){
						modelAtomNumber = (int)st.nval;
					} // read the atom name
					if ( st.nextToken() == StreamTokenizer.TT_NUMBER ){
						atomName = new String( new 
								Integer( (int)st.nval ).toString() );
						if ( st.nextToken() == StreamTokenizer.TT_WORD )
							atomName += st.sval;
					}  // read the residue name
					else{
						atomName = st.sval;
					}
					if ( st.nextToken() == StreamTokenizer.TT_NUMBER ){
						residueName = new String( new 
								Integer( (int)st.nval ).toString() );
						if ( st.nextToken() == StreamTokenizer.TT_WORD )
							residueName += st.sval;
					}  // read the strand name
					else{
						residueName = st.sval;
					}
					if ( !( residueName.equals( lastResidueName ) ) ){
						if ( newResidue != null ){
							m.addResidue( strandNumber, newResidue );
						}
						newResidue = new Residue();
						newResidue.name = residueName;
						lastResidueName = residueName;
						residueNumber++;
					}

					if ( st.nextToken() == StreamTokenizer.TT_WORD ){
					}
					else
						st.pushBack();
					if ( st.nextToken() == StreamTokenizer.TT_NUMBER ){
						//strandResidueNumber = (int)st.nval;
					}  // stick x-coord back in the queue
					if (st.nextToken() == StreamTokenizer.TT_NUMBER){
						x = (float)st.nval;
						if (st.nextToken() == StreamTokenizer.TT_NUMBER){
							y = (float)st.nval;
							if (st.nextToken() == 
									StreamTokenizer.TT_NUMBER)
								z = (float)st.nval;
						}
					}
					newAtom = new Atom( atomName, x, y, z );
					newAtom.modelAtomNumber = modelAtomNumber;
					newAtom.strandNumber = strandNumber;
					newResidue.addAtom( newAtom );
				}
			}
		if ( residueNumber <= 1 ){ // only 1 residue
			m.addResidue( 0, newResidue );
		}
		m.center();  // center the molecule about it's c.o.m.
		is.close();
		for ( int i = 0; i < m.numberOfAtoms; i++ ){
			for ( int j = i + 1 ; j < m.numberOfAtoms ; j++ ){
				if ( m.atom[ i ].distance( m.atom[ j ] ) < 1.7 ){
					m.atom[ i ].addBond( j );
				}
			}
		}
	}
}
