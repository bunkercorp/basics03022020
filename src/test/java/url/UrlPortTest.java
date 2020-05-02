package url;

import hw12.URL;
import hw12.urlExeption.UrlException;
import org.junit.Assert;
import org.junit.Test;

public class UrlPortTest {

    @Test
    public void testPortIsNotSecure() throws UrlException {
        URL url = new URL.Composer("user-data.hillel.it").isSecure(false).compose();
        Assert.assertEquals("Default port for http is 80",
                80, url.getPort());
    }

    @Test
    public void testPortIsSecure() throws UrlException {
        URL url1 = new URL.Composer("user-data.hillel.it").isSecure(true).compose();
        Assert.assertEquals("Default port for http is 443",
                443, url1.getPort());
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
        Assert.assertEquals("Incorrect port",0,url1.getPort());
    }

}
