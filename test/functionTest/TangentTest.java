package functionTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.core.FunctionIF;
import model.functions.Tangent;
import parser.Parser;
import parser.SyntaxException;
/**
 * 
 * This class tests the Tangent (getValue + getDerivative)
 *
 */
public class TangentTest {
	private static final double DELTA = 1e-15;
	private  Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;		
		System.out.println("START UNIT TEST CALLED " + TangentTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}
	
	@Test
	public void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Tangent tan = new Tangent(o);
		logger.info("Running scenario tan(Math.PI/4) = 1");
		assertEquals("Result ", 1, tan.getValue(Math.PI/4), DELTA); 
	}

	@Test
	public void testGetDerivative() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Tangent tan = new Tangent(o);
		FunctionIF derivate = Parser.parseAndbuild("1/cos(x)^2");
		logger.info("Running scenario derivative of tan(x)");
		assertEquals("Result ", derivate.toString(), tan.getDerivative().getSimplified().toString());
	}
	
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + TangentTest.class.getSimpleName());
	}
}
