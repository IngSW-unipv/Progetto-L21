package persistence;

import java.io.File;
import java.util.HashMap;

/**
 * 
 * This class manages Modules.
 * (It is meant to be a singleton).
 *
 */
public class ModuleManager {
	
	private static ModuleManager instance = null;
	
	private static String PATH_TO_MODULES_DIR = Module.PATH_TO_MODULES_DIR;
	
	private HashMap<String, Module> loadedModulesMap;
	
	
	public ModuleManager() {
		//create a new map in RAM
		loadedModulesMap=  new HashMap<String, Module>();
		
		//assuming NO new Modules are gonna get created 
		//during runtime, you can load everything at the beginning.
		for(File file : new File(PATH_TO_MODULES_DIR).listFiles()) {
			loadedModulesMap.put(file.getName(), new Module(file.getName()));
		}
	}
	
	/**
	 * gets the static instance of ModuleManager.
	 * @return
	 */
	public static ModuleManager getInstance() {
		if(instance==null) {
			instance = new ModuleManager();
		}
		return instance;
	}
	
	
	/**
	 * Gets a Module by its name.
	 * In case it wasn't loaded, or it didn't exists, getModule
	 * will also load/create-and-load the Module. In case 
	 * it had to be created, any new Module will be empty, 
	 * of course.
	 * 
	 * @param name
	 * @return
	 */
	public Module getModule(String name) {
		
		Module module = loadedModulesMap.get(name);
		
		//if the module in question is not loaded yet:
		if(module==null) {
			
			//load the Module:
			module = new Module(name);
			loadedModulesMap.put(name, module);
			
			//if the module in question doesn't exist on the disk, create a new empty Module. 
			if(!module.exists()) {
				module.create();
			}
		}
		
		return module;
	}
	
	
	
	
	
	
	
	
	

}
