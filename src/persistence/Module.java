package persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A (persistence/settings) Module is basically a 
 * persistent Dictionary/Map that can also notify ModuleListeners of a change.
 *
 */

public class Module extends TextFile {

	/**
	 * This is the path to the directory where modules are stored.
	 */
	public final static String PATH_TO_MODULES_DIR = "modules";
	
	/**
	 * This Module's name
	 */
	String name;
	
	/**
	 * This Module's Listeners
	 */
	ArrayList<ModuleListener> listenersList;
	
	public Module(String name) {
		super(PATH_TO_MODULES_DIR+File.separator+name);
		this.name = name;
		listenersList = new ArrayList<ModuleListener>();
	}
	
	/**
	 * Sets or re-sets a key-value pair.
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {

		//get the old value of the key
		String oldValue = get(key);
		
		
		//get this file's text
		String text = read();
		
		//if it's the first time you're putting this key in, add a new key-value line
		if(oldValue==null) {
			text+=key+" : "+value+"\n";
		}else {
			//else replace oldValue with new value
			text = text.replace(key+" : "+oldValue, key+" : "+value);
		}
		
		//push changes
		write(text);
		
		//notify listeners of this module's change
		notifyModuleListeners(key, value);
		
	}
	
	
	/**
	 * Reference a WHOLE TEXT FILE as the value associated to a key.
	 * 
	 * get(String key), if invoked on a key associated to a file,
	 * will return the whole text-contents of that file.
	 * 
	 * @overload
	 * @param key
	 * @param referencedFile
	 */
	public void put(String key, File referencedFile) {
		put(key, "contentsOf("+referencedFile.getPath()+")");
	}
	
	
	
	/**
	 * Returns the value of a key. Returns null if key doesn't exist.
	 * @param key
	 * @return
	 */
	public String get(String key) {
		//get this file's text
		String text = this.read();
		String value = null;
		try {
			//try matching the pattern: key : value\n 
			Pattern pattern = Pattern.compile(key+" : (.*?)\n");
			
			Matcher matcher = pattern.matcher(text);
			matcher.find();
			
			//get the value
			value = matcher.group(1);
			
			//check if value is just a plain value, or it's actually a reference to the contents of a file
			if(value.contains("contentsOf(")) {
				String filePath = value.replace("contentsOf(", "").replace(")", "").trim();
				//try reading the file and returning its text-contents
				return FileIO.readFile(filePath);
			}
			
			
		}catch(Exception e) {/*do nothing*/}
		
		return value;
	}
	
	
	/**
	 * Removes existing key
	 * @param key
	 */
	public void remove(String key) {
		String value = get(key);
		if(value==null) {
			return; //no key to remove
		}
		String newText = read().replace(key+" : "+value+"\n", "");
		write(newText);
		
		//notify listeners of this module's change
		notifyModuleListeners(key, null);
	}

	

	private void notifyModuleListeners(String key, String newVal) {
		for(ModuleListener listener : listenersList) {
			listener.dealWithModuleUpdate(this);
		}
	}
	
	public void addListener(ModuleListener moduleListener) {
		listenersList.add(moduleListener);
		moduleListener.dealWithModuleUpdate(this);
	}
	
    public void removeListener(ModuleListener moduleListener) {
		listenersList.remove(moduleListener);
	}
	
	
	public HashMap<String, String> getKeyValMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		
		try {
			String[] lines = read().split("\n");
			for(String line : lines) {
				String key = line.split(" : ")[0];
				map.put(key, get(key));
			}
		}catch(Exception e) {
			
		}
		
		
		return map;
	}
    
	
	
	
	

}
