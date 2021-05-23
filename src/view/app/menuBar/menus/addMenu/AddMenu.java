package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Calculator;

public class AddMenu extends JMenu{

	Calculator controller;

	public AddMenu(Calculator controller) {

		//set the title
		super("Aggiungi");
		//set the controller
		this.controller = controller;

		//make the "add function" menu item
		JMenuItem addFunctionItem = new JMenuItem("Nuova funzione");
		//the addFunctionItem calls the addFunctionProcedure()
		addFunctionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addFunctionProcedure();
			}
		});

		//add the saved functions selector submenu
		JMenu savedFunctionsMenu = new SavedFunctionsMenu(controller);


		//add the items to the addMenu menu
		this.add(addFunctionItem);
		//add the saved functions menu 
		this.add(savedFunctionsMenu);

	}

	/**
	 * Prompts the user to input an expression, then passes it to the controller
	 */

	private void addFunctionProcedure() {
		String expression = JOptionPane.showInputDialog(this,"Inserisci una funzione:");
		if(expression==null) {
			return;
		}
		controller.addFunction(expression);
	}





}
