package model.functions;


//This is a helper class that manages the "building-block" 
//functions that can be referenced in StackFunction's 
//expression.
public class SupportedFunctions {
	
	
	//hardcoded list of supported functions
	public static String[] functionNames = new String[] {"sin", "cos", "tan", "exp"};
	
	
	//evaluate a predefined function at a given point
	public static Double getValue(String functionName, double x) {
		switch(functionName) {
		case "sin":
			return Math.sin(x);
		case "cos":
			return Math.cos(x);
		case "tan":
			return Math.tan(x);
		}
		
		//default 
		return null;
	}
	
	
	//check if a function is supported
	public static boolean isFunction(String functionName) {
		for(String funcName : functionNames) {
			if(funcName.equals(functionName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	

}
