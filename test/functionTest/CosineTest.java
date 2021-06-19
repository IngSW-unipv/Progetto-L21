package functionTest;

import static org.junit.Assert.assertEquals;
import java.util.logging.Logger;
import org.junit.*;

import model.core.FunctionIF;
import model.functions.Cosine;
import parser.Parser;
import parser.SyntaxException;

/**
 * @author Angelo
 *
 */
public class CosineTest {
	private static final double DELTA = 1e-15;
	private Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;
		System.out.println("START UNIT TEST CALLED " + CosineTest.class.getSimpleName() + "\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Start test number " + ++countTest );
	}
	
	/**
	 * Test method for {@link model.functions.Cosine#getValue(double)}.
	 * @throws SyntaxException 
	 */
	@Test
	public void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Cosine cos = new Cosine(o);
		logger.info("Running scenario cos(0) = 1");
		assertEquals("Result ", 1, cos.getValue(0), DELTA); 
	}

	/**
	 * Test method for {@link model.functions.Cosine#getDerivative()}.
	 * @throws SyntaxException 
	 */
	@Test
	public void testGetDerivative() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Cosine cos = new Cosine(o);
		FunctionIF derivate = Parser.parseAndbuild("sin(x)*-1");
		logger.info("Running scenario derivative of cos(x)");
		assertEquals("Result ", derivate.toString(), cos.getDerivative().getSimplified().toString()); 
	}
	
	@After
	public void endTest() {
		System.out.println("End test number " + countTest + "\n");
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END UNIT TEST " + CosineTest.class.getSimpleName());
	}
}
