package model.core;

/**
 * The BasicOperand is just a value. 
 * 
 * It can be extended by Constants as well as Variable
 * numbers.
 * @author Team - L21
 */

public abstract class BasicOperand extends FunctionAB {

	/**
	 * The value to be printed out for representational purposes.
	 */
	private String literal;
	
	
	public BasicOperand(String literal) {
		this.literal = literal;
	}


	@Override
	public String toString() {
		return literal;
	}
	

}
