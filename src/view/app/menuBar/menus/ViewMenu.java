package view.app.menuBar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.graph.GraphPanel;

public class ViewMenu extends JMenu {

	GraphPanel graphPanel;
	
	public ViewMenu(GraphPanel graphPanel) {

		//set the title
		super("Visualizza");
		this.graphPanel = graphPanel;
		//make the "view zeros" menu item
		JMenuItem viewZeros = new JMenuItem("zeri");
		viewZeros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightZeros();
			}				
		});
		this.add(viewZeros);	


		//make the view critical points item
		JMenuItem viewCriticalPoints = new JMenuItem("estremanti");
		viewCriticalPoints.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHighlightCriticalPoints();
			}

		});
		this.add(viewCriticalPoints);	

		//make the view coordinates by hovering cursor item
		JMenuItem viewHoveringCoordinates = new JMenuItem("coordinata puntata");
		viewHoveringCoordinates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				graphPanel.toggleHoverCoordinates();
			}

		});
		this.add(viewHoveringCoordinates);

	}




}
