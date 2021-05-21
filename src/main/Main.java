package main;

import controller.Calculator;
import view.app.AppFrame;

/**
 * Test the app!
 *
 */

public class Main {

	public static void main(String[] args) {

		//make a new controller.
		Calculator calculator = new Calculator();

		//start the app!
		new AppFrame(calculator);

	}

}
