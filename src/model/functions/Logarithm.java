package model.functions;

import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.numbers.Constant;
import model.operators.Division;
import model.operators.Product;

public class Logarithm extends UnaryFunction {

	double base;
	
	public Logarithm(FunctionIF operand, double base) {
		super(operand);
		this.base = base;
	}

	/**
	 * Math.log() => natural logarithm.
	 * Using the change of base formula:
	 */
	@Override
	public double getValue(double x) {
		return Math.log(x)/Math.log(base);
	}
	
	
	/**
	 * Math.log() => natural logarithm.
	 * Using the general formula to derive logarithms of any base.
	 * 
	 * 
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Division(operand.getDerivative(), new Product(operand, new Constant(Math.log(base))));
	}
	
	
	@Override
	public String toString() {
		return "log"+base+"("+operand+")";
	}
	
	

}
