package model.functions;

import java.util.LinkedList;

import model.parser.Expression;
import model.parser.Parser;

public class StackFunction extends FunctionAB {

	
	LinkedList<Object> parsedExpressionList;
	
	public StackFunction(String expressionString) {
		
		//save the expression of this function.
		this.expression = new Expression(expressionString);
		
		//call the parser to get a list of tokens in reverse-Polish notation.
		parsedExpressionList = Parser.parseExpression(expression);
	
	}
	
	@Override
	public double getValue(double x) {
		
		
		
		//store the partially calculated value at x
		Double accumulator = null;
		//buffer used to store an operand 
		Double buffer = null;
		
		//go through all of the tokens of the expression.
		for(Object token : parsedExpressionList) {
			
			System.out.println(token);
			
			//if the token is a number:
			//if no exception is thrown, token is a number:
			try {
				double number = Double.parseDouble(token.toString());
				buffer = number;
				
			}catch(NumberFormatException e) {
				
			}
			
			
			//if the token is an "x", add its (double) value to the accumulator. Somehow*
			if(token.toString().toLowerCase().equals("x")) { 
				buffer = x;
				
				
			}
			
			

			//...else the token is a function:
			
			
			
			
			//initialize accumulator if it's still null
			if(accumulator==null) {
				accumulator =buffer;
			}
			
			
			//if the token is an operator:
			switch(token.toString()) {
			case "+":
				accumulator+=buffer;
				break;
			case "-":
				accumulator-=buffer;
				break;
			case "*":
				accumulator=accumulator*buffer;
				break;
			case "/":
				accumulator=accumulator/buffer;
				break;
				
			}
			
			
			
			
			
		}
	
		//return the final accumulated value.
		return accumulator;
	}
	
	

}
