package main;


import java.text.DecimalFormat;

import model.functions.StackFunction;

/**
 * @author user
 *
 */
public class TesterMath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackFunction f = new StackFunction("tan(x)");
		
		//double degrees = 0; - ok
		//double degrees = 45; - ok
//		double degrees = 30;// - ok
		double degrees = 60;// - ok
		//double degrees = 90; //- non va
		//double degrees = 180; // - così così - mi da -0 al posto di 0
		// convert degrees to radians
        double radians = Math.toRadians(degrees);
        
		double v = f.getValue(radians);
		System.out.println("" + String.format("%.3f", v));
		System.out.print((new DecimalFormat("###.###").format(v)));// così si risolve tan(pigreco) = 0
																 // ma tan(pigreco/2) != infinito
		//lo inseriamo dopo ogni return della SupportedFunctions
		
		//GESTIRE QUESTO CASO, NON ESATTAMENTE NULLO, x = pigreco 
		//GRAFICO DELLA tan(x) E' PESSIMO
		
		int[] myArray = new int[5];
		
		for (int i = 0; i < 5; i++) {
			myArray[i] = i*5;
			System.out.println(myArray[i]);
		}
	}

}
