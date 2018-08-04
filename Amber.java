package com.goosebumpanalytics.biomer;

public class Amber implements ForceField{

	final int AC=0, AH=1, BC=2, BH=3, BR=4, C=5, C0=6, C2=7, C3=8, C4=9, CA=10;
	final int CB=11, CC=12, CD=13, CE=14, CF=15, CG=16, CH=17, CI=18, CJ=19, CK=20;
	final int CL=21, CM=22, CN=23, CP=24, CQ=25, CR=26, CS=27, CT=28, CU=29, CV=30;
	final int CW=31, CX=32, CY=33, CZ=34, F=35, FE=36, H=37, H2=38, H3=39, H4=40; 
	final int HC=41, HO=42, HS=43, HT=44, HW=45, HY=46, I=47, IM=48, IP=49, LP=50;
	final int M1=51, M2=52, MG=53, N=54, N2=55, N3=56, N4=57, NA=58, NB=59, NC=60;
	final int NO=61, NP=62, NT=63, NZ=64, O=65, O2=66, OA=67, OB=68, OE=69, OH=70;
	final int OM=71, OS=72, OT=73, OW=74, P=75, S=76, S4=77, SF=78, SH=79, W=80;

	Molecule m;
	int numberOfDihedralTerms = 0, numberNonBonded = 0, numberHydrogenBonded = 0;
	double bondStretchTerms[], angleBendTerms[], dihedralAngleTerms[], nonBondedTerms[];
	double halfNonBondedTerms[], hydrogenBondedTerms[];
	double  D2R = 0.01745329251994329576;
	double  R2D = 57.29577951308232090712;

	Amber( Molecule m ){
		this.m = m;
	}

	public void assignType( int atomNumber, int atomType ){
		if ( atomType == 0 )
			m.atom[ atomNumber ].forceFieldType = "AC";
		else if ( atomType == 1 )
			m.atom[ atomNumber ].forceFieldType = "AH";
		else if ( atomType == 2 )
			m.atom[ atomNumber ].forceFieldType = "BC";
		else if ( atomType == 3 )
			m.atom[ atomNumber ].forceFieldType = "BH";
		else if ( atomType == 4 )
			m.atom[ atomNumber ].forceFieldType = "BR";
		else if ( atomType == 5 )
			m.atom[ atomNumber ].forceFieldType = "C";
		else if ( atomType == 6 )
			m.atom[ atomNumber ].forceFieldType = "C0";
		else if ( atomType == 7 )
			m.atom[ atomNumber ].forceFieldType = "C2";
		else if ( atomType == 8 )
			m.atom[ atomNumber ].forceFieldType = "C3";
		else if ( atomType == 9 )
			m.atom[ atomNumber ].forceFieldType = "C4";
		else if ( atomType == 10 )
			m.atom[ atomNumber ].forceFieldType = "CA";
		else if ( atomType == 11 )
			m.atom[ atomNumber ].forceFieldType = "CB";
		else if ( atomType == 12 )
			m.atom[ atomNumber ].forceFieldType = "CC";
		else if ( atomType == 13 )
			m.atom[ atomNumber ].forceFieldType = "CD";
		else if ( atomType == 14 )
			m.atom[ atomNumber ].forceFieldType = "CE";
		else if ( atomType == 15 )
			m.atom[ atomNumber ].forceFieldType = "CF";
		else if ( atomType == 16 )
			m.atom[ atomNumber ].forceFieldType = "CG";
		else if ( atomType == 17 )
			m.atom[ atomNumber ].forceFieldType = "CH";
		else if ( atomType == 18 )
			m.atom[ atomNumber ].forceFieldType = "CI";
		else if ( atomType == 19 )
			m.atom[ atomNumber ].forceFieldType = "CJ";
		else if ( atomType == 20 )
			m.atom[ atomNumber ].forceFieldType = "CK";
		else if ( atomType == 21 )
			m.atom[ atomNumber ].forceFieldType = "CL";
		else if ( atomType == 22 )
			m.atom[ atomNumber ].forceFieldType = "CM";
		else if ( atomType == 23 )
			m.atom[ atomNumber ].forceFieldType = "CN";
		else if ( atomType == 24 )
			m.atom[ atomNumber ].forceFieldType = "CP";
		else if ( atomType == 25 )
			m.atom[ atomNumber ].forceFieldType = "CQ";
		else if ( atomType == 26 )
			m.atom[ atomNumber ].forceFieldType = "CR";
		else if ( atomType == 27 )
			m.atom[ atomNumber ].forceFieldType = "CS";
		else if ( atomType == 28 )
			m.atom[ atomNumber ].forceFieldType = "CT";
		else if ( atomType == 29 )
			m.atom[ atomNumber ].forceFieldType = "CU";
		else if ( atomType == 30 )
			m.atom[ atomNumber ].forceFieldType = "CV";
		else if ( atomType == 31 )
			m.atom[ atomNumber ].forceFieldType = "CW";
		else if ( atomType == 32 )
			m.atom[ atomNumber ].forceFieldType = "CX";
		else if ( atomType == 33 )
			m.atom[ atomNumber ].forceFieldType = "CY";
		else if ( atomType == 34 )
			m.atom[ atomNumber ].forceFieldType = "CZ";
		else if ( atomType == 35 )
			m.atom[ atomNumber ].forceFieldType = "F";
		else if ( atomType == 36 )
			m.atom[ atomNumber ].forceFieldType = "FE";
		else if ( atomType == 37 )
			m.atom[ atomNumber ].forceFieldType = "H";
		else if ( atomType == 38 )
			m.atom[ atomNumber ].forceFieldType = "H2";
		else if ( atomType == 39 )
			m.atom[ atomNumber ].forceFieldType = "H3";
		else if ( atomType == 40 )
			m.atom[ atomNumber ].forceFieldType = "H4";
		else if ( atomType == 41 )
			m.atom[ atomNumber ].forceFieldType = "HC";
		else if ( atomType == 42 )
			m.atom[ atomNumber ].forceFieldType = "HO";
		else if ( atomType == 43 )
			m.atom[ atomNumber ].forceFieldType = "HS";
		else if ( atomType == 44 )
			m.atom[ atomNumber ].forceFieldType = "HT";
		else if ( atomType == 45 )
			m.atom[ atomNumber ].forceFieldType = "HW";
		else if ( atomType == 46 )
			m.atom[ atomNumber ].forceFieldType = "HY";
		else if ( atomType == 47 )
			m.atom[ atomNumber ].forceFieldType = "I";
		else if ( atomType == 48 )
			m.atom[ atomNumber ].forceFieldType = "IM";
		else if ( atomType == 49 )
			m.atom[ atomNumber ].forceFieldType = "IP";
		else if ( atomType == 50 )
			m.atom[ atomNumber ].forceFieldType = "LP";
		else if ( atomType == 51 )
			m.atom[ atomNumber ].forceFieldType = "M1";
		else if ( atomType == 52 )
			m.atom[ atomNumber ].forceFieldType = "M2";
		else if ( atomType == 53 )
			m.atom[ atomNumber ].forceFieldType = "MG";
		else if ( atomType == 54 )
			m.atom[ atomNumber ].forceFieldType = "N";
		else if ( atomType == 55 )
			m.atom[ atomNumber ].forceFieldType = "N2";
		else if ( atomType == 56 )
			m.atom[ atomNumber ].forceFieldType = "N3";
		else if ( atomType == 57 )
			m.atom[ atomNumber ].forceFieldType = "N4";
		else if ( atomType == 58 )
			m.atom[ atomNumber ].forceFieldType = "NA";
		else if ( atomType == 59 )
			m.atom[ atomNumber ].forceFieldType = "NB";
		else if ( atomType == 60 )
			m.atom[ atomNumber ].forceFieldType = "NC";
		else if ( atomType == 61 )
			m.atom[ atomNumber ].forceFieldType = "NO";
		else if ( atomType == 62 )
			m.atom[ atomNumber ].forceFieldType = "NP";
		else if ( atomType == 63 )
			m.atom[ atomNumber ].forceFieldType = "NT";
		else if ( atomType == 64 )
			m.atom[ atomNumber ].forceFieldType = "NZ";
		else if ( atomType == 65 )
			m.atom[ atomNumber ].forceFieldType = "O";
		else if ( atomType == 66 )
			m.atom[ atomNumber ].forceFieldType = "O2";
		else if ( atomType == 67 )
			m.atom[ atomNumber ].forceFieldType = "OA";
		else if ( atomType == 68 )
			m.atom[ atomNumber ].forceFieldType = "OB";
		else if ( atomType == 69 )
			m.atom[ atomNumber ].forceFieldType = "OE";
		else if ( atomType == 70 )
			m.atom[ atomNumber ].forceFieldType = "OH";
		else if ( atomType == 71 )
			m.atom[ atomNumber ].forceFieldType = "OM";
		else if ( atomType == 72 )
			m.atom[ atomNumber ].forceFieldType = "OS";
		else if ( atomType == 73 )
			m.atom[ atomNumber ].forceFieldType = "OT";
		else if ( atomType == 74 )
			m.atom[ atomNumber ].forceFieldType = "OW";
		else if ( atomType == 75 )
			m.atom[ atomNumber ].forceFieldType = "P";
		else if ( atomType == 76 )
			m.atom[ atomNumber ].forceFieldType = "S";
		else if ( atomType == 77 )
			m.atom[ atomNumber ].forceFieldType = "S4";
		else if ( atomType == 78 )
			m.atom[ atomNumber ].forceFieldType = "SF";
		else if ( atomType == 79 )
			m.atom[ atomNumber ].forceFieldType = "SH";
		else if ( atomType == 80 )
			m.atom[ atomNumber ].forceFieldType = "W";
	}

	public boolean getStretchParameters( int atomType1, int atomType2, double forceConstant[],
			double equilibriumDistance[] ){
		if ( atomType2 < atomType1 ){
			int temp = atomType2;
			atomType2 = atomType1;
			atomType1 = temp;
		}
		if ( ( atomType1 == C ) && ( atomType2 == C2 ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.522000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == C3 ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.522000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CA ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 447;
			equilibriumDistance[ 0 ] = 1.419000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CD ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.522000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CJ ) ){
			forceConstant[ 0 ] = 410;
			equilibriumDistance[ 0 ] = 1.444000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) ){
			forceConstant[ 0 ] = 410;
			equilibriumDistance[ 0 ] = 1.444000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.522000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 490;
			equilibriumDistance[ 0 ] = 1.335000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 424;
			equilibriumDistance[ 0 ] = 1.383000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 418;
			equilibriumDistance[ 0 ] = 1.388000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NC ) ){
			forceConstant[ 0 ] = 457;
			equilibriumDistance[ 0 ] = 1.358000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == O ) ){
			forceConstant[ 0 ] = 570;
			equilibriumDistance[ 0 ] = 1.229000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == O2 ) ){
			forceConstant[ 0 ] = 656;
			equilibriumDistance[ 0 ] = 1.250000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 450;
			equilibriumDistance[ 0 ] = 1.364000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) ){
			forceConstant[ 0 ] = 260;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C3 ) ){
			forceConstant[ 0 ] = 260;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C4 ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.495000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CA ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 450;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.504000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) ){
			forceConstant[ 0 ] = 260;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.449000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.463000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N3 ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == NT ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 386;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OS ) ){
			forceConstant[ 0 ] = 320;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == S ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == SH ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 300;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) ){
			forceConstant[ 0 ] = 260;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CM ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.449000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.463000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N3 ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.475000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 386;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == OS ) ){
			forceConstant[ 0 ] = 320;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == S ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == SH ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 388;
			equilibriumDistance[ 0 ] = 1.459000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CG ) ){
			forceConstant[ 0 ] = 546;
			equilibriumDistance[ 0 ] = 1.352000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.495000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CW ) ){
			forceConstant[ 0 ] = 546;
			equilibriumDistance[ 0 ] = 1.352000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.404000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CD ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CJ ) ){
			forceConstant[ 0 ] = 427;
			equilibriumDistance[ 0 ] = 1.433000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CM ) ){
			forceConstant[ 0 ] = 427;
			equilibriumDistance[ 0 ] = 1.433000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 481;
			equilibriumDistance[ 0 ] = 1.340000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 427;
			equilibriumDistance[ 0 ] = 1.381000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NC ) ){
			forceConstant[ 0 ] = 483;
			equilibriumDistance[ 0 ] = 1.339000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 520;
			equilibriumDistance[ 0 ] = 1.370000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CC ) ){
			forceConstant[ 0 ] = 460;
			equilibriumDistance[ 0 ] = 1.440000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CD ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 447;
			equilibriumDistance[ 0 ] = 1.419000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CY ) ){
			forceConstant[ 0 ] = 450;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 436;
			equilibriumDistance[ 0 ] = 1.374000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 414;
			equilibriumDistance[ 0 ] = 1.391000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NC ) ){
			forceConstant[ 0 ] = 461;
			equilibriumDistance[ 0 ] = 1.354000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CD ) ){
			forceConstant[ 0 ] = 250;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CF ) ){
			forceConstant[ 0 ] = 512;
			equilibriumDistance[ 0 ] = 1.375000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CG ) ){
			forceConstant[ 0 ] = 518;
			equilibriumDistance[ 0 ] = 1.371000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.504000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CV ) ){
			forceConstant[ 0 ] = 512;
			equilibriumDistance[ 0 ] = 1.375000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CW ) ){
			forceConstant[ 0 ] = 518;
			equilibriumDistance[ 0 ] = 1.371000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 422;
			equilibriumDistance[ 0 ] = 1.385000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 410;
			equilibriumDistance[ 0 ] = 1.394000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NO ) ){
			forceConstant[ 0 ] = 180;
			equilibriumDistance[ 0 ] = 1.380000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NP ) ){
			forceConstant[ 0 ] = 180;
			equilibriumDistance[ 0 ] = 1.380000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CD ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 469;
			equilibriumDistance[ 0 ] = 1.400000;
			return( true );
		}
		else if ( ( atomType1 == CE ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 440;
			equilibriumDistance[ 0 ] = 1.371000;
			return( true );
		}
		else if ( ( atomType1 == CE ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 529;
			equilibriumDistance[ 0 ] = 1.304000;
			return( true );
		}
		else if ( ( atomType1 == CF ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 410;
			equilibriumDistance[ 0 ] = 1.394000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 427;
			equilibriumDistance[ 0 ] = 1.381000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) ){
			forceConstant[ 0 ] = 260;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.449000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N3 ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.475000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == NT ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 386;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OS ) ){
			forceConstant[ 0 ] = 320;
			equilibriumDistance[ 0 ] = 1.425000;
			return( true );
		}
		else if ( ( atomType1 == CI ) && ( atomType2 == NC ) ){
			forceConstant[ 0 ] = 502;
			equilibriumDistance[ 0 ] = 1.324000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CJ ) ){
			forceConstant[ 0 ] = 549;
			equilibriumDistance[ 0 ] = 1.350000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CM ) ){
			forceConstant[ 0 ] = 549;
			equilibriumDistance[ 0 ] = 1.350000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 448;
			equilibriumDistance[ 0 ] = 1.365000;
			return( true );
		}
		else if ( ( atomType1 == CK ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CK ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 440;
			equilibriumDistance[ 0 ] = 1.371000;
			return( true );
		}
		else if ( ( atomType1 == CK ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 529;
			equilibriumDistance[ 0 ] = 1.304000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CM ) ){
			forceConstant[ 0 ] = 549;
			equilibriumDistance[ 0 ] = 1.350000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.510000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 448;
			equilibriumDistance[ 0 ] = 1.365000;
			return( true );
		}
		else if ( ( atomType1 == CN ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 428;
			equilibriumDistance[ 0 ] = 1.380000;
			return( true );
		}
		else if ( ( atomType1 == CP ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 477;
			equilibriumDistance[ 0 ] = 1.343000;
			return( true );
		}
		else if ( ( atomType1 == CP ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 488;
			equilibriumDistance[ 0 ] = 1.335000;
			return( true );
		}
		else if ( ( atomType1 == CQ ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CQ ) && ( atomType2 == NC ) ){
			forceConstant[ 0 ] = 502;
			equilibriumDistance[ 0 ] = 1.324000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 477;
			equilibriumDistance[ 0 ] = 1.343000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 488;
			equilibriumDistance[ 0 ] = 1.335000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) ){
			forceConstant[ 0 ] = 310;
			equilibriumDistance[ 0 ] = 1.526000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 331;
			equilibriumDistance[ 0 ] = 1.090000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.449000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.463000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N3 ) ){
			forceConstant[ 0 ] = 367;
			equilibriumDistance[ 0 ] = 1.471000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N4 ) ){
			forceConstant[ 0 ] = 337;
			equilibriumDistance[ 0 ] = 1.475000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 320;
			equilibriumDistance[ 0 ] = 1.410000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == OS ) ){
			forceConstant[ 0 ] = 320;
			equilibriumDistance[ 0 ] = 1.410000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == S ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == SH ) ){
			forceConstant[ 0 ] = 222;
			equilibriumDistance[ 0 ] = 1.810000;
			return( true );
		}
		else if ( ( atomType1 == CV ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CV ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 410;
			equilibriumDistance[ 0 ] = 1.394000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == HC ) ){
			forceConstant[ 0 ] = 340;
			equilibriumDistance[ 0 ] = 1.080000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 427;
			equilibriumDistance[ 0 ] = 1.381000;
			return( true );
		}
		else if ( ( atomType1 == CX ) && ( atomType2 == CY ) ){
			forceConstant[ 0 ] = 450;
			equilibriumDistance[ 0 ] = 1.350000;
			return( true );
		}
		else if ( ( atomType1 == FE ) && ( atomType2 == NB ) ){
			forceConstant[ 0 ] = 70;
			equilibriumDistance[ 0 ] = 2.050000;
			return( true );
		}
		else if ( ( atomType1 == FE ) && ( atomType2 == NO ) ){
			forceConstant[ 0 ] = 70;
			equilibriumDistance[ 0 ] = 2.000000;
			return( true );
		}
		else if ( ( atomType1 == FE ) && ( atomType2 == NP ) ){
			forceConstant[ 0 ] = 70;
			equilibriumDistance[ 0 ] = 2.000000;
			return( true );
		}
		else if ( ( atomType1 == FE ) && ( atomType2 == W ) ){
			forceConstant[ 0 ] = 70;
			equilibriumDistance[ 0 ] = 2.010000;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == NT ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N3 ) ){
			forceConstant[ 0 ] = 434;
			equilibriumDistance[ 0 ] = 1.010000;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == OH ) ){
			forceConstant[ 0 ] = 553;
			equilibriumDistance[ 0 ] = 0.960000;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == OS ) ){
			forceConstant[ 0 ] = 553;
			equilibriumDistance[ 0 ] = 0.960000;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == SH ) ){
			forceConstant[ 0 ] = 274;
			equilibriumDistance[ 0 ] = 1.336000;
			return( true );
		}
		else if ( ( atomType1 == HW ) && ( atomType2 == HW ) ){
			forceConstant[ 0 ] = 553;
			equilibriumDistance[ 0 ] = 1.513600;
			return( true );
		}
		else if ( ( atomType1 == HW ) && ( atomType2 == OW ) ){
			forceConstant[ 0 ] = 553;
			equilibriumDistance[ 0 ] = 0.957200;
			return( true );
		}
		else if ( ( atomType1 == LP ) && ( atomType2 == S ) ){
			forceConstant[ 0 ] = 600;
			equilibriumDistance[ 0 ] = 0.679000;
			return( true );
		}
		else if ( ( atomType1 == LP ) && ( atomType2 == SH ) ){
			forceConstant[ 0 ] = 600;
			equilibriumDistance[ 0 ] = 0.679000;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == P ) ){
			forceConstant[ 0 ] = 525;
			equilibriumDistance[ 0 ] = 1.480000;
			return( true );
		}
		else if ( ( atomType1 == OH ) && ( atomType2 == P ) ){
			forceConstant[ 0 ] = 230;
			equilibriumDistance[ 0 ] = 1.610000;
			return( true );
		}
		else if ( ( atomType1 == OS ) && ( atomType2 == P ) ){
			forceConstant[ 0 ] = 230;
			equilibriumDistance[ 0 ] = 1.610000;
			return( true );
		}
		else if ( ( atomType1 == S ) && ( atomType2 == S ) ){
			forceConstant[ 0 ] = 166;
			equilibriumDistance[ 0 ] = 2.038000;
			return( true );
		}
		else{
			forceConstant[ 0 ] = 317;
			equilibriumDistance[ 0 ] = 1.522;
			System.out.println( "Ambstretch DEFAULTING TO C-C2" );
		}
		return( false );
	}

	public boolean getBendParameters( int atomType1, int atomType2, int atomType3, 
			double forceConstant[], double equilibriumAngle[] ){
		if ( atomType3 < atomType1 ){
			int temp = atomType3;
			atomType3 = atomType1;
			atomType1 = temp;
		}
		if ( ( atomType1 == C ) && ( atomType2 == C2 ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == C2 ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 110.300000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == C2 ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CA ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CA ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CB ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 119.200000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CB ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 130.000000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CD ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 110.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 110.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CH ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CJ ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) && ( atomType3 == CM ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CM ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CT ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 110.100000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == CT ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 121.900000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 121.900000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 121.900000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 121.900000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.800000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.600000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.600000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) && ( atomType3 == CM ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.600000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.600000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N4 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.200000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NA ) && ( atomType3 == C ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.400000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NA ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.200000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 116.800000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NC ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 113.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 116.600000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 115.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 46.500000;
			equilibriumAngle[ 0 ] = 112.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 114.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C4 ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.600000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C4 ) && ( atomType3 == CG ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == C4 ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CA ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CA ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CB ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CB ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == CF ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 131.900000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == CG ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 129.050000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == CV ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 131.900000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 129.050000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 122.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CC ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.050000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 118.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 38.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N2 ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 123.200000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N2 ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N2 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N3 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == NT ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 111.800000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 111.800000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == S ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 62.000000;
			equilibriumAngle[ 0 ] = 98.900000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == S ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == S ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 68.000000;
			equilibriumAngle[ 0 ] = 103.700000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == SH ) && ( atomType3 == HS ) ){
			forceConstant[ 0 ] = 44.000000;
			equilibriumAngle[ 0 ] = 96.000000;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == SH ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == C ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 116.600000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.400000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CB ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CB ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && ( atomType3 == C3 ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CM ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 38.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N2 ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 123.200000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N2 ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N3 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N4 ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.800000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N4 ) && ( atomType3 == CE ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == N4 ) && ( atomType3 == CK ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == S ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == S ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 68.000000;
			equilibriumAngle[ 0 ] = 103.700000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == SH ) && ( atomType3 == HS ) ){
			forceConstant[ 0 ] = 44.000000;
			equilibriumAngle[ 0 ] = 96.000000;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == SH ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 115.600000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CB ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 134.900000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CB ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 134.900000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CB ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 108.800000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CG ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 108.700000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 115.600000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CW ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CW ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 108.700000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == C ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 114.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CB ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 117.300000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CB ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 116.200000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CB ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 132.400000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CD ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CJ ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CM ) && ( atomType3 == CM ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CM ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 123.300000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CN ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 122.700000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CN ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 132.800000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 114.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == N2 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 123.200000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == N2 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == N2 ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == N2 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 118.000000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NC ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 112.200000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NC ) && ( atomType3 == CI ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 118.600000;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == NC ) && ( atomType3 == CQ ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 118.600000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.300000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C4 ) && ( atomType3 == CG ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 106.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.600000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C4 ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 106.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == C4 ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 126.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CA ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 123.500000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.300000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 107.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) && ( atomType3 == CY ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 106.200000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 110.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CB ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 127.700000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CC ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 124.200000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CC ) && ( atomType3 == NO ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 110.300000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CC ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 110.300000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CD ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CN ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 122.700000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CN ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 104.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CY ) && ( atomType3 == CX ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) && ( atomType3 == CE ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) && ( atomType3 == CK ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.400000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == N4 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 127.300000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NB ) && ( atomType3 == CE ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 103.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NB ) && ( atomType3 == CK ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 103.800000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NC ) && ( atomType3 == CI ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.000000;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == NC ) && ( atomType3 == CQ ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 113.100000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CB ) && ( atomType3 == CY ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CD ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 124.400000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CF ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 109.900000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CG ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.900000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 113.100000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CV ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CV ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CW ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == CW ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NA ) && ( atomType3 == CP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 107.300000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NA ) && ( atomType3 == CR ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NB ) && ( atomType3 == CP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.300000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NB ) && ( atomType3 == CR ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NO ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 105.400000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NO ) && ( atomType3 == FE ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 127.300000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NP ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 105.400000;
			return( true );
		}
		else if ( ( atomType1 == CC ) && ( atomType2 == NP ) && ( atomType3 == FE ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 127.300000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == C ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CA ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CB ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 116.200000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CC ) && ( atomType3 == NO ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.500000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CC ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.500000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CD ) && ( atomType3 == CD ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CD ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CD ) && ( atomType2 == CN ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 132.800000;
			return( true );
		}
		else if ( ( atomType1 == CE ) && ( atomType2 == N4 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == CE ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == CE ) && ( atomType2 == N4 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 127.300000;
			return( true );
		}
		else if ( ( atomType1 == CF ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.900000;
			return( true );
		}
		else if ( ( atomType1 == CF ) && ( atomType2 == NB ) && ( atomType3 == CP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.300000;
			return( true );
		}
		else if ( ( atomType1 == CF ) && ( atomType2 == NB ) && ( atomType3 == CR ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 105.300000;
			return( true );
		}
		else if ( ( atomType1 == CF ) && ( atomType2 == NB ) && ( atomType3 == FE ) ){
			forceConstant[ 0 ] = 130.000000;
			equilibriumAngle[ 0 ] = 124.800000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 108.750000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == CC ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 109.900000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == NA ) && ( atomType3 == CN ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.600000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == NA ) && ( atomType3 == CP ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 107.300000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == NA ) && ( atomType3 == CR ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 107.300000;
			return( true );
		}
		else if ( ( atomType1 == CG ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 126.350000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 116.600000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.400000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 65.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 115.000000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 112.400000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 114.700000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && ( atomType3 == SH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 108.600000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 63.000000;
			equilibriumAngle[ 0 ] = 111.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == NT ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 38.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N3 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N4 ) && ( atomType3 == CJ ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == N4 ) && ( atomType3 == CK ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == NT ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OS ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 111.800000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OS ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == C ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 114.100000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 125.300000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.100000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.500000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CJ ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == CM ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 85.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CJ ) && ( atomType2 == N4 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.200000;
			return( true );
		}
		else if ( ( atomType1 == CK ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 128.800000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == C ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 114.100000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 125.300000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.100000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.500000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CJ ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CM ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CM ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.700000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CM ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == N4 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 121.200000;
			return( true );
		}
		else if ( ( atomType1 == CM ) && ( atomType2 == N4 ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.200000;
			return( true );
		}
		else if ( ( atomType1 == CN ) && ( atomType2 == CA ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CN ) && ( atomType2 == NA ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.600000;
			return( true );
		}
		else if ( ( atomType1 == CN ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 123.100000;
			return( true );
		}
		else if ( ( atomType1 == CP ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 126.350000;
			return( true );
		}
		else if ( ( atomType1 == CP ) && ( atomType2 == NB ) && ( atomType3 == FE ) ){
			forceConstant[ 0 ] = 130.000000;
			equilibriumAngle[ 0 ] = 124.800000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == NA ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CR ) && ( atomType2 == NB ) && ( atomType3 == CV ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == C ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 116.600000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.400000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 117.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == C4 ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 125.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CC ) && ( atomType3 == CV ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CC ) && ( atomType3 == CW ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CC ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 40.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.700000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 111.200000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 114.700000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == CT ) && ( atomType3 == SH ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 108.600000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 118.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 38.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N2 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 118.400000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N3 ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 113.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N3 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == OS ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 60.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == S ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 62.000000;
			equilibriumAngle[ 0 ] = 98.900000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == S ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == S ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 68.000000;
			equilibriumAngle[ 0 ] = 103.700000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == SH ) && ( atomType3 == HS ) ){
			forceConstant[ 0 ] = 44.000000;
			equilibriumAngle[ 0 ] = 96.000000;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == SH ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == CV ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == C4 ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 126.800000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == CC ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == CW ) && ( atomType2 == NA ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == N ) && ( atomType3 == H ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N2 ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == NT ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N2 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N3 ) && ( atomType3 == H3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CK ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 123.050000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CK ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 123.050000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CM ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 119.100000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CQ ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 115.450000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CR ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CR ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == HC ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == N ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == N3 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == N4 ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CT ) && ( atomType3 == SH ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CV ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CW ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == OH ) && ( atomType3 == HO ) ){
			forceConstant[ 0 ] = 47.000000;
			equilibriumAngle[ 0 ] = 104.500000;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == OH ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 45.000000;
			equilibriumAngle[ 0 ] = 108.500000;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == SH ) && ( atomType3 == HS ) ){
			forceConstant[ 0 ] = 35.000000;
			equilibriumAngle[ 0 ] = 92.070000;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == SH ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == HW ) && ( atomType2 == OW ) && ( atomType3 == HW ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 104.520000;
			return( true );
		}
		else if ( ( atomType1 == LP ) && ( atomType2 == S ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 160.000000;
			return( true );
		}
		else if ( ( atomType1 == LP ) && ( atomType2 == S ) && ( atomType3 == S ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 96.700000;
			return( true );
		}
		else if ( ( atomType1 == LP ) && ( atomType2 == SH ) && ( atomType3 == LP ) ){
			forceConstant[ 0 ] = 600.000000;
			equilibriumAngle[ 0 ] = 160.000000;
			return( true );
		}
		else if ( ( atomType1 == N ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 122.900000;
			return( true );
		}
		else if ( ( atomType1 == N2 ) && ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == N2 ) && ( atomType2 == CA ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 116.000000;
			return( true );
		}
		else if ( ( atomType1 == N2 ) && ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 119.300000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == C ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 115.400000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == C ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 118.600000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.900000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == CB ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 126.200000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == CE ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 113.900000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == CH ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == CK ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 113.900000;
			return( true );
		}
		else if ( ( atomType1 == N4 ) && ( atomType2 == CT ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 50.000000;
			equilibriumAngle[ 0 ] = 109.500000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 120.600000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 123.300000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CP ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 110.750000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CP ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 111.600000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CR ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CR ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 120.000000;
			return( true );
		}
		else if ( ( atomType1 == NB ) && ( atomType2 == FE ) && ( atomType3 == NB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			return( true );
		}
		else if ( ( atomType1 == NB ) && ( atomType2 == FE ) && ( atomType3 == NO ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 90.000000;
			return( true );
		}
		else if ( ( atomType1 == NB ) && ( atomType2 == FE ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 90.000000;
			return( true );
		}
		else if ( ( atomType1 == NB ) && ( atomType2 == FE ) && ( atomType3 == W ) ){
			forceConstant[ 0 ] = 30.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			return( true );
		}
		else if ( ( atomType1 == NC ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 122.500000;
			return( true );
		}
		else if ( ( atomType1 == NC ) && ( atomType2 == CI ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 129.100000;
			return( true );
		}
		else if ( ( atomType1 == NC ) && ( atomType2 == CQ ) && ( atomType3 == NC ) ){
			forceConstant[ 0 ] = 70.000000;
			equilibriumAngle[ 0 ] = 129.100000;
			return( true );
		}
		else if ( ( atomType1 == NO ) && ( atomType2 == FE ) && ( atomType3 == NO ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			return( true );
		}
		else if ( ( atomType1 == NO ) && ( atomType2 == FE ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 55.000000;
			equilibriumAngle[ 0 ] = 90.000000;
			return( true );
		}
		else if ( ( atomType1 == NO ) && ( atomType2 == FE ) && ( atomType3 == W ) ){
			forceConstant[ 0 ] = 30.000000;
			equilibriumAngle[ 0 ] = 90.000000;
			return( true );
		}
		else if ( ( atomType1 == NP ) && ( atomType2 == FE ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			return( true );
		}
		else if ( ( atomType1 == NP ) && ( atomType2 == FE ) && ( atomType3 == W ) ){
			forceConstant[ 0 ] = 30.000000;
			equilibriumAngle[ 0 ] = 90.000000;
			return( true );
		}
		else if ( ( atomType1 == O ) && ( atomType2 == C ) && ( atomType3 == O ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 126.000000;
			return( true );
		}
		else if ( ( atomType1 == O ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 126.000000;
			return( true );
		}
		else if ( ( atomType1 == O ) && ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 126.000000;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 80.000000;
			equilibriumAngle[ 0 ] = 126.000000;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == P ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 140.000000;
			equilibriumAngle[ 0 ] = 119.900000;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == P ) && ( atomType3 == OH ) ){
			forceConstant[ 0 ] = 45.000000;
			equilibriumAngle[ 0 ] = 108.230000;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == P ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 108.230000;
			return( true );
		}
		else if ( ( atomType1 == OH ) && ( atomType2 == P ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 45.000000;
			equilibriumAngle[ 0 ] = 102.600000;
			return( true );
		}
		else if ( ( atomType1 == OS ) && ( atomType2 == P ) && ( atomType3 == OS ) ){
			forceConstant[ 0 ] = 45.000000;
			equilibriumAngle[ 0 ] = 102.600000;
			return( true );
		}
		else if ( ( atomType1 == P ) && ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ 0 ] = 100.000000;
			equilibriumAngle[ 0 ] = 120.500000;
			return( true );
		}
		System.out.println( "AmberBend: couldn't find correct angle, defaulting  " );
		forceConstant[ 0 ] = 63.000000;
		equilibriumAngle[ 0 ] = 113.100000;
		return( false );
	}

	public boolean getTorsionParameters( int atomType1, int atomType2, int atomType3, 
			int atomType4, double forceConstant[], double equilibriumAngle[], int terms[], 
			int multiplicity[] ){
		if ( atomType3 < atomType2 ){
			int temp = atomType3;
			atomType3 = atomType2;
			atomType2 = temp;
			temp = atomType4;
			atomType4 = atomType1;
			atomType1 = temp;
		}
		int forceCounter = 0, eqCounter = 0, multCounter = 0, termCounter = 0;
		if ( ( atomType2 == C ) && ( atomType3 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CA ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CB ) ){
			forceConstant[ forceCounter++ ] = 4.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CD ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CJ ) ){
			forceConstant[ forceCounter++ ] = 3.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CM ) ){
			forceConstant[ forceCounter++ ] = 3.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == N ) ){
			forceConstant[ forceCounter++ ] = 10.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 5.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 5.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == NC ) ){
			forceConstant[ forceCounter++ ] = 8.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C ) && ( atomType3 == OH ) ){
			forceConstant[ forceCounter++ ] = 1.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == C2 ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == C4 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == CA ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == CB ) ){
			forceConstant[ forceCounter++ ] = 1.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == CC ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == N ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == N2 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == N3 ) ){
			forceConstant[ forceCounter++ ] = 1.400000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == NT ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == OS ) ){
			forceConstant[ forceCounter++ ] = 1.450000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == S ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == SH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C4 ) && ( atomType3 == CB ) ){
			forceConstant[ forceCounter++ ] = 4.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C4 ) && ( atomType3 == CG ) ){
			forceConstant[ forceCounter++ ] = 23.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C4 ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == C4 ) && ( atomType3 == CW ) ){
			forceConstant[ forceCounter++ ] = 23.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CA ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CB ) ){
			forceConstant[ forceCounter++ ] = 10.200000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CD ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CJ ) ){
			forceConstant[ forceCounter++ ] = 3.700000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CM ) ){
			forceConstant[ forceCounter++ ] = 3.700000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CN ) ){
			forceConstant[ forceCounter++ ] = 10.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ forceCounter++ ] = 6.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 6.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == NC ) ){
			forceConstant[ forceCounter++ ] = 9.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CB ) ){
			forceConstant[ forceCounter++ ] = 16.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CC ) ){
			forceConstant[ forceCounter++ ] = 20.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CD ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CN ) ){
			forceConstant[ forceCounter++ ] = 20.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CY ) ){
			forceConstant[ forceCounter++ ] = 5.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 6.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 5.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == NC ) ){
			forceConstant[ forceCounter++ ] = 8.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CD ) ){
			forceConstant[ forceCounter++ ] = 10.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CF ) ){
			forceConstant[ forceCounter++ ] = 14.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CG ) ){
			forceConstant[ forceCounter++ ] = 15.900000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CV ) ){
			forceConstant[ forceCounter++ ] = 14.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CW ) ){
			forceConstant[ forceCounter++ ] = 15.900000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 5.600000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 4.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == NO ) ){
			forceConstant[ forceCounter++ ] = 6.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == NP ) ){
			forceConstant[ forceCounter++ ] = 6.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CD ) && ( atomType3 == CD ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CD ) && ( atomType3 == CN ) ){
			forceConstant[ forceCounter++ ] = 5.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CE ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 6.700000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CE ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 20.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CF ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 4.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CG ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 6.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N3 ) ){
			forceConstant[ forceCounter++ ] = 1.400000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == NT ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == OS ) ){
			forceConstant[ forceCounter++ ] = 1.450000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CI ) && ( atomType3 == NC ) ){
			forceConstant[ forceCounter++ ] = 13.500000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CJ ) && ( atomType3 == CJ ) ){
			forceConstant[ forceCounter++ ] = 24.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CJ ) && ( atomType3 == CM ) ){
			forceConstant[ forceCounter++ ] = 24.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CJ ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 7.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CK ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 6.700000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CK ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 20.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CM ) && ( atomType3 == CM ) ){
			forceConstant[ forceCounter++ ] = 24.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CM ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CM ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 7.400000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CN ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 12.200000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CP ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 9.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CP ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 10.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CQ ) && ( atomType3 == NC ) ){
			forceConstant[ forceCounter++ ] = 13.500000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CR ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 9.300000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CR ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 10.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == CT ) ){
			forceConstant[ forceCounter++ ] = 1.300000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == N ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == N2 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == N3 ) ){
			forceConstant[ forceCounter++ ] = 1.400000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == N4 ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == OS ) ){
			forceConstant[ forceCounter++ ] = 1.150000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == S ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == SH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CV ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 4.800000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == CW ) && ( atomType3 == NA ) ){
			forceConstant[ forceCounter++ ] = 6.000000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == FE ) && ( atomType3 == NB ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == FE ) && ( atomType3 == NO ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == FE ) && ( atomType3 == NP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == OH ) && ( atomType3 == P ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		else if ( ( atomType2 == OS ) && ( atomType3 == P ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 0;
		}
		if ( ( atomType1 == C ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == HC ) ){
			forceConstant[ forceCounter++ ] = 6.590000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == N4 ) ){
			forceConstant[ forceCounter++ ] = 9.510000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 1.450000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == C2 ) && 
				( atomType3 == S )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CH )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 0.600000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == C2 ) ){
			forceConstant[ forceCounter++ ] = 3.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C2 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == C2 ) && 
				( atomType3 == OS )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 1.450000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 0.600000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == C3 ) ){
			forceConstant[ forceCounter++ ] = 3.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == C3 ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CA ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == HC ) ){
			forceConstant[ forceCounter++ ] = 6.590000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CA ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == N4 ) ){
			forceConstant[ forceCounter++ ] = 9.510000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == C2 ) && 
				( atomType3 == SH )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CH )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == N4 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == N4 ) && ( atomType2 == CH ) && 
				( atomType3 == OS )&& ( atomType4 == CH ) ){
			forceConstant[ forceCounter++ ] = 0.725000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CH ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == HC ) ){
			forceConstant[ forceCounter++ ] = 1.710000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == N4 ) ){
			forceConstant[ forceCounter++ ] = 6.590000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CT )&& ( atomType4 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.067000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == CT ) && 
				( atomType3 == OS )&& ( atomType4 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.200000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == CT ) && 
				( atomType3 == OS )&& ( atomType4 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.383000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.250000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == OS ) && 
				( atomType3 == P )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.750000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == CT ) ){
			forceConstant[ forceCounter++ ] = 0.600000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == CT ) ){
			forceConstant[ forceCounter++ ] = 3.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == CT ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == N )&& ( atomType4 == H ) ){
			forceConstant[ forceCounter++ ] = 0.650000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 1;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == N )&& ( atomType4 == H ) ){
			forceConstant[ forceCounter++ ] = 2.500000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == HC ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == HC ) ){
			forceConstant[ forceCounter++ ] = 1.710000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == HC ) && ( atomType2 == CM ) && 
				( atomType3 == CM )&& ( atomType4 == N4 ) ){
			forceConstant[ forceCounter++ ] = 6.590000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CT )&& ( atomType4 == HC ) ){
			forceConstant[ forceCounter++ ] = 0.067000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == LP ) && ( atomType2 == S ) && 
				( atomType3 == S )&& ( atomType4 == LP ) ){
			forceConstant[ forceCounter++ ] = 0.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == C2 )&& ( atomType4 == N ) ){
			forceConstant[ forceCounter++ ] = 0.200000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CH )&& ( atomType4 == N ) ){
			forceConstant[ forceCounter++ ] = 0.100000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == O ) && ( atomType2 == C ) && 
				( atomType3 == CT )&& ( atomType4 == N ) ){
			forceConstant[ forceCounter++ ] = 0.067000;
			equilibriumAngle[ eqCounter++ ] = 180.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.144000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OH ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.144000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OH ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == C2 )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 2.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == C2 ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 1.000000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == CH ) && 
				( atomType3 == CH )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.144000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 3;
		}
		if ( ( atomType1 == OS ) && ( atomType2 == CT ) && 
				( atomType3 == CT )&& ( atomType4 == OS ) ){
			forceConstant[ forceCounter++ ] = 0.500000;
			equilibriumAngle[ eqCounter++ ] = 0.000000;
			multiplicity[ 0 ] = multCounter++;
			terms[ termCounter++ ] = 2;
		}
		if ( multCounter > 0 )
			return( true );
		return( false );
	}

	public boolean getHydrogenBondedParameters( int atomType1, int atomType2, int c[], int d[] ){
		if ( ( atomType1 < H ) || ( atomType1 > H4 ) ){
			int temp = atomType2;
			atomType2 = atomType1;
			atomType1 = temp;
		}
		if ( ( atomType1 == H ) && ( atomType2 == NB ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == NC ) ){
			c[ 0 ] = 10238;
			d[ 0 ] = 3071;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == O ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == O2 ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == OH ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == S ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == SH ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == NB ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == O ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == O2 ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == OH ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == S ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == HO ) && ( atomType2 == SH ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == NB ) ){
			c[ 0 ] = 14184;
			d[ 0 ] = 3082;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == O ) ){
			c[ 0 ] = 14184;
			d[ 0 ] = 3082;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == O2 ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == OH ) ){
			c[ 0 ] = 14184;
			d[ 0 ] = 3082;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == S ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == HS ) && ( atomType2 == SH ) ){
			c[ 0 ] = 265720;
			d[ 0 ] = 35429;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == NB ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == O ) ){
			c[ 0 ] = 10238;
			d[ 0 ] = 3071;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == O2 ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == OH ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == S ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == SH ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == NB ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == O ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == O2 ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == OH ) ){
			c[ 0 ] = 4019;
			d[ 0 ] = 1409;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == S ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == SH ) ){
			c[ 0 ] = 7557;
			d[ 0 ] = 2385;
			return( true );
		}
		return( false );
	}

	public boolean getNonBondedParameters( int atomType, double r[], double epsilon[] ){
		if ( atomType == C ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == C4 ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == C0 ){
			r[ 0 ] = 1.600000;
			epsilon[ 0 ] = 0.100000;
			return( true );
		}
		else if ( atomType == C2 ){
			r[ 0 ] = 1.925000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == C3 ){
			r[ 0 ] = 2.000000;
			epsilon[ 0 ] = 0.150000;
			return( true );
		}
		else if ( atomType == CA ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CB ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CC ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CD ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CE ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CF ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CG ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CH ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.090000;
			return( true );
		}
		else if ( atomType == CI ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CJ ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CK ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CM ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CN ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CP ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CQ ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CR ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CT ){
			r[ 0 ] = 1.800000;
			epsilon[ 0 ] = 0.060000;
			return( true );
		}
		else if ( atomType == CU ){
			r[ 0 ] = 1.200000;
			epsilon[ 0 ] = 0.050000;
			return( true );
		}
		else if ( atomType == CV ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CW ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CX ){
			r[ 0 ] = 1.925000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == CY ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == FE ){
			r[ 0 ] = 2.000000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == H ){
			r[ 0 ] = 1.000000;
			epsilon[ 0 ] = 0.020000;
			return( true );
		}
		else if ( atomType == H2 ){
			r[ 0 ] = 1.000000;
			epsilon[ 0 ] = 0.020000;
			return( true );
		}
		else if ( atomType == H3 ){
			r[ 0 ] = 1.000000;
			epsilon[ 0 ] = 0.020000;
			return( true );
		}
		else if ( atomType == HC ){
			r[ 0 ] = 1.540000;
			epsilon[ 0 ] = 0.010000;
			return( true );
		}
		else if ( atomType == HO ){
			r[ 0 ] = 1.000000;
			epsilon[ 0 ] = 0.020000;
			return( true );
		}
		else if ( atomType == HS ){
			r[ 0 ] = 1.000000;
			epsilon[ 0 ] = 0.020000;
			return( true );
		}
		else if ( atomType == HW ){
			r[ 0 ] = 0.000000;
			epsilon[ 0 ] = 0.000000;
			return( true );
		}
		else if ( atomType == I ){
			r[ 0 ] = 2.350000;
			epsilon[ 0 ] = 0.400000;
			return( true );
		}
		else if ( atomType == IM ){
			r[ 0 ] = 2.000000;
			epsilon[ 0 ] = 0.300000;
			return( true );
		}
		else if ( atomType == IP ){
			r[ 0 ] = 1.600000;
			epsilon[ 0 ] = 0.050000;
			return( true );
		}
		else if ( atomType == LP ){
			r[ 0 ] = 1.200000;
			epsilon[ 0 ] = 0.016000;
			return( true );
		}
		else if ( atomType == MG ){
			r[ 0 ] = 1.170000;
			epsilon[ 0 ] = 0.100000;
			return( true );
		}
		else if ( atomType == N ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == N4 ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == N2 ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == N3 ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.080000;
			return( true );
		}
		else if ( atomType == NA ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == NB ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == NC ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == NO ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == NP ){
			r[ 0 ] = 1.750000;
			epsilon[ 0 ] = 0.160000;
			return( true );
		}
		else if ( atomType == NT ){
			r[ 0 ] = 1.850000;
			epsilon[ 0 ] = 0.120000;
			return( true );
		}
		else if ( atomType == O ){
			r[ 0 ] = 1.600000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == O2 ){
			r[ 0 ] = 1.600000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == OH ){
			r[ 0 ] = 1.650000;
			epsilon[ 0 ] = 0.150000;
			return( true );
		}
		else if ( atomType == OS ){
			r[ 0 ] = 1.650000;
			epsilon[ 0 ] = 0.150000;
			return( true );
		}
		else if ( atomType == OW ){
			r[ 0 ] = 1.768000;
			epsilon[ 0 ] = 0.152000;
			return( true );
		}
		else if ( atomType == P ){
			r[ 0 ] = 2.100000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == S ){
			r[ 0 ] = 2.000000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == SH ){
			r[ 0 ] = 2.000000;
			epsilon[ 0 ] = 0.200000;
			return( true );
		}
		else if ( atomType == W ){
			r[ 0 ] = 1.650000;
			epsilon[ 0 ] = 0.150000;
			return( true );
		}
		return( false );
	}

	public boolean getImproperTorsionParameters( int atomType1, int atomType2, int atomType3, 
			int atomType4, double forceConstant[], double equilibriumAngle[], int terms[] ){
		if ( ( atomType3 == C ) && ( atomType4 == O ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == O ) && ( atomType2 == C ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == C4 ) && ( atomType4 == HC ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == C4 ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CA ) && ( atomType4 == HC ) ){
			forceConstant[ 0 ] = 2.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CA ) ){
			forceConstant[ 0 ] = 2.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CB ) && ( atomType4 == C4 ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CB ) && ( atomType4 == CA ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CB ) && ( atomType4 == CN ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CN ) && ( atomType2 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CN ) && ( atomType4 == CA ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CN ) && ( atomType4 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CB ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CN ) && ( atomType4 == NA ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == NA ) && ( atomType2 == CN ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == CW ) && ( atomType4 == HC ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == HC ) && ( atomType2 == CW ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == N ) && ( atomType4 == H ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == N ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == N2 ) && ( atomType4 == H2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == N2 ) && ( atomType4 == H3 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H3 ) && ( atomType2 == N2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == NA ) && ( atomType4 == H ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H ) && ( atomType2 == NA ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType3 == NT ) && ( atomType4 == H2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == NT ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == C2 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == C2 ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CA ) && ( atomType3 == NA ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == NA ) && ( atomType3 == CA ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == NO ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == NO ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CB ) && ( atomType3 == NP ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == NP ) && ( atomType3 == CB ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CC ) && ( atomType3 == CC ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N ) && ( atomType4 == C ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N ) && ( atomType4 == C2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == N ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == N3 ) && ( atomType4 == C ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == N3 ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CH ) && ( atomType3 == NT ) && ( atomType4 == C ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NT ) && ( atomType3 == CH ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType2 == CT ) && ( atomType3 == N ) && ( atomType4 == CT ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CT ) && ( atomType2 == N ) && ( atomType3 == CT ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == H2 ) && ( atomType3 == N ) && ( atomType4 == H2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N ) && ( atomType3 == H2 ) ){
			forceConstant[ 0 ] = 1.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == N2 ) && ( atomType3 == CA ) && ( atomType4 == N2 ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == N2 ) && ( atomType2 == CA ) && ( atomType3 == N2 ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType2 == O2 ) && ( atomType3 == C ) && ( atomType4 == O2 ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == O2 ) && ( atomType2 == C ) && ( atomType3 == O2 ) ){
			forceConstant[ 0 ] = 10.500000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == C4 ) && ( atomType2 == NA ) && 
				( atomType3 == CA )&& ( atomType4 == CA ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == CA ) && ( atomType2 == CA ) && 
				( atomType3 == NA )&& ( atomType4 == C4 ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 2;
			return( true );
		}
		else if ( ( atomType1 == C2 ) && ( atomType2 == CH ) && 
				( atomType3 == C )&& ( atomType4 == N3 ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == N3 ) && ( atomType2 == C ) && 
				( atomType3 == CH )&& ( atomType4 == C2 ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == CA )&& ( atomType4 == C3 ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CA ) && 
				( atomType3 == CH )&& ( atomType4 == C3 ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C3 ) && ( atomType2 == CH ) && 
				( atomType3 == NT )&& ( atomType4 == C ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == C ) && ( atomType2 == NT ) && 
				( atomType3 == CH )&& ( atomType4 == C3 ) ){
			forceConstant[ 0 ] = 14.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == CH ) && ( atomType2 == CH ) && 
				( atomType3 == C )&& ( atomType4 == N3 ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == N3 ) && ( atomType2 == C ) && 
				( atomType3 == CH )&& ( atomType4 == CH ) ){
			forceConstant[ 0 ] = 7.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == CH ) && 
				( atomType3 == N2 )&& ( atomType4 == H2 ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		else if ( ( atomType1 == H2 ) && ( atomType2 == N2 ) && 
				( atomType3 == CH )&& ( atomType4 == H2 ) ){
			forceConstant[ 0 ] = 0.000000;
			equilibriumAngle[ 0 ] = 180.000000;
			terms[ 0 ] = 3;
			return( true );
		}
		return( false );
	}

	public boolean canHydrogenBond( int atom1, int atom2 ){
		int atomType1 = atom1;
		int atomType2 = atom2;
		if ( atomType1 > atomType2 ){
			int temp = atomType2;
			atomType2 = atomType1;
			atomType1 = temp;
		}
		if ( atomType1 == H ){
			switch( atomType2 ){
			case NB: return true;
			case NC: return true;
			case O2: return true;
			case O: return true;
			case OH: return true;
			case S: return true;
			case SH: return true;
			}
			return false;
		}
		else if ( ( atomType1 == HO ) || ( atomType1 == H2 ) || ( atomType1 == H3 ) ||
				( atomType1 == HS ) ){
			switch( atomType2 ){
			case NB: return true;
			case O2: return true;
			case O: return true;
			case OH: return true;
			case S: return true;
			case SH: return true;
			}
			return false;
		}
		else
			return false;
	}

	public void calculateTypes(){
		for( int i = 0; i < m.numberOfAtoms; i++ ){
			Atom atom = m.atom[ i ];
			int atomNumber = atom.moleculeAtomNumber;
			int elementNumber = atom.elementNumber;
			if ( elementNumber == 1 ){
				if ( m.isConnected( atomNumber, "N(*4)", true ) )
					atom.type = H3;
				else if ( m.isConnected( atomNumber, "N(H)(C(N(H2)))", true ) )
					atom.type = H3;
				else if ( ( m.isConnected( atomNumber, "N(C(N2))", true ) ) &&
						m.isConnected( atomNumber, "N(C(N(H2))(N(H2)))", true ) )
					atom.type = H3;
				else if ( m.isConnected( atomNumber, "C", true ) )
					atom.type = HC;
				else if ( m.isConnected( atomNumber, "O(H)", true ) )
					atom.type = HW;
				else if ( m.isConnected( atomNumber, "O", true ) )
					atom.type = HO;
				else if ( m.isConnected( atomNumber, "S", true ) )
					atom.type = HS;
				else if ( m.isConnected( atomNumber, "N(H)(C(~*))", true ) )
					atom.type = H2;
				else if ( m.isConnected( atomNumber, "N(H)(C(=N)(-C))", true ) )
					atom.type = H2;
				else if ( m.isConnected( atomNumber, "N(H)(C(=N)(-N))", true ) )
					atom.type = H2;
				else if ( m.isConnected( atomNumber, "N(H2)", true ) )
					atom.type = H2;
				else
					atom.type = H;
			}
			else if ( elementNumber == 6 ){
				if ( m.sp( atomNumber ) == 2 ){
					if ( ( m.inRing( atomNumber, atomNumber, 5, true ) ) && 
							( m.inRing( atomNumber, atomNumber, 6, true ) ) ){
						if ( m.isConnected( atomNumber, "C(N)", true ) )
							atom.type = CB;
						else if ( m.isConnected( atomNumber, "N(Fe)", true ) )
							atom.type = CC;
						else if ( m.isConnected( atomNumber, "N", true ) )
							atom.type = CN;
						else
							atom.type = CT;
					}
					else if ( m.inRing( atomNumber, atomNumber, 5, true ) ){
						if ( m.isConnected( atomNumber, "(C)(C)(C(N(Fe)))", 
								true ) )
							atom.type = CB;
						else if ( ( m.isConnected( atomNumber, "(H)(N2)", true ) )
								&& ( m.isConnected( atomNumber, 
										"(N)(H)(N(C(N(C(N(C))))))", true ) ) )
							atom.type = CK;
						else if ( m.isConnected( atomNumber,"(N2)(H)", true ) )
							atom.type = CR;
						else if ( m.isConnected( atomNumber,"(C(H2))(N)", true ) )
							atom.type = CC;
						else if ( m.isConnected( atomNumber,"C(H2)", true ) )
							atom.type = C4;
						/* else if ( m.isConnected( atomNumber,"(H)(N(H))", true ) )
							atom.type = CG; */
						else if ( m.isConnected( atomNumber,"N(~*)(*)", true ) )
							atom.type = CW;
						/* else if ( m.isConnected( atomNumber,"N(H)", true ) )
							atom.type = CF; */
						else if ( m.isConnected( atomNumber,"N", true ) )
							atom.type = CV;
						else
							atom.type = CT;
					}
					else if ( m.inRing( atomNumber, atomNumber, 6, true ) ){
						/* if ( m.isConnected( atomNumber, "(-H)(~*)(~*)", true ) )
							atom.type = CD; */
						if ( m.isConnected( atomNumber, "O(H)", true ) )
							atom.type = C;
						else if ( m.isConnected( atomNumber, "=O", true ) )
							atom.type = C;
						else if ( m.isConnected( atomNumber, "(N2)(H)", true ) )
							atom.type = CQ;
						else if ( m.isConnected( atomNumber, "N(H2)", true ) )
							atom.type = CA;
						else if ( m.isConnected( atomNumber, "C(N(C(=O)))", true ) )
							atom.type = CM;
						else if ( m.isConnected( atomNumber, "N(C(=O))", true ) )
							atom.type = CM;
						else 
							atom.type = CA;
					}
					else if ( m.isConnected( atomNumber, "(~O2)", true ) )
						atom.type = C;
					else if ( m.isConnected( atomNumber, "=O", true ) )
						atom.type = C;
					else if ( m.isConnected( atomNumber, "(=C)(-S)", true ) )
						atom.type = CY;
					else if ( m.isConnected( atomNumber, "(=C)", true ) )
						atom.type = CX;
					else if ( m.isConnected( atomNumber, "(N3)", true ) )
						atom.type = CA;
					else
						atom.type = CT;
				}
				else if ( m.sp( atomNumber ) == 3 ){
					/* if ( m.isConnected( atomNumber, "(H3)", true ) )
						atom.type = C3;
					else if ( m.isConnected( atomNumber, "(H2)", true ) )
						atom.type = C2; 
					else if ( m.isConnected( atomNumber, "(H)", true ) )
						atom.type = CH; */
					if ( m.isConnected( atomNumber, "(N3)", true ) )
						atom.type = CA;
					else
						atom.type = CT;
				}
				else 
					atom.type = CT;
			}
			else if ( elementNumber == 7 ){
				if ( m.sp( atomNumber ) < 3 ){
					if ( m.inRing( atomNumber, atomNumber, 5, true ) ){
						if ( ( m.isConnected( atomNumber, "(C3)", true ) ) && 
								( m.isConnected( atomNumber, 
										"(C)(C(=*))(C(=*))", true ) ) )
							atom.type = N4;
						else if ( m.isConnected( atomNumber, "(C3)", true ) )
							atom.type = N4;
						else if ( m.isConnected( atomNumber, "Fe", true ) )
							atom.type = NA;
						else if ( m.isConnected( atomNumber, "H", true ) )
							atom.type = NA;
						else
							atom.type = NB;
					}
					else if ( m.inRing( atomNumber, atomNumber, 6, true ) ){
						if ( m.isConnected( atomNumber, "H", true ) )
							atom.type = NA;
						else if ( ( m.isConnected( atomNumber, "(H)(C2)", true ) ) 
								&& ( m.isConnected( atomNumber, 
										"(H)(C(=O))(C(=O))", true ) ) )
							atom.type = NA;
						else if ( m.isConnected( atomNumber, "(H)(C(=O))(C(=N))", true ) )
							atom.type = NA;
						else if ( m.isConnected( atomNumber, "(C3)", true ) )
							atom.type = N4;
						else
							atom.type = NC;
					}
					else if ( m.isConnected( atomNumber, "-C(=O)", true ) )
						atom.type = N;
					else if ( m.isConnected( atomNumber, "-C(-C(=O))", true ) )
						atom.type = N;
					else if ( m.isConnected( atomNumber, "C(N2)", true ) )
						atom.type = N2;
					else if ( m.isConnected( atomNumber, "(H2)(C~*)", true ) )
						atom.type = N2;
					else if ( m.isConnected( atomNumber, "(H2)(C=*)", true ) )
						atom.type = N2;
					else 
						atom.type = NT;
				}
				else if ( m.sp( atomNumber ) == 3 ){
					atom.type = N3;
				}
				else
					atom.type = NT;
			}
			else if ( elementNumber == 8 ){
				if ( m.isConnected( atomNumber, "~C(~O2)", true ) )
					atom.type = O2;
				else if ( m.isConnected( atomNumber, "H2", true ) )
					atom.type = OW;
				else if ( m.isConnected( atomNumber, "H", true ) )
					atom.type = OH;
				else if ( m.isConnected( atomNumber, "=C", true ) )
					atom.type = O;
				else if ( m.isConnected( atomNumber, "(H)(C(=O))", true ) )
					atom.type = OH;
				else if ( m.isConnected( atomNumber, "-C2", true ) )
					atom.type = OS;
				else if ( m.isConnected( atomNumber, "(C(=O))(C)", true ) )
					atom.type = OS;
				else if ( m.isConnected( atomNumber, "(C)(P)", true ) )
					atom.type = OS;
				else if ( m.isConnected( atomNumber, "P", true ) )
					atom.type = O2;
				else
					atom.type = OS;
			}
			else if ( elementNumber == 9 )
				atom.type = F;
			else if ( elementNumber == 11 )
				atom.type = IP;
			else if ( elementNumber == 12 )
				atom.type = MG;
			else if ( elementNumber == 15 )
				atom.type = P;
			else if ( elementNumber == 16 ){
				if ( m.isConnected( atomNumber, "H", true ) )
					atom.type = SH;
				else
					atom.type = S;
			}
			else if ( elementNumber == 17 ){
				if ( m.connected[ i ][ 0 ] > 0 )
					atom.type = CL;
				else 
					atom.type = IM;
			}
			else if ( elementNumber == 20 )
				atom.type = C0;
			else if ( elementNumber == 26 )
				atom.type = FE;
			else if ( elementNumber == 29 )
				atom.type = CU;
			else if ( elementNumber == 35 )
				atom.type = BR;
			else if ( elementNumber == 53 )
				atom.type = I;
			assignType( i, atom.type );
		}
	}

	public void initializeCalculation(){
		int natomsx3 = m.numberOfAtoms * 3;
		m.gradient = new double[ natomsx3 ];
		int summation;
		int atom1, atom2, atom3, atom4, ix2, ix3, ix4, ix5;
		int numberNonBondedx4, numberHydrogenBondedx4;
		int atomType1, atomType2, atomType3, atomType4;
		double equilibriumAngle[] = new double[ 1 ];
		double equilibriumDistance[] = new double[ 1 ];
		double forceConstant[] = new double[ 1 ];
		int terms[] = new int[ 1 ];
		int multiplicity[] = new int[ 1 ];
		double epsilon[] = new double[ 1 ];
		int C[] = new int[ 1 ];
		int D[] = new int[ 1 ];


		for ( int i = 0; i < natomsx3; i++ ){
			m.gradient[ i ] = 0;
		}
		/* new array for minimization of bond lengths */
		bondStretchTerms = new double[ m.numberOf12Connections * 4 ];

		/* make an array of 4 terms 1=atom1, 2=atom2, 3=Kb, 4=bond */
		ix2 = -2;
		ix4 = -4;
		for ( int i = 0; i < m.numberOf12Connections; i++ ){
			ix2 += 2;
			ix4 += 4;
			atom1 = m.atom[ m.connected12[ ix2 ] ].moleculeAtomNumber;
			atom2 = m.atom[ m.connected12[ ix2 + 1 ] ].moleculeAtomNumber;
			atomType1 = m.atom[ atom1 ].type;
			atomType2 = m.atom[ atom2 ].type;
			if ( ! ( getStretchParameters( atomType1, atomType2,
					forceConstant, equilibriumDistance ) ) )
				System.out.println( "**** couldn't find stretch parameters for " + atom1 + "," + atom2 );
						bondStretchTerms[ ix4 ] = atom1;
						bondStretchTerms[ ix4 + 1 ] = atom2;
						bondStretchTerms[ ix4 + 2 ] = forceConstant[ 0 ];
						bondStretchTerms[ ix4 + 3 ] = equilibriumDistance[ 0 ];
		}

		angleBendTerms = new double[ m.numberOf13Connections * 5 ];

		ix3 = -3;
		ix5 = -5;
		/* make an array of 4 terms 1=atom1, 2=atom2, 3=atom3, 4=Ka, 5=angle */
		for ( int i = 0; i < m.numberOf13Connections; i++ ){
			ix3 += 3;
			ix5 += 5;
			atom1 = m.atom[ m.connected13[ ix3 ] ].moleculeAtomNumber;
			atom2 = m.atom[ m.connected13[ ix3 + 1 ] ].moleculeAtomNumber;
			atom3 = m.atom[ m.connected13[ ix3 + 2 ] ].moleculeAtomNumber;
			atomType1 = m.atom[ atom1 ].type;
			atomType2 = m.atom[ atom2 ].type;
			atomType3 = m.atom[ atom3 ].type;
			if ( ! ( getBendParameters( atomType1, atomType2, atomType3,
					forceConstant, equilibriumAngle ) ) )
				System.out.println( "**** couldn't find bend parameters for " + atom1 + "," + atom2 + "," + atom3 );
						angleBendTerms[ ix5 ] = atom1;
						angleBendTerms[ ix5 + 1 ] = atom2;
						angleBendTerms[ ix5 + 2 ] = atom3;
						angleBendTerms[ ix5 + 3 ] = forceConstant[ 0 ];
						angleBendTerms[ ix5 + 4 ] = equilibriumAngle[ 0 ];
		}

		dihedralAngleTerms = new double[ m.numberOf14Connections * 7 ];
		halfNonBondedTerms = new double[ m.numberOf14Connections * 4 ];

		/* make an array of 7 terms 1=atom1, 2=atom2, 3=atom3, 4=atom4, 5=Ka,
                        6=angle, 7=summation limit in torsional energy term */
		forceConstant = new double[ 5 ];
		equilibriumAngle = new double[ 5 ];
		terms = new int[ 5 ];
		ix4 = -4;
		for ( int i = 0, ix7=0; i < m.numberOf14Connections; i++ ){
			ix4 += 4;
			atom1 = m.atom[ m.connected14[ ix4 ] ].moleculeAtomNumber;
			atom2 = m.atom[ m.connected14[ ix4 + 1 ] ].moleculeAtomNumber;
			atom3 = m.atom[ m.connected14[ ix4 + 2 ] ].moleculeAtomNumber;
			atom4 = m.atom[ m.connected14[ ix4 + 3 ] ].moleculeAtomNumber;
			atomType1 = m.atom[ atom1 ].type;
			atomType2 = m.atom[ atom2 ].type;
			atomType3 = m.atom[ atom3 ].type;
			atomType4 = m.atom[ atom4 ].type;
			forceConstant[ 0 ] = 0;
			multiplicity[ 0 ] = 0;
			if ( ! ( getTorsionParameters( atomType1, atomType2, atomType3,
					atomType4, forceConstant, equilibriumAngle, terms, multiplicity ) ) )
				System.out.println( "**** couldn't find torsion parameters for " + atom1 + "," + atom2 + "," + atom3 +"," + atom4);
						dihedralAngleTerms[ ix7++ ] = atom1;
						dihedralAngleTerms[ ix7++ ] = atom2;
						dihedralAngleTerms[ ix7++ ] = atom3;
						dihedralAngleTerms[ ix7++ ] = atom4;
						dihedralAngleTerms[ ix7++ ] = forceConstant[ 0 ];
						dihedralAngleTerms[ ix7++ ] = equilibriumAngle[ 0 ];
						dihedralAngleTerms[ ix7++ ] = terms[ 0 ];
						summation = multiplicity[ 0 ];
						if ( summation > 0 ){
							double largerArray[] = new double[ dihedralAngleTerms.length +
							                                   7 * summation ];
							System.arraycopy( dihedralAngleTerms, 0, largerArray, 0,
									dihedralAngleTerms.length );
							dihedralAngleTerms = largerArray;
							for ( int j = 1; j <= multiplicity[ 0 ]; j++ ){
								dihedralAngleTerms[ ix7++ ] = atom1;
								dihedralAngleTerms[ ix7++ ] = atom2;
								dihedralAngleTerms[ ix7++ ] = atom3;
								dihedralAngleTerms[ ix7++ ] = atom4;
								dihedralAngleTerms[ ix7++ ] = forceConstant[ j ];
								dihedralAngleTerms[ ix7++ ] = equilibriumAngle[ j ];
								dihedralAngleTerms[ ix7++ ] = terms[ j ];
							}
						}
						double epsilonProduct = 0, ri = 0, rj = 0;
						if ( getNonBondedParameters( atomType1, equilibriumDistance, epsilon ) ){
							epsilonProduct = epsilon[ 0 ];
							ri = equilibriumDistance[ 0 ];
						}
						else
							epsilonProduct = 0;
						if ( getNonBondedParameters( atomType4, equilibriumDistance, epsilon ) ){
							epsilonProduct *= epsilon[ 0 ];
							rj = equilibriumDistance[ 0 ];
						}
						else
							epsilonProduct = 0;
						epsilonProduct = Math.sqrt( epsilonProduct );
						double Bij = ( ri + rj ) * ( ri + rj );
						Bij = Bij * Bij * Bij;
						double Aij = Bij * Bij * epsilonProduct / 2.0;
						Bij *= epsilonProduct;
						halfNonBondedTerms[ ix4 ] = atom1;
						halfNonBondedTerms[ ix4 + 1 ] = atom4;
						halfNonBondedTerms[ ix4 + 2 ] = Aij;
						halfNonBondedTerms[ ix4 + 3 ] = Bij;
		}

		numberOfDihedralTerms = dihedralAngleTerms.length / 7;

		/*              improperTorsionTerms = new double[ m.numberOf14Connections * 7 ];

                //make an array of 7 terms 1=atom1, 2=atom2, 3=atom3, 4=atom4, 5=Ka,
                //      6=angle, 7=summation limit in torsional energy term
                for ( int i = 0; i < m.numberOf14Connections; i++ ){
                        ix4 = i * 4;
                        atom1 = m.atom[ m.connected14[ ix4 ] ].moleculeAtomNumber;
                        atom2 = m.atom[ m.connected14[ ix4 + 1 ] ].moleculeAtomNumber;
                        atom3 = m.atom[ m.connected14[ ix4 + 2 ] ].moleculeAtomNumber;
                        atom4 = m.atom[ m.connected14[ ix4 + 3 ] ].moleculeAtomNumber;
                        atomType1 = m.atom[ atom1 ].type;
                        atomType2 = m.atom[ atom2 ].type;
                        atomType3 = m.atom[ atom3 ].type;
                        atomType4 = m.atom[ atom4 ].type;
                        getImproperTorsionParameters( atomType1, atomType2, atomType3,
                                atomType4, forceConstant, equilibriumAngle, terms );
                        dihedralAngleTerms[ ix6++ ] = atom1;
                        dihedralAngleTerms[ ix6++ ] = atom2;
                        dihedralAngleTerms[ ix6++ ] = atom3;
                        dihedralAngleTerms[ ix6++ ] = atom4;
                        dihedralAngleTerms[ ix6++ ] = forceConstant[ 0 ];
                        dihedralAngleTerms[ ix6++ ] = equilibriumAngle[ 0 ];
                        dihedralAngleTerms[ ix6++ ] = terms[ 0 ];
                }

                numberOfImproperTorsionTerms = dihedralAngleTerms.length / 7;  */

		/* new array for minimization of bond lengths */
		nonBondedTerms = new double[ m.numberNonBonded * 4 ];
		hydrogenBondedTerms = new double[ m.numberNonBonded * 4 ];

		/* make an array of 4 terms 1=atom1, 2=atom2, 3=Aij, 4=Bij */
		ix2 = -2;
		numberNonBondedx4 = -4;
		numberHydrogenBondedx4 = -4;
		numberNonBonded = 0;
		numberHydrogenBonded = 0;
		for ( int i = 0; i < m.numberNonBonded; i++ ){
			ix2 += 2;
			atom1 = m.atom[ m.nonBonded[ ix2 ] ].moleculeAtomNumber;
			atom2 = m.atom[ m.nonBonded[ ix2 + 1 ] ].moleculeAtomNumber;
			atomType1 = m.atom[ atom1 ].type;
			atomType2 = m.atom[ atom2 ].type;
			if ( canHydrogenBond( atomType1, atomType2 ) ){
				numberHydrogenBondedx4 += 4;
				getHydrogenBondedParameters( atomType1, atomType2, C, D );
				hydrogenBondedTerms[ numberHydrogenBondedx4 ] = atom1;
				hydrogenBondedTerms[ numberHydrogenBondedx4 + 1 ] = atom2;
				hydrogenBondedTerms[ numberHydrogenBondedx4 + 2 ] = C[ 0 ];
				hydrogenBondedTerms[ numberHydrogenBondedx4 + 3 ] = D[ 0 ];
				numberHydrogenBonded++; 
			}
			else{ 
				numberNonBondedx4 += 4;
				if ( ! ( getNonBondedParameters( atomType1, equilibriumDistance,
						epsilon ) ) )
					System.out.println( "**** couldn't find nb parameters for " + atom1 + "," + atom2 );
				double epsilonProduct = epsilon[ 0 ];
				double ri = equilibriumDistance[ 0 ];
				getNonBondedParameters( atomType2, equilibriumDistance,
						epsilon );
				epsilonProduct *= epsilon[ 0 ];
				double rj = equilibriumDistance[ 0 ];
				epsilonProduct = Math.sqrt( epsilonProduct );
				double Bij = ( ri + rj ) * ( ri + rj );
				Bij = Bij * Bij * Bij;
				double Aij = Bij * Bij * epsilonProduct;
				Bij *= epsilonProduct * 2.0;
				nonBondedTerms[ numberNonBondedx4 ] = atom1;
				nonBondedTerms[ numberNonBondedx4 + 1 ] = atom2;
				nonBondedTerms[ numberNonBondedx4 + 2 ] = Aij;
				nonBondedTerms[ numberNonBondedx4 + 3 ] = Bij;
				numberNonBonded++;
			}
		}
	}

	public void calculateGradient(){
		int atomix3, atomjx3, atomkx3, atomlx3, atomi, atomj, atomk, atoml;
		int ix4, ix5, ix7;
		double rijx, rijy, rijz, forceConstant, equilibriumDistance, term;
		double forceix, forceiy, forceiz;
		double denominator, delta = 0.0004, absTheta;
		double permittivityScale = 1, permittivity = 1;
		double bondLength, thetaDeg, thetaRad, cosTheta;
		double rkjx, rkjy, rkjz, rij2, rij, rkj2, rkj, tempTerm1;
		double rij3, rkj3, denominatori, denominatork, term2ix;
		double term2iy, term2iz, rijDotrkj, term2kx, term2ky, term2kz;
		double term2jx, term2jy, term2jz, forcejx, forcejy, forcejz;
		double forcekx, forceky, forcekz, forcelx, forcely, forcelz;
		double phiDeg, phiRad, cosPhi, term2, rklx, rkly, rklz, termxterm2;
		double tempTerm, rimx, rimy, rimz, rlnx, rlny, rlnz, rimInv, rlnInv;
		double cosPhiDivrim, term3ix, term3iy, term3iz, rim, rln, cosPhiDivrln;
		double term3lx, term3ly, term3lz, tempTerm2, term3jx, term3jy, term3jz;
		double tempTerm3, term3kx, term3ky, term3kz, chargei, chargej, coulombTerm;
		double Aij, Bij, rij6, rij7, rij14, term1, term3, rklDotrkj, rij8;
		double coulombFactor, Cij, Dij, rij4, rij12;

		m.gradient = new double[ m.numberOfAtoms * 3 ];
		ix4 = -4;
		for ( int i = 0; i < m.numberOf12Connections; i++ ){
			ix4 += 4;
			atomi = (int)bondStretchTerms[ ix4 ];
			atomj = (int)bondStretchTerms[ ix4 + 1 ];
			forceConstant = bondStretchTerms[ ix4 + 2 ];
			equilibriumDistance = bondStretchTerms[ ix4 + 3 ];
			atomix3 = atomi * 3;	
			atomjx3 = atomj * 3;	
			rijx = m.actualCoordinates[ atomix3 ] - 
					m.actualCoordinates[ atomjx3 ];	
			rijy = m.actualCoordinates[ atomix3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];	
			rijz = m.actualCoordinates[ atomix3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];	
			bondLength = Math.sqrt( rijx * rijx + rijy * rijy + rijz * rijz );
			if ( bondLength < 1.0e-10 ) 
				bondLength = 1.0e-10;
			term = - forceConstant * ( bondLength - equilibriumDistance ) / bondLength;
			forceix = term * rijx;
			forceiy = term * rijy;
			forceiz = term * rijz;
			m.gradient[ atomix3 ] -= forceix;
			m.gradient[ atomix3 + 1 ] -= forceiy;
			m.gradient[ atomix3 + 2 ] -= forceiz;
			m.gradient[ atomjx3 ] += forceix;
			m.gradient[ atomjx3 + 1 ] += forceiy;
			m.gradient[ atomjx3 + 2 ] += forceiz;
		} 

		ix4 = -4;
		ix5 = -5;
		for ( int i = 0; i < m.numberOf13Connections; i++ ){
			ix4 += 4;
			ix5 += 5;
			atomi = (int)angleBendTerms[ ix5 ];
			atomj = (int)angleBendTerms[ ix5 + 1 ];
			atomk = (int)angleBendTerms[ ix5 + 2 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			atomkx3 = atomk * 3;
			m.resolveCoordinates( atomi );
			m.resolveCoordinates( atomj );
			m.resolveCoordinates( atomk );
			thetaDeg = m.atom[ atomk ].angle( m.atom[ atomi ], m.atom[ atomj ] );
			thetaRad = thetaDeg * D2R;
			absTheta = Math.abs( thetaDeg );
			cosTheta = Math.cos( thetaRad );
			if ( ( absTheta > delta ) && ( absTheta < 180.0 - delta ) ){
				denominator = Math.sqrt( 1 - cosTheta * cosTheta );
				if ( denominator < 1.0e-10 )
					denominator = 1.0e-10;
				term = angleBendTerms[ ix5 + 3 ] * ( thetaDeg - 
						angleBendTerms[ ix5 + 4 ] ) / denominator;
				rijx = m.actualCoordinates[ atomix3 ] - 
						m.actualCoordinates[ atomjx3 ];
				rijy = m.actualCoordinates[ atomix3 + 1 ] - 
						m.actualCoordinates[ atomjx3 + 1 ];
				rijz = m.actualCoordinates[ atomix3 + 2 ] - 
						m.actualCoordinates[ atomjx3 + 2 ];
				rkjx = m.actualCoordinates[ atomkx3 ] - 
						m.actualCoordinates[ atomjx3 ];
				rkjy = m.actualCoordinates[ atomkx3 + 1 ] - 
						m.actualCoordinates[ atomjx3 + 1 ];
				rkjz = m.actualCoordinates[ atomkx3 + 2 ] - 
						m.actualCoordinates[ atomjx3 + 2 ];
				rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
				rij = Math.sqrt( rij2 );
				rkj2 = rkjx * rkjx + rkjy * rkjy + rkjz * rkjz;
				rkj = Math.sqrt( rkj2 );
				rijDotrkj = rijx * rkjx + rijy * rkjy + rijz * rkjz;
				rij3 = rij2 * rij;
				rkj3 = rkj2 * rkj;
				denominatori = rij3 * rkj;
				if ( denominatori < 1.0e-10 )
					denominatori = 1.0e-10;
				denominatork = rij * rkj3;
				if ( denominatork < 1.0e-10 )
					denominatork = 1.0e-10;
				term2ix = ( rij2 * rkjx - rijDotrkj * rijx ) / denominatori;
				term2iy = ( rij2 * rkjy - rijDotrkj * rijy ) / denominatori;
				term2iz = ( rij2 * rkjz - rijDotrkj * rijz ) / denominatori;
				term2kx = ( rkj2 * rijx - rijDotrkj * rkjx ) / denominatork;
				term2ky = ( rkj2 * rijy - rijDotrkj * rkjy ) / denominatork;
				term2kz = ( rkj2 * rijz - rijDotrkj * rkjz ) / denominatork;
				term2jx = - term2ix - term2kx;
				term2jy = - term2iy - term2ky;
				term2jz = - term2iz - term2kz;
				forceix = term * term2ix;
				forceiy = term * term2iy;
				forceiz = term * term2iz;
				forcejx = term * term2jx;
				forcejy = term * term2jy;
				forcejz = term * term2jz;
				forcekx = term * term2kx;
				forceky = term * term2ky;
				forcekz = term * term2kz;
				m.gradient[ atomix3 ] -= forceix;
				m.gradient[ atomix3 + 1 ] -= forceiy;
				m.gradient[ atomix3 + 2 ] -= forceiz;
				m.gradient[ atomjx3 ] -= forcejx;
				m.gradient[ atomjx3 + 1 ] -= forcejy;
				m.gradient[ atomjx3 + 2 ] -= forcejz;
				m.gradient[ atomkx3 ] -= forcekx;
				m.gradient[ atomkx3 + 1 ] -= forceky;
				m.gradient[ atomkx3 + 2 ] -= forcekz;
			}
		} 

		ix7 = -7;
		for ( int i = 0; i < numberOfDihedralTerms; i++ ){
			ix7 += 7;

			atomi = (int)dihedralAngleTerms[ ix7 ];
			atomj = (int)dihedralAngleTerms[ ix7 + 1 ];
			atomk = (int)dihedralAngleTerms[ ix7 + 2 ];
			atoml = (int)dihedralAngleTerms[ ix7 + 3 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			atomkx3 = atomk * 3;
			atomlx3 = atoml * 3;
			m.resolveCoordinates( atomi );
			m.resolveCoordinates( atomj );
			m.resolveCoordinates( atomk );
			m.resolveCoordinates( atoml );
			phiDeg = m.atom[ atoml ].torsion( m.atom[ atomi ], m.atom[ atomj ],
					m.atom[ atomk ] );
			phiRad = phiDeg * D2R;
			cosPhi = Math.cos( phiRad );
			term = - dihedralAngleTerms[ ix7 + 4 ] * 
					Math.cos( D2R * dihedralAngleTerms[ ix7 + 5 ] );
			term2 = dihedralAngleTerms[ ix7 + 6 ];
			if ( term2 == 2 )
				term2 = 4 * cosPhi;
			else if ( term2 == 3 )
				term2 = 12 * cosPhi * cosPhi - 3;
			else if ( term2 == 4 )
				term2 = cosPhi * ( 32 * cosPhi * cosPhi - 16 );
			else if ( term2 == 6 )
				term2 = cosPhi * ( 192 * ( cosPhi * cosPhi * 
						( cosPhi * cosPhi + 1 ) ) + 36 );
			rijx = m.actualCoordinates[ atomix3 ] - 
					m.actualCoordinates[ atomjx3 ];
			rijy = m.actualCoordinates[ atomix3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];
			rijz = m.actualCoordinates[ atomix3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];
			rkjx = m.actualCoordinates[ atomkx3 ] - 
					m.actualCoordinates[ atomjx3 ];
			rkjy = m.actualCoordinates[ atomkx3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];
			rkjz = m.actualCoordinates[ atomkx3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];
			rklx = m.actualCoordinates[ atomkx3 ] - 
					m.actualCoordinates[ atomlx3 ];
			rkly = m.actualCoordinates[ atomkx3 + 1 ] - 
					m.actualCoordinates[ atomlx3 + 1 ];
			rklz = m.actualCoordinates[ atomkx3 + 2 ] - 
					m.actualCoordinates[ atomlx3 + 2 ];
			rijDotrkj = rijx * rkjx + rijy * rkjy + rijz * rkjz;
			rklDotrkj = rklx * rkjx + rkly * rkjy + rklz * rkjz;
			rkj2 = rkjx * rkjx + rkjy * rkjy + rkjz * rkjz;
			tempTerm = 0;
			if ( rkj2 > 1.0e-10 )
				tempTerm = rijDotrkj / rkj2;
			rimx = rijx - tempTerm * rkjx;
			rimy = rijy - tempTerm * rkjy;
			rimz = rijz - tempTerm * rkjz;
			rim = Math.sqrt( rimx * rimx + rimy * rimy + rimz * rimz );
			tempTerm = 0;
			if ( rkj2 > 1.0e-10 )
				tempTerm = rklDotrkj / rkj2;
			rlnx = - rklx + tempTerm * rkjx;
			rlny = - rkly + tempTerm * rkjy;
			rlnz = - rklz + tempTerm * rkjz;
			rln = Math.sqrt( rlnx * rlnx + rlny * rlny + rlnz * rlnz );
			term3ix = 0;
			term3iy = 0;
			term3iz = 0;
			term3lx = 0;
			term3ly = 0;
			term3lz = 0;
			if ( ( rim > 0 ) && ( rln > 0 ) ){
				rimInv = 1 / rim;
				cosPhiDivrim = cosPhi / rim;
				term3ix = rimInv * ( rlnx / rln - cosPhiDivrim * rimx );
				term3iy = rimInv * ( rlny / rln - cosPhiDivrim * rimy );
				term3iz = rimInv * ( rlnz / rln - cosPhiDivrim * rimz );
				rlnInv = 1 / rln;
				cosPhiDivrln = cosPhi / rln;
				term3lx = rlnInv * ( rimx / rim - cosPhiDivrln * rlnx );
				term3ly = rlnInv * ( rimy / rim - cosPhiDivrln * rlny );
				term3lz = rlnInv * ( rimz / rim - cosPhiDivrln * rlnz );
			}
			tempTerm3 = 0;
			if ( rkj2 > 1.0e-10 )
				tempTerm3 = - ( rijx * rkjx + rijy * rkjy + rijz * rkjz ) / rkj2;
			tempTerm1 = - tempTerm3 - 1;
			tempTerm2 = 0;
			if ( rkj2 > 1.0e-10 )
				tempTerm2 = - ( rklx * rkjx + rkly * rkjy + rklz * rkjz ) / rkj2;
			term3jx = tempTerm1 * term3ix + tempTerm2 * term3lx;
			term3jy = tempTerm1 * term3iy + tempTerm2 * term3ly;
			term3jz = tempTerm1 * term3iz + tempTerm2 * term3lz;
			tempTerm1 = - tempTerm2 - 1;
			term3kx = tempTerm1 * term3lx + tempTerm3 * term3ix;
			term3ky = tempTerm1 * term3ly + tempTerm3 * term3iy;
			term3kz = tempTerm1 * term3lz + tempTerm3 * term3iz;
			termxterm2 = term * term2;
			forceix = termxterm2 * term3ix;
			forceiy = termxterm2 * term3iy;
			forceiz = termxterm2 * term3iz;
			forcejx = termxterm2 * term3jx;
			forcejy = termxterm2 * term3jy;
			forcejz = termxterm2 * term3jz;
			forcekx = termxterm2 * term3kx;
			forceky = termxterm2 * term3ky;
			forcekz = termxterm2 * term3kz;
			forcelx = termxterm2 * term3lx;
			forcely = termxterm2 * term3ly;
			forcelz = termxterm2 * term3lz;
			m.gradient[ atomix3 ] -= forceix;
			m.gradient[ atomix3 + 1 ] -= forceiy;
			m.gradient[ atomix3 + 2 ] -= forceiz;
			m.gradient[ atomjx3 ] -= forcejx;
			m.gradient[ atomjx3 + 1 ] -= forcejy;
			m.gradient[ atomjx3 + 2 ] -= forcejz;
			m.gradient[ atomkx3 ] -= forcekx;
			m.gradient[ atomkx3 + 1 ] -= forceky;
			m.gradient[ atomkx3 + 2 ] -= forcekz;
			m.gradient[ atomlx3 ] -= forcelx;
			m.gradient[ atomlx3 + 1 ] -= forcely;
			m.gradient[ atomlx3 + 2 ] -= forcelz;
		}

		/* now for 1/2 non-bonded terms */
		ix4 = -4;
		coulombFactor = 166.0 / ( permittivity * permittivityScale );
		for ( int i = 0; i < m.numberOf14Connections; i++ ){
			ix4 += 4;
			atomi = (int)halfNonBondedTerms[ ix4 ];
			atomj = (int)halfNonBondedTerms[ ix4 + 1 ];
			Aij = halfNonBondedTerms[ ix4 + 2 ];
			Bij = halfNonBondedTerms[ ix4 + 3 ];
			chargei = m.atom[ atomi ].charge;
			chargej = m.atom[ atomj ].charge;
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = m.actualCoordinates[ atomix3 ] - 
					m.actualCoordinates[ atomjx3 ];
			rijy = m.actualCoordinates[ atomix3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];
			rijz = m.actualCoordinates[ atomix3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			if ( rij2 < 1.0e-2 )
				rij2 = 1.0e-2;	
			rij = Math.sqrt( rij2 );
			rij3 = rij2 * rij;
			rij6 = rij3 * rij3;
			rij7 = rij6 * rij;
			rij8 = rij7 * rij;
			rij14 = rij7 * rij7;
			coulombTerm = ( chargei * chargej * coulombFactor ) / rij3;
			term1 = 12 * Aij / rij14;
			term2 = 6 * Bij / rij8;
			term3 = term1 - term2 + coulombTerm;
			forceix = term3 * rijx;
			forceiy = term3 * rijy;
			forceiz = term3 * rijz;
			forcejx = - forceix;
			forcejy = - forceiy;
			forcejz = - forceiz;
			m.gradient[ atomix3 ] -= forceix;
			m.gradient[ atomix3 + 1 ] -= forceiy;
			m.gradient[ atomix3 + 2 ] -= forceiz;
			m.gradient[ atomjx3 ] -= forcejx;
			m.gradient[ atomjx3 + 1 ] -= forcejy;
			m.gradient[ atomjx3 + 2 ] -= forcejz;
		}

		/* non-bonded part */
		ix4 = -4;
		coulombFactor = 332.0 / ( permittivity * permittivityScale );
		for ( int i = 0; i < numberNonBonded; i++ ){
			ix4 += 4;
			atomi = (int)nonBondedTerms[ ix4 ];
			atomj = (int)nonBondedTerms[ ix4 + 1 ];
			Aij = nonBondedTerms[ ix4 + 2 ];
			Bij = nonBondedTerms[ ix4 + 3 ];
			chargei = m.atom[ atomi ].charge;
			chargej = m.atom[ atomj ].charge;
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = m.actualCoordinates[ atomix3 ] - 
					m.actualCoordinates[ atomjx3 ];
			rijy = m.actualCoordinates[ atomix3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];
			rijz = m.actualCoordinates[ atomix3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			if ( rij2 < 1.0e-2 )
				rij2 = 1.0e-2;	
			rij = Math.sqrt( rij2 );
			rij3 = rij2 * rij;
			rij6 = rij3 * rij3;
			rij7 = rij6 * rij;
			rij8 = rij7 * rij;
			rij14 = rij7 * rij7;
			coulombTerm = ( chargei * chargej * coulombFactor ) / rij3;
			term1 = 12 * Aij / rij14;
			term2 = 6 * Bij / rij8;
			term3 = term1 - term2 + coulombTerm;
			forceix = term3 * rijx;
			forceiy = term3 * rijy;
			forceiz = term3 * rijz;
			forcejx = - forceix;
			forcejy = - forceiy;
			forcejz = - forceiz;
			m.gradient[ atomix3 ] -= forceix;
			m.gradient[ atomix3 + 1 ] -= forceiy;
			m.gradient[ atomix3 + 2 ] -= forceiz;
			m.gradient[ atomjx3 ] -= forcejx;
			m.gradient[ atomjx3 + 1 ] -= forcejy;
			m.gradient[ atomjx3 + 2 ] -= forcejz;
		}  

		/* Hydrogen-bonded part */
		for ( int i = 0; i < numberHydrogenBonded; i++ ){
			ix4 += 4;
			atomi = (int)nonBondedTerms[ ix4 ];
			atomj = (int)nonBondedTerms[ ix4 + 1 ];
			Cij = nonBondedTerms[ ix4 + 2 ];
			Dij = nonBondedTerms[ ix4 + 3 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = m.actualCoordinates[ atomix3 ] - 
					m.actualCoordinates[ atomjx3 ];
			rijy = m.actualCoordinates[ atomix3 + 1 ] - 
					m.actualCoordinates[ atomjx3 + 1 ];
			rijz = m.actualCoordinates[ atomix3 + 2 ] - 
					m.actualCoordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			if ( rij2 < 1.0e-2 )
				rij2 = 1.0e-2;	
			rij4 = rij2 * rij2;
			rij8 = rij4 * rij4;
			rij12 = rij8 * rij4;
			rij14 = rij12 * rij2;
			term1 = Cij / rij14;
			term2 = Dij / rij12;
			term3 = term1 - term2;
			forceix = term3 * rijx;
			forceiy = term3 * rijy;
			forceiz = term3 * rijz;
			forcejx = - forceix;
			forcejy = - forceiy;
			forcejz = - forceiz;
			m.gradient[ atomix3 ] -= forceix;
			m.gradient[ atomix3 + 1 ] -= forceiy;
			m.gradient[ atomix3 + 2 ] -= forceiz;
			m.gradient[ atomjx3 ] -= forcejx;
			m.gradient[ atomjx3 + 1 ] -= forcejy;
			m.gradient[ atomjx3 + 2 ] -= forcejz;
		}  
	}

	public double calculateEnergy( float coordinates[] ){
		int atomix3, atomjx3, atomkx3, atomlx3, atomi, atomj, atomk, atoml;
		int ix4, ix5, ix7; 
		double rij2, rij6, rij12, thetaDeg, phiDeg, coulombTerm;
		double rijx, rijy, rijz, forceConstant, equilibriumDistance, term;
		double energy = 0, chargei, chargej, Aij, Bij, rij, bondLength;
		double permittivityScale = 1, permittivity = 1;
		double x12, x32, y12, y32, z12, z32, l12, l32, dp, rij4, rij10;
		double xij, yij, zij, xkj, ykj, zkj, xkl, ykl, zkl, dx, dy, dz;
		double gx, gy, gz, bi, bk, ct, d, ap, app;
		double coulombFactor, Cij, Dij;

		ix4 = -4;
		for ( int i = 0; i < m.numberOf12Connections; i++ ){
			ix4 += 4;
			atomi = (int)bondStretchTerms[ ix4 ];
			atomj = (int)bondStretchTerms[ ix4 + 1 ];
			forceConstant = bondStretchTerms[ ix4 + 2 ];
			equilibriumDistance = bondStretchTerms[ ix4 + 3 ];
			atomix3 = atomi * 3;	
			atomjx3 = atomj * 3;	
			rijx = coordinates[ atomix3 ] - coordinates[ atomjx3 ];	
			rijy = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];	
			rijz = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];	
			bondLength = Math.sqrt( rijx * rijx + rijy * rijy + rijz * rijz );
			term = bondLength - equilibriumDistance;

			energy += ( forceConstant / 2.0 ) * term * term;
		} 

		ix4 = -4;
		ix5 = -5;
		for ( int i = 0; i < m.numberOf13Connections; i++ ){
			ix4 += 4;
			ix5 += 5;
			atomi = (int)angleBendTerms[ ix5 ];
			atomj = (int)angleBendTerms[ ix5 + 1 ];
			atomk = (int)angleBendTerms[ ix5 + 2 ];
			atomix3 = atomi * 3;	
			atomjx3 = atomj * 3;	
			atomkx3 = atomk * 3;	

			x12 = coordinates[ atomix3 ] - coordinates[ atomjx3 ];
			y12 = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];
			z12 = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];
			x32 = coordinates[ atomkx3 ] - coordinates[ atomjx3 ];
			y32 = coordinates[ atomkx3 + 1 ] - coordinates[ atomjx3 + 1 ];
			z32 = coordinates[ atomkx3 + 2 ] - coordinates[ atomjx3 + 2 ];
			l12 = Math.sqrt( x12 * x12 + y12 * y12 + z12 * z12 );
			l32 = Math.sqrt( x32 * x32 + y32 * y32 + z32 * z32 );
			if( l12 == 0.0 ){
				thetaDeg = 0;
			}
			if( l32 == 0.0 ){
				thetaDeg = 0;
			}
			dp = ( x12 * x32 + y12 * y32 + z12 * z32 ) / (l12 * l32 );
			if ( dp < -1.0 )
				dp = -1.0;
			else if ( dp > 1.0 )
				dp = 1.0;
			thetaDeg = R2D * Math.acos( dp );

			term = thetaDeg - angleBendTerms[ ix5 + 4 ];
			term *= term * angleBendTerms[ ix5 + 3 ] / 2.0;

			energy += term;
		} 

		ix7 = -7;
		for ( int i = 0; i < numberOfDihedralTerms; i++ ){
			ix7 += 7;

			atomi = (int)dihedralAngleTerms[ ix7 ];
			atomj = (int)dihedralAngleTerms[ ix7 + 1 ];
			atomk = (int)dihedralAngleTerms[ ix7 + 2 ];
			atoml = (int)dihedralAngleTerms[ ix7 + 3 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			atomkx3 = atomk * 3;
			atomlx3 = atoml * 3;

			xij = coordinates[ atomix3 ] - coordinates[ atomjx3 ];
			yij = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];
			zij = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];
			xkj = coordinates[ atomkx3 ] - coordinates[ atomjx3 ];
			ykj = coordinates[ atomkx3 + 1 ] - coordinates[ atomjx3 + 1 ];
			zkj = coordinates[ atomkx3 + 2 ] - coordinates[ atomjx3 + 2 ];
			xkl = coordinates[ atomkx3 ] - coordinates[ atomlx3 ];
			ykl = coordinates[ atomkx3 + 1 ] - coordinates[ atomlx3 + 1 ];
			zkl = coordinates[ atomkx3 + 2 ] - coordinates[ atomlx3 + 2 ];

			dx = yij * zkj - zij * ykj;
			dy = zij * xkj - xij * zkj;
			dz = xij * ykj - yij * xkj;
			gx = zkj * ykl - ykj * zkl;
			gy = xkj * zkl - zkj * xkl;
			gz = ykj * xkl - xkj * ykl;

			bi = dx * dx + dy * dy + dz * dz;
			bk = gx * gx + gy * gy + gz * gz;
			ct = dx * gx + dy * gy + dz * gz;
			ct = ct / Math.sqrt( bi * bk );
			if( ct < -1.0 )
				ct = -1.0;
			else if( ct > 1.0 )
				ct = 1.0;

			ap = Math.acos( ct );
			d  = xkj*(dz*gy-dy*gz) + ykj*(dx*gz-dz*gx) + zkj*(dy*gx-dx*gy);
			if( d < 0.0 )
				ap = -ap;
			ap = Math.PI - ap;
			app = 180.0 * ap / Math.PI;
			if( app > 180.0 )
				app = app - 360.0;
			phiDeg = app;

			energy += dihedralAngleTerms[ ix7 + 4 ] * ( 1.0 + Math.cos( 
					dihedralAngleTerms[ ix7 + 6 ] * 
					phiDeg - dihedralAngleTerms[ ix7 + 5 ] ) );
		}

		/* now for 1/2 non-bonded term */
		ix4 = -4;
				coulombFactor = 166.0 / ( permittivity * permittivityScale );
				for ( int i = 0; i < m.numberOf14Connections; i++ ){
					ix4 += 4;
					atomi = (int)halfNonBondedTerms[ ix4 ];
					atomj = (int)halfNonBondedTerms[ ix4 + 1 ];
					Aij = halfNonBondedTerms[ ix4 + 2 ];
					Bij = halfNonBondedTerms[ ix4 + 3 ];
					chargei = m.atom[ atomi ].charge;
					chargej = m.atom[ atomj ].charge;
					atomix3 = atomi * 3;
					atomjx3 = atomj * 3;
					rijx = coordinates[ atomix3 ] - 
							coordinates[ atomjx3 ];
					rijy = coordinates[ atomix3 + 1 ] - 
							coordinates[ atomjx3 + 1 ];
					rijz = coordinates[ atomix3 + 2 ] - 
							coordinates[ atomjx3 + 2 ];
					rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
					rij = Math.sqrt( rij2 );
					rij6 = rij2 * rij2 * rij2;
					rij12 = rij6 * rij6;

					coulombTerm = ( chargei * chargej * coulombFactor ) / rij;

					energy += Aij / rij12 - Bij / rij6 + coulombTerm;
				}

				ix4 = -4;
				coulombFactor = 332.0 / ( permittivity * permittivityScale );
				for ( int i = 0; i < numberNonBonded; i++ ){
					ix4 += 4;
					atomi = (int)nonBondedTerms[ ix4 ];
					atomj = (int)nonBondedTerms[ ix4 + 1 ];
					Aij = nonBondedTerms[ ix4 + 2 ];
					Bij = nonBondedTerms[ ix4 + 3 ];
					chargei = m.atom[ atomi ].charge;
					chargej = m.atom[ atomj ].charge;
					atomix3 = atomi * 3;
					atomjx3 = atomj * 3;
					rijx = coordinates[ atomix3 ] - 
							coordinates[ atomjx3 ];
					rijy = coordinates[ atomix3 + 1 ] - 
							coordinates[ atomjx3 + 1 ];
					rijz = coordinates[ atomix3 + 2 ] - 
							coordinates[ atomjx3 + 2 ];
					rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
					rij = Math.sqrt( rij2 );
					rij6 = rij2 * rij2 * rij2;
					rij12 = rij6 * rij6;

					coulombTerm = ( chargei * chargej * coulombFactor ) / rij;

					energy += Aij / rij12 - Bij / rij6 + coulombTerm;
				}  

				/* Hydrogen-bonded term */
				ix4 = -4;
				for ( int i = 0; i < numberHydrogenBonded; i++ ){
					ix4 += 4;
					atomi = (int)hydrogenBondedTerms[ ix4 ];
					atomj = (int)hydrogenBondedTerms[ ix4 + 1 ];
					Cij = hydrogenBondedTerms[ ix4 + 2 ];
					Dij = hydrogenBondedTerms[ ix4 + 3 ];
					atomix3 = atomi * 3;
					atomjx3 = atomj * 3;
					rijx = coordinates[ atomix3 ] - 
							coordinates[ atomjx3 ];
					rijy = coordinates[ atomix3 + 1 ] - 
							coordinates[ atomjx3 + 1 ];
					rijz = coordinates[ atomix3 + 2 ] - 
							coordinates[ atomjx3 + 2 ];
					rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
					rij4 = rij2 * rij2;
					rij6 = rij4 * rij2;
					rij10 = rij6 * rij4;
					rij12 = rij10 * rij2;

					energy += Cij / rij12 - Dij / rij10;
				}  
				return( energy );				
	}
	public double calculateEnergy( double coordinates[] ){
		int atomix3, atomjx3, atomkx3, atomlx3, atomi, atomj, atomk, atoml;
		int ix4, ix5, ix7; 
		double rij2, rij6, rij12, thetaDeg, phiDeg, coulombTerm;
		double rijx, rijy, rijz, forceConstant, equilibriumDistance, term;
		double energy = 0, chargei, chargej, Aij, Bij, rij, bondLength;
		double permittivityScale = 1, permittivity = 1;
		double x12, x32, y12, y32, z12, z32, l12, l32, dp, rij4, rij10;
		double xij, yij, zij, xkj, ykj, zkj, xkl, ykl, zkl, dx, dy, dz;
		double gx, gy, gz, bi, bk, ct, d, ap, app;
		double coulombFactor, Cij, Dij;

		ix4 = -4;
		for ( int i = 0; i < m.numberOf12Connections; i++ ){
			ix4 += 4;
			atomi = (int)bondStretchTerms[ ix4 ];
			atomj = (int)bondStretchTerms[ ix4 + 1 ];
			forceConstant = bondStretchTerms[ ix4 + 2 ];
			equilibriumDistance = bondStretchTerms[ ix4 + 3 ];
			atomix3 = atomi * 3;	
			atomjx3 = atomj * 3;	
			rijx = coordinates[ atomix3 ] - coordinates[ atomjx3 ];	
			rijy = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];	
			rijz = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];	
			bondLength = Math.sqrt( rijx * rijx + rijy * rijy + rijz * rijz );
			term = bondLength - equilibriumDistance;

			energy += ( forceConstant / 2.0 ) * term * term;
		} 

		ix4 = -4;
		ix5 = -5;
		for ( int i = 0; i < m.numberOf13Connections; i++ ){
			ix4 += 4;
			ix5 += 5;
			atomi = (int)angleBendTerms[ ix5 ];
			atomj = (int)angleBendTerms[ ix5 + 1 ];
			atomk = (int)angleBendTerms[ ix5 + 2 ];
			atomix3 = atomi * 3;	
			atomjx3 = atomj * 3;	
			atomkx3 = atomk * 3;	

			x12 = coordinates[ atomix3 ] - coordinates[ atomjx3 ];
			y12 = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];
			z12 = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];
			x32 = coordinates[ atomkx3 ] - coordinates[ atomjx3 ];
			y32 = coordinates[ atomkx3 + 1 ] - coordinates[ atomjx3 + 1 ];
			z32 = coordinates[ atomkx3 + 2 ] - coordinates[ atomjx3 + 2 ];
			l12 = Math.sqrt( x12 * x12 + y12 * y12 + z12 * z12 );
			l32 = Math.sqrt( x32 * x32 + y32 * y32 + z32 * z32 );
			if( l12 == 0.0 ){
				thetaDeg = 0;
			}
			if( l32 == 0.0 ){
				thetaDeg = 0;
			}
			dp = ( x12 * x32 + y12 * y32 + z12 * z32 ) / (l12 * l32 );
			if ( dp < -1.0 )
				dp = -1.0;
			else if ( dp > 1.0 )
				dp = 1.0;
			thetaDeg = R2D * Math.acos( dp );

			term = thetaDeg - angleBendTerms[ ix5 + 4 ];
			term *= term * angleBendTerms[ ix5 + 3 ] / 2.0;

			energy += term;
		} 

		ix7 = -7;
		for ( int i = 0; i < numberOfDihedralTerms; i++ ){
			ix7 += 7;

			atomi = (int)dihedralAngleTerms[ ix7 ];
			atomj = (int)dihedralAngleTerms[ ix7 + 1 ];
			atomk = (int)dihedralAngleTerms[ ix7 + 2 ];
			atoml = (int)dihedralAngleTerms[ ix7 + 3 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			atomkx3 = atomk * 3;
			atomlx3 = atoml * 3;

			xij = coordinates[ atomix3 ] - coordinates[ atomjx3 ];
			yij = coordinates[ atomix3 + 1 ] - coordinates[ atomjx3 + 1 ];
			zij = coordinates[ atomix3 + 2 ] - coordinates[ atomjx3 + 2 ];
			xkj = coordinates[ atomkx3 ] - coordinates[ atomjx3 ];
			ykj = coordinates[ atomkx3 + 1 ] - coordinates[ atomjx3 + 1 ];
			zkj = coordinates[ atomkx3 + 2 ] - coordinates[ atomjx3 + 2 ];
			xkl = coordinates[ atomkx3 ] - coordinates[ atomlx3 ];
			ykl = coordinates[ atomkx3 + 1 ] - coordinates[ atomlx3 + 1 ];
			zkl = coordinates[ atomkx3 + 2 ] - coordinates[ atomlx3 + 2 ];

			dx = yij * zkj - zij * ykj;
			dy = zij * xkj - xij * zkj;
			dz = xij * ykj - yij * xkj;
			gx = zkj * ykl - ykj * zkl;
			gy = xkj * zkl - zkj * xkl;
			gz = ykj * xkl - xkj * ykl;

			bi = dx * dx + dy * dy + dz * dz;
			bk = gx * gx + gy * gy + gz * gz;
			ct = dx * gx + dy * gy + dz * gz;
			ct = ct / Math.sqrt( bi * bk );
			if( ct < -1.0 )
				ct = -1.0;
			else if( ct > 1.0 )
				ct = 1.0;

			ap = Math.acos( ct );
			d  = xkj*(dz*gy-dy*gz) + ykj*(dx*gz-dz*gx) + zkj*(dy*gx-dx*gy);
			if( d < 0.0 )
				ap = -ap;
			ap = Math.PI - ap;
			app = 180.0 * ap / Math.PI;
			if( app > 180.0 )
				app = app - 360.0;
			phiDeg = app;

			energy += dihedralAngleTerms[ ix7 + 4 ] * ( 1.0 + Math.cos( 
					dihedralAngleTerms[ ix7 + 6 ] * 
					phiDeg - dihedralAngleTerms[ ix7 + 5 ] ) );
		}

		ix4 = -4;
		coulombFactor = 166.0 / ( permittivity * permittivityScale );
		for ( int i = 0; i < m.numberOf14Connections; i++ ){
			ix4 += 4;
			atomi = (int)halfNonBondedTerms[ ix4 ];
			atomj = (int)halfNonBondedTerms[ ix4 + 1 ];
			Aij = halfNonBondedTerms[ ix4 + 2 ];
			Bij = halfNonBondedTerms[ ix4 + 3 ];
			chargei = m.atom[ atomi ].charge;
			chargej = m.atom[ atomj ].charge;
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = coordinates[ atomix3 ] - 
					coordinates[ atomjx3 ];
			rijy = coordinates[ atomix3 + 1 ] - 
					coordinates[ atomjx3 + 1 ];
			rijz = coordinates[ atomix3 + 2 ] - 
					coordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			rij = Math.sqrt( rij2 );
			rij6 = rij2 * rij2 * rij2;
			rij12 = rij6 * rij6;

			coulombTerm = ( chargei * chargej * coulombFactor ) / rij;

			energy += Aij / rij12 - Bij / rij6 + coulombTerm;
		}

		ix4 = -4;
		coulombFactor = 332.0 / ( permittivity * permittivityScale );
		for ( int i = 0; i < numberNonBonded; i++ ){
			ix4 += 4;
			atomi = (int)nonBondedTerms[ ix4 ];
			atomj = (int)nonBondedTerms[ ix4 + 1 ];
			Aij = nonBondedTerms[ ix4 + 2 ];
			Bij = nonBondedTerms[ ix4 + 3 ];
			chargei = m.atom[ atomi ].charge;
			chargej = m.atom[ atomj ].charge;
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = coordinates[ atomix3 ] - 
					coordinates[ atomjx3 ];
			rijy = coordinates[ atomix3 + 1 ] - 
					coordinates[ atomjx3 + 1 ];
			rijz = coordinates[ atomix3 + 2 ] - 
					coordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			rij = Math.sqrt( rij2 );
			rij6 = rij2 * rij2 * rij2;
			rij12 = rij6 * rij6;

			coulombTerm = ( chargei * chargej * coulombFactor ) / rij;

			energy += Aij / rij12 - Bij / rij6 + coulombTerm;
		}  

		/* Hydrogen-bonded term */
		ix4 = -4;
		for ( int i = 0; i < numberHydrogenBonded; i++ ){
			ix4 += 4;
			atomi = (int)hydrogenBondedTerms[ ix4 ];
			atomj = (int)hydrogenBondedTerms[ ix4 + 1 ];
			Cij = hydrogenBondedTerms[ ix4 + 2 ];
			Dij = hydrogenBondedTerms[ ix4 + 3 ];
			atomix3 = atomi * 3;
			atomjx3 = atomj * 3;
			rijx = coordinates[ atomix3 ] - 
					coordinates[ atomjx3 ];
			rijy = coordinates[ atomix3 + 1 ] - 
					coordinates[ atomjx3 + 1 ];
			rijz = coordinates[ atomix3 + 2 ] - 
					coordinates[ atomjx3 + 2 ];
			rij2 = rijx * rijx + rijy * rijy + rijz * rijz;
			rij4 = rij2 * rij2;
			rij6 = rij4 * rij2;
			rij10 = rij6 * rij4;
			rij12 = rij10 * rij2;

			energy += Cij / rij12 - Dij / rij10;
		}  

		return( energy );				
	}
}
