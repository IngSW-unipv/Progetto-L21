package model.critPointFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;

public class CriticalPointFinder implements CriticalPointFinderIF {

	@Override
	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function) {
		FunctionIF derivative = function.getDerivative();
		ArrayList<Coordinate> criticalPoints = new ArrayList<Coordinate>();
		
		for(Coordinate criticalPoint : derivative.getZeros()) {
			criticalPoints.add(new Coordinate(criticalPoint.x, function.getValue(criticalPoint.x)));
		}
		
		return criticalPoints;
	}

}
