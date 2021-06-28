package controller;

import model.core.FunctionIF;

/**
 * An interface that has to be implemented by any graphical component 
 * that needs to be kept up to date on the status of the Calculator
 * and the functions added/removed from it.
 * @author Team - L21
 */
public interface CalculatorListener {

	/**
	 * Notifies the listener that a function was added.
	 * @param function
	 */
	public void onFunctionAdded(FunctionIF function);
	
	/**
	 * Notifies the listener that a function was removed.
	 * @param function
	 */
	public void onFunctionRemoved(FunctionIF function);
	
	/**
	 * Notifies the listener that a handled error occurred.
	 * @param errorCode
	 * @param message
	 */
	public void onError(ErrorCodes errorCode, String message);


}
