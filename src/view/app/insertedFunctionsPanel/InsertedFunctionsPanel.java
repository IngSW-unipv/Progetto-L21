package view.app.insertedFunctionsPanel;

import java.util.HashMap;
import javax.swing.JPanel;
import controller.Calculator;
import controller.CalculatorListener;
import controller.ErrorCodes;
import model.core.FunctionIF;
/**
 * The InsertedFunctionsPanel is the space on the screen
 * where all of the currently plotted functions are shown.
 * 
 * InsertedFunctionsPanel contains a FunctionFragment FOR EACH
 * function that is being displayed.
 * 
 * InsertedFunctionsPanel is an Observer of the Calculator
 * controller, so it creates/deletes FunctionFragments 
 * automatically whenever they're needed/not needed anymore.
 *
 * @author Team - L21
 * 
 */
@SuppressWarnings("serial")
public class InsertedFunctionsPanel extends JPanel implements CalculatorListener{


	/*
	 * Keeps track of fragments. One fragment for each plotted function.
	 */
	HashMap<String, FunctionFragment> functionFragmentsMap;

	/*
	 * This panel observes a Calculator controller.
	 */
	Calculator controller;

	public InsertedFunctionsPanel(Calculator controller) {
		this.controller = controller;
		//add this panel as an observer to the controller
		controller.addListener(this);
		//make a new fragment map
		functionFragmentsMap = new HashMap<String, FunctionFragment>();
	}


	@Override
	public void onFunctionAdded(FunctionIF function) {
		//add a new function fragment for a new function
		addFunctionFragment(function);
	}

	
	@Override
	public void onFunctionRemoved(FunctionIF function) {
		//remove no longer needed fragment of deleted function
		removeFunctionFragment(function);
	}

	
	@Override
	public void onError(ErrorCodes errorCode, String message) {
		//does nothing about it for now
	}


	/**
	 * adds a new FunctionFragment to display a new function's expression.
	 * @param expression
	 */
	private void addFunctionFragment(FunctionIF function) {
		FunctionFragment newFrag = new FunctionFragment(function, controller);
		this.addMouseListener(newFrag);
		functionFragmentsMap.put(function.getExpression().toString(), newFrag);
		this.add(newFrag);
		this.repaint();
		this.revalidate();
	}

	
	/**
	 * removes an old FunctionFragment of a deleted function.
	 * @param expression
	 */
	private void removeFunctionFragment(FunctionIF function) {
		this.remove(functionFragmentsMap.get(function.getExpression()));
		functionFragmentsMap.remove(function.getExpression());
		this.repaint();
		this.revalidate();
	}

}

