package model.functions;
/**
 * This class is used to wrap a saved expression into a FunctionIF object
 *
 * @author Team - L21
 */
import model.core.FunctionIF;
import model.core.UnaryFunction;
import model.operators.Product;

public class UnaryMask extends UnaryFunction {

	FunctionIF mask;
	String name;

	public UnaryMask(String name, FunctionIF mask, FunctionIF operand) {
		super(operand);
		this.mask = mask;
		this.name = name;
	}

	@Override
	public double getValue(double x) {
		return mask.getValue(operand.getValue(x));
	}

	/**
	 * The chain rule.
	 */
	@Override
	public FunctionIF getDerivative() {
		return new Product(mask.getDerivative(), operand.getDerivative());
	}

	@Override
	public String toString() {
		return "("+name+" : "+operand+"-> "+mask+")";
	}

}
