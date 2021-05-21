package view.app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Calculator;
import controller.Observer;
import model.core.FunctionIF;


public class InsertedFunctionsPanel extends JPanel implements Observer{


	//Keeps track of fragments. One fragment for each plotted function.
	HashMap<String, FunctionFragment> functionFragmentsMap;

	//This panel observes a Calculator controller.
	Calculator controller;

	public InsertedFunctionsPanel(Calculator controller) {
		this.controller = controller;
		//add this panel as an observer to the controller
		controller.addObserver(this);
		//make a new fragment map
		functionFragmentsMap = new HashMap<String, FunctionFragment>();
	}



	/**
	 * The update method here adds/removes FunctionFragments
	 * based on inserted/removed functions.
	 */
	@Override
	public void update(ArrayList<Object> message) {

		//get the expression of the function added/removed
		FunctionIF function = ((FunctionIF)message.get(0));

		switch((String)message.get(1)) {
		case "ADDED":
			//add a new function fragment for a new function
			addFunctionFragment(function);
			break;
		case "DELETED":
			//remove no longer needed fragment of deleted function
			removeFunctionFragment(function);
			break;
		}
	}



	/**
	 * adds a new FunctionFragment to display a new function's expression.
	 * @param expression
	 */
	private void addFunctionFragment(FunctionIF function) {
		FunctionFragment newFrag = new FunctionFragment(function);
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





	/**
	 * Each plotted function gets to have a FunctionFragment that
	 * represents its expression on screen, and allows the user 
	 * to delete the plotted function at any time.
	 * 
	 * Eventually this may contain further options to 
	 * be applied on single functions, such as derivation.
	 *
	 */
	public class FunctionFragment extends JPanel{

		//button that deletes its function 
		JButton removeButton;
		//the expression of this fragment's function
		String expression;
		//label used to display the expression
		JLabel label;
		
		//button that allows you to differentiate its function
		JButton deriveButton;
		

		public FunctionFragment(FunctionIF function) {
			//make a new label 
			label = new JLabel(function.getExpression().toString());
			//set the color of the label to match the color of the function on the graph
			label.setForeground(function.getColor());
			//make a new remove button
			removeButton = new JButton("/");
			//color of remove button text = RED
			removeButton.setForeground(Color.red);
			//make a new derive button
			deriveButton = new JButton("derivata");
			
			
			//add buttons and label
			this.add(removeButton);
			this.add(deriveButton);
			this.add(label);

			//makes sure that keylistening doesn't get stuck on this panel for some swing-related reason.
			removeButton.setFocusable(false);
			deriveButton.setFocusable(false);

			//set the remove button's action to = delete the function
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//tell the controller to remove the function
					controller.removeFunction(function.getExpression().toString());
				}
			});
			
			//set the derive's button action
			deriveButton.addActionListener(new ActionListener() {

				//tell the controller to add this function's derivative
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.addFunction(function.getDerivative());
				}
				
			});
			

		}

	}














}

