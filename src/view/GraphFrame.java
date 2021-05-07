package view;

import java.awt.*;
import javax.swing.JFrame;
import model.Calculator;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {

	public GraphFrame(Calculator controller) {
		GraphPanel g = new GraphPanel();
		controller.addObserver(g);
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
