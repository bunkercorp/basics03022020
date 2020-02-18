import org.junit.Test;

import static org.junit.Assert.*;

public class JadenTextTests {

    @Test
    public void test() {
        assertEquals("toJadenCase doesn't return a valide JadenCase String! try again please  :)", JadenCase.toJadenCase("most trees are blue  &*90927 42789034 sdf 353 rtet-09et e0rt- erj"), "Most Trees Are Blue  &*90927 42789034 Sdf 353 Rtet-09et E0rt- Erj");
    }

    @Test
    public void testNullArg() {
        assertNull("Must return null when the arg is null", JadenCase.toJadenCase(null));
    }

    @Test
    public void testEmptyArg() {
        assertNull("Must return null when the arg is null", JadenCase.toJadenCase(""));
    }
}
