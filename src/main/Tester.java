package main;

import model.functions.StackFunction;

public class Tester {

	public static void main(String[] args) {
		
	
		//StackFunction f = new StackFunction("1+sin(x)"); 
		
		//StackFunction f = new StackFunction("1+x*(5+x)");

		//StackFunction f = new StackFunction("sin(cos(tan(1)))");
		
		//StackFunction f = new StackFunction("sin(1)+sin(1)");
		
		
		//StackFunction f = new StackFunction("sin(x)");
 		
		StackFunction f = new StackFunction("1+sin(cos(1+3))");

		
		System.out.println("FINAL RESULT: "+f.getValue(2));

		
		
	}

}
