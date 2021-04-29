package model.parser;

import java.util.LinkedList;

import org.scijava.parsington.ExpressionParser;

import model.functions.FunctionIF;

public class Parser {
	
	
	//turn an Expression (String) into a FunctionIF
	public static LinkedList<Object> parseExpression(Expression expression) {
		
		ExpressionParser parser = new ExpressionParser();
		
		LinkedList<Object> linkedList = parser.parsePostfix(expression.toString());
		
		
		//System.out.println(linkedList);
		
		return linkedList;
	}
	
	

}
