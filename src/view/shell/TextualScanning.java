/**
 * 
 */
package view.shell;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Calculator;
import view.help.AboutFrame;
import view.help.WelcomeFrame;


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

	Calculator c ;
	
	public TextualScanning(Calculator c) {
		this.c=c;
		printShell();
		getExpression(c);
	}

	
	private void printShell() {
		System.out.println("\t\tWelcome - Progetto L21 - UniPv");
		new WelcomeFrame();
		System.out.println("Insert the expression:");
	}


	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String command = scan.nextLine();
		
			//try finding the pattern (Anything)WHITESPACE(Anything)
			Matcher matcher = Pattern.compile("(.*?)\\s+(.*?)").matcher(command);
			matcher.find();
			
			/**
			 * String firstArgument = command.split("\\s+")[0].toUpperCase().trim();
		     * String secondArgument = command.split("\\s+")[1].trim()
			 */
			
			//get the first part (The command), and run a switch on it
			switch(matcher.group(1).toUpperCase()) {
			
			case "PLOT":
				c.addFunction(command.toUpperCase().replace("PLOT", "").trim());
				break;
			case "DEL":
				c.removeFunction(command.toUpperCase().replace("DEL", "").trim());
				break;
			case "DERIV":
				c.addFunction(c.buildFunction(command.toUpperCase().replace("DERIV", "").trim()).getDerivative());
				break;
			/*case "HELP": // sistemare con le regex con ricevere l'help
				System.out.println(matcher.group(1).toUpperCase());
				new AboutFrame();
				break;*/
			}
		
	
		}
		
		
	}

}