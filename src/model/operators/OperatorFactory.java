package model.operators;

import model.core.FunctionIF;
import model.numbers.Constant;

public class OperatorFactory {

	public static FunctionIF buildOperator(String operator, FunctionIF leftOperand, FunctionIF rightOperand) {
		
		switch(operator.toUpperCase()) {
		
		case "PRODUCT":
		case "*":
			
			if(leftOperand.equals(new Constant(0)) || rightOperand.equals(new Constant(0))  ) {
				return new Constant(0);
			}
			
			return new Product(leftOperand, rightOperand);
		case "DIVISION":
		case "/":
			
			
		
		
		}
		
		return null;
	}
	
	
}
