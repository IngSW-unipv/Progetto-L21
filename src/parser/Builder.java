package parser;

import java.util.ArrayList;
import java.util.Stack;

import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.functions.Cosine;
import model.functions.Logarithm;
import model.functions.NaturalLogarithm;
import model.functions.Sine;
import model.functions.Tangent;
import model.functions.UnaryMask;
import model.numbers.Constant;
import model.numbers.Variable;
import model.operators.Division;
import model.operators.Power;
import model.operators.Product;
import model.operators.Subtraction;
import model.operators.Sum;
import persistence.Module;
import persistence.ModuleManager;
/**
 * Build a FunctionIF object, given a postFixList (SyntaxTree) of an expression
 * @author Team - L21
 *
 */
public class Builder {

	/**
	 * these are the supported operators' literals
	 */
	static String[] OPERATORS = new String[]{"+", "-", "*", "/", "^"};

	/**
	 * This token signifies the end of a function.
	 */
	public static final String END_OF_FUNCTION = "END_OF_FUNCTION";

	/**
	 * 	this stack keeps the last two generated operands, so that 
	 *  an operator can be applied to them after they've been built.
	 */
	private Stack<FunctionIF> mainStack = new Stack<>();

	/**
	 * this is a stack of function-objects to keep track
	 * of recursive calls.
	 */
	private Stack<FunctionIF> functionsStack = new Stack<>();

	/**
	 * this is an alternative stack to keep track
	 * of the argument of functions
	 */
	private Stack<FunctionIF> altStack = new Stack<>();

	/**
	 * this is a reference to a stack that switches 
	 * between mainStack and altStack. 
	 */
	private Stack<FunctionIF> currentStack = mainStack;


	/**
	 * Given a suitable postfix list of tokens, this method builds 
	 * a composite function object that can be referenced 
	 * and called.
	 * 
	 * @param postfixListOfTokens
	 * @return
	 */
	public FunctionIF build(ArrayList<String> postfixListOfTokens) {
		mainStack.clear();
		functionsStack.clear();
		altStack.clear();

		FunctionIF oggettone = null;


		for(String token : postfixListOfTokens) {
			oggettone = build(token);

		}
		return oggettone;
	}


	/**
	 * Given an expression, this method builds an OperationIF
	 * object. It could be a Constant, a Variable, an Operator
	 * or even a Function.
	 * 
	 * @param token
	 * @return
	 */
	private FunctionIF build(String token) {

		//if it's x
		if(token.toLowerCase().equals("x")) {
			Variable x = new Variable("x");
			currentStack.push(x);
			return x;
		}

		//if it's a constant
		if(token.trim().matches("-*\\d+(\\.\\d+)*")) {
			Constant constant = new Constant(Double.parseDouble(token.trim()));
			currentStack.push(constant);
			return constant;
		}

		//if it's an operator
		if(isOperator(token)) {
			return buildOperator(token);
		}

		//if it's the end of a function
		if(isEndOfFunction(token)) {
			//pop the innermost function in the call-stack
			FunctionIF function = functionsStack.pop();
			//feed the function with the operand
			((UnaryFunction)function).setOperand(currentStack.pop());
			//push the function onto the current stack
			currentStack.push(function);
			//if end of recursive calls is reached 
			if(functionsStack.size()==0) {
				mainStack.push(altStack.pop());
				currentStack = mainStack;
				return mainStack.peek();
			}

		}

		//if it's the beginning of a function
		FunctionIF newFunction;
		if((newFunction = buildFunction(token))!=null) {
			//switch to altStack
			currentStack = altStack;
			functionsStack.push(newFunction);
		}

		return null;
	}


	/**
	 * Check if a token-string is an operator of those defined 
	 * in this class.
	 * 
	 * @param token
	 * @return
	 */
	private static boolean isOperator(String token) {
		for(String operator : OPERATORS) {
			if(token.equals(operator)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Build an Operator object (Sum, Subtraction, Multiplication
	 * or Division).
	 * @param operator
	 * @return
	 */
	private FunctionIF buildOperator(String operator) {

		//pop the operands from the stack.
		FunctionIF firstOperand = currentStack.pop();
		FunctionIF secondOperand = currentStack.pop();

		switch(operator) {
		case "+":
			Sum sum = new Sum(firstOperand, secondOperand);
			currentStack.push(sum); 
			return sum;
		case "-":
			Subtraction sub = new Subtraction(secondOperand, firstOperand);
			currentStack.push(sub);
			return sub;
		case "*":
			Product mul = new Product(firstOperand, secondOperand);
			currentStack.push(mul);
			return mul;
		case "/":
			Division div = new Division(secondOperand, firstOperand);
			currentStack.push(div);
			return div;
		case "^":
			Power power = new Power(secondOperand,firstOperand);
			currentStack.push(power);
			return power;

		}

		return null;
	}


	/**
	 * Check if a token represents the end of a function.
	 * @param token
	 * @return
	 */
	private boolean isEndOfFunction(String token) {
		if(token.equals(END_OF_FUNCTION)) {
			return true;
		}
		return false;
	}


	/**
	 * Builds a function object from a function's name.
	 * @param function
	 * @return
	 */
	private static FunctionIF buildFunction(String function) {

		switch(function.toLowerCase()) {
		case "sin":
			Sine sin = new Sine(null);

			return sin;
		case "cos":
			Cosine cos = new Cosine(null);
			return cos;	

		case "ln":
			NaturalLogarithm natLog = new NaturalLogarithm(null);
			return natLog;

		case "tan":
			Tangent tan = new Tangent(null);
			return tan;	

		}

		//general logarithm:
		if(function.matches("log\\d+")) {
			double base = Double.parseDouble(function.replace("log", ""));
			return new Logarithm(null, base);
		}

		//try building a stored custom function identified by its name
		return buildSavedFunction(function);

		//if no match was found, this method returns null.
	}

	
	/**
	 * Build a saved-function from available modules
	 * @param name
	 * @return
	 */
	private static FunctionIF buildSavedFunction(String name) {

		FunctionIF mask;

		try {
			String functionDefinition = null;
			for(Module module : ModuleManager.getInstance().getModules()) {

				if (module.get(name)!=null) {
					functionDefinition = module.get(name);
					mask = Parser.parseAndbuild(functionDefinition);
					return new UnaryMask(name, mask, null);
				}
			}

		} catch (SyntaxException e) {
		}

		return null;

	}

}
