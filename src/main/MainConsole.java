package main;

import controller.Calculator;
import view.shell.Shell;
/**
 * Test the console app!
 * @author Team - L21
 * 
 */
public class MainConsole {

	public static void main(String[] args) {
		
		//make a new controller.
		Calculator c = new Calculator();
		
		//start the app!
		new Shell(c);
		
	}

}
