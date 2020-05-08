package homeWork12;
import homeWork12.urlExeption.UrlException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UrlPortTest {

    @Test
    public void testPortIsNotSecure() throws UrlException {
        URL url = new URL.Composer("user-data.hillel.it").isSecure(false).compose();
        Assert.assertEquals(80, url.getPort(),"Default port for http is 80");
    }

    @Test
    public void testPortIsSecure() throws UrlException {
        URL url1 = new URL.Composer("user-data.hillel.it").isSecure(true).compose();
        Assert.assertEquals(443, url1.getPort(),"Default port for http is 443");

    }

    @Test
    public void testIncorrectMinPort() {
        try {
            URL url1 = new URL.Composer("user-data.hillel.it").port(-1).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(),"Invalida port number : " + -1);
        }
    }

    @Test
    public void testIncorrectMaxPort(){
        try {
            URL url1 = new URL.Composer("user-data.hillel.it").port(65537).compose();
        } catch (UrlException e) {
            Assert.assertEquals(e.getMessage(),"Invalida port number : " + 65537);
        }
    }

    @Test
    public void correctMinPort() throws UrlException {
        URL url1 = new URL.Composer("user-data.hillel.it").port(0).compose();
        Assert.assertEquals(0,url1.getPort(),"Incorrect port");
    }

}
