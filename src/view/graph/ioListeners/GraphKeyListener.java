package view.graph.ioListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.graph.GraphPanel;

/**
 * This Class controls the action of keystrokes on the 
 * GraphPanel.
 *  
 * @author user
 *
 */

public class GraphKeyListener implements KeyListener {
	
	GraphPanel graphPanel;
	
	public GraphKeyListener(GraphPanel graphPanel) {
		this.graphPanel = graphPanel;
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
	 */
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_EQUALS:
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
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
