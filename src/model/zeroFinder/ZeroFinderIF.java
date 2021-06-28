package model.zeroFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;
/**
 * This interface could be implemented by different algorithms
 * @author Team - L21
 *
 */
public interface ZeroFinderIF {
	
	/**
	 * Computes the zeros of a function in its current interval.
	 * @param function
	 * @return
	 */
	public ArrayList<Coordinate> getZeros(FunctionIF function);
	
	/**
	 * Computes the zeroes of a function in an interval.
	 * @param function
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */
	public ArrayList<Coordinate> getZeros(FunctionIF function, double lowerBound, double upperBound);
	
	/**
	 * For the purpose of approximation, set a number close enough to zero, that can be considered as good as zero.
	 * @param almostZero
	 */
	public void setMargin(double almostZero);

}
