package main;

import persistance.Module;
import persistance.ModuleManager;

public class PersistanceTester {

	public static void main(String[] args) {
		
		Module module = ModuleManager.getInstance().getModule("graph");
		
		System.out.println(module.getKeyValMap());
		
	}

}
