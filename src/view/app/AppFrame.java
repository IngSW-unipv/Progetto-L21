package view.app;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.Calculator;
import view.app.insertedFunctionsPanel.InsertedFunctionsPanel;
import view.app.menuBar.AppMenuBar;
import view.graph.GraphPanel;

/**
 * This is a standalone frame that does not rely on the tester shell
 * for commands.
 * 
 * @author user
 *
 */

public class AppFrame extends JFrame{
	
	//The Calculator object is an Observable controller.
	Calculator controller;
	
	//The graph panel specialized for plotting functions.
	static GraphPanel graphPanel;
	
	//Displays the inserted functions' expressions, and allows their deletion.
	InsertedFunctionsPanel insertedFunctionsPanel;
	
	//App-Parameters
	String ICON_PATH = "./images/Cattura.JPG";
	String TITLE = "Calcolatrice Grafica";
	
	public AppFrame(Calculator controller) {
		
		//set this frame's controller to a Calculator object
		this.controller = controller;
		
		//create a new GraphPanel that listens to the controller
		graphPanel = new GraphPanel(controller);
		
		//The graph panel has to have access to the keyboard
		this.addKeyListener(graphPanel.getKeyListener());
		this.setFocusable(true);
		
		
		//make and add the menu bar to this frame
		AppMenuBar menuBar = new AppMenuBar(controller, graphPanel);
		this.setJMenuBar(menuBar);	
		
		//>--SET THIS FRAME'S LAYOUT, AND INSERT ALL OF THE PANELS-----<
		//set this frame's layout to border layout
		this.setLayout(new BorderLayout());
		
		//make a panel for the inserted functions
		insertedFunctionsPanel = new InsertedFunctionsPanel(controller);
		
		//add the graph panel in the middle of the frame 
		this.add(graphPanel, BorderLayout.CENTER);
		
		//add the inserted functions panel to the bottom of the frame
		this.add(insertedFunctionsPanel, BorderLayout.SOUTH);
		//>------------------------------------------------------------<
				
		
		//>--ROUTINE FRAME STUFF---------------------------------------<
		//set this Frame's icon
		setAppIcon(ICON_PATH);
		//set this frame's title
		setTitle(TITLE);
		//closing this frame shuts down the app
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set this frame's size
		this.setSize(700,700);
		//set visible
		this.setVisible(true);
		//set the initial position
		this.setLocationRelativeTo(null);
		//>------------------------------------------------------------<
		
	}
	
	
   /**
   * set the app's icon.
   * 
   * @param iconPath
   */
	private void setAppIcon(String iconPath) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		setIconImage(kit.getImage(iconPath));
	}
	
	


	
	
	
	

	

	
	
	
	
	

	
	
	
	
	
	
	
	

}
