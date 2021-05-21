package main;

import model.core.FunctionIF;
import parser.Parser;

public class CriticalPointFinderTester {

	public static void main(String[] args) {
		
		FunctionIF f = Parser.parseAndbuild("sin(x)");
		
		System.out.println(f.getCriticalPoints());
		
	}

}
