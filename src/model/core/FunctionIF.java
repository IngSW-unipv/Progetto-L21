package model.core;

import java.awt.Color;
import java.util.ArrayList;

/**
 * ALL OF THE PROJECT REVOLVES AROUND THIS PUNY LITTLE INTERFACE!
 * 
 * Following the popular bandwagon, we could say that,
 * in this model, "EVERYTHING is an FunctionIF".
 * 
 * Meaning: constants, variables, operators and functions, all implement
 * this interface. This is a flavor of the "Composite" GoF-pattern.
 * 
 * 
 * 
 * METHODS OF THIS INTERFACE:
 * 
 * 1)getValue: which returns a numerical value given an input value. 
 * 
 * 2)getDerivative: which returns another OperationIF, that is 
 * supposed to represent the derivative of this OperationIF.
 * 
 *
 */

public interface FunctionIF {
	
	/**
	 * evaluate this function at a given point.
	 * @param x
	 * @return
	 */
	public double getValue(double x);
	
	/**
	 * get the derivative of this function
	 * @return
	 */
	public FunctionIF getDerivative();
	
	/** * Computes samples of this function from lowerBound to upperBound with a given step * @param lowerBound * @param upperBound * @param step * @return */ 
	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step); 
	
	/** * Returns the last-computed coordinates. If no coordinates were computed yet, returns default interval. * @return */ 
	public ArrayList<Coordinate> getSamples(); 
	
	/** * returns the expression of this function * @return */
	public String getExpression(); 
	
	/** * returns the zeros/roots of this function */ 
	public ArrayList<Coordinate> getZeros();
	
	/** * returns the zeros/roots of this function */ 
	public ArrayList<Coordinate> getZeros(double lowerBound, double upperBound);
	
	/**
	 * returns the critical/stationary points of this function. 
	 * (In the default interval).
	 * @return
	 */
 	public ArrayList<Coordinate> getCriticalPoints();
 	
 	
 	
 	
 	/**
 	 * returns the critical/stationary points of a function in an interval. 	
 	 */
 	public ArrayList<Coordinate> getCriticalPoints(double lowerBound, double upperBound);
 	
 	
 	/**
 	 * Returns a simplified version of this FunctionIF object.
 	 * @return
 	 */
 	public FunctionIF getSimplified();
 	
	
	/**
 	 * Get the color of this function from a hash based on its expression.
 	 * Useful for color-coding the graphs/representations functions.
 	 */
 	public Color getColor();
	
 	/**
 	 * 
 	 * @param f
 	 * @return
 	 */
 	public boolean equals(FunctionIF f);
 	

}
