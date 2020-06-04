package Infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class InfrastructureSetup {
    private static InfrastructureSetup instance = null;


    private WebDriver driver;
    
    static String driverPath = String.format("%s/bin/", System.getProperty("user.dir"));


    final public String bookingMainPage = "https://www.booking.com/";


    public static InfrastructureSetup getInstance() {
        if (instance == null) {
            instance = new InfrastructureSetup();
        }
        return instance;
    }

    public WebDriver setDriver(Browser browserType) {
        if (driver == null) {
            if (browserType.equals(Browser.CHROME)) {
                return driver = initChromeDriver();
            } else if (browserType.equals(Browser.FIREFOX)) {
                return driver = initFirefoxDriver();
            } else {
                System.out.println("browser : " + browserType + " is invalid, launching Chrome as browser by default");
                return driver = initChromeDriver();
            }
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        if (Objects.equals(OS.currentOS(), OS.WINDOWS)) {
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        } else if (Objects.equals(OS.currentOS(), OS.LINUX)) {
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
        } else {
            throw new RuntimeException("os undefined");
        }
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(bookingMainPage);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }


    private WebDriver initFirefoxDriver() {
        if (Objects.equals(OS.currentOS(), OS.WINDOWS)) {
            System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        } else if (Objects.equals(OS.currentOS(), OS.LINUX)) {
            System.setProperty("webdriver.chrome.driver", driverPath + "geckodriver");
        } else {
            throw new RuntimeException("os undefined");
        }
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(bookingMainPage);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
