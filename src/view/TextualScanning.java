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
public class TextualScanning {

	/**
	 * 
	 */
	public TextualScanning(Calculator c) {
		getExpression(c);
	}

	
	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Introduci funzione desiderata: ");
			String espressione = scan.nextLine();
			
			if(espressione.matches("add \\S+")) {
				c.addFunction(espressione.replace("add ", ""));
			}
			
			if(espressione.matches("del \\S+")) {
				c.removeFunction(espressione.replace("del ", ""));
			}
			
			
			
			
			
			
			
		}
		
		
	}

}