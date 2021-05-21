package view.graph;

import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MouseTracker implements MouseMotionListener {
	
	GraphPanel panelToBeTracked;
	
	public MouseTracker(GraphPanel panelToBeTracked) {
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
		panelToBeTracked.cursorCartesianCoord = panelToBeTracked.pixelToCartesian(mouseOnPanelX, mouseOnPanelY);

		panelToBeTracked.repaint();
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	

}
