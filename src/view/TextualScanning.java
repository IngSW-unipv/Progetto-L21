/**
 * 
 */
package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Calculator;
import model.functions.FunctionIF;

/**
 * @author user
 * 
 * This is a simple command shell, meant for testing purposes.
 * 
 * commands:
 * 
 * add [expression]: plots a new function on the graph 
 * 
 * del [expression]: removes all of the functions with a given expression.
 * 
 * 
 * NB: all of these operations rely on the Calculator (controller) 
 * adding a function to its list, and notifying the Observer
 * (GraphPanel) of the change.
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
			String command = scan.nextLine();
			
			Matcher matcher = Pattern.compile("(.*?)\\s+(.*?)").matcher(command);
			matcher.find();
			
			
			System.out.println(matcher.group(1));
			
			switch(matcher.group(1).toUpperCase()) {
			
			case "PLOT":
				matcher.find();
				c.addFunction(command.toUpperCase().replace("PLOT", "").trim());
				break;
			case "DEL":
				matcher.find();
				c.removeFunction(command.toUpperCase().replace("DEL", "").trim());
				break;
			}
		
			
			
		}
		
		
	}

}