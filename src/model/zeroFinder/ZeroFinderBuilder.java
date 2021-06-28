package model.zeroFinder;

/**
 * Constructs a Concrete ZeroFinder.
 * It could be useful in case we come about a different method to find zeros
 *
 * @author Team - L21
 */

public class ZeroFinderBuilder {
	
	/**
	 * Get a concrete ZeroFinder
	 * @param type
	 * @return
	 */
	public static ZeroFinderIF getZeroFinder(String type) {
		switch(type) {
		case "simple":
			return new SimpleZeroFinder();
		case "bisection":
			return new BisectionZeroFinder();
		}
		
		//by default
		return new SimpleZeroFinder();
	}
	

}
