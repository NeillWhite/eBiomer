package com.goosebumpanalytics.biomer;

public class ConjugateGradient{

	int numberOfAtomsx3, updateFrequency, maxIterations;
	int updateNumber = 0, maxLine;
	double epsilon, initialStep;
	double lastGradient[];
	double direction[], gradientNorm;
	double temporaryCoordinates[];
	double lastInitialBracket = 0, initialBracket = 1.0e-7, term;
	ForceField forceField;
	MoleculeCanvas c;
	Molecule m;

	ConjugateGradient( ForceField forceField, Molecule m, MoleculeCanvas c, int updateFrequency,
			int maxIterations, double epsilon, int minimizerOptions, int maxLine, 
			double initialStep ){

		this.forceField = forceField;
		this.m = m;
		this.c = c;
		this.updateFrequency = updateFrequency;
		this.maxIterations = maxIterations;
		this.epsilon = epsilon;
		this.initialStep = initialStep;
		initialBracket = initialStep;
		this.maxLine = maxLine;

		numberOfAtomsx3 = m.numberOfAtoms * 3;
		lastGradient = new double[ numberOfAtomsx3 ];
		direction = new double[ numberOfAtomsx3 ];
		temporaryCoordinates = new double[ numberOfAtomsx3 ];

		if ( minimizerOptions == 0 )
			hestenesStiefel();	
		else if ( minimizerOptions == 1 ) 
			fletcherReeves();	
		else if ( minimizerOptions == 2 )
			polakRibiere();	
		else if ( minimizerOptions == 3 )
			powell();	 
	}

	public void fletcherReeves(){
		double lastGradientDotGradient = 0, gradientDotGradient = 0, beta;
		int iterations = 0;

		forceField.calculateGradient();
		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			direction[ i ] = -m.gradient[ i ];
			lastGradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
		}
		gradientDotGradient = lastGradientDotGradient;
		while ( ( lastGradientDotGradient > epsilon ) && ( iterations++ < maxIterations ) ){
			lineMinimize();
			forceField.calculateGradient();
			lastGradientDotGradient = gradientDotGradient;	
			gradientDotGradient = 0;	
			for ( int i = 0; i < numberOfAtomsx3; i++ )
				gradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
			beta = gradientDotGradient / lastGradientDotGradient;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				direction[ i ] = beta * direction[ i ] - m.gradient[ i ];
			}
			if ( updateNumber >= updateFrequency ){
				c.statusText = "gradient = " + (float)gradientDotGradient;
				c.redraw();
				updateNumber = 0;
			}
			updateNumber++;
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

	public void polakRibiere(){
		double lastGradientDotGradient = 0, gradientDotGradient, beta;
		int iterations = 0;

		forceField.calculateGradient();
		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			direction[ i ] = lastGradient[ i ] = -m.gradient[ i ];
			lastGradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
		}
		gradientDotGradient = lastGradientDotGradient;
		while ( ( lastGradientDotGradient > epsilon ) && ( iterations++ < maxIterations ) ){
			lineMinimize();
			forceField.calculateGradient();
			lastGradientDotGradient = gradientDotGradient;
			gradientDotGradient = 0;	
			beta = 0;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				gradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
				beta += ( m.gradient[ i ] - lastGradient[ i ] ) * m.gradient[ i ];
			}
			beta /= lastGradientDotGradient;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				direction[ i ] = beta * direction[ i ] - m.gradient[ i ];
			}
			System.arraycopy( m.gradient, 0, lastGradient, 0, numberOfAtomsx3 );
			if ( updateNumber >= updateFrequency ){
				c.statusText = "gradient = " + gradientDotGradient;
				c.redraw();
				updateNumber = 0;
			}
			updateNumber++;
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

	public void hestenesStiefel(){
		double gradientDotGradient = 1, beta, gradientDiff, denom;
		int iterations = 0;

		forceField.calculateGradient();
		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			direction[ i ] = lastGradient[ i ] = -m.gradient[ i ];
		}
		while ( ( gradientDotGradient > epsilon ) && ( iterations++ < maxIterations ) ){
			lineMinimize();
			forceField.calculateGradient();
			gradientDotGradient = 0;	
			for ( int i = 0; i < numberOfAtomsx3; i++ )
				gradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
			beta = 0;
			denom = 0;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				gradientDiff = m.gradient[ i ] - lastGradient[ i ];
				beta += m.gradient[ i ] * gradientDiff;
				denom += direction[ i ] * gradientDiff;
			}
			if ( Math.abs( denom ) > 1.0e-10 )
				beta /= denom;
			else
				beta = 0;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				direction[ i ] = beta * direction[ i ] - m.gradient[ i ];
			}
			System.arraycopy( m.gradient, 0, lastGradient, 0, numberOfAtomsx3 );
			if ( updateNumber >= updateFrequency ){
				c.statusText = "gradient = " + gradientDotGradient;
				c.redraw();
				updateNumber = 0;
			}
			updateNumber++;
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

	public void powell(){
		double lastGradientDotGradient = 0, gradientDotGradient, beta;
		int iterations = 0;

		forceField.calculateGradient();
		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			direction[ i ] = lastGradient[ i ] = -m.gradient[ i ];
			lastGradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
		}
		while ( ( lastGradientDotGradient > epsilon ) && ( iterations++ < maxIterations ) ){
			lineMinimize();
			forceField.calculateGradient();
			gradientDotGradient = 0;	
			for ( int i = 0; i < numberOfAtomsx3; i++ )
				gradientDotGradient += m.gradient[ i ] * m.gradient[ i ];
			beta = 0;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				beta += ( m.gradient[ i ] - lastGradient[ i ] ) * m.gradient[ i ];
			}
			beta /= lastGradientDotGradient;
			if ( beta < 0 )
				beta = 0;
			lastGradientDotGradient = gradientDotGradient;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				direction[ i ] = beta * direction[ i ] - m.gradient[ i ];
			}
			System.arraycopy( m.gradient, 0, lastGradient, 0, numberOfAtomsx3 );
			if ( updateNumber >= updateFrequency ){
				c.statusText = "gradient = " + gradientDotGradient;
				c.redraw();
				updateNumber = 0;
			}
			updateNumber++;
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

	public double lineMinimize(){
		double a[] = new double[ 1 ];
		double b[] = new double[ 1 ];
		double c[] = new double[ 1 ];
		double minimum[] = new double[ 1 ];
		double energy, delta = 1.0e-7;

		a[ 0 ] = 0; 
		b[ 0 ] = initialBracket;
		bracketMinimum( a, b, c );

		energy = inverseParabolicInterpolation( a, b, c, minimum );
		initialBracket = minimum[ 0 ];

		if ( ( Math.abs( initialBracket ) < delta ) || 
				( initialBracket == lastInitialBracket ) ){
			System.out.println( "MAKING BIGGER, initialBracket = " + initialBracket );
			initialBracket = Math.random() * initialStep;
			for ( int i = 0; i < numberOfAtomsx3; i++ ){
				direction[ i ] = -m.gradient[ i ];
			}
		} 
		lastInitialBracket = initialBracket;
		System.out.println( "initialBracket = " + initialBracket );
		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			m.actualCoordinates[ i ] += direction[ i ] * initialBracket;
		}
		return( energy );
	}

	public void bracketMinimum( double pointA[], double pointB[], double pointC[] ){
		final double GOLDENRATIO = 1.618034;
		double energyA, energyB, energyC, temp;
		double ulim, u, r, q, fu, denominator;

		energyA = oneDimensionalEnergy( pointA[ 0 ] );
		energyB = oneDimensionalEnergy( pointB[ 0 ] );
		if ( energyB > energyA ) {  // step size is too large
			temp = pointA[ 0 ];
			pointA[ 0 ] = pointB[ 0 ];
			pointB[ 0 ] = temp;
			temp = energyB;
			energyB = energyA;
			energyA = temp;  
		}
		pointC[ 0 ] = pointB[ 0 ] + GOLDENRATIO * ( pointB[ 0 ] - pointA[ 0 ] );
		energyC = oneDimensionalEnergy( pointC[ 0 ] );
		while ( energyB > energyC ) {
			r = ( pointB[ 0 ] - pointA[ 0 ] ) * ( energyB - energyC );
			q = ( pointB[ 0 ] - pointC[ 0 ] ) * ( energyB - energyA );
			denominator = Math.max( Math.abs( q - r ), 1.0e-20 );
			if ( ( q - r ) < 0 )
				denominator = -denominator;
			u = ( pointB[ 0 ] ) - ( ( pointB[ 0 ]-pointC[ 0 ] ) * q - 
					( pointB[ 0 ] - pointA[ 0 ] ) * r ) /
					( 2.0 * denominator );
			ulim = pointB[ 0 ] + 100 * ( pointC[ 0 ] - pointB[ 0 ] );
			if ( ( pointB[ 0 ] - u ) * ( u - pointC[ 0 ] ) > 0.0 ) {
				fu=oneDimensionalEnergy( u );
				if ( fu < energyC ) {
					pointA[ 0 ] = pointB[ 0 ];
					pointB[ 0 ] = u;
					energyA = energyB;
					energyB = fu;
					return;
				} else if ( fu > energyB ){
					pointC[ 0 ] = u;
					energyC = fu;
					return;
				}
				u = pointC[ 0 ] + GOLDENRATIO * ( pointC[ 0 ] - pointB[ 0 ] );
				fu = oneDimensionalEnergy( u );
			}else if ( ( pointC[ 0 ] - u ) * ( u - ulim ) > 0.0 ){
				fu = oneDimensionalEnergy( u );
				if ( fu < energyC ){
					pointB[ 0 ] = pointC[ 0 ];
					pointC[ 0 ] = u;
					u = pointC[ 0 ] + GOLDENRATIO * ( pointC[ 0 ] - pointB[ 0 ] );
					energyB = energyC;
					energyC = fu;
					fu = oneDimensionalEnergy( u );
				}
			}else if ( ( u - ulim ) * ( ulim - pointC[ 0 ] ) >= 0.0 ){
				u = ulim;
				fu = oneDimensionalEnergy( u );
			}else{
				u = pointC[ 0 ] + GOLDENRATIO * ( pointC[ 0 ] - pointB[ 0 ] );
				fu = oneDimensionalEnergy( u );
			}
			pointA[ 0 ] = pointB[ 0 ];
			pointB[ 0 ] = pointC[ 0 ];
			pointC[ 0 ] = u;
			energyA = energyB;
			energyB = energyC;
			energyC = fu;
		}
	}

	public double oneDimensionalEnergy( double factor ){

		for ( int i = 0; i < numberOfAtomsx3; i++ ){
			temporaryCoordinates[ i ] = m.actualCoordinates[ i ] + 
					factor * direction[ i ];
		}

		return ( forceField.calculateEnergy( temporaryCoordinates ) );
	}

	public double inverseParabolicInterpolation(double pointa[], double mid[], double pointb[], 
			double minimum[] ){
		int iter;
		int maxIterations = maxLine;
		final double CGOLD = 0.3819660;
		double a,b,d=0,etemp,fu,fv,fw,fx,p,q,r,tol1,tol2,u,v,w,x,xm;
		double e=0.0, tol=2.0e-4;
		double pointA, pointB;
		pointA = pointa[ 0 ];
		pointB = pointb[ 0 ];
		a=(pointA < pointB ? pointA : pointB);
		b=(pointA > pointB ? pointA : pointB);
		x=w=v=mid[ 0 ];
		fw=fv=fx=fu=oneDimensionalEnergy(x);
		for (iter=1;iter<=maxIterations;iter++) {
			xm=0.5*(a+b);
			tol1 = tol* Math.abs( x ) + 1.0e-10;
			tol2=2.0*tol1;
			if (Math.abs(x-xm) <= (tol2-0.5*(b-a))) {
				minimum[0]=x;
				return fu;
			}
			if (Math.abs(e) > tol1) {
				r=(x-w)*(fx-fv);
				q=(x-v)*(fx-fw);
				p=(x-v)*q-(x-w)*r;
				q=2.0*(q-r);
				if (q > 0.0) p = -p;
				q=Math.abs(q);
				etemp=e;
				e=d;
				if (Math.abs(p) >= Math.abs(0.5*q*etemp) || 
						p <= q*(a-x) || p >= q*(b-x))
					d=CGOLD*(e=(x >= xm ? a-x : b-x));
				else {
					d=p/q;
					u=x+d;
					if (u-a < tol2 || b-u < tol2){
						if ( ( xm - x ) < 0 )
							d = - tol1;
						else
							d = tol1;
					}
				}
			} else {
				d=CGOLD*(e=(x >= xm ? a-x : b-x));
			}
			if ( Math.abs( d ) >= tol1 ){
				u = x + d;
			}
			else{
				if ( d >= 0 )
					u = x + tol1;
				else
					u = x - tol1;
			}
			fu=oneDimensionalEnergy(u);
			if (fu <= fx) {
				if (u >= x) a=x; else b=x;
				v = w;
				w = x;
				x = u;
				fv = fw;
				fw = fx;
				fx = fu;
			} else {
				if (u < x) a=u; else b=u;
				if (fu <= fw || w == x) {
					v=w;
					w=u;
					fv=fw;
					fw=fu;
				} else if (fu <= fv || v == x || v == w) {
					v=u;
					fv=fu;
				}
			}
		}
		return( fu );
	}
}
