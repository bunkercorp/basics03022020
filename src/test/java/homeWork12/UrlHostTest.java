package homeWork12;
import homeWork12.urlExeption.UrlException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UrlHostTest {

    @Test
    public void testCorrectHost() throws UrlException {
        String expectedHost = "user-data.hillel.it";
        URL url1 = new URL.Composer(expectedHost).compose();
        Assert.assertEquals( expectedHost, url1.getHost(),"Incorrect host");
    }

    @Test
    public void testIncorrectHostFirstPart() {
        String host = ".hillel.it";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostFirstPartLength() {
        String host = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwer.hillel.it";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostSecondPart() {
        String host = "user-data..it";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostThirdPart() {
        String host = "user-data.hillel.";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testCorrectIp() throws UrlException {
        String host = "0.0.0.0";
        URL url1 = new URL.Composer(host).compose();
        Assert.assertEquals(host,url1.getHost(), "Incorrect host");
    }

    @Test
    public void testIncorrectHostIpFirstPart() {
        String host = "256.0.0.0";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostIpSecondPart() {
        String host = "0.256.0.0";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostIpThirdPart() {
        String host = "0.0.256.0";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }

    @Test
    public void testIncorrectHostIpForthPart() {
        String host = "0.0.0.256";
        try {
            URL url1 = new URL.Composer(host).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(), "Invalid host name :" + host);
        }
    }


}
