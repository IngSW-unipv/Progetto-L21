package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NOT READY FOR FUNCTIONS YET, ONLY WORKS ON PLONOMIALS.
 * 
 *  
 * Takes a postfix list of tokens and spits out a sum of 
 * powers of x in its simplest form.  
 * 
 * 
 */

public class Simplifier {



	HashMap<Integer, Integer> coefficients;
	Stack<String> operands;


	public Simplifier() {
		operands = new Stack<String>();
		coefficients = new HashMap<>();
	}



	/**
	 * Turns a non-simplified postfix list of tokens to a simplified string that has to be parsed once more.
	 * @param postfixList
	 * @return
	 */

	public String simplifyExpression(ArrayList<String> postfixList) {

		//get the fully expanded expression
		String expandedExpression = expand(postfixList);

		//for each addendum in the expanded expression...
		for(String addendum : expandedExpression.split("\\+")) {

			
			//get the degree of the addendum
			int degree = getDegree(addendum.trim());
			
			//get the coefficient of the addendum
			int coeff = (int)getCoefficient(addendum.trim());

			//update the coefficient of that degree
			//coefficients[degree]+=coeff;
			Integer oldCoeff;

			oldCoeff = coefficients.get(degree);
			if(oldCoeff==null) {
				oldCoeff = 0;
			}

			coefficients.put(degree, oldCoeff+coeff);
		}


		//re-construct the simplified expression in a pretty form:
		String result = "";
		for(int degree : coefficients.keySet()) {
			
			int coeff = coefficients.get(degree);


			if(coeff!=0) {
				String coeffString = coeff+"*";

				if(coeff==1) {
					coeffString="";
				}


				if(degree==0) {
					result+=coeff+" + ";
					continue;
				}

				if(degree==1) {
					result+=coeffString+"x"+" + ";
					continue;
				}


				result+=coeffString+"x^"+degree+" + ";
			}
		}


		//return "0" if result is empty
		return result.trim().equals("")? "0" : result.substring(0, result.length()-2).trim();
	}





	/**
	 * Decides whether or not a token is an operator
	 * @param token
	 * @return
	 */
	private static boolean isOperator(String token) {
		if(token.matches("[+,\\-,*,/]")) {
			return true;
		}
		return false;
	}



	private static String distribute(String operator, String firstOperand, String secondOperand) {



		switch(operator) {
		case "+":
			return firstOperand+" + "+secondOperand;
		case "-":
			return firstOperand+" + (-"+secondOperand+")";	
		case "*":
			String result = "";


			if(firstOperand.split("\\+").length>1) {
				for(String addendum : firstOperand.split("\\+")) {
					result+= secondOperand+"*"+addendum+" + ";
				}
				return result.substring(0, result.length()-2);

			}



			if(secondOperand.split("\\+").length>1) {
				for(String addendum : secondOperand.split("\\+")) {
					result+= firstOperand+"*"+addendum+" + ";
				}
				return result.substring(0, result.length()-2);
			}

			return firstOperand+"*"+secondOperand;
		case "/":
			String res = "";
			try {
				for(String addendum : secondOperand.split("\\+")) {
					
					res+= addendum+"/"+firstOperand +" + ";
				}
				return res.substring(0, res.length()-2);
			}catch(Exception e) {
				return firstOperand+"/"+secondOperand;
			}	
		}


		return null;
	}


	/**
	 * Get the degree of a monomial term. ie: the power of the 
	 * x term.
	 * @param operand
	 * @return
	 */

	private static int getDegree(String operand) {

		//pattern that matches "x" terms
		Pattern pattern = Pattern.compile(".*?(x).*?");

		//counter to keep track of the power of x
		int counter = 0;

		//split by division-sign
		String[] parts = operand.split("/");

		//get the numerator term
		String numerator = parts[0];

		//match "x"s in the numerator term, for each x at the numerator, counter++
		Matcher macther = pattern.matcher(numerator);
		while(macther.find()) {
			counter++;
		}

		//match "x"s in the denominators, for each x, counter--
		for(int i =1; i<parts.length; i++) {
			Matcher matcher = pattern.matcher(parts[i]);
			while(matcher.find()) {
				counter--;
			}
		}


		return counter;
	}


	/**
	 * Get the coefficient of a monomial term.
	 * @param operand
	 * @return
	 */

	private static double getCoefficient(String operand) {

		//pattern: a number that can be negative
		Pattern pattern = Pattern.compile("\\-*\\d+");
		//split the operand by divide-signs
		String[] parts = operand.split("/");

		//initialize quotient to one 
		double quotient = 1;


		//get the coefficient at the numerator
		String[] multipliers = parts[0].split("\\*");

		for(String multiplier : multipliers) {
			Matcher matcher = pattern.matcher(multiplier);
			matcher.find();

			try {
				quotient *= Double.parseDouble(matcher.group(0));
			}catch(Exception e) {

			}

		}


		//keep dividing the numerator until there are divisors			
		for(int i =1; i<parts.length;i++) {
			Matcher matcher = pattern.matcher(parts[i]);
			matcher.find();

			try {
				quotient /= Double.parseDouble(matcher.group(0));
			}catch(Exception e) {

			}
		}


		return quotient;
	}



	private String expand(ArrayList<String> postfixList) {



		//this is the expression you get when you perform the operations in order and keep expanding everytime
		String expandedExpression = "";

		//for each token...
		for(String token : postfixList) {

			//token is an operator
			if(isOperator(token)) {
				String operandTwo = operands.pop();
				String operandOne = operands.pop();
				operands.push(distribute(token, operandTwo, operandOne));
				continue;
			}

			//token is an operand
			operands.push(token);
		}

		return operands.pop();
	}




	/**
	 * Tester
	 * @param args
	 */

	public static void main(String args[]) {

		ArrayList<String> postfix = Parser.parsePostfixList("x*x*x*(x+2) +1 + (x +1)*x");
		String simplified = new Simplifier().simplifyExpression(postfix);
		System.out.println("simplified: "+simplified);

	}







}
