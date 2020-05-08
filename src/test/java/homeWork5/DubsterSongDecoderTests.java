package homeWork5;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class DubsterSongDecoderTests {
    @Test
    public void test1() {
        assertEquals("ABC", Dubster.songDecoder("WUBWUBABCWUB"));
    }

    @Test
    public void test2() {
        assertEquals("R L", Dubster.songDecoder("RWUBWUBWUBLWUB"));
    }

    @Test
    public void test3() {
        assertEquals("", Dubster.songDecoder("WUBWUBWUB"));
    }
}
