package main;

import model.functions.StackFunction;

public class ZeroFinderTester {

	public static void main(String[] args) {
		
		StackFunction function = new StackFunction("x^3 -3");
		
		for(Double zero : function.getZeros()) {
			System.out.println(zero);
		}
		
		
	}

}
