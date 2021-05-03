package main;

import model.Coordinate;
import model.functions.StackFunction;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		//StackFunction f = new StackFunction("1+sin(x)"); 
		
		//StackFunction f = new StackFunction("1+x*(5+x)");

		//StackFunction f = new StackFunction("sin(cos(tan(1)))");
		
		//StackFunction f = new StackFunction("sin(1)+sin(1)");
		
		//StackFunction f = new StackFunction("sin(x)");
 		
		//StackFunction f = new StackFunction("1+sin(cos(1+3))");
		
		//StackFunction f = new StackFunction("1+x^2");
		
		//StackFunction f = new StackFunction("tan(x)"); GESTIRE QUESTO CASO, NON ESATTAMENTE NULLO, x = pigreco 
		
		//StackFunction f = new StackFunction("sin(cos(x)^(tan(x)))/(x^2+1)");
		
		//StackFunction f = new StackFunction("(sin(x))^cos(0)");
		
		StackFunction f = new StackFunction("x"); 

		//System.out.println("FINAL RESULT: "+f.getValue(Math.PI));
		
		for(Coordinate c : f.getSamples(-2.0, +2.0, 0.1)) {
			System.out.println(c);
//			int y = (int)(c.x*1000);
//			c.x = (double)y/1000;
//			System.out.println(c.x);
		}
		
//		System.out.print(new DecimalFormat("##.##").format(0.87654321));
	}

}
