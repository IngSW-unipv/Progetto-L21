package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Calculator;
import view.graph.GraphPanel;

public class AddMenu extends JMenu{

	Calculator controller;
	GraphPanel graphPanel;

	public AddMenu(Calculator controller, GraphPanel graphPanel) {

		//set the title
		super("Aggiungi");
		//set the controller
		this.controller = controller;
		//set the graph panel
		this.graphPanel = graphPanel;
		
		//make the "add function" menu item
		JMenuItem addFunctionItem = new JMenuItem("Nuova funzione");
		//the addFunctionItem calls the addFunctionProcedure()
		addFunctionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.addFunctionProcedure();
			}
		});

		//add the saved functions selector submenu
		JMenu savedFunctionsMenu = new SavedFunctionsMenu(controller);


		//add the items to the addMenu menu
		this.add(addFunctionItem);
		//add the saved functions menu 
		this.add(savedFunctionsMenu);

	}

	



}
