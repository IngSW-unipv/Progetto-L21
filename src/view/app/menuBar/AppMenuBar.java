package view.app.menuBar;

import javax.swing.JMenuBar;
import controller.Calculator;
import view.app.menuBar.menus.ExportMenu;
import view.app.menuBar.menus.HelpMenu;
import view.app.menuBar.menus.ViewMenu;
import view.app.menuBar.menus.addMenu.AddMenu;
import view.graph.GraphController;
import view.graph.GraphPanel;
/**
 * This custom menu bar contains the main options and menus to access
 * the app's functionalities and customize user-preferences.
 * 
 * @author Team - L21
 * 
 */
@SuppressWarnings("serial")
public class AppMenuBar extends JMenuBar{

	Calculator controller;
	GraphPanel graphPanel;
	
	public AppMenuBar(Calculator controller, GraphPanel graphPanel) {

		this.controller = controller;
		this.graphPanel = graphPanel;
		
		GraphController interactivePopups = new GraphController(controller, graphPanel);
		
		//create the menus
		AddMenu addMenu = new AddMenu(interactivePopups);
		ViewMenu viewMenu = new ViewMenu(interactivePopups);
		ExportMenu exportMenu = new ExportMenu(interactivePopups);
		HelpMenu helpMenu = new HelpMenu(interactivePopups);

		//add the menus to the menu bar
		this.add(addMenu);
		this.add(viewMenu);
		this.add(exportMenu);
		this.add(helpMenu);

	}

}
