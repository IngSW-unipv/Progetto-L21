/**
 * 
 */
package functionTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.core.FunctionIF;
import parser.Parser;
import parser.SyntaxException;

/**
 * @author Angelo
 *
 */
public class DerivativeTest {
	private static final double DELTA = 1e-15;
	private  Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;		
		System.out.println("START UNIT TEST CALLED " + DerivativeTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}

	@Test
	public void testGetDerivative1() throws SyntaxException {
		FunctionIF function = Parser.parseAndbuild("x");
		FunctionIF derivate = Parser.parseAndbuild("1");
		logger.info("Running scenario derivative of x");
		assertEquals("Result ", derivate.toString(), function.getDerivative().getSimplified().toString());
	}
	
	@Test
	public void testGetDerivative2() throws SyntaxException {
		FunctionIF function = Parser.parseAndbuild("x^2");
		FunctionIF derivate = Parser.parseAndbuild("(2.0/x)*x^(2.0)");
		logger.info("Running scenario derivative of x^2");
		assertEquals("Result ", derivate.toString(), function.getDerivative().getSimplified().toString());
	}
	
	
	@Test
	public void testGetDerivative3() throws SyntaxException {
		FunctionIF function = Parser.parseAndbuild("x^3");
		FunctionIF derivate = Parser.parseAndbuild("(3.0/x)*x^(3.0)");
		logger.info("Running scenario derivative of x^3");
		assertEquals("Result ", derivate.toString(), function.getDerivative().getSimplified().toString());
	}
	
	@Test
	public void testGetDerivative4() throws SyntaxException {
		FunctionIF function = Parser.parseAndbuild("x^3+x^2");
		FunctionIF derivate = Parser.parseAndbuild("(3*x^(2.0)+(2*x))");// fix simplified division
		logger.info("Running scenario derivative of x^3+x^2");
		assertEquals("Result ", derivate.toString(), function.getDerivative().getSimplified().toString());
	}
	
	@Test
	public void testGetDerivative5() throws SyntaxException {
		FunctionIF function = Parser.parseAndbuild("x+ln(x)+3*sin(x)");
		FunctionIF derivate = Parser.parseAndbuild("(1+1/x+3*cos(x))");
		logger.info("Running scenario derivative of x+ln(x)+3*sin(x)");
		assertEquals("Result ", derivate.toString(), function.getDerivative().getSimplified().toString());
	}
	
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + DerivativeTest.class.getSimpleName());
	}
}
