package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Coordinate;
import model.Observer;
import model.functions.FunctionIF;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements Observer, KeyListener, MouseMotionListener, MouseWheelListener, MouseListener{



	//(for now) hardcoded parameters for the graph 
	int xMin = -20;
	int xMax = 20;
	int yMin = -20;
	int yMax = 20;
	double step = 0.1;
	Color BG_COLOR =  Color.gray;
	Color AXES_COLOR = Color.black;
	Color FUNCTIONS_COLOR = Color.red;
	BasicStroke AXES_STROKE = new BasicStroke(2);
	BasicStroke FUNCTIONS_STROKE = new BasicStroke(3);
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;


	/**
	 * this list is to store functions to be plotted cumulatively
	 * on the same instance of the graph.
	 */
	ArrayList<FunctionIF> functionsOnDisplay = new ArrayList<FunctionIF>();

	/**
	 * this is a reference to this panel (to be used in separate thread)
	 */
	GraphPanel autoreferenceToGraphPanel;

	/**
	 * this is the (Cartesian) coordinate pointed to by the cursor on screen
	 * it's constantly kept up to date by the HoveringCoordinatesThread
	 */
	volatile Coordinate cursorCartesianCoord = new Coordinate(0,0);


	public GraphPanel() {

		//set this panel's size
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		//get a reference to this panel (to be used in separate thread)
		autoreferenceToGraphPanel = this;
	}



	/**
	 * Convert Cartesian point to pixel point
	 * @param x
	 * @param y
	 * @return
	 */
	public Point cartesianToPixel(double x, double y) {
		double unit = getWidth()/(xMax - xMin);

		int pixelX = (int)Math.round(( x  - xMin)*unit);
		int pixelY = (int)Math.round((yMax - y)*unit);

		return new Point(pixelX, pixelY);
	}


	/**
	 * Converts a pixel point to a Cartesian point.
	 *  
	 * This is used to determine a Cartesian coordinate, 
	 * from the pixel-point (mouse-position) the cursor is hovering on.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */

	public Coordinate pixelToCartesian(int x, int y) {
		double unit = getWidth()/(xMax - xMin); //pixels on the interval
		return new Coordinate( x/unit +xMin  , yMax - y/unit);
	}


	/**
	 * The paint method is called by repaint(). 
	 * It repaints all of the panel, putting on a new 
	 * plain background, then proceeding to sample
	 * the functions and plot them again.
	 * 
	 * It also paints the coordinate hovered over by 
	 * the cursor at any time.
	 */

	@Override
	public void paint(Graphics arg0) {

		//cast the Graphics obj to a Graphics2D obj
		Graphics2D g2d = (Graphics2D)arg0;

		//paint the background
		g2d.setColor(BG_COLOR);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		//paint the axes
		drawAxes(g2d);

		//plot the functions in the order they got inserted
		for(FunctionIF functionOnDisplay : functionsOnDisplay) {
			plotFunction(functionOnDisplay, g2d, FUNCTIONS_COLOR);
		}

		//paints the coordinate that the cursor is hovering on
		Point p = cartesianToPixel(cursorCartesianCoord.x, cursorCartesianCoord.y);
		g2d.drawString("("+cursorCartesianCoord.x+", "+cursorCartesianCoord.y+")", p.x, p.y);

	}

	/**
	 * Plots the axes.
	 * @param g
	 */
	private void drawAxes(Graphics g)  {
		Graphics2D g2d = (Graphics2D)g;
		int w = getSize().width;
		int h = getSize().height;

		Point p1,p2;

		//set axis color
		g.setColor(AXES_COLOR);

		//set axis thickness
		g2d.setStroke(AXES_STROKE);

		p1 = cartesianToPixel(-w, 0);
		p2 = cartesianToPixel(w, 0);

		//draw xAxis
		g.drawLine(p1.x, p1.y, p2.x, p2.y);

		p1 = cartesianToPixel(0, -h);
		p2 = cartesianToPixel(0, h);

		//draw yAxis
		g.drawLine(p1.x, p1.y, p2.x, p2.y);  

	}



	/**
	 * Plots a function on the graph, 
	 * to be called by loop in re/paint().
	 * 
	 * @param function
	 * @param g2d
	 * @param functionsColor
	 */
	private void plotFunction(FunctionIF function, Graphics2D g2d, Color functionsColor) {

		//get a list of Cartesian coordinates (samples)
		ArrayList<Coordinate> cartesianPoints;
		try {
			cartesianPoints = function.getSamples(xMin, xMax, step);
		} catch (NullPointerException e) {
			return;
		}

		//convert it to a list of pixel-points 2b displayed on the screen
		ArrayList<Point> displayPoints = new ArrayList<Point>();
		for(Coordinate c : cartesianPoints) {
			Point displayPoint = cartesianToPixel(c.x, c.y);
			displayPoints.add(displayPoint);
		}

		/**
		 * set the color, and draw the lines used to approximate
		 * the graph of the function. 
		 */
		g2d.setColor(functionsColor);
		for(int i = 0; i < displayPoints.size()-1; i++) {
			g2d.drawLine(displayPoints.get(i).x, displayPoints.get(i).y, displayPoints.get(i+1).x, displayPoints.get(i+1).y);
			g2d.setStroke(FUNCTIONS_STROKE);
		}

	}


	/**
	 * zoom in on the graph (amount > 0), by shrinking the 2 intervals 
	 * (vertical and horizontal), thus drawing the function in a smaller
	 * interval.
	 * 
	 * zoom out (amount < 0), by enlarging the 2 intervals, thus 
	 * putting the function in a bigger perspective.
	 * 
	 * @param amount
	 */

	private void zoom(double amount) {
		xMin+=amount;
		yMin+=amount;

		xMax-=amount;
		xMin-=amount;

		repaint();
	}


	/**
	 * move around sideways on the graph
	 */
	private void panHorizontally(double amount) {
		xMin+=amount;
		xMax+=amount;
		repaint();
	}

	/**
	 * 	move around vertically on the graph
	 */
	private void panVerically(double amount) {
		yMin+=amount;
		yMax+=amount;
		repaint();
	}

	/**
	 * Reset the graph's perspective to default.
	 */
	private void backHome() {
		xMin = -20;
		xMax = 20;
		yMin = -18;
		yMax = 18;
		repaint();
	}




	/**
	 * auto updates the status of the graph based on what happens to the 
	 * Calculator. ie: adds or removes plotted functions.
	 */
	@Override
	public void update(ArrayList<Object> message) {

		switch((String)message.get(1)) {
		case "ADDED":
			functionsOnDisplay.add((FunctionIF)message.get(0));
			repaint();
			break;

		case "DELETED":
			functionsOnDisplay.remove((FunctionIF)message.get(0));
			repaint();
			break;
		}
	}


	/**
	 * implements a few keyPressed actions, of which:
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
				zoom(1);
			}
			break;
		case KeyEvent.VK_MINUS:
			if(arg0.isControlDown()) {
				zoom(-1);
			}
			break;
		case KeyEvent.VK_H:
			if(arg0.isControlDown()) {
				backHome();
			}
			break;
		case KeyEvent.VK_UP:
			panVerically(1);
			break;
		case KeyEvent.VK_DOWN:
			panVerically(-1);
			break;
		case KeyEvent.VK_RIGHT:
			panHorizontally(1);
			break;
		case KeyEvent.VK_LEFT:
			panHorizontally(-1);
			break;		
		}


	}


	/**
	 * Get the panel's location on the screen. 
	 * NB: this is the coordinate of the TOP-LEFT corner of the PANEL.
	 */
	public Point getPanelLocation() {
		Point panelLocation = new Point(0,0);
		try {
			panelLocation = autoreferenceToGraphPanel.getLocationOnScreen();
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
		//covert it to the corresponding Cartesian coordinate
		cursorCartesianCoord = pixelToCartesian(mouseOnPanelX, mouseOnPanelY);

		autoreferenceToGraphPanel.repaint();
	}



	//>---------------------------------------<
	/*
	 * These two methods are used to implement a simple
	 * move-by-dragging the mouse on the graph functionality.
	 */

	Point initialMousePosition = new Point(1,1);

	//get the mouse's initial position and store it
	@Override
	public void mousePressed(MouseEvent arg0) {
		initialMousePosition = arg0.getPoint();
	}

	//calculate the distance that the mouse was dragged for,
	//and based on that, pan the graph.
	@Override
	public void mouseDragged(MouseEvent arg0) {
		int distanceMovedX = arg0.getX()- initialMousePosition.x;
		int distanceMovedY = arg0.getY()- initialMousePosition.y;
		//TODO: Dynamic calibration. (Fix this magic number):
		this.panHorizontally(distanceMovedX/30);
		this.panVerically(-distanceMovedX/30);
	}
	//>------------------------------------------------<



	/**
	 * Make use of the mouse's scroll-wheel to 
	 * zoom in on the graph.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		this.zoom(arg0.getWheelRotation()*(-1));
	}





	//>-----CURRENTLY UNIMPLEMENTED IF METHODS--------<
	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	//>-------------------------------------------<










}