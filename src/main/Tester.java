package main;

import org.scijava.parsington.ExpressionParser;

import model.functions.StackFunction;
import model.parser.Expression;
import model.parser.Parser;

public class Tester {

	public static void main(String[] args) {
		
		//doesn't always respect PEMDAS!!!!!!
		StackFunction f = new StackFunction("2+2+x/x");
		
		System.out.println(f.getValue(2));;
		
		
	}

}
