package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.functions.NaturalLogarithm;
import model.numbers.Constant;
/**
 * 
 * @author Team - L21
 *
 */
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


	@Override
	public FunctionIF getDerivative() {
		return new Product((new Power(leftOperand, rightOperand)), new Sum((new Product(rightOperand.getDerivative(), new NaturalLogarithm(leftOperand))), new Division(new Product(rightOperand, leftOperand.getDerivative()), leftOperand)));
	}


	@Override
	public String toString() {
		return leftOperand+"^"+"("+rightOperand+")";
	}

	
	@Override
	public FunctionIF getSimplified() {

		//simplify the operarands recursively
		super.getSimplified();

		// both costant
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant(Math.pow(leftOperand.getValue(0), rightOperand.getValue(0)));
		}

		// if left operand (base) is one
		if(leftOperand.equals(new Constant(1))) {
			return new Constant(1);
		}

		// if right operand (exp) is zero
		if(rightOperand.equals(new Constant(0))) {
			return new Constant(1);
		}

		return this;
	}





}
