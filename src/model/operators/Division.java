package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;

public class Division extends BinaryFunction {

	/**
	 * NB: leftOperand is the numerator, and 
	 * rightOperand is the denominator.
	 * 
	 * @param leftOperand
	 * @param rightOperand
	 */
	
	public Division(FunctionIF leftOperand, FunctionIF rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public double getValue(double x) {
		return leftOperand.getValue(x)/rightOperand.getValue(x);
	}

	@Override
	public FunctionIF getDerivative() {
		return new Division(new Subtraction(new Product(leftOperand.getDerivative(), rightOperand), new Product(leftOperand, rightOperand.getDerivative())), new Product(rightOperand, rightOperand) );
	}

	@Override
	public String toString() {
		return leftOperand+"/"+rightOperand;
	}

	
	
	
	
	
}
