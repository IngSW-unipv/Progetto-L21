package view.app.menuBar.menus.addMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.app.menuBar.menus.AbstractMenu;
import view.graph.GraphController;
/**
 * Use to add new functions on the graph 
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class AddMenu extends AbstractMenu{

	public AddMenu(GraphController interactivePopups) {
		//set the title
		super(LANGUAGE_MODULE.get("add_menu_title"), interactivePopups);


		//make the "add function" menu item
		JMenuItem addFunctionItem = new JMenuItem(LANGUAGE_MODULE.get("add_menu_new_function"));
		//the addFunctionItem calls the addFunctionProcedure()
		addFunctionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interactivePopups.addFunctionProcedure();
			}
		});

		//add the saved functions selector submenu
		JMenu savedFunctionsMenu = new SavedFunctionsMenu(interactivePopups, "customFunctions", LANGUAGE_MODULE.get("custom_function"), true);

		//add the history selector submenu
		JMenu history = new SavedFunctionsMenu(interactivePopups, "functionsHistoryModule", LANGUAGE_MODULE.get("add_menu_history"), false);

		
		//add the items to the addMenu menu
		this.add(addFunctionItem);
		//add the saved functions menu 
		this.add(savedFunctionsMenu);
		this.add(history);

	}

}
