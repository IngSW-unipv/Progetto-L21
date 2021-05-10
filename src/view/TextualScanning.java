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

	Calculator c;

	public TextualScanning(Calculator c) {
		getExpression(c);
	}


	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("This is the textual interface for a Graph Calculator");
			System.out.println("\t\t----");
			System.out.println("Support functions: \"sin\", \"cos\", \"tan\", \"exp\", \"Log\", \"ln\"");
			System.out.println("You can delete a function on display, press DEL followed by the desired function\n");
			System.out.println("Enter the desired function (after the Keyword PLOT) : ");
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