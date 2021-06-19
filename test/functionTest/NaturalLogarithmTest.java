package functionTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.core.FunctionIF;
import model.functions.NaturalLogarithm;
import parser.Parser;
import parser.SyntaxException;

public class NaturalLogarithmTest {
	private static final double DELTA = 1e-15;
	private  Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;		
		System.out.println("START UNIT TEST CALLED " + NaturalLogarithmTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}

	@Test
	public void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		NaturalLogarithm ln = new NaturalLogarithm(o);
		logger.info("Running scenario log(Math.e) = 1");
		assertEquals("Result ", 1, ln.getValue(Math.E), DELTA); 
	}

	@Test
	public void testGetDerivative() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		NaturalLogarithm ln = new NaturalLogarithm(o);
		FunctionIF derivate = Parser.parseAndbuild("1/(x)");
		logger.info("Running scenario derivative of ln(x)");
		assertEquals("Result ", derivate.toString(), ln.getDerivative().getSimplified().toString()); 
	}
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + NaturalLogarithmTest.class.getSimpleName());
	}
}
