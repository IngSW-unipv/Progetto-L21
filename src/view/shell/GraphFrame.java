package view.shell;

import java.awt.*;
import javax.swing.JFrame;

import controller.Calculator;
import view.graph.GraphPanel;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {

	public GraphFrame(Calculator controller) {
		GraphPanel g = new GraphPanel();
		controller.addObserver(g);
		
		
		this.addKeyListener(g);
		this.addMouseMotionListener(g);
		this.addMouseListener(g);
		this.addMouseWheelListener(g);
		this.setFocusable(true);
		this.add(g);
		
		
		this.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.(dimensions of panel)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./images/Cattura.JPG");
		setIconImage(img);
		setTitle("Calcolatrice Grafica");
		
	}
	
	
}
