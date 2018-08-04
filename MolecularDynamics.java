package com.goosebumpanalytics.biomer;


public class MolecularDynamics{

	Molecule m;
	MoleculeCanvas c;
	BiomerFrame BiomerFrame;
	ForceField forceField = null;
	int previousForceField, screenRefresh, numberOfAtoms, numberOfAtomsx3;
	int AMBER = 0, MM2 = 1, LJ = 2;
	double heatTime, runTime, coolTime, heatTemp, runTemp, coolTemp, stepSize;
	double halfStepSize, currentTemp;
	double velocity[], timeStep[], halfTimeStep[], thisTimeStep, thisHalfTimeStep;
	double characteristicMass = 1.007825037;

	MolecularDynamics( Molecule m, MoleculeCanvas c, BiomerFrame BiomerFrame, 
			int ff, double heatTime, double runTime, double coolTime, double heatTemp,
			double runTemp, double coolTemp, double stepSize, int screenRefresh ){

		int numberOfHeatSteps, numberOfRunSteps, numberOfCoolSteps;
		this.m = m;
		this.c = c;
		this.BiomerFrame = BiomerFrame;
		this.heatTime = heatTime;
		this.runTime = runTime;
		this.coolTime = coolTime;
		this.heatTemp = heatTemp;
		this.runTemp = runTemp;
		this.coolTemp = coolTemp;
		// conversion to AKMA units
		this.stepSize = stepSize * 0.0204548295;
		halfStepSize = 0.5 * this.stepSize;
		double stepSizeDiv1000 = stepSize * .001;
		numberOfHeatSteps = (int)( heatTime / stepSizeDiv1000 );
		numberOfRunSteps = (int)( runTime / stepSizeDiv1000 );
		numberOfCoolSteps = (int)( coolTime / stepSizeDiv1000 );
		this.screenRefresh = screenRefresh;
		previousForceField = ff;
		forceField = BiomerFrame.forceField;
		numberOfAtoms = m.numberOfAtoms;
		numberOfAtomsx3 = numberOfAtoms * 3;
		velocity = new double[ numberOfAtomsx3 ];
		timeStep = new double[ numberOfAtoms ];
		halfTimeStep = new double[ numberOfAtoms ];

		if ( ( previousForceField != ff ) || ( m.typeSet == false ) ){
			forceField = null;
		}
		if ( ff == AMBER ){
			if ( forceField == null ){
				forceField = new Amber( m );
				BiomerFrame.forceField = forceField;
			}
		}

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
		previousForceField = ff;
		// set up time step arrays
		for ( int i = 0; i < numberOfAtoms; i++ ){
			timeStep[ i ] = characteristicMass * this.stepSize / m.atom[ i ].mass;	
			halfTimeStep[ i ] = 0.5 * timeStep[ i ];	
		}

		initializeCalculation();
		forceField.calculateGradient();
		if ( heatTemp > 0 )
			rescaleVelocities( heatTemp );
		currentTemp = heatTemp;
		for ( int i = 0; i < numberOfHeatSteps; i++ ){
			verlet();
			c.statusText = "MD Heating: " + i + " fs, Temperature = " + 
					(int)currentTemp + "K";
			c.redraw();
			currentTemp = heatTemp + ( runTemp - heatTemp ) *
					( ( double )( i + 1 )/ numberOfHeatSteps );
			rescaleVelocities( currentTemp );
		}
		rescaleVelocities( runTemp );
		currentTemp = runTemp;
		for ( int i = 0; i < numberOfRunSteps; i++ ){
			verlet();
			c.statusText = "MD Running: " + i + " fs, Temperature = " + 
					(int)currentTemp + "K";
			c.redraw();
		}
		for ( int i = 0; i < numberOfCoolSteps; i++ ){
			currentTemp = runTemp - ( runTemp - coolTemp ) * 
					( ( double )( i + 1 )/ numberOfCoolSteps );
			rescaleVelocities( currentTemp );
			verlet();
			c.statusText = "MD Cooling: " + i + " fs, Temperature = " + 
					(int)currentTemp + "K";
			c.redraw();
		}
		forceField.calculateGradient();
		double gradientNorm = 0;
		for( int i = 0; i < numberOfAtomsx3; i++ ){
			gradientNorm += m.gradient[ i ] * m.gradient[ i ];
		}
		gradientNorm = Math.sqrt( gradientNorm );
		double energy = forceField.calculateEnergy( m.actualCoordinates );
		c.m.center();
		c.displayMolecule( c.m );
		c.statusText = "MD Simulation complete.  Gradient = " + (float)gradientNorm + 
				", Energy = " + (float)energy;
		c.repaint();
	}

	public void initializeCalculation(){
		double xTotal = 0, yTotal = 0, zTotal = 0;
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			velocity[ i ] = Math.random();
			velocity[ i + 1 ] = Math.random();
			velocity[ i + 2 ] = Math.random();
			xTotal += velocity[ i ];
			yTotal += velocity[ i + 1 ];
			zTotal += velocity[ i + 2 ];
		}
		xTotal /= numberOfAtoms;
		yTotal /= numberOfAtoms;
		zTotal /= numberOfAtoms;
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			velocity[ i ] -= xTotal;
			velocity[ i + 1 ] -= yTotal;
			velocity[ i + 2 ] -= zTotal;
		}
	}

	public void rescaleVelocities( double temperature ){
		//System.out.println( "rescaling velocities to " + temperature );
		if ( temperature < 0 ) 
			return;
		double vx, vy, vz, vSquared = 0, scaleFactor;
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			vx = velocity[ i ];
			vy = velocity[ i + 1 ];
			vz = velocity[ i + 2 ];
			vSquared += vx * vx + vy * vy + vz * vz;
		}
		//		vSquared /= numberOfAtoms;
		scaleFactor = Math.sqrt( temperature / vSquared );
		for ( int i = 0; i < numberOfAtomsx3; i = i + 3 ){
			velocity[ i ] *= scaleFactor;
			velocity[ i + 1 ] *= scaleFactor;
			velocity[ i + 2 ] *= scaleFactor;
		}
		//			vSquared  =0;
		//		for ( int i = 0; i < numberOfAtomsx3; i++ ){
		//			vSquared += velocity[ i ] * velocity[ i ];
		//		}
	}

	public void verlet(){
		double totalX = 0, totalY = 0, totalZ = 0;
		for ( int i = 0, j = 0; i < numberOfAtomsx3; i = i + 3 ){
			thisTimeStep = timeStep[ j ];
			thisHalfTimeStep = halfTimeStep[ j++ ];
			velocity[ i ] -= m.gradient[ i ] * thisHalfTimeStep;
			velocity[ i + 1 ] -= m.gradient[ i + 1 ] * thisHalfTimeStep;
			velocity[ i + 2 ] -= m.gradient[ i + 2 ] * thisHalfTimeStep;
			m.actualCoordinates[ i ] += velocity[ i ] * thisTimeStep;	
			m.actualCoordinates[ i + 1 ] += velocity[ i + 1 ] * thisTimeStep;	
			m.actualCoordinates[ i + 2 ] += velocity[ i + 2 ] * thisTimeStep;	
			totalX += m.actualCoordinates[ i ];
			totalY += m.actualCoordinates[ i + 1 ];
			totalZ += m.actualCoordinates[ i + 2 ];
		}
		totalX /= m.numberOfAtoms;
		totalY /= m.numberOfAtoms;
		totalZ /= m.numberOfAtoms;
		forceField.calculateGradient();
		for ( int i = 0, j = 0; i < numberOfAtomsx3; i = i + 3 ){
			m.actualCoordinates[ i ] -= totalX;
			m.actualCoordinates[ i + 1 ] -= totalY;
			m.actualCoordinates[ i + 2 ] -= totalZ;
			thisHalfTimeStep = halfTimeStep[ j++ ];
			velocity[ i ] -= m.gradient[ i ] * thisHalfTimeStep;
			velocity[ i + 1 ] -= m.gradient[ i + 1 ] * thisHalfTimeStep;
			velocity[ i + 2 ] -= m.gradient[ i + 2 ] * thisHalfTimeStep;
		}
	}
}
