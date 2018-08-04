package com.goosebumpanalytics.biomer;

import java.awt.*;

class MoleculeCanvas extends Canvas{

	int	zDepth, shadePartition = 0, zMid;
	int	atomNumber, bondNumber, atomNumberZ, bondedAtomNumberZ, bondedAtomNumber;
	int	WIREFRAME = 0, BALLANDSTICK = 1, POLYTUBES = 2, SPHERES = 3, SPACEFILL = 4;
	int	HIDE = 5, atomNumberx3x, atomNumberx3y, atomNumberx3z;
	int	bondedAtomNumberx3x, bondedAtomNumberx3y, bondedAtomNumberx3z;
	int	midBondX, midBondY, midBondZ, renderLoop;
	int	POLYTUBERADIUS = 10, STICKRADIUS = 5, BALLRADIUS = 10, SPHERERADIUS = 40;
	int	lassoPositions[], newlassoPositions[], maxlassoPositions = 1000; 
	int	lassoPositionCounter = 0;
	float	translateX, translateY, translateZ;
	float	zoomX, zoomY, zoomZ, atomActualCoords[];
	Atom	atom1 = null, atom2 = null, atom3 = null, atom4 = null, firstDrawAtom = null;
	Atom	secondDrawAtom = null, bondedAtom;
	int	zs[], atomScreenCoords[];
	int	x, y;
	int	ww, wh;
	int	prevx, prevy;
	int 	spacefillUpdate = 0;
	double	zoomFactor = 10;
	Molecule m;
	Color	atomColor;
	Color	bondedatomColor;
	Color	backgroundColor = Color.black, textColor = Color.white;
	boolean ROTATE = false, ATOMLABEL = false, CENTER = false, DRAG = false;
	boolean	ZOOM = false, UNZOOM = false, POINT = false, LASSO = false, BUILD = false;
	boolean	LABELS = false, UNROTATE = false, SHINY = true, STOP = false, PLACE = false;
	boolean	COORD = false, DIST = false, ANGLE = false, TORSION = false, DELETE = false;
	boolean nameLabel = true, elementLabel = true, numberLabel = true, typeLabel = false;
	boolean	chargeLabel = false;
	float	DEPTHCUEFACTOR = .94f, INVDEPTHCUEFACTOR = (float)(1/.94);
	float	xmin,xmax,ymin,ymax,zmin,zmax,xfac;
	float    transX, transY, transZ;
	Matrix3D amat = new Matrix3D(), tmat = new Matrix3D();
	Matrix3D numat = new Matrix3D();
	Matrix3D drawMat = null;
	String	statusText;
	String	currentAtomName = "C", fragmentName;
	Image	backBuffer, sphereBuffer;
	Graphics backGC, sphereGC;
	BiomerFrame BMF;
	Graphics drawG, buildG;
	MoleculeCanvas3D mC3D;
	Dimension canvasSize;

	public MoleculeCanvas( BiomerFrame BMF, int ww, int wh ){
		atomColor = new Color(192,192,192);
		bondedatomColor = new Color(192,192,192);
		this.BMF = BMF;
		canvasSize = size();	
		//ww = canvasSize.width;
		//wh = canvasSize.height;
		this.ww = ww;
		this.wh = wh;
		//backBuffer = createImage( ww, wh );
		//backGC = backBuffer.getGraphics();
		translateX = ww / 2;
		translateY = ( wh - 100 )/ 2;
		translateZ = 0;
		lassoPositions = new int[ maxlassoPositions ];
		statusText = "empty";
		m = new Molecule();
		atomScreenCoords = new int[ 3 ];
		atomActualCoords = new float[ 3 ];
		amat.unit();
		setBackground( backgroundColor );
		mC3D = new MoleculeCanvas3D( this );
	}

	public void resizeCanvas(){
		canvasSize = size();	
		ww = canvasSize.width;
		wh = canvasSize.height;
		backBuffer = createImage( ww, wh );
		backGC = backBuffer.getGraphics();
		repaint();
		float f1 = ww / ( xmax - xmin );
		float f2 = wh / ( ymax - ymin );
		xfac = 0.7f * (f1<f2 ? f1:f2);
		shadePartition = (int)(xfac*zDepth/8) > 1 ? (int)(xfac*zDepth/8) : 1;
		translateX = ww / 2;
		translateY = ( wh - 100 )/ 2;
	}

	public void displayMolecule( Molecule m1 ){
		m = m1;
		if ( m.name == null )
			m.name = "m0";
		statusText = m.name;
		m.mat = numat;
		findBB();
		float f1 = ww / ( xmax - xmin );
		float f2 = wh / ( ymax - ymin );
		xfac = 0.7f * (f1<f2 ? f1:f2);
		zoomX = xfac;
		zoomY = -xfac;
		zoomZ = 16 * xfac / ww;
		backBuffer = createImage( ww, wh );
		backGC = backBuffer.getGraphics();
		shadePartition = (int)(zDepth/8) > 1 ? (int)(zDepth/8) : 1;
		transform();
		repaint();
	}

	/** Find the bounding box of this model */
	private void findBB() {
		if (( m == null ) || ( m.numberOfAtoms <= 0) )
			return;
		float xmin = xmax = m.atom[0].coord[ 0 ];
		float ymin = ymax = m.atom[0].coord[ 1 ];
		float zmin = zmax = m.atom[0].coord[ 2 ];
		for (int i = 0; i < m.numberOfAtoms; i++) {
			float x = m.atom[ i ].coord[ 0 ];
			if (x < xmin)
				xmin = x;
			if (x > xmax)
				xmax = x;
			float y = m.atom[ i ].coord[ 1 ];
			if (y < ymin)
				ymin = y;
			if (y > ymax)
				ymax = y;
			float z = m.atom[ i ].coord[ 2 ];
			if (z < zmin)
				zmin = z;
			if (z > zmax)
				zmax = z;
		}
		this.xmin = xmin;
		this.ymin = ymin;
		this.zmin = zmin;
		transX = (float)(0.5*( xmax - xmin ) );
		transY = (float)(0.5*( ymax - ymin ) );
		transZ = (float)(0.5*( zmax - zmin ) );
		zDepth = (int)((xmax + xmin)/2);
		if ((ymax + ymin) > zDepth )
			zDepth = (int)((ymax + ymin)/2);
		if ((zmax - zmin) > zDepth )
			zDepth = (int)((zmax - zmin)/2);
		zMid = (int)(zDepth/2);
	}
	public boolean mouseDown(Event e, int x, int y) {
		if (  m != null ){
			float min = 10;
			int closestatom = 0, closestatomx3 = 0;
			Atom closeAtom = null;
			boolean rightClick = ( e.modifiers & Event.META_MASK ) == Event.META_MASK;
			boolean middleClick = ( e.modifiers & Event.ALT_MASK ) == Event.ALT_MASK;
			if ( ATOMLABEL || POINT || COORD || DIST || ANGLE || TORSION || BUILD || 
					PLACE || DELETE ){
				int jx3 = -3;
				for( int i = 0; i < m.numberOfAtoms; i++ ){
					jx3 += 3;
					float distance = (float)Math.sqrt( ( m.screenCoordinates[ jx3 ] -
							x ) * ( m.screenCoordinates[ jx3 ] - x ) +
							( m.screenCoordinates[ jx3 + 1 ] - y ) * 
							( m.screenCoordinates[ jx3 + 1 ] - y ) );
					if ( distance < min ){
						min = distance;
						closestatom = i;
					}
				}
				if ( min < 5 ){
					closeAtom = m.atom[ closestatom ];
					closestatomx3 = closestatom * 3;
					closeAtom.coord[ 0 ] = m.actualCoordinates[ closestatomx3 ];
					closeAtom.coord[ 1 ] = m.actualCoordinates[ closestatomx3 + 1 ];
					closeAtom.coord[ 2 ] = m.actualCoordinates[ closestatomx3 + 2 ];
					if ( DELETE ){ // deleting atom
						m.deleteAtom( closestatom );
						m.saved = false;
						statusText = "";
						repaint();
						DELETE = false;
					}
					else if ( PLACE ){ // putting in new group
						Fragments frag = new Fragments();
						int atomToDelete[] = new int[ 1 ];
						int atomToBondTo[] = new int[ 1 ];
						int angleAtom[] = new int[ 1 ];
						int previousNumberOfAtoms = m.numberOfAtoms;
						float closeX = closeAtom.coord[ 0 ];
						float closeY = closeAtom.coord[ 1 ];
						float closeZ = closeAtom.coord[ 2 ];
						Residue newRes = frag.getResidue( fragmentName,
								atomToDelete, atomToBondTo, angleAtom );
						float bondToX = newRes.actualCoordinates[ 
						                                         atomToDelete[ 0 ] * 3 ];
						float bondToY = newRes.actualCoordinates[ 
						                                         atomToDelete[ 0 ] * 3 + 1 ];
						float bondToZ = newRes.actualCoordinates[ 
						                                         atomToDelete[ 0 ] * 3 + 2 ];
						int ix3 = -3;
						for ( int i = 0; i < newRes.numberOfAtoms; i++ ){
							ix3 += 3;
							newRes.actualCoordinates[ ix3 ] += 
									closeX - bondToX;
							newRes.actualCoordinates[ ix3 + 1 ] += 
									closeY - bondToY;
							newRes.actualCoordinates[ ix3 + 2 ] += 
									closeZ - bondToZ;
						}
						m.addResidue( closeAtom.strandNumber, newRes );
						int newAtomNumber = m.numberOfAtoms - previousNumberOfAtoms;
						int atomList[] = new int[ newAtomNumber ];
						for( int i = 0; i < newAtomNumber; i++ ){
							atomList[ i ] = previousNumberOfAtoms + i;
						}
						if ( angleAtom[ 0 ] == -1 ){
							m.addFragment( closestatom, previousNumberOfAtoms + 
									atomToBondTo[ 0 ], -1, atomList, 
									newAtomNumber );
						}
						else{
							m.addFragment( closestatom, previousNumberOfAtoms + 
									atomToBondTo[ 0 ], previousNumberOfAtoms +
									angleAtom[ 0 ], atomList, newAtomNumber );
						}
						m.saved = false;
						closeAtom.addBond( previousNumberOfAtoms + 
								atomToBondTo[ 0 ] );
						if ( atomToDelete[ 0 ] != -1 )
							m.deleteAtom( previousNumberOfAtoms + 
									atomToDelete[ 0 ] );
						statusText = "";
						repaint();
						PLACE = false;
					}
					else if ( ATOMLABEL ){
						Graphics textG = getGraphics();
						textG.setColor( m.atom[ closestatom ].color );
						textG.drawString( atomString( m.atom[ closestatom ] ),
								m.screenCoordinates[ closestatomx3 ],
								m.screenCoordinates[ closestatomx3 + 1 ] );
					}
					else if ( POINT ){
						if ( BMF.selectAtom.getState() ){
							m.atom[ closestatom ].selected = true;
						}	
						else if ( BMF.selectResidue.getState() ){
							int selectedresidue = m.atom[ closestatom ].
									totalResidueNumber;
							for ( int i = 0; i < m.numberOfResidues; i++ ){
								if ( m.atom[ i ].totalResidueNumber ==
										selectedresidue ){
									m.atom[ i ].selected = true;
								}
							}
						}	
						else if ( BMF.selectStrand.getState() ){
							int selectedStrand = m.atom[ closestatom ].
									strandNumber;
							for ( int i = 0; i < m.numberOfStrands; i++ ){
								if ( m.atom[ i ].strandNumber ==
										selectedStrand ){
									m.atom[ i ].selected = true;
								}
							}
						}	
						else if ( BMF.selectMolecule.getState() ){
							for ( int i = 0; i < m.numberOfAtoms; i++ )
								m.atom[ i ].selected = true;
						}
						repaint();
					}
					else if ( COORD ){
						statusText = m.name + " " + closeAtom.name +
								" " + closeAtom.coord[ 0 ] + ", " + closeAtom.coord[ 
								                                                    1 ] + ", " + closeAtom.coord[ 2 ];
						float temp[] = new float[ 3 ];
						int temp2[] = new int[ 3 ];
						temp2[ 0 ] = m.screenCoordinates[ closeAtom.moleculeAtomNumber*3];
						temp2[ 1 ] = m.screenCoordinates[ closeAtom.moleculeAtomNumber*3+1];
						temp2[ 2 ] = m.screenCoordinates[ closeAtom.moleculeAtomNumber*3+2];
						m.mat.inverseTransform( temp, temp2, 1 );
						repaint();
					}
					else if ( DIST ){
						if ( atom1 == null ){
							atom1 = closeAtom;
							statusText = m.name + " Distance (" + 
									closeAtom.name + ")-";
						}
						else if ( atom2 == null ){
							atom2 = closeAtom;
							statusText += "(" + closeAtom.name + ")=" +
									(float)atom2.distance( atom1 );
							atom1 = atom2 = null;
						}
						repaint();
					}
					else if ( ANGLE ){
						if ( atom1 == null ){
							atom1 = closeAtom;
							statusText = m.name + " Angle (" + 
									closeAtom.name + ")-";
						}
						else if ( atom2 == null ){
							atom2 = closeAtom;
							statusText += "(" + closeAtom.name + ")-";
						}
						else if ( atom3 == null ){
							atom3 = closeAtom;
							statusText += "(" + closeAtom.name + ")=" +
									(float)atom3.angle( atom1, atom2 );
							atom1 = atom2 = atom3 = null;
						}
						repaint();
					}
					else if ( TORSION ){
						if ( atom1 == null ){
							atom1 = closeAtom;
							statusText = m.name + " Torsion (" + 
									closeAtom.name + ")-";
						}
						else if ( atom2 == null ){
							atom2 = closeAtom;
							statusText += "(" + closeAtom.name + ")-";
						}
						else if ( atom3 == null ){
							atom3 = closeAtom;
							statusText += "(" + closeAtom.name + ")-";
						}
						else if ( atom4 == null ){
							atom4 = closeAtom;
							statusText += "(" + closeAtom.name + ")=" +
									(float)atom4.torsion( atom1, atom2, atom3 );
							atom1 = atom2 = atom3 = atom4 = null;
						}
						repaint();
					}
					else if ( BUILD ){ // making bond from existing atom
						firstDrawAtom = closeAtom;
					}
				}	
				else if ( DELETE ){  // deleting bond
					min = 10;
					DELETE = false;
					int atomNumber = -1;
					int bondedToAtomNumber = -1;
					int ix3 = -3;
					statusText = "";
					for ( int i = 0; i < m.numberOfAtoms; i++ ){
						ix3 += 3;
						for ( int j = 0; j < m.atom[ i ].numberOfBonds; j++ ){
							int bx3 = m.atom[ i ].bond[ j ] * 3;
							double ax = m.screenCoordinates[ ix3 ];
							double ay = m.screenCoordinates[ ix3 + 1 ];
							double bx = m.screenCoordinates[ bx3 ];
							double by = m.screenCoordinates[ bx3 + 1 ];
							double distanceToA = Math.sqrt( ( ax - x ) * 
									( ax - x ) + ( ay - y ) * ( ay - y ) );
							double bondLength = Math.sqrt( ( ax - bx ) * 
									( ax - bx ) + ( ay - by ) * ( ay - by ) );
							double fractionOfDistance = distanceToA / bondLength;
							if ( ( fractionOfDistance > 1 ) || 
									( fractionOfDistance < 0 ) )
								continue;
							double xPointOnBond = bx * fractionOfDistance +
									ax * ( 1 - fractionOfDistance );
							double yPointOnBond = by * fractionOfDistance +
									ay * ( 1 - fractionOfDistance );
							double proximityToBond = Math.sqrt( 
									( x - xPointOnBond ) * ( x - xPointOnBond ) + 
									( y - yPointOnBond ) * ( y - yPointOnBond ) );
							if ( proximityToBond < min ){
								min = (float)proximityToBond;
								atomNumber = i;
								bondedToAtomNumber = m.atom[ i ].bond[ j ];
							}
						}
					}
					if ( min < 5 ){  
						m.atom[ atomNumber ].deleteBondTo( bondedToAtomNumber );
						m.saved = false;
					}
					repaint();
				}
				else if ( PLACE ){
					float realPosition[] = new float[ 3 ];
					int screenPosition[] = new int[ 3 ];
					screenPosition[ 0 ] = x;
					screenPosition[ 1 ] = y;
					screenPosition[ 2 ] = 0;
					if ( m.numberOfAtoms == 0 ){
						translateX = 371;
						translateY = 218;
						translateZ = 0;
						zoomX = xfac = 62.44019f;
						zoomY = -62.44019f;
						zoomZ = 1.3648129f;
						transX = 3.3874998f;
						transY = 2.926f;
						transZ = 0.90349996f;
						zDepth = 6;
						zMid = 3;
						m.mat.unit();
						m.mat.scale(zoomX, zoomY, zoomZ );
						m.mat.translate( translateX + transX, 
								translateY + transY, 
								translateZ + transZ );
						m.mat.inverseTransform( realPosition, 
								screenPosition, 1 );
						amat.unit();
					}
					Fragments frag = new Fragments();
					int previousNumberOfAtoms = m.numberOfAtoms;
					int atomToDelete[] = new int[ 1 ];
					int atomToBondTo[] = new int[ 1 ];
					int angleAtom[] = new int[ 1 ];
					Residue newRes = frag.getResidue( fragmentName,
							atomToDelete, atomToBondTo, angleAtom );
					m.addResidue( 0, newRes );
					int newAtomNumber = m.numberOfAtoms - previousNumberOfAtoms;
					int atomList[] = new int[ newAtomNumber ];
					for( int i = 0; i < newAtomNumber; i++ ){
						atomList[ i ] = previousNumberOfAtoms + i;
					}
					float centerOfMass[] = new float[ 3 ];
					newRes.getCenterOfMass( centerOfMass ); 
					m.mat.inverseTransform( realPosition, 
							screenPosition, 1 );
					Atom pseudo = new Atom( "pseudo", realPosition[ 0 ], 
							realPosition[ 1 ], realPosition[ 2 ] );
					Atom com = new Atom( "com", centerOfMass[ 0 ], 
							centerOfMass[ 1 ], centerOfMass[ 2 ] );
					m.addAtom( 0, pseudo );
					m.addAtom( 0, com );
					m.setBondDistance( pseudo.moleculeAtomNumber, 
							com.moleculeAtomNumber, 0.0, atomList, newAtomNumber );
					m.deleteAtom( com.moleculeAtomNumber );
					m.deleteAtom( pseudo.moleculeAtomNumber );   
					m.saved = false;
					statusText = "";
					repaint();
					redraw();
					PLACE = false;
				}
				else if ( BUILD ){  // creating new atom
					if ( ! ( rightClick ) ){
						atomScreenCoords[ 0 ] = x;
						atomScreenCoords[ 1 ] = y;
						atomScreenCoords[ 2 ] = 0;
						if ( m.numberOfAtoms == 0 ){
							translateX = 371;
							translateY = 218;
							translateZ = 0;
							zoomX = xfac = 62.44019f;
							zoomY = -62.44019f;
							zoomZ = 1.3648129f;
							transX = 3.3874998f;
							transY = 2.926f;
							transZ = 0.90349996f;
							zDepth = 6;
							zMid = 3;
							m.mat.unit();
							m.mat.scale(zoomX, zoomY, zoomZ );
							m.mat.translate( translateX + transX, 
									translateY + transY, 
									translateZ + transZ );
							m.mat.inverseTransform( atomActualCoords, 
									atomScreenCoords, 1 );
							amat.unit();
						}
						m.mat.inverseTransform( atomActualCoords, 
								atomScreenCoords, 1 );
						firstDrawAtom = new Atom( currentAtomName, 
								atomActualCoords[0], atomActualCoords[1], 
								atomActualCoords[2] );
						if ( m.numberOfStrands == 0 )
							m.addStrand();
						if ( m.numberOfResidues == 0 ){
							Residue buildResidue = new Residue();
							buildResidue.name = "BIO";
							buildResidue.addAtom( firstDrawAtom );
							m.addResidue( 0, buildResidue );
						}
						else 
							m.addAtom( 0, firstDrawAtom );
						m.saved = false;
						repaint();
					}
					else{ // right click -- this means to change bond types
						min = 10;
						int atomNumber = -1;
						int bondedToAtomNumber = -1;
						for ( int i = 0; i < m.numberOfAtoms; i++ ){
							int ix3 = i * 3;
							for ( int j = 0; j < m.atom[ i ].numberOfBonds; j++ ){
								int bx3 = m.atom[ i ].bond[ j ] * 3;
								double ax = m.screenCoordinates[ ix3 ];
								double ay = m.screenCoordinates[ ix3 + 1 ];
								double bx = m.screenCoordinates[ bx3 ];
								double by = m.screenCoordinates[ bx3 + 1 ];
								double distanceToA = Math.sqrt( ( ax - x ) * ( ax - x ) +
										( ay - y ) * ( ay - y ) );
								double bondLength = Math.sqrt( ( ax - bx ) * ( ax - bx ) +
										( ay - by ) * ( ay - by ) );
								double fractionOfDistance = distanceToA / bondLength;
								if ( ( fractionOfDistance > 1 ) || ( fractionOfDistance < 0 ) )
									continue;
								double xPointOnBond = bx * fractionOfDistance +
										ax * ( 1 - fractionOfDistance );
								double yPointOnBond = by * fractionOfDistance +
										ay * ( 1 - fractionOfDistance );
								double proximityToBond = Math.sqrt( ( x - xPointOnBond ) *
										( x - xPointOnBond ) + ( y - yPointOnBond ) *
										( y - yPointOnBond ) );
								if ( proximityToBond < min ){
									min = (float)proximityToBond;
									atomNumber = i;
									bondedToAtomNumber = m.atom[ i ].bond[ j ];
								}
							}
						}
						if ( min < 5 ){  
							int eleA = m.atom[ atomNumber ].elementNumber;
							int eleB = m.atom[ bondedToAtomNumber ].elementNumber;
							// don't do it if not right atoms
							if ( ( ( eleA == 6 ) || ( eleA == 7 ) || ( eleA == 8 ) || 
									( eleA == 14 ) || ( eleA == 15 ) || ( eleA == 16 ) ) &&
									( ( eleB == 6 ) || ( eleB == 7 ) || ( eleB == 8 ) || 
											( eleB == 14 ) || ( eleB == 15 ) || ( eleB == 16 ) ) ){
								m.atom[ atomNumber ].switchBonds( bondedToAtomNumber );
								repaint();
							}
						}
					}
				}
			}
		}
		prevx = x;
		prevy = y;
		return true;
	}

	public boolean mouseDrag(Event e, int x, int y) {
		boolean rightClick = ( e.modifiers & Event.META_MASK ) == Event.META_MASK;
		boolean middleClick = ( e.modifiers & Event.ALT_MASK ) == Event.ALT_MASK;
		if ( ROTATE ){
			tmat.unit();
			float xtheta = (prevy - y) * (360.0f / ww);
			float ytheta = (x - prevx) * (360.0f / wh);
			tmat.xrot(xtheta);
			tmat.yrot(ytheta);
			amat.mult(tmat);
			BMF.moveScrollbar( (int)(( x - prevx )*360/(ww-30)), 
					(int)((y - prevy)*360/(ww-100)) );
			repaint();
			prevx = x;
			prevy = y;
			return true;
		}
		else if ( DRAG ){
			translateX = translateX + (x - prevx)/10;
			translateY = translateY + (y - prevy)/10;
			repaint();
		}
		else if ( ZOOM ){
			zoomX = zoomX * ( 1000 + x - prevx )/1000;
			zoomY = zoomY * ( 1000 + x - prevx )/1000;
			zoomZ = zoomZ * ( 1000 + x - prevx )/1000;
			repaint();
		}
		else if ( LASSO ){
			if ( drawG == null )
				drawG = getGraphics();
			if ( newlassoPositions == null )
				newlassoPositions = new int[ maxlassoPositions ];
			int lassoPositionCounterx2 = lassoPositionCounter * 2;
			if ( lassoPositionCounterx2 >= maxlassoPositions ){
				maxlassoPositions = maxlassoPositions + 1000;
				newlassoPositions = new int[ 2 * maxlassoPositions ];
				System.arraycopy( lassoPositions, 0, newlassoPositions, 0,
						lassoPositions.length );
				lassoPositions = newlassoPositions;
			}
			lassoPositions[ lassoPositionCounterx2 ] = x;
			lassoPositions[ lassoPositionCounterx2 + 1 ] = y;
			drawG.setColor( textColor );
			if ( lassoPositionCounter != 0 )
				drawG.drawLine( lassoPositions[ 2 * ( lassoPositionCounter - 1 ) ],
						lassoPositions[ 2 * ( lassoPositionCounter - 1 ) + 1 ], 
						x, y );
			lassoPositionCounter++;	
		}
		else if ( BUILD ){   // MOUSE DRAG
			atomScreenCoords[ 0 ] = x;
			atomScreenCoords[ 1 ] = y;
			atomScreenCoords[ 2 ] = 0;
			if ( ! ( middleClick ) ){
				if ( secondDrawAtom == null ){   // making new draw atom
					m.mat.inverseTransform( atomActualCoords, 
							atomScreenCoords, 1 );
					secondDrawAtom = new Atom( currentAtomName, 
							atomActualCoords[0], atomActualCoords[1], 
							atomActualCoords[2] );
					m.addAtom( firstDrawAtom.totalResidueNumber, secondDrawAtom );
					m.saved = false;
					if ( secondDrawAtom.moleculeAtomNumber > 
					firstDrawAtom.moleculeAtomNumber )
						firstDrawAtom.addBond( 
								secondDrawAtom.moleculeAtomNumber );
					else
						secondDrawAtom.addBond( 
								firstDrawAtom.moleculeAtomNumber );
				}
				m.mat.inverseTransform( secondDrawAtom.coord, atomScreenCoords, 1 );
				m.updateCoordinates( secondDrawAtom );
				repaint();
			}
			else{  // middle click - means moving atom
				m.mat.inverseTransform( firstDrawAtom.coord, atomScreenCoords, 1 );
				m.updateCoordinates( firstDrawAtom );
				m.saved = false;
				repaint();
			}
		}
		return true;
	}

	public boolean mouseUp( Event e, int x, int y ){
		boolean rightClick = ( e.modifiers & Event.META_MASK ) == Event.META_MASK;
		boolean middleClick = ( e.modifiers & Event.ALT_MASK ) == Event.ALT_MASK;
		boolean closedPath = false, advance = false;
		if ( LASSO ){
			int xval, yval, xmax, xmin, ymax, ymin, xtopcnt = 0, xbottomcnt = 0;
			int yleftcnt = 0, yrightcnt = 0;
			int ix2 = -2, lassoX, lassoY;
			for ( int i = 0; i < lassoPositionCounter - 1; i++ ){
				if ( closedPath )
					break;
				ix2 += 2;
				xval = lassoPositions[ ix2 ];
				yval = lassoPositions[ ix2 + 1 ];
				for ( int j = i + 7; j < lassoPositionCounter; j++ ){
					int jx2 = j * 2;
					if ( Math.abs( xval - lassoPositions[ jx2 ] ) < 5 ){
						if ( Math.abs( yval - lassoPositions[ jx2 + 1 ] )<5){
							// cross-over
							closedPath = true;
							System.arraycopy( lassoPositions, ix2,
									newlassoPositions, 0, jx2-ix2 );
							lassoPositions = newlassoPositions;
							lassoPositionCounter = (jx2-ix2)/2 + 1;
							break;
						}
					}
				}     // all of this cuts off the end of the loop (makes a circle)
			}
			if ( closedPath ){
				xmax = xmin = lassoPositions[ 0 ];
				ymax = ymin = lassoPositions[ 1 ];
				ix2 = -2;
				// this encloses the lasso circle in a box
				for ( int i = 0; i < lassoPositionCounter - 1; i++ ){
					ix2 += 2;
					lassoX = lassoPositions[ ix2 ];
					lassoY = lassoPositions[ ix2 + 1 ];
					if ( lassoX > xmax )
						xmax = lassoX;
					else if ( lassoX < xmin )
						xmin = lassoX;
					if ( lassoY > ymax )
						ymax = lassoY;
					else if ( lassoY < ymin )
						ymin = lassoY;
				}	
				xmax += 5;
				xmin -= 5;
				ymin -= 5;
				ymax += 5;
				int ix3 = -3;
				for ( int i = 0; i < m.numberOfAtoms; i++ ){
					ix3 += 3;
					// excludes all atoms not in the box
					if ( ! ( ( m.screenCoordinates[ ix3 ] <= xmax ) &&
							( m.screenCoordinates[ ix3 ] >= xmin ) &&
							( m.screenCoordinates[ ix3 + 1 ] <= ymax ) &&
							( m.screenCoordinates[ ix3 + 1 ] >= ymin ) ) )
						continue;
					int atomxcoord = m.screenCoordinates[ ix3 ];
					int atomycoord = m.screenCoordinates[ ix3 + 1 ];
					for ( int j = 0; j < lassoPositionCounter - 1; j++ ){
						int jx2 = j * 2;
						lassoX = lassoPositions[ jx2 ];
						lassoY = lassoPositions[ jx2 + 1 ];
						if ( Math.abs( lassoX - atomxcoord ) < 4 ){
							if ( atomycoord < lassoY ){
								xtopcnt++;
							}
							else{
								xbottomcnt++;
							}
							advance = true;
						}
						if ( Math.abs( lassoY - atomycoord ) < 4 ){
							if ( atomxcoord < lassoX ){
								yleftcnt++;
							}
							else{
								yrightcnt++;
							}
							advance = true;
						}
						if ( advance ){
							j += 7;
							advance = false;
						}
					}
					if ( ( xtopcnt % 2 != 0 ) && ( xbottomcnt % 2 != 0 ) &&
							( yleftcnt % 2 != 0 ) && ( yrightcnt % 2 != 0 ) ){
						m.atom[ i ].selected = true;
					}
					xtopcnt = xbottomcnt = yleftcnt = yrightcnt = 0;
				}
			}
			repaint();
			lassoPositions = new int[ maxlassoPositions ];
			newlassoPositions = null;
			lassoPositionCounter = 0;
			maxlassoPositions = 1000;
			return true;
		}
		else if ( BUILD ){
			if ( ! ( middleClick ) ){
				float min = 10;
				int closestatom = 0, closestatomx3 = 0;
				Atom closeAtom = null;
				if ( secondDrawAtom != null ){
					int jx = m.screenCoordinates[ 3 * 
					                              secondDrawAtom.moleculeAtomNumber ];
					int jy = m.screenCoordinates[ 3 * 
					                              secondDrawAtom.moleculeAtomNumber + 1 ];
					int ix3 = -3;
					for( int i = 0; i < m.numberOfAtoms; i++ ){
						ix3 += 3;
						float distance = (float)Math.sqrt( 
								( m.screenCoordinates[ ix3 ] - jx ) * 
								( m.screenCoordinates[ ix3 ] - jx ) + 
								( m.screenCoordinates[ ix3 + 1 ] - jy ) * 
								( m.screenCoordinates[ ix3 + 1 ] - jy ) );
						if ( distance < min ){
							if ( i != secondDrawAtom.moleculeAtomNumber ){
								min = distance;
								closestatom = i;
							}
						}
					}
					if ( min < 5 ){
						closeAtom = m.atom[ closestatom ];
						closestatomx3 = closestatom * 3;	
						closeAtom.coord[ 0 ] = m.actualCoordinates[ 
						                                           closestatomx3 ];
						closeAtom.coord[ 1 ] = m.actualCoordinates[ 
						                                           closestatomx3 + 1 ];
						closeAtom.coord[ 2 ] = m.actualCoordinates[ 
						                                           closestatomx3 + 2 ];
						m.deleteAtom( secondDrawAtom.moleculeAtomNumber );
						secondDrawAtom = closeAtom;
						m.saved = false;
						if ( secondDrawAtom.moleculeAtomNumber > 
						firstDrawAtom.moleculeAtomNumber )
							firstDrawAtom.addBond( 
									secondDrawAtom.moleculeAtomNumber );
						else
							secondDrawAtom.addBond( 
									firstDrawAtom.moleculeAtomNumber );
					}
				}
				else{
					firstDrawAtom.changeType( currentAtomName );
				}
				repaint();
				secondDrawAtom = null;
				return true;
			}
			else{  // means we just moved an atom
				return true;
			}
		}
		return false;
	}

	public void transform(){
		m.mat.transform( m.actualCoordinates, m.screenCoordinates, m.numberOfAtoms );
	}

	public void redraw(){
		transform();
		update( getGraphics() );
	}

	public String atomString( Atom a ){
		String wholeString = " ";
		if ( nameLabel )
			wholeString = a.name;
		if ( elementLabel )
			wholeString += "(" + a.elementType + ")";
		if ( numberLabel )
			wholeString += "[" + a.moleculeAtomNumber + "]";
		if ( typeLabel )
			wholeString += "{" + a.forceFieldType + "}";
		if ( chargeLabel )
			wholeString += "<" + a.charge + ">";
		return ( wholeString );
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void unrotate(){
		amat.unit();
	}

	public void unzoom(){
		displayMolecule( m );
		/*		zoomX = xfac;
		zoomY = -xfac;
		zoomZ = (float)((16.0 * xfac )/ (float)ww);
System.out.println( "zoomXYZ = " + zoomX + ", " + zoomY + ", " + zoomZ );  */
	}

	public void center(){
		translateX = ww / 2;
		translateY = ( wh - 100 ) / 2;
		translateZ = 0;
	}

	public void paint(Graphics g) {
		m.mat.unit();
		m.mat.mult(amat);
		m.mat.scale(zoomX, zoomY, zoomZ );
		m.mat.translate( translateX + transX, translateY + transY, 
				translateZ + transZ );
		transform();
		if ( ( zs == null ) || ( zs.length != m.numberOfAtoms ) ){
			zs = new int[ m.numberOfAtoms ];
			for ( int i = 0; i < m.numberOfAtoms; i++ )
				zs[ i ] = i;
		}
		for ( int i = m.numberOfAtoms-1; --i >=0; ){
			boolean flipped = false;
			for ( int j = 0; j <= i; j++ ){
				int a = zs[ j ];
				int b = zs[ j + 1 ];
				if (m.screenCoordinates[a*3+2] > m.screenCoordinates[b*3+2]){
					zs[ j + 1 ] = a;
					zs[ j ] = b;
					flipped = true;
				}
			}
			if ( !flipped )
				break;
		}
		if (backBuffer != null) {
			backGC.setColor( backgroundColor );
			backGC.fillRect(0,0,ww,wh);
			backGC.setColor( textColor );
			backGC.drawString( statusText, 30 , wh - 30 );
			render();		
			g.drawImage(backBuffer, 0, 0, this);
		} 
		else 
			render();
	}

	public void render(){
		for ( renderLoop = 0; renderLoop < m.numberOfAtoms; renderLoop++ ){
			int render = m.atom[ zs[ renderLoop ] ].render;
			if ( render == WIREFRAME )
				renderWireframe();
			else if ( render == BALLANDSTICK )
				renderBallandStick();
			else if ( render == POLYTUBES )
				renderPolytubes();  
			else if ( render == SPHERES )
				renderSpheres();
			else if ( render == SPACEFILL )
				renderSpacefill();  
		}
	}

	public void renderWireframe(){
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			atomNumberx3x = atomNumber * 3;
			atomNumberx3y = atomNumberx3x + 1;
			atomNumberx3z = atomNumberx3x + 2;
			if ( m.atom[ atomNumber ].render != WIREFRAME ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberZ = m.screenCoordinates[atomNumberx3z];
			atomColor = m.atom[ atomNumber ].color;
			if ( ! m.atom[ atomNumber ].selected )
				atomColor = Color.gray;
			if ( atomNumberZ < zMid ){
				for ( int o = atomNumberZ ; o < zMid; o = o + shadePartition )
					atomColor = darker( atomColor );
				backGC.setColor( atomColor );
			}
			else if ( atomNumberZ >= zMid ){
				for ( int o = zMid; o <= atomNumberZ; o = o + shadePartition ) 
					atomColor = brighter( atomColor );
				backGC.setColor( atomColor );
			}
			if ( LABELS )
				backGC.drawString( atomString( m.atom[ atomNumber ] ),
						m.screenCoordinates[ atomNumberx3x ], 
						m.screenCoordinates[ atomNumberx3y ] );
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtomNumberx3x = bondedAtomNumber * 3;
				bondedAtomNumberx3y = bondedAtomNumberx3x + 1;
				bondedAtomNumberx3z = bondedAtomNumberx3x + 2;
				bondedAtomNumberZ = m.screenCoordinates[ bondedAtomNumberx3z ];
				midBondX = (int)((m.screenCoordinates[ atomNumberx3x ] +
						m.screenCoordinates[ bondedAtomNumberx3x ] ) / 2 );
				midBondY = (int)((m.screenCoordinates[ atomNumberx3y ] + 
						m.screenCoordinates[ bondedAtomNumberx3y ] ) / 2 );
				backGC.setColor( atomColor );
				backGC.drawLine( m.screenCoordinates[ atomNumberx3x ],
						m.screenCoordinates[ atomNumberx3y ], midBondX, midBondY );
				if ( m.atom[ atomNumber ].doubleBondedTo( bondedAtomNumber ) )
					backGC.drawLine( m.screenCoordinates[ atomNumberx3x ] + 3,
							m.screenCoordinates[ atomNumberx3y ] + 3, 
							midBondX + 3, midBondY + 3 );
				else if ( m.atom[ atomNumber ].tripleBondedTo( bondedAtomNumber ) ){
					backGC.drawLine( m.screenCoordinates[ atomNumberx3x ] - 3,
							m.screenCoordinates[ atomNumberx3y ] - 3, 
							midBondX - 3, midBondY - 3 );
					backGC.drawLine( m.screenCoordinates[ atomNumberx3x ] + 3,
							m.screenCoordinates[ atomNumberx3y ] + 3, 
							midBondX + 3, midBondY + 3 );
				}
				bondedatomColor = m.atom[bondedAtomNumber].color;
				if ( ! m.atom[ bondedAtomNumber ].selected )
					bondedatomColor = Color.gray;
				if ( bondedAtomNumberZ < zMid ){
					for (int o = bondedAtomNumberZ;o<zMid;o = o + shadePartition) 
						bondedatomColor = darker( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				else if ( bondedAtomNumberZ >= zMid ){
					for ( int o = zMid; o <= bondedAtomNumberZ ; 
							o = o + shadePartition) 
						bondedatomColor = brighter( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				backGC.drawLine( midBondX, midBondY, 
						m.screenCoordinates[ bondedAtomNumberx3x ],
						m.screenCoordinates[ bondedAtomNumberx3y ] );
				if ( m.atom[ atomNumber ].doubleBondedTo( bondedAtomNumber ) )
					backGC.drawLine( midBondX + 3, midBondY +3, 
							m.screenCoordinates[ bondedAtomNumberx3x ] + 3,
							m.screenCoordinates[ bondedAtomNumberx3y ] + 3 );
				else if ( m.atom[ atomNumber ].tripleBondedTo( bondedAtomNumber ) ){
					backGC.drawLine( midBondX + 3, midBondY + 3, 
							m.screenCoordinates[ bondedAtomNumberx3x ] + 2,
							m.screenCoordinates[ bondedAtomNumberx3y ] + 3 );
					backGC.drawLine( midBondX - 3, midBondY - 3, 
							m.screenCoordinates[ bondedAtomNumberx3x ] - 2,
							m.screenCoordinates[ bondedAtomNumberx3y ] - 3 );
				}
			}
		}
	}
	public void renderBadBallandStick(){
		int Xcoord1[], Xcoord2[], Ycoord1[], Ycoord2[];
		int MidX1, MidX2, MidY1, MidY2;
		double slope, xdiff, b, dx, dy;
		Xcoord1 = new int[ 4 ];
		Xcoord2 = new int[ 4 ];
		Ycoord1 = new int[ 4 ];
		Ycoord2 = new int[ 4 ];
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			if ( m.atom[ atomNumber ].render != BALLANDSTICK ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberZ = m.screenCoordinates[atomNumber*3+2];
			atomColor = m.atom[ atomNumber ].color;
			if ( ! m.atom[ atomNumber ].selected )
				atomColor = Color.gray;
			if ( atomNumberZ < zMid ){
				for ( int o = atomNumberZ ; o < zMid; o = o + shadePartition )
					atomColor = darker( atomColor );
				backGC.setColor( atomColor );
			}
			else if ( atomNumberZ >= zMid ){
				for ( int o = zMid; o <= atomNumberZ; o = o + shadePartition ) 
					atomColor = brighter( atomColor );
				backGC.setColor( atomColor );
			}
			if ( LABELS )
				backGC.drawString( m.atom[ atomNumber ].name + 
						"("+m.atom[atomNumber].moleculeAtomNumber+")", 
						m.screenCoordinates[ atomNumber * 3 ], 
						m.screenCoordinates[ atomNumber*3+1 ] );
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtomNumberZ = m.screenCoordinates[ bondedAtomNumber * 3 + 2 ];
				midBondX = (int)((m.screenCoordinates[ atomNumber*3 ] +
						m.screenCoordinates[ bondedAtomNumber *3 ] ) / 2 );
				midBondY = (int)((m.screenCoordinates[ atomNumber*3 + 1 ] + 
						m.screenCoordinates[ bondedAtomNumber*3+1 ] ) / 2 );
				dy = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] - 
						m.screenCoordinates[ atomNumber*3 + 1 ];
				dx = m.screenCoordinates[ bondedAtomNumber * 3 ] - 	
						m.screenCoordinates[ atomNumber*3 ];
				slope = 0;
				if ( dy != 0 ){
					slope = -1.0*( dx/dy );
					xdiff = Math.sqrt( ( STICKRADIUS * 
							STICKRADIUS ) / ( 1 + slope*slope ) );
					b = midBondY - midBondX * slope;
					if ( slope > 0 ){
						MidX1 = midBondX + (int)xdiff;
						MidY1 = (int)( ( slope * ( midBondX + xdiff )) + b );
						MidX2 = midBondX - (int)xdiff;
						MidY2 = (int)( ( slope * ( midBondX - xdiff )) + b );
					}
					else {
						MidX1 = midBondX - (int)xdiff;
						MidY1 = (int)( ( slope * ( midBondX - xdiff )) + b );
						MidX2 = midBondX + (int)xdiff;
						MidY2 = (int)( ( slope * ( midBondX + xdiff )) + b );
					}
				}
				else{
					MidX1 = midBondX;
					MidX2 = midBondX;
					MidY1 = midBondY + STICKRADIUS;
					MidY2 = midBondY - STICKRADIUS;
				}
				if ( Math.abs( dy ) > Math.abs( dx ) ){
					Xcoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 ] -
							STICKRADIUS;
					Ycoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 + 1 ];

					Xcoord1[ 1 ] = MidX1;
					Ycoord1[ 1 ] = MidY1;

					Xcoord1[ 2 ] = MidX2;
					Ycoord1[ 2 ] = MidY2;
					if ( slope > 0 ){
						Xcoord1[ 1 ] = MidX2;
						Ycoord1[ 1 ] = MidY2;
						Xcoord1[ 2 ] = MidX1;
						Ycoord1[ 2 ] = MidY1;
					}

					Xcoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 ] +
							STICKRADIUS;
					Ycoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 + 1 ];


					Xcoord2[ 0 ] = MidX1;
					Ycoord2[ 0 ] = MidY1;

					if ( slope > 0 ){
						Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] + STICKRADIUS;	
						Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
						Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] - STICKRADIUS;	
						Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
					}
					else{
						Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] - STICKRADIUS;	
						Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
						Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] + STICKRADIUS;	
						Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
					}
					Xcoord2[ 3 ] = MidX2;
					Ycoord2[ 3 ] = MidY2;
				}
				else {
					Xcoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 ];
					Ycoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 + 1 ] -
							STICKRADIUS;

					Xcoord1[ 1 ] = MidX2;
					Ycoord1[ 1 ] = MidY2;

					Xcoord1[ 2 ] = MidX1;
					Ycoord1[ 2 ] = MidY1;

					Xcoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 ];
					Ycoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 + 1 ] +
							STICKRADIUS;


					Xcoord2[ 0 ] = MidX1;
					Ycoord2[ 0 ] = MidY1;

					Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber * 3 ];
					Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] +
							STICKRADIUS;

					Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber * 3 ];
					Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] -
							STICKRADIUS;

					Xcoord2[ 3 ] = MidX2;
					Ycoord2[ 3 ] = MidY2;
				}
				if ( ! m.atom[ atomNumber ].selected )
					atomColor = Color.gray;
				backGC.setColor( atomColor );
				backGC.fillOval( m.screenCoordinates[ atomNumber * 3 ] - 
						BALLRADIUS, m.screenCoordinates[ atomNumber * 3 + 1] -
						BALLRADIUS,
						2 * BALLRADIUS, 2 * BALLRADIUS );  
				backGC.fillPolygon( Xcoord1, Ycoord1, 4 );
				bondedatomColor = m.atom[bondedAtomNumber].color;
				if ( ! m.atom[ bondedAtomNumber ].selected )
					bondedatomColor = Color.gray;
				if ( bondedAtomNumberZ < zMid ){
					for (int o = bondedAtomNumberZ;o<zMid;o = o + shadePartition) 
						bondedatomColor = darker( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				else if ( bondedAtomNumberZ >= zMid ){
					for ( int o = zMid; o <= bondedAtomNumberZ ; 
							o = o + shadePartition) 
						bondedatomColor = brighter( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				if ( LABELS )
					backGC.drawString( m.atom[ bondedAtomNumber ].
							name + "("+ 
							m.atom[bondedAtomNumber].moleculeAtomNumber+")", 
							m.screenCoordinates[ bondedAtomNumber * 3 ], 
							m.screenCoordinates[ bondedAtomNumber*3+1 ] );
				backGC.fillOval( m.screenCoordinates[ bondedAtomNumber * 3 ] 
						- BALLRADIUS, 
						m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] -
						BALLRADIUS,
						2 * BALLRADIUS, 2 * BALLRADIUS );  
				backGC.fillPolygon( Xcoord2, Ycoord2, 4 );
			}
		}
	}
	public void renderBallandStick(){
		m.mat.spacefillTransform( m.actualCoordinates, m.screenCoordinates, 
				m.numberOfAtoms, 80 );
		zoomFactor = zoomZ / 8.0;
		if ( zoomFactor > 1 )
			zoomFactor = 1;
		mC3D.prepareBackgroundBuffer();
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			if ( m.atom[ atomNumber ].render != BALLANDSTICK ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberx3x = atomNumber * 3;
			atomNumberx3y = atomNumberx3x + 1;
			atomNumberx3z = atomNumberx3y + 1;
			Atom thisAtom = m.atom[ atomNumber ];
			int thisAtomRadius = (int)( thisAtom.radius * zoomFactor );
			mC3D.paintSphere( m.screenCoordinates[ atomNumberx3x ],
					m.screenCoordinates[ atomNumberx3y ], 
					m.screenCoordinates[ atomNumberx3z ], 
					thisAtomRadius, thisAtom.colorIndex );
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtom = m.atom[ bondedAtomNumber ];
				bondedAtomNumberx3x = bondedAtomNumber * 3;
				bondedAtomNumberx3y = bondedAtomNumberx3x + 1;
				bondedAtomNumberx3z = bondedAtomNumberx3y + 1;
				int bondedAtomRadius = (int)( bondedAtom.radius * zoomFactor );
				int cylinderRadius = (int)(0.2*( thisAtomRadius + bondedAtomRadius));
				mC3D.paintCylinder( m.screenCoordinates[ atomNumberx3x ], 
						m.screenCoordinates[ atomNumberx3y ], 
						m.screenCoordinates[ atomNumberx3z ],
						m.screenCoordinates[ bondedAtomNumberx3x ], 
						m.screenCoordinates[ bondedAtomNumberx3y ], 
						m.screenCoordinates[ bondedAtomNumberx3z ], 
						thisAtom.colorIndex, bondedAtom.colorIndex, cylinderRadius );
				mC3D.paintSphere( m.screenCoordinates[ bondedAtomNumberx3x ],
						m.screenCoordinates[ bondedAtomNumberx3y ], 
						m.screenCoordinates[ bondedAtomNumberx3z ], 
						(int)(bondedAtom.radius * zoomFactor), bondedAtom.colorIndex );
			}
		}
		mC3D.paint( backGC );
	}
	public void renderPolytubes(){
		m.mat.spacefillTransform( m.actualCoordinates, m.screenCoordinates, 
				m.numberOfAtoms, 400 );
		zoomFactor = zoomZ / 5.0;
		if ( zoomFactor > 1 )
			zoomFactor = 1;
		mC3D.prepareBackgroundBuffer();
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			if ( m.atom[ atomNumber ].render != POLYTUBES ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberx3x = atomNumber * 3;
			atomNumberx3y = atomNumberx3x + 1;
			atomNumberx3z = atomNumberx3y + 1;
			Atom thisAtom = m.atom[ atomNumber ];
			int thisAtomRadius = (int)( thisAtom.radius * zoomFactor );
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtom = m.atom[ bondedAtomNumber ];
				bondedAtomNumberx3x = bondedAtomNumber * 3;
				bondedAtomNumberx3y = bondedAtomNumberx3x + 1;
				bondedAtomNumberx3z = bondedAtomNumberx3y + 1;
				int bondedAtomRadius = (int)( bondedAtom.radius * zoomFactor );
				int cylinderRadius = (int)(0.2*( thisAtomRadius + bondedAtomRadius));
				mC3D.paintCylinder( m.screenCoordinates[ atomNumberx3x ], 
						m.screenCoordinates[ atomNumberx3y ], 
						m.screenCoordinates[ atomNumberx3z ],
						m.screenCoordinates[ bondedAtomNumberx3x ], 
						m.screenCoordinates[ bondedAtomNumberx3y ], 
						m.screenCoordinates[ bondedAtomNumberx3z ], 
						thisAtom.colorIndex, bondedAtom.colorIndex, cylinderRadius );
			}
		}
		mC3D.paint( backGC );
	}
	public void renderBadPolytubes(){
		int Xcoord1[], Xcoord2[], Ycoord1[], Ycoord2[];
		int MidX1, MidX2, MidY1, MidY2;
		double slope, xdiff, b, dx, dy;
		Xcoord1 = new int[ 4 ];
		Xcoord2 = new int[ 4 ];
		Ycoord1 = new int[ 4 ];
		Ycoord2 = new int[ 4 ];
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			if ( m.atom[ atomNumber ].render != POLYTUBES ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberZ = m.screenCoordinates[atomNumber*3+2];
			atomColor = m.atom[ atomNumber ].color;
			if ( ! m.atom[ atomNumber ].selected )
				atomColor = Color.gray;
			if ( atomNumberZ < zMid ){
				for ( int o = atomNumberZ ; o < zMid; o = o + shadePartition )
					atomColor = darker( atomColor );
				backGC.setColor( atomColor );
			}
			else if ( atomNumberZ >= zMid ){
				for ( int o = zMid; o <= atomNumberZ; o = o + shadePartition ) 
					atomColor = brighter( atomColor );
				backGC.setColor( atomColor );
			}
			if ( LABELS )
				backGC.drawString( m.atom[ atomNumber ].name + 
						"("+m.atom[atomNumber].moleculeAtomNumber+")", 
						m.screenCoordinates[ atomNumber * 3 ], 
						m.screenCoordinates[ atomNumber*3+1 ] );
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtomNumberZ = m.screenCoordinates[ bondedAtomNumber * 3 + 2 ];
				midBondX = (int)((m.screenCoordinates[ atomNumber*3 ] +
						m.screenCoordinates[ bondedAtomNumber *3 ] ) / 2 );
				midBondY = (int)((m.screenCoordinates[ atomNumber*3 + 1 ] + 
						m.screenCoordinates[ bondedAtomNumber*3+1 ] ) / 2 );
				dy = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] - 
						m.screenCoordinates[ atomNumber*3 + 1 ];
				dx = m.screenCoordinates[ bondedAtomNumber * 3 ] - 	
						m.screenCoordinates[ atomNumber*3 ];
				slope = 0;
				if ( dy != 0 ){
					slope = -1.0*( dx/dy );
					xdiff = Math.sqrt( ( POLYTUBERADIUS * 
							POLYTUBERADIUS ) / ( 1 + slope*slope ) );
					b = midBondY - midBondX * slope;
					if ( slope > 0 ){
						MidX1 = midBondX + (int)xdiff;
						MidY1 = (int)( ( slope * ( midBondX + xdiff )) + b );
						MidX2 = midBondX - (int)xdiff;
						MidY2 = (int)( ( slope * ( midBondX - xdiff )) + b );
					}
					else {
						MidX1 = midBondX - (int)xdiff;
						MidY1 = (int)( ( slope * ( midBondX - xdiff )) + b );
						MidX2 = midBondX + (int)xdiff;
						MidY2 = (int)( ( slope * ( midBondX + xdiff )) + b );
					}
				}
				else{
					MidX1 = midBondX;
					MidX2 = midBondX;
					MidY1 = midBondY + POLYTUBERADIUS;
					MidY2 = midBondY - POLYTUBERADIUS;
				}
				if ( Math.abs( dy ) > Math.abs( dx ) ){
					Xcoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 ] -
							POLYTUBERADIUS;
					Ycoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 + 1 ];

					Xcoord1[ 1 ] = MidX1;
					Ycoord1[ 1 ] = MidY1;

					Xcoord1[ 2 ] = MidX2;
					Ycoord1[ 2 ] = MidY2;
					if ( slope > 0 ){
						Xcoord1[ 1 ] = MidX2;
						Ycoord1[ 1 ] = MidY2;
						Xcoord1[ 2 ] = MidX1;
						Ycoord1[ 2 ] = MidY1;
					}

					Xcoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 ] +
							POLYTUBERADIUS;
					Ycoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 + 1 ];


					Xcoord2[ 0 ] = MidX1;
					Ycoord2[ 0 ] = MidY1;

					if ( slope > 0 ){
						Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] + POLYTUBERADIUS;	
						Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
						Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] - POLYTUBERADIUS;	
						Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
					}
					else{
						Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] - POLYTUBERADIUS;	
						Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
						Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 ] + POLYTUBERADIUS;	
						Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber 
						                                    * 3 + 1 ];
					}
					Xcoord2[ 3 ] = MidX2;
					Ycoord2[ 3 ] = MidY2;
				}
				else {
					Xcoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 ];
					Ycoord1[ 0 ] = m.screenCoordinates[ atomNumber * 3 + 1 ] -
							POLYTUBERADIUS;

					Xcoord1[ 1 ] = MidX2;
					Ycoord1[ 1 ] = MidY2;

					Xcoord1[ 2 ] = MidX1;
					Ycoord1[ 2 ] = MidY1;

					Xcoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 ];
					Ycoord1[ 3 ] = m.screenCoordinates[ atomNumber * 3 + 1 ] +
							POLYTUBERADIUS;


					Xcoord2[ 0 ] = MidX1;
					Ycoord2[ 0 ] = MidY1;

					Xcoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber * 3 ];
					Ycoord2[ 1 ] = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] +
							POLYTUBERADIUS;

					Xcoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber * 3 ];
					Ycoord2[ 2 ] = m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] -
							POLYTUBERADIUS;

					Xcoord2[ 3 ] = MidX2;
					Ycoord2[ 3 ] = MidY2;
				}
				if ( ! m.atom[ atomNumber ].selected )
					atomColor = Color.gray;
				backGC.setColor( atomColor );
				backGC.fillOval( m.screenCoordinates[ atomNumber * 3 ] - 
						POLYTUBERADIUS, m.screenCoordinates[ atomNumber * 3 + 1] -
						POLYTUBERADIUS,
						2 * POLYTUBERADIUS, 2 * POLYTUBERADIUS );  
				backGC.fillPolygon( Xcoord1, Ycoord1, 4 );
				bondedatomColor = m.atom[bondedAtomNumber].color;
				if ( ! m.atom[ bondedAtomNumber ].selected )
					bondedatomColor = Color.gray;
				if ( bondedAtomNumberZ < zMid ){
					for (int o = bondedAtomNumberZ;o<zMid;o = o + shadePartition) 
						bondedatomColor = darker( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				else if ( bondedAtomNumberZ >= zMid ){
					for ( int o = zMid; o <= bondedAtomNumberZ ; 
							o = o + shadePartition) 
						bondedatomColor = brighter( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				if ( LABELS )
					backGC.drawString( m.atom[ bondedAtomNumber ].
							name + "("+ 
							m.atom[bondedAtomNumber].moleculeAtomNumber+")", 
							m.screenCoordinates[ bondedAtomNumber * 3 ], 
							m.screenCoordinates[ bondedAtomNumber*3+1 ] );
				backGC.fillOval( m.screenCoordinates[ bondedAtomNumber * 3 ] 
						- POLYTUBERADIUS, 
						m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] -
						POLYTUBERADIUS,
						2 * POLYTUBERADIUS, 2 * POLYTUBERADIUS );  
				backGC.fillPolygon( Xcoord2, Ycoord2, 4 );
			}
		}
	}
	public void renderBadSpheres(){
		int atomradius, bondedatomradius;
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			if ( m.atom[ atomNumber ].render != SPHERES ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			atomNumberZ = m.screenCoordinates[atomNumber*3+2];
			atomColor = m.atom[ atomNumber ].color;
			if ( ! m.atom[ atomNumber ].selected )
				atomColor = Color.gray;
			if ( atomNumberZ < zMid ){
				for ( int o = atomNumberZ ; o < zMid; o = o + shadePartition )
					atomColor = darker( atomColor );
				backGC.setColor( atomColor );
			}
			else if ( atomNumberZ >= zMid ){
				for ( int o = zMid; o <= atomNumberZ; o = o + shadePartition ) 
					atomColor = brighter( atomColor );
				backGC.setColor( atomColor );
			}
			if ( LABELS )
				backGC.drawString( m.atom[ atomNumber ].name + 
						"("+m.atom[atomNumber].moleculeAtomNumber+")", 
						m.screenCoordinates[ atomNumber * 3 ], 
						m.screenCoordinates[ atomNumber*3+1 ] );
			atomradius = (int)m.atom[ atomNumber ].radius * SPHERERADIUS;
			for ( int bondNumber = 0; bondNumber < m.atom[ 
			                                              atomNumber ].numberOfBonds ; bondNumber++ ){
				bondedAtomNumber = m.atom[ atomNumber ].bond[ bondNumber ];
				bondedAtomNumberZ = m.screenCoordinates[ bondedAtomNumber * 3 + 2 ];
				backGC.setColor( atomColor );
				if ( ! m.atom[ atomNumber ].selected )
					backGC.setColor( Color.gray );
				backGC.fillOval( m.screenCoordinates[ atomNumber * 3 ] - 
						atomradius, m.screenCoordinates[ atomNumber * 3 + 1] -
						atomradius,
						2 * atomradius, 2 * atomradius );  
				bondedatomColor = m.atom[bondedAtomNumber].color;
				if ( ! m.atom[ bondedAtomNumber ].selected )
					bondedatomColor = Color.gray;
				if ( bondedAtomNumberZ < zMid ){
					for (int o = bondedAtomNumberZ;o<zMid;o = o + shadePartition) 
						bondedatomColor = darker( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				else if ( bondedAtomNumberZ >= zMid ){
					for ( int o = zMid; o <= bondedAtomNumberZ ; 
							o = o + shadePartition) 
						bondedatomColor = brighter( bondedatomColor );
					backGC.setColor( bondedatomColor );
				}
				if ( LABELS )
					backGC.drawString( m.atom[ bondedAtomNumber ].
							name + "("+ 
							m.atom[bondedAtomNumber].moleculeAtomNumber+")", 
							m.screenCoordinates[ bondedAtomNumber * 3 ], 
							m.screenCoordinates[ bondedAtomNumber*3+1 ] );
				bondedatomradius = (int)m.atom[ bondedAtomNumber ].radius *
						SPHERERADIUS;
				backGC.fillOval( m.screenCoordinates[ bondedAtomNumber * 3 ] 
						- bondedatomradius, 
						m.screenCoordinates[ bondedAtomNumber * 3 + 1 ] -
						bondedatomradius,
						2 * bondedatomradius, 2 * bondedatomradius );  
			}
		}
	}

	public void renderSpheres(){
		m.mat.spacefillTransform( m.actualCoordinates, m.screenCoordinates, 
				m.numberOfAtoms, 2000 );
		zoomFactor = zoomZ / 3.0;
		if ( zoomFactor > 1 )
			zoomFactor = 1;
		mC3D.prepareBackgroundBuffer();
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			atomNumberx3x = atomNumber * 3;
			atomNumberx3y = atomNumberx3x + 1;
			atomNumberx3z = atomNumberx3y + 1;
			Atom thisAtom = m.atom[ atomNumber ];
			if ( thisAtom.render != SPHERES ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			mC3D.paintSphere( m.screenCoordinates[ atomNumberx3x ],
					m.screenCoordinates[ atomNumberx3y ], 
					m.screenCoordinates[ atomNumberx3z ], 
					(int)(thisAtom.radius * zoomFactor), thisAtom.colorIndex );
		}
		mC3D.paint( backGC );
	}

	public void renderSpacefill(){
		m.mat.spacefillTransform( m.actualCoordinates, m.screenCoordinates, 
				m.numberOfAtoms, 80 );
		zoomFactor = zoomZ / 3.0;
		if ( zoomFactor > 1 )
			zoomFactor = 1;
		mC3D.prepareBackgroundBuffer();
		for ( ; renderLoop < m.numberOfAtoms; renderLoop++ ){
			atomNumber = zs[ renderLoop ];
			atomNumberx3x = atomNumber * 3;
			atomNumberx3y = atomNumberx3x + 1;
			atomNumberx3z = atomNumberx3y + 1;
			Atom thisAtom = m.atom[ atomNumber ];
			if ( thisAtom.render != SPACEFILL ){
				if ( renderLoop != 0 )
					renderLoop--;
				return;
			}
			mC3D.paintSphere( m.screenCoordinates[ atomNumberx3x ],
					m.screenCoordinates[ atomNumberx3y ], 
					m.screenCoordinates[ atomNumberx3z ], 
					(int)(thisAtom.radius * zoomFactor), thisAtom.colorIndex );
		}
		mC3D.paint( backGC );
	}

	/**
	 * Returns a brighter version of this color.
	 */
	public Color brighter(Color C) {
		return new Color(Math.min((int)(C.getRed() * INVDEPTHCUEFACTOR), 255),
				Math.min((int)(C.getGreen() * INVDEPTHCUEFACTOR), 255),
				Math.min((int)(C.getBlue() * INVDEPTHCUEFACTOR), 255));
	}

	/**
	 * Returns a darker version of this color.
	 */
	public Color darker(Color C) {
		return new Color(Math.max((int)(C.getRed() * DEPTHCUEFACTOR), 0),
				Math.max((int)(C.getGreen() * DEPTHCUEFACTOR), 0),
				Math.max((int)(C.getBlue() * DEPTHCUEFACTOR), 0));
	}

}
