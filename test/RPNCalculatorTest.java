import org.junit.Test;
import static org.junit.Assert.*;

public class RPNCalculatorTest {
    RPNCalculator calc = new RPNCalculator();

    @Test
    public void testEnter() {
        assertEquals(3.0, calc.enter(3.0), 0.0001);
    }

    @Test
    public void testPop() {
        calc.enter(3.0);
        assertEquals(3.0, calc.pop(), 0.0001);
    }

    @Test
    public void testDiv() throws CalculatorException {
        calc.processLine("8 2 /");
        assertEquals(4.0,calc.pop(), 0.0001);
    }

    @Test
    public void testPow() throws CalculatorException {
        calc.processLine("4 3 ^");
        assertEquals(64,calc.pop(), 0.0001);
    }

    @Test
    public void testLog2() throws CalculatorException {
        calc.processLine("8 log");
        assertEquals(2.0794,calc.pop(), 0.0001);
    }

    @Test
    public void testCos() throws CalculatorException {
        calc.processLine("0 cos");
        assertEquals(1, calc.pop(), 0.0001);
    }

    @Test
    public void testLogLong() throws CalculatorException {
        calc.processLine("8 log 2 log /");
        assertEquals(3,calc.pop(), 0.0001);
    }

}
