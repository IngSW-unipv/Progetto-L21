package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.functions.NaturalLogarithm;


public class Power extends BinaryFunction {

	/**
	 * left operand is the base, right operand is the exponent.
	 * @param leftOperand
	 * @param rightOperand
	 */
	
	public Power(FunctionIF leftOperand, FunctionIF rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public double getValue(double x) {
		return Math.pow(leftOperand.getValue(x), rightOperand.getValue(x));
	}

	
	
	
	/**
	 * 
	 * 
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Product((new Power(leftOperand, rightOperand)), new Sum(new Product(rightOperand.getDerivative(), new NaturalLogarithm(leftOperand)), new Division(new Product(rightOperand, leftOperand.getDerivative()), leftOperand)));
	}
	
	

	@Override
	public String toString() {
		return leftOperand+"^"+"("+rightOperand+")";
	}
	
	
	
	

}
