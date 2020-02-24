import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceSumTests {
    @Test
    public void testBasic() {
        assertEquals("0+1+2+3+4+5+6 = 21", SequenceSum.showSequence(6));
        assertEquals("0+1+2+3+4+5+6+7+8+9+10 = 55", SequenceSum.showSequence(10));
    }
}
