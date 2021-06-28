package model.operators;

import model.core.BinaryFunction;
import model.core.FunctionIF;
import model.numbers.Constant;
/**
 * 
 * @author Team - L21
 *
 */
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
		super.getSimplified();
		
		//both costant
		if(leftOperand instanceof Constant && rightOperand instanceof Constant) {
			return new Constant(leftOperand.getValue(0)*rightOperand.getValue(0));
		}
		
		//one operand is zero
		if(leftOperand.equals(new Constant(0)) || rightOperand.equals(new Constant(0))) {
			return new Constant(0);
		}
		
		//one operand is one
		if(leftOperand.equals(new Constant(1))) {
			return rightOperand;
		}
		
		if(rightOperand.equals(new Constant(1))) {
			return leftOperand;
		}
		
		// both Power
		if(leftOperand instanceof Power && rightOperand instanceof Power) {
			((Power)leftOperand).leftOperand.equals(((Power)rightOperand).leftOperand);
			Constant exp = new Constant(((Power)leftOperand).rightOperand.getValue(0) + ((Power)rightOperand).rightOperand.getValue(0));
			return new Power(((Power)leftOperand).leftOperand, exp);
		}
		
		return this;
	}
	
	
	

	

}
