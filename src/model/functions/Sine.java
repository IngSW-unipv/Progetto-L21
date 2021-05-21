package model.functions;

import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.operators.Product;

public class Sine extends UnaryFunction {

	public Sine(FunctionIF operand) {
		super(operand);
	}

	@Override
	public double getValue(double x) {
		return Math.sin(operand.getValue(x));
	}

	/**
	 * Applying: Derivative of Sine + The Chain Rule.
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Product(new Cosine(operand), operand.getDerivative());
	}

	@Override
	public String toString() {
		return "sin("+operand+")";
	}
	
	
	
	

}
