package model.parser;

import java.util.LinkedList;

import org.scijava.parsington.ExpressionParser;

public class Parser {

	private static ExpressionParser parser;

	/**
	 * turn an Expression (String) into a list in reverse-Polish notation
	 * @param expression
	 * @return
	 */
	public static LinkedList<Object> parseExpression(Expression expression) {

		parser = new ExpressionParser();

		LinkedList<Object> linkedList = parser.parsePostfix(expression.toString());

		return linkedList;
	}



}
