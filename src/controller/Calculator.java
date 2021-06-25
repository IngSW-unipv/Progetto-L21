package controller;

import java.util.ArrayList;

import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;
import persistence.ModuleManager;
import persistence.Module;
import persistence.ModuleListener;


/**
 * This is a controller, that can have listeners.
 * 
 * Calculator is a facade controller, in that it can 
 * be told explicitly to add/remove functions from 
 * the list that it keeps internally.
 * 
 * Moreover, listeners (such as panels and/or other GUI elements) 
 * can be added to Calculator. They will thus be
 * notified of any change in the list of inserted functions.
 * 
 * If a new function is added, it will be delivered to 
 * all of the CalculatorListener, so that each one of them can decide 
 * to display it, or plot it, somehow.
 * 
 * If a function is removed, the CalculatorListeners are told about 
 * that too.
 * 
 * @author Team L21
 *
 */

public class Calculator implements ModuleListener{

	/**
	 * List of FunctionIFs inserted by user
	 */
	private ArrayList<FunctionIF> functions;


	/**
	 *Due to performance issues, we thought it might be a good
	 *idea to limit the tot. number of simultaneously 
	 *plottable functions. Eventually the user may change 
	 *this amount, at their own RAM card's risk.
	 */
	int MAX_INSERTABLE_FUNCTIONS  = 3;

	/**
	 * List of listeners 
	 */
	private ArrayList<CalculatorListener> listeners;


	/**
	 * Persistence module to store the history of inputed functions
	 */
	private Module functionsHistoryModule = ModuleManager.getInstance().getModule("functionsHistoryModule");

	
	/**
	 * Persistence module to store the graph settings
	 */

	private Module graphModule = ModuleManager.getInstance().getModule("graph");

	
	
	
	public Calculator() {
		this.functions = new ArrayList<FunctionIF>();
		this.listeners = new ArrayList<CalculatorListener>();
		graphModule.addListener(this);
	}


	/**
	 * Parse an expression and add the function object
	 * @param stringExpression
	 * @return
	 */
	public FunctionIF addFunction(String stringExpression) {

		//parse the string and turn it into a FunctionIF object
		FunctionIF newFunction = buildFunction(stringExpression);

		//reject null functions 
		if(newFunction==null) {
			return null;
		}

		//reject function if it's already in the list
		for(FunctionIF function : functions) {
			if(function.equals(newFunction)) {
				return null;
			}
		}



		//if parsing went ok, try adding the FunctionIF object...
		functionsHistoryModule.put("function"+System.currentTimeMillis()%1000, stringExpression);
		return addFunction(newFunction);

	}


	/**
	 * just add a ready-made function object
	 * @param function
	 * @return
	 */
	public FunctionIF addFunction(FunctionIF function) {


		//reject any function if max amount is exceeded
		if(functions.size()+1>MAX_INSERTABLE_FUNCTIONS) {

			//notify listeners
			for(CalculatorListener listener : listeners) {
				listener.onError(ErrorCodes.FUNCTIONS_LIMIT_REACHED, "Limite funzioni settato a: "+this.MAX_INSERTABLE_FUNCTIONS);
			}

			return null;
		}


		//simplify the function before adding it
		function = function.getSimplified();

		//add the function to this Calculator
		this.functions.add(function);

		//notify the listeners that a new function got added
		for(CalculatorListener listener : listeners) {
			listener.onFunctionAdded(function);
		}

		return function;
	}


	/**
	 * Remove a FunctionIF from the list
	 * @param f
	 */
	public void removeFunction(FunctionIF f) {
		this.functions.remove(f);

		//notify the listeners that a function was removed.
		for(CalculatorListener listener : listeners) {
			listener.onFunctionRemoved(f);
		}

	}

	/**
	 * @overload
	 * Remove a FunctionIF by its index
	 */
	public void removeFunction(int index) {
		removeFunction(functions.get(index));
	}


	/**
	 * @overload
	 * Remove a FunctionIF by its expression
	 */
	public void removeFunction(String expression) {
		for(int i=0; i< functions.size(); i++) {
			if(functions.get(i).getExpression().toUpperCase().equals(expression.toUpperCase())) {
				removeFunction(functions.get(i));
			}
		}
	}


	/**
	 * Remove all of the functions.
	 */
	public void removeAll() {
		for(FunctionIF function : functions) {
			for(CalculatorListener listener : listeners) {
				listener.onFunctionRemoved(function);
			}
		}
		functions.clear();
	}


	/**
	 * add a listener to this Calculator.
	 */
	public void addListener(CalculatorListener listener) {
		this.listeners.add(listener);
	}


	/**
	 * remove a listener from this Calculator.
	 */
	public void removeListener(CalculatorListener listener) {
		this.listeners.remove(listener);

	}



	/**
	 * To build a FunctionIF from a String
	 * @throws SyntaxException 
	 */
	public FunctionIF buildFunction(String stringExpression){

		FunctionIF f = null;

		try {
			//call the parser and build a function from the string-expression
			f = Parser.parseAndbuild(stringExpression);

		} catch (SyntaxException | IllegalArgumentException e) {
			//notify listeners in case of a syntax exception:
			for(CalculatorListener listener : listeners) {
				listener.onError(ErrorCodes.SYNTAX_ERROR, "Sintassi errata! re-inserisci:");
			}
		} catch(ArithmeticException e) {
			//notify listeners in case of an arithmetic exception:
			for(CalculatorListener listener : listeners) {
				listener.onError(ErrorCodes.ARITHMETIC_ERROR, "Aritmetica errata! re-inserisci:");
			}
		}

		return f;
	}


	@Override
	public void dealWithModuleUpdate(Module moduleThatGotUpdated) {
		//update everything 
		for(String chiave : moduleThatGotUpdated.getKeyValMap().keySet()) {
			dealWithSingularUpdate(chiave, moduleThatGotUpdated.get(chiave));
		}
	}

	@Override
	public void dealWithSingularUpdate(String key, String value) {
		switch(key) {
		case "MAX_INSERTABLE_FUNCTIONS":
			MAX_INSERTABLE_FUNCTIONS = Integer.parseInt(value);
			break;
		}
	}










}