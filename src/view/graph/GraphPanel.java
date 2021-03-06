package view.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import controller.Calculator;
import controller.CalculatorListener;
import controller.ErrorCodes;
import model.core.Coordinate;
import model.core.FunctionIF;
import persistence.Module;
import persistence.ModuleListener;
import persistence.ModuleManager;
import view.graph.ioListeners.GraphKeyListener;
import view.graph.ioListeners.HoveringCoordsMouseTracker;
import view.graph.ioListeners.MovePanelWithMouseListener;
/**
 * 
 * This is the panel where the functions are drawn
 * 
 * @author Team - L21
 *
 */

@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements ModuleListener, CalculatorListener{


	//internal parameters of the graph 
	double xMin = -20;
	double xMax = 20;
	double yMin = -20;
	double yMax = 20;
	double STEP = 0.1;
	Color BG_COLOR =  Color.gray;
	Color AXES_COLOR = Color.black;
	Color ZEROS_COLOR = Color.YELLOW;
	public Color CRITICAL_POINTS_COLOR = Color.BLUE;
	BasicStroke AXES_STROKE = new BasicStroke(2);
	BasicStroke FUNCTIONS_STROKE = new BasicStroke(3);
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	public boolean HIGHLIGHT_ZEROS = false;
	public boolean HIGHLIGHT_CRITICAL_POINTS = false;
	public boolean HOVER_COORDINATES = true;


	/*
	 * this graph's keylistsner
	 */
	public GraphKeyListener keyListener;

	/*
	 * this mouse tracker can plot the coordinate that is being hovered over.
	 */
	HoveringCoordsMouseTracker hoveringCoordsTracker;

	/*
	 * mouse tracker for mouse movement 
	 */
	MovePanelWithMouseListener movePanelWithMouseListener;

	/*
	 * PERSISTANCE MODULES
	 */
	Module LANGUAGE_MODULE;
	
	

	/**
	 * the controller that this GraphPanel listens to.
	 */
	Calculator controller;

	/**
	 * stores the functions to be plotted cumulatively (by order of insertion) on the same instance of the graph.
	 */
	public ArrayList<FunctionIF> functionsOnDisplay = new ArrayList<FunctionIF>();

	/**
	 * 
	 */
	GraphController interactivePopups;


	/**
	 * Needed 'cuz this Panel's container needs to add this Panel's KeyListener
	 * @return
	 */
	public KeyListener getKeyListener() {
		return keyListener;
	}


	public GraphPanel(Calculator controller) {

		interactivePopups = new GraphController(controller, this);

		this.controller = controller;
		controller.addListener(this);

		//set this panel's size
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		//create a mouse tracker, without adding it yet
		hoveringCoordsTracker = new HoveringCoordsMouseTracker(this);

		//create a mouse tracker for the mouse-based movement
		movePanelWithMouseListener = new MovePanelWithMouseListener(this);
		this.addMouseListener(movePanelWithMouseListener);
		this.addMouseMotionListener(movePanelWithMouseListener);
		this.addMouseWheelListener(movePanelWithMouseListener);
		
		this.addMouseMotionListener(hoveringCoordsTracker);
		
		
		//add the keylistsner to the graph
		keyListener = new GraphKeyListener(this, controller);
		this.addKeyListener(keyListener);

		//start listening to the graph-settings Module
		ModuleManager.getInstance().getModule("graph").addListener(this);

		
		//grab the language Module
		String currentLanguage = ModuleManager.getInstance().getModule("local_settings").get("language");
	    LANGUAGE_MODULE =ModuleManager.getInstance().getModule(currentLanguage !=null ? currentLanguage : "english");
	}


	@Override
	public void onFunctionAdded(FunctionIF function) {
		functionsOnDisplay.add(function);
		repaint();
	}


	@Override
	public void onFunctionRemoved(FunctionIF function) {
		functionsOnDisplay.remove(function);
		repaint();
	}


	@Override
	public void onError(ErrorCodes errorCode, String message) {
		switch(errorCode) {
		case FUNCTIONS_LIMIT_REACHED:
			JOptionPane.showMessageDialog(this, message);
			break;
		case ARITHMETIC_ERROR:
			interactivePopups.addFunctionProcedure(message);
			break;
		case SYNTAX_ERROR:
			interactivePopups.addFunctionProcedure(message);
			break;
		}
	}


	/**
	 * Convert Cartesian point to pixel point
	 * @param x
	 * @param y
	 * @return
	 */
	public Point cartesianToPixel(double x, double y) {
		//NB: make sure that xMax and xMin are doubles!!!!! Or else frequent / by zero exception!
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
			plotFunction(functionOnDisplay, g2d);
		}

		//plots the zeros of all functions
		if(HIGHLIGHT_ZEROS) {
			for(FunctionIF functionOnDisplay : functionsOnDisplay) {
				plotZeros(functionOnDisplay, g2d);
			}
		}

		//plots the critical points of all functions
		if(HIGHLIGHT_CRITICAL_POINTS) {
			for(FunctionIF functionOnDisplay : functionsOnDisplay) {
				plotCriticalPoints(functionOnDisplay, g2d);
			}
		}

		
		if(HOVER_COORDINATES) {
			//paints the coordinate that the cursor is hovering on
			g2d.setColor(Color.black);
			Coordinate cursorCartesianCoord = hoveringCoordsTracker.getCursorCartesianCoordinate();
			Point p = cartesianToPixel(cursorCartesianCoord.x, cursorCartesianCoord.y);
			g2d.drawString("("+cursorCartesianCoord.x+", "+cursorCartesianCoord.y+")", p.x, p.y);
		}

	}


	/**
	 * Plots the axes.
	 * @param g
	 */
	private void drawAxes(Graphics g)  {
		Graphics2D g2d = (Graphics2D)g;

		//set axis color
		g.setColor(AXES_COLOR);

		//set axis thickness
		g2d.setStroke(AXES_STROKE);

		Point p1, p2;

		//draw xAxis
		p1 = cartesianToPixel(xMin, 0);
		p2 = cartesianToPixel(xMax, 0);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);


		//draw yAxis
		p1 = cartesianToPixel(0, yMin);
		p2 = cartesianToPixel(0, yMax);
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
	private void plotFunction(FunctionIF function, Graphics2D g2d) {

		//get a list of Cartesian coordinates (samples)
		ArrayList<Coordinate> cartesianPoints;
		cartesianPoints = function.getSamples(xMin, xMax, STEP);

		//convert it to a list of pixel-points 2b displayed on the screen
		ArrayList<Point> displayPoints = new ArrayList<Point>();
		for(Coordinate c : cartesianPoints) {
			Point displayPoint = cartesianToPixel(c.x, c.y);
			displayPoints.add(displayPoint);
		}

		//set the color, and draw the lines used to approximate the graph of the function. 
		g2d.setColor(function.getColor());
		for(int i = 0; i < displayPoints.size()-1; i++) {

			//calculate the approx. slope
			int slope = (displayPoints.get(i+1).y-displayPoints.get(i).y)/(displayPoints.get(i+1).x - displayPoints.get(i).x);

			//if the slope is greater than or equal to a threshold, don't join two consecutive points
			//not to join asymptotes
			if(Math.abs(slope)>=300) {
				continue;
			}

			g2d.setColor(function.getColor());
			g2d.setStroke(this.FUNCTIONS_STROKE);

			g2d.drawLine(displayPoints.get(i).x, displayPoints.get(i).y, displayPoints.get(i+1).x, displayPoints.get(i+1).y);
			g2d.setStroke(FUNCTIONS_STROKE);
		}

	}


	/**
	 * Plots the zeros of a function.
	 * @param function
	 */
	public void plotZeros(FunctionIF function, Graphics2D g2d) {
		g2d.setColor(ZEROS_COLOR);
		plotPoints(function.getZeros(), g2d);
	}


	/**
	 * Plots the critical points of a function.
	 * @param function
	 */
	public void plotCriticalPoints(FunctionIF function, Graphics2D g2d) {
		g2d.setColor(CRITICAL_POINTS_COLOR);
		plotPoints(function.getCriticalPoints(xMin, xMax), g2d);
	}


	/**
	 * Plots a list of Coordinates on the graph.
	 * @param coords
	 * @param g2d
	 */
	public void plotPoints(ArrayList<Coordinate> coords, Graphics2D g2d) {
		for(Coordinate critPoints : coords) {
			Point p = cartesianToPixel(critPoints.x, critPoints.y);
			g2d.drawRect(p.x, p.y, 1, 1);
			g2d.drawString("("+critPoints.x+", "+critPoints.y+")", p.x, p.y);
		}
	}



	//>-------------IN-GRAPH MOTION------------------------<
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
	public void zoom(double amount) {
		xMin+=amount;
		yMin+=amount;

		xMax-=amount;
		xMin-=amount;

		repaint();
	}


	/**
	 * move around sideways on the graph
	 * @param amount
	 */
	public void panHorizontally(double amount) {
		xMin+=amount;
		xMax+=amount;
		repaint();
	}

	/**
	 * 	move around vertically on the graph
	 * @param amount
	 */
	public void panVerically(double amount) {
		yMin+=amount;
		yMax+=amount;
		repaint();
	}

	/**
	 * Reset the graph's perspective to default.
	 */
	public void backHome() {
		xMin = -20;
		xMax = 20;
		yMin = -18;
		yMax = 18;
		repaint();
	}
	//>--------------END IN-GRAPH MOTION------------------------<

	
	/**
	 * Save a snapshot of the graph as an image.
	 * @param File
	 */
	public void takeSnapshot(File file) {
		//create a new buffered image
		BufferedImage snapshot = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB); 
		//get the graphics object of the buffered image to draw on
		Graphics2D g = (Graphics2D) snapshot.getGraphics();
		//print the panel's contents on the image
		this.print(g);
		//save the image to a user-defined location
		try {
			ImageIO.write(snapshot, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//>---HANDLE PERSISTENCE LISTENING-------------------------<//
	@Override
	public void dealWithModuleUpdate(Module module) {
		//update everything 
		for(String chiave : module.getKeyValMap().keySet()) {
			dealWithSingularUpdate(chiave, module.get(chiave));
		}

		//repaint everything.
		repaint();
	}


	@Override
	public void dealWithSingularUpdate(String key, String value) {
		switch(key) {
		case "HIGHLIGHT_ZEROS":
			if(value.contains("false")) {
				HIGHLIGHT_ZEROS = false;
			}else {
				HIGHLIGHT_ZEROS = true;
			}
			return;
		case "HIGHLIGHT_CRITICAL_POINTS":
			if(value.contains("false")) {
				HIGHLIGHT_CRITICAL_POINTS = false;
			}else {
				HIGHLIGHT_CRITICAL_POINTS = true;
			}
			return;
		case "HOVER_COORDINATES":
			
			
			
			if(value.contains("true")) {
				addMouseMotionListener(hoveringCoordsTracker);
				HOVER_COORDINATES  = true;
			}else {
				removeMouseMotionListener(hoveringCoordsTracker);
				HOVER_COORDINATES  = false;
			}
			
			return;
		case "BACKGROUND_COLOR":
			int colorInt = Integer.parseInt(value);
			BG_COLOR = new Color(colorInt);
			break;
		case "AXES_COLOR":
			int axesColorInt = Integer.parseInt(value);
			AXES_COLOR = new Color(axesColorInt);
			break;
		case "STEP":
			this.STEP = Double.parseDouble(value);
			break;
		}
	}
	//>---END HANDLE PERSISTENCE LISTENING-------------------------<

}
