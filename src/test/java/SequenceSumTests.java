import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceSumTests {
    @Test
    public void testBasic() {
        assertEquals("-100500 < 0", SequenceSum.showSequence(-100500));
        assertEquals("0 = 0", SequenceSum.showSequence(0));
    }
}
