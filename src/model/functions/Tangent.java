package model.functions;


import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.numbers.Constant;
import model.operators.Division;
import model.operators.Power;

public class Tangent extends UnaryFunction {

	public Tangent(FunctionIF operand) {
		super(operand);
	}

	@Override
	public double getValue(double x) {
		return Math.tan(x);
	}

	@Override
	public FunctionIF getDerivative() {
		return new Division(operand.getDerivative(), new Power(new Cosine(operand), new Constant(2)));

	}

	@Override
	public String toString() {
		return "tan("+operand+")";
	}

}
