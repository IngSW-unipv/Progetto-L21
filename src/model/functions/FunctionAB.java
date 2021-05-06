package model.functions;

import java.util.ArrayList;
import model.Coordinate;
import model.parser.Expression;

/**
 * @author user
 *
 */
public abstract class FunctionAB implements FunctionIF {

	/**
	 * expression of this function
	 */
	Expression expression;


	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step){

		ArrayList<Coordinate> coordinatesList = new ArrayList<Coordinate>();
		
		double i;
		for(i = lowerBound; i <= upperBound; i+=step) {
			coordinatesList.add(new Coordinate(i, getValue(i)));
		}
		
		// Do an extra iteration to correct for floating point excess
		if ( ((int)step) != step )
			coordinatesList.add(new Coordinate(i, getValue(i)));
		
		return coordinatesList;
	}



}
