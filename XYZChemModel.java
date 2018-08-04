package com.goosebumpanalytics.biomer;

import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.BufferedInputStream;

/** The representation of a Chemical .pdb model */

class XYZChemModel {

	/** Create a Chemical model by parsing an input stream */
	XYZChemModel (Molecule m, InputStream is) throws Exception {
		String	atomName = "ZZZ";
		float	x = 0f, y = 0f, z = 0f;
		Atom	newAtom;
		Residue newResidue = new Residue();
		m.addStrand();

		StreamTokenizer st = new StreamTokenizer(new BufferedInputStream(is, 4000));
		st.eolIsSignificant(true);
		st.commentChar('#');
		scan:
			while( true ){
				switch( st.nextToken() ){
				case StreamTokenizer.TT_EOF:
					break scan;
				case StreamTokenizer.TT_WORD:
					atomName = st.sval;
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
					newResidue.addAtom( newAtom );
				}
			}
		m.addResidue( 0, newResidue );
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
