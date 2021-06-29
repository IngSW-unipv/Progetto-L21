package view.graph.ioListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Calculator;
import view.graph.GraphController;
import view.graph.GraphPanel;
/**
 * This Class controls the action of keystrokes on the 
 * GraphPanel.
 *  
 * @author Team - L21
 *
 */
public class GraphKeyListener implements KeyListener {
	
	GraphPanel graphPanel;
	GraphController interactivePopups;
	
	public GraphKeyListener(GraphPanel graphPanel, Calculator controller) {
		this.graphPanel = graphPanel;
		interactivePopups = new GraphController(controller, graphPanel);
	}
	
	
	/**
	 * implements a few keyPressed actions, out of which:
	 * 
	 * CTRL && +/- zoom in/out, 
	 * 
	 * CTRL && [arrow key]: pan left-right/up-down, 
	 * 
	 * CTRL && H: return home (ie:
	 * default perspective on the graph)
	 * 
	 * CTRL && P: plot a new function on the graph.
	 * 
	 * CTRL && E: export a snapshot of the current graph.
	 * 
	 * CTRL && X: view critical points. (eXtremanti)
	 * 
	 * CTRL && Z: view Zeros.
	 * 
	 * CTRL && C: clears the graph.
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_EQUALS:
		case KeyEvent.VK_PLUS:
			if(arg0.isControlDown()) {
				graphPanel.zoom(1);
			}
			break;
		case KeyEvent.VK_MINUS:
			if(arg0.isControlDown()) {
				graphPanel.zoom(-1);
			}
			break;
		case KeyEvent.VK_H:
			if(arg0.isControlDown()) {
				graphPanel.backHome();
			}
			break;
		case KeyEvent.VK_UP:
			graphPanel.panVerically(1);
			break;
		case KeyEvent.VK_DOWN:
			graphPanel.panVerically(-1);
			break;
		case KeyEvent.VK_RIGHT:
			graphPanel.panHorizontally(1);
			break;
		case KeyEvent.VK_LEFT:
			graphPanel.panHorizontally(-1);
			break;		
		case KeyEvent.VK_P:
			if(arg0.isControlDown()) {
				interactivePopups.addFunctionProcedure();
			}
			break;
		case KeyEvent.VK_E:
			if(arg0.isControlDown()) {
				interactivePopups.saveSnapshotProcedure();
			}
			break;
		case KeyEvent.VK_X:
			if(arg0.isControlDown()) {
				interactivePopups.toggleHighlightCriticalPoints();
			}
			break;
		case KeyEvent.VK_Z:
			if(arg0.isControlDown()) {
				interactivePopups.toggleHighlightZeros();
			}
			break;	
		case KeyEvent.VK_C:
			if(arg0.isControlDown()) {
				interactivePopups.controller.removeAll();
			}
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
