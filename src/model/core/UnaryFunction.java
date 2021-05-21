package model.core;

/**
 * UnaryFunction can be extended by single-input functions.
 * 
 */

public abstract class UnaryFunction extends FunctionAB {

	protected FunctionIF operand;
	
	public UnaryFunction(FunctionIF operand) {
		this.operand = operand;
	}
	
	
	/**
	 * (re-)set the operand post-creating the UnaryFunction.
	 */
	public void setOperand(FunctionIF operand) {
		this.operand = operand;
	}
	
	
	
}
