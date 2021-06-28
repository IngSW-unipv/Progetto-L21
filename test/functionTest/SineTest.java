package functionTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.core.FunctionIF;
import model.functions.Sine;
import parser.Parser;
import parser.SyntaxException;
/**
 * 
 * This class tests the Sine (getValue + getDerivative)
 *
 */
public class SineTest {
	private static final double DELTA = 1e-15;
	private  Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;		
		System.out.println("START UNIT TEST CALLED " + SineTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}
	
	/**
	 * Test method for {@link model.functions.Sine#getValue(double)}.
	 * @throws SyntaxException 
	 */
	@Test
	public void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Sine sin = new Sine(o);
		logger.info("Running scenario sin(0) = 0");
		assertEquals("Result ", 0, sin.getValue(0), DELTA); 
	}

	/**
	 * Test method for {@link model.functions.Sine#getDerivative()}.
	 * @throws SyntaxException 
	 */
	@Test
	public void testGetDerivative() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Sine sin = new Sine(o);
		FunctionIF derivate = Parser.parseAndbuild("cos(x)");
		logger.info("Running scenario derivative of sin(x)");
		assertEquals("Result ", derivate.toString(), sin.getDerivative().getSimplified().toString()); 
	}
	
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + SineTest.class.getSimpleName());
	}
}
