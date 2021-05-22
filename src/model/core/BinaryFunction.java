package model.core;

/**
 * A BinaryFunction takes in two FunctionIFs as operands.
 * 
 * NB: the two operands could be BasicOperands, as well as other 
 * complex BinaryFunctions, or anything at all that implements
 * FunctionIF. 
 * 
 * BinaryFunction can be extended by the 
 * basic operators (+, -, *, /), as well as by 
 * two-input functions, etc...
 *
 */

public abstract class BinaryFunction extends FunctionAB {
	
	protected FunctionIF leftOperand;
	protected FunctionIF rightOperand;
	
	public BinaryFunction(FunctionIF leftOperand, FunctionIF rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	
	
}