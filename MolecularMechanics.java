package com.goosebumpanalytics.biomer;


public class MolecularMechanics{

	Molecule m;
	MoleculeCanvas c;
	BiomerFrame BiomerFrame;
	final int AMBER = 0, MM2 = 1, STEEPESTDESCENT = 0, CONJUGATEGRADIENT = 1;
	ForceField forceField = null;
	int previousForceField, minimizerOptions;

	MolecularMechanics( Molecule m, MoleculeCanvas c, BiomerFrame BiomerFrame, 
			int ff, int minimizer, int minimizerOptions, int maxIter, double gradNorm,
			int maxLine, double stepSize ){
		this.m = m;
		this.c = c;
		this.BiomerFrame = BiomerFrame;
		this.forceField = BiomerFrame.forceField;
		this.minimizerOptions = minimizerOptions;
		previousForceField = ff;

		minimize( m, ff, minimizer, minimizerOptions, maxIter, gradNorm,
				maxLine, stepSize );
	}

	public void minimize( Molecule m, int ff, int minimizer, int options, int maxIter,
			double gradNorm, int maxLine, double stepSize ){	
		int updateFrequency = 0;
		minimizerOptions = options;

		if ( ( previousForceField != ff ) || ( m.typeSet == false ) ){
			forceField = null;
		}
		if ( ff == AMBER ){
			if ( forceField == null ){
				forceField = new Amber( m );
				BiomerFrame.forceField = forceField;
			}
		}
		//		else if ( ff == MM2 )
		//			forceField = new MM2( m );

		if ( m.typeSet == false ){
			c.statusText = "Establishing connectivity...";
			c.redraw();
			m.establishConnectivity( false );
			c.statusText = "Calculating atom types...";
			c.redraw();
			forceField.calculateTypes();	
			c.statusText = "Initializing calculation...";
			c.redraw();
			m.typeSet = true;
			forceField.initializeCalculation();	
		}

		if ( minimizer == STEEPESTDESCENT ){
			new SteepestDescent( forceField, m, c, 
					updateFrequency, maxIter, gradNorm );
		}
		else if ( minimizer == CONJUGATEGRADIENT ){
			new ConjugateGradient( forceField, 
					m, c, updateFrequency, maxIter, gradNorm, minimizerOptions, 
					maxLine, stepSize );
		}
		previousForceField = ff;
	}
}
