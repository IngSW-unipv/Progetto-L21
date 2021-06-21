package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Calculator;
import persistence.FileIO;
import persistence.ModuleManager;
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
		JMenu savedFunctionsMenu = new SavedFunctionsMenu(controller, "customFunctions", "Funzione Custom");


		//make the "history" menu item
		JMenuItem history = new JMenuItem("Cronologia");
		history.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, ModuleManager.getInstance().getModule("functionsHistoryModule").read());
			}
		});






		//add the items to the addMenu menu
		this.add(addFunctionItem);
		//add the saved functions menu 
		this.add(savedFunctionsMenu);
		this.add(history);

	}





}
