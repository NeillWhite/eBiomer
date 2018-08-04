package com.goosebumpanalytics.biomer;


public interface ForceField{

	boolean getStretchParameters( int a1, int a2, double f[], double eqD[] );
	boolean getBendParameters( int a1, int a2, int a3, double f[], double eqD[] );
	boolean getTorsionParameters( int a1, int a2, int a3, int a4, double f[], 
			double eqA[], int terms[], int mult[] );
	boolean getHydrogenBondedParameters( int a1, int a2, int c[], int d[] );
	boolean getNonBondedParameters( int a1, double r[], double eps[] );
	boolean getImproperTorsionParameters( int a1, int a2, int a3, int a4, double f[], 
			double eqA[], int terms[] );
	void 	calculateTypes();
	void 	initializeCalculation();
	void 	calculateGradient();
	double 	calculateEnergy( float coordinates[] );
	double 	calculateEnergy( double coordinates[] );
}
