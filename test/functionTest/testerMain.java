package functionTest;

import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;

public class testerMain {

	public static void main(String[] args) throws SyntaxException {
		FunctionIF derivate = Parser.parseAndbuild("(1/(x*0.6931471805599453))");

	}

}
