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
		try {
			getExpression(c);
		} catch (IllegalStateException e) {
			System.out.println("Non hai inserito la Keyword PLOT davanti alla funzione");
			getExpression(c);
		}
	}

	
	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Introduci funzione desiderata, preceduta dalla Keyword PLOT: ");
			String command = scan.nextLine();
			
			//try finding the pattern (Anything)WHITESPACE(Anything)
			Matcher matcher = Pattern.compile("(.*?)\\s+(.*?)").matcher(command);
			matcher.find();
			
			//get the first part (The command), and run a switch on it
			switch(matcher.group(1).toUpperCase()) {
			
			case "PLOT":
				c.addFunction(command.toUpperCase().replace("PLOT", "").trim());
				break;
			case "DEL":
				c.removeFunction(command.toUpperCase().replace("DEL", "").trim());
				break;
			}
		
	
		}
		
		
	}

}