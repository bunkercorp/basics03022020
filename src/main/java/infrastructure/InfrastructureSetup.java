package infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class InfrastructureSetup {
    private WebDriver driver = null;
    static String driverPath = String.format("%s/bin/", System.getProperty("user.dir"));


    public WebDriver setDriver(String browserType, String appURL) {
        if (driver == null) {
            if ("chrome".equals(browserType)) {
                return driver = initChromeDriver(appURL);
            } else if ("firefox".equals(browserType)) {
                return driver = initFirefoxDriver(appURL);
            } else {
                System.out.println("browser : " + browserType + " is invalid, launching Chrome as browser by default");
                return driver = initChromeDriver(appURL);
            }
        }
        return driver;
    }

    private static WebDriver initChromeDriver(String appURL) {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver initFirefoxDriver(String appURL) {
        System.out.println("Launching Firefox browser..");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }
}
