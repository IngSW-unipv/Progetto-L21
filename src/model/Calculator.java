package model;

import java.util.ArrayList;
import model.functions.FunctionIF;
import model.functions.StackFunction;

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
	 * To add a FunctionIF in the List 
	 * @param stringExpression
	 * @return
	 */
	public FunctionIF addFunction(String stringExpression) {
		
		//check if max amount has been reached
		if(functions.size()+1>MAX_INSERTABLE_FUNCTIONS) {
			return null;
		}
		
		StackFunction f = new StackFunction(stringExpression);
		this.functions.add(f);
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(f);
		a.add("ADDED");
		notifyObservers(a);
		return f;
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
			if(functions.get(i).getExpression().equals(expression)) {
				removeFunction(functions.get(i));
			}
		}
	}
	
	

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
	
	

}