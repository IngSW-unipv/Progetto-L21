package main;

import org.scijava.parsington.ExpressionParser;

import model.functions.StackFunction;
import model.parser.Expression;
import model.parser.Parser;

public class Tester {

	public static void main(String[] args) {
		
		StackFunction f = new StackFunction("x+2.1110000100101010-4/4+0");
		
		f.getValue(10);
		
		
	}

}
