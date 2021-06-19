package functionTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.core.FunctionIF;
import model.functions.Logarithm;
import parser.Parser;
import parser.SyntaxException;

public class LogarithmTest {
	private static final double DELTA = 1e-15;
	private  Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;		
		System.out.println("START UNIT TEST CALLED " + LogarithmTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}

	@Test
	public void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Logarithm log = new Logarithm(o, 2);
		logger.info("Running scenario log2(2) = 1");
		assertEquals("Result ", 1, log.getValue(2), DELTA); 
	}

	@Test
	public void testGetDerivative() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Logarithm log = new Logarithm(o, 2);
		String derivate = "(1.0/(x*0.6931471805599453))"; // use string for parser limit
		logger.info("Running scenario derivative of log2(x)");
		assertEquals("Result ", derivate, log.getDerivative().getSimplified().toString()); 
	}
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + LogarithmTest.class.getSimpleName());
	}
}
