package view.graph.ioListeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import view.graph.GraphPanel;

/**
 * Takes care of all of the mouse-based movement interactions
 * with the GraphPanel.
 * 
 * @author user
 *
 */

public class MovePanelWithMouseListener implements MouseMotionListener, MouseListener, MouseWheelListener {

	GraphPanel graphPanel;
	
	Point initialMousePosition = new Point(1,1);
	
	public MovePanelWithMouseListener(GraphPanel graphPanel) {
		this.graphPanel = graphPanel;
	}
	
	/**
	 * Make use of the mouse's scroll-wheel to 
	 * zoom in on the graph.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		graphPanel.zoom(arg0.getWheelRotation()*(-1));
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("kskdkdskd");
		initialMousePosition = e.getPoint();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int distanceMovedX = e.getX()- initialMousePosition.x;
		int distanceMovedY = e.getY()- initialMousePosition.y;
		//TODO: Dynamic calibration. (Fix this magic number):
		graphPanel.panHorizontally(distanceMovedX/30);
		graphPanel.panVerically(-distanceMovedY/30);
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
