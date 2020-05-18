package infra;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserSupplier {
    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) {
            final OS currentOs = OS.current();
            final Browser browser = ConfigurationManager.getInstance().browser;
            final String binPath = String.format("%s/bin/%s%s",
                    System.getProperty("user.dir"),
                    browser.driverPathInBin,
                    currentOs.driverFilenameEnding
            );
            if (ConfigurationManager.getInstance().getConfig("remoteRun").asFlag()) {
                Capabilities caps = new DesiredCapabilities();
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.3.69:4444/wd/hub"), caps);
                } catch (MalformedURLException murl) {
                    throw new RuntimeException(murl.getMessage());
                }

            } else {
                switch (browser) {
                    case CHROME:
                        System.setProperty("webdriver.chrome.driver", binPath);
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        //FIXME
                        break;
                }
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

}
