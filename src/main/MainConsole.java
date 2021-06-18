package main;

import controller.Calculator;
import view.shell.GraphFrame;
import view.shell.TextualScanning;

public class MainConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calculator c = new Calculator();
		//GraphFrame g = new GraphFrame(c);
//		FunctionIF f = c.addFunction("x^2+1");
//		FunctionIF f2 = c.addFunction("sin(x)");
		TextualScanning t = new TextualScanning(c);
		
	}

}
