import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberRotatorTests {
    private static void testing(long actual, long expected) {
        assertEquals(expected, actual);
    }
    @Test
    public void test() {
        testing(NumberRotator.rotate(38458215), 85821534);
        testing(NumberRotator.rotate(195881031), 988103115);
        testing(NumberRotator.rotate(896219342), 962193428);
        testing(NumberRotator.rotate(69418307), 94183076);
    }
}
