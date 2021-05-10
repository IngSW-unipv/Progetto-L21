package model.parser;

public class Expression {

	/**
	 * A mathematical expression in terms of x. This is the user input to the parser.
	 */
	String expressionString;

	public Expression(String expressionString) {
		this.expressionString = expressionString;
	}

	@Override
	public String toString() {
		return expressionString;
	}





}
