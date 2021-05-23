package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.graph.GraphPanel;

public class ExportMenu extends JMenu {

	GraphPanel graphPanel;
	
	public ExportMenu(GraphPanel graphPanel) {
		//set title
		super("Esporta");
		this.graphPanel = graphPanel;
		//make the "export snapshot" menu item
		JMenuItem exportSnapshot = new JMenuItem("istantanea");
		exportSnapshot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {					
				graphPanel.saveSnapshotProcedure();
			}

		});
		this.add(exportSnapshot);
	}

	
	

}
