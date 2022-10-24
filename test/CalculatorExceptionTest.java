import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorExceptionTest {

    @Test
    public void testDefaultConstructor() {
        CalculatorException e = new CalculatorException();
        assertEquals("CalculatorException: ", e.toString());
    }

    @Test
    public void testConstructor() {
        CalculatorException e = new CalculatorException("Error");
        assertEquals("CalculatorException: Error", e.toString());
    }
}
