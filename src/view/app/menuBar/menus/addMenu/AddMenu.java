package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Calculator;
import view.app.menuBar.menus.AbstractMenu;
import view.graph.GraphPanel;
/**
 * Use to add new functions on the graph 
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class AddMenu extends AbstractMenu{

	Calculator controller;
	GraphPanel graphPanel;

	public AddMenu(Calculator controller, GraphPanel graphPanel) {

		//set the title
		super(LANGUAGE_MODULE.get("add_menu_title"));
		//set the controller
		this.controller = controller;
		//set the graph panel
		this.graphPanel = graphPanel;

		//make the "add function" menu item
		JMenuItem addFunctionItem = new JMenuItem(LANGUAGE_MODULE.get("add_menu_new_function"));
		//the addFunctionItem calls the addFunctionProcedure()
		addFunctionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.addFunctionProcedure();
			}
		});

		//add the saved functions selector submenu
		JMenu savedFunctionsMenu = new SavedFunctionsMenu(controller, "customFunctions", LANGUAGE_MODULE.get("custom_function"), true);

		//add the history selector submenu
		JMenu history = new SavedFunctionsMenu(controller, "functionsHistoryModule", LANGUAGE_MODULE.get("add_menu_history"), false);

		
		//add the items to the addMenu menu
		this.add(addFunctionItem);
		//add the saved functions menu 
		this.add(savedFunctionsMenu);
		this.add(history);

	}

}
