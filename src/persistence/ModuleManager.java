package persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * This class manages Modules.
 * (It is meant to be a singleton).
 *
 */
public class ModuleManager {

	private static ModuleManager instance = null;

	private static String PATH_TO_LOCAL_SETTINGS_MODULES_DIR = "modules";
	
	private static String PATH_TO_GENERAL_MODULES_DIR = "general_modules";
	
	
	private HashMap<String, Module> loadedModulesMap;


	private ModuleManager() {
		//create a new map in RAM
		loadedModulesMap=  new HashMap<String, Module>();

		//create the modules directory in case it doesn't exist yet
		File modulesDir = new File(PATH_TO_LOCAL_SETTINGS_MODULES_DIR);
		if(!modulesDir.exists()) {
			modulesDir.mkdir();
		}


		//load the modules that are present at launch-time
		
		//load the custom/local modules
		for(File file : new File(PATH_TO_LOCAL_SETTINGS_MODULES_DIR).listFiles()) {
			loadedModulesMap.put(file.getName(), new Module(PATH_TO_LOCAL_SETTINGS_MODULES_DIR, file.getName()));
		}
		
		//load the general/public modules
		for(File file : new File(PATH_TO_GENERAL_MODULES_DIR).listFiles()) {
			loadedModulesMap.put(file.getName(), new Module(PATH_TO_GENERAL_MODULES_DIR, file.getName()));
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
			module = new Module(PATH_TO_LOCAL_SETTINGS_MODULES_DIR, name);
			loadedModulesMap.put(name, module);

			//if the module in question doesn't exist on the disk, create a new empty Module, in the PATH_TO_LOCAL_SETTINGS_MODULES_DIR. 
			if(!module.exists()) {
				module.create();
			}
		}

		return module;
	}


	/**
	 * Returns a list of all of the loaded Modules.
	 * Can be used for debugging purposes, and
	 * to determine a posteriori what Modules are being 
	 * created and used in a project.
	 * @return
	 */
	public ArrayList<Module> getModules(){
		return new ArrayList<Module>(loadedModulesMap.values());
	}










}
