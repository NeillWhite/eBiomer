package com.goosebumpanalytics.biomer;

import java.awt.*;

public class MinimizeOptionsDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	MoleculeCanvas          c;
	Molecule                m;
	Checkbox		hestenesStiefelCheckbox, fletcherReevesCheckbox;
	Checkbox		polakRibiereCheckbox, powellCheckbox;
	Checkbox		betaAnomerCheckbox;
	CheckboxGroup		conjugateGradientCBG;
	Choice			connectivityChoice;
	TextField		maxIterTextField, gradNormTextField, maxLineTextField; 
	TextField		stepSizeTextField;
	BiomerFrame		BiomerFrame;
	ErrorDialog		error;
	int			maxIter, maxLine;
	double			gradNorm, stepSize;

	MinimizeOptionsDialog( Molecule m, MoleculeCanvas c, BiomerFrame BiomerFrame,
			Frame CSF ){
		super( CSF, "eBiomer : Minimization Options", true );
		setResizable( false );
		this.m = m;
		this.BiomerFrame = BiomerFrame;
		this.c = c;

		layoutConstraint = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout( gridBagLayout );

		Button b;
		Label label = new Label( "Conjugate Gradient Algorithm", Label.CENTER );
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		add( label, 0, 0, 0 );
		conjugateGradientCBG = new CheckboxGroup();
		hestenesStiefelCheckbox = new Checkbox( "Hestenes-Stiefel", 
				conjugateGradientCBG, false );
		fletcherReevesCheckbox = new Checkbox( "Fletcher-Reeves", 
				conjugateGradientCBG, false );
		polakRibiereCheckbox = new Checkbox( "Polak-Ribiere", 
				conjugateGradientCBG, false );
		powellCheckbox = new Checkbox( "Powell", 
				conjugateGradientCBG, false );
		switch (BiomerFrame.cgOption ){
		case 0:
			hestenesStiefelCheckbox.setState( true );
			break;
		case 1:
			fletcherReevesCheckbox.setState( true );
			break;
		case 2:
			polakRibiereCheckbox.setState( true );
			break;
		case 3:
			powellCheckbox.setState( true );
			break;
		}
		add( hestenesStiefelCheckbox, 0, 1, 0 );
		add( fletcherReevesCheckbox, 0, 2, 0 );
		add( polakRibiereCheckbox, 0, 3, 0 );
		add( powellCheckbox, 0, 4, 0 );

		Panel subPanel = new Panel();
		label = new Label( "Maximum Iterations", Label.LEFT );                
		subPanel.add( label );
		maxIterTextField = new TextField( 
				( new Integer( BiomerFrame.maxIter ) ).toString(), 8 );
		maxIterTextField.setEditable( true );
		subPanel.add( maxIterTextField );
		add( subPanel, 1, 0, 1 );

		subPanel = new Panel();
		label = new Label( "Gradient Norm", Label.LEFT );                
		subPanel.add( label );
		gradNormTextField = new TextField( 
				( new Double( BiomerFrame.gradNorm ) ).toString(), 8 );
		gradNormTextField.setEditable( true );
		subPanel.add( gradNormTextField );
		add( subPanel, 1, 1, 1 );

		subPanel = new Panel();
		label = new Label( "Maximum Line Searches", Label.LEFT );                
		subPanel.add( label );
		maxLineTextField = new TextField( 
				( new Integer( BiomerFrame.maxLine ) ).toString(), 8 );
		maxLineTextField.setEditable( true );
		subPanel.add( maxLineTextField );
		add( subPanel, 1, 2, 1 );

		subPanel = new Panel();
		label = new Label( "Step Size", Label.LEFT );                
		subPanel.add( label );
		stepSizeTextField = new TextField( 
				( new Double( BiomerFrame.stepSize ) ).toString(), 8 );
		stepSizeTextField.setEditable( true );
		subPanel.add( stepSizeTextField );
		add( subPanel, 1, 3, 1 );

		subPanel = new Panel();
		b = new Button( "Help" );
		subPanel.add( b );
		b = new Button( "Cancel" );
		subPanel.add( b );
		b = new Button( "Done" );
		subPanel.add( b );

		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( subPanel, 1, 4, 1 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		reshape( 300, 300, 480, 230 );
	}

	public void add( Component c, int x, int y, int z ){
		layoutConstraint.gridx = x;
		layoutConstraint.gridy = y;
		if ( z == -1 )
			layoutConstraint.anchor = GridBagConstraints.WEST;
		else if ( z == 0 )
			layoutConstraint.anchor = GridBagConstraints.CENTER;
		else
			layoutConstraint.anchor = GridBagConstraints.EAST;
		gridBagLayout.setConstraints( c, layoutConstraint );
		add( c );
		return;
	}

	public boolean handleEvent( Event event ){
		if ( event.id == Event.WINDOW_DESTROY ){
			dispose();
			return true;
		}
		else 
			return super.handleEvent( event );
	}

	public boolean action( Event event, Object what ){
		if ( what == "Cancel" ){
			dispose();
			return true;
		}
		else if ( what == "Help" ){
			error = new ErrorDialog( BiomerFrame, "The Help option is not available in this version.", "Please check FAQs until this feature is implemented." );
			error.show();
			return true;
		}
		else if ( what == "Done" ){
			if ( getOptions() ){
				dispose();
				BiomerFrame.cgOption = getCGAlgorithm();
				BiomerFrame.maxIter = maxIter;
				BiomerFrame.gradNorm = gradNorm;
				BiomerFrame.maxLine = maxLine;
				BiomerFrame.stepSize = stepSize;
			}
			return true;
		}
		return true;
	}

	public int getCGAlgorithm(){
		if ( hestenesStiefelCheckbox.getState() == true )
			return 0;
		else if ( fletcherReevesCheckbox.getState() == true )
			return 1;
		else if ( polakRibiereCheckbox.getState() == true )
			return 2;
		else if ( powellCheckbox.getState() == true )
			return 3;
		return 2;
	}

	public boolean getOptions(){
		try{
			maxIter = new Integer( maxIterTextField.getText() ).intValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The maximum number of iterations must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			gradNorm = new Double( gradNormTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The value for the norm of the gradient must be a number.", "Suggestion: Make it a number." );
			error.show();
			return( false );
		}
		try{
			maxLine = new Integer( maxLineTextField.getText() ).intValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The maximum number of line searches must be a number.", "Suggestion: Try an integral number of line searches." );
			error.show();
			return( false );
		}
		try{
			stepSize = new Double( stepSizeTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The initial step size must be a number.", "Suggestion: A number." );
			error.show();
			return( false );
		}
		return( true );
	}


}

