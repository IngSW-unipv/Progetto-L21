package model.core;

import java.awt.Color;
import java.util.ArrayList;

/**
 * ALL OF THE PROJECT REVOLVES AROUND THIS INTERFACE!
 * 
 * Following the popular bandwagon, we could say that,
 * in this model, "EVERYTHING is an FunctionIF".
 * 
 * Meaning: constants, variables, operators and functions, all implement
 * this interface. This is a flavor of the "Composite" GoF-pattern.
 * 
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
	 *returns the critical/stationary points of a function in a given interval. 	
	 * @param lowerBound
	 * @param upperBound
	 * @return
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
	 * @return
	 */
 	public Color getColor();
	
 	/**
 	 * @param otherFunctionIF
 	 * @return
 	 */
 	public boolean equals(FunctionIF otherFunctionIF);
 	

}
