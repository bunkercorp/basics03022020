package setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class BrowserManager {
    public enum BrowserType {
        CHROME, FIREFOX, OPERA
    }

    private static BrowserManager instance;
    private WebDriver[] drivers = new WebDriver[3];

    private BrowserManager() {
    }

    public static BrowserManager getInstance() {
        if (instance == null) {        //если объект еще не создан
            instance = new BrowserManager();    //создать новый объект
        }
        return instance;        // вернуть ранее созданный объект
    }

    public WebDriver getDriver(BrowserType type, String url) {
        WebDriver driver = drivers[type.ordinal()];
        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().to(url);
            return driver;
        }

        switch (type) {
            case CHROME: {
                final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", binPath);
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                driver = new FirefoxDriver();
                break;
            }
            case OPERA: {
                final String binPath = String.format("%s/bin/operadriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.opera.driver", binPath);
                driver = new OperaDriver();
                break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);

        drivers[type.ordinal()] = driver;
        return driver;
    }
}

