package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.*;
import model.Coordinate;
import model.Observer;
import model.functions.FunctionIF;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements Observer {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	// hardcode parametrs for the graph 
	int xMin = -20;
	int xMax = 20;
	int yMin = -18;
	int yMax = 18;
	double step = 0.1;

	private FunctionIF drawFunction;


	public GraphPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	/**
	 * Convert cartesian point in pixel point
	 * @param x
	 * @param y
	 * @return
	 */
	public Coordinate cartesianToPixel(double x, double y) {
		double unit = getWidth()/(xMax - xMin);

		int pixelX = (int)Math.round(( x  - xMin)*unit);
		int pixelY = (int)Math.round((yMax - y)*unit);

		return new Coordinate(pixelX, pixelY);
	}

	@Override
	public void paint(Graphics arg0) {
		Graphics2D g2d = (Graphics2D)arg0;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		ArrayList<Coordinate> cartesianPoints;
		try {
			cartesianPoints = drawFunction.getSamples(xMin, xMax, step);
		} catch (NullPointerException e) {
			return;
		}
		ArrayList<Coordinate> displayPoints = new ArrayList<Coordinate>();
		for(Coordinate c : cartesianPoints) {

			Coordinate displayPoint = cartesianToPixel(c.x, c.y);
			displayPoints.add(displayPoint);

		}

		g2d.setColor(Color.red);
		for(int i = 0; i < displayPoints.size()-1; i++) {
			g2d.drawLine((int)displayPoints.get(i).x, (int)displayPoints.get(i).y, (int)displayPoints.get(i+1).x, (int)displayPoints.get(i+1).y);
			g2d.setStroke(new BasicStroke(3));
		}

		disegnaAssi(g2d);	
	}
	
	public void disegnaAssi(Graphics g)  {
		Graphics2D g2d = (Graphics2D)g;
		int w = getSize().width;
		int h = getSize().height;

		Coordinate p1,p2;

		//setta colore degli assi
		g.setColor(Color.yellow);

		//setta lo spessore degli assi
		g2d.setStroke(new BasicStroke(1));

		p1 = cartesianToPixel(-w, 0);
		p2 = cartesianToPixel(w, 0);

		//disegno asse ascisse
		g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);

		p1 = cartesianToPixel(0, -h);
		p2 = cartesianToPixel(0, h);

		//disegno asse ordinate
		g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);  

	}

	@Override
	public void update(ArrayList<Object> a) {
		drawFunction = (FunctionIF)a.get(0);
		repaint();
	}



}
