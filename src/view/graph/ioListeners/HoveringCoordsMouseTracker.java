package view.graph.ioListeners;

import java.awt.IllegalComponentStateException;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.core.Coordinate;
import view.graph.GraphPanel;


/**
 * This is a mouse listener that tracks the coordinate pointed
 * to by the cursor at any given time.
 * 
 * It triggers the GraphPanel's repaint() method whenever
 * the coordinate in question changes. (ie: whenever
 * the cursor moves).
 * 
 *
 */

public class HoveringCoordsMouseTracker implements MouseMotionListener {
	
	GraphPanel panelToBeTracked;
	
	
	volatile Coordinate cursorCartesianCoord = new Coordinate(0,0);
	
	public HoveringCoordsMouseTracker(GraphPanel panelToBeTracked) {
		this.panelToBeTracked = panelToBeTracked;
	}
	
	
	/**
	 * Get the panel's location on the screen. 
	 * NB: this is the coordinate of the TOP-LEFT corner of the PANEL.
	 */
	public Point getPanelLocation() {
		Point panelLocation = new Point(0,0);
		try {
			panelLocation = panelToBeTracked.getLocationOnScreen();
		}catch(IllegalComponentStateException e) {

		}	
		return panelLocation;
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {

		//get the cursor's position RELATIVE TO THE WHOLE SCREEN
		Point mouseCoord = MouseInfo.getPointerInfo().getLocation();

		//get the location of the panel
		Point panelLocation = getPanelLocation();

		//get the position of the cursor RELATIVE TO THE PANEL
		int mouseOnPanelX = mouseCoord.x - panelLocation.x;
		int mouseOnPanelY = mouseCoord.y - panelLocation.y;
		//convert it to the corresponding Cartesian coordinate
		cursorCartesianCoord = panelToBeTracked.pixelToCartesian(mouseOnPanelX, mouseOnPanelY);

		panelToBeTracked.repaint();
	}

	/**
	 * Returns the coordinate pointed to by the cursor.
	 * To be called by the GraphPanel.
	 * @return
	 */
	public Coordinate getCursorCartesianCoordinate() {
		return cursorCartesianCoord;
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	

}
