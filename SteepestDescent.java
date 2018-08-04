package com.goosebumpanalytics.biomer;

public class SteepestDescent{

	SteepestDescent( ForceField forceField, Molecule m, MoleculeCanvas c, int updateFrequency,
			int maxIterations, double epsilon ){

		int iteration = 0, updateNumber = 0;
		int numberOfAtomsx3 = m.numberOfAtoms * 3;
		double gradientNorm, lastGradientNorm = 1, stepSize = 0.005;
		double term;

		while( ( lastGradientNorm > epsilon ) && ( iteration++ < maxIterations ) ){
			forceField.calculateGradient();	
			gradientNorm = 0;
			for ( int i = 0; i < numberOfAtomsx3; i++ )
				gradientNorm += m.gradient[ i ] * m.gradient[ i ];
			gradientNorm = Math.sqrt( gradientNorm );

			if ( lastGradientNorm < gradientNorm )
				stepSize *= .35;
			else  // gradient is getting smaller
				stepSize *= 1.2;

			term = stepSize / gradientNorm;
			//System.out.println( "term = " + term );
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				m.actualCoordinates[ i ] -= term * m.gradient[ i ];
			}
			lastGradientNorm = gradientNorm;

			if ( updateNumber++ >= updateFrequency ){
				c.statusText = "gradient = " + (float)gradientNorm;
				c.redraw();
				updateNumber = 0;
			}
		}
		forceField.calculateGradient();
		gradientNorm = 0;
		for( int i = 0; i < numberOfAtomsx3; i++ ){
			gradientNorm += m.gradient[ i ] * m.gradient[ i ];
		}
		gradientNorm = Math.sqrt( gradientNorm );
		double energy = forceField.calculateEnergy( m.actualCoordinates );
		c.m.center();
		c.displayMolecule( c.m );
		c.statusText = "Gradient = " + (float)gradientNorm + ", Energy = " + (float)energy;
		c.repaint();
	}
}
