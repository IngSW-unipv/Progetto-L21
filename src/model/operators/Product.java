package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.numbers.Constant;

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

	@Override
	public FunctionIF getSimplified() {
		
		//simplify the operarands recursively
		leftOperand = leftOperand.getSimplified();
		rightOperand = rightOperand.getSimplified();
		
		
		
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant(leftOperand.getValue(0)*rightOperand.getValue(0));
		}
		
		if(leftOperand.equals(new Constant(0)) || rightOperand.equals(new Constant(0))) {
			return new Constant(0);
		}
		
		if(leftOperand.equals(new Constant(1))) {
			return rightOperand;
		}
		
		if(rightOperand.equals(new Constant(1))) {
			return leftOperand;
		}
		
		
		return this;
	}
	
	
	

	

}
