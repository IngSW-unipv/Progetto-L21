package main;

import model.core.Coordinate;
import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;

public class AsymptoteTester {

	public AsymptoteTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SyntaxException {
		FunctionIF f = Parser.parseAndbuild("1/x");
		
//		System.out.println(f.getValue(0));
		for(Coordinate c : f.getSamples()) {
			System.out.println(c);
		}
	}

}
