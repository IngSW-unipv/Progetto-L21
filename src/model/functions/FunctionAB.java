package model.functions;

import java.util.ArrayList;
import model.Coordinate;
import model.parser.Expression;
import model.zeroFinder.ZeroFinderBuilder;
import model.zeroFinder.ZeroFinderIF;

/**
 * Implements some behaviors that are common 
 * to all kinds of FunctionIFs.
 */

public abstract class FunctionAB implements FunctionIF {

	/**
	 * expression of this function
	 */
	Expression expression;

	
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
				//add a coordinate only if its y is in the domain
				//(to avoid straight lines for intervals outside of the domain)
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
		return expression.toString();
	}



	@Override
	public double getValue(double x) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	/**
	 * get the zeros (or part of the zeros) of this function. 
	 */
	@Override
	public ArrayList<Double> getZeros() {

		//make a new zero finder if this function's zero finder is null
		if(zeroFinder==null) {
			zeroFinder = ZeroFinderBuilder.getZeroFinder("simple");
		}
		
		//get and return this function's zeros
		return zeroFinder.getZeros(this);
	}

	
	
	
	
	


}