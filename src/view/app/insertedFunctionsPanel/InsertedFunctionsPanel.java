package view.app.insertedFunctionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Popup;

import controller.Calculator;
import controller.Observer;
import model.core.FunctionIF;
import persistance.ModuleManager;


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

