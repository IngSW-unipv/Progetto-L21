package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.numbers.Constant;

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
		if(rightOperand.equals(new Constant(0))) {
			throw new ArithmeticException();
		}
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
		return "("+leftOperand+"/"+rightOperand+")";
	}


	@Override
	public FunctionIF getSimplified() {
		//simplify the operarands recursively
		leftOperand = leftOperand.getSimplified();
		rightOperand = rightOperand.getSimplified();

		//if numerator = denominator
		if(leftOperand.equals(rightOperand)) {
			return new Constant(1);
		}
		
		//if the denominator =1
		if(rightOperand.equals(new Constant(1))) {
			return leftOperand;
		}
		
		return this;
	}






}
