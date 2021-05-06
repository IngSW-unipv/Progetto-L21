/**
 * 
 */
package view;

import java.util.Scanner;
import model.Calculator;
import model.functions.FunctionIF;

/**
 * @author user
 *
 */
public class TestualScanning {

	/**
	 * 
	 */
	public TestualScanning(Calculator c) {
		getExpression(c);
	}

	
	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduci funzione desiderata: ");
		while(true) {
			String espressione = scan.nextLine();
			FunctionIF f = c.addFunction(espressione);
		}
		
		
	}

}
