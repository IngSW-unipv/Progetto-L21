package model.critPointFinder;
/**
 * This interface is there just in case we come about a better method of finding critical points. 
 * 
 * @author Team - L21
 */
import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;

public interface CriticalPointFinderIF {
	
	public ArrayList<Coordinate> getCriticalPoints(FunctionIF function, double lowerBound, double upperBound);

}
