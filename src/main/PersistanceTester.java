package main;

import persistance.Module;

public class PersistanceTester {

	public static void main(String[] args) {
		
		Module module=  new Module("graph");
		module.create();
		
		//module.put("key", "value");
		
		System.out.println(module.get("key"));
		
	}

}
