package infra;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static WebDriver driver = null;

    public static WebDriver getDriver() throws MalformedURLException {
        Browser browser = Browser.valueOf(System.getProperty("browser").toUpperCase());
        System.out.println(browser);
//        if (driver == null) {
//            final OS currentOS = OS.current();
//            System.out.println(currentOS);
//            final String binPath = String.format("%s/bin/%s%s", System.getProperty("user.dir"), browser.driverPathInBin, currentOS.driverFilenameEnding);
//            if(ConfigurationManager.getInstance().getConfig("isRemoteRun").asFlag()){
//                Capabilities cap = new DesiredCapabilities();
//                driver =  new RemoteWebDriver(new URL("http://192.168.3.69:4444/wd/hub"), cap);
//            }else {
//                switch (browser) {
//                    case CHROME:
//                        System.setProperty("webdriver.chrome.driver", binPath);
//                        driver = new ChromeDriver();
//                        break;
//                    case FIREFOX:
//                        //FIXME
//                        break;
//                }
//            }
//
//
//            driver.manage().window().maximize();
//        }
        return driver;
    }
}