package model.functions;

import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.numbers.Constant;
import model.operators.Product;

public class Cosine extends UnaryFunction {

	public Cosine(FunctionIF operand) {
		super(operand);
	}

	@Override
	public double getValue(double x) {
		return Math.cos(operand.getValue(x));
	}

	/**
	 * Applying: Derivative of Cosine + The Chain Rule.
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Product(new Product(new Constant(-1), new Sine(operand)), operand.getDerivative());
	}
	
	@Override
	public String toString() {
		return "cos("+operand+")";
	}

	
	
}
