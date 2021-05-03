package model.functions;

import java.util.ArrayList;
import model.Coordinate;
import model.parser.Expression;

public abstract class FunctionAB implements FunctionIF {
	
	//expression of this function
	Expression expression;
	
	
	
	//computes samples of this function from lowerBound to upperBound with a given step 
	public ArrayList<Coordinate> getSamples(double lowerBound, double upperBound, double step){
		
		ArrayList<Coordinate> coordinatesList = new ArrayList<Coordinate>();

		
		for(double i = lowerBound; i <= upperBound; i+=step) {
			coordinatesList.add(new Coordinate(i, getValue(i)));
		}
		return coordinatesList;
	}

	

}
