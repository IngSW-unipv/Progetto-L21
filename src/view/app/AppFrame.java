package view.app;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Calculator;
import persistance.Module;
import persistance.ModuleListener;
import persistance.ModuleManager;
import view.app.insertedFunctionsPanel.InsertedFunctionsPanel;
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
		graphPanel = new GraphPanel();
		controller.addObserver(graphPanel);
		
		//The graph panel has to have access to a bunch of I/O stuff...
		this.addKeyListener(graphPanel);
		this.addMouseMotionListener(graphPanel);
		this.addMouseListener(graphPanel);
		this.addMouseWheelListener(graphPanel);
		this.setFocusable(true);
		
		
		//make and add the menu bar to this frame
		AppMenuBar menuBar = new AppMenuBar();
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
	
	
	
	/**
	 * Prompts the user to input an expression, then passes it to the controller
	 */
	
	private void addFunctionProcedure() {
		String expression = JOptionPane.showInputDialog(this,"Inserisci una funzione:");
		controller.addFunction(expression);
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
	
	


	/**
	 * This custom menu bar will eventually contain menus and options
	 * to interact with and navigate through the app.
	 */
	
	public class AppMenuBar extends JMenuBar{
		
		public AppMenuBar() {
			
			//>------------ADD MENU--------------------<
			//make the "add" menu
			JMenu addMenu = new JMenu("Aggiungi");
			//make the "add function" menu item
			JMenuItem addFunctionItem = new JMenuItem("Nuova funzione");
			//the addFunctionItem calls the addFunctionProcedure()
			addFunctionItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					addFunctionProcedure();
				}
			});
			
			//add the saved functions selector menu
			JMenu savedFunctionsMenu = new SavedFunctionsMenu(controller);
			
			
			//add the items to the addMenu menu
			addMenu.add(addFunctionItem);
			//add the saved functions menu 
			addMenu.add(savedFunctionsMenu);
			
			//>------------ADD MENU END--------------------<

			
			
			
			//>------------VIEW MENU--------------------<
			//make the "view" menu
			JMenu viewMenu = new JMenu("Visualizza");
			//make the "view zeros" menu item
			JMenuItem viewZeros = new JMenuItem("zeri");
			viewZeros.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					graphPanel.toggleHighlightZeros();
				}				
			});
			viewMenu.add(viewZeros);	
			
			
			//make the view critical points item
			JMenuItem viewCriticalPoints = new JMenuItem("estremanti");
			viewCriticalPoints.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					graphPanel.toggleHighlightCriticalPoints();
				}
				
			});
			viewMenu.add(viewCriticalPoints);	
			
			//make the view coordinates by hovering cursor item
			JMenuItem viewHoveringCoordinates = new JMenuItem("coordinata puntata");
			viewHoveringCoordinates.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					graphPanel.toggleHoverCoordinates();
				}
				
			});
			viewMenu.add(viewHoveringCoordinates);
			
		
			
			//>------------VIEW MENU END--------------------<
			
			
			//>------EXPORT MENU----------------------<
			//make the "export" menu
			JMenu exportMenu = new JMenu("Esporta");
			//make the "export snapshot" menu item
			JMenuItem exportSnapshot = new JMenuItem("istantanea");
			exportSnapshot.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {					
					saveSnapshotProcedure();
				}
				
			});
			exportMenu.add(exportSnapshot);
			
			
			//>------EXPORT MENU END----------------------<
			
			
			//add the menus to the menu bar
			this.add(addMenu);
			this.add(viewMenu);
			this.add(exportMenu);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
