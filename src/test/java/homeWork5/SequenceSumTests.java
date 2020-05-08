package homeWork5;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SequenceSumTests {
    @Test
    public void testBasic() {
        assertEquals("0+1+2+3+4+5+6 = 21", SequenceSum.showSequence(6));
        assertEquals("0+1+2+3+4+5+6+7+8+9+10 = 55", SequenceSum.showSequence(10));
        assertEquals("-15 < 0",SequenceSum.showSequence(-15));
        assertEquals("0 = 0",SequenceSum.showSequence(0));
    }
}
