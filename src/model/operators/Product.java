package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;

public class Product extends BinaryFunction {

	public Product(FunctionIF leftOperand, FunctionIF rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public double getValue(double x) {
		return leftOperand.getValue(x)*rightOperand.getValue(x);
	}

	@Override
	public FunctionIF getDerivative() {
		return new Sum(new Product(leftOperand.getDerivative(), rightOperand), new Product(leftOperand, rightOperand.getDerivative()));
	}
	
	
	@Override
	public String toString() {
		return "("+leftOperand+"*"+rightOperand+")";
	}

	

}
