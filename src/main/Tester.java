package main;

import controller.Calculator;
import view.shell.GraphFrame;
import view.shell.TextualScanning;

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
		
	}

}
