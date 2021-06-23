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

		//if the denominator = 1
		if(rightOperand.equals(new Constant(1))) {
			return leftOperand;
		}
		
		// both costant
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant((leftOperand.getValue(0)/rightOperand.getValue(0)));
		}

		// both Power
		if(leftOperand instanceof Power && rightOperand instanceof Power) {
			((Power)leftOperand).leftOperand.equals(((Power)rightOperand).leftOperand);
			Constant exp = new Constant(((Power)leftOperand).rightOperand.getValue(0) - ((Power)rightOperand).rightOperand.getValue(0));
			return new Power(((Power)leftOperand).leftOperand, exp);
		}
		
		
		return this;
	}






}
