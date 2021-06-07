package main;

import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;

public class ZeroFinderTester {

	public static void main(String[] args) throws SyntaxException {
		
		//FunctionIF f = Parser.parseAndbuild("x^2+2*x"); // ok
		//FunctionIF f = Parser.parseAndbuild("ln(x)"); // non proprio ok
		//FunctionIF f = Parser.parseAndbuild("(x-1)*(x-2)*(x-3)");
		FunctionIF f = Parser.parseAndbuild("sin(x)"); //ok
		//FunctionIF f = Parser.parseAndbuild("x^3"); // ok
		//FunctionIF f = Parser.parseAndbuild("x^2"); // è corretto perchè tutta funziona è pos
		System.out.println(f.getZeros());
		
		
		
	}

}
