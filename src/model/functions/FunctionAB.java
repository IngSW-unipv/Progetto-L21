package model.functions;

import java.util.ArrayList;
import model.Coordinate;
import model.parser.Expression;

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
			coordinatesList.add(new Coordinate(i, getValue(i)));
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
	 * returns the expression of this function
	 * @return
	 */
	@Override
	public String getExpression() {
		return expression.toString();
	}

	
	
	


}