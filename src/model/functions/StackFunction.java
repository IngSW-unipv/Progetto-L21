package model.functions;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;
import com.sun.istack.internal.logging.Logger;
import model.parser.Expression;
import model.parser.Parser;


//StackFunction is initialized with an expression-string.
//The expression can contain up to ONE variable (x ONLY),
//and can reference supported functions from class: SupportedFunctions.
//Supported functions can be composed (nested).

public class StackFunction extends FunctionAB {

	/**
	 * This is a reverse-Polish (postfix) notation list of operands, operators
	 * and functions.
	 */
	LinkedList<Object> parsedExpressionList;

	//this is a logger, for testing purposes
	Logger logger;

	/**
	 * to create a new StackFunction, pass it the expression to be parsed 
	 * @param expressionString
	 */
	public StackFunction(String expressionString) {

		//save the expression of this function.
		this.expression = new Expression(expressionString);

		//call the parser to get a list of tokens in reverse-Polish (postfix) notation.
		parsedExpressionList = Parser.parseExpression(expression);

		//get a logger for this class
		logger = Logger.getLogger(StackFunction.class);

		//log a "creation successful" message
		logger.info("INITIALIZED STACKFUNCTION: "+expression.toString()+"\n"+parsedExpressionList.toString());

	}


	/**
	 * The StackFunction examines the parsedExpressionList to evaluate the expression for a given value of x.
	 */
	@Override
	public double getValue(double x) {

		//the main stack, the one that will hold the final result.
		Stack<Double> resultsStack = new Stack<>();

		//"branching" stack, that will contain a (possibly nested) function's return value.
		Stack<Double> functionsEvalStack = new Stack<Double>();

		//to keep track of nested function calls.
		Stack<String> functionNamesStack = new Stack<String>();

		//currentStack (could point either to resultsStack or functionsStack).
		Stack<Double> currentStack = resultsStack;


		//for each token of the list...
		for(Object token : parsedExpressionList) {

			//IS TOKEN A NUMBER?
			try {
				currentStack.push(Double.parseDouble(token.toString()));
				continue; //skip to the next iteration
			} catch (NumberFormatException e) {
			}


			//IS TOKEN AN "X"?
			if(token.toString().toLowerCase().equals("x")) { 
				currentStack.push(x);
				continue; //skip to the next iteration
			}

			//IS TOKEN THE BEGINNING OF A FUNCTION CALL?
			if(SupportedFunctions.isFunction(token.toString().toLowerCase())) {
				//push a new name in the functionNamesStack
				functionNamesStack.push(token.toString().toLowerCase());
				//log the start of a function call
				logger.info(token.toString().toUpperCase()+" FUNCTION CALLED");
				//switch currentStack to functionsEvalStack
				currentStack = functionsEvalStack;
				continue; //skip to the next iteration
			}


			//IS TOKEN THE END OF A FUNCTION?
			if(token.toString().equals("<Fn>")) {

				//pop the INNER function's name
				String functionName = functionNamesStack.pop();

				//get the function's argument
				double argument = functionsEvalStack.pop();

				//document the calling of the function on the argument
				logger.info(functionName.toUpperCase()+"'S ARGUMENT IS: "+argument);

				//evaluate the function for that argument
				double functionResult = SupportedFunctions.getValue(functionName, argument);

				//push the function's return value back on the functions stack
				functionsEvalStack.push(functionResult);

				//document the end of a function
				logger.info(functionName.toUpperCase()+" FUNCTION RETURNED: "+ functionResult);

				//if there are no more function calls...:
				if(functionNamesStack.isEmpty()) {
					//switch currentStack back to resultsStack
					currentStack = resultsStack;
					//return function evaluation results to resultsStack
					currentStack.push(functionsEvalStack.pop());
				}

				continue;//skip to the next iteration
			}



			//NONE OF THE ABOVE? TOKEN IS AN OPERATOR! 
			Double val1=null;
			Double val2=null;
			try {
				//pop two operands from the currentStack, prepare for operation.
				val1 = currentStack.pop();
				val2 = currentStack.pop();
			}catch(EmptyStackException e) {
				//no second operand? it might be the end-result.
				//But idk...
				//e.printStackTrace();

			}

			//check what operator must be used, perform the operation,
			//push the result back into the currentStack.
			switch(token.toString())
			{
			case "+":
				currentStack.push(val1+val2);
				break;
			case "-":
				currentStack.push(val2-val1);
				break;
			case "/":
				currentStack.push(val2/val1);
				break;
			case "*":
				currentStack.push(val2*val1);
				break;
			case "^":
				currentStack.push(Math.pow(val2, val1));
				break;
			case "(1)":
				currentStack.push(val2);
				currentStack.push(val1);
				break;
			}


		}	

		//return end-result once done.
		return resultsStack.pop();
	}


}
