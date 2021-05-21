package persistance;

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
	
	public static ModuleManager getInstance() {
		if(instance==null) {
			instance = new ModuleManager();
		}
		return instance;
	}
	
	
	
	public Module getModule(String name) {
		return loadedModulesMap.get(name);
	}
	
	
	
	
	
	
	
	
	

}
