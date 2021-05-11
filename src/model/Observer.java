/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author user
 *
 */
public interface Observer {
	
	/**
     * Notify the observer of a change. 
     * Called by an Observable.
     */
	public void update(ArrayList<Object> messages);

}
