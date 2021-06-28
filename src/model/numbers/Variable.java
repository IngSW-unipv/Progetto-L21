package model.numbers;

import model.core.BasicOperand;
import model.core.FunctionIF;

/**
 * Represents a variable value.
 * @author Team - L21
 */

public class Variable extends BasicOperand {
	
	public Variable(String literal) {
		super(literal);
	}

	@Override
	public double getValue(double x) {
		return x;
	}

	/**
	 * The derivative of an atomic variable is the constant 1.
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Constant(1);
	}

}
