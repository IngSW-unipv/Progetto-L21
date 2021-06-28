package model.functions;
/**
 * @author Team - L21
 */
import model.core.FunctionIF;

public class NaturalLogarithm extends Logarithm {

	public NaturalLogarithm(FunctionIF operand) {
		super(operand, Math.E);
	}
	
	

	@Override
	public String toString() {
		return "ln"+"("+operand+")";
	}
	

}
