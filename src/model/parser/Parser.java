package model.parser;

import java.util.LinkedList;

import org.scijava.parsington.ExpressionParser;

public class Parser {
	
	
	//turn an Expression (String) into a list in reverse-Polish notation
	public static LinkedList<Object> parseExpression(Expression expression) {
		
		ExpressionParser parser = new ExpressionParser();
		
		LinkedList<Object> linkedList = parser.parsePostfix(expression.toString());
		
		
//		System.out.println(linkedList);
		
		return linkedList;
	}
	
	

}
