package model.zeroFinder;

import java.util.ArrayList;

import model.functions.FunctionIF;

public interface ZeroFinderIF {
	
	/**
	 * Computes all/or almost all of the zeros of a function.
	 * @param function
	 * @return
	 */
	public ArrayList<Double> getZeros(FunctionIF function);
	
	/**
	 * For the purpose of approximation, set a number close enough to zero, that can be considered as good as zero.
	 * @param almostZero
	 */
	public void setMargin(double almostZero);

}
