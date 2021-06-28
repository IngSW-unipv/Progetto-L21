package view.shell;

import java.util.Scanner;
import controller.Calculator;
import controller.CalculatorListener;
import controller.ErrorCodes;
import model.core.FunctionIF;
import persistence.FileIO;
import view.help.AboutFrame;
import view.help.WelcomeFrame;
/**
 * 
 * The command shell can be used to plot functions on a graph-frame,
 * or to test some other functionalities of the system.
 * 
 * For a list of all of the commands, go to HtmlFiles/shell_commands.txt
 * @author Team - L21
 */
public class Shell implements CalculatorListener{

	/**
	 * The controller.
	 */
	Calculator controller;

	/**
	 * A simple container for the GraphPanel
	 */
	GraphFrame graphFrame;

	/**
	 * If the "headless" flag is on, the graphframe will not be called to the foreground.
	 */
	boolean headlessFlag;
	
	
	public Shell(Calculator c) {
		this.controller=c;
		this.graphFrame = new GraphFrame(c);
		c.addListener(this);
		printWelcome();
		mainLoop(c);
	}


	/**
	 * Prints a welcome message on the shell.
	 */
	private void printWelcome() {
		System.out.println("\t\tWelcome - Progetto L21 - UniPv");
		new WelcomeFrame();
		System.out.println("Type 'help-sh' for a list of the available commands:");
	}


	/**
	 * Contains the main shell-loop.
	 * @param calculator
	 */
	public void mainLoop(Calculator calculator) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while(true) {
			//get the next line of user input.
			String command = scanner.nextLine();

			//that is: the command-name.
			String firstArgument = command.split("\\s+")[0];

			//everything that comes after the first argument (could be nothing at all)
			String secondArgument = command.replace(firstArgument, "").trim();

			//run the command
			runCommand(firstArgument, secondArgument);
		}
	}


	/**
	 * Execute a shell command.
	 * @param firstArgument
	 * @param secondArgument
	 */
	public void runCommand(String firstArgument, String secondArgument) {

		switch(firstArgument.toUpperCase().trim()) {

		case "PLOT":
			controller.addFunction(secondArgument);
			break;
		case "DEL":
			if(secondArgument.equals("*")) {
				controller.removeAll();
			}
			controller.removeFunction(secondArgument);
			break;
		case "DERIV":
			try {
				FunctionIF derivative = controller.buildFunction(secondArgument).getDerivative();
				System.out.println(derivative.getSimplified().getExpression());
				controller.addFunction(derivative);
			}catch(NullPointerException e) {
				System.out.println("ERROR: invalid expression.");
			}
			break;
		case "HELP": 
			new AboutFrame();
			break;
		case "HELP-SH":
			System.out.println(FileIO.readFile("HtmlFiles/shell_commands.txt"));
			break;
		case "HDLS":
			this.headlessFlag =!headlessFlag; 
			break;
		case "SHOW":
			graphFrame.setVisible(true);
			break;
		case "SMPL":
			FunctionIF function = controller.buildFunction(secondArgument).getSimplified();
			System.out.println(function.getExpression());
			break;
		case "EVAL":
			function = controller.buildFunction(secondArgument).getSimplified();
			System.out.println(function.getValue(0));
			break;
		case "EXIT":
			System.exit(0);
			break;
		default:
			System.out.println("ERROR: '"+firstArgument+"' not recognized as a command!\nEnter 'help-sh' for a list of all valid commands.");
			break;
			
		}
	}


	@Override
	public void onFunctionAdded(FunctionIF function) {
		if(!headlessFlag) {
			graphFrame.setVisible(true);
		}
	}
	
	
	@Override
	public void onFunctionRemoved(FunctionIF function) {
		if(!headlessFlag) {
			graphFrame.setVisible(true);
		}
	}
	
	
	@Override
	public void onError(ErrorCodes errorCode, String message) {
		System.out.println(errorCode+": "+message);
	}
	
}