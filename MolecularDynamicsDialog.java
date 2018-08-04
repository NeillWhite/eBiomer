package com.goosebumpanalytics.biomer;


import java.awt.*;

public class MolecularDynamicsDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints      layoutConstraint;
	GridBagLayout           gridBagLayout;
	MoleculeCanvas          c;
	Molecule                m;
	Checkbox		NVECheckbox, NVTCheckbox;
	Checkbox		NPTCheckbox, NPHCheckbox;
	CheckboxGroup		ensembleCBG;
	TextField		heatTimeTextField, equilibriumTimeTextField, coolTimeTextField; 
	TextField		stepSizeTextField, screenRefreshTextField;
	TextField		heatTemperatureTextField, equilibriumTemperatureTextField;
	TextField		coolTemperatureTextField;
	int			screenRefresh;
	BiomerFrame		BiomerFrame;
	ErrorDialog		error;
	double			heatTime, coolTime, equilibriumTime, stepSize;
	double			heatTemperature, coolTemperature, equilibriumTemperature;

	MolecularDynamicsDialog( Molecule m, MoleculeCanvas c, BiomerFrame BiomerFrame,
			Frame CSF ){
		super( CSF, "eBiomer : Molecular Dynamics Options", true );
		setResizable( true );
		this.m = m;
		this.BiomerFrame = BiomerFrame;
		this.c = c;

		layoutConstraint = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout( gridBagLayout );

		Button b;
		Panel subPanel = new Panel();
		Label label = new Label( "Ensemble", Label.LEFT );
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		subPanel.add( label );
		ensembleCBG = new CheckboxGroup();
		NVECheckbox = new Checkbox( "NVE", 
				ensembleCBG, true );
		NVTCheckbox = new Checkbox( "NVT", 
				ensembleCBG, false );
		NVTCheckbox.setEnabled(false);
		NPTCheckbox = new Checkbox( "NPT", 
				ensembleCBG, false );
		NPTCheckbox.setEnabled(false);
		NPHCheckbox = new Checkbox( "NPH", 
				ensembleCBG, false );
		NPHCheckbox.setEnabled(false);
		subPanel.add( NVECheckbox );
		subPanel.add( NVTCheckbox );
		subPanel.add( NPTCheckbox );
		subPanel.add( NPHCheckbox );
		layoutConstraint.fill = GridBagConstraints.HORIZONTAL;
		add( subPanel, 0, 0, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		GridLayout gridLayout = new GridLayout( 3, 4 );
		Panel panel = new Panel();
		panel.setLayout( gridLayout );

		label = new Label( "Heat", Label.CENTER );                
		label.hide();
		panel.add( label );
		label = new Label( "Heat", Label.CENTER );                
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		panel.add( label );
		label = new Label( "Equilibrium", Label.CENTER );                
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		panel.add( label );
		label = new Label( "Cool", Label.CENTER );                
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		panel.add( label );
		label = new Label( "Time", Label.CENTER );                
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		panel.add( label );

		subPanel = new Panel();
		heatTimeTextField = new TextField( 
				( new Double( BiomerFrame.heatTime ) ).toString(), 6 );
		heatTimeTextField.setEditable( true );
		subPanel.add( heatTimeTextField );
		label = new Label( "ps", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		subPanel = new Panel();
		equilibriumTimeTextField = new TextField( 
				( new Double( BiomerFrame.equilibriumTime ) ).toString(), 6 );
		equilibriumTimeTextField.setEditable( true );
		subPanel.add( equilibriumTimeTextField );
		label = new Label( "ps", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		subPanel = new Panel();
		coolTimeTextField = new TextField( 
				( new Double( BiomerFrame.coolTime ) ).toString(), 6 );
		coolTimeTextField.setEditable( true );
		subPanel.add( coolTimeTextField );
		label = new Label( "ps", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		label = new Label( "Temperature", Label.CENTER );                
		label.setFont( new Font( "Helvetica", Font.PLAIN, 14 ) );
		panel.add( label );

		subPanel = new Panel();
		heatTemperatureTextField = new TextField( 
				( new Double( BiomerFrame.heatTemperature ) ).toString(), 6 );
		heatTemperatureTextField.setEditable( true );
		subPanel.add( heatTemperatureTextField );
		label = new Label( "K", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		subPanel = new Panel();
		equilibriumTemperatureTextField = new TextField( 
				( new Double( BiomerFrame.equilibriumTemperature ) ).toString(), 6 );
		equilibriumTemperatureTextField.setEditable( true );
		subPanel.add( equilibriumTemperatureTextField );
		label = new Label( "K", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		subPanel = new Panel();
		coolTemperatureTextField = new TextField( 
				( new Double( BiomerFrame.coolTemperature ) ).toString(), 6 );
		coolTemperatureTextField.setEditable( true );
		subPanel.add( coolTemperatureTextField );
		label = new Label( "K", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );
		add( panel, 0, 1, 1 );

		panel = new Panel();

		subPanel = new Panel();
		label = new Label( "Step Size", Label.RIGHT );                
		subPanel.add( label );
		stepSizeTextField = new TextField( 
				( new Double( BiomerFrame.MDStepSize ) ).toString(), 4 );
		stepSizeTextField.setEditable( true );
		subPanel.add( stepSizeTextField );
		label = new Label( "fs", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		subPanel = new Panel();
		label = new Label( "Screen Refresh", Label.RIGHT );                
		subPanel.add( label );
		screenRefreshTextField = new TextField( 
				( new Integer( BiomerFrame.screenRefresh ) ).toString(), 2 );
		screenRefreshTextField.setEditable( true );
		subPanel.add( screenRefreshTextField );
		label = new Label( "steps", Label.LEFT );                
		subPanel.add( label );
		panel.add( subPanel );

		layoutConstraint.fill = GridBagConstraints.HORIZONTAL;
		add( panel, 0, 2, 0 );

		subPanel = new Panel();
		b = new Button( "Help" );
		subPanel.add( b );
		b = new Button( "Cancel" );
		subPanel.add( b );
		b = new Button( "Done" );
		subPanel.add( b );

		layoutConstraint.fill = GridBagConstraints.BOTH;
		add( subPanel, 0, 3, 0 );
		layoutConstraint.fill = GridBagConstraints.NONE;

		reshape( 300, 300, 435, 270 );
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
				BiomerFrame.ensemble = getEnsemble();
				BiomerFrame.heatTime = heatTime;
				BiomerFrame.equilibriumTime = equilibriumTime;
				BiomerFrame.coolTime = coolTime;
				BiomerFrame.heatTemperature = heatTemperature;
				BiomerFrame.equilibriumTemperature = equilibriumTemperature;
				BiomerFrame.coolTemperature = coolTemperature;
				BiomerFrame.MDStepSize = stepSize;
				BiomerFrame.screenRefresh = screenRefresh;
			}
			return true;
		}
		return true;
	}

	public int getEnsemble(){
		if ( NVECheckbox.getState() == true )
			return 0;
		else if ( NVTCheckbox.getState() == true )
			return 1;
		else if ( NPTCheckbox.getState() == true )
			return 2;
		else if ( NPHCheckbox.getState() == true )
			return 3;
		return 0;
	}

	public boolean getOptions(){
		try{
			heatTime = new Double( heatTimeTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The heating time must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			equilibriumTime = new Double( equilibriumTimeTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The time for the equilibrium part of the run must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			coolTime = new Double( coolTimeTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The cooling time must be an integer.", "Suggestion: Try 0." );
			error.show();
			return( false );
		}
		try{
			heatTemperature = new Double( heatTemperatureTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The heating temperature must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			equilibriumTemperature = 
					new Double( equilibriumTemperatureTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The equilibrium temperature must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			coolTemperature = 
					new Double( coolTemperatureTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The cool temperature must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			screenRefresh = 
					new Integer( screenRefreshTextField.getText() ).intValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The screen refresh period must be an integer.", "Suggestion: Make it an integer." );
			error.show();
			return( false );
		}
		try{
			stepSize = new Double( stepSizeTextField.getText() ).doubleValue();
		}
		catch ( NumberFormatException e ){
			error = new ErrorDialog( BiomerFrame, "The step size must be an integer.", "Suggestion: Make the step size an integer." );
			error.show();
			return( false );
		}
		if ( heatTemperature > equilibriumTemperature ){
			error = new ErrorDialog( BiomerFrame, "The heating temperature is higher than the equilibrium temperature.  The heating temperature field should have the starting temperature of the simulation and should be less than or equal to the equilibrium temperature.", "Suggestion: Make the heating temperature 0K.  This will heat the system to the equilibrium temperature from 0K with 10 velocity rescalings." );
			error.show();
			return( false );
		}
		if ( coolTemperature > equilibriumTemperature ){
			error = new ErrorDialog( BiomerFrame, "The cooling temperature is higher than the equilibrium temperature.  The cooling temperature field should have the ending temperature of the simulation and should be less than or equal to the equilibrium temperature.", "Suggestion: Make the cooling temperature 0K.  This will cool the system from the equilibrium temperature to 0K with 10 velocity rescalings." );
			error.show();
			return( false );
		}
		return( true );
	}


}

