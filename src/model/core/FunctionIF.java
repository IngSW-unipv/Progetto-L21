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
 * Something about the general philosophy of this popular 
 * OO approach to mathematics:
 * 
 * The top-container, or top-level OperationIF, (aka:  the "oggettone"),
 * knows its general structure, and applies it to its components,
 * calling their methods and combining them suitably. 
 * 
 * This process can carry on recursively, with each sub-container
 * knowing its structure and combining its sub-elements suitably,
 * till a point is reached where the elements to be combined are
 * atomic (ie: the Base Case).
 * 
 * Eg: if the top container is a Sum, and the operation requested
 * of it is differentiation, the "oggettone" returns the Sum 
 * of the derivatives of its components. 
 * 
 * Each component knows what its own derivative is, if not 
 * directly, indirecly: by calling the getDerivative method
 * of its components.
 * 
 * In this scenario (differentiaton), the process halts 
 * when the component is direcly aware of what its derivative is, 
 * ie: when the component is a Constant or a Variable. 
 * This is the Base Case.
 *  
 * @author Team - L21
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
	
	/** 
	 * Computes samples of this function from lowerBound to upperBound with a given step 
	 * @param lowerBound 
	 * @param upperBound 
	 * @param step 
	 * @return 
	 */ 
	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step); 
	
	/**
	 * Returns the last-computed coordinates. If no coordinates were computed yet, returns default interval. 
	 * @return 
	 */ 
	public ArrayList<Coordinate> getSamples(); 
	
	/** 
	 * returns the expression of this function 
	 * @return
	 */
	public String getExpression(); 
	
	/** 
	 * returns the zeros/roots of this function 
	 */ 
	public ArrayList<Coordinate> getZeros();
	
	/**
	 * returns the zeros/roots of this function in a given interval
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */ 
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
