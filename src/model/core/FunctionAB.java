package model.core;

import java.awt.Color;
import java.util.ArrayList;

import model.critPointFinder.CriticalPointFinder;
import model.zeroFinder.ZeroFinderBuilder;
import model.zeroFinder.ZeroFinderIF;

/**
 * Implements some behaviors that are common 
 * to all kinds of FunctionIFs.
 * 
 * @author Team - L21
 */

public abstract class FunctionAB implements FunctionIF {

	/**
	 * stash the last-calculated bounds and step
	 */
	double lastLowerBound, lastUpperBound, lastStep;

	/**
	 * store the last-computed samples
	 */
	ArrayList<Coordinate> cachedSamples;

	/**
	 * this function's zerofinder
	 */
	ZeroFinderIF zeroFinder;

	/**
	 * this function's critical point finder
	 */
	CriticalPointFinder criticalPointFinder;


	/**
	 * Computes samples of this function from lowerBound to upperBound with a given step 
	 * @param lowerBound
	 * @param upperBound
	 * @param step
	 * @return
	 */
	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step){

		//check if the samples required are the same as the previous ones, if they are, return the cached samples
		if(lastLowerBound == lowerBound && lastUpperBound==upperBound && lastStep==step) {
			return cachedSamples;
		}

		ArrayList<Coordinate> coordinatesList = new ArrayList<Coordinate>();

		double i;
		for(i = lowerBound; i <= upperBound; i+=step) {

			//check if value is outside of the domain
			double y = getValue(i);
			if(!Double.isNaN(y)) {
				//add a coordinate only if its y is in the domain (to avoid straight lines for intervals outside of the domain)
				coordinatesList.add(new Coordinate(i, y));
			}
		}

		// Do an extra iteration to correct for floating point excess
		if ( ((int)step) != step )
			coordinatesList.add(new Coordinate(i, getValue(i)));

		lastLowerBound = lowerBound;
		lastUpperBound =upperBound;
		lastStep =step;
		cachedSamples = coordinatesList;

		return coordinatesList;
	}


	/**
	 * Returns the last-computed coordinates. If no coordinates were computed yet, returns default interval.
	 * @return
	 */
	public ArrayList<Coordinate> getSamples(){
		if(cachedSamples==null) {
			return getSamples(-20,20,0.1);
		}
		return cachedSamples;
	}


	/**
	 * returns the expression of this function
	 * @return
	 */
	@Override
	public String getExpression() {
		return toString();
	}


	/**
	 * get the zeros (or part of the zeros) of this function. 
	 */
	@Override
	public ArrayList<Coordinate> getZeros() {

		//make a new zero finder if this function's zero finder is null
		if(zeroFinder==null) {
			zeroFinder = ZeroFinderBuilder.getZeroFinder("bisection");
		}

		//get and return this function's zeros
		return zeroFinder.getZeros(this);
	}

	@Override
	public ArrayList<Coordinate> getZeros(double lowerBound, double upperBound) {

		//make a new zero finder if this function's zero finder is null
		if(zeroFinder==null) {
			zeroFinder = ZeroFinderBuilder.getZeroFinder("bisection");
		}

		//get and return this function's zeros in a given interval
		return zeroFinder.getZeros(this, lowerBound, upperBound);
	}


	@Override
	public ArrayList<Coordinate> getCriticalPoints(double lowerBound, double upperBound){

		//make a new critical point finder if the current one is null
		if(criticalPointFinder==null) {
			criticalPointFinder = new CriticalPointFinder();
		}

		//get and retrun this function's critical points in a given interval
		return criticalPointFinder.getCriticalPoints(this, lowerBound, upperBound);		
	}


	/**
	 * Compute color from hash that turns expression into an int 
	 * usable as an rgb code.
	 */
	@Override
	public Color getColor() {

		String expression = getExpression();

		int key = 0;
		for(int i =0; i<expression.length(); i++) {
			key+=((int)expression.charAt(i));
		}

		return new Color( (int)(1000000*(0.3*(double)key))  );
	}

	
	@Override
	public boolean equals(FunctionIF f) {
		return f.getExpression().toLowerCase().equals(this.getExpression().toLowerCase()); 
	}


	@Override
	public FunctionIF getSimplified() {
		return this;
	}

}