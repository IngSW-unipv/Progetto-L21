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
 * der [expression]: derives the expression and plots the derivative.
 * 
 * help: displays the help dialog-box.
 * 
 * 
 * NB: all the add/remove operations rely on the Calculator (controller) 
 * adding a function to its list, and notifying the Observer
 * (GraphPanel) of the change.
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
		System.out.println("Insert the keyword 'PLOT' followed by the requested expression.");
	}


	public void getExpression(Calculator c) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String command = scan.nextLine();

			//that is: the command-name.
			String firstArgument = command.split("\\s+")[0];

			//everything that comes after the first argument (could be nothing at all)
			String secondArgument = command.replace(firstArgument, "").trim().toUpperCase();

			//get the first part (The command), and run a switch on it
			switch(firstArgument.toUpperCase().trim()) {

			case "PLOT":
				c.addFunction(secondArgument);
				break;
			case "DEL":
				c.removeFunction(secondArgument);
				break;
			case "DERIV":
				c.addFunction(c.buildFunction(secondArgument).getDerivative());
				break;
			case "HELP": 
				new AboutFrame();
				break;
			}


		}


	}

}