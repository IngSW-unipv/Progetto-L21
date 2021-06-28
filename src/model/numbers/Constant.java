package model.numbers;

import model.core.BasicOperand;
import model.core.FunctionIF;

/**
 * Represents a constant value.
 * @author Team - L21
 */

public class Constant extends BasicOperand {

	double value;
	
	public Constant(double value) {
		super(value+"");
		this.value = value;
	}

	@Override
	public double getValue(double x) {
		return value;
	}

	/**
	 * The derivative of a constant is 0.
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Constant(0);
	}

}
