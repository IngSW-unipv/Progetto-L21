package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;

public class Subtraction extends BinaryFunction {

	public Subtraction(FunctionIF leftOperand, FunctionIF rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public double getValue(double x) {
		return leftOperand.getValue(x) - rightOperand.getValue(x);
	}

	@Override
	public FunctionIF getDerivative() {
		return new Subtraction(leftOperand, rightOperand);
	}
	
	@Override
	public String toString() {
		return leftOperand+" - "+rightOperand;
	}

}
