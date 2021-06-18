package controller;

import java.util.ArrayList;

import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;


/**
 * This is a controller, that is also Observable.
 * 
 * Calculator is a facade controller, in that it can 
 * be told explicitly to add/remove functions from 
 * the list that it keeps internally.
 * 
 * Moreover, observers (such as panels and/or other GUI elements) 
 * can be added to Calculator. They will thus be
 * notified of any change in the list of inserted functions.
 * 
 * If a new function is added, it will be delivered to 
 * all of the Observers, so that each one of them can decide 
 * to display it, or plot it, somehow.
 * 
 * If a function is removed, the Observers are told about 
 * that too.
 * 
 * @author Team L21
 *
 */

public class Calculator implements Observable{

	/**
	 * List of FunctionIFs inserted by user
	 */
	private ArrayList<FunctionIF> functions;


	/**
	 *Due to performance issues, we thought it might be a good
	 *idea to limit the tot number of simultaneously 
	 *plottable functions. Eventually the user may change 
	 *this amount, at their own RAM card's risk.
	 */
	int MAX_INSERTABLE_FUNCTIONS  = 3;


	/**
	 * List of Observers observing this Calculator.
	 */
	private ArrayList<Observer> observers;


	public Calculator() {

		this.functions = new ArrayList<FunctionIF>();
		this.observers = new ArrayList<Observer>();
	}


	/**
	 * Parse an expression and add the function object
	 * @param stringExpression
	 * @return
	 */
	public FunctionIF addFunction(String stringExpression) {

		FunctionIF f = buildFunction(stringExpression);

		//reject null functions 
		if(f==null) {
			return null;
		}
		
		//reject function if it's already in the list
		for(FunctionIF function : functions) {
			if(function.getExpression().toLowerCase().equals(f.getExpression().toLowerCase())) {
				return null;
			}
		}
		
		return addFunction(f);

	}


	/**
	 * just add a ready-made function object
	 * @param function
	 * @return
	 */
	public FunctionIF addFunction(FunctionIF function) {
		//reject any function if max amount is exceeded
		if(functions.size()+1>MAX_INSERTABLE_FUNCTIONS) {
			ArrayList<Object> a = new ArrayList<Object>();
			a.add(null);
			a.add("INSERTED_MAX");
			a.add(MAX_INSERTABLE_FUNCTIONS);
			notifyObservers(a);
			
			return null;
		}
		//reject null functions
		if (function == null) {
			return null;
		}
		
		
		//simplify the function before adding it
		function = function.getSimplified();
		
		//add the function to this Calculator
		this.functions.add(function);
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(function);
		a.add("ADDED");
		notifyObservers(a);

		return function;
	}


	/**
	 * Remove a FunctionIF from the list
	 * @param f
	 */
	public void removeFunction(FunctionIF f) {
		this.functions.remove(f);
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(f);
		a.add("DELETED");
		notifyObservers(a);
	}

	/**
	 * @overload
	 * Remove a FunctionIF by its index
	 */
	public void removeFunction(int index) {
		ArrayList<Object> a = new ArrayList<Object>();
		removeFunction(functions.get(index));
	}


	/**
	 * @overload
	 * Remove a FunctionIF by its expression
	 */
	public void removeFunction(String expression) {

		for(int i=0; i< functions.size(); i++) {
			if(functions.get(i).getExpression().toUpperCase().equals(expression)) {
				removeFunction(functions.get(i));
			}
		}
	}

	//	public void Derivative(String expression) {
	//		this.addFunction(expression);
	//		
	//	}


	/**
	 * add an observer to this Calculator.s
	 */
	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);

	}


	/**
	 * remove an observer from this Calculator.s
	 */
	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);

	}

	/**
	 * Tell all of the Observers what on Earth happened with the functions.
	 */
	@Override
	public void notifyObservers(ArrayList<Object> message) {
		for(Observer observer : observers) {
			observer.update(message);
		}
	}

	/**
	 * To build a functionIF from a String
	 * @throws SyntaxException 
	 */
	public FunctionIF buildFunction(String stringExpression){

		//call the parser and build a function from the string-expression
		FunctionIF f = null;
		try {
			f = Parser.parseAndbuild(stringExpression);
		} catch (SyntaxException | IllegalArgumentException e) {
			//notify all of the observers that the expression entered was not valid:
			ArrayList<Object> messages = new ArrayList<Object>();
			messages.add(null);
			messages.add("SYNTAX_ERROR");
			notifyObservers(messages);
		} catch(ArithmeticException e) {
			ArrayList<Object> messages = new ArrayList<Object>();
			messages.add(null);
			messages.add("ARITHMETIC_ERROR");
			notifyObservers(messages);	
		}
		
		
		
		return f;
	}

}