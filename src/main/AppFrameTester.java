package main;

import model.Calculator;
import view.AppFrame;

/**
 * Test the app!
 *
 */

public class AppFrameTester {

	public static void main(String[] args) {
		
		//make a new controller.
		Calculator calculator = new Calculator();
		
		//start the app!
		new AppFrame(calculator);
		
	}

}
