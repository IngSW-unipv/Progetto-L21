package model.functions;


/**
 * This is a helper class that manages the "building-block" 
 * functions that can be referenced in StackFunction's 
 * expression.
 */
public class SupportedFunctions {


	//hardcoded list of supported functions
	public static String[] functionNames = new String[] {"sin", "cos", "tan", "exp", "Log", "ln"};


	/**
	 * Evaluate a predefined function at a given point
	 * @param functionName
	 * @param x
	 * @return
	 */
	public static Double getValue(String functionName, double x) {
		switch(functionName) {
		case "sin":
			return Math.sin(x);
		case "cos":
			return Math.cos(x);
		case "tan":
			return Math.tan(x);
		case "Log":
			return Math.log10(x);
		case "ln" :
			return Math.log(x);
		case "exp" :
			return Math.exp(x);
		}

		//default 
		return null;
	}


	/**
	 * Check if a function is supported
	 * @param functionName
	 * @return
	 */
	public static boolean isFunction(String functionName) {
		for(String funcName : functionNames) {
			if(funcName.equals(functionName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}


}
