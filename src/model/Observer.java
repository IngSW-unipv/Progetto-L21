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
     * Notify the observer that the sequence changed.
     */
	public void update(ArrayList<Object> a);

}
