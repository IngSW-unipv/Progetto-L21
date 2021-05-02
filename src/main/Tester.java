package main;

import model.functions.StackFunction;

public class Tester {

	public static void main(String[] args) {
		
		//doesn't always respect PEMDAS!!!!!!
		System.out.println("result:");
		//StackFunction f = new StackFunction("1+sin(x)"); DA SISTEMARE LE FUNZIONI
		StackFunction f = new StackFunction("1+x*(5+x)");

 		System.out.println(f.getValue(2));

		
		
	}

}
