package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;

public class Sum extends BinaryFunction {

	public Sum(FunctionIF leftOperand, FunctionIF rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public FunctionIF getDerivative() {
		return new Sum(leftOperand.getDerivative(), rightOperand.getDerivative());
	}

	@Override
	public double getValue(double x) {
		return leftOperand.getValue(x) +rightOperand.getValue(x);
	}

	@Override
	public String toString() {
		return "("+leftOperand+" + "+rightOperand+")";
	}

	

	
	

}
