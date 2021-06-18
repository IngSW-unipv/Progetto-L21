package tests;

import java.util.ArrayList;

import persistence.Module;
import persistence.ModuleListener;
import persistence.ModuleManager;

public class PersistanceTester {
	
	/**
	 * This project makes use of a simple inbuilt "framework" to manage
	 * and store pieces of information that need to survive beyond
	 * a runtime session.
	 * 
	 * You can find it all in the package: "persistence".
	 * 
	 * 
	 * HOW TO USE IT:
	 * 
	 * The system is organized in Modules. Each Module can be 
	 * thought of as a persistent and observable Map/Dictionary.
	 * 
	 * 
	 * A MODULE HAS THE FOLLOWING METHODS: 
	 * 
	 * 1) put(String key, String value): inserts a key-value pair
	 * in the module. If the added key was already in the Module,
	 * its value gets OVERWRITTEN.
	 * 
	 * 
	 * 2) put(String key, File referencedFile): instead of a value,
	 * there's a reference to a (text) file. The get method
	 * called for such a key will return the contents of the 
	 * referenced file. 
	 * 
	 * [Side-note: since a Module is itself a TextFile, you can even auto-reference its contents!]
	 * 
	 * 
	 * 3) get(String key) : returns the value associated to a key.
	 * In case the value is a referenced file, it returns 
	 * the contents of that referenced file.
	 * 
	 * 
	 * 4) remove(String key): removes a key and its associated value.
	 * 
	 * 
	 * NB: The methods put() and remove() also notify any 
	 * ModuleListeners that where added to the Module. 
	 * 
	 * 
	 * 5) getKeyValMap() : returns a HashMap of key : values, that 
	 * can be easily read through the usual HashMap.keySet() method.
	 * 
	 * 
	 * 6) addListener(ModuleListener moduleListener) : adds a ModuleListener 
	 * to a Module.
	 * 
	 * 7) removeListener(ModuleListener moduleListener) : removes
	 * a ModuleListener from a Module.
	 * 
	 * 8) getListeners(): Returns a list of all of the current listeners of this Module.

	 * 
	 * 
	 * METHODS OF MODULEMANAGER:
	 * 
	 * ModuleManager is a singleton Class that loads and 
	 * manages Modules.
	 * 
	 * getInstance(): gets the static instance of ModuleManager.
	 * 
	 * getModule(String name): gets a Module by its name.
	 * In case it wasn't loaded, or it didn't exists, getModule
	 * will also load/create-and-load the Module. In case 
	 * it had to be created, any new Module will be empty, 
	 * of course.
	 * 
	 * getModules(): Returns a list of all of the loaded Modules.
	 * Can be used for debugging purposes, and
	 * to determine a posteriori what Modules are being 
	 * created and used in a project.
	 * 
	 * 
	 * METHODS OF MODULELISTENER:
	 * 
	 * ModuleListener is an interface.
	 * 
	 * dealWithModuleUpdate(Module moduleThatGotUpdated): this 
	 * method gets called by a Module on all of its listeners, 
	 * whenever any addition/deletion occurs in the Module's
	 * contents.
	 * 
	 * dealWithSingularUpdate(String key, String value): this
	 * method is meant to be called internally to a ModuleListener.
	 * Maybe inside of a loop that goes through the key-vals of
	 * the Module that was changed, (making use of the getKeyValMap()
	 * method of Module).
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {
		
		//get the key:value pairs map of a module
		Module module = ModuleManager.getInstance().getModule("graph");
		//print it:
		System.out.println("Map of the \""+module.getName()+"\" module:");
		System.out.println(module.getKeyValMap());
		System.out.println("--------------------");
		
		//try loading a NEW module dynamically
		Module myNewModule = ModuleManager.getInstance().getModule("myNewModule");
		System.out.println(myNewModule.getPath()+" was created and loaded.");
		System.out.println("---------------------");
		
		
		//add a key-val pair to it:
		myNewModule.put("ciao mondo!", "hello world!");
		//print the value:
		System.out.println(myNewModule.get("ciao mondo!"));
		System.out.println("---------------");
		
		//add a reference to the Module file itself as a value
		myNewModule.put("myFileReference", myNewModule);
		//print out the new key-val map for myNewModule
		System.out.println("key-val map for "+myNewModule.getName()+":");
		System.out.println(myNewModule.getKeyValMap());
		System.out.println("----------------");
		
		//get the contents of the referenced file
		System.out.println("contents of the Module's file:");
		System.out.println(myNewModule.get("myFileReference"));
		System.out.println("--------------");
		
		
		//add a listener to the module
		myNewModule.addListener(new ModuleListener() {
			@Override
			public void dealWithModuleUpdate(Module moduleThatGotUpdated) {	}

			@Override
			public void dealWithSingularUpdate(String key, String value) {}
			
			@Override
			public String toString(){
				return "AnonymousListener";
			}
			
		});
		
		
		//get a list of all of the Modules currenly in use 
		System.out.println("All of the Modules:");
		ArrayList<Module> modules = ModuleManager.getInstance().getModules();
		System.out.println(modules);
		System.out.println("------------");
		
		
		//for each Module, get every one of its listeners
		System.out.println("listeners of each Module:");
		for(Module modulo : modules) {
			System.out.println(modulo+": "+modulo.getListeners());
		}
		
	}

}
