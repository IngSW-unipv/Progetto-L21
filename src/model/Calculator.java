package model;

import java.util.ArrayList;
import model.functions.FunctionIF;
import model.functions.StackFunction;

/**
 * This is Facade Controller 
 * @author user
 *
 */

public class Calculator implements Observable{
	
	/**
	 * Lit of FunctionIF insert by user
	 */
	private ArrayList<FunctionIF> functions;
	
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
		
		StackFunction f = new StackFunction(stringExpression);
		this.functions.add(f);
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(f);
		a.add("Funzione aggiunta");
		notifyObservers(a);
		return f;
	}
	
	/**
	 * Remove the FunctionIF
	 * @param f
	 */
	public void removeFunction(FunctionIF f) {
		
		this.functions.remove(f);
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(f);
		a.add("Funzione rimossa");
		notifyObservers(a);
	}
	
	

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
		
	}

	@Override
	public void notifyObservers(ArrayList<Object> o) {
		for(Observer oo : observers) {
			oo.update(o);
		}
	}
	
	

}
