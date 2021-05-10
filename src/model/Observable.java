/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author user
 *
 */
public interface Observable {

	public void addObserver(Observer observer);

	public void removeObserver(Observer observer);

	//public void setMessage(ArrayList<Object> a);

	public void notifyObservers(ArrayList<Object> a);

}
