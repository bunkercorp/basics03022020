package homeWork5;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberRotatorTests {
    private static void testing(long actual, long expected) {
        assertEquals(expected, actual);
    }
    @Test
    public void test() {
        testing(NumberRotator.rotate(12), 21);
        testing(NumberRotator.rotate(195881031), 988103115);
        testing(NumberRotator.rotate(896219342), 962193428);
        testing(NumberRotator.rotate(69418307), 94183076);
    }

}
