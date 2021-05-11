package model.functions;

import java.util.ArrayList;

import model.Coordinate;

public interface FunctionIF {
	
	/**
	 * Evaluate the function at a given point. (Valore puntuale).
	 * @param x
	 * @return
	 */
	public double getValue(double x);
	
	/**
	 * Computes samples of this function from lowerBound to upperBound with a given step 
	 * @param lowerBound
	 * @param upperBound
	 * @param step
	 * @return
	 */
	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step);
	
	/**
	 * returns the expression of this function
	 * @return
	 */
	public String getExpression();
	
	
	//public FunctionIF getDerivative();
	

}