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
				saveSnapshotProcedure();
			}

		});
		this.add(exportSnapshot);
	}

	
	/**
	 * Prompts the user to select a location for a snapshot of the graph.
	 */

	private void saveSnapshotProcedure() {
		//make a new file chooser
		JFileChooser fileChooser = new JFileChooser();
		//set the default file name
		fileChooser.setSelectedFile(new File("snapshot.png"));
		//launch the file chooser and get the user's response
		int response = fileChooser.showOpenDialog(this);
		//if response is affirmative, save snapshot to user-provided location
		if(response == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			graphPanel.takeSnapshot(file);
		}
	}


}
