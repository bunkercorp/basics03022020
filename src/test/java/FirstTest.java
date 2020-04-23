

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void testBasic() {
        String str = System.getProperty("lorem");
        System.out.println(str);
        Assert.assertEquals(str,"Ipsum");
    }
}
