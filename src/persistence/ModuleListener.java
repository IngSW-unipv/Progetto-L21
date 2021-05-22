package persistence;

public interface ModuleListener{

	/**
	 * this method gets called by a Module on all of its listeners, 
	 * whenever any addition/deletion occurs in the Module's
	 * contents.
	 * 
	 * @param moduleThatGotUpdated
	 */
	public void dealWithModuleUpdate(Module moduleThatGotUpdated);
	
	/**
	 * this method is meant to be called internally to a ModuleListener.
	 * Maybe inside of a loop that goes through the key-vals of
	 * the Module that was changed, (making use of the getKeyValMap()
	 * method of Module).
	 * 
	 * @param key
	 * @param value
	 */
	public void dealWithSingularUpdate(String key, String value);
	
	
}
