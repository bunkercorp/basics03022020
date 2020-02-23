import org.junit.Test;

import static org.junit.Assert.*;

public class JadenTextTests {

    @Test
    public void test() {
        assertEquals("toJadenCase doesn't return a valide JadenCase String! try again please :)",
                JadenCase.toJadenCase("most trees are blue"), "Most Trees Are Blue");
    }

    @Test
    public void testNullArg() {
        String text = null;
        assertNull("Must return null when the arg is null", JadenCase.toJadenCase(text));
    }

    @Test
    public void testEmptyArg() {
        assertNull("Must return null when the arg is null", JadenCase.toJadenCase(""));
    }
}
