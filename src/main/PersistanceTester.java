package main;

import persistence.Module;
import persistence.ModuleManager;

public class PersistanceTester {

	public static void main(String[] args) {
		
		//get the contents (key:value pairs) of a module
		Module module = ModuleManager.getInstance().getModule("graph");
		System.out.println(module.getKeyValMap());
		
		//try loading a NEW module dynamically
		//Module testModule = ModuleManager.getInstance().getModule("customFunctions");
		//System.out.println(testModule);
		
		//testModule.put("f", "x*sin(x)");
		
		System.out.println(ModuleManager.getInstance().getModule("customFunctions").get("f"));
		
		
		
	}

}
