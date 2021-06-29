package view.app.insertedFunctionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Calculator;
import model.core.FunctionIF;
/**
 * Each plotted function gets to have a FunctionFragment that
 * represents its expression on screen, allows the user 
 * to delete the plotted function at any time, and to plot
 * its derivative.
 * 
 * Eventually this may contain further options to 
 * be applied on single functions.
 *
 * @author Team - L21
 * 
 */
@SuppressWarnings("serial")
public class FunctionFragment extends JPanel{

	/*
	 * controller
	 */
	Calculator controller;
	
	/*
	 * this fragment's function
	 */
	FunctionIF function;
	
	/*
	 * button that deletes its function 
	 */
	JButton removeButton;
	
	/*
	 * the expression of this fragment's function
	 */
	String expression;
	
	/*
	 * label used to display the expression
	 */
	JLabel label;
	
	/*
	 * button that allows you to differentiate its function
	 */
	JButton deriveButton;
		
	
	public FunctionFragment(FunctionIF function, Calculator controller) {
		
		//set this fragment's function
		this.function = function;
		
		//make a new label 
		label = new JLabel(function.getExpression().toString());
		
		//set the color of the label to match the color of the function on the graph
		label.setForeground(function.getColor());
		
		//make a new remove button
		removeButton = new JButton("/");
		
		//color of remove button text = RED
		removeButton.setForeground(Color.red);
		
		//make a new derive button
		deriveButton = new JButton("d/dx");

		
		//add buttons and label
		this.add(removeButton);
		this.add(deriveButton);
		this.add(label);

		
		//IMPORTANT: makes sure that keylistening doesn't get stuck on this panel for some swing-related reason.
		removeButton.setFocusable(false);
		deriveButton.setFocusable(false);

		
		//set the remove button's action to = delete the function
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//tell the controller to remove the function
				controller.removeFunction(function);
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


