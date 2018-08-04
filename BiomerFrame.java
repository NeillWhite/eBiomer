package com.goosebumpanalytics.biomer;

import java.awt.*;
import java.util.*;
import java.io.*;

public final class BiomerFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int windowWidth = 800;
	int windowHeight = 620;
	int horizontalScrollMax = 360, horizontalScrollMin = 1, verticalScrollMax = 360; 
	int verticalScrollMin = 1; 
	int newXValue = ( horizontalScrollMax + horizontalScrollMin )/2; 
	int newYValue = (verticalScrollMax + verticalScrollMin)/2;
	int numberOfMolecules = 0, maxIter = 100, maxLine = 25;
	int ff = 0, minimizer = 0, cgOption = 0, screenRefresh = 1;
	double heatTime = 1, equilibriumTime = 1, coolTime = 4;
	double heatTemperature = 0, equilibriumTemperature = 300, coolTemperature = 0;
	double MDStepSize = 1, ensemble = 0;
	double gradNorm = 0.01, stepSize = 1.0e-3;
	MoleculeCanvas c;
	Molecule m, moleculeNumber[], newMolecule[];
	String savedFilePathname = null, pathname = null, filename = null;
	Scrollbar horizontalScroll, verticalScroll;
	CheckboxMenuItem checkWireframe, checkBallandStick, checkPolytubes, checkSpheres;
	CheckboxMenuItem checkSpacefill, checkDepthCue, checkShiny, checkHydrogens;
	CheckboxMenuItem selectAtom, selectResidue, selectStrand, selectMolecule;
	CheckboxMenuItem checkAMBER, checkMM2, checkCG, checkSD;
	CheckboxMenuItem checkBackbone;
	PeriodicTableDialog PTD;
	Properties properties = new Properties();
	PrintStream printStream;
	SubImageButton buildButton;
	MolecularMechanics mm = null;
	MinimizeOptionsDialog mo;
	MolecularDynamicsDialog mdd;
	MolecularDynamics md;
	ForceField forceField = null;
	ErrorDialog error;
	boolean firstRendering = true;

	public BiomerFrame(){
		super("eBiomer");
		c = new MoleculeCanvas(this, windowWidth-40, windowHeight-60);

		MenuBar bar = new MenuBar();
		Menu fileMenu = new Menu("File");
		fileMenu.add("New");
		fileMenu.add("Open...");
		fileMenu.add("Save");
		fileMenu.add("Save As...");
		fileMenu.add("Print");
//		fileMenu.add("Close");
		Menu exportMenu = new Menu("Export");
		exportMenu.add("Ppm image");
		exportMenu.add("Gif image");
		exportMenu.add("Jpeg image");
		fileMenu.add( exportMenu );
		fileMenu.addSeparator();
		fileMenu.add("Exit");
		bar.add(fileMenu);
		Menu editMenu = new Menu("Edit");
		Menu selectMenu = new Menu("Select by");
		selectAtom = new CheckboxMenuItem( "Atom" );
		selectResidue = new CheckboxMenuItem( "Residue" );
		selectStrand = new CheckboxMenuItem( "Strand" );
		selectMolecule = new CheckboxMenuItem( "Molecule" );
		selectAtom.setState( true );
		selectMenu.add( selectAtom );
		selectMenu.add( selectResidue );
		selectMenu.add( selectStrand );
		selectMenu.add( selectMolecule );
		editMenu.add( selectMenu );
		editMenu.add("Select All");
		editMenu.add("Invert Selection");
		editMenu.add("Cut");
		editMenu.add("Copy");
		editMenu.add("Delete");
		editMenu.add("Hide");
		editMenu.add("Paste");
		editMenu.add("Center Molecule");
		editMenu.addSeparator();
		editMenu.add("Preferences...");
/*		editMenu.getItem(1).disable();
		editMenu.getItem(2).disable(); */
		editMenu.getItem(3).setEnabled(false);
		editMenu.getItem(3).setEnabled(false);  // cut
		editMenu.getItem(4).setEnabled(false);  // copy
/*		editMenu.getItem(5).disable(); */ // delete
		editMenu.getItem(6).setEnabled(false);  // hide
		editMenu.getItem(7).setEnabled(false);  // paste
		bar.add(editMenu);
		Menu toolsMenu = new Menu("Tools");
		toolsMenu.add( "Calculate Net Mass" );
		toolsMenu.add( "Calculate Net Charge" );
		toolsMenu.add( "Calculate Energy" );
		toolsMenu.add( "Invert Chirality" );
		bar.add(toolsMenu);
		Menu buildMenu = new Menu("Build/Add");
		buildMenu.add("Show Periodic Table" );
		buildMenu.addSeparator();
		buildMenu.add( "Add Hydrogens" );
		buildMenu.addSeparator();
		Menu functionalMenu = new Menu("Attach Functional Group");
		functionalMenu.add( "Acid Anhydride" );
		functionalMenu.add( "Aldehyde" );
		functionalMenu.add( "Amide" );
		functionalMenu.add( "Amine" );
		functionalMenu.add( "Carboxylate" );
		functionalMenu.add( "Carboxylic Acid" );
		functionalMenu.add( "Hydroxy" );
		functionalMenu.add( "Methoxy" );
		functionalMenu.add( "Methyl" );
		functionalMenu.add( "Nitrile" );
		functionalMenu.add( "Nitroso" );
		functionalMenu.add( "Nitro" );
		functionalMenu.add( "Thiol" );
		buildMenu.add( functionalMenu );
		Menu hydroMenu = new Menu("Add Hydrocarbon");
		hydroMenu.add( "Methane" );
		hydroMenu.add( "Ethane" );
		hydroMenu.add( "Ethylene" );
		hydroMenu.add( "Propane" );
		hydroMenu.add( "Propylene" );
		hydroMenu.add( "cis-Butane" );
		hydroMenu.add( "trans-Butane" );
		hydroMenu.add( "Butadiene" );
		hydroMenu.add( "Hexane" );
		hydroMenu.add( "Heptane" );
		hydroMenu.add( "Heptane" );
		hydroMenu.add( "Octane" );
		hydroMenu.add( "Nonane" );
		hydroMenu.add( "Decane" );
		hydroMenu.add( "Undecane" );
		hydroMenu.add( "Dodecane" );
		hydroMenu.add( "Tridecane" );
		hydroMenu.add( "Tetradecane" );
		hydroMenu.add( "Pentadecane" );
		hydroMenu.add( "Hexadecane" );
		hydroMenu.add( "Heptadecane" );
		hydroMenu.add( "Octadecane" );
		hydroMenu.add( "Nonadecane" );
		hydroMenu.add( "Eicosane" );
		hydroMenu.add( "Triacontane" );
		hydroMenu.add( "Tetracontane" );
		buildMenu.add( hydroMenu );
		Menu ringMenu = new Menu("Add Ring");
		ringMenu.add( "Acenaphthene" );
		ringMenu.add( "Benzene" );
		ringMenu.add( "Benzofuran" );
		ringMenu.add( "Cyclopropane" );
		ringMenu.add( "Cyclobutane" );
		ringMenu.add( "Cyclobutene" );
		ringMenu.add( "Cyclobutadiene" );
		ringMenu.add( "Cyclopentane" );
		ringMenu.add( "Cyclopentene" );
		ringMenu.add( "Cyclopentadiene" );
		ringMenu.add( "Cyclohexane" );
		ringMenu.add( "Cyclohexene" );
		ringMenu.add( "Cycloheptane" );
		ringMenu.add( "Cycloheptene" );
		ringMenu.add( "Cyclooctane" );
		ringMenu.add( "Imidazole" );
		ringMenu.add( "Norbornane" );
		ringMenu.add( "2-Norbornene" );
		ringMenu.add( "Oxazole" );
		ringMenu.add( "Porphine" );
		ringMenu.add( "Xanthene" );
		buildMenu.add( ringMenu );
		Menu miscMenu = new Menu("Add Miscellaneous");
		miscMenu.add( "Ammonia" );
		miscMenu.add( "Formaldehyde" );
		miscMenu.add( "Formamide" );
		miscMenu.add( "Glycerol" );
		miscMenu.add( "Glycol" );
		miscMenu.add( "Hydrazone" );
		miscMenu.add( "Imine" );
		miscMenu.add( "Urea" );
		miscMenu.add( "Water" );
		buildMenu.add( miscMenu );
		buildMenu.addSeparator();
		buildMenu.add("Polynucleotide");
		buildMenu.addSeparator();
		buildMenu.add("Polypeptide");
		buildMenu.addSeparator();
		buildMenu.add("Polysaccharide");
		bar.add(buildMenu);
		Menu molecularMechanicsMenu = new Menu("Molecular Mechanics");
		Menu forceFieldMenu = new Menu("Force Field");
		checkAMBER = new CheckboxMenuItem( "AMBER" );
		checkAMBER.setState( true );
		checkMM2 = new CheckboxMenuItem( "MM2" );
		checkMM2.setEnabled(false);
		forceFieldMenu.add( checkAMBER );
		forceFieldMenu.add( checkMM2 );
		Menu minimizerMenu = new Menu("Minimizer");
		checkSD = new CheckboxMenuItem( "Steepest Descent" );
		checkSD.setState( true );
		checkCG = new CheckboxMenuItem( "Conjugate Gradient" );
		minimizerMenu.add( checkSD );
		minimizerMenu.addSeparator();
		minimizerMenu.add( checkCG );
		molecularMechanicsMenu.add( forceFieldMenu );
		molecularMechanicsMenu.add( minimizerMenu );
		molecularMechanicsMenu.addSeparator();
		molecularMechanicsMenu.add( "Minimization Options" );
		molecularMechanicsMenu.addSeparator();
		molecularMechanicsMenu.add( "Minimize" );
		bar.add( molecularMechanicsMenu );
		Menu molecularDynamicsMenu = new Menu("Molecular Dynamics");
		molecularDynamicsMenu.add( "Options" );
		molecularDynamicsMenu.addSeparator();
		molecularDynamicsMenu.add( "Start Trajectory" );
		bar.add( molecularDynamicsMenu );
		Menu displayMenu = new Menu("Display");
		Menu renderMenu = new Menu("Render");
		renderMenu.add("Wireframe");
		renderMenu.add("Ball-and-Stick");
		renderMenu.add("Polytubes");
		renderMenu.add("Spheres");
		renderMenu.add("Spacefill");
		displayMenu.add( renderMenu );
		displayMenu.addSeparator();
		checkDepthCue = new CheckboxMenuItem("Depth Cueing" );
		checkDepthCue.setState( true );
		displayMenu.add( checkDepthCue );
		checkShiny = new CheckboxMenuItem("Shiny" );
		displayMenu.add( checkShiny );
//		checkHydrogens = new CheckboxMenuItem("Show Hydrogens" );
//		checkHydrogens.setState( true );
//		checkBackbone = new CheckboxMenuItem("Backbone only" );
//		displayMenu.add( checkHydrogens );
//		displayMenu.add( checkBackbone );
		displayMenu.addSeparator();
		displayMenu.add( "Fit To Screen" );
		//displayMenu.getItem( 7 ).disable();
		bar.add( displayMenu );
		Menu helpMenu = new Menu("Help");
		helpMenu.add( "About" );
		bar.add(helpMenu);
		setMenuBar(bar);

		horizontalScroll = new Scrollbar( Scrollbar.HORIZONTAL, newXValue, 80, 
			horizontalScrollMin, horizontalScrollMax );
		verticalScroll = new Scrollbar( Scrollbar.VERTICAL, newYValue, 80, 
			verticalScrollMin, verticalScrollMax );
		GridBagLayout layout = new GridBagLayout();
		setLayout( layout );
		GridBagConstraints layoutConstraint = new GridBagConstraints();
		layoutConstraint.gridx = 0;
		layoutConstraint.gridy = 0;
		layoutConstraint.weighty = 0;
		layoutConstraint.anchor = GridBagConstraints.WEST;  
		SubImageButton newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/rotate.gif"), "images/rotate.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 1;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/drag.gif"), "images/drag.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 2;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/zoom.gif"), "images/zoom.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 3;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/point.gif"), "images/point.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 4;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/lasso.gif"), "images/lasso.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 5;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/select.gif"), "images/select.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 6;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/labels.gif"), "images/labels.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 7;
		buildButton = new SubImageButton( c, getClass().getResourceAsStream("/images/build.gif"), "images/build.gif" );
		layout.setConstraints( buildButton, layoutConstraint );
		add( buildButton );
		layoutConstraint.gridy = 8;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/coord.gif"), "images/coord.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 9;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/distance.gif"), "images/distance.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 10;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/angle.gif"), "images/angle.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.gridy = 11;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/torsion.gif"), "images/torsion.gif" );
		layoutConstraint.anchor = GridBagConstraints.NORTH;  
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );
		layoutConstraint.anchor = GridBagConstraints.NORTH;  
		layoutConstraint.gridy = 12;
		newButton = new SubImageButton( c, getClass().getResourceAsStream("/images/stop.gif"), "images/stop.gif" );
		layout.setConstraints( newButton, layoutConstraint );
		add( newButton );    
		layoutConstraint.gridx = 1;
		layoutConstraint.gridy = 0;
		layoutConstraint.fill = GridBagConstraints.BOTH;
		layoutConstraint.gridheight = GridBagConstraints.RELATIVE;
		layoutConstraint.gridwidth = GridBagConstraints.RELATIVE;
		layoutConstraint.weightx = 1.0;
		layoutConstraint.weighty = 1.0;  
		layout.setConstraints( c, layoutConstraint );
		add( c );
		layoutConstraint.weightx = 0;
		layoutConstraint.weighty = 0;
		layoutConstraint.gridy = 13;
		layoutConstraint.fill = GridBagConstraints.BOTH;
		layoutConstraint.gridheight = GridBagConstraints.REMAINDER; 
		layout.setConstraints( horizontalScroll, layoutConstraint );
		add( horizontalScroll );    
		layoutConstraint.gridx = 2;
		layoutConstraint.gridy = 0;
		layoutConstraint.gridwidth = GridBagConstraints.REMAINDER; 
		layout.setConstraints( verticalScroll, layoutConstraint );
		add( verticalScroll );    

		reshape(100,100,windowWidth,windowHeight);
		show();
	}

	private void openMolecule(){
		try{
			FileDialog FD = 
				new FileDialog( this, "Open File", FileDialog.LOAD );
			class PDBFileFilter implements FilenameFilter{
				public boolean accept( File dir, String name ){
					if ( name.endsWith( ".pdb" ) )
						return true;
					return false;
				}
			}
			FD.setFilenameFilter( new PDBFileFilter() );
			FD.show();
			filename = FD.getFile();
			String dirname = FD.getDirectory();
			pathname = dirname + filename;
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Suggestion: check app permissions." );
			error.show();
		}
		if ( pathname != null )
		try { 
			savedFilePathname = pathname;
			FileInputStream is = new FileInputStream( pathname );
			m = new Molecule();
			if ( ( filename.toLowerCase().endsWith( ".pdb" ) )
				|| ( filename.toLowerCase().endsWith( ".ent" ) ) )
				new PDBChemModel( m, is );
			else if ( filename.toLowerCase().endsWith( ".xyz" ) )
				new XYZChemModel( m, is );
			else{
				error = new ErrorDialog( this, "Only PDB ( .pdb or .ent ) files are supported.", "Suggestion: Select a supported file from the list in the dialog." );
				error.show();
			}
			m.name = filename;
			c.displayMolecule( m );
			setTitle("eBiomer : " + m.name );
		}
		catch ( FileNotFoundException e ){
			error = new ErrorDialog( this, "File Not Found.", "Suggestion: Select a file from the list in the dialog." );
				error.show();
		}
		catch ( IOException e ){
			error = new ErrorDialog( this, "An error occurred while reading the file.  The file should be of PDB format.", "If you've clicked this button, it must be that you ARE trying to read a PDB file and it is not being read correctly.  If this is the case, please report this bug." );
			error.show();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Suggestion: Check app permissions." );
			error.show();
		}
		catch ( Exception e ){
			error = new ErrorDialog( this, "An error occurred while reading the file.  The file should be of PDB format.", "If you've clicked this button, it must be that you ARE trying to read a PDB file and it is not being read correctly.  If this is the case, please report this bug." );
			error.show();
			e.printStackTrace();
		}
	}
	
	private void saveMolecule(){
		try{
			FileDialog FD = new FileDialog( this, "Save File", FileDialog.SAVE );
			class PDBFileFilter implements FilenameFilter{
				public boolean accept( File dir, String name ){
					if ( name.endsWith( ".pdb" ) )
						return true;
					return false;
				}
			} 
			FD.setFilenameFilter( new PDBFileFilter() ); 
			FD.show();
			String filename = FD.getFile();
			if ( filename != null ){
				if ( ! ( filename.endsWith( ".pdb" ) ) )
					filename += ".pdb";
			}
			String dirname = FD.getDirectory();
			String pathname = dirname + filename;
			if ( pathname != null ){
				savedFilePathname = pathname;
				FileOutputStream fileOutputStream = 
					new FileOutputStream( savedFilePathname );
				BufferedOutputStream bufferedOutputStream = 
					new BufferedOutputStream( fileOutputStream );
				printStream = new PrintStream( bufferedOutputStream );
				new SaveMolecule( c.m, printStream );
				printStream.close();
				setTitle("eBiomer : " + c.m.name );
				c.m.saved = true;
			}
		}
		catch ( IOException e ){
			error = new ErrorDialog( this, "An I/O error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Suggestion: Check app permissions." );
			error.show();
		}
		catch ( Exception e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
			e.printStackTrace();
		}  
	}

	private void writeJpeg(){
		try { 
			FileDialog FD = new FileDialog( this, "Save File", FileDialog.SAVE );
			class JPEGFileFilter implements FilenameFilter{
				public boolean accept( File dir, String name ){
					if ( name.endsWith( ".jpg" ) )
						return true;
					return false;
				}
			}	 
			FD.show();
			String filename = FD.getFile();
			if ( filename != null ){
				if ( ! ( filename.endsWith( ".jpg" ) ) )
					filename += ".jpg";
			}
			String dirname = FD.getDirectory();
			String pathname = dirname + filename;
			savedFilePathname = pathname;
			FileOutputStream fileOutputStream = new FileOutputStream( pathname );
			BufferedOutputStream bufferedOutputStream = 
				new BufferedOutputStream( fileOutputStream, 8000);
			Image image = c.backBuffer;
			JpegEncoder jpegEncoder = 
				new JpegEncoder( image, 80, bufferedOutputStream );
			jpegEncoder.Compress();
			fileOutputStream.flush();
			bufferedOutputStream.flush();
			fileOutputStream.close();
			bufferedOutputStream.close();
		}
		catch ( IOException e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Suggestion: Check app permissions." );
			error.show();
		}
		catch ( Exception e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
			e.printStackTrace();
		}  
	}

	private void writePpm(){
		try { 
			FileDialog FD = new FileDialog( this, "Save File", FileDialog.SAVE );
			class PPMFileFilter implements FilenameFilter{
				public boolean accept( File dir, String name ){
					if ( name.toLowerCase().endsWith( ".ppm" ) )
						return true;
					return false;
				}
			}	 
			FD.show();
			String filename = FD.getFile();
			if ( filename != null ){
				if ( ! ( filename.toLowerCase().endsWith( ".ppm" ) ) )
					filename += ".ppm";
			}
			String dirname = FD.getDirectory();
			String pathname = dirname + filename;
			savedFilePathname = pathname;
			FileOutputStream fileOutputStream = new FileOutputStream( pathname );
			BufferedOutputStream bufferedOutputStream = 
				new BufferedOutputStream( fileOutputStream, 8000);
			Image image = c.backBuffer;
			PpmEncoder ppmEncoder = 
				new PpmEncoder( image, bufferedOutputStream );
			ppmEncoder.encode();
			fileOutputStream.flush();
			bufferedOutputStream.flush();
			fileOutputStream.close();
			bufferedOutputStream.close();
		}
		catch ( IOException e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Check app permissions." );
			error.show();
		}
		catch ( Exception e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
			e.printStackTrace();
		}  
	}

	private void writeGif(){
		try { 
			FileDialog FD = new FileDialog( this, "Save File", FileDialog.SAVE );
			class GIFFileFilter implements FilenameFilter{
				public boolean accept( File dir, String name ){
					if ( name.endsWith( ".gif" ) )
						return true;
					return false;
				}
			}	 
			FD.show();
			String filename = FD.getFile();
			if ( filename != null ){
				if ( ! ( filename.endsWith( ".gif" ) ) )
					filename += ".gif";
			}
			String dirname = FD.getDirectory();
			String pathname = dirname + filename;
			savedFilePathname = pathname;
			FileOutputStream fileOutputStream = new FileOutputStream( pathname );
			BufferedOutputStream bufferedOutputStream = 
				new BufferedOutputStream( fileOutputStream, 8000 );
			Image image = c.backBuffer;
			GifEncoder GifEncoder = 
				new GifEncoder( image, bufferedOutputStream, true );
			GifEncoder.encode();
			fileOutputStream.flush();
			bufferedOutputStream.flush();
			fileOutputStream.close();
			bufferedOutputStream.close();
		}
		catch ( IOException e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to open the file dialog.", "Suggestion: Check app permissions." );
			error.show();
		}
		catch ( Exception e ){
			error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
			error.show();
			e.printStackTrace();
		}  
	}

	private void print(){
		try{
			error = new ErrorDialog( this, "Note: There are problems when printing from some platforms.  If you are unable to print to the printer, try printing to a file and then send that file to the printer.", "Another suggestion is to export a .gif or .jpg image, load the image into the browser and then print directly from the browser." );
			Toolkit toolkit = c.getToolkit();
			PrintJob printJob = toolkit.getPrintJob( this, "Print", properties );
			Graphics printGraphics = printJob.getGraphics();
			c.print( printGraphics );
			printGraphics.dispose();
			printJob.end();
		}
		catch ( SecurityException e ){
			error = new ErrorDialog( this, "A security exception occurred while trying to print.", "Suggestion: Check app permissions." );
			error.show();
		}
	}

	public boolean handleEvent(Event event) {
		switch( event.id ){
			case Event.WINDOW_DESTROY:
				return action(event,"Exit"); 
			case Event.SCROLL_ABSOLUTE:
			case Event.SCROLL_LINE_UP:
			case Event.SCROLL_LINE_DOWN:
			case Event.SCROLL_PAGE_UP:
			case Event.SCROLL_PAGE_DOWN:
				return action(event,"Scrollbar");
			default:
				return super.handleEvent(event); 
		}
	}       
 
	public boolean action(Event event,Object what) {
		if ( ( what == "true" ) || ( what == "false" ) ){
			action( event, "Depth Cueing" );
//			action( event, "Show Hydrogens" );
			action( event, "Shiny" );
//			action( event, "Backbone Only" );
			action( event, "Steepest Descent" );
			action( event, "Conjugate Gradient" );
			return true;
		}
		if (what == "Exit") {
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
			dispose();  
			return true; 
		}
		else if ( what == "New" ){
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
			savedFilePathname = null;
			newMolecule();
			setTitle("eBiomer" );
			return true;
		}
		else if (what == "Close") {
			dispose();
			return true; 
		}
		else if (what == "Open..." ){
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
				openMolecule();
			return true;
		}
		else if (what == "Save" ){
			if ( savedFilePathname == null ){
				return( action( event, "Save As..." ) );
			}
			try { 
				FileOutputStream fileOutputStream = 
					new FileOutputStream( savedFilePathname );
				BufferedOutputStream bufferedOutputStream = 
					new BufferedOutputStream( fileOutputStream );
				printStream = new PrintStream( bufferedOutputStream );
				new SaveMolecule( c.m, printStream );
				printStream.close();
				setTitle("eBiomer : " + c.m.name );
			}
			catch ( IOException e ){
				error = new ErrorDialog( this, "An I/O error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
				error.show();
			}
			catch ( Exception e ){
				error = new ErrorDialog( this, "An error occurred while attempting to write the file.", "Suggestion:  Try again.  ( Not much of a suggestion )." );
				error.show();
				e.printStackTrace();
			}
			return true;
		}
		else if (what == "Save As..." ){
				saveMolecule();
			return true;
		}
		else if (what == "Preferences..." ){
			PreferencesDialog PD = new PreferencesDialog( c, this );
			PD.show();

			return true;
		}
		else if ( what == "Jpeg image" ){
				writeJpeg();
			return true;
		}
		else if ( what == "Gif image" ){
				writeGif();
			return true;
		}
		else if ( what == "Ppm image" ){
				writePpm();
			return true;
		}
		else if ( what == "Print" ){
				print();
			return true;
		}
		else if (what == "Scrollbar") {
			Scrollbar sb = (Scrollbar)event.target;
			c.ROTATE = true;
			if ( sb.getOrientation() == Scrollbar.VERTICAL ){
				c.prevy = newYValue;
				c.mouseDrag( event, c.prevx, sb.getValue() );
			}
			else{
				c.prevx = newXValue;
				c.mouseDrag( event, sb.getValue(), c.prevy );
			}
			return true;
		}
		else if (what == "Atom" ){
			if ( selectAtom.getState() == true ){
				selectResidue.setState( false );
				selectStrand.setState( false );
				selectMolecule.setState( false );
			}
			return true;
		}
		else if (what == "Residue" ){
			if ( selectResidue.getState() == true ){
				selectAtom.setState( false );
				selectStrand.setState( false );
				selectMolecule.setState( false );
			}
			return true;
		}
		else if (what == "Strand" ){
			if ( selectStrand.getState() == true ){
				selectAtom.setState( false );
				selectResidue.setState( false );
				selectMolecule.setState( false );
			}
			return true;
		}
		else if (what == "Molecule" ){
			if ( selectMolecule.getState() == true ){
				selectAtom.setState( false );
				selectResidue.setState( false );
				selectStrand.setState( false );
			}
			return true;
		}
		else if (what == "Select All" ){
			if ( c.m != null )
				for ( int i = 0; i < c.m.numberOfAtoms; i++ )
					c.m.atom[ i ].selected = true;
			c.repaint();
			return true;
		}
		else if (what == "Invert Selection" ){
			if ( c.m != null )
				for ( int i = 0; i < c.m.numberOfAtoms; i++ )
					if ( c.m.atom[ i ].selected )
						c.m.atom[ i ].selected = false;
					else
						c.m.atom[ i ].selected = true;
			c.repaint();
			return true;
		}
		else if (what == "Delete" ){
			c.statusText = "Click on atom or bond to Delete";
			c.repaint();
			c.DELETE = true;
			return true;
		}
		else if (what == "Center Molecule" ){
			if ( ( c != null ) && ( c.m != null ) ){
				c.unrotate();
				c.unzoom();
				c.m.center();
				c.transform();
				c.displayMolecule( c.m );
			}
			c.repaint();
			return true;
		}
		else if (what == "Calculate Net Mass" ){
			double mass = c.m.mass();
			if ( mass == 0 )
				c.statusText = "No atoms selected";
			else
				c.statusText = "Net Mass = " + (float)mass;
			c.redraw();
			return true;
		}
		else if (what == "Calculate Net Charge" ){
			int atomCounter[] = new int[ 1 ];
			double charge = c.m.charge( atomCounter );
			if ( atomCounter[ 0 ] == 0 )
				c.statusText = "No atoms selected";
			else
				c.statusText = "Net Charge = " + (float)charge;
			c.redraw();
			return true;
		}
		else if (what == "Invert Chirality" ){
			c.m.invertChirality();
			c.displayMolecule( c.m );
			c.m.saved = false;
			return true;
		}
		else if (what == "Calculate Energy" ){
			if ( forceField == null ){
				if ( ff == 0 )
					forceField = new Amber( c.m );
			}
	                if ( c.m.typeSet == false ){
				c.statusText = "Establishing connectivity...";
                        	c.redraw();
                        	c.m.establishConnectivity( false );
                        	c.statusText = "Calculating atom types...";
                        	c.redraw();
                        	forceField.calculateTypes();
                        	c.statusText = "Initializing calculation...";
                        	c.redraw();
                        	c.m.typeSet = true;
                        	forceField.initializeCalculation();
                	}
			forceField.calculateGradient();	
			int numberOfAtomsx3 = c.m.numberOfAtoms * 3;
			double gradNorm = 0;
			for( int i = 0; i < numberOfAtomsx3; i++ ){
				gradNorm += c.m.gradient[ i ] * c.m.gradient[ i ];	
			}
			gradNorm = Math.sqrt( gradNorm );
			double energy = forceField.calculateEnergy( c.m.actualCoordinates );
			c.statusText = "Gradient = " + (float)gradNorm + ", Energy = " + 
				(float)energy;
			c.repaint();
			return true;
		}
		else if (what == "Show Periodic Table" ){
			if ( PTD != null )
				PTD.dispose();
			PTD = new PeriodicTableDialog( c.m, c, this );
			PTD.show();
			return true;
		}
		else if ( what == "Add Hydrogens" ){
			c.m.addHydrogens();
			c.repaint();
			c.m.saved = false;
			return true;
		}
		else if ( ( what == "Acid Anhydride" ) || ( what == "Aldehyde" )
			|| ( what == "Alkynyl" ) || ( what == "Allenyl" ) || ( what == "Amide" )
			|| ( what == "Carboxylate" ) || ( what == "Carboxylic Acid" ) 
			|| ( what == "Ester" ) || ( what == "Hydroxy" ) || ( what == "Methoxy" )
			|| ( what == "Methyl" ) || ( what == "Nitrile" ) || ( what == "Nitroso" )
			|| ( what == "Nitro" ) || ( what == "Thiol" ) || ( what == "Amine" ) ){
			c.statusText = "Click to place " + what.toString();
			c.PLACE = true;
			c.fragmentName = what.toString();
			c.repaint();
			return true;
		}
		else if ( ( what == "Methane" ) || ( what == "Ethane" ) || ( what == "Ethylene" )
			|| ( what == "Propane" ) || ( what == "Propylene" ) 
			|| ( what == "cis-Butane" ) || ( what == "trans-Butane" ) 
			|| ( what == "Butadiene" ) || ( what == "Hexane" ) || ( what == "Heptane" )
			|| ( what == "Octane" ) || ( what == "Nonane" ) || ( what == "Decane" )
			|| ( what == "Undecane" ) || ( what == "Dodecane" ) || ( what == "Tridecane" )
			|| ( what == "Tetradecane" ) || ( what == "Pentadecane" ) 
			|| ( what == "Hexadecane" ) || ( what == "Heptadecane" ) 
			|| ( what == "Octadecane" ) || ( what == "Nonadecane" ) 
			|| ( what == "Eicosane" ) || ( what == "Triacontane" ) 
			|| ( what == "Tetracontane" ) ){
			c.statusText = "Click to place " + what.toString();
			c.PLACE = true;
			c.fragmentName = what.toString();
			c.repaint();
			return true;
		}
		else if ( ( what == "Acenaphthene" ) || ( what == "Benzene" ) 
			|| ( what == "Benzofuran" ) || ( what == "Cyclopropane" ) 
			|| ( what == "Cyclobutane" ) || ( what == "Cyclobutene" ) 
			|| ( what == "Cyclobutadiene" ) || ( what == "Cyclopentane" )
			|| ( what == "Cyclopentene" ) || ( what == "Cyclopentadiene" )
			|| ( what == "Cyclohexane" ) || ( what == "Cyclohexene" ) 
			|| ( what == "Cycloheptane" ) || ( what == "Cycloheptene" ) 
			|| ( what == "Cyclooctane" ) || ( what == "Imidazole" ) 
			|| ( what == "Norbornane" ) || ( what == "2-Norbornene" ) 
			|| ( what == "Oxazole" ) || ( what == "Porphine" ) 
			|| ( what == "Xanthene" ) ){
			c.statusText = "Click to place " + what.toString();
			c.PLACE = true;
			c.fragmentName = what.toString();
			c.repaint();
			return true;
		}
		else if ( ( what == "Ammonia" ) || ( what == "Formaldehyde" )
			|| ( what == "Formamide" ) || ( what == "Glycerol" ) || ( what == "Glycol" )
			|| ( what == "Hydrazone" ) || ( what == "Imine" ) 
			|| ( what == "Urea" ) || ( what == "Water" ) ){
			c.statusText = "Click to place " + what.toString();
			c.PLACE = true;
			c.fragmentName = what.toString();
			c.repaint();
			return true;
		}
		else if (what == "Polypeptide" ){
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
			m = new Molecule();
			PolyPeptideDialog PPD = new PolyPeptideDialog( m, c, this );
			PPD.show();

			c.displayMolecule( m );
			c.m.saved = false;
			return true;
		}
		else if (what == "Polysaccharide" ){
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
			m = new Molecule();
			PolySaccharideDialog PSD = new PolySaccharideDialog( m, c, this );
			PSD.show();

			c.displayMolecule( m );
			c.m.saved = false;
			return true;
		}
		else if (what == "Polynucleotide" ){
                        int whichButton[] = new int[ 1 ];
                        if ( c.m.saved == false ){
                                ThreeButtonDialog TBD = new ThreeButtonDialog( this,
                                        "You will lose all unsaved work.  Do you wish to continue?",
                                        "Warning", "Yes", "No", "Cancel", whichButton );
                                TBD.show();
                                if ( ( whichButton[ 0 ] == 2 ) || ( whichButton[ 0 ] == 3 ) )
                                        return true;
                        }
			m = new Molecule();
			NucleicAcidDialog ND = new NucleicAcidDialog( m, c, this );
			ND.show();

			c.displayMolecule( m );
			c.m.saved = false;
			return true;
		}
		else if (what == "AMBER" ){
			checkMM2.setState( false );
			ff = 0;
			return true;
		}
		else if (what == "MM2" ){
			checkAMBER.setState( false );
			ff = 1;
			return true;
		}
		else if (what == "Steepest Descent" ){
			if ( checkSD.getState() == true ){
				checkCG.setState( false );
				minimizer = 0;
			}
			return true;
		}
		else if (what == "Conjugate Gradient" ){
			if ( checkCG.getState() == true ){
				checkSD.setState( false );
				minimizer = 1;
			}
			return true;
		}
		else if (what == "Minimization Options" ){
			mo = new MinimizeOptionsDialog( m, c, this, this );
			mo.show();
			return true;
		}
		else if (what == "Minimize" ){
			if ( mm == null )
				mm = new MolecularMechanics( c.m, c, this, ff, 
					minimizer, cgOption, maxIter, gradNorm, maxLine,
					stepSize );
			else
				mm.minimize( c.m, ff, minimizer, cgOption, maxIter,
					gradNorm, maxLine, stepSize );
			return true;
		}
		else if (what == "Options" ){
			mdd = new MolecularDynamicsDialog( c.m, c, this, this );
			mdd.show();
			return true;
		}
		else if (what == "Start Trajectory" ){
			md = new MolecularDynamics( c.m, c, this, ff, heatTime,
				equilibriumTime, coolTime, heatTemperature, 
				equilibriumTemperature, coolTemperature, MDStepSize, 
				screenRefresh );
			return true;
		}
		else if (what == "Wireframe" ){
			if ( ( c != null ) && ( c.m != null ) ){
				for ( int i = 0; i < c.m.numberOfAtoms; i++ ){
					if ( c.m.atom[ i ].selected )	
						c.m.atom[ i ].render = c.WIREFRAME;
				}
				c.repaint();
			}
			return true;
		}
		else if (what == "Ball-and-Stick" ){
			if ( ( c != null ) && ( c.m != null ) ){
				for ( int i = 0; i < c.m.numberOfAtoms; i++ ){
					if ( c.m.atom[ i ].selected ){
						c.m.atom[ i ].render = c.BALLANDSTICK;
					}
				}
				if ( firstRendering ){
                                        c.repaint();
                                        c.renderBallandStick();
                                        c.repaint();
                                        firstRendering = false;
                                }
                                c.repaint();
			}
			return true;
		}
		else if (what == "Polytubes" ){
			if ( ( c != null ) && ( c.m != null ) ){
				for ( int i = 0; i < c.m.numberOfAtoms; i++ ){
					if ( c.m.atom[ i ].selected )	
						c.m.atom[ i ].render = c.POLYTUBES;
				}
                                if ( firstRendering ){
                                        c.repaint();
                                        c.renderPolytubes();
                                        c.repaint();
                                        firstRendering = false;
                                }
                                c.repaint();
			}
			return true;
		}
		else if (what == "Spheres" ){
			if ( ( c != null ) && ( c.m != null ) ){
				for ( int i = 0; i < c.m.numberOfAtoms; i++ ){
					if ( c.m.atom[ i ].selected )	
						c.m.atom[ i ].render = c.SPHERES;
				}
                                if ( firstRendering ){
                                        c.repaint();
                                        c.renderSpheres();
                                        c.repaint();
                                        firstRendering = false;
                                }
                                c.repaint();
			}
			return true;
		}
		else if (what == "Spacefill" ){
			if ( ( c != null ) && ( c.m != null ) ){
				for ( int i = 0; i < c.m.numberOfAtoms; i++ ){
					if ( c.m.atom[ i ].selected )	
						c.m.atom[ i ].render = c.SPACEFILL;
				}
                                if ( firstRendering ){
                                        c.repaint();
                                        c.renderSpacefill();
                                        c.repaint();
                                        firstRendering = false;
                                }
                                c.repaint();
			}
			return true;
		}
		else if (what == "Depth Cueing" ){
			if ( ( c != null ) && ( c.m != null ) ){
				if ( ! checkDepthCue.getState() )
					c.DEPTHCUEFACTOR = 1.0f;
				else
					c.DEPTHCUEFACTOR = .94f;
				c.repaint();
			}
			return true;
		}
/*		else if (what == "Show Hydrogens" ){
			if ( ( c != null ) && ( c.m != null ) ){
				if ( ! checkHydrogens.getState() )
					c.m.hideHydrogens();
				else
					c.m.showHydrogens();
				c.repaint();
			}
			return true;
		}  */
		else if (what == "Shiny" ){
			if ( ( c != null ) && ( c.m != null ) ){
				if ( checkShiny.getState() ){
					c.SHINY = true;
					c.mC3D.setShiny( true );
				}
				else{
					c.SHINY = false;
					c.mC3D.setShiny( false );
				}
				c.repaint();
			}
			return true;
		}
		else if (what == "Fit To Screen" ){
			if ( ( c != null ) && ( c.m != null ) ){
				c.m.center();
				c.displayMolecule( c.m );
			}
			return true;
		}
		else if (what == "About" ){
			About about = new About( this );
			about.show();
			return true;
		}
		else
			return false;
	}

	public void newMolecule(){
/*		Molecule newMol[] = new Molecule[ numberOfMolecules + 1 ];
		if ( moleculeNumber != null ){
			for ( int i = 0; i < numberOfMolecules; i++ )
				newMol[ i ] = moleculeNumber[ i ];
		}
		Molecule moleculeNumber[] = new Molecule[ numberOfMolecules + 1 ];
		if ( moleculeNumber != null ){
			for ( int i = 0; i < numberOfMolecules; i++ )
				moleculeNumber[ i ] = newMol[ i ];
		}
		newMol = null;
		moleculeNumber[ numberOfMolecules ] = new Molecule();
		m = moleculeNumber[ numberOfMolecules++ ];  */
		m = new Molecule();
		c.displayMolecule( m );
	}

	public void moveScrollbar( int x, int y ){
		newXValue = horizontalScroll.getValue() + x;
		newYValue = verticalScroll.getValue() + y;
		if ( newXValue > horizontalScrollMax )
			horizontalScroll.setValue( newXValue - horizontalScrollMax );
		else if ( newXValue < horizontalScrollMin )
			horizontalScroll.setValue( horizontalScrollMax + newXValue );
		else
			horizontalScroll.setValue( newXValue );
		if ( newYValue > verticalScrollMax )
			verticalScroll.setValue( newYValue - verticalScrollMax );
		else if ( newYValue < verticalScrollMin )
			verticalScroll.setValue( verticalScrollMax + newYValue );
		else
			verticalScroll.setValue( newYValue );
	}

	public void paint( Graphics g ){
		c.resizeCanvas();
	}
 
}
