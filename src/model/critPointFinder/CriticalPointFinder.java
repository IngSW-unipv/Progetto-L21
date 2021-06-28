package model.critPointFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;
/**
 * To find the critical points of the function
 * @author Team - L21
 */
public class CriticalPointFinder implements CriticalPointFinderIF {

	@Override
	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function, double lowerBound, double upperBound) {
		FunctionIF derivative = function.getDerivative().getSimplified();
		
		ArrayList<Coordinate> criticalPoints = new ArrayList<Coordinate>();

		for(Coordinate criticalPoint : derivative.getZeros(lowerBound, upperBound)) {
			criticalPoints.add(new Coordinate(criticalPoint.x, function.getValue(criticalPoint.x)));
		}
		
		return criticalPoints;	
	}

}
