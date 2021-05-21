/**
 * 
 */
package controller;

import java.util.ArrayList;

/**
 * @author user
 *
 */
public interface Observable {
	
	
	/**
	 * Add a new Observer to this Observable.
	 * @param observer
	 */
	public void addObserver(Observer observer);
	
	/**
	 * Remove an old Observer from this Observable.
	 * @param observer
	 */
	public void removeObserver(Observer observer);
	
	/**
	 * Notify Observer(s) of change. 
	 * Calls Observers' update(ArrayList<Object> messages)
	 * @param messages
	 */
	
	public void notifyObservers(ArrayList<Object> messages);
	
	//Hmmmm... maybe later if async updates are needed?
	//public void setMessage(ArrayList<Object> a);
	
	
}
