package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.numbers.Constant;

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
		return new Subtraction(leftOperand.getDerivative(), rightOperand.getDerivative());
	}

	@Override
	public String toString() {
		return "("+leftOperand+" - "+rightOperand+")";
	}







	@Override
	public FunctionIF getSimplified() {
		//simplify the operarands recursively
		leftOperand = leftOperand.getSimplified();
		rightOperand = rightOperand.getSimplified();

		//if both operands are constants
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant(leftOperand.getValue(0)-rightOperand.getValue(0));
		}

		//if left operand is = 0
		if(leftOperand.equals(new Constant(0))) {
			return new Product(new Constant(-1), rightOperand);
		}

		//if right operand is = 0
		if(rightOperand.equals(new Constant(0))) {
			return leftOperand;
		}


		return this;
	}





}
