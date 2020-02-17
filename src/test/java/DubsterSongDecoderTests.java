import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DubsterSongDecoderTests {
    @Test
    public void test1() {
        assertEquals("ABC", new Dubster().songDecoder("WUBWUBABCWUB"));
    }

    @Test
    public void test2()
    {
        assertEquals("R L", new Dubster().songDecoder("RWUBWUBWUBLWUB"));
    }

    @Test
    public void test3() {
        assertEquals("", new Dubster().songDecoder("WUBWUBWUB"));
    }
}
