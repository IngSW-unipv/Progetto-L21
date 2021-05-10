package main;

import model.Calculator;
import model.functions.FunctionIF;
import view.GraphFrame;
import view.TextualScanning;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//StackFunction f = new StackFunction("tan(x)"); 
		//GESTIRE QUESTO CASO, NON ESATTAMENTE NULLO, x = pigreco 

		Calculator c = new Calculator();
		GraphFrame g = new GraphFrame(c);
//		FunctionIF f = c.addFunction("x^2+1");
//		FunctionIF f2 = c.addFunction("sin(x)");
		TextualScanning t = new TextualScanning(c);
		
		
//		System.out.println("FINAL RESULT: " + f.getValue(1));

		//GESTIRE L'ULTIMO STEP, CHE NON VIENE CALCOLATO
//		for(Coordinate co : f.getSamples(-2.0, +2.0, 0.01)) {
//			System.out.println(co);
//		}
		
		

		//System.out.print(new DecimalFormat("##.##").format(0.87654321));
	}

}
