package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.numbers.Constant;
/**
 * 
 * @author Team - L21
 *
 */
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


	@Override
	public FunctionIF getSimplified() {
		
		//simplify the operarands recursively
		super.getSimplified();

		//if both operands are constants
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant(leftOperand.getValue(0)+rightOperand.getValue(0));
		}

		//if left operand is = 0
		if(leftOperand.equals(new Constant(0))) {
			return rightOperand;
		}

		//if right operand is = 0
		if(rightOperand.equals(new Constant(0))) {
			return leftOperand;
		}

		return this;
	}








}
