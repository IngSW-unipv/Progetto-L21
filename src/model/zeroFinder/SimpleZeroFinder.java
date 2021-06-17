package model.zeroFinder;

import java.util.ArrayList;

import model.core.Coordinate;
import model.core.FunctionIF;


public class SimpleZeroFinder implements ZeroFinderIF {
	
	static double DEFAULT_ALMOST_ZERO = 0.05; //... is as good as zero
	static double WORSENING_FACTOR = 4;
	double almostZero;
	
	
	public SimpleZeroFinder(double almostZero) {
		setMargin(almostZero);
	}
	
	public SimpleZeroFinder() {
		this(DEFAULT_ALMOST_ZERO);
	}
	
	
	
	@Override
	public ArrayList<Coordinate> getZeros(FunctionIF function) {
		
		//tries finding zeros with the default precision (almostZero)
		ArrayList<Coordinate> results = findZerosFromCoordinates(function, almostZero);
		
		//if no zeros were found that way, lower (worsen :-( ) the precision by a factor of WORSENING_FACTOR
		if(results.size()==0) {
			results = findZerosFromCoordinates(function, almostZero*WORSENING_FACTOR);
		}
		
		return results;
	}

	
	/**
	 * The SimpleZeroFinder just browses through the (already computed) samples
	 * of a function and tries to find some y-values that are close enough
	 * to zero.
	 * 
	 * @param function
	 * @param almostZero
	 * @return
	 */
	protected ArrayList<Coordinate> findZerosFromCoordinates(FunctionIF function, double almostZero) {
		ArrayList<Coordinate> results = new ArrayList<Coordinate>();
		for(Coordinate coord : function.getSamples()) {
			if(Math.abs(coord.y)<=almostZero) {
				results.add(new Coordinate(coord.x, 0) );
			}
		}
		return results;
	}
	
	
	
	
	/**
	 * For the purpose of approximation, set a number close enough to zero, that can be considered as good as zero.
	 * @param almostZero
	 */
	@Override
	public void setMargin(double almostZero) {		
		this.almostZero = almostZero;
	}
	
	
	

}
