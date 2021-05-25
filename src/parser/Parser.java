package parser;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.scijava.parsington.ExpressionParser;

import model.core.FunctionIF;

/**
 * Something about the general philosophy of this popular 
 * OO approach to mathematics:
 * 
 * 
 * The top-container, or top-level OperationIF, (aka:  the "oggettone"),
 * knows its general structure, and applies it to its components,
 * calling their methods and combining them suitably. 
 * 
 * This process can carry on recursively, with each sub-container
 * knowing its structure and combining its sub-elements suitably,
 * till a point is reached where the elements to be combined are
 * atomic (ie: the Base Case).
 * 
 * 
 * 
 * Eg: if the top container is a Sum, and the operation requested
 * of it is differentiation, the "oggettone" returns the Sum 
 * of the derivatives of its components. 
 * 
 * Each component knows what its own derivative is, if not 
 * directly, indirecly: by calling the getDerivative method
 * of its components.
 * 
 * In this scenario (differentiaton), the process halts 
 * when the component is direcly aware of what its derivative is, 
 * ie: when the component is a Constant or a Variable. 
 * This is the Base Case.
 *  
 * 
 * 
 * 
 *
 */

public class Parser {
	
	/**
	 * Parse a full mathematical expression, and build a 
	 * composite FunctionIF-object that can be called and 
	 * referenced by any outsider that knows FunctionIF.
	 * 
	 * @param expression
	 * @return
	 * @throws SyntaxException 
	 */
	
    public static FunctionIF parseAndbuild(String expression) throws SyntaxException {
		
		//parse a given expression to obtain a postfix list of tokens
		ArrayList<String> postfixList = parsePostfixList(expression);
		
		//IMPORTANT: make a new builder
		Builder builder = new Builder();
		
		//build the composite function object out of the list of tokens
		
		FunctionIF oggettone;
		
		try {
			oggettone = builder.build(postfixList);
		} catch (EmptyStackException e) {
			throw new SyntaxException();
		}
		
		if(oggettone == null)
			throw new SyntaxException();
		
		return oggettone;
	}
	
	
	
	
	/**
	 * Returns a postfix list of tokens given a valid expression.
	 * The list follows conventions internal to this program.
	 * @param expression
	 * @return
	 */
	public static ArrayList<String> parsePostfixList(String expression) {
		
		//This is an access to an external library to be isolated from the rest of the project.
		LinkedList<Object> toBeCleanedUp = new ExpressionParser().parsePostfix(expression);
		
		//ADAPTER: clean up the list obtained from the external library 
		ArrayList<String> postfixList = new ArrayList<String>();
		
		for(Object token : toBeCleanedUp) {
			//remove library-specific useless extra tokens.
			if(token.toString().matches("\\(\\d*\\)")) {
				continue; //(1)
			}
			
			if(token.toString().equals("<Fn>")) {
				//convert this external end-of-function symbol to the projects own one.
				postfixList.add(Builder.END_OF_FUNCTION);
				continue;
			}
			
			
			postfixList.add(token.toString());
		}
		
		
		//return a postfix list of tokens that is compatible with the conventions required by the rest of the project.
		return postfixList;
	}
	
	
	
	
	
	
	
	

}
