package view;

import javax.swing.JFrame;
import model.Calculator;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {

	public GraphFrame(Calculator controller) {
		GraphPanel g = new GraphPanel(500, 500);
		controller.addObserver(g);
		this.add(g);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500, 500);
	}
	
	     
}
