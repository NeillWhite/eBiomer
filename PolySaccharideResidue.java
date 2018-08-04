package com.goosebumpanalytics.biomer;

public class PolySaccharideResidue {

	Residue residue;

	Residue getResidue( String residueName, boolean alpha ){
		if ( residueName.equals( "Allose" ) ){
			createResidue( "ALL", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -0.167f, 2.160f, 1.365f );
			residue.atom[ 1 ] = new Atom( "O", -0.667f, 1.350f, 2.458f );
			residue.atom[ 2 ] = new Atom( "C1", -0.160f, -0.012f, 2.547f );
			residue.atom[ 4 ] = new Atom( "C2", -0.488f, -0.780f, 1.244f );
			residue.atom[ 5 ] = new Atom( "O2", 0.112f, -2.053f, 1.277f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.404f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.482f, 1.473f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", 0.133f, 2.163f, -1.062f );
			residue.atom[ 10 ] = new Atom( "C6", -0.789f, 3.579f, 1.507f );
			residue.atom[ 11 ] = new Atom( "O6", -0.208f, 4.489f, 0.599f );
			residue.atom[ 12 ] = new Atom( "H5", 0.929f, 2.264f, 1.471f );
			residue.atom[ 15 ] = new Atom( "H2", -1.587f, -0.908f, 1.172f );
			residue.atom[ 16 ] = new Atom( "HO2", -0.135f, -2.508f, 0.450f );
			residue.atom[ 17 ] = new Atom( "H3", -0.351f, -0.507f, -0.921f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, -0.933f, -0.021f );
			residue.atom[ 19 ] = new Atom( "H4", -1.578f, 1.486f, -0.157f );
			residue.atom[ 20 ] = new Atom( "HO4", -0.209f, 3.075f, -1.048f );
			residue.atom[ 21 ] = new Atom( "H61", -0.628f, 3.945f, 2.527f );
			residue.atom[ 22 ] = new Atom( "H62", -1.871f, 3.536f, 1.352f );
			residue.atom[ 23 ] = new Atom( "H6", -0.630f, 5.337f, 0.750f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", 1.218f, 0.026f, 2.734f );
				residue.atom[ 13 ] = new Atom( "H1", -0.627f, -0.512f, 3.411f );
				residue.atom[ 14 ] = new Atom( "HO1", 1.610f, 0.462f, 1.952f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -0.750f, -0.642f, 3.637f );	
				residue.atom[ 13 ] = new Atom( "H1", 0.933f, 0.018f, 2.695f );
				residue.atom[ 14 ] = new Atom( "HO1", -1.716f, -0.647f, 3.488f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Altrose" ) ){
			createResidue( "ALT", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -0.167f, 2.160f, 1.365f );
			residue.atom[ 1 ] = new Atom( "O", -0.667f, 1.350f, 2.458f );
			residue.atom[ 2 ] = new Atom( "C1", -0.160f, -0.012f, 2.547f );
			residue.atom[ 4 ] = new Atom( "C2", -0.488f, -0.780f, 1.244f );
			residue.atom[ 5 ] = new Atom( "O2", -1.884f, -0.943f, 1.153f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.404f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.482f, 1.473f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", 0.133f, 2.163f, -1.062f );
			residue.atom[ 10 ] = new Atom( "C6", -0.789f, 3.579f, 1.507f );
			residue.atom[ 11 ] = new Atom( "O6", -0.208f, 4.489f, 0.599f );
			residue.atom[ 12 ] = new Atom( "H5", 0.929f, 2.264f, 1.471f );
			residue.atom[ 15 ] = new Atom( "H2", -0.016f, -1.783f, 1.271f );
			residue.atom[ 16 ] = new Atom( "HO2", -2.166f, -1.442f, 1.942f );
			residue.atom[ 17 ] = new Atom( "H3", -0.351f, -0.507f, -0.921f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, -0.933f, -0.021f );
			residue.atom[ 19 ] = new Atom( "H4", -1.578f, 1.486f, -0.157f );
			residue.atom[ 20 ] = new Atom( "HO4", -0.209f, 3.075f, -1.048f );
			residue.atom[ 21 ] = new Atom( "H61", -0.628f, 3.945f, 2.527f );
			residue.atom[ 22 ] = new Atom( "H62", -1.871f, 3.536f, 1.352f );
			residue.atom[ 23 ] = new Atom( "H6", -0.630f, 5.337f, 0.750f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", 1.218f, 0.026f, 2.734f );
				residue.atom[ 13 ] = new Atom( "H1", -0.627f, -0.512f, 3.411f );
				residue.atom[ 14 ] = new Atom( "HO1", 1.610f, 0.462f, 1.952f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -0.750f, -0.642f, 3.637f );
				residue.atom[ 13 ] = new Atom( "H1", 0.933f, 0.018f, 2.695f );
				residue.atom[ 14 ] = new Atom( "HO1", -1.716f, -0.647f, 3.488f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Arabinose" ) ){
			createResidue( "ARA", 20 );
			residue.atom[ 0 ] = new Atom( "C4", -0.577f, 1.416f, 0.000f );
			residue.atom[ 1 ] = new Atom( "O", -1.854f, 1.324f, 0.641f );
			residue.atom[ 2 ] = new Atom( "C1", -1.966f, 0.072f, 1.327f );
			residue.atom[ 4 ] = new Atom( "C2", -0.590f, -0.591f, 1.277f );
			residue.atom[ 5 ] = new Atom( "O2", -0.700f, -1.997f, 1.177f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.413f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C5", -0.715f, 2.048f, -1.388f );
			residue.atom[ 9 ] = new Atom( "O5", 0.551f, 2.198f, -1.995f );
			residue.atom[ 10 ] = new Atom( "H4", 0.050f, 2.058f, 0.636f );
			residue.atom[ 13 ] = new Atom( "H2", -0.001f, -0.309f, 2.161f );
			residue.atom[ 14 ] = new Atom( "HO2", 0.187f, -2.385f, 1.096f );
			residue.atom[ 15 ] = new Atom( "H3", -0.392f, -0.554f, -0.864f );
			residue.atom[ 16 ] = new Atom( "HO3", 1.735f, 0.506f, 0.765f );
			residue.atom[ 17 ] = new Atom( "H51", -1.178f, 3.031f, -1.285f );
			residue.atom[ 18 ] = new Atom( "H52", -1.347f, 1.417f, -2.014f );
			residue.atom[ 19 ] = new Atom( "H5", 0.430f, 2.596f, -2.860f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.374f, 0.319f, 2.633f );
				residue.atom[ 11 ] = new Atom( "H1", -2.715f, -0.528f, 0.790f );
				residue.atom[ 12 ] = new Atom( "HO1", -2.618f, -0.520f, 3.059f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.916f, -0.690f, 0.645f );
				residue.atom[ 11 ] = new Atom( "H1", -2.291f, 0.263f, 2.357f );
				residue.atom[ 12 ] = new Atom( "HO1", -3.787f, -0.271f, 0.737f );
			}
			addBackboneBonds2();
		}
		else if ( residueName.equals( "Fructose" ) ){
			createResidue( "FRU", 24 );
			if ( alpha ){
				residue.atom[ 0 ] = new Atom( "O", -0.449f, 0.577f, -2.248f );
				residue.atom[ 1 ] = new Atom( "C5", -0.532f, 1.817f, -1.517f );
				residue.atom[ 2 ] = new Atom( "C4", -0.458f, 1.473f, 0.000f );
				residue.atom[ 3 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
				residue.atom[ 4 ] = new Atom( "C2", -0.557f, -0.530f, -1.344f );
				residue.atom[ 5 ] = new Atom( "C6", 0.691f, 2.643f, -1.994f );
				residue.atom[ 6 ] = new Atom( "O4", -1.682f, 1.680f, 0.724f );
				residue.atom[ 7 ] = new Atom( "O3", 1.440f, 0.000f, 0.000f );
				residue.atom[ 8 ] = new Atom( "C1", 0.325f, -1.688f, -1.878f );
				residue.atom[ 9 ] = new Atom( "O1", 1.727f, -1.374f, -1.805f );
				residue.atom[ 10 ] = new Atom( "O6", 0.805f, 3.882f, -1.275f );
				residue.atom[ 11 ] = new Atom( "O2", -1.911f, -0.990f, -1.293f );
				residue.atom[ 12 ] = new Atom( "HO2", -2.469f, -0.281f, -0.964f );
				residue.atom[ 13 ] = new Atom( "H5", -1.449f, 2.399f, -1.695f );
				residue.atom[ 14 ] = new Atom( "H4", 0.338f, 2.075f, 0.463f );
				residue.atom[ 15 ] = new Atom( "H3", -0.359f, -0.646f, 0.815f );
				residue.atom[ 16 ] = new Atom( "H61", 1.603f, 2.050f, -1.831f );
				residue.atom[ 17 ] = new Atom( "H62", 0.582f, 2.844f, -3.070f );
				residue.atom[ 18 ] = new Atom( "HO4", -2.341f, 1.079f, 0.396f );
				residue.atom[ 19 ] = new Atom( "HO3", 1.749f, -0.898f, 0.004f );
				residue.atom[ 20 ] = new Atom( "H11", 0.128f, -2.585f, -1.273f );
				residue.atom[ 21 ] = new Atom( "H12", 0.040f, -1.890f, -2.922f );
				residue.atom[ 22 ] = new Atom( "HO1", 2.227f, -2.118f, -2.116f );
				residue.atom[ 23 ] = new Atom( "H6", 0.020f, 4.399f, -1.421f );
				residue.atom[ 0 ].addBond( 1, 4 );
				residue.atom[ 1 ].addBond( 2, 5, 13 );
				residue.atom[ 2 ].addBond( 3, 6, 14 );
				residue.atom[ 3 ].addBond( 4, 7, 15 );
				residue.atom[ 4 ].addBond( 8, 11 );
				residue.atom[ 5 ].addBond( 10, 16, 17 );
				residue.atom[ 6 ].addBond( 18 );
				residue.atom[ 7 ].addBond( 19 );
				residue.atom[ 8 ].addBond( 9, 20, 21 );
				residue.atom[ 9 ].addBond( 22 );
				residue.atom[ 10 ].addBond( 23 );
				residue.atom[ 11 ].addBond( 12 );
			}
			else{
				residue.atom[ 0 ] = new Atom( "O", -0.449f, 0.577f, -2.248f );
				residue.atom[ 1 ] = new Atom( "C5", -0.533f, 1.817f, -1.518f );
				residue.atom[ 2 ] = new Atom( "C4", -0.458f, 1.473f, 0.000f );
				residue.atom[ 3 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
				residue.atom[ 4 ] = new Atom( "C2", -0.557f, -0.530f, -1.344f );
				residue.atom[ 5 ] = new Atom( "H5", -1.439f, 2.383f, -1.774f );
				residue.atom[ 6 ] = new Atom( "C6", 0.691f, 2.643f, -1.994f );
				residue.atom[ 7 ] = new Atom( "O4", -1.682f, 1.681f, 0.724f );
				residue.atom[ 8 ] = new Atom( "H4", 0.263f, 2.115f, 0.531f );
				residue.atom[ 9 ] = new Atom( "H3", -0.378f, -0.553f, 0.875f );
				residue.atom[ 10 ] = new Atom( "O3", 1.440f, 0.000f, 0.000f );
				residue.atom[ 11 ] = new Atom( "C1", -2.025f, -1.028f, -1.289f );
				residue.atom[ 12 ] = new Atom( "O1", -2.941f, 0.032f, -0.964f );
				residue.atom[ 13 ] = new Atom( "O6", 0.805f, 3.882f, -1.275f );
				residue.atom[ 14 ] = new Atom( "O2", 0.256f, -1.598f, -1.837f );
				residue.atom[ 15 ] = new Atom( "H61", 1.614f, 2.068f, -1.830f );
				residue.atom[ 16 ] = new Atom( "H62", 0.601f, 2.842f, -3.077f );
				residue.atom[ 17 ] = new Atom( "HO4", -2.391f, 1.114f, 0.452f );
				residue.atom[ 18 ] = new Atom( "HO3", 1.809f, -0.877f, 0.005f );
				residue.atom[ 19 ] = new Atom( "H11", -2.284f, -1.432f, -2.282f );
				residue.atom[ 20 ] = new Atom( "H12", -2.103f, -1.839f, -0.546f );
				residue.atom[ 21 ] = new Atom( "HO1", -3.851f, -0.256f, -0.953f );
				residue.atom[ 22 ] = new Atom( "H6", 0.042f, 4.436f, -1.389f );
				residue.atom[ 23 ] = new Atom( "HO2", 1.169f, -1.303f, -1.877f );
				residue.atom[ 0 ].addBond( 1, 4 );
				residue.atom[ 1 ].addBond( 2, 5, 6 );
				residue.atom[ 2 ].addBond( 3, 7, 8 );
				residue.atom[ 3 ].addBond( 4, 9, 10 );
				residue.atom[ 4 ].addBond( 11, 14 );
				residue.atom[ 6 ].addBond( 13, 15, 16 );
				residue.atom[ 7 ].addBond( 17 );
				residue.atom[ 10 ].addBond( 18 );
				residue.atom[ 11 ].addBond( 12, 19, 20 );
				residue.atom[ 12 ].addBond( 21 );
				residue.atom[ 13 ].addBond( 22 );
				residue.atom[ 14 ].addBond( 23 );
			}
		}
		else if ( residueName.equals( "Galactose" ) ){
			createResidue( "GAL", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -2.070f, 1.496f, -0.189f );
			residue.atom[ 1 ] = new Atom( "O", -2.450f, 0.707f, -1.345f );
			residue.atom[ 2 ] = new Atom( "C1", -2.061f, -0.696f, -1.335f );
			residue.atom[ 4 ] = new Atom( "C2", -0.523f, -0.813f, -1.208f );
			residue.atom[ 5 ] = new Atom( "O2", -0.158f, -2.166f, -1.070f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.406f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.521f, 1.460f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", 0.078f, 2.150f, -1.071f );
			residue.atom[ 10 ] = new Atom( "C6", -2.640f, 2.930f, -0.389f );
			residue.atom[ 11 ] = new Atom( "O6", -2.484f, 3.712f, 0.775f );
			residue.atom[ 12 ] = new Atom( "H5", -2.552f, 1.067f, 0.709f );
			residue.atom[ 15 ] = new Atom( "H2", -0.057f, -0.413f, -2.131f );
			residue.atom[ 16 ] = new Atom( "HO2", 0.815f, -2.195f, -1.012f );
			residue.atom[ 17 ] = new Atom( "H3", -0.350f, -0.490f, 0.929f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, 0.496f, 0.792f );
			residue.atom[ 19 ] = new Atom( "H4", -0.228f, 1.954f, 0.948f );
			residue.atom[ 20 ] = new Atom( "HO4", 1.042f, 2.102f, -0.940f );
			residue.atom[ 21 ] = new Atom( "H61", -3.706f, 2.865f, -0.633f );
			residue.atom[ 22 ] = new Atom( "H62", -2.149f, 3.419f, -1.236f );
			residue.atom[ 23 ] = new Atom( "H6", -2.865f, 4.571f, 0.582f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.670f, -1.329f, -0.256f );
				residue.atom[ 13 ] = new Atom( "H1", -2.403f, -1.168f, -2.270f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.343f, -0.902f, 0.560f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.494f, -1.292f, -2.512f );
				residue.atom[ 13 ] = new Atom( "H1", -2.544f, -1.198f, -0.476f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.063f, -0.826f, -3.254f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Glucose" ) ){
			createResidue( "GLU", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -2.070f, 1.496f, -0.189f );
			residue.atom[ 1 ] = new Atom( "O", -2.450f, 0.707f, -1.345f );
			residue.atom[ 2 ] = new Atom( "C1", -2.061f, -0.696f, -1.335f );
			residue.atom[ 4 ] = new Atom( "C2", -0.523f, -0.813f, -1.208f );
			residue.atom[ 5 ] = new Atom( "O2", -0.158f, -2.166f, -1.070f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.406f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.521f, 1.460f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", -0.148f, 2.087f, 1.204f );
			residue.atom[ 10 ] = new Atom( "C6", -2.640f, 2.930f, -0.389f );
			residue.atom[ 11 ] = new Atom( "O6", -2.484f, 3.712f, 0.775f );
			residue.atom[ 12 ] = new Atom( "H5", -2.552f, 1.067f, 0.709f );
			residue.atom[ 15 ] = new Atom( "H2", -0.057f, -0.413f, -2.131f );
			residue.atom[ 16 ] = new Atom( "HO2", 0.815f, -2.195f, -1.012f );
			residue.atom[ 17 ] = new Atom( "H3", -0.350f, -0.490f, 0.929f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, 0.496f, 0.792f );
			residue.atom[ 19 ] = new Atom( "H4", -0.050f, 2.003f, -0.843f );
			residue.atom[ 20 ] = new Atom( "HO4", -0.469f, 3.005f, 1.161f );
			residue.atom[ 21 ] = new Atom( "H61", -3.706f, 2.865f, -0.633f );
			residue.atom[ 22 ] = new Atom( "H62", -2.149f, 3.419f, -1.236f );
			residue.atom[ 23 ] = new Atom( "H6", -2.865f, 4.571f, 0.582f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.670f, -1.329f, -0.256f );
				residue.atom[ 13 ] = new Atom( "H1", -2.403f, -1.168f, -2.270f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.343f, -0.902f, 0.560f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.494f, -1.292f, -2.512f );
				residue.atom[ 13 ] = new Atom( "H1", -2.544f, -1.198f, -0.476f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.063f, -0.826f, -3.254f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Gulose" ) ){
			createResidue( "GUL", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -0.167f, 2.160f, 1.365f );
			residue.atom[ 1 ] = new Atom( "O", -0.667f, 1.350f, 2.458f );
			residue.atom[ 2 ] = new Atom( "C1", -0.160f, -0.012f, 2.547f );
			residue.atom[ 4 ] = new Atom( "C2", -0.488f, -0.780f, 1.244f );
			residue.atom[ 5 ] = new Atom( "O2", 0.112f, -2.053f, 1.277f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.404f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.482f, 1.473f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", -1.875f, 1.489f, -0.200f );
			residue.atom[ 10 ] = new Atom( "C6", -0.789f, 3.579f, 1.507f );
			residue.atom[ 11 ] = new Atom( "O6", -0.208f, 4.489f, 0.599f );
			residue.atom[ 12 ] = new Atom( "H5", 0.929f, 2.264f, 1.471f );
			residue.atom[ 15 ] = new Atom( "H2", -1.587f, -0.908f, 1.172f );
			residue.atom[ 16 ] = new Atom( "HO2", -0.135f, -2.508f, 0.450f );
			residue.atom[ 17 ] = new Atom( "H3", -0.351f, -0.507f, -0.921f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, -0.933f, -0.021f );
			residue.atom[ 19 ] = new Atom( "H4", 0.002f, 2.017f, -0.836f );
			residue.atom[ 20 ] = new Atom( "HO4", -2.049f, 1.044f, -1.048f );
			residue.atom[ 21 ] = new Atom( "H61", -0.628f, 3.945f, 2.527f );
			residue.atom[ 22 ] = new Atom( "H62", -1.871f, 3.536f, 1.352f );
			residue.atom[ 23 ] = new Atom( "H6", -0.630f, 5.337f, 0.750f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", 1.218f, 0.026f, 2.734f );
				residue.atom[ 13 ] = new Atom( "H1", -0.627f, -0.512f, 3.411f );
				residue.atom[ 14 ] = new Atom( "HO1", 1.610f, 0.462f, 1.952f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -0.750f, -0.642f, 3.637f );
				residue.atom[ 13 ] = new Atom( "H1", 0.933f, 0.018f, 2.695f );
				residue.atom[ 14 ] = new Atom( "HO1", -1.716f, -0.647f, 3.488f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Idose" ) ){
			createResidue( "IDO", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -0.167f, 2.160f, 1.365f );
			residue.atom[ 1 ] = new Atom( "O", -0.667f, 1.350f, 2.458f );
			residue.atom[ 2 ] = new Atom( "C1", -0.160f, -0.012f, 2.547f );
			residue.atom[ 4 ] = new Atom( "C2", -0.488f, -0.780f, 1.244f );
			residue.atom[ 5 ] = new Atom( "O2", -1.884f, -0.943f, 1.153f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.404f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.482f, 1.473f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", -1.875f, 1.489f, -0.200f );
			residue.atom[ 10 ] = new Atom( "C6", -0.789f, 3.579f, 1.507f );
			residue.atom[ 11 ] = new Atom( "O6", -0.208f, 4.489f, 0.599f );
			residue.atom[ 12 ] = new Atom( "H5", 0.929f, 2.264f, 1.471f );
			residue.atom[ 15 ] = new Atom( "H2", -0.016f, -1.783f, 1.271f );
			residue.atom[ 16 ] = new Atom( "HO2", -2.166f, -1.442f, 1.942f );
			residue.atom[ 17 ] = new Atom( "H3", -0.351f, -0.507f, -0.921f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, -0.933f, -0.021f );
			residue.atom[ 19 ] = new Atom( "H4", 0.002f, 2.017f, -0.836f );
			residue.atom[ 20 ] = new Atom( "HO4", -2.049f, 1.044f, -1.048f );
			residue.atom[ 21 ] = new Atom( "H61", -0.628f, 3.945f, 2.527f );
			residue.atom[ 22 ] = new Atom( "H62", -1.871f, 3.536f, 1.352f );
			residue.atom[ 23 ] = new Atom( "H6", -0.630f, 5.337f, 0.750f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", 1.218f, 0.026f, 2.734f );
				residue.atom[ 13 ] = new Atom( "H1", -0.627f, -0.512f, 3.411f );
				residue.atom[ 14 ] = new Atom( "HO1", 1.610f, 0.462f, 1.952f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -0.750f, -0.642f, 3.637f );
				residue.atom[ 13 ] = new Atom( "H1", 0.933f, 0.018f, 2.695f );
				residue.atom[ 14 ] = new Atom( "HO1", -1.716f, -0.647f, 3.488f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Lyxose" ) ){
			createResidue( "LYX", 20 );
			residue.atom[ 0 ] = new Atom( "C4", -0.508f, 1.442f, 0.000f );
			residue.atom[ 1 ] = new Atom( "O", -0.510f, 1.862f, -1.369f );
			residue.atom[ 2 ] = new Atom( "C1", -0.379f, 0.724f, -2.228f );
			residue.atom[ 4 ] = new Atom( "C2", -0.496f, -0.519f, -1.346f );
			residue.atom[ 5 ] = new Atom( "O2", 0.330f, -1.564f, -1.819f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.412f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C5", 0.314f, 2.407f, 0.858f );
			residue.atom[ 9 ] = new Atom( "O5", 0.266f, 2.031f, 2.218f );
			residue.atom[ 10 ] = new Atom( "H4", -1.556f, 1.453f, 0.336f );
			residue.atom[ 13 ] = new Atom( "H2", -1.544f, -0.847f, -1.290f );
			residue.atom[ 14 ] = new Atom( "HO2", 0.273f, -2.320f, -1.211f );
			residue.atom[ 15 ] = new Atom( "H3", -0.391f, -0.578f, 0.850f );
			residue.atom[ 16 ] = new Atom( "HO3", 1.734f, 0.505f, -0.766f );
			residue.atom[ 17 ] = new Atom( "H51", -0.097f, 3.413f, 0.753f );
			residue.atom[ 18 ] = new Atom( "H52", 1.350f, 2.406f, 0.517f );
			residue.atom[ 19 ] = new Atom( "H5", 0.788f, 2.654f, 2.728f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -1.386f, 0.775f, -3.186f );
				residue.atom[ 11 ] = new Atom( "H1", 0.612f, 0.784f, -2.700f );
				residue.atom[ 12 ] = new Atom( "HO1", -1.211f, 0.110f, -3.873f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", 0.878f, 0.799f, -2.826f );
				residue.atom[ 11 ] = new Atom( "H1", -1.173f, 0.763f, -2.986f );
				residue.atom[ 12 ] = new Atom( "HO1", 0.904f, 1.569f, -3.418f );
			}
			addBackboneBonds2();
		}
		else if ( residueName.equals( "Mannose" ) ){
			createResidue( "MAN", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -2.070f, 1.496f, -0.189f );
			residue.atom[ 1 ] = new Atom( "O", -2.450f, 0.707f, -1.345f );
			residue.atom[ 2 ] = new Atom( "C1", -2.061f, -0.696f, -1.335f );
			residue.atom[ 4 ] = new Atom( "C2", -0.523f, -0.813f, -1.208f );
			residue.atom[ 5 ] = new Atom( "O2", 0.069f, -0.306f, -2.381f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.406f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.521f, 1.460f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", -0.148f, 2.087f, 1.204f );
			residue.atom[ 10 ] = new Atom( "C6", -2.640f, 2.930f, -0.389f );
			residue.atom[ 11 ] = new Atom( "O6", -2.484f, 3.712f, 0.775f );
			residue.atom[ 12 ] = new Atom( "H5", -2.552f, 1.067f, 0.709f );
			residue.atom[ 15 ] = new Atom( "H2", -0.236f, -1.879f, -1.100f );
			residue.atom[ 16 ] = new Atom( "HO2", -0.270f, -0.838f, -3.125f );
			residue.atom[ 17 ] = new Atom( "H3", -0.350f, -0.490f, 0.929f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, 0.496f, 0.792f );
			residue.atom[ 19 ] = new Atom( "H4", -0.050f, 2.003f, -0.843f );
			residue.atom[ 20 ] = new Atom( "HO4", -0.469f, 3.005f, 1.161f );
			residue.atom[ 21 ] = new Atom( "H61", -3.706f, 2.865f, -0.633f );
			residue.atom[ 22 ] = new Atom( "H62", -2.149f, 3.419f, -1.236f );
			residue.atom[ 23 ] = new Atom( "H6", -2.865f, 4.571f, 0.582f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.670f, -1.329f, -0.256f );
				residue.atom[ 13 ] = new Atom( "H1", -2.403f, -1.168f, -2.270f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.343f, -0.902f, 0.560f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.494f, -1.292f, -2.512f );
				residue.atom[ 13 ] = new Atom( "H1", -2.544f, -1.198f, -0.476f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.063f, -0.826f, -3.254f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Ribose" ) ){
			createResidue( "RIB", 20 );
			residue.atom[ 0 ] = new Atom( "C4", -0.577f, 1.416f, 0.000f );
			residue.atom[ 1 ] = new Atom( "O", -1.854f, 1.324f, 0.641f );
			residue.atom[ 2 ] = new Atom( "C1", -1.966f, 0.072f, 1.327f );
			residue.atom[ 4 ] = new Atom( "C2", -0.590f, -0.591f, 1.277f );
			residue.atom[ 5 ] = new Atom( "O2", 0.168f, -0.229f, 2.414f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.413f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C5", -0.715f, 2.048f, -1.388f );
			residue.atom[ 9 ] = new Atom( "O5", 0.551f, 2.198f, -1.995f );
			residue.atom[ 10 ] = new Atom( "H4", 0.050f, 2.058f, 0.636f );
			residue.atom[ 13 ] = new Atom( "H2", -0.676f, -1.685f, 1.199f );
			residue.atom[ 14 ] = new Atom( "HO2", -0.271f, -0.564f, 3.215f );
			residue.atom[ 15 ] = new Atom( "H3", -0.392f, -0.554f, -0.864f );
			residue.atom[ 16 ] = new Atom( "HO3", 1.735f, 0.506f, 0.765f );
			residue.atom[ 17 ] = new Atom( "H51", -1.178f, 3.031f, -1.285f );
			residue.atom[ 18 ] = new Atom( "H52", -1.347f, 1.417f, -2.014f );
			residue.atom[ 19 ] = new Atom( "H5", 0.430f, 2.596f, -2.860f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.374f, 0.319f, 2.633f );
				residue.atom[ 11 ] = new Atom( "H1", -2.715f, -0.528f, 0.790f );
				residue.atom[ 12 ] = new Atom( "HO1", -2.618f, -0.520f, 3.059f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.916f, -0.690f, 0.645f );
				residue.atom[ 11 ] = new Atom( "H1", -2.291f, 0.263f, 2.357f );
				residue.atom[ 12 ] = new Atom( "HO1", -3.787f, -0.271f, 0.737f );
			}
			addBackboneBonds2();
		}
		else if ( residueName.equals( "Talose" ) ){
			createResidue( "TAL", 24 );
			residue.atom[ 0 ] = new Atom( "C5", -2.070f, 1.496f, -0.189f );
			residue.atom[ 1 ] = new Atom( "O", -2.450f, 0.707f, -1.345f );
			residue.atom[ 2 ] = new Atom( "C1", -2.061f, -0.696f, -1.335f );
			residue.atom[ 4 ] = new Atom( "C2", -0.523f, -0.813f, -1.208f );
			residue.atom[ 5 ] = new Atom( "O2", 0.069f, -0.306f, -2.381f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.406f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C4", -0.521f, 1.460f, 0.000f );
			residue.atom[ 9 ] = new Atom( "O4", 0.078f, 2.150f, -1.071f );
			residue.atom[ 10 ] = new Atom( "C6", -2.640f, 2.930f, -0.389f );
			residue.atom[ 11 ] = new Atom( "O6", -2.484f, 3.712f, 0.775f );
			residue.atom[ 12 ] = new Atom( "H5", -2.552f, 1.067f, 0.709f );
			residue.atom[ 15 ] = new Atom( "H2", -0.236f, -1.879f, -1.100f );
			residue.atom[ 16 ] = new Atom( "HO2", -0.270f, -0.838f, -3.125f );
			residue.atom[ 17 ] = new Atom( "H3", -0.350f, -0.490f, 0.929f );
			residue.atom[ 18 ] = new Atom( "HO3", 1.690f, 0.496f, 0.792f );
			residue.atom[ 19 ] = new Atom( "H4", -0.228f, 1.954f, 0.948f );
			residue.atom[ 20 ] = new Atom( "HO4", 1.042f, 2.102f, -0.940f );
			residue.atom[ 21 ] = new Atom( "H61", -3.706f, 2.865f, -0.633f );
			residue.atom[ 22 ] = new Atom( "H62", -2.149f, 3.419f, -1.236f );
			residue.atom[ 23 ] = new Atom( "H6", -2.865f, 4.571f, 0.582f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -2.670f, -1.329f, -0.256f );
				residue.atom[ 13 ] = new Atom( "H1", -2.403f, -1.168f, -2.270f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.343f, -0.902f, 0.560f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", -2.494f, -1.292f, -2.512f );
				residue.atom[ 13 ] = new Atom( "H1", -2.544f, -1.198f, -0.476f );
				residue.atom[ 14 ] = new Atom( "HO1", -2.063f, -0.826f, -3.254f );
			}
			addBackboneBonds1();
		}
		else if ( residueName.equals( "Xylose" ) ){
			createResidue( "XYL", 20 );
			residue.atom[ 0 ] = new Atom( "C4", -0.508f, 1.442f, 0.000f );
			residue.atom[ 1 ] = new Atom( "O", -0.510f, 1.862f, -1.369f );
			residue.atom[ 2 ] = new Atom( "C1", -0.379f, 0.724f, -2.228f );
			residue.atom[ 4 ] = new Atom( "C2", -0.496f, -0.519f, -1.346f );
			residue.atom[ 5 ] = new Atom( "O2", -1.844f, -0.942f, -1.274f );
			residue.atom[ 6 ] = new Atom( "C3", 0.000f, 0.000f, 0.000f );
			residue.atom[ 7 ] = new Atom( "O3", 1.412f, 0.000f, 0.000f );
			residue.atom[ 8 ] = new Atom( "C5", 0.314f, 2.407f, 0.858f );
			residue.atom[ 9 ] = new Atom( "O5", 0.266f, 2.031f, 2.218f );
			residue.atom[ 10 ] = new Atom( "H4", -1.556f, 1.453f, 0.336f );
			residue.atom[ 13 ] = new Atom( "H2", 0.147f, -1.332f, -1.714f );
			residue.atom[ 14 ] = new Atom( "HO2", -2.148f, -1.202f, -2.160f );
			residue.atom[ 15 ] = new Atom( "H3", -0.391f, -0.578f, 0.850f );
			residue.atom[ 16 ] = new Atom( "HO3", 1.734f, 0.505f, -0.766f );
			residue.atom[ 17 ] = new Atom( "H51", -0.097f, 3.413f, 0.753f );
			residue.atom[ 18 ] = new Atom( "H52", 1.350f, 2.406f, 0.517f );
			residue.atom[ 19 ] = new Atom( "H5", 0.788f, 2.654f, 2.728f );
			if ( alpha ){
				residue.atom[ 3 ] = new Atom( "O1", -1.386f, 0.775f, -3.186f );
				residue.atom[ 11 ] = new Atom( "H1", 0.612f, 0.784f, -2.700f );
				residue.atom[ 12 ] = new Atom( "HO1", -1.211f, 0.110f, -3.873f );
			}
			else{
				residue.atom[ 3 ] = new Atom( "O1", 0.878f, 0.799f, -2.826f );
				residue.atom[ 11 ] = new Atom( "H1", -1.173f, 0.763f, -2.986f );
				residue.atom[ 12 ] = new Atom( "HO1", 0.904f, 1.569f, -3.418f );
			}
			addBackboneBonds2();
		}

		for ( int ix3, i = 0; i < residue.numberOfAtoms; i++ ){
			ix3 = i * 3;
			residue.actualCoordinates[ ix3 ] = residue.atom[ i ].coord[ 0 ];
			residue.actualCoordinates[ ix3 + 1 ] = residue.atom[ i ].coord[ 1 ];
			residue.actualCoordinates[ ix3 + 2 ] = residue.atom[ i ].coord[ 2 ];
			residue.atom[ i ].residueAtomNumber = i;
		}
		return( residue );
	}

	public void createResidue( String residueName, int numberOfResidueAtoms ){
		residue = new Residue();
		residue.name = residueName;
		residue.numberOfAtoms = numberOfResidueAtoms;
		residue.atom = new Atom[ numberOfResidueAtoms ];
		residue.actualCoordinates = new float[ 3 * residue.numberOfAtoms ];
		residue.mat = new Matrix3D();
		residue.totalResidueNumber = 0;
	}

	public void addBackboneBonds1(){
		residue.atom[ 0 ].addBond( 1, 8, 10, 12 );
		residue.atom[ 1 ].addBond( 2 );
		residue.atom[ 2 ].addBond( 3, 4, 13 );
		residue.atom[ 3 ].addBond( 14 );
		residue.atom[ 4 ].addBond( 5, 6, 15 );
		residue.atom[ 5 ].addBond( 16 );
		residue.atom[ 6 ].addBond( 7, 8, 17 );
		residue.atom[ 7 ].addBond( 18 );
		residue.atom[ 8 ].addBond( 9, 19 );
		residue.atom[ 9 ].addBond( 20 );
		residue.atom[ 10 ].addBond( 11, 21, 22 );
		residue.atom[ 11 ].addBond( 23 );
	}

	public void addBackboneBonds2(){
		residue.atom[ 0 ].addBond( 1, 6, 8, 10 );
		residue.atom[ 1 ].addBond( 2 );
		residue.atom[ 2 ].addBond( 3, 4, 11 );
		residue.atom[ 3 ].addBond( 12 );
		residue.atom[ 4 ].addBond( 5, 6, 13 );
		residue.atom[ 5 ].addBond( 14 );
		residue.atom[ 6 ].addBond( 7, 15 );
		residue.atom[ 7 ].addBond( 16 );
		residue.atom[ 8 ].addBond( 9, 17, 18 );
		residue.atom[ 9 ].addBond( 19 );
	}

}
