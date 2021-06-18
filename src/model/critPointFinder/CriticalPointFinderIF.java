package model.critPointFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;

public interface CriticalPointFinderIF {

	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function);
	
	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function, double lowerBound, double upperBound);

	
	
}
