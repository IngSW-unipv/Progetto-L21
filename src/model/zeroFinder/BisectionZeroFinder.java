package model.zeroFinder;

import java.util.ArrayList;
import java.util.HashMap;
import model.core.Coordinate;
import model.core.FunctionIF;
/*
 * Find the zeros of function using the Bisection Algoritm
 */
public class BisectionZeroFinder extends SimpleZeroFinder implements ZeroFinderIF {

	HashMap<Double,Coordinate> finalResult;
	SimpleZeroFinder simple;

	public BisectionZeroFinder() {
		super();
	}

	@Override
	public ArrayList<Coordinate> getZeros(FunctionIF function) {

		finalResult = new HashMap<Double,Coordinate>();
		simple = new SimpleZeroFinder();

		//tries finding zeros with the default precision (almostZero)
		ArrayList<Coordinate> results = simple.findZerosFromCoordinates(function, super.almostZero);

		if(results.size()==0) {
			return new ArrayList<Coordinate>(finalResult.values());
		}

		int indexZeros = 0;
		int i = 0;

		/*for (Coordinate c : results) {
			zeroStrange(c);
		}*/

		for (Coordinate c : results) {
			//zeroStrange(c);
			// find the "count"
			for(i = 0; i < function.getSamples().size(); i++) {
				double x = c.x;
				if(x==function.getSamples().get(i).x) {
					indexZeros = i; 
					break;
				}	
			}

			// if there are aproximate zeros
			if(indexZeros==0) {
				return new ArrayList<Coordinate>(finalResult.values());
			}

			for(i = indexZeros; i < function.getSamples().size(); i++) {
				//zeroStrange(function.getSamples().get(i));
				if((function.getSamples().get(i).y)*(function.getSamples().get(indexZeros-1).y)<0) {
					Coordinate d = bisectionMetod(function, function.getSamples().get(i).x, function.getSamples().get(indexZeros-1).x);
					finalResult.put(d.x, d);
					break;
				}	
			}
		}
		return new ArrayList<Coordinate>(finalResult.values());
	}

	/*
	 * The Bisection Algoritm: consists of repeatedly bisecting the interval defined by these values 
	 * and then selecting the subinterval in which the function changes sign, and therefore must 
	 * contain a root.
	 */
	private Coordinate bisectionMetod(FunctionIF f, double x1, double x2){
		double xm, fx1, fx2, fxm;
		
		fx1 = f.getValue(x1);
		
		fx2 = f.getValue(x2);
		

		while(Math.abs((x2-x1)) > 1.0E-7) {
			
			xm = (x2+x1)/2;
			
			fxm = f.getValue(xm);
			if (fxm * fx1 >= 0) {
				x1 = xm;
				fx1 = fxm;
			}
			else {
				x2 = xm;
				fx2 = fxm;
			}
		}

		return new Coordinate(x1, 0);
	}

	@Override
	public void setMargin(double almostZero) {
		super.setMargin(almostZero);
	}

}
