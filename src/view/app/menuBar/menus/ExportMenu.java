package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import view.graph.GraphController;
/**
 * 
 * Allows the user to export images
 * 
 * @author Team - L21
 *
 */
@SuppressWarnings("serial")
public class ExportMenu extends AbstractMenu {
	
	public ExportMenu(GraphController interactivePopups) {
		//set title
		super(LANGUAGE_MODULE.get("export_menu_title"), interactivePopups);
		//make the "export snapshot" menu item
		JMenuItem exportSnapshot = new JMenuItem(LANGUAGE_MODULE.get("export_menu_snapshot"));
		exportSnapshot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				interactivePopups.saveSnapshotProcedure();
			}

		});
		this.add(exportSnapshot);
	}

}
