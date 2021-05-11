package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Calculator;
import model.Observer;
import model.functions.FunctionIF;

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
		String expression = ((FunctionIF)message.get(0)).getExpression();

		switch((String)message.get(1)) {
		case "ADDED":
			//add a new function fragment for a new function
			addFunctionFragment(expression);
			break;
		case "DELETED":
			//remove no longer needed fragment of deleted function
			removeFunctionFragment(expression);
			break;
		}
	}
	
	
	
	/**
	 * adds a new FunctionFragment to display a new function's expression.
	 * @param expression
	 */
	private void addFunctionFragment(String expression) {
		FunctionFragment newFrag = new FunctionFragment(expression);
		functionFragmentsMap.put(expression, newFrag);
		this.add(newFrag);
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * removes an old FunctionFragment of a deleted function.
	 * @param expression
	 */
	private void removeFunctionFragment(String expression) {
		this.remove(functionFragmentsMap.get(expression));
		functionFragmentsMap.remove(expression);
		this.repaint();
		this.revalidate();
	}
	
	
	
	
	
	/**
	 * Each plotted function gets to have a FunctionFragment that
	 * represents its expression on screen, and allows the user 
	 * to delete the plotted function at any time.
	 *
	 */
	public class FunctionFragment extends JPanel{
		
		//button that deletes its function 
		JButton removeButton;
		//the expression of this fragment's function
		String expression;
		//label used to display the expression
		JLabel label;
		
		
		public FunctionFragment(String expression) {
			//set expression
			this.expression = expression;
			//make a new label 
			label = new JLabel(expression);
			//make a new remove button
			removeButton = new JButton("/");
			//color of remove button text = RED
			removeButton.setForeground(Color.red);
			
			//add button and label
			this.add(removeButton);
			this.add(label);
			
			//makes sure that keylistening doesn't get stuck on this panel for some swing-related reason.
			removeButton.setFocusable(false);
			
			//set the remove button's action to = delete the function
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//tell the controller to remove the function
					controller.removeFunction(expression);
				}
			});
			
		}
		
	}












	

}

