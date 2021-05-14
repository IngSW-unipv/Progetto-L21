package model.zeroFinder;

/**
 * Constructs a Concrete ZeroFinder.
 * 
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
		}
		
		
		//by default
		return new SimpleZeroFinder();
	}
	

}
