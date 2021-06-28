package main;

import controller.Calculator;
import view.app.AppFrame;
/**
 * Test the GUI app!
 * @author Team - L21
 * 
 */

public class MainApp {

	public static void main(String[] args) {

		//make a new controller.
		Calculator calculator = new Calculator();

		//start the app!
		new AppFrame(calculator);
		
	}

}
