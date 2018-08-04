package com.goosebumpanalytics.biomer;

import java.awt.*;

public class NucleicAcidResidue {

	final int dADE = 0, dCYT = 1, dGUA = 2, dTHY = 3, rADE = 4, rCYT = 5, rGUA = 6;
	final int rURA = 7, r1MA = 8, r2MG = 9, r5MC = 10, r5MU = 11, r7MG = 12, r1MG = 20;
	final int rH2U = 13, rI = 14, rM2G = 15, rOMC = 16, rOMG = 17, rPSU = 18, rY = 19;
	final int dURA = 20, rTHY = 21, d1MA = 22, d2MG = 23, d5MC = 24, d5MU = 25, d7MG = 26;
	final int dH2U = 27, dI = 28, dM2G = 29, dOMC = 30, dOMG = 31, dPSU = 32, dY = 33;
	final int d1MG = 34;
	Residue residue;

	Residue getResidue( int res ){
		if ( res == dADE ){
			createResidue( "ADE", 32 );	
			residue.atom[ 0 ] = new Atom( "P", 0.130f, -4.768f, 1.423f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.137f, -5.477f, 1.133f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.326f, -5.261f, 0.703f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.056f, -3.204f, 1.147f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.405f, 2.111f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.527f, -3.016f, 2.598f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.065f, -2.031f, 2.855f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.226f, 1.419f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.236f, -0.840f, 2.047f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.245f, 0.991f, -0.381f, 0.009f );
			residue.atom[ 12 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.233f );
			residue.atom[ 13 ] = new Atom( "H3'", -2.275f, -2.474f, -0.111f, 0.025f );
			residue.atom[ 14 ] = new Atom( "C2'", -0.605f, -1.139f, -0.824f, -0.307f );
			residue.atom[ 15 ] = new Atom( "H2'1", -0.294f, -2.017f, -1.388f, 0.081f );
			residue.atom[ 16 ] = new Atom( "H2'2", -0.809f, -0.318f, -1.512f, 0.081f );
			residue.atom[ 17 ] = new Atom( "O3'", -2.909f, -0.631f, -0.522f, -0.509f );
			residue.atom[ 18 ] = new Atom( "N9", 1.520f, 0.000f, 0.000f, -0.073f );
			residue.atom[ 19 ] = new Atom( "C8", 2.423f, -1.030f, 0.000f, 0.263f );
			residue.atom[ 20 ] = new Atom( "H8", 2.075f, -2.052f, -0.001f, 0.062f );
			residue.atom[ 21 ] = new Atom( "N7", 3.663f, -0.645f, -0.063f, -0.543f );
			residue.atom[ 22 ] = new Atom( "C5", 3.583f, 0.748f, -0.061f, -0.097f );
			residue.atom[ 23 ] = new Atom( "C6", 4.559f, 1.750f, -0.112f, 0.769f );
			residue.atom[ 24 ] = new Atom( "N6", 5.873f, 1.499f, -0.177f, -0.768f );
			residue.atom[ 25 ] = new Atom( "H61", 6.206f, 0.546f, -0.192f, 0.324f );
			residue.atom[ 26 ] = new Atom( "H62", 6.532f, 2.264f, -0.212f, 0.335f );
			residue.atom[ 27 ] = new Atom( "N1", 4.138f, 3.022f, -0.094f, -0.774f );
			residue.atom[ 28 ] = new Atom( "C2", 2.832f, 3.265f, -0.029f, 0.661f );
			residue.atom[ 29 ] = new Atom( "H2", 2.472f, 4.282f, -0.014f, -0.032f );
			residue.atom[ 30 ] = new Atom( "N3", 1.825f, 2.408f, 0.001f, -0.728f );
			residue.atom[ 31 ] = new Atom( "C4", 2.277f, 1.136f, 0.000f, 0.546f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 12 );
			residue.atom[ 10 ].addBond( 14, 18 );
			residue.atom[ 12 ].addBond( 13, 14, 17 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 18 ].addBond( 19, 31 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23, 31 );
			residue.atom[ 23 ].addBond( 24, 27 );
			residue.atom[ 24 ].addBond( 25, 26 );
			residue.atom[ 27 ].addBond( 28 );
			residue.atom[ 28 ].addBond( 29, 30 );
			residue.atom[ 30 ].addBond( 31 );
		}
		else if ( res == dCYT ){
			createResidue( "CYT", 30 );	
			residue.atom[ 0 ] = new Atom( "P", 0.178f, -4.768f, 1.420f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.094f, -5.480f, 1.164f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.357f, -5.257f, 0.669f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.018f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.707f, -2.406f, 2.131f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.452f, -3.019f, 2.638f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", 0.014f, -2.030f, 2.856f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.393f, -1.229f, 1.456f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.264f, -1.035f, 2.082f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.440f, -0.128f, 1.344f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.335f, 0.955f, -0.402f, 0.009f );
			residue.atom[ 12 ] = new Atom( "N1", 1.490f, 0.000f, 0.000f, -0.187f );
			residue.atom[ 13 ] = new Atom( "C6", 2.193f, -1.165f, -0.001f, 0.185f );
			residue.atom[ 14 ] = new Atom( "H6", 1.671f, -2.110f, -0.001f, 0.098f );
			residue.atom[ 15 ] = new Atom( "C5", 3.553f, -1.162f, -0.001f, -0.576f );
			residue.atom[ 16 ] = new Atom( "H5", 4.099f, -2.105f, -0.001f, 0.153f );
			residue.atom[ 17 ] = new Atom( "C4", 4.198f, 0.114f, -0.002f, 0.935f );
			residue.atom[ 18 ] = new Atom( "N4", 5.515f, 0.191f, -0.002f, -0.834f );
			residue.atom[ 19 ] = new Atom( "H41", 6.035f, -0.675f, -0.002f, 0.329f );
			residue.atom[ 20 ] = new Atom( "H42", 5.973f, 1.091f, -0.002f, 0.351f );
			residue.atom[ 21 ] = new Atom( "N3", 3.502f, 1.272f, -0.001f, -0.860f );
			residue.atom[ 22 ] = new Atom( "C2", 2.143f, 1.226f, -0.001f, 0.859f );
			residue.atom[ 23 ] = new Atom( "O2", 1.443f, 2.249f, 0.000f, -0.508f );
			residue.atom[ 24 ] = new Atom( "C3'", -1.868f, -1.469f, 0.021f, 0.233f );
			residue.atom[ 25 ] = new Atom( "H3'", -2.271f, -2.479f, -0.051f, 0.025f );
			residue.atom[ 26 ] = new Atom( "C2'", -0.624f, -1.140f, -0.807f, -0.307f );
			residue.atom[ 27 ] = new Atom( "H2'1", -0.325f, -2.018f, -1.379f, 0.081f );
			residue.atom[ 28 ] = new Atom( "H2'2", -0.847f, -0.321f, -1.490f, 0.081f );
			residue.atom[ 29 ] = new Atom( "O3'", -2.920f, -0.638f, -0.446f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 24 );
			residue.atom[ 10 ].addBond( 12, 26 );
			residue.atom[ 12 ].addBond( 13, 22 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 17 );
			residue.atom[ 17 ].addBond( 18, 21 );
			residue.atom[ 18 ].addBond( 19, 20 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23 );
			residue.atom[ 24 ].addBond( 25, 26, 29 );
			residue.atom[ 26 ].addBond( 27, 28 );
		}
		else if ( res == dGUA ){
			createResidue( "GUA", 33 );	
			residue.atom[ 0 ] = new Atom( "P", 0.179f, -4.768f, 1.420f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.093f, -5.479f, 1.165f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.357f, -5.257f, 0.668f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.017f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.705f, -2.406f, 2.131f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.450f, -3.019f, 2.639f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", 0.016f, -2.030f, 2.856f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.392f, -1.229f, 1.457f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.263f, -1.035f, 2.084f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.439f, -0.128f, 1.344f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.335f, 0.955f, -0.402f, 0.009f );
			residue.atom[ 12 ] = new Atom( "N9", 1.490f, 0.000f, 0.000f, -0.042f );
			residue.atom[ 13 ] = new Atom( "C8", 2.363f, -1.069f, 0.000f, 0.266f );
			residue.atom[ 14 ] = new Atom( "H8", 1.979f, -2.079f, 0.000f, 0.046f );
			residue.atom[ 15 ] = new Atom( "N7", 3.627f, -0.726f, 0.002f, -0.543f );
			residue.atom[ 16 ] = new Atom( "C5", 3.595f, 0.664f, 0.004f, -0.060f );
			residue.atom[ 17 ] = new Atom( "C6", 4.655f, 1.609f, 0.006f, 0.690f );
			residue.atom[ 18 ] = new Atom( "O6", 5.868f, 1.405f, 0.007f, -0.458f );
			residue.atom[ 19 ] = new Atom( "N1", 4.169f, 2.922f, 0.009f, -0.729f );
			residue.atom[ 20 ] = new Atom( "H1", 4.842f, 3.661f, 0.012f, 0.336f );
			residue.atom[ 21 ] = new Atom( "C2", 2.835f, 3.276f, 0.006f, 0.871f );
			residue.atom[ 22 ] = new Atom( "N2", 2.577f, 4.592f, 0.008f, -0.778f );
			residue.atom[ 23 ] = new Atom( "H21", 3.252f, 5.344f, 0.023f, 0.325f );
			residue.atom[ 24 ] = new Atom( "H22", 1.604f, 4.861f, 0.015f, 0.339f );
			residue.atom[ 25 ] = new Atom( "N3", 1.844f, 2.390f, 0.003f, -0.709f );
			residue.atom[ 26 ] = new Atom( "C4", 2.299f, 1.109f, 0.000f, 0.391f );
			residue.atom[ 27 ] = new Atom( "C3'", -1.868f, -1.469f, 0.022f, 0.233f );
			residue.atom[ 28 ] = new Atom( "H3'", -2.271f, -2.479f, -0.049f, 0.025f );
			residue.atom[ 29 ] = new Atom( "C2'", -0.624f, -1.140f, -0.807f, -0.307f );
			residue.atom[ 30 ] = new Atom( "H2'1", -0.326f, -2.018f, -1.379f, 0.081f );
			residue.atom[ 31 ] = new Atom( "H2'2", -0.848f, -0.321f, -1.489f, 0.081f );
			residue.atom[ 32 ] = new Atom( "O3'", -2.921f, -0.638f, -0.444f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 27 );
			residue.atom[ 10 ].addBond( 12, 29 );
			residue.atom[ 12 ].addBond( 13, 26 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 26 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 25 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 25 ].addBond( 26 );
			residue.atom[ 27 ].addBond( 28, 29, 32 );
		}
		if ( res == dTHY ){
			createResidue( "THY", 32 );	
			residue.atom[ 0 ] = new Atom( "P", 0.131f, -4.768f, 1.425f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.136f, -5.477f, 1.136f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.327f, -5.260f, 0.705f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.055f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.404f, 2.112f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.526f, -3.015f, 2.600f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.064f, -2.030f, 2.856f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.225f, 1.420f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.235f, -0.839f, 2.048f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.322f, 0.956f, -0.411f, 0.009f );
			residue.atom[ 12 ] = new Atom( "N1", 1.531f, 0.000f, 0.000f, -0.217f );
			residue.atom[ 13 ] = new Atom( "C6", 2.277f, -1.148f, -0.001f, 0.034f );
			residue.atom[ 14 ] = new Atom( "H6", 1.789f, -2.110f, 0.000f, 0.134f );
			residue.atom[ 15 ] = new Atom( "C5", 3.615f, -1.106f, -0.055f, -0.176f );
			residue.atom[ 16 ] = new Atom( "C7", 4.443f, -2.357f, -0.055f, -0.382f );
			residue.atom[ 17 ] = new Atom( "H71", 4.180f, -2.966f, -0.920f, 0.114f );
			residue.atom[ 18 ] = new Atom( "H72", 5.500f, -2.095f, -0.104f, 0.114f );
			residue.atom[ 19 ] = new Atom( "H73", 4.251f, -2.920f, 0.858f, 0.114f );
			residue.atom[ 20 ] = new Atom( "C4", 4.311f, 0.154f, -0.115f, 0.809f );
			residue.atom[ 21 ] = new Atom( "O4", 5.532f, 0.291f, -0.167f, -0.464f );
			residue.atom[ 22 ] = new Atom( "N3", 3.476f, 1.373f, -0.002f, -0.851f );
			residue.atom[ 23 ] = new Atom( "H3", 3.996f, 2.330f, -0.046f, 0.355f );
			residue.atom[ 24 ] = new Atom( "C2", 2.103f, 1.246f, -0.001f, 0.849f );
			residue.atom[ 25 ] = new Atom( "O2", 1.368f, 2.216f, 0.091f, -0.488f );
			residue.atom[ 26 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.233f );
			residue.atom[ 27 ] = new Atom( "H3'", -2.275f, -2.474f, -0.109f, 0.025f );
			residue.atom[ 28 ] = new Atom( "C2'", -0.605f, -1.139f, -0.823f, -0.307f );
			residue.atom[ 29 ] = new Atom( "H2'1", -0.294f, -2.017f, -1.387f, 0.081f );
			residue.atom[ 30 ] = new Atom( "H2'2", -0.809f, -0.319f, -1.511f, 0.081f );
			residue.atom[ 31 ] = new Atom( "O3'", -2.909f, -0.632f, -0.521f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 26 );
			residue.atom[ 10 ].addBond( 12, 28 );
			residue.atom[ 12 ].addBond( 13, 24 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 20 );
			residue.atom[ 16 ].addBond( 17, 18, 19 );
			residue.atom[ 20 ].addBond( 21, 22 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25 );
			residue.atom[ 26 ].addBond( 27, 28, 31 );
			residue.atom[ 28 ].addBond( 29, 30 );
		}
		else if ( res == rADE ){
			createResidue( "ADE", 33 );	
			residue.atom[ 0 ] = new Atom( "P", 0.130f, -4.768f, 1.423f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.137f, -5.477f, 1.133f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.326f, -5.261f, 0.703f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.056f, -3.204f, 1.147f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.405f, 2.111f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.527f, -3.016f, 2.598f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.065f, -2.031f, 2.855f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.226f, 1.419f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.320f, -1.030f, 2.022f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.322f, 0.956f, -0.410f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.520f, 0.000f, 0.000f, -0.073f );
			residue.atom[ 13 ] = new Atom( "C8", 2.423f, -1.030f, 0.000f, 0.263f );
			residue.atom[ 14 ] = new Atom( "H8", 2.075f, -2.052f, -0.001f, 0.062f );
			residue.atom[ 15 ] = new Atom( "N7", 3.663f, -0.645f, -0.063f, -0.543f );
			residue.atom[ 16 ] = new Atom( "C5", 3.583f, 0.748f, -0.061f, -0.097f );
			residue.atom[ 17 ] = new Atom( "C6", 4.559f, 1.750f, -0.112f, 0.769f );
			residue.atom[ 18 ] = new Atom( "N6", 5.873f, 1.499f, -0.177f, -0.768f );
			residue.atom[ 19 ] = new Atom( "H61", 6.206f, 0.546f, -0.192f, 0.324f );
			residue.atom[ 20 ] = new Atom( "H62", 6.532f, 2.264f, -0.212f, 0.335f );
			residue.atom[ 21 ] = new Atom( "N1", 4.138f, 3.022f, -0.094f, -0.774f );
			residue.atom[ 22 ] = new Atom( "C2", 2.832f, 3.265f, -0.029f, 0.661f );
			residue.atom[ 23 ] = new Atom( "H2", 2.472f, 4.282f, -0.014f, -0.032f );
			residue.atom[ 24 ] = new Atom( "N3", 1.825f, 2.408f, 0.001f, -0.728f );
			residue.atom[ 25 ] = new Atom( "C4", 2.277f, 1.136f, 0.000f, 0.546f );
			residue.atom[ 26 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.233f );
			residue.atom[ 27 ] = new Atom( "H3'", -2.275f, -2.474f, -0.111f, 0.007f );
			residue.atom[ 28 ] = new Atom( "C2'", -0.605f, -1.139f, -0.824f, 0.101f );
			residue.atom[ 29 ] = new Atom( "H2'", -0.294f, -2.017f, -1.388f, 0.008f );
			residue.atom[ 30 ] = new Atom( "O2'", -0.873f, -0.063f, -1.727f, -0.546f );
			residue.atom[ 31 ] = new Atom( "HO2", -1.320f, -0.402f, -2.505f, -0.324f );
			residue.atom[ 32 ] = new Atom( "O3'", -2.909f, -0.631f, -0.522f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 26 );
			residue.atom[ 10 ].addBond( 12, 28 );
			residue.atom[ 12 ].addBond( 13, 25 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 25 );
			residue.atom[ 17 ].addBond( 18, 21 );
			residue.atom[ 18 ].addBond( 19, 20 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25 );
			residue.atom[ 26 ].addBond( 27, 28, 32 );
			residue.atom[ 28 ].addBond( 29, 30 );
			residue.atom[ 30 ].addBond( 31 );
		}
		else if ( res == rCYT ){
			createResidue( "CYT", 31 );	
			residue.atom[ 0 ] = new Atom( "P", 0.178f, -4.768f, 1.420f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.094f, -5.480f, 1.164f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.357f, -5.257f, 0.669f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.018f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.707f, -2.406f, 2.131f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.452f, -3.019f, 2.638f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", 0.014f, -2.030f, 2.856f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.393f, -1.229f, 1.456f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.264f, -1.035f, 2.082f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.440f, -0.128f, 1.344f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.177f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.335f, 0.955f, -0.402f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.490f, 0.000f, 0.000f, -0.187f );
			residue.atom[ 13 ] = new Atom( "C6", 2.193f, -1.165f, -0.001f, 0.185f );
			residue.atom[ 14 ] = new Atom( "H6", 1.671f, -2.110f, -0.001f, 0.098f );
			residue.atom[ 15 ] = new Atom( "C5", 3.553f, -1.162f, -0.001f, -0.576f );
			residue.atom[ 16 ] = new Atom( "H5", 4.099f, -2.105f, -0.001f, 0.153f );
			residue.atom[ 17 ] = new Atom( "C4", 4.198f, 0.114f, -0.002f, 0.935f );
			residue.atom[ 18 ] = new Atom( "N4", 5.515f, 0.191f, -0.002f, -0.834f );
			residue.atom[ 19 ] = new Atom( "H41", 6.035f, -0.675f, -0.002f, 0.329f );
			residue.atom[ 20 ] = new Atom( "H42", 5.973f, 1.091f, -0.002f, 0.351f );
			residue.atom[ 21 ] = new Atom( "N3", 3.502f, 1.272f, -0.001f, -0.860f );
			residue.atom[ 22 ] = new Atom( "C2", 2.143f, 1.226f, -0.001f, 0.859f );
			residue.atom[ 23 ] = new Atom( "O2", 1.443f, 2.249f, 0.000f, -0.508f );
			residue.atom[ 24 ] = new Atom( "C3'", -1.868f, -1.469f, 0.021f, 0.303f );
			residue.atom[ 25 ] = new Atom( "H3'", -2.271f, -2.479f, -0.051f, 0.007f );
			residue.atom[ 26 ] = new Atom( "C2'", -0.624f, -1.140f, -0.807f, 0.101f );
			residue.atom[ 27 ] = new Atom( "H2'", -0.325f, -2.018f, -1.379f, 0.008f );
			residue.atom[ 28 ] = new Atom( "O2'", -0.918f, -0.065f, -1.703f, -0.546f );
			residue.atom[ 29 ] = new Atom( "HO2", -1.384f, -0.406f, -2.470f, -0.324f );
			residue.atom[ 30 ] = new Atom( "O3'", -2.920f, -0.638f, -0.446f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 24 );
			residue.atom[ 10 ].addBond( 12, 26 );
			residue.atom[ 12 ].addBond( 13, 22 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 17 );
			residue.atom[ 17 ].addBond( 18, 21 );
			residue.atom[ 18 ].addBond( 19, 20 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23 );
			residue.atom[ 24 ].addBond( 25, 26, 30 );
			residue.atom[ 26 ].addBond( 27, 28 );
			residue.atom[ 28 ].addBond( 29 );
		}
		else if ( res == rGUA ){
			createResidue( "GUA", 34 );	
			residue.atom[ 0 ] = new Atom( "P", 0.179f, -4.768f, 1.420f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.093f, -5.479f, 1.165f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.357f, -5.257f, 0.668f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.017f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.705f, -2.406f, 2.131f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.450f, -3.019f, 2.639f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", 0.016f, -2.030f, 2.856f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.392f, -1.229f, 1.457f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.263f, -1.035f, 2.084f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.439f, -0.128f, 1.344f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.335f, 0.955f, -0.402f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.490f, 0.000f, 0.000f, -0.042f );
			residue.atom[ 13 ] = new Atom( "C8", 2.363f, -1.069f, 0.000f, 0.266f );
			residue.atom[ 14 ] = new Atom( "H8", 1.979f, -2.079f, 0.000f, 0.046f );
			residue.atom[ 15 ] = new Atom( "N7", 3.627f, -0.726f, 0.002f, -0.543f );
			residue.atom[ 16 ] = new Atom( "C5", 3.595f, 0.664f, 0.004f, -0.060f );
			residue.atom[ 17 ] = new Atom( "C6", 4.655f, 1.609f, 0.006f, 0.690f );
			residue.atom[ 18 ] = new Atom( "O6", 5.868f, 1.405f, 0.007f, -0.458f );
			residue.atom[ 19 ] = new Atom( "N1", 4.169f, 2.922f, 0.009f, -0.729f );
			residue.atom[ 20 ] = new Atom( "H1", 4.842f, 3.661f, 0.012f, 0.336f );
			residue.atom[ 21 ] = new Atom( "C2", 2.835f, 3.276f, 0.006f, 0.871f );
			residue.atom[ 22 ] = new Atom( "N2", 2.577f, 4.592f, 0.008f, -0.778f );
			residue.atom[ 23 ] = new Atom( "H21", 3.252f, 5.344f, 0.023f, 0.325f );
			residue.atom[ 24 ] = new Atom( "H22", 1.604f, 4.861f, 0.015f, 0.339f );
			residue.atom[ 25 ] = new Atom( "N3", 1.844f, 2.390f, 0.003f, -0.709f );
			residue.atom[ 26 ] = new Atom( "C4", 2.299f, 1.109f, 0.000f, 0.391f );
			residue.atom[ 27 ] = new Atom( "C3'", -1.868f, -1.469f, 0.022f, 0.303f );
			residue.atom[ 28 ] = new Atom( "H3'", -2.271f, -2.479f, -0.049f, 0.007f );
			residue.atom[ 29 ] = new Atom( "C2'", -0.624f, -1.140f, -0.807f, 0.101f );
			residue.atom[ 30 ] = new Atom( "H2'", -0.326f, -2.018f, -1.379f, 0.008f );
			residue.atom[ 31 ] = new Atom( "O2'", -0.919f, -0.065f, -1.703f, -0.546f );
			residue.atom[ 32 ] = new Atom( "HO2", -1.385f, -0.406f, -2.469f, -0.324f );
			residue.atom[ 33 ] = new Atom( "O3'", -2.921f, -0.638f, -0.444f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 27 );
			residue.atom[ 10 ].addBond( 12, 29 );
			residue.atom[ 12 ].addBond( 13, 26 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 26 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 25 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 25 ].addBond( 26 );
			residue.atom[ 27 ].addBond( 28, 29, 33 );
			residue.atom[ 29 ].addBond( 30, 31 );
			residue.atom[ 31 ].addBond( 32 );
		}
		if ( res == rURA ){
			createResidue( "URA", 30 );	
			residue.atom[ 0 ] = new Atom( "P", 0.131f, -4.768f, 1.425f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.136f, -5.477f, 1.136f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.327f, -5.260f, 0.705f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.055f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.404f, 2.112f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.526f, -3.015f, 2.600f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.064f, -2.030f, 2.856f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.225f, 1.420f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.320f, -1.029f, 2.023f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.322f, 0.956f, -0.411f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.531f, 0.000f, 0.000f, -0.159f );
			residue.atom[ 13 ] = new Atom( "C6", 2.277f, -1.148f, -0.001f, 0.160f );
			residue.atom[ 14 ] = new Atom( "H6", 1.789f, -2.110f, 0.000f, 0.098f );
			residue.atom[ 15 ] = new Atom( "C5", 3.615f, -1.106f, -0.055f, -0.529f );
			residue.atom[ 16 ] = new Atom( "H5", 4.190f, -2.031f, -0.054f, 0.146f );
			residue.atom[ 17 ] = new Atom( "C4", 4.311f, 0.154f, -0.115f, 0.834f );
			residue.atom[ 18 ] = new Atom( "O4", 5.532f, 0.291f, -0.167f, -0.474f );
			residue.atom[ 19 ] = new Atom( "N3", 3.476f, 1.373f, -0.002f, -0.768f );
			residue.atom[ 20 ] = new Atom( "H3", 3.996f, 2.330f, -0.046f, 0.334f );
			residue.atom[ 21 ] = new Atom( "C2", 2.103f, 1.246f, -0.001f, 0.775f );
			residue.atom[ 22 ] = new Atom( "O2", 1.368f, 2.216f, 0.091f, -0.472f );
			residue.atom[ 23 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.303f );
			residue.atom[ 24 ] = new Atom( "H3'", -2.275f, -2.474f, -0.109f, 0.007f );
			residue.atom[ 25 ] = new Atom( "C2'", -0.605f, -1.139f, -0.823f, 0.101f );
			residue.atom[ 26 ] = new Atom( "H2'", -0.294f, -2.017f, -1.387f, 0.008f );
			residue.atom[ 27 ] = new Atom( "O2'", -0.873f, -0.064f, -1.726f, -0.546f );
			residue.atom[ 28 ] = new Atom( "HO2", -1.320f, -0.403f, -2.505f, -0.324f );
			residue.atom[ 29 ] = new Atom( "O3'", -2.909f, -0.632f, -0.521f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 23 );
			residue.atom[ 10 ].addBond( 12, 25 );
			residue.atom[ 12 ].addBond( 13, 21 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 17 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 23 ].addBond( 24, 25, 29 );
			residue.atom[ 25 ].addBond( 26, 27 );
			residue.atom[ 27 ].addBond( 28 );
		}
		else if ( res == dURA ){
			createResidue( "URA", 29 );	
			residue.atom[ 0 ] = new Atom( "P", 0.131f, -4.768f, 1.425f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.136f, -5.477f, 1.136f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.327f, -5.260f, 0.705f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.055f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.404f, 2.112f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.526f, -3.015f, 2.600f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.064f, -2.030f, 2.856f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.225f, 1.420f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.320f, -1.029f, 2.023f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.322f, 0.956f, -0.411f, 0.009f );
			residue.atom[ 12 ] = new Atom( "N1", 1.531f, 0.000f, 0.000f, -0.159f );
			residue.atom[ 13 ] = new Atom( "C6", 2.277f, -1.148f, -0.001f, 0.160f );
			residue.atom[ 14 ] = new Atom( "H6", 1.789f, -2.110f, 0.000f, 0.098f );
			residue.atom[ 15 ] = new Atom( "C5", 3.615f, -1.106f, -0.055f, -0.529f );
			residue.atom[ 16 ] = new Atom( "H5", 4.190f, -2.031f, -0.054f, 0.146f );
			residue.atom[ 17 ] = new Atom( "C4", 4.311f, 0.154f, -0.115f, 0.834f );
			residue.atom[ 18 ] = new Atom( "O4", 5.532f, 0.291f, -0.167f, -0.474f );
			residue.atom[ 19 ] = new Atom( "N3", 3.476f, 1.373f, -0.002f, -0.768f );
			residue.atom[ 20 ] = new Atom( "H3", 3.996f, 2.330f, -0.046f, 0.334f );
			residue.atom[ 21 ] = new Atom( "C2", 2.103f, 1.246f, -0.001f, 0.775f );
			residue.atom[ 22 ] = new Atom( "O2", 1.368f, 2.216f, 0.091f, -0.472f );
			residue.atom[ 23 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.233f );
			residue.atom[ 24 ] = new Atom( "H3'", -2.275f, -2.474f, -0.109f, 0.025f );
			residue.atom[ 25 ] = new Atom( "C2'", -0.605f, -1.139f, -0.823f, -0.307f );
			residue.atom[ 26 ] = new Atom( "H2'1", -0.294f, -2.017f, -1.387f, 0.081f );
			residue.atom[ 27 ] = new Atom( "H2'2", -0.873f, -0.064f, -1.726f, 0.081f );
			residue.atom[ 28 ] = new Atom( "O3'", -2.909f, -0.632f, -0.521f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 23 );
			residue.atom[ 10 ].addBond( 12, 25 );
			residue.atom[ 12 ].addBond( 13, 21 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 17 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 23 ].addBond( 24, 25, 28 );
			residue.atom[ 25 ].addBond( 26, 27 );
		}
		else if ( res == rTHY ){
			createResidue( "THY", 33 );	
			residue.atom[ 0 ] = new Atom( "P", 0.131f, -4.768f, 1.425f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.136f, -5.477f, 1.136f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.327f, -5.260f, 0.705f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.055f, -3.203f, 1.149f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -0.767f, -2.404f, 2.112f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.526f, -3.015f, 2.600f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -0.064f, -2.030f, 2.856f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.433f, -1.225f, 1.420f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.235f, -0.839f, 2.048f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.475f, -0.127f, 1.332f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.322f, 0.956f, -0.411f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.531f, 0.000f, 0.000f, -0.217f );
			residue.atom[ 13 ] = new Atom( "C6", 2.277f, -1.148f, -0.001f, 0.034f );
			residue.atom[ 14 ] = new Atom( "H6", 1.789f, -2.110f, 0.000f, 0.134f );
			residue.atom[ 15 ] = new Atom( "C5", 3.615f, -1.106f, -0.055f, -0.176f );
			residue.atom[ 16 ] = new Atom( "C7", 4.443f, -2.357f, -0.055f, -0.382f );
			residue.atom[ 17 ] = new Atom( "H71", 4.180f, -2.966f, -0.920f, 0.114f );
			residue.atom[ 18 ] = new Atom( "H72", 5.500f, -2.095f, -0.104f, 0.114f );
			residue.atom[ 19 ] = new Atom( "H73", 4.251f, -2.920f, 0.858f, 0.114f );
			residue.atom[ 20 ] = new Atom( "C4", 4.311f, 0.154f, -0.115f, 0.809f );
			residue.atom[ 21 ] = new Atom( "O4", 5.532f, 0.291f, -0.167f, -0.464f );
			residue.atom[ 22 ] = new Atom( "N3", 3.476f, 1.373f, -0.002f, -0.851f );
			residue.atom[ 23 ] = new Atom( "H3", 3.996f, 2.330f, -0.046f, 0.355f );
			residue.atom[ 24 ] = new Atom( "C2", 2.103f, 1.246f, -0.001f, 0.849f );
			residue.atom[ 25 ] = new Atom( "O2", 1.368f, 2.216f, 0.091f, -0.488f );
			residue.atom[ 26 ] = new Atom( "C3'", -1.871f, -1.464f, -0.028f, 0.303f );
			residue.atom[ 27 ] = new Atom( "H3'", -2.275f, -2.474f, -0.109f, 0.007f );
			residue.atom[ 28 ] = new Atom( "C2'", -0.605f, -1.139f, -0.823f, 0.101f );
			residue.atom[ 29 ] = new Atom( "H2'", -0.294f, -2.017f, -1.387f, 0.008f );
			residue.atom[ 30 ] = new Atom( "O2'", -0.873f, -0.064f, -1.726f, -0.546f );
			residue.atom[ 31 ] = new Atom( "HO2", -1.320f, -0.403f, -2.505f, -0.324f );
			residue.atom[ 32 ] = new Atom( "O3'", -2.909f, -0.632f, -0.521f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 26 );
			residue.atom[ 10 ].addBond( 12, 28 );
			residue.atom[ 12 ].addBond( 13, 24 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 20 );
			residue.atom[ 16 ].addBond( 17, 18, 19 );
			residue.atom[ 20 ].addBond( 21, 22 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25 );
			residue.atom[ 26 ].addBond( 27, 28, 32 );
			residue.atom[ 28 ].addBond( 29, 30 );
			residue.atom[ 30 ].addBond( 31 );
		}
		if ( ( res == r1MA ) || ( res == d1MA ) ){
			if ( res == r1MA )
				createResidue( "1MA", 37 );	
			else
				createResidue( "1MA", 36 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.445f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.327f, 3.632f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.402f, 3.213f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.350f, 1.898f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.971f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.099f, 2.497f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.804f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.442f, -1.174f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 2.163f, -2.217f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.713f, -0.696f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5", 3.703f, 0.654f, -0.002f );
			residue.atom[ 17 ] = new Atom( "C6", 4.788f, 1.524f, -0.002f );
			residue.atom[ 18 ] = new Atom( "N6", 6.031f, 1.080f, -0.003f );
			residue.atom[ 19 ] = new Atom( "H61", 6.212f, 0.086f, -0.003f );
			residue.atom[ 20 ] = new Atom( "H62", 6.801f, 1.733f, -0.003f );
			residue.atom[ 21 ] = new Atom( "N1", 4.515f, 2.824f, -0.002f );
			residue.atom[ 22 ] = new Atom( "C1", 5.628f, 3.785f, -0.003f );
			residue.atom[ 23 ] = new Atom( "H11", 6.238f, 3.633f, -0.894f );
			residue.atom[ 24 ] = new Atom( "H12", 5.231f, 4.801f, -0.003f );
			residue.atom[ 25 ] = new Atom( "H13", 6.239f, 3.634f, 0.886f );
			residue.atom[ 26 ] = new Atom( "C2", 3.262f, 3.288f, -0.002f );
			residue.atom[ 27 ] = new Atom( "H2", 3.101f, 4.356f, -0.003f );
			residue.atom[ 28 ] = new Atom( "N3", 2.189f, 2.469f, -0.001f );
			residue.atom[ 29 ] = new Atom( "C4", 2.427f, 1.158f, -0.001f );
			residue.atom[ 30 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 31 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 32 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == r1MA ){
				residue.atom[ 33 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 34 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 35 ] = new Atom( "HO2", -0.097f, -2.056f, 2.583f, -0.324f );
				residue.atom[ 36 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 33 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 34 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 35 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 30 );
			residue.atom[ 10 ].addBond( 12, 32 );
			residue.atom[ 12 ].addBond( 13, 29 );
			residue.atom[ 12 ].addBond( 13, 29 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 29 );
			residue.atom[ 17 ].addBond( 18, 21 );
			residue.atom[ 18 ].addBond( 19, 20 );
			residue.atom[ 21 ].addBond( 22, 26 );
			residue.atom[ 22 ].addBond( 23, 24, 25 );
			residue.atom[ 26 ].addBond( 27, 28 );
			residue.atom[ 28 ].addBond( 29 );
			if ( res == r1MA )
				residue.atom[ 30 ].addBond( 31, 32, 36 );
			else 
				residue.atom[ 30 ].addBond( 31, 32, 35 );
			residue.atom[ 32 ].addBond( 33, 34 );
			if ( res == r1MA )
				residue.atom[ 34 ].addBond( 35 );
		}
		else if ( ( res == r1MG ) || ( res == d1MG ) ){
			if ( res == r1MG )
				createResidue( "1MG", 37 );	
			else
				createResidue( "1MG", 36 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.445f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.328f, 3.631f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.402f, 3.213f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.350f, 1.898f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.971f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.099f, 2.497f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.804f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.263f, -1.078f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 1.918f, -2.101f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.542f, -0.703f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5", 3.500f, 0.686f, -0.002f );
			residue.atom[ 17 ] = new Atom( "C6", 4.571f, 1.661f, -0.003f );
			residue.atom[ 18 ] = new Atom( "O6", 5.745f, 1.331f, -0.003f );
			residue.atom[ 19 ] = new Atom( "N1", 4.198f, 2.935f, -0.003f );
			residue.atom[ 20 ] = new Atom( "C1A", 5.231f, 3.981f, -0.003f );
			residue.atom[ 21 ] = new Atom( "H11", 5.852f, 3.878f, -0.894f );
			residue.atom[ 22 ] = new Atom( "H12", 4.755f, 4.961f, -0.004f );
			residue.atom[ 23 ] = new Atom( "H13", 5.852f, 3.879f, 0.886f );
			residue.atom[ 24 ] = new Atom( "C2", 2.910f, 3.296f, -0.002f );
			residue.atom[ 25 ] = new Atom( "N2", 2.614f, 4.583f, -0.003f );
			residue.atom[ 26 ] = new Atom( "H21", 3.353f, 5.271f, -0.003f );
			residue.atom[ 27 ] = new Atom( "H22", 1.648f, 4.879f, -0.003f );
			residue.atom[ 28 ] = new Atom( "N3", 1.914f, 2.413f, -0.002f );
			residue.atom[ 29 ] = new Atom( "C4", 2.252f, 1.062f, -0.001f );
			residue.atom[ 30 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 31 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 32 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == r1MG ){
				residue.atom[ 33 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 34 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 35 ] = new Atom( "HO2", -0.097f, -2.056f, 2.583f, -0.324f );
				residue.atom[ 36 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 33 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 34 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 35 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 30 );
			residue.atom[ 10 ].addBond( 12, 32 );
			residue.atom[ 12 ].addBond( 13, 29 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 29 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 24 );
			residue.atom[ 20 ].addBond( 21, 22, 23 );
			residue.atom[ 24 ].addBond( 25, 28 );
			residue.atom[ 25 ].addBond( 26, 27 );
			residue.atom[ 28 ].addBond( 29 );
			if ( res == r1MG )
				residue.atom[ 30 ].addBond( 31, 32, 36 );
			else
				residue.atom[ 30 ].addBond( 31, 32, 35 );
			residue.atom[ 32 ].addBond( 33, 34 );
			if ( res == r1MG )
				residue.atom[ 34 ].addBond( 35 );
		}
		else if ( ( res == r2MG ) || ( res == d2MG ) ){
			if ( res == r2MG )
				createResidue( "2MG", 37 );	
			else
				createResidue( "2MG", 36 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.445f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.328f, 3.631f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.402f, 3.213f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.350f, 1.898f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.971f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.099f, 2.497f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.804f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.263f, -1.078f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 1.918f, -2.101f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.542f, -0.703f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5", 3.500f, 0.686f, -0.002f );
			residue.atom[ 17 ] = new Atom( "C6", 4.571f, 1.661f, -0.003f );
			residue.atom[ 18 ] = new Atom( "O6", 5.745f, 1.331f, -0.003f );
			residue.atom[ 19 ] = new Atom( "N1", 4.198f, 2.935f, -0.003f );
			residue.atom[ 20 ] = new Atom( "H1", 4.907f, 3.654f, -0.003f );
			residue.atom[ 21 ] = new Atom( "C2", 2.910f, 3.296f, -0.002f );
			residue.atom[ 22 ] = new Atom( "N2", 2.614f, 4.583f, -0.003f );
			residue.atom[ 23 ] = new Atom( "H2", 3.353f, 5.271f, -0.003f );
			residue.atom[ 24 ] = new Atom( "C2A", 1.208f, 5.014f, -0.002f );
			residue.atom[ 25 ] = new Atom( "H2A1", 1.162f, 6.103f, -0.002f );
			residue.atom[ 26 ] = new Atom( "H2A2", 0.710f, 4.629f, -0.891f );
			residue.atom[ 27 ] = new Atom( "H2A3", 0.711f, 4.629f, 0.888f );
			residue.atom[ 28 ] = new Atom( "N3", 1.914f, 2.413f, -0.002f );
			residue.atom[ 29 ] = new Atom( "C4", 2.252f, 1.062f, -0.001f );
			residue.atom[ 30 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 31 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 32 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == r2MG ){
				residue.atom[ 33 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 34 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 35 ] = new Atom( "HO2", -0.097f, -2.056f, 2.583f, -0.324f );
				residue.atom[ 36 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 33 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 34 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 35 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 30 );
			residue.atom[ 10 ].addBond( 12, 32 );
			residue.atom[ 12 ].addBond( 13, 29 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 29 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 28 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25, 26, 27 );
			residue.atom[ 28 ].addBond( 29 );
			if ( res == r2MG )
				residue.atom[ 30 ].addBond( 31, 32, 36 );
			else
				residue.atom[ 30 ].addBond( 31, 32, 35 );
			residue.atom[ 32 ].addBond( 33, 34 );
			if ( res == r2MG )
				residue.atom[ 34 ].addBond( 35 );
		}
		if ( ( res == r5MC ) || ( res == d5MC ) ){
			if ( res == r5MC )
				createResidue( "5MC", 34 );	
			else
				createResidue( "5MC", 33 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.446f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.327f, 3.632f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.401f, 3.214f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C6", 2.117f, -1.155f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H6", 1.559f, -2.080f, 0.000f );
			residue.atom[ 15 ] = new Atom( "C5", 3.456f, -1.186f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5A", 4.194f, -2.515f, -0.001f );
			residue.atom[ 17 ] = new Atom( "H5A1", 3.921f, -3.083f, -0.892f );
			residue.atom[ 18 ] = new Atom( "H5A2", 5.269f, -2.333f, -0.001f );
			residue.atom[ 19 ] = new Atom( "H5A3", 3.923f, -3.083f, 0.889f );
			residue.atom[ 20 ] = new Atom( "C4", 4.196f, 0.068f, -0.002f );
			residue.atom[ 21 ] = new Atom( "N4", 5.515f, 0.075f, -0.002f );
			residue.atom[ 22 ] = new Atom( "H41", 6.024f, -0.798f, -0.002f );
			residue.atom[ 23 ] = new Atom( "H42", 6.016f, 0.952f, -0.002f );
			residue.atom[ 24 ] = new Atom( "N3", 3.517f, 1.197f, -0.002f );
			residue.atom[ 25 ] = new Atom( "C2", 2.116f, 1.155f, -0.001f );
			residue.atom[ 26 ] = new Atom( "O2", 1.484f, 2.199f, 0.000f );
			residue.atom[ 27 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 28 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 29 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == r5MC ){
				residue.atom[ 30 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 31 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 32 ] = new Atom( "HO2", -0.097f, -2.057f, 2.583f, -0.324f );
				residue.atom[ 33 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 30 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 31 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 32 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 27 );
			residue.atom[ 10 ].addBond( 12, 29 );
			residue.atom[ 12 ].addBond( 13, 25 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 20 );
			residue.atom[ 16 ].addBond( 17, 18, 19 );
			residue.atom[ 20 ].addBond( 21, 24 );
			residue.atom[ 21 ].addBond( 22, 23 );
			residue.atom[ 24 ].addBond( 25 );
			residue.atom[ 25 ].addBond( 26 );
			if ( res == r5MC )
				residue.atom[ 27 ].addBond( 28, 29, 33 );
			else
				residue.atom[ 27 ].addBond( 28, 29, 32 );
			residue.atom[ 29 ].addBond( 30, 31 );
			if ( res == r5MC )
				residue.atom[ 31 ].addBond( 32 );
		}
		else if ( ( res == r5MU ) || ( res == d5MU ) ){
			if ( res == r5MU )
				createResidue( "5MU", 33 );	
			else
				createResidue( "5MU", 32 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.447f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.327f, 3.633f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.401f, 3.214f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.164f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C6", 2.116f, -1.153f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H6", 1.566f, -2.083f, 0.002f );
			residue.atom[ 15 ] = new Atom( "C5", 3.452f, -1.167f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5A", 4.221f, -2.479f, -0.002f );
			residue.atom[ 17 ] = new Atom( "H5A1", 3.960f, -3.052f, -0.892f );
			residue.atom[ 18 ] = new Atom( "H5A2", 3.961f, -3.052f, 0.888f );
			residue.atom[ 19 ] = new Atom( "H5A3", 5.291f, -2.274f, -0.001f );
			residue.atom[ 20 ] = new Atom( "C4", 4.153f, 0.108f, -0.005f );
			residue.atom[ 21 ] = new Atom( "O4", 5.372f, 0.151f, -0.008f );
			residue.atom[ 22 ] = new Atom( "N3", 3.438f, 1.216f, -0.004f );
			residue.atom[ 23 ] = new Atom( "H3", 3.902f, 2.114f, -0.006f );
			residue.atom[ 24 ] = new Atom( "C2", 2.117f, 1.157f, -0.001f );
			residue.atom[ 25 ] = new Atom( "O2", 1.476f, 2.195f, 0.002f );
			residue.atom[ 26 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 27 ] = new Atom( "H3'", 0.020f, 0.889f, 2.720f, 0.007f );
			residue.atom[ 28 ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, 0.101f );
			if ( res == r5MU ){
				residue.atom[ 29 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 30 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 31 ] = new Atom( "HO2", -0.097f, -2.057f, 2.583f, -0.324f );
				residue.atom[ 32 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 29 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 30 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 31 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 26 );
			residue.atom[ 10 ].addBond( 12, 28 );
			residue.atom[ 12 ].addBond( 13, 24 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 20 );
			residue.atom[ 16 ].addBond( 17, 18, 19 );
			residue.atom[ 20 ].addBond( 21, 22 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25 );
			if ( res == r5MU )
				residue.atom[ 26 ].addBond( 27, 28, 32 );
			else
				residue.atom[ 26 ].addBond( 27, 28, 31 );
			residue.atom[ 28 ].addBond( 29, 30 );
			if ( res == r5MU )
				residue.atom[ 30 ].addBond( 31 );
		}
		if ( ( res == r7MG ) || ( res == d7MG ) ){
			if ( res == r7MG )
				createResidue( "7MG", 38 );	
			else
				createResidue( "7MG", 37 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.053f, 2.447f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.326f, 3.633f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.401f, 3.215f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.608f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.164f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.257f, -1.070f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 1.942f, -2.103f, -0.001f );
			residue.atom[ 15 ] = new Atom( "N7", 3.688f, -0.557f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C7", 4.938f, -1.331f, -0.002f );
			residue.atom[ 17 ] = new Atom( "H71", 4.976f, -1.959f, -0.892f );
			residue.atom[ 18 ] = new Atom( "H72", 5.788f, -0.648f, -0.002f );
			residue.atom[ 19 ] = new Atom( "H73", 4.977f, -1.959f, 0.887f );
			residue.atom[ 20 ] = new Atom( "C5", 3.534f, 0.746f, -0.002f );
			residue.atom[ 21 ] = new Atom( "C6", 4.551f, 1.781f, -0.002f );
			residue.atom[ 22 ] = new Atom( "O6", 5.740f, 1.510f, -0.002f );
			residue.atom[ 23 ] = new Atom( "N1", 4.121f, 3.036f, -0.002f );
			residue.atom[ 24 ] = new Atom( "H1", 4.797f, 3.785f, -0.002f );
			residue.atom[ 25 ] = new Atom( "C2", 2.820f, 3.335f, -0.001f );
			residue.atom[ 26 ] = new Atom( "N2", 2.464f, 4.607f, -0.001f );
			residue.atom[ 27 ] = new Atom( "H21", 3.170f, 5.329f, -0.002f );
			residue.atom[ 28 ] = new Atom( "H22", 1.486f, 4.857f, 0.000f );
			residue.atom[ 29 ] = new Atom( "N3", 1.868f, 2.407f, -0.001f );
			residue.atom[ 30 ] = new Atom( "C4", 2.254f, 1.067f, -0.001f );
			residue.atom[ 31 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 32 ] = new Atom( "H3'", 0.020f, 0.889f, 2.720f, 0.007f );
			residue.atom[ 33 ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, 0.101f );
			if ( res == r7MG ){
				residue.atom[ 34 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 35 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 36 ] = new Atom( "HO2", -0.097f, -2.057f, 2.582f, -0.324f );
				residue.atom[ 37 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 34 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 35 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 36 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 31 );
			residue.atom[ 10 ].addBond( 12, 33 );
			residue.atom[ 12 ].addBond( 13, 30 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 20 );
			residue.atom[ 16 ].addBond( 17, 18, 19 );
			residue.atom[ 20 ].addBond( 21, 30 );
			residue.atom[ 21 ].addBond( 22, 23 );
			residue.atom[ 23 ].addBond( 24, 25 );
			residue.atom[ 25 ].addBond( 26, 29 );
			residue.atom[ 26 ].addBond( 27, 28 );
			residue.atom[ 29 ].addBond( 30 );
			if ( res == r7MG )
				residue.atom[ 31 ].addBond( 32, 33, 37 );
			else
				residue.atom[ 31 ].addBond( 32, 33, 36 );
			residue.atom[ 33 ].addBond( 34, 35 );
			if ( res == r7MG )
				residue.atom[ 35 ].addBond( 36 );
		}
		else if ( res == rH2U ){
			createResidue( "h2U", 32 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.053f, 2.448f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.326f, 3.635f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.400f, 3.216f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.900f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.608f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.097f, 2.499f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.806f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.164f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f ); 
			residue.atom[ 12 ] = new Atom( "C3'", -0.833f, 0.499f, 2.166f, 0.303f );
			residue.atom[ 13 ] = new Atom( "H3'", 0.020f, 0.889f, 2.721f, 0.007f );
			residue.atom[ 14 ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, 0.101f );
			residue.atom[ 15 ] = new Atom( "H2'", -1.493f, -1.183f, 0.998f, 0.008f );
			residue.atom[ 16 ] = new Atom( "O2'", 0.317f, -1.671f, 1.806f, -0.546f );
			residue.atom[ 17 ] = new Atom( "HO2", -0.097f, -2.058f, 2.582f, -0.324f );
			residue.atom[ 18 ] = new Atom( "O3'", -1.744f, 0.131f, 3.299f, -0.509f );
			residue.atom[ 19 ] = new Atom( "N1", 1.470f, 0.000f, 0.000f );
			residue.atom[ 20 ] = new Atom( "C6", 2.201f, -1.274f, -0.001f );
			residue.atom[ 21 ] = new Atom( "H61", 1.550f, -2.075f, -0.352f );
			residue.atom[ 22 ] = new Atom( "H62", 2.548f, -1.502f, 1.006f );
			residue.atom[ 23 ] = new Atom( "C5", 3.401f, -1.125f, -0.946f );
			residue.atom[ 24 ] = new Atom( "H51", 3.057f, -1.047f, -1.978f );
			residue.atom[ 25 ] = new Atom( "H52", 4.062f, -1.986f, -0.847f );
			residue.atom[ 26 ] = new Atom( "C4", 4.134f, 0.142f, -0.547f );
			residue.atom[ 27 ] = new Atom( "O4", 5.353f, 0.183f, -0.581f );
			residue.atom[ 28 ] = new Atom( "N3", 3.443f, 1.201f, -0.161f );
			residue.atom[ 29 ] = new Atom( "H3", 3.928f, 2.070f, 0.013f );
			residue.atom[ 30 ] = new Atom( "C2", 2.128f, 1.149f, -0.001f );
			residue.atom[ 31 ] = new Atom( "O2", 1.508f, 2.187f, 0.152f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 12 );
			residue.atom[ 10 ].addBond( 14, 19 );
			residue.atom[ 12 ].addBond( 13, 14, 18 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 16 ].addBond( 17 );
			residue.atom[ 19 ].addBond( 20, 30 );
			residue.atom[ 20 ].addBond( 21, 22, 23 );
			residue.atom[ 23 ].addBond( 24, 25, 26 );
			residue.atom[ 26 ].addBond( 27, 28 );
			residue.atom[ 28 ].addBond( 29, 30 );
			residue.atom[ 30 ].addBond( 31 );
		}
		else if ( ( res == rI ) || ( res == dI ) ){
			if ( res == rH2U )
				createResidue( "i", 31 );	
			else
				createResidue( "i", 30 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.445f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.328f, 3.631f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.402f, 3.213f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.350f, 1.898f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.971f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.099f, 2.497f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.804f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.263f, -1.078f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 1.918f, -2.101f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.542f, -0.703f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5", 3.500f, 0.686f, -0.002f );
			residue.atom[ 17 ] = new Atom( "C6", 4.571f, 1.661f, -0.003f );
			residue.atom[ 18 ] = new Atom( "O6", 5.745f, 1.331f, -0.003f );
			residue.atom[ 19 ] = new Atom( "N1", 4.198f, 2.935f, -0.003f );
			residue.atom[ 20 ] = new Atom( "H1", 4.907f, 3.654f, -0.003f );
			residue.atom[ 21 ] = new Atom( "C2", 2.910f, 3.296f, -0.002f );
			residue.atom[ 22 ] = new Atom( "N3", 1.914f, 2.413f, -0.002f );
			residue.atom[ 23 ] = new Atom( "C4", 2.252f, 1.062f, -0.001f );
			residue.atom[ 24 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 25 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 26 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == rH2U ){
				residue.atom[ 27 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 28 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 29 ] = new Atom( "HO2", -0.097f, -2.056f, 2.583f, -0.324f );
				residue.atom[ 30 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 27 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 28 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 29 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 24 );
			residue.atom[ 10 ].addBond( 12, 26 );
			residue.atom[ 12 ].addBond( 13, 23 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 23 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23 );
			if ( res == rH2U )
				residue.atom[ 24 ].addBond( 25, 26, 30 );
			else
				residue.atom[ 24 ].addBond( 25, 26, 29 );
			residue.atom[ 26 ].addBond( 27, 28 );
			if ( res == rH2U )
				residue.atom[ 28 ].addBond( 29 );

		}
		if ( ( res == rM2G ) || ( res == dM2G ) ){
			if ( res == rM2G )
				createResidue( "m2G", 40 );	
			else
				createResidue( "m2G", 39 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.445f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.328f, 3.631f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.402f, 3.213f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.350f, 1.898f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.971f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.099f, 2.497f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.804f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.263f, -1.078f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H8", 1.918f, -2.101f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.542f, -0.703f, -0.001f );
			residue.atom[ 16 ] = new Atom( "C5", 3.500f, 0.686f, -0.002f );
			residue.atom[ 17 ] = new Atom( "C6", 4.571f, 1.661f, -0.003f );
			residue.atom[ 18 ] = new Atom( "O6", 5.745f, 1.331f, -0.003f );
			residue.atom[ 19 ] = new Atom( "N1", 4.198f, 2.935f, -0.003f );
			residue.atom[ 20 ] = new Atom( "H1", 4.907f, 3.654f, -0.003f );
			residue.atom[ 21 ] = new Atom( "C2", 2.910f, 3.296f, -0.002f );
			residue.atom[ 22 ] = new Atom( "N2", 2.614f, 4.583f, -0.003f );
			residue.atom[ 23 ] = new Atom( "C2A", 3.690f, 5.584f, -0.003f );
			residue.atom[ 24 ] = new Atom( "H2A1", 3.256f, 6.585f, -0.004f );
			residue.atom[ 25 ] = new Atom( "H2A2", 4.306f, 5.456f, 0.887f );
			residue.atom[ 26 ] = new Atom( "H2A3", 4.305f, 5.456f, -0.893f );
			residue.atom[ 27 ] = new Atom( "C2B", 1.208f, 5.014f, -0.002f );
			residue.atom[ 28 ] = new Atom( "H2B1", 1.162f, 6.103f, -0.002f );
			residue.atom[ 29 ] = new Atom( "H2B2", 0.710f, 4.629f, -0.891f );
			residue.atom[ 30 ] = new Atom( "H2B3", 0.711f, 4.629f, 0.888f );
			residue.atom[ 31 ] = new Atom( "N3", 1.914f, 2.413f, -0.002f );
			residue.atom[ 32 ] = new Atom( "C4", 2.252f, 1.062f, -0.001f );
			residue.atom[ 33 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 34 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 35 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			if ( res == rM2G ){
				residue.atom[ 36 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ 37 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ 38 ] = new Atom( "HO2", -0.097f, -2.056f, 2.583f, -0.324f );
				residue.atom[ 39 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 36 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ 37 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
				residue.atom[ 38 ] = new Atom( "O3'", -1.744f, 0.133f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 33 );
			residue.atom[ 10 ].addBond( 12, 35 );
			residue.atom[ 12 ].addBond( 13, 32 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 32 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 31 );
			residue.atom[ 22 ].addBond( 23, 27 );
			residue.atom[ 23 ].addBond( 24, 25, 26 );
			residue.atom[ 27 ].addBond( 28, 29, 30 );
			residue.atom[ 31 ].addBond( 32 );
			if ( res == rM2G )
				residue.atom[ 33 ].addBond( 34, 35, 39 );
			else
				residue.atom[ 33 ].addBond( 34, 35, 38 );
			residue.atom[ 35 ].addBond( 36, 37 );
			if ( res == rM2G )
				residue.atom[ 37 ].addBond( 38 );
		}
		else if ( ( res == rOMC ) || ( res == dOMC ) ){
			createResidue( "oMC", 34 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.446f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.327f, 3.632f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.401f, 3.214f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C6", 2.117f, -1.155f, -0.001f );
			residue.atom[ 14 ] = new Atom( "H6", 1.559f, -2.080f, 0.000f );
			residue.atom[ 15 ] = new Atom( "C5", 3.456f, -1.186f, -0.001f );
			residue.atom[ 16 ] = new Atom( "H5", 3.980f, -2.131f, -0.001f );
			residue.atom[ 17 ] = new Atom( "C4", 4.196f, 0.068f, -0.002f );
			residue.atom[ 18 ] = new Atom( "N4", 5.515f, 0.075f, -0.002f );
			residue.atom[ 19 ] = new Atom( "H41", 6.024f, -0.798f, -0.002f );
			residue.atom[ 20 ] = new Atom( "H42", 6.016f, 0.952f, -0.002f );
			residue.atom[ 21 ] = new Atom( "N3", 3.517f, 1.197f, -0.002f );
			residue.atom[ 22 ] = new Atom( "C2", 2.116f, 1.155f, -0.001f );
			residue.atom[ 23 ] = new Atom( "CO2", 1.421f, 2.301f, -0.001f );
			residue.atom[ 24 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ 25 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 26 ] = new Atom( "C2'", -0.554f, -0.685f, 1.245f, 0.101f );
			residue.atom[ 27 ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
			residue.atom[ 28 ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f );
			residue.atom[ 29 ] = new Atom( "C2A", -0.299f, -2.246f, 2.963f );
			residue.atom[ 30 ] = new Atom( "H2A1", 0.366f, -2.996f, 3.392f );
			residue.atom[ 31 ] = new Atom( "H2A2", -0.488f, -1.465f, 3.700f );
			residue.atom[ 32 ] = new Atom( "H2A3", -1.241f, -2.714f, 2.678f );
			residue.atom[ 33 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 24 );
			residue.atom[ 10 ].addBond( 12, 26 );
			residue.atom[ 12 ].addBond( 13, 22 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16, 17 );
			residue.atom[ 17 ].addBond( 18, 21 );
			residue.atom[ 18 ].addBond( 19, 20 );
			residue.atom[ 21 ].addBond( 22 );
			residue.atom[ 22 ].addBond( 23 );
			residue.atom[ 24 ].addBond( 25, 26, 33 );
			residue.atom[ 26 ].addBond( 27, 28 );
			residue.atom[ 28 ].addBond( 29 );
			residue.atom[ 29 ].addBond( 30, 31, 32 );
		}
		if ( ( res == rOMG ) || ( res == dOMG ) ){
			createResidue( "oMG", 37 );	
			residue.atom[ 0 ] = new Atom( "P", 0.064f, 5.054f, 2.447f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.248f, 5.329f, 3.633f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.644f, 5.400f, 3.215f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.028f, 3.351f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.320f, 2.973f, 1.607f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.934f, 3.101f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.701f, 3.604f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.369f, 1.524f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.434f, 1.362f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.516f, 1.322f, 0.000f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.347f, -0.525f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "C8", 2.263f, -1.078f, 0.000f );
			residue.atom[ 14 ] = new Atom( "H8", 1.917f, -2.101f, 0.000f );
			residue.atom[ 15 ] = new Atom( "N7", 3.543f, -0.705f, 0.000f );
			residue.atom[ 16 ] = new Atom( "C5", 3.500f, 0.683f, 0.000f );
			residue.atom[ 17 ] = new Atom( "C6", 4.572f, 1.659f, 0.000f );
			residue.atom[ 18 ] = new Atom( "O6", 5.747f, 1.328f, -0.001f );
			residue.atom[ 19 ] = new Atom( "N1", 4.199f, 2.933f, -0.001f );
			residue.atom[ 20 ] = new Atom( "H1", 4.910f, 3.651f, -0.001f );
			residue.atom[ 21 ] = new Atom( "C2", 2.912f, 3.296f, 0.000f );
			residue.atom[ 22 ] = new Atom( "N2", 2.617f, 4.582f, -0.001f );
			residue.atom[ 23 ] = new Atom( "H21", 3.357f, 5.270f, 0.000f );
			residue.atom[ 24 ] = new Atom( "H22", 1.652f, 4.879f, 0.000f );
			residue.atom[ 25 ] = new Atom( "N3", 1.916f, 2.413f, -0.001f );
			residue.atom[ 26 ] = new Atom( "C4", 2.252f, 1.061f, 0.000f );
			residue.atom[ 27 ] = new Atom( "C3'", -0.833f, 0.502f, 2.166f, 0.303f );
			residue.atom[ 28 ] = new Atom( "H3'", 0.021f, 0.890f, 2.720f, 0.007f );
			residue.atom[ 29 ] = new Atom( "C2'", -0.555f, -0.685f, 1.245f, 0.101f );
			residue.atom[ 30 ] = new Atom( "H2'", -1.494f, -1.180f, 0.998f, 0.008f );
			residue.atom[ 31 ] = new Atom( "O2'", 0.315f, -1.670f, 1.807f );
			residue.atom[ 32 ] = new Atom( "C2A", -0.301f, -2.245f, 2.963f );
			residue.atom[ 33 ] = new Atom( "H2A1", 0.363f, -2.997f, 3.391f );
			residue.atom[ 34 ] = new Atom( "H2A2", -0.491f, -1.465f, 3.699f );
			residue.atom[ 35 ] = new Atom( "H2A3", -1.243f, -2.712f, 2.677f );
			residue.atom[ 36 ] = new Atom( "O3'", -1.745f, 0.134f, 3.299f, -0.509f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 27 );
			residue.atom[ 10 ].addBond( 12, 29 );
			residue.atom[ 12 ].addBond( 13, 26 );
			residue.atom[ 13 ].addBond( 14, 15 );
			residue.atom[ 15 ].addBond( 16 );
			residue.atom[ 16 ].addBond( 17, 26 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 25 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 25 ].addBond( 26 );
			residue.atom[ 27 ].addBond( 28, 29, 36 );
			residue.atom[ 29 ].addBond( 30, 31 );
			residue.atom[ 31 ].addBond( 32 );
			residue.atom[ 32 ].addBond( 33, 34, 35 );
		}
		else if ( ( res == rPSU ) || ( res == dPSU ) ){
			if ( res == rPSU )
				createResidue( "pSU", 30 );	
			else
				createResidue( "pSU", 29 );	
			residue.atom[ 0 ] = new Atom( "P", -1.472f, 4.834f, 2.447f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -2.806f, 4.697f, 3.634f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", -0.071f, 5.644f, 3.216f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", -0.990f, 3.200f, 1.900f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -2.159f, 2.431f, 1.608f, 0.180f );
			residue.atom[ 5 ] = new Atom( "H5'1", -2.783f, 2.366f, 2.498f, 0.008f );
			residue.atom[ 6 ] = new Atom( "H5'2", -2.714f, 2.917f, 0.805f, 0.008f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.767f, 1.037f, 1.163f, 0.100f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.733f, 0.558f, 0.998f, 0.061f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.893f, 1.103f, 0.001f, -0.343f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.172f, -0.606f, -0.889f, 0.054f );
			residue.atom[ 12 ] = new Atom( "N1", 3.703f, 0.000f, 0.000f );
			residue.atom[ 13 ] = new Atom( "H1", 4.453f, -0.676f, -0.001f );
			residue.atom[ 14 ] = new Atom( "C6", 2.450f, -0.425f, 0.000f );
			residue.atom[ 15 ] = new Atom( "H6", 2.238f, -1.483f, 0.000f );
			residue.atom[ 16 ] = new Atom( "C5", 1.449f, 0.460f, 0.000f );
			residue.atom[ 17 ] = new Atom( "C4", 1.783f, 1.876f, 0.000f );
			residue.atom[ 18 ] = new Atom( "O4", 0.905f, 2.724f, 0.000f );
			residue.atom[ 19 ] = new Atom( "N3", 3.055f, 2.221f, 0.000f );
			residue.atom[ 20 ] = new Atom( "H3", 3.311f, 3.198f, 0.000f );
			residue.atom[ 21 ] = new Atom( "C2", 3.996f, 1.292f, 0.000f );
			residue.atom[ 22 ] = new Atom( "O2", 5.167f, 1.634f, 0.000f );
			residue.atom[ 23 ] = new Atom( "C3'", -0.946f, 0.223f, 2.166f, 0.303f );
			residue.atom[ 24 ] = new Atom( "H3'", -0.251f, 0.853f, 2.721f, 0.007f );
			residue.atom[ 25 ] = new Atom( "C2'", -0.321f, -0.821f, 1.244f, 0.101f );
			if ( res == rPSU ){
				residue.atom[ 26 ] = new Atom( "H2'", -1.065f, -1.579f, 0.998f, 0.008f );
				residue.atom[ 27 ] = new Atom( "O2'", 0.807f, -1.496f, 1.807f, -0.546f );
				residue.atom[ 28 ] = new Atom( "HO2", 0.530f, -1.990f, 2.582f, -0.324f );
				residue.atom[ 29 ] = new Atom( "O3'", -1.703f, -0.403f, 3.299f, -0.509f );
			}
			else{
				residue.atom[ 26 ] = new Atom( "H2'1", -1.065f, -1.579f, 0.998f, 0.081f );
				residue.atom[ 27 ] = new Atom( "H2'2", 0.807f, -1.496f, 1.807f, 0.081f );
				residue.atom[ 28 ] = new Atom( "O3'", -1.703f, -0.403f, 3.299f, -0.509f );
			}
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 23 );
			residue.atom[ 10 ].addBond( 16, 25 );
			residue.atom[ 12 ].addBond( 13, 14, 21 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 16 ].addBond( 17 );
			residue.atom[ 17 ].addBond( 18, 19 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22 );
			if ( res == rPSU )
				residue.atom[ 23 ].addBond( 24, 25, 29 );
			else
				residue.atom[ 23 ].addBond( 24, 25, 28 );
			residue.atom[ 25 ].addBond( 26, 27 );
			if ( res == rPSU )
				residue.atom[ 27 ].addBond( 28 );
		}
		else if ( ( res == rY ) || ( res == dY ) ){
			if ( res == rY )
				createResidue( "yG", 66 );	
			else
				createResidue( "yG", 65 );	
			int i = 0;
			residue.atom[ i++ ] = new Atom( "P", 0.061f, 5.054f, 2.446f, 1.385f );
			residue.atom[ i++ ] = new Atom( "O1P", -1.252f, 5.327f, 3.633f, -0.847f );
			residue.atom[ i++ ] = new Atom( "O2P", 1.641f, 5.401f, 3.214f, -0.847f );
			residue.atom[ i++ ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ i++ ] = new Atom( "C5'", -1.321f, 2.970f, 1.607f, 0.180f );
			residue.atom[ i++ ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.008f );
			residue.atom[ i++ ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.008f );
			residue.atom[ i++ ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.100f );
			residue.atom[ i++ ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.061f );
			residue.atom[ i++ ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.343f );
			residue.atom[ i++ ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.117f );
			residue.atom[ i++ ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.054f );
			residue.atom[ i++ ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.303f );
			residue.atom[ i++ ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.007f );
			residue.atom[ i++ ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, 0.101f );
			if ( res == rY ){
				residue.atom[ i++ ] = new Atom( "H2'", -1.493f, -1.182f, 0.999f, 0.008f );
				residue.atom[ i++ ] = new Atom( "O2'", 0.317f, -1.670f, 1.807f, -0.546f );
				residue.atom[ i++ ] = new Atom( "HO2", -0.097f, -2.057f, 2.583f, -0.324f );
			}
			else{
				residue.atom[ i++ ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
				residue.atom[ i++ ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
			}
			residue.atom[ i++ ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			residue.atom[ i++ ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ i++ ] = new Atom( "C8", 2.413f, -1.192f, -0.001f );
			residue.atom[ i++ ] = new Atom( "H8", 2.133f, -2.235f, -0.001f );
			residue.atom[ i++ ] = new Atom( "N7", 3.647f, -0.703f, -0.001f );
			residue.atom[ i++ ] = new Atom( "H7", 4.502f, -1.240f, -0.002f );
			residue.atom[ i++ ] = new Atom( "C5", 3.549f, 0.617f, -0.001f );
			residue.atom[ i++ ] = new Atom( "C6", 4.637f, 1.582f, -0.006f );
			residue.atom[ i++ ] = new Atom( "O6", 5.814f, 1.259f, -0.004f );
			residue.atom[ i++ ] = new Atom( "N1", 4.235f, 2.838f, -0.011f );
			residue.atom[ i++ ] = new Atom( "C2", 2.963f, 3.181f, -0.012f );
			residue.atom[ i++ ] = new Atom( "N2", 2.857f, 4.492f, -0.025f );
			residue.atom[ i++ ] = new Atom( "N3", 1.961f, 2.312f, -0.005f );
			residue.atom[ i++ ] = new Atom( "C3", 0.559f, 2.753f, -0.003f );
			residue.atom[ i++ ] = new Atom( "H31", 0.363f, 3.348f, -0.895f );
			residue.atom[ i++ ] = new Atom( "H32", -0.095f, 1.881f, 0.003f );
			residue.atom[ i++ ] = new Atom( "H33", 0.369f, 3.355f, 0.885f );
			residue.atom[ i++ ] = new Atom( "C4", 2.284f, 1.031f, -0.001f );
			residue.atom[ i++ ] = new Atom( "C10", 4.597f, 6.435f, -0.046f );
			residue.atom[ i++ ] = new Atom( "H101", 4.205f, 6.936f, 0.839f );
			residue.atom[ i++ ] = new Atom( "H102", 5.685f, 6.500f, -0.048f );
			residue.atom[ i++ ] = new Atom( "H103", 4.203f, 6.918f, -0.940f );
			residue.atom[ i++ ] = new Atom( "C11", 4.177f, 4.975f, -0.032f );
			residue.atom[ i++ ] = new Atom( "C12", 5.002f, 3.911f, -0.022f );
			residue.atom[ i++ ] = new Atom( "C13", 6.523f, 3.941f, -0.024f );
			residue.atom[ i++ ] = new Atom( "H131", 6.865f, 4.975f, -0.035f );
			residue.atom[ i++ ] = new Atom( "H132", 6.896f, 3.443f, 0.870f );
			residue.atom[ i++ ] = new Atom( "C14", 7.047f, 3.212f, -1.275f );
			residue.atom[ i++ ] = new Atom( "H141", 6.705f, 2.178f, -1.265f );
			residue.atom[ i++ ] = new Atom( "H142", 6.674f, 3.710f, -2.170f );
			residue.atom[ i++ ] = new Atom( "C15", 8.588f, 3.242f, -1.278f );
			residue.atom[ i++ ] = new Atom( "H15", 8.963f, 2.745f, -0.383f );
			residue.atom[ i++ ] = new Atom( "C16", 9.107f, 2.524f, -2.513f );
			residue.atom[ i++ ] = new Atom( "O17", 10.308f, 2.434f, -2.706f );
			residue.atom[ i++ ] = new Atom( "O18", 8.232f, 1.981f, -3.401f );
			residue.atom[ i++ ] = new Atom( "C19", 8.958f, 1.365f, -4.468f );
			residue.atom[ i++ ] = new Atom( "H191", 8.257f, 0.931f, -5.181f );
			residue.atom[ i++ ] = new Atom( "H192", 9.569f, 2.115f, -4.972f );
			residue.atom[ i++ ] = new Atom( "H193", 9.600f, 0.583f, -4.067f );
			residue.atom[ i++ ] = new Atom( "N20", 9.050f, 4.638f, -1.291f );
			residue.atom[ i++ ] = new Atom( "H20", 8.380f, 5.392f, -1.299f );
			residue.atom[ i++ ] = new Atom( "C21", 10.343f, 4.904f, -1.296f );
			residue.atom[ i++ ] = new Atom( "O22", 10.727f, 6.062f, -1.308f );
			residue.atom[ i++ ] = new Atom( "O23", 11.247f, 3.888f, -1.287f );
			residue.atom[ i++ ] = new Atom( "C24", 12.572f, 4.428f, -1.294f );
			residue.atom[ i++ ] = new Atom( "H241", 13.296f, 3.613f, -1.288f );
			residue.atom[ i++ ] = new Atom( "H242", 12.712f, 5.031f, -2.190f );
			residue.atom[ i++ ] = new Atom( "H243", 12.715f, 5.049f, -0.410f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 12 );
			residue.atom[ 10 ].addBond( 14, 19 );
			residue.atom[ 12 ].addBond( 13, 14, 18 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 16 ].addBond( 17 );
			residue.atom[ 19 ].addBond( 20, 35 );
			residue.atom[ 20 ].addBond( 21, 22 );
			residue.atom[ 22 ].addBond( 23, 24 );
			residue.atom[ 24 ].addBond( 25, 35 );
			residue.atom[ 25 ].addBond( 26, 27 );
			residue.atom[ 27 ].addBond( 28, 41 );
			residue.atom[ 28 ].addBond( 29, 30 );
			residue.atom[ 29 ].addBond( 40 );
			residue.atom[ 30 ].addBond( 31, 35 );
			residue.atom[ 31 ].addBond( 32, 33, 34 );
			residue.atom[ 36 ].addBond( 37, 38, 39, 40 );
			residue.atom[ 40 ].addBond( 41 );
			residue.atom[ 41 ].addBond( 42 );
			residue.atom[ 42 ].addBond( 43, 44, 45 );
			residue.atom[ 45 ].addBond( 46, 47, 48 );
			residue.atom[ 48 ].addBond( 49, 50, 57 );
			residue.atom[ 50 ].addBond( 51, 52 );
			residue.atom[ 52 ].addBond( 53 );
			residue.atom[ 53 ].addBond( 54, 55, 56 );
			residue.atom[ 57 ].addBond( 58, 59 );
			residue.atom[ 59 ].addBond( 60, 61 );
			residue.atom[ 61 ].addBond( 62 );
			residue.atom[ 62 ].addBond( 63, 64, 65 );
		}
		if ( res == dH2U ){
			createResidue( "h2U", 31 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.053f, 2.448f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.326f, 3.635f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.400f, 3.216f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.900f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.608f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.097f, 2.499f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.806f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.164f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.009f ); 
			residue.atom[ 12 ] = new Atom( "C3'", -0.833f, 0.499f, 2.166f, 0.233f );
			residue.atom[ 13 ] = new Atom( "H3'", 0.020f, 0.889f, 2.721f, 0.025f );
			residue.atom[ 14 ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, -0.307f );
			residue.atom[ 15 ] = new Atom( "H2'1", -1.493f, -1.183f, 0.998f, 0.081f );
			residue.atom[ 16 ] = new Atom( "H2'2", 0.317f, -1.671f, 1.806f, 0.081f );
			residue.atom[ 17 ] = new Atom( "O3'", -1.744f, 0.131f, 3.299f, -0.509f );
			residue.atom[ 18 ] = new Atom( "N1", 1.470f, 0.000f, 0.000f );
			residue.atom[ 19 ] = new Atom( "C6", 2.201f, -1.274f, -0.001f );
			residue.atom[ 20 ] = new Atom( "H61", 1.550f, -2.075f, -0.352f );
			residue.atom[ 21 ] = new Atom( "H62", 2.548f, -1.502f, 1.006f );
			residue.atom[ 22 ] = new Atom( "C5", 3.401f, -1.125f, -0.946f );
			residue.atom[ 23 ] = new Atom( "H51", 3.057f, -1.047f, -1.978f );
			residue.atom[ 24 ] = new Atom( "H52", 4.062f, -1.986f, -0.847f );
			residue.atom[ 25 ] = new Atom( "C4", 4.134f, 0.142f, -0.547f );
			residue.atom[ 26 ] = new Atom( "O4", 5.353f, 0.183f, -0.581f );
			residue.atom[ 27 ] = new Atom( "N3", 3.443f, 1.201f, -0.161f );
			residue.atom[ 28 ] = new Atom( "H3", 3.928f, 2.070f, 0.013f );
			residue.atom[ 29 ] = new Atom( "C2", 2.128f, 1.149f, -0.001f );
			residue.atom[ 30 ] = new Atom( "O2", 1.508f, 2.187f, 0.152f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 12 );
			residue.atom[ 10 ].addBond( 14, 18 );
			residue.atom[ 12 ].addBond( 13, 14, 17 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 18 ].addBond( 19, 29 );
			residue.atom[ 19 ].addBond( 20, 21, 22 );
			residue.atom[ 22 ].addBond( 23, 24, 25 );
			residue.atom[ 25 ].addBond( 26, 27 );
			residue.atom[ 27 ].addBond( 28, 29 );
			residue.atom[ 29 ].addBond( 30 );
		}
		else if ( res == dY ){
			createResidue( "yG", 65 );	
			residue.atom[ 0 ] = new Atom( "P", 0.061f, 5.054f, 2.446f, 1.385f );
			residue.atom[ 1 ] = new Atom( "O1P", -1.252f, 5.327f, 3.633f, -0.847f );
			residue.atom[ 2 ] = new Atom( "O2P", 1.641f, 5.401f, 3.214f, -0.847f );
			residue.atom[ 3 ] = new Atom( "O5'", 0.026f, 3.349f, 1.899f, -0.509f );
			residue.atom[ 4 ] = new Atom( "C5'", -1.321f, 2.970f, 1.607f, 0.118f );
			residue.atom[ 5 ] = new Atom( "H5'1", -1.936f, 3.098f, 2.498f, 0.021f );
			residue.atom[ 6 ] = new Atom( "H5'2", -1.704f, 3.602f, 0.805f, 0.021f );
			residue.atom[ 7 ] = new Atom( "C4'", -1.370f, 1.523f, 1.163f, 0.036f );
			residue.atom[ 8 ] = new Atom( "H4'", -2.435f, 1.359f, 0.999f, 0.056f );
			residue.atom[ 9 ] = new Atom( "O4'", -0.517f, 1.321f, 0.001f, -0.368f );
			residue.atom[ 10 ] = new Atom( "C1'", 0.000f, 0.000f, 0.000f, 0.376f );
			residue.atom[ 11 ] = new Atom( "H1'", -0.348f, -0.526f, -0.889f, 0.009f );
			residue.atom[ 12 ] = new Atom( "C3'", -0.833f, 0.500f, 2.166f, 0.233f );
			residue.atom[ 13 ] = new Atom( "H3'", 0.020f, 0.890f, 2.720f, 0.025f );
			residue.atom[ 14 ] = new Atom( "C2'", -0.554f, -0.686f, 1.245f, -0.307f );
			residue.atom[ 15 ] = new Atom( "H2'1", -1.493f, -1.182f, 0.999f, 0.081f );
			residue.atom[ 16 ] = new Atom( "H2'2", 0.317f, -1.670f, 1.807f, 0.081f );
			residue.atom[ 17 ] = new Atom( "O3'", -1.744f, 0.132f, 3.299f, -0.509f );
			residue.atom[ 18 ] = new Atom( "N9", 1.470f, 0.000f, 0.000f );
			residue.atom[ 19 ] = new Atom( "C8", 2.413f, -1.192f, -0.001f );
			residue.atom[ 20 ] = new Atom( "H8", 2.133f, -2.235f, -0.001f );
			residue.atom[ 21 ] = new Atom( "N7", 3.647f, -0.703f, -0.001f );
			residue.atom[ 22 ] = new Atom( "H7", 4.502f, -1.240f, -0.002f );
			residue.atom[ 23 ] = new Atom( "C5", 3.549f, 0.617f, -0.001f );
			residue.atom[ 24 ] = new Atom( "C6", 4.637f, 1.582f, -0.006f );
			residue.atom[ 25 ] = new Atom( "O6", 5.814f, 1.259f, -0.004f );
			residue.atom[ 26 ] = new Atom( "N1", 4.235f, 2.838f, -0.011f );
			residue.atom[ 27 ] = new Atom( "C2", 2.963f, 3.181f, -0.012f );
			residue.atom[ 28 ] = new Atom( "N2", 2.857f, 4.492f, -0.025f );
			residue.atom[ 29 ] = new Atom( "N3", 1.961f, 2.312f, -0.005f );
			residue.atom[ 30 ] = new Atom( "C3", 0.559f, 2.753f, -0.003f );
			residue.atom[ 31 ] = new Atom( "H31", 0.363f, 3.348f, -0.895f );
			residue.atom[ 32 ] = new Atom( "H32", -0.095f, 1.881f, 0.003f );
			residue.atom[ 33 ] = new Atom( "H33", 0.369f, 3.355f, 0.885f );
			residue.atom[ 34 ] = new Atom( "C4", 2.284f, 1.031f, -0.001f );
			residue.atom[ 35 ] = new Atom( "C10", 4.597f, 6.435f, -0.046f );
			residue.atom[ 36 ] = new Atom( "H101", 4.205f, 6.936f, 0.839f );
			residue.atom[ 37 ] = new Atom( "H102", 5.685f, 6.500f, -0.048f );
			residue.atom[ 38 ] = new Atom( "H103", 4.203f, 6.918f, -0.940f );
			residue.atom[ 39 ] = new Atom( "C11", 4.177f, 4.975f, -0.032f );
			residue.atom[ 40 ] = new Atom( "C12", 5.002f, 3.911f, -0.022f );
			residue.atom[ 41 ] = new Atom( "C13", 6.523f, 3.941f, -0.024f );
			residue.atom[ 42 ] = new Atom( "H131", 6.865f, 4.975f, -0.035f );
			residue.atom[ 43 ] = new Atom( "H132", 6.896f, 3.443f, 0.870f );
			residue.atom[ 44 ] = new Atom( "C14", 7.047f, 3.212f, -1.275f );
			residue.atom[ 45 ] = new Atom( "H141", 6.705f, 2.178f, -1.265f );
			residue.atom[ 46 ] = new Atom( "H142", 6.674f, 3.710f, -2.170f );
			residue.atom[ 47 ] = new Atom( "C15", 8.588f, 3.242f, -1.278f );
			residue.atom[ 48 ] = new Atom( "H15", 8.963f, 2.745f, -0.383f );
			residue.atom[ 49 ] = new Atom( "C16", 9.107f, 2.524f, -2.513f );
			residue.atom[ 50 ] = new Atom( "O17", 10.308f, 2.434f, -2.706f );
			residue.atom[ 51 ] = new Atom( "O18", 8.232f, 1.981f, -3.401f );
			residue.atom[ 52 ] = new Atom( "C19", 8.958f, 1.365f, -4.468f );
			residue.atom[ 53 ] = new Atom( "H191", 8.257f, 0.931f, -5.181f );
			residue.atom[ 54 ] = new Atom( "H192", 9.569f, 2.115f, -4.972f );
			residue.atom[ 55 ] = new Atom( "H193", 9.600f, 0.583f, -4.067f );
			residue.atom[ 56 ] = new Atom( "N20", 9.050f, 4.638f, -1.291f );
			residue.atom[ 57 ] = new Atom( "H20", 8.380f, 5.392f, -1.299f );
			residue.atom[ 58 ] = new Atom( "C21", 10.343f, 4.904f, -1.296f );
			residue.atom[ 59 ] = new Atom( "O22", 10.727f, 6.062f, -1.308f );
			residue.atom[ 60 ] = new Atom( "O23", 11.247f, 3.888f, -1.287f );
			residue.atom[ 61 ] = new Atom( "C24", 12.572f, 4.428f, -1.294f );
			residue.atom[ 62 ] = new Atom( "H241", 13.296f, 3.613f, -1.288f );
			residue.atom[ 63 ] = new Atom( "H242", 12.712f, 5.031f, -2.190f );
			residue.atom[ 64 ] = new Atom( "H243", 12.715f, 5.049f, -0.410f );
			addBackboneAndSugarBonds();
			residue.atom[ 7 ].addBond( 12 );
			residue.atom[ 10 ].addBond( 14, 18 );
			residue.atom[ 12 ].addBond( 13, 14, 17 );
			residue.atom[ 14 ].addBond( 15, 16 );
			residue.atom[ 18 ].addBond( 19, 34 );
			residue.atom[ 19 ].addBond( 20, 21 );
			residue.atom[ 21 ].addBond( 22, 23 );
			residue.atom[ 23 ].addBond( 24, 34 );
			residue.atom[ 24 ].addBond( 25, 26 );
			residue.atom[ 26 ].addBond( 27, 40 );
			residue.atom[ 27 ].addBond( 28, 29 );
			residue.atom[ 28 ].addBond( 39 );
			residue.atom[ 29 ].addBond( 30, 34 );
			residue.atom[ 30 ].addBond( 31, 32, 33 );
			residue.atom[ 35 ].addBond( 36, 37, 38, 39 );
			residue.atom[ 39 ].addBond( 40 );
			residue.atom[ 40 ].addBond( 41 );
			residue.atom[ 41 ].addBond( 42, 43, 44 );
			residue.atom[ 44 ].addBond( 45, 46, 47 );
			residue.atom[ 47 ].addBond( 48, 49, 56 );
			residue.atom[ 49 ].addBond( 50, 51 );
			residue.atom[ 51 ].addBond( 52 );
			residue.atom[ 52 ].addBond( 53, 54, 55 );
			residue.atom[ 56 ].addBond( 57, 58 );
			residue.atom[ 58 ].addBond( 59, 60 );
			residue.atom[ 60 ].addBond( 61 );
			residue.atom[ 61 ].addBond( 62, 63, 64 );
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

	void addBackboneAndSugarBonds(){
		residue.atom[ 0 ].addBond( 1, 2, 3 );
		residue.atom[ 3 ].addBond( 4 );
		residue.atom[ 4 ].addBond( 5, 6, 7 );
		residue.atom[ 7 ].addBond( 8, 9 );
		residue.atom[ 9 ].addBond( 10 );
		residue.atom[ 10 ].addBond( 11 );
	}

	void createResidue( String residueName, int numberOfResidueAtoms ){
		residue = new Residue();
		residue.name = residueName;
		residue.numberOfAtoms = numberOfResidueAtoms;
		residue.atom = new Atom[ numberOfResidueAtoms ];
		residue.actualCoordinates = new float[ 3 * residue.numberOfAtoms ];
		residue.mat = new Matrix3D();
	}

	public void connectNAResidues( Molecule m, int instrand, boolean fiveToThree ){
		int firstAtomNumber = -1;
		int secondAtomNumber = -1;
		String firstAtom = "O3'";
		String secondAtom = "P";
		if ( fiveToThree ){
			firstAtom = "P";
			secondAtom = "O3'";
		}
		int i = m.numberOfAtoms - 1;
		while( ( firstAtomNumber == -1 ) && ( i >= 0 ) ){
			if ( m.atom[ i ].name.equals( firstAtom ) &&
					m.atom[ i ].strandNumber == instrand ){
				firstAtomNumber = i;
			}
			i--;
		}
		while( ( secondAtomNumber == -1 ) && ( i >= 0 ) ){
			if ( m.atom[ i ].name.equals( secondAtom ) &&
					m.atom[ i ].strandNumber == instrand ){
				secondAtomNumber = i;
			}
			i--;
		}
		if ( ( firstAtomNumber != -1 ) && ( secondAtomNumber != -1 ) )
			m.atom[ secondAtomNumber ].addBond( firstAtomNumber );
	}

	public void connectNAResidues( Molecule m, int senseStrand, int antiStrand, 
			boolean fiveToThree ){
		if ( fiveToThree ){
			connectNAResidues( m, senseStrand, true );
			connectNAResidues( m, antiStrand, false );
		}
		else{
			connectNAResidues( m, senseStrand, false );
			connectNAResidues( m, antiStrand, true );
		}
	}

	public void addCounterIons( Molecule m, Frame f, Residue residue ){
		ErrorDialog error;
		Atom C5P = null, O5P = null, P = null, O1P = null, O2P = null;
		for ( int i = 0; i < residue.numberOfAtoms; i++ ){
			String name = residue.atom[ i ].name;
			if ( name.equals( "P" ) )
				P = residue.atom[ i ];
			else if ( name.equals( "O5'" ) )
				O5P = residue.atom[ i ];
			else if ( name.equals( "C5'" ) )
				C5P = residue.atom[ i ];
			else if ( name.equals( "O1P" ) )
				O1P = residue.atom[ i ];
			else if ( name.equals( "O2P" ) )
				O2P = residue.atom[ i ];
			else if ( name.equals( "Na" ) ){
				error = new ErrorDialog( f, 
						"This residue already has a counter ion!", 
						"How many counter ions do you need, anyway?  You know, some residues don't even have *one* counter ion.  Don't get greedy." );
				error.show();
				return;
			} 
		}
		if ( ( P == null ) || ( O5P == null ) || ( C5P == null ) || 
				( O1P == null ) || ( O2P == null ) ){
			error = new ErrorDialog( f, "Unable to add counter ion.", 
					"Internal error" );
			error.show();
			return;
		} 
		Atom Na = new Atom( "Na", 0.0f, 0.0f, 0.0f );
		Na.setBondDistance( m, O5P, P, 1.88 );
		Na.render = 3;
		m.addAtom( P.totalResidueNumber, Na );
		P.addBond( Na.moleculeAtomNumber );	
		Na.setAngle( m, O5P, P, 129.5 );
		double naTorsion = ( O1P.torsion( C5P, O5P, P ) + O2P.torsion( C5P, O5P, P ) )/ 2.0;
		Na.setTorsion( m, C5P, O5P, P, naTorsion );
	}

	public boolean cap( Molecule m, Frame f, boolean fiveToThree, boolean doubleStranded, 
			boolean fiveEnd ){
		ErrorDialog error;
		if ( m.numberOfResidues == 0 ){
			error = new ErrorDialog( f, "There are no residues to cap!  You must first create a nucleic acid mono-/polymer.", "I feel your pain." );
			error.show();
			return false;
		}
		else if ( ( m.numberOfResidues == 1 ) && ( doubleStranded ) ){
			error = new ErrorDialog( f, "There are not enough residues to cap!  There is only one residue in the molecule, yet \"Double Stranded\" has been selected.", "Suggestion: select \"Single Stranded\"." );
			error.show();
			return false;
		}
		Residue residue[] = new Residue[ 2 ];
		residue[ 0 ] = null;
		residue[ 1 ] = null;
		Atom P = null, O1P = null, O2P = null, O3P = null, C3P = null, O5P = null;
		Atom C4P = null, C5P = null;         // residue[ 0 ] is residue to be P-capped
		if ( fiveToThree ){	             // residue[ 1 ] is residue to be H-capped 
			if ( fiveEnd ){
				residue[ 0 ] = m.residue[ 0 ];
				if ( doubleStranded )
					residue[ 1 ] = m.residue[ 1 ];
			}
			else{
				residue[ 1 ] = m.residue[ m.numberOfResidues - 1 ];
				if ( doubleStranded ){
					residue[ 0 ] = m.residue[ m.numberOfResidues - 1 ];
					residue[ 1 ] = m.residue[ m.numberOfResidues - 2 ];
				}
			}
		}
		else{
			if ( fiveEnd ){
				residue[ 0 ] = m.residue[ m.numberOfResidues - 1 ];
				if ( doubleStranded ){
					residue[ 0 ] = m.residue[ m.numberOfResidues - 2 ];
					residue[ 1 ] = m.residue[ m.numberOfResidues - 1 ];
				}
			}
			else{
				residue[ 1 ] = m.residue[ 0 ];
				if ( doubleStranded ){
					residue[ 0 ] = m.residue[ 1 ];
					residue[ 1 ] = m.residue[ 0 ];
				}
			}
		}
		if ( residue[ 0 ] != null ){
			for ( int i = 0; i < residue[ 0 ].numberOfAtoms; i++ ){	
				String name = residue[ 0 ].atom[ i ].name;
				if ( name.equals( "P" ) )
					P = residue[ 0 ].atom[ i ];
				if ( name.equals( "O1P" ) )
					O1P = residue[ 0 ].atom[ i ];
				if ( name.equals( "O2P" ) )
					O2P = residue[ 0 ].atom[ i ];
				else if ( name.equals( "O5'" ) )
					O5P = residue[ 0 ].atom[ i ];
				else if ( name.equals( "C5'" ) )
					C5P = residue[ 0 ].atom[ i ];
				else if ( name.equals( "O3T" ) ){
					error = new ErrorDialog( f, 
							"This residue is already capped.", 
							"Two caps are *not* better than one." );
					error.show();
					return false;
				} 
			}
			if ( ( O5P == null ) || ( P == null ) || ( C5P == null ) || 
					( O1P == null ) || ( O2P == null ) ){
				error = new ErrorDialog( f, "Unable to cap residue.", 
						"Internal error" );
				error.show();
			} 
			else{
				Atom O3T = new Atom( "O3T", 0.0f, 0.0f, 0.0f );
				O3T.setBondDistance( m, O5P, P, 1.48 );
				m.addAtom( P.totalResidueNumber, O3T );
				P.addBond( O3T.moleculeAtomNumber );	
				O3T.setAngle( m, O5P, P, 109.5 );  
				double O3TTorsion = ( O1P.torsion( C5P, O5P, P ) + 
						O2P.torsion( C5P, O5P, P ) )/ 2.0;
				O3T.setTorsion( m, C5P, O5P, P, O3TTorsion );
				Atom H3T = new Atom( "H3T", 0.0f, 0.0f, 0.0f );
				H3T.setBondDistance( m, P, O3T, 0.9 );
				m.addAtom( O3T.totalResidueNumber, H3T );
				O3T.addBond( H3T.moleculeAtomNumber );	
				H3T.setAngle( m, P, O3T, 109.5 );  
				H3T.setTorsion( m, O5P, P, O3T, 180.0 );
			}
		}
		if ( residue[ 1 ] != null ){
			for ( int i = 0; i < residue[ 1 ].numberOfAtoms; i++ ){	
				String name = residue[ 1 ].atom[ i ].name;
				if ( name.equals( "C3'" ) )
					C3P = residue[ 1 ].atom[ i ];
				else if ( name.equals( "O3'" ) )
					O3P = residue[ 1 ].atom[ i ];
				else if ( name.equals( "C4'" ) )
					C4P = residue[ 1 ].atom[ i ];
				else if ( name.equals( "HT3" ) ){
					error = new ErrorDialog( f, 
							"This residue is already capped!", 
							"You don't need to cap a residue twice, do you?" );
					error.show();
					return false;
				} 
			}
			if ( ( C3P == null ) || ( O3P == null ) || ( C4P == null ) ){
				error = new ErrorDialog( f, "Unable to cap residue.", 
						"Internal error" );
				error.show();
				return false;
			} 
			Atom HP = new Atom( "HT3", 0.0f, 0.0f, 0.0f );
			HP.setBondDistance( m, C3P, O3P, 0.9 );
			m.addAtom( C3P.totalResidueNumber, HP );
			O3P.addBond( HP.moleculeAtomNumber );	
			HP.setAngle( m, C3P, O3P, 109.5 );
			HP.setTorsion( m, C4P, C3P, O3P, 180.0 );
		}
		return true;
	}

}
