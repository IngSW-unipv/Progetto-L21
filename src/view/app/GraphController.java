package view.app;

import java.awt.Color;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.Calculator;
import persistence.Module;
import persistence.ModuleManager;
import view.graph.GraphPanel;
/**
 * It's a pure fabrication made to contain popups, iteractive procedures and persistance info to get input from th user 
 * 
 * @author Team - L21
 *
 */
public class GraphController {

	Module graphModule;

	public Calculator controller;

	private GraphPanel graphPanel;

	public GraphController(Calculator controller, GraphPanel graphPanel) {
		this.controller = controller;
		this.graphPanel = graphPanel;
		graphModule = ModuleManager.getInstance().getModule("graph");
	}

	//>--START INTERACTIVE PROCEDURES-----------------------<//
	/**
	 * Prompts the user to select the background color.
	 */
	public void setBackgroundColorProcedure() {

		Color chosenColor = JColorChooser.showDialog(graphPanel, "Seleziona il colore di sfondo", null);
		if(chosenColor==null) {
			return;
		}
		graphModule.put("BACKGROUND_COLOR", chosenColor.getRGB()+"");
	}


	/**
	 * Prompts the user to select the color of the axes.
	 */
	public void setAxesColorProcedure() {
		Color color = JColorChooser.showDialog(graphPanel, "Seleziona il colore degli assi", null);
		if(color==null) {
			return;
		}
		graphModule.put("AXES_COLOR", color.getRGB()+"");
	}


	/**
	 * Prompts the user to input an expression, then passes it to the controller
	 */
	public void addFunctionProcedure(String message) {
		String expression = JOptionPane.showInputDialog(graphPanel,  message);
		if(expression==null) {
			return;
		}
		controller.addFunction(expression);
	}


	/**
	 * Default add function procedure
	 */
	public void addFunctionProcedure() {
		String message = "Inserisci una funzione:";
		addFunctionProcedure(message);
	}


	/**
	 * Prompts the user to select a location for a snapshot of the graph.
	 */
	public void saveSnapshotProcedure() {
		//make a new file chooser
		JFileChooser fileChooser = new JFileChooser();
		//set the default file name
		fileChooser.setSelectedFile(new File("snapshot.png"));
		//launch the file chooser and get the user's response
		int response = fileChooser.showOpenDialog(graphPanel);
		//if response is affirmative, save snapshot to user-provided location
		if(response == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			graphPanel.takeSnapshot(file);
		}
	}
	//>--END INTERACTIVE PROCEDURES-----------------------<//

	
	//>-----------SET PERSISTENT PREFRERENCES--------------<//
	public void toggleHighlightZeros() {
		graphModule.put("HIGHLIGHT_ZEROS", (!graphPanel.HIGHLIGHT_ZEROS)+"");
		graphPanel.repaint();
	}


	public void toggleHighlightCriticalPoints() {
		graphModule.put("HIGHLIGHT_CRITICAL_POINTS", (!graphPanel.HIGHLIGHT_CRITICAL_POINTS)+"");
		graphPanel.repaint();
	}

	// TODO !!
	public void toggleHoverCoordinates() {
		graphModule.put("HOVER_COORDINATES", (!graphPanel.HOVER_COORDINATES)+"");
		graphPanel.repaint();
	}


	public void setStep(double step) {
		graphModule.put("STEP", step+"");
		graphPanel.repaint();
	}
	//>---------END SET PERSISTENT PREFRERENCES-----------------<//

}
