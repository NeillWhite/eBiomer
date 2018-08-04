package com.goosebumpanalytics.biomer;

import java.awt.Color;

public class Atom{

	int	moleculeAtomNumber;
	int	residueAtomNumber;
	int	modelAtomNumber;
	int	totalResidueNumber;
	int	strandResidueNumber;
	int	strandNumber;
	int	numberOfBonds;
	int	bond[], doubleBond[], tripleBond[];
	int	render;
	int	elementNumber;
	int 	numberOfConnections;
	int 	numberOfDoubleBonds;
	int 	numberOfTripleBonds;
	int	sp;
	int	type;
	int	cpkColor;
	int	colorIndex;
	String	elementType;
	String	forceFieldType;
	boolean	selected = true;
	boolean hidden;
	Color	color;
	String	name;
	float	charge;
	int	radius = 170;
	double	mass;
	float	coord[] = new float[ 3 ];
	Matrix3D mat;

	Atom(){
		color = new Color( 192, 192, 192 );
		numberOfBonds = 0;
		render = 0;
		hidden = false;
		sp = 0;
	}

	Atom( String atomName, float xpos, float ypos, float zpos ){
		name = atomName;
		setProperties();
		coord[ 0 ] = xpos;
		coord[ 1 ] = ypos;
		coord[ 2 ] = zpos;
		numberOfBonds = 0;
		numberOfDoubleBonds = 0;
		numberOfTripleBonds = 0;
		render = 0;
		hidden = false;
		sp = 0;
	}

	Atom( String atomName, float xpos, float ypos, float zpos, float charge ){
		name = atomName;
		setProperties();
		coord[ 0 ] = xpos;
		coord[ 1 ] = ypos;
		coord[ 2 ] = zpos;
		numberOfBonds = 0;
		numberOfDoubleBonds = 0;
		numberOfTripleBonds = 0;
		render = 0;
		hidden = false;
		sp = 0;
		this.charge = charge;
	}

	public void setColor( Color color, int colorIndex ){
		this.color = color;
		this.colorIndex = colorIndex;
	}

	public int getColorIndex(){
		return( colorIndex );
	}

	public void changeType( String newName ){
		name = newName;	
		setProperties();
	}

	public void setProperties(){
		forceFieldType = "?";
		cpkColor = 12;
		if ( name.indexOf("Ac") != -1 ){
			color = Color.darkGray;
			elementNumber = 89;
			radius = 295;
			mass = 227;
			elementType = "Ac";
		}
		else if( name.indexOf("Ag") != -1 ){
			color = Color.lightGray;
			cpkColor = 9;
			radius = 398;
			elementNumber = 47;
			mass = 107.9;
			elementType = "Ag";
		}
		else if( name.indexOf("Al") != -1 ){
			color = Color.darkGray;
			cpkColor = 9;
			radius = 338;
			elementNumber = 13;
			mass = 26.98;
			elementType = "Al";
		}
		else if( name.indexOf("Am") != -1 ){
			color = Color.darkGray;
			elementNumber = 95;
			radius = 230;
			mass = 243;
			elementType = "Am";
		}
		else if( name.indexOf("Ar") != -1 ){
			color = Color.pink;
			elementNumber = 18;
			radius = 392;
			mass = 39.95;
			elementType = "Ar";
		}
		else if( name.indexOf("As") != -1 ){
			color = Color.darkGray;
			elementNumber = 33;
			radius = 302;
			mass = 74.92;
			elementType = "As";
		}
		else if( name.indexOf("At") != -1 ){
			color = Color.darkGray;
			elementNumber = 85;
			radius = 302;
			mass = 210;
			elementType = "At";
		}
		else if( name.indexOf("Au") != -1 ){
			color = Color.yellow;
			cpkColor = 6;
			radius = 375;
			elementNumber = 79;
			mass = 197;
			elementType = "Au";
		}
		else if( name.indexOf("Ba") != -1 ){
			color = Color.darkGray;
			cpkColor = 8;
			radius = 335;
			elementNumber = 56;
			mass = 137.3;
			elementType = "Ba";
		}
		else if( name.indexOf("Be") != -1 ){
			color = Color.darkGray;
			elementNumber = 4;
			radius = 88;
			mass = 9.012;
			elementType = "Be";
		}
		else if( name.indexOf("Bi") != -1 ){
			color = Color.darkGray;
			elementNumber = 83;
			radius = 385;
			mass = 209;
			elementType = "Bi";
		}
		else if( name.indexOf("Bk") != -1 ){
			color = Color.darkGray;
			elementNumber = 97;
			radius = 225;
			mass = 247;
			elementType = "Bk";
		}
		else if( name.indexOf("Br") != -1 ){
			color = new Color( 255, 0, 255 );
			cpkColor = 10;
			radius = 302;
			elementNumber = 97;
			mass = 247;
			elementType = "Br";
		}
		else if( name.indexOf("Ca") != -1 ){
			color = Color.darkGray;
			cpkColor = 9;
			radius = 248;
			elementNumber = 20;
			mass = 40.08;
			elementType = "Ca";
		}
		else if( name.indexOf("Cd") != -1 ){
			color = Color.darkGray;
			elementNumber = 48;
			radius = 422;
			mass = 112.4;
			elementType = "Cd";
		}
		else if( name.indexOf("Ce") != -1 ){
			color = Color.darkGray;
			elementNumber = 58;
			radius = 458;
			mass = 140.1;
			elementType = "Ce";
		}
		else if( name.indexOf("Cf") != -1 ){
			color = Color.darkGray;
			elementNumber = 98;
			radius = 222;
			mass = 249;
			elementType = "Cf";
		}
		else if( name.indexOf("Cl") != -1 ){
			color = Color.green;
			elementNumber = 17;
			radius = 250;
			mass = 35.45;
			elementType = "Cl";
		}
		else if( name.indexOf("Cm") != -1 ){
			color = Color.darkGray;
			elementNumber = 96;
			radius = 228;
			mass = 247;
			elementType = "Cm";
		}
		else if( name.indexOf("Co") != -1 ){
			color = Color.darkGray;
			radius = 332;
			elementNumber = 27;
			mass = 58.93;
			elementType = "Co";
		}
		else if( name.indexOf("Cr") != -1 ){
			color = Color.darkGray;
			radius = 338;
			cpkColor = 9;
			elementNumber = 24;
			mass = 52;
			elementType = "Cr";
		}
		else if( name.indexOf("Cs") != -1 ){
			color = Color.darkGray;
			elementNumber = 55;
			radius = 418;
			mass = 132.9;
			elementType = "Cs";
		}
		else if( name.indexOf("Cu") != -1 ){
			color = Color.darkGray;
			cpkColor = 10;
			radius = 380;
			elementNumber = 29;
			mass = 63.55;
			elementType = "Cu";
		}
		else if( name.indexOf("Dy") != -1 ){
			color = Color.darkGray;
			elementNumber = 66;
			radius = 438;
			mass = 162.5;
			elementType = "Dy";
		}
		else if( name.indexOf("Er") != -1 ){
			color = Color.darkGray;
			elementNumber = 68;
			radius = 432;
			mass = 167.3;
			elementType = "Er";
		}
		else if( name.indexOf("Es") != -1 ){
			color = Color.darkGray;
			elementNumber = 99;
			radius = 220;
			mass = 254;
			elementType = "Es";
		}
		else if( name.indexOf("Eu") != -1 ){
			color = Color.darkGray;
			elementNumber = 63;
			radius = 498;
			mass = 152;
			elementType = "Eu";
		}
		else if( name.indexOf("Fe") != -1 ){
			color = new Color( 238, 130, 238 );
			cpkColor = 8;
			radius = 335;
			elementNumber = 26;
			mass = 55.85;
			elementType = "Fe";
		}
		else if( name.indexOf("Fm") != -1 ){
			color = Color.darkGray;
			elementNumber = 100;
			radius = 218;
			mass = 250;
			elementType = "Fm";
		}
		else if( name.indexOf("Fr") != -1 ){
			color = Color.darkGray;
			elementNumber = 87;
			radius = 450;
			mass = 223;
			elementType = "Fr";
		}
		else if( name.indexOf("Ga") != -1 ){
			color = Color.darkGray;
			radius = 305;
			elementNumber = 31;
			mass = 69.72;
			elementType = "Ga";
		}
		else if( name.indexOf("Gd") != -1 ){
			color = Color.darkGray;
			elementNumber = 64;
			radius = 448;
			mass = 157.3;
			elementType = "Gd";
		}
		else if( name.indexOf("Ge") != -1 ){
			color = Color.darkGray;
			elementNumber = 32;
			radius = 292;
			mass = 72.59;
			elementType = "Ge";
		}
		else if( name.indexOf("He") != -1 ){
			color = Color.pink;
			cpkColor = 5;
			radius = 400;
			elementNumber = 2;
			mass = 4.003;
			elementType = "He";
		}
		else if( name.indexOf("Hf") != -1 ){
			color = Color.darkGray;
			elementNumber = 72;
			radius = 392;
			mass = 178.5;
			elementType = "Hf";
		}
		else if( name.indexOf("Hg") != -1 ){
			color = Color.darkGray;
			elementNumber = 80;
			radius = 425;
			mass = 200.6;
			elementType = "Hg";
		}
		else if( name.indexOf("Ho") != -1 ){
			color = Color.darkGray;
			elementNumber = 67;
			radius = 435;
			mass = 164.9;
			elementType = "Ho";
		}
		else if( name.indexOf("In") != -1 ){
			color = Color.darkGray;
			elementNumber = 49;
			radius = 408;
			mass = 114.8;
			elementType = "In";
		}
		else if( name.indexOf("Ir") != -1 ){
			color = Color.darkGray;
			elementNumber = 77;
			radius = 330;
			mass = 192.2;
			elementType = "Ir";
		}
		else if( name.indexOf("Kr") != -1 ){
			color = Color.darkGray;
			elementNumber = 36;
			radius = 400;
			mass = 83.8;
			elementType = "Kr";
		}
		else if( name.indexOf("La") != -1 ){
			color = Color.darkGray;
			elementNumber = 57;
			radius = 468;
			mass = 138.9;
			elementType = "La";
		}
		else if( name.indexOf("Li") != -1 ){
			color = Color.white;
			cpkColor = 14;
			radius = 170;
			elementNumber = 3;
			mass = 6.941;
			elementType = "Li";
		}
		else if( name.indexOf("Lr") != -1 ){
			color = Color.darkGray;
			elementNumber = 103;
			radius = 210;
			mass = 257;
			elementType = "Lr";
		}
		else if( name.indexOf("Lu") != -1 ){
			color = Color.darkGray;
			elementNumber = 71;
			radius = 430;
			mass = 175;
			elementType = "Lu";
		}
		else if( name.indexOf("Md") != -1 ){
			color = Color.darkGray;
			elementNumber = 101;
			radius = 215;
			mass = 256;
			elementType = "Md";
		}
		else if( name.indexOf("Mg") != -1 ){
			color = Color.darkGray;
			cpkColor = 15;
			radius = 275;
			elementNumber = 12;
			mass = 24.31;
			elementType = "Mg";
		}
		else if( name.indexOf("Mn") != -1 ){
			color = Color.darkGray;
			cpkColor = 9;
			radius = 338;
			elementNumber = 25;
			mass = 54.94;
			elementType = "Mn";
		}
		else if( name.indexOf("Mo") != -1 ){
			color = Color.darkGray;
			elementNumber = 42;
			radius = 368;
			mass = 95.94;
			elementType = "Mo";
		}
		else if( name.indexOf("Na") != -1 ){
			color = Color.darkGray;
			elementNumber = 11;
			radius = 243;
			mass = 22.99;
			elementType = "Na";
		}
		else if( name.indexOf("Nb") != -1 ){
			color = Color.darkGray;
			elementNumber = 41;
			radius = 370;
			mass = 92.91;
			elementType = "Nb";
		}
		else if( name.indexOf("Nd") != -1 ){
			color = Color.darkGray;
			elementNumber = 60;
			radius = 452;
			mass = 144.2;
			elementType = "Nd";
		}
		else if( name.indexOf("Ne") != -1 ){
			color = Color.pink;
			elementNumber = 10;
			radius = 280;
			mass = 20.18;
			elementType = "Ne";
		}
		else if( name.indexOf("Ni") != -1 ){
			color = Color.darkGray;
			cpkColor = 10;
			radius = 405;
			elementNumber = 28;
			mass = 58.69;
			elementType = "Ni";
		}
		else if( name.indexOf("No") != -1 ){
			color = Color.darkGray;
			elementNumber = 102;
			radius = 212;
			mass = 253;
			elementType = "No";
		}
		else if( name.indexOf("Np") != -1 ){
			color = Color.darkGray;
			elementNumber = 93;
			radius = 238;
			mass = 237;
			elementType = "Np";
		}
		else if( name.indexOf("Os") != -1 ){
			color = Color.darkGray;
			elementNumber = 76;
			radius = 342;
			mass = 190.2;
			elementType = "Os";
		}
		else if( name.indexOf("Pa") != -1 ){
			color = Color.darkGray;
			elementNumber = 91;
			radius = 222;
			mass = 231;
			elementType = "Pa";
		}
		else if( name.indexOf("Pb") != -1 ){
			color = Color.darkGray;
			elementNumber = 82;
			radius = 385;
			mass = 207.2;
			elementType = "Pb";
		}
		else if( name.indexOf("Pd") != -1 ){
			color = Color.darkGray;
			elementNumber = 46;
			radius = 375;
			mass = 106.4;
			elementType = "Pd";
		}
		else if( name.indexOf("Pm") != -1 ){
			color = Color.darkGray;
			elementNumber = 61;
			radius = 450;
			mass = 147;
			elementType = "Pm";
		}
		else if( name.indexOf("Po") != -1 ){
			color = Color.darkGray;
			elementNumber = 84;
			radius = 420;
			mass = 210;
			elementType = "Po";
		}
		else if( name.indexOf("Pr") != -1 ){
			color = Color.darkGray;
			elementNumber = 59;
			radius = 455;
			mass = 140.9;
			elementType = "Pr";
		}
		else if( name.indexOf("Pt") != -1 ){
			color = Color.darkGray;
			elementNumber = 78;
			radius = 375;
			mass = 195.1;
			elementType = "Pt";
		}
		else if( name.indexOf("Pu") != -1 ){
			color = Color.darkGray;
			elementNumber = 94;
			radius = 232;
			mass = 242;
			elementType = "Pu";
		}
		else if( name.indexOf("Ra") != -1 ){
			color = Color.darkGray;
			elementNumber = 88;
			radius = 358;
			mass = 226;
			elementType = "Ra";
		}
		else if( name.indexOf("Rb") != -1 ){
			color = Color.darkGray;
			elementNumber = 37;
			radius = 368;
			mass = 85.47;
			elementType = "Rb";
		}
		else if( name.indexOf("Re") != -1 ){
			color = Color.darkGray;
			elementNumber = 75;
			radius = 338;
			mass = 186.2;
			elementType = "Re";
		}
		else if( name.indexOf("Rh") != -1 ){
			color = Color.darkGray;
			elementNumber = 45;
			radius = 362;
			mass = 102.9;
			elementType = "Rh";
		}
		else if( name.indexOf("Rn") != -1 ){
			color = Color.pink;
			elementNumber = 88;
			radius = 475;
			mass = 222;
			elementType = "Rn";
		}
		else if( name.indexOf("Ru") != -1 ){
			color = Color.darkGray;
			elementNumber = 44;
			radius = 350;
			mass = 101.1;
			elementType = "Ru";
		}
		else if( name.indexOf("Sb") != -1 ){
			color = Color.darkGray;
			elementNumber = 51;
			radius = 365;
			mass = 121.8;
			elementType = "Sb";
		}
		else if( name.indexOf("Sc") != -1 ){
			color = Color.darkGray;
			radius = 360;
			elementNumber = 21;
			mass = 44.96;
			elementType = "Sc";
		}
		else if( name.indexOf("Se") != -1 ){
			color = Color.darkGray;
			elementNumber = 34;
			radius = 305;
			mass = 78.96;
			elementType = "Se";
		}
		else if( name.indexOf("Si") != -1 ){
			color = Color.darkGray;
			cpkColor = 6;
			radius = 300;
			elementNumber = 14;
			mass = 28.09;
			elementType = "Si";
		}
		else if( name.indexOf("Sm") != -1 ){
			color = Color.darkGray;
			elementNumber = 62;
			radius = 450;
			mass = 150.4;
			elementType = "Sm";
		}
		else if( name.indexOf("Sn") != -1 ){
			color = Color.darkGray;
			elementNumber = 50;
			radius = 365;
			mass = 118.7;
			elementType = "Sn";
		}
		else if( name.indexOf("Sr") != -1 ){
			color = Color.darkGray;
			elementNumber = 38;
			radius = 280;
			mass = 87.62;
			elementType = "Sr";
		}
		else if( name.indexOf("Ta") != -1 ){
			color = Color.darkGray;
			elementNumber = 73;
			radius = 358;
			mass = 180.9;
			elementType = "Ta";
		}
		else if( name.indexOf("Tb") != -1 ){
			color = Color.darkGray;
			elementNumber = 65;
			radius = 440;
			mass = 158.9;
			elementType = "Tb";
		}
		else if( name.indexOf("Tc") != -1 ){
			color = Color.darkGray;
			elementNumber = 43;
			radius = 338;
			mass = 99;
			elementType = "Tc";
		}
		else if( name.indexOf("Te") != -1 ){
			color = Color.darkGray;
			elementNumber = 52;
			radius = 368;
			mass = 127.6;
			elementType = "Te";
		}
		else if( name.indexOf("Th") != -1 ){
			color = Color.darkGray;
			elementNumber = 90;
			radius = 255;
			mass = 232;
			elementType = "Th";
		}
		else if( name.indexOf("Ti") != -1 ){
			color = Color.darkGray;
			cpkColor = 9;
			radius = 368;
			elementNumber = 22;
			mass = 47.88;
			elementType = "Ti";
		}
		else if( name.indexOf("Tl") != -1 ){
			color = Color.darkGray;
			elementNumber = 81;
			radius = 388;
			mass = 204.4;
			elementType = "Tl";
		}
		else if( name.indexOf("Tm") != -1 ){
			color = Color.darkGray;
			elementNumber = 69;
			radius = 430;
			mass = 168.9;
			elementType = "Tm";
		}
		else if( name.indexOf("Une") != -1 ){
			color = Color.darkGray;
			elementNumber = 109;
			mass = 266;
			elementType = "Une";
		}
		else if( name.indexOf("Unh") != -1 ){
			color = Color.darkGray;
			elementNumber = 106;
			mass = 263;
			elementType = "Unh";
		}
		else if( name.indexOf("Uno") != -1 ){
			color = Color.darkGray;
			elementNumber = 108;
			mass = 265;
			elementType = "Uno";
		}
		else if( name.indexOf("Unp") != -1 ){
			color = Color.darkGray;
			elementNumber = 105;
			mass = 260;
			elementType = "Unp";
		}
		else if( name.indexOf("Unq") != -1 ){
			color = Color.darkGray;
			elementNumber = 104;
			mass = 257;
			elementType = "Unq";
		}
		else if( name.indexOf("Uns") != -1 ){
			color = Color.darkGray;
			elementNumber = 107;
			mass = 262;
			elementType = "Uns";
		}
		else if( name.indexOf("Xe") != -1 ){
			color = Color.pink;
			elementNumber = 54;
			radius = 425;
			mass = 131.3;
			elementType = "Xe";
		}
		else if( name.indexOf("Yb") != -1 ){
			color = Color.darkGray;
			elementNumber = 70;
			radius = 485;
			mass = 173;
			elementType = "Yb";
		}
		else if( name.indexOf("Zn") != -1 ){
			color = new Color( 255, 0, 255 );
			cpkColor = 10;
			radius = 362;
			elementNumber = 30;
			mass = 65.39;
			elementType = "Zn";
		}
		else if( name.indexOf("Zr") != -1 ){
			color = Color.darkGray;
			elementNumber = 40;
			radius = 390;
			mass = 91.22;
			elementType = "Zr";
		}
		else if( name.toUpperCase().indexOf("C") == 0 ){
			color = Color.lightGray;
			cpkColor = 0;
			radius = 180;
			elementNumber = 6;
			mass = 12.01;
			elementType = "C";
		}
		else if( name.toUpperCase().indexOf("H") == 0 ){
			color = Color.white;
			cpkColor = 4;
			radius = 80;
			elementNumber = 1;
			mass = 1;
			elementType = "H";
		}
		else if( name.toUpperCase().indexOf("N") == 0 ){
			color = Color.cyan;
			radius = 170;
			cpkColor = 1;
			elementNumber = 7;
			mass = 14.01;
			elementType = "N";
		}
		else if( name.toUpperCase().indexOf("O") == 0 ){
			color = Color.red;
			cpkColor = 2;
			radius = 170;
			elementNumber = 8;
			mass = 16;
			elementType = "O";
		}
		else if( name.toUpperCase().indexOf("B") == 0 ){
			color = Color.darkGray;
			cpkColor = 13;
			radius = 208;
			elementNumber = 5;
			mass = 10.81;
			elementType = "B";
		}
		else if( name.toUpperCase().indexOf("I") == 0 ){
			color = Color.green;
			cpkColor = 11;
			radius = 350;
			elementNumber = 53;
			mass = 126.9;
			elementType = "I";
		}
		else if( name.toUpperCase().indexOf("F") == 0 ){
			color = Color.green;
			cpkColor = 6;
			radius = 160;
			elementNumber = 9;
			mass = 19.0;
			elementType = "F";
		}
		else if( name.toUpperCase().indexOf("P") == 0 ){
			color = Color.orange;
			cpkColor = 8;
			radius = 259;
			elementNumber = 15;
			mass = 30.97;
			elementType = "P";
		}
		else if( name.toUpperCase().indexOf("K") == 0 ){
			color = Color.darkGray;
			cpkColor = 12;
			radius = 332;
			elementNumber = 19;
			mass = 39.1;
			elementType = "K";
		}
		else if( name.toUpperCase().indexOf("S") == 0 ){
			color = Color.yellow;
			cpkColor = 3;
			radius = 255;
			elementNumber = 16;
			mass = 32.07;
			elementType = "S";
		}
		else if( name.toUpperCase().indexOf("U") == 0 ){
			color = Color.darkGray;
			cpkColor = 12;
			radius = 242;
			elementNumber = 92;
			mass = 238;
			elementType = "U";
		}
		else if( name.toUpperCase().indexOf("V") == 0 ){
			color = Color.darkGray;
			cpkColor = 12;
			radius = 332;
			elementNumber = 23;
			mass = 50.94;
			elementType = "V";
		}
		else if( name.toUpperCase().indexOf("W") == 0 ){
			color = Color.darkGray;
			cpkColor = 12;
			radius = 342;
			elementNumber = 74;
			mass = 183.9;
			elementType = "W";
		}
		else if( name.toUpperCase().indexOf("Y") == 0 ){
			color = Color.darkGray;
			cpkColor = 12;
			radius = 445;
			elementNumber = 39;
			mass = 88.91;
			elementType = "Y";
		}
		else if( name.toUpperCase().indexOf("H") != -1 ){
			color = Color.white;
			cpkColor = 4;
			radius = 80;
			elementNumber = 1;
			mass = 1;
			elementType = "H";
		}
		else{  // default to carbon!!
			color = Color.lightGray;
			cpkColor = 12;
			elementNumber = 6;
			mass = 12.01;
			elementType = "C";
		}
	}

	public void addBond( int j ){
		if ( bond == null ){
			bond = new int[ ++numberOfBonds ];
			bond[ 0 ] = j;
		}
		else{
			int newBond[] = new int[ ++numberOfBonds ];
			System.arraycopy( bond, 0, newBond, 0, bond.length );
			bond = newBond;
			bond[ numberOfBonds - 1 ] = j;
		}
	}

	public void addDoubleBond( int j ){
		if ( doubleBond == null ){
			doubleBond = new int[ ++numberOfDoubleBonds ];
			doubleBond[ 0 ] = j;
		}
		else{
			int newDoubleBond[] = new int[ ++numberOfDoubleBonds ];
			System.arraycopy( doubleBond, 0, newDoubleBond, 0, doubleBond.length );
			doubleBond = newDoubleBond;
			doubleBond[ numberOfDoubleBonds - 1 ] = j;
		}
	}

	public void addTripleBond( int j ){
		if ( tripleBond == null ){
			tripleBond = new int[ ++numberOfTripleBonds ];
			tripleBond[ 0 ] = j;
		}
		else{
			int newTripleBond[] = new int[ ++numberOfTripleBonds ];
			System.arraycopy( tripleBond, 0, newTripleBond, 0, tripleBond.length );
			tripleBond = newTripleBond;
			tripleBond[ numberOfTripleBonds - 1 ] = j;
		}
	}

	public void deleteBond( int j ){
		int smallerBondArray[] = new int[ numberOfBonds - 1 ];
		if ( numberOfBonds > 1 ){
			System.arraycopy( bond, 0, smallerBondArray, 0, j );
			if ( j < numberOfBonds - 1 )
				System.arraycopy( bond, j + 1, smallerBondArray,
						j, bond.length - j - 1 );
		}
		bond = smallerBondArray;
		numberOfBonds--;
	}

	public void deleteBondTo( int atomj ){
		int smallerBondArray[] = new int[ numberOfBonds - 1 ];
		int j;
		for ( j = 0; j < numberOfBonds; j++ )
			if ( bond[ j ] == atomj )
				break;
		if ( numberOfBonds > 1 ){
			System.arraycopy( bond, 0, smallerBondArray, 0, j );
			if ( j < numberOfBonds - 1 )
				System.arraycopy( bond, j + 1, smallerBondArray,
						j, bond.length - j - 1 );
		}
		bond = smallerBondArray;
		numberOfBonds--;
	}

	public void deleteDoubleBond( int j ){
		int smallerDoubleBondArray[] = new int[ numberOfDoubleBonds - 1 ];
		if ( numberOfDoubleBonds > 1 ){
			System.arraycopy( doubleBond, 0, smallerDoubleBondArray, 0, j );
			if ( j < numberOfDoubleBonds - 1 )
				System.arraycopy( doubleBond, j + 1, smallerDoubleBondArray,
						j, doubleBond.length - j - 1 );
		}
		doubleBond = smallerDoubleBondArray;
		numberOfDoubleBonds--;
	}

	public void deleteTripleBond( int j ){
		int smallerTripleBondArray[] = new int[ numberOfTripleBonds - 1 ];
		if ( numberOfTripleBonds > 1 ){
			System.arraycopy( tripleBond, 0, smallerTripleBondArray, 0, j );
			if ( j < numberOfTripleBonds - 1 )
				System.arraycopy( tripleBond, j + 1, smallerTripleBondArray,
						j, tripleBond.length - j - 1 );
		}
		tripleBond = smallerTripleBondArray;
		numberOfTripleBonds--;
	}

	public void addBond( int j, int k ){
		if ( bond == null ){
			numberOfBonds = 2;
			bond = new int[ numberOfBonds ];
			bond[ 0 ] = j;
			bond[ 1 ] = k;
		}
		else{
			numberOfBonds += 2;
			int newBond[] = new int[ numberOfBonds ];
			System.arraycopy( bond, 0, newBond, 0, bond.length );
			bond = newBond;
			bond[ numberOfBonds - 2 ] = j;
			bond[ numberOfBonds - 1 ] = k;
		}
	}

	public void addBond( int j, int k, int l ){
		if ( bond == null ){
			numberOfBonds = 3;
			bond = new int[ numberOfBonds ];
			bond[ 0 ] = j;
			bond[ 1 ] = k;
			bond[ 2 ] = l;
		}
		else{
			numberOfBonds += 3;
			int newBond[] = new int[ numberOfBonds ];
			System.arraycopy( bond, 0, newBond, 0, bond.length );
			bond = newBond;
			bond[ numberOfBonds - 3 ] = j;
			bond[ numberOfBonds - 2 ] = k;
			bond[ numberOfBonds - 1 ] = l;
		}
	}

	public void addBond( int j, int k, int l, int m ){
		if ( bond == null ){
			numberOfBonds = 4;
			bond = new int[ numberOfBonds ];
			bond[ 0 ] = j;
			bond[ 1 ] = k;
			bond[ 2 ] = l;
			bond[ 3 ] = m;
		}
		else{
			numberOfBonds += 4;
			int newBond[] = new int[ numberOfBonds ];
			System.arraycopy( bond, 0, newBond, 0, bond.length );
			bond = newBond;
			bond[ numberOfBonds - 4 ] = j;
			bond[ numberOfBonds - 3 ] = k;
			bond[ numberOfBonds - 2 ] = l;
			bond[ numberOfBonds - 1 ] = m;
		}
	}

	public void switchBonds( int j ){
		boolean notSwitched = true;
		for ( int i = 0; i < numberOfDoubleBonds; i++ ){
			if ( doubleBond[ i ] == j ){
				deleteDoubleBond( i );
				addTripleBond( j );
				notSwitched = false;
			}
		}
		if ( notSwitched ){
			for ( int i = 0; i < numberOfTripleBonds; i++ )
				if ( tripleBond[ i ] == j ){
					deleteTripleBond( i );
					notSwitched = false;
				}
		}
		if ( notSwitched )
			addDoubleBond( j );
	}

	public boolean bondedTo( int j ){
		for ( int i = 0; i < numberOfBonds; i++ ){
			if ( bond[ i ] == j )
				return true;
		}
		return false;
	}

	public boolean doubleBondedTo( int j ){
		for ( int i = 0; i < numberOfDoubleBonds; i++ ){
			if ( doubleBond[ i ] == j )
				return true;
		}
		return false;
	}

	public boolean tripleBondedTo( int j ){
		for ( int i = 0; i < numberOfTripleBonds; i++ ){
			if ( tripleBond[ i ] == j )
				return true;
		}
		return false;
	}

	public double distance( Atom atom ){
		return ( Math.sqrt( ( coord[ 0 ] - atom.coord[ 0 ] ) * 
				( coord[ 0 ] - atom.coord[ 0 ] ) +
				( coord[ 1 ] - atom.coord[ 1 ] ) * ( coord[ 1 ] - atom.coord[ 1 ] ) +
				( coord[ 2 ] - atom.coord[ 2 ] ) * ( coord[ 2 ] - atom.coord[ 2 ] ) ) );
	}

	public void setBondDistance( Molecule m, Atom a1, Atom a2, double bondLength ){
		m.resolveCoordinates( a1.moleculeAtomNumber );
		m.resolveCoordinates( a2.moleculeAtomNumber );
		double coord0 = a2.coord[ 0 ] - a1.coord[ 0 ];
		double coord1 = a2.coord[ 1 ] - a1.coord[ 1 ];
		double coord2 = a2.coord[ 2 ] - a1.coord[ 2 ];
		double lengthFactor = bondLength / a2.distance( a1 );
		coord[ 0 ] = (float)( coord0 * lengthFactor + a2.coord[ 0 ] );
		coord[ 1 ] = (float)( coord1 * lengthFactor + a2.coord[ 1 ] );
		coord[ 2 ] = (float)( coord2 * lengthFactor + a2.coord[ 2 ] );
	}

	public double angleAboutXAxis(){
		double	R2D = 57.29577951308232090712;
		double distance = Math.sqrt( coord[ 0 ] * coord[ 0 ] +
				coord[ 1 ] * coord[ 1 ] + coord[ 2 ] * coord[ 2 ] );
		double yComponent = coord[ 1 ] / distance;
		double zComponent = coord[ 2 ] / distance;
		double theta = 0.0;
		if ( ( zComponent == 0 ) && ( yComponent > 0 ) )
			theta = 90.0;
		else if ( ( zComponent == 0 ) && ( yComponent < 0 ) )
			theta = 270.0;
		else if ( ( yComponent == 0 ) && ( zComponent > 0 ) )
			theta = 180.0;
		else if ( ( yComponent == 0 ) && ( zComponent <= 0 ) )
			theta = 0.0;
		else{ 
			theta = Math.atan( Math.abs( yComponent / zComponent ) ) * R2D;
			if ( ( yComponent > 0 ) && ( zComponent > 0 ) )
				theta = 90.0 + Math.atan( Math.abs( zComponent / 
						yComponent ) ) * R2D;
			else if ( ( yComponent < 0 ) && ( zComponent > 0 ) )
				theta += 180.0;
			else if ( ( yComponent < 0 ) && ( zComponent < 0 ) )
				theta = 270.0 + Math.atan( Math.abs( zComponent / 
						yComponent ) ) * R2D;
		}
		return( theta );
	}

	public double angleAboutYAxis(){
		double	R2D = 57.29577951308232090712;
		double distance = Math.sqrt( coord[ 0 ] * coord[ 0 ] +
				coord[ 1 ] * coord[ 1 ] + coord[ 2 ] * coord[ 2 ] );
		double xComponent = coord[ 0 ] / distance;
		double zComponent = coord[ 2 ] / distance;
		double theta = 0.0;
		if ( ( zComponent == 0 ) && ( xComponent > 0 ) )
			theta = 180.0;
		else if ( ( zComponent == 0 ) && ( xComponent <= 0 ) )
			theta = 0.0;
		else if ( ( xComponent == 0 ) && ( zComponent > 0 ) )
			theta = 90.0;
		else if ( ( xComponent == 0 ) && ( zComponent < 0 ) )
			theta = 270.0;
		else{ 
			theta = Math.atan( Math.abs( zComponent / xComponent ) ) * R2D;
			if ( ( zComponent > 0 ) && ( xComponent > 0 ) )
				theta = 90.0 + Math.atan( Math.abs( xComponent / 
						zComponent ) ) * R2D;
			else if ( ( zComponent < 0 ) && ( xComponent > 0 ) )
				theta += 180.0;
			else if ( ( zComponent < 0 ) && ( xComponent < 0 ) )
				theta = 270.0 + Math.atan( Math.abs( xComponent / 
						zComponent ) ) * R2D;
		}
		return( theta );
	}

	public double angleAboutZAxis(){
		double	R2D = 57.29577951308232090712;
		double distance = Math.sqrt( coord[ 0 ] * coord[ 0 ] +
				coord[ 1 ] * coord[ 1 ] + coord[ 2 ] * coord[ 2 ] );
		double xComponent = coord[ 0 ] / distance;
		double yComponent = coord[ 1 ] / distance;
		double theta = 0.0;
		if ( ( xComponent == 0 ) && ( yComponent > 0 ) )
			theta = 90.0;
		else if ( ( xComponent == 0 ) && ( yComponent < 0 ) )
			theta = 270.0;
		else if ( ( yComponent == 0 ) && ( xComponent >= 0 ) )
			theta = 0.0;
		else if ( ( yComponent == 0 ) && ( xComponent < 0 ) )
			theta = 180.0;
		else{ 
			theta = Math.atan( Math.abs( yComponent / xComponent ) ) * R2D;
			if ( ( yComponent > 0 ) && ( xComponent < 0 ) )
				theta = 90.0 + Math.atan( Math.abs( xComponent / 
						yComponent ) ) * R2D;
			else if ( ( yComponent < 0 ) && ( xComponent < 0 ) )
				theta += 180.0;
			else if ( ( yComponent < 0 ) && ( xComponent > 0 ) )
				theta = 270.0 + Math.atan( Math.abs( xComponent / 
						yComponent ) ) * R2D;
		}
		return( theta );
	}

	public double angle( Atom atom1, Atom atom2 ){	
		double x12, x32, y12, y32, z12, z32, l12, l32, dp;
		double	R2D = 57.29577951308232090712;

		x12 = atom1.coord[ 0 ] - atom2.coord[ 0 ];
		y12 = atom1.coord[ 1 ] - atom2.coord[ 1 ];
		z12 = atom1.coord[ 2 ] - atom2.coord[ 2 ];
		x32 = coord[ 0 ] - atom2.coord[ 0 ];
		y32 = coord[ 1 ] - atom2.coord[ 1 ];
		z32 = coord[ 2 ] - atom2.coord[ 2 ];
		l12 = Math.sqrt( x12 * x12 + y12 * y12 + z12 * z12 );
		l32 = Math.sqrt( x32 * x32 + y32 * y32 + z32 * z32 );
		if( l12 == 0.0 ){
			return( 0.0 );
		}
		if( l32 == 0.0 ){
			return( 0.0 );
		}
		dp = ( x12 * x32 + y12 * y32 + z12 * z32 ) / (l12 * l32 );
		if ( dp < -1.0 )
			dp = -1.0;
		else if ( dp > 1.0 )
			dp = 1.0;
		return( R2D * Math.acos( dp ) );
	}

	public double angleInRadians( Atom atom1, Atom atom2 ){	
		double x12, x32, y12, y32, z12, z32, l12, l32, dp;
		x12 = atom1.coord[ 0 ] - atom2.coord[ 0 ];
		y12 = atom1.coord[ 1 ] - atom2.coord[ 1 ];
		z12 = atom1.coord[ 2 ] - atom2.coord[ 2 ];
		x32 = coord[ 0 ] - atom2.coord[ 0 ];
		y32 = coord[ 1 ] - atom2.coord[ 1 ];
		z32 = coord[ 2 ] - atom2.coord[ 2 ];
		l12 = Math.sqrt( x12 * x12 + y12 * y12 + z12 * z12 );
		l32 = Math.sqrt( x32 * x32 + y32 * y32 + z32 * z32 );
		if( l12 == 0.0 ){
			return( 0.0 );
		}
		if( l32 == 0.0 ){
			return( 0.0 );
		}
		dp = ( x12 * x32 + y12 * y32 + z12 * z32 ) / (l12 * l32 );
		if ( dp < -1.0 )
			dp = -1.0;
		else if ( dp > 1.0 )
			dp = 1.0;
		return( Math.acos( dp ) );
	}

	public double torsion( Atom atom1, Atom atom2, Atom atom3 ){
		double   xij, yij, zij;
		double   xkj, ykj, zkj;
		double   xkl, ykl, zkl;
		double   dx, dy, dz;
		double   gx, gy, gz;
		double   bi, bk;
		double   ct, d, ap, app, bibk;

		xij = atom1.coord[ 0 ] - atom2.coord[ 0 ];
		yij = atom1.coord[ 1 ] - atom2.coord[ 1 ];
		zij = atom1.coord[ 2 ] - atom2.coord[ 2 ];
		xkj = atom3.coord[ 0 ] - atom2.coord[ 0 ];
		ykj = atom3.coord[ 1 ] - atom2.coord[ 1 ];
		zkj = atom3.coord[ 2 ] - atom2.coord[ 2 ];
		xkl = atom3.coord[ 0 ] - coord[ 0 ];
		ykl = atom3.coord[ 1 ] - coord[ 1 ];
		zkl = atom3.coord[ 2 ] - coord[ 2 ];

		dx = yij * zkj - zij * ykj;
		dy = zij * xkj - xij * zkj;
		dz = xij * ykj - yij * xkj;
		gx = zkj * ykl - ykj * zkl;
		gy = xkj * zkl - zkj * xkl;
		gz = ykj * xkl - xkj * ykl;

		bi = dx * dx + dy * dy + dz * dz;
		bk = gx * gx + gy * gy + gz * gz;
		ct = dx * gx + dy * gy + dz * gz;
		bibk = bi * bk;
		if ( bibk < 1.0e-6 )	
			return 0;
		ct = ct / Math.sqrt( bibk );
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
		return( app );
	}

	public double torsionInRadians( Atom atom1, Atom atom2, Atom atom3 ){
		double   xij, yij, zij;
		double   xkj, ykj, zkj;
		double   xkl, ykl, zkl;
		double   dx, dy, dz;
		double   gx, gy, gz;
		double   bi, bk;
		double   ct, d, ap, app;
		double 	D2R = 0.01745329251994329576;

		xij = atom1.coord[ 0 ] - atom2.coord[ 0 ];
		yij = atom1.coord[ 1 ] - atom2.coord[ 1 ];
		zij = atom1.coord[ 2 ] - atom2.coord[ 2 ];
		xkj = atom3.coord[ 0 ] - atom2.coord[ 0 ];
		ykj = atom3.coord[ 1 ] - atom2.coord[ 1 ];
		zkj = atom3.coord[ 2 ] - atom2.coord[ 2 ];
		xkl = atom3.coord[ 0 ] - coord[ 0 ];
		ykl = atom3.coord[ 1 ] - coord[ 1 ];
		zkl = atom3.coord[ 2 ] - coord[ 2 ];

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
		return( D2R * app );
	}

	public void setAngle( Molecule m, Atom a1, Atom a2, double angle ){
		m.resolveCoordinates( a1.moleculeAtomNumber );
		m.resolveCoordinates( a2.moleculeAtomNumber );
		m.resolveCoordinates( moleculeAtomNumber );
		double originalAngle = angle( a1, a2 );
		Atom pseudoA1 = new Atom( "a1", a1.coord[ 0 ] - a2.coord[ 0 ],
				a1.coord[ 1 ] - a2.coord[ 1 ], a1.coord[ 2 ] - a2.coord[ 2 ] );
		Atom pseudoA3 = new Atom( "a3", coord[ 0 ] - a2.coord[ 0 ],
				coord[ 1 ] - a2.coord[ 1 ], coord[ 2 ] - a2.coord[ 2 ] );
		pseudoA3.mat = new Matrix3D();
		pseudoA1.mat = new Matrix3D();
		pseudoA3.mat.unit();
		pseudoA1.mat.unit();
		double a1YAngle = pseudoA1.angleAboutYAxis();

		pseudoA1.mat.yrot( -a1YAngle );	
		pseudoA1.mat.transform( pseudoA1.coord, 1 );
		pseudoA1.mat.unit();
		double a1ZAngle = pseudoA1.angleAboutZAxis();
		pseudoA1.mat.zrot( 180.0 -a1ZAngle );	
		pseudoA1.mat.transform( pseudoA1.coord, 1 );

		pseudoA3.mat.yrot( -a1YAngle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.zrot( 180.0 - a1ZAngle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		double a3XAngle = pseudoA3.angleAboutXAxis();
		pseudoA3.mat.unit();
		pseudoA3.mat.xrot( a3XAngle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.yrot( originalAngle - angle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.xrot( -a3XAngle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.zrot( a1ZAngle - 180.0 );
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.yrot( a1YAngle );	
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		pseudoA3.mat.unit();
		pseudoA3.mat.translate( a2.coord[ 0 ], a2.coord[ 1 ], a2.coord[ 2 ] );
		pseudoA3.mat.transform( pseudoA3.coord, 1 );
		coord[ 0 ] = pseudoA3.coord[ 0 ];
		coord[ 1 ] = pseudoA3.coord[ 1 ];
		coord[ 2 ] = pseudoA3.coord[ 2 ];
		m.updateCoordinates( this );
	}

	public void setTorsion( Molecule m, Atom a1, Atom a2, Atom a3, double torsion ){
		m.resolveCoordinates( a1.moleculeAtomNumber );
		m.resolveCoordinates( a2.moleculeAtomNumber );
		m.resolveCoordinates( a3.moleculeAtomNumber );
		m.resolveCoordinates( moleculeAtomNumber );
		double originalTorsion = torsion( a1, a2, a3 );
		Atom pseudoA2 = new Atom( "a2", a2.coord[ 0 ] - a3.coord[ 0 ],
				a2.coord[ 1 ] - a3.coord[ 1 ], a2.coord[ 2 ] - a3.coord[ 2 ] );
		Atom pseudoA4 = new Atom( "a4", coord[ 0 ] - a3.coord[ 0 ],
				coord[ 1 ] - a3.coord[ 1 ], coord[ 2 ] - a3.coord[ 2 ] );
		pseudoA4.mat = new Matrix3D();
		pseudoA4.mat.unit();
		pseudoA2.mat = new Matrix3D();
		pseudoA2.mat.unit();
		double a2YAngle = pseudoA2.angleAboutYAxis();
		pseudoA4.mat.yrot( -a2YAngle );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		pseudoA4.mat.unit();
		pseudoA2.mat.yrot( -a2YAngle );
		pseudoA2.mat.transform( pseudoA2.coord, 1 );	
		pseudoA2.mat.unit();
		double a2ZAngle = pseudoA2.angleAboutZAxis();
		pseudoA4.mat.zrot( 180.0 - a2ZAngle );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		pseudoA4.mat.unit();
		pseudoA4.mat.xrot( originalTorsion - torsion );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		pseudoA4.mat.unit();
		pseudoA4.mat.zrot( a2ZAngle - 180.0 );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		pseudoA4.mat.unit();
		pseudoA4.mat.yrot( a2YAngle );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		pseudoA4.mat.unit();
		pseudoA4.mat.translate( a3.coord[ 0 ], a3.coord[ 1 ], a3.coord[ 2 ] );
		pseudoA4.mat.transform( pseudoA4.coord, 1 );	
		coord[ 0 ] = pseudoA4.coord[ 0 ];
		coord[ 1 ] = pseudoA4.coord[ 1 ];
		coord[ 2 ] = pseudoA4.coord[ 2 ];
		m.updateCoordinates( this );
	}
}
