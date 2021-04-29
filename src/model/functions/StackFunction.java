package model.functions;

import java.util.LinkedList;

import model.parser.Expression;
import model.parser.Parser;

public class StackFunction extends FunctionAB {

	
	LinkedList<Object> parsedExpressionList;
	
	public StackFunction(String expressionString) {
		
		this.expression = new Expression(expressionString);
		
		parsedExpressionList = Parser.parseExpression(expression);
	
	}
	
	@Override
	public double getValue(double x) {
		
		double accumulator = 0;
		
		
		for(Object token : parsedExpressionList) {
			
		
			if(token.toString().toUpperCase().equals(token)) {
				
			}
			
			
			
			
			
		}
	
		return 0;
	}
	
	

}
