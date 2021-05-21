package model.critPointFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;

public class CriticalPointFinder implements CriticalPointFinderIF {

	@Override
	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function) {
		FunctionIF derivative = function.getDerivative();
		ArrayList<Coordinate> criticalPoints = new ArrayList<Coordinate>();
		
		for(Double zeroOfDerivative : derivative.getZeros()) {
			criticalPoints.add(new Coordinate(zeroOfDerivative, function.getValue(zeroOfDerivative)));
		}
		
		return criticalPoints;
	}

}
