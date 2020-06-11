import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver() throws MalformedURLException {

        //final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        //System.setProperty("webdriver.chrome.driver", binPath);
//
//        ChromeOptions options = new ChromeOptions(); /*ChromeOptions is for firing Chrome with additional settings.*/
//        options.addArguments("--allow-file-access-from-files"); /*Allow Chrome to access files*/
//        //options.addArguments("user-data-dir="+System.getProperty("user.dir")+pathChrome);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        capabilities.setBrowserName("chrome");
//
//        //ChromeOptions options = new ChromeOptions();
//        //options.addArguments("--headless");
//
//        capabilities.setPlatform(Platform.ANY);
        //capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        if (driver == null) {

            driver = new RemoteWebDriver(new URL("http://192.168.0.231:4444/wb/hub"), DesiredCapabilities.chrome());


            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
    }
}
