package model.functions;

import java.util.LinkedList;
import java.util.Stack;

import model.parser.Expression;
import model.parser.Parser;

public class StackFunction extends FunctionAB {

	
	LinkedList<Object> parsedExpressionList;
	
	public StackFunction(String expressionString) {
		
		//save the expression of this function.
		this.expression = new Expression(expressionString);
		
		//call the parser to get a list of tokens in reverse-Polish notation.
		parsedExpressionList = Parser.parseExpression(expression);
		
		//System.out.println(parsedExpressionList);
	
	}
	
	@Override
	public double getValue(double x) {

		//create a stack
		Stack<Double> stack = new Stack<>();
		// Scan all characters one by one
		for(Object token : parsedExpressionList) {
//			System.out.println(token.toString());
//			System.out.println(stack);
			try {
				double c =  Double.parseDouble(token.toString());
				stack.push(c);
			} catch (NumberFormatException e) {
					
				//if the token is an "x", add its (double) value to the accumulator. Somehow*
				if(token.toString().toLowerCase().equals("x")) { 
					stack.push(x);
					continue;
				}
				
				/*
				 * if(startFunction(token.toString())) {
				 * 
				 * }
				 * 
				 * if(endFunction(token.toString())) {
				 * 
				 * }
				 */
				

				String c = token.toString();
				double val1 = stack.pop();
				double val2 = stack.pop();

				switch(c)
				{
				case "+":
					stack.push(val2+val1);
					break;

				case "-":
					stack.push(val2- val1);
					break;

				case "/":
					stack.push(val2/val1);
					break;

				case "*":
					stack.push(val2*val1);
					break;

				case "(1)":
					stack.push(val2);
					stack.push(val1);
					break;

				}

			}
		}
		return stack.pop();
	}
	
	

}
