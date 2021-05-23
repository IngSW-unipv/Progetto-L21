package view.app.menuBar;

import javax.swing.JMenuBar;
import controller.Calculator;
import view.app.menuBar.menus.ExportMenu;
import view.app.menuBar.menus.ViewMenu;
import view.app.menuBar.menus.addMenu.AddMenu;
import view.graph.GraphPanel;

/**
 * This custom menu bar contains the main options and menus to access
 * the app's functionalities and customize user-preferences.
 */

public class AppMenuBar extends JMenuBar{

	Calculator controller;
	GraphPanel graphPanel;

	public AppMenuBar(Calculator controller, GraphPanel graphPanel) {

		this.controller = controller;
		this.graphPanel = graphPanel;

		
		//create the menus
		AddMenu addMenu = new AddMenu(controller);
		ViewMenu viewMenu = new ViewMenu(graphPanel);
		ExportMenu exportMenu = new ExportMenu(graphPanel);

		//add the menus to the menu bar
		this.add(addMenu);
		this.add(viewMenu);
		this.add(exportMenu);

	}



	
	



}
