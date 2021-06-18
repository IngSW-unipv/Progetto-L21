/**
 * 
 */
package functionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
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
class CosineTest {
	private static final double DELTA = 1e-15;
	private Logger logger = Logger.getLogger("loggerTest");
	private static int countTest;
	
	@BeforeClass
	public static void startTestUnit() {
		countTest = 0;
		System.out.println("START TEST UNIT CALLED " + CosineTest.class.getSimpleName());
	}
	
	@Before
	public static void startTest() {
		System.out.println("Start test number " + countTest++);
	}
	
	/**
	 * Test method for {@link model.functions.Cosine#getValue(double)}.
	 * @throws SyntaxException 
	 */
	@Test
	void testGetValue() throws SyntaxException {
		FunctionIF o = Parser.parseAndbuild("x");
		Cosine cos = new Cosine(o);
		assertEquals("Result ", 1, cos.getValue(0), DELTA); 
	}

	/**
	 * Test method for {@link model.functions.Cosine#getDerivative()}.
	 */
	@Test
	void testGetDerivative() {
		fail("Not yet implemented");
	}
	
	@After
	public static void endTest() {
		System.out.println("End test number " + countTest);
	}

	@AfterClass
	public static void endTestUnit() {
		System.out.println("END TEST UNIT " + CosineTest.class.getSimpleName());
	}
}
