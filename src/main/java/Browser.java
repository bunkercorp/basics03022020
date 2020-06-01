import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import static java.lang.Thread.sleep;

public class Browser {
    private static WebDriver driver;

    public enum BrowserName {
        CHROME,
        FIREFOX,
        OPERA,
    }

    private Browser() {
    }

    public static WebDriver getBrowser(String browserName) throws InterruptedException {
        if (driver == null) {
            driver = giveMeBrowser(browserName);
        }
        return driver;
    }

    private static WebDriver giveMeBrowser(String browserName) throws InterruptedException {
        WebDriver browser;
        switch (browserName.toLowerCase().trim()) {
            case "firefox": {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                browser = new FirefoxDriver();
                break;
            }
            case "chrome": {
                final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", binPath);
                browser = new ChromeDriver();
                break;
            }
            case "opera": {
                final String binPath = String.format("%s/bin/operadriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.opera.driver", binPath);
                browser = new OperaDriver();
                break;
            }
            default:
                throw new IllegalArgumentException("Have no such driver");
        }
        sleep(200);
        /*browser.manage().window().setSize(new Dimension(500, 380));
        browser.manage().window().fullscreen();*/
        browser.manage().window().maximize();
        return browser;
    }

    public static WebDriver getBrowser(BrowserName browserName) throws InterruptedException {
        if (driver == null) {
            driver = giveMeBrowser(browserName);
        }
        return driver;
    }

    private static WebDriver giveMeBrowser(BrowserName browserName) throws InterruptedException {
        WebDriver browser;
        switch (browserName) {
            case FIREFOX: {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                browser = new FirefoxDriver();
                break;
            }
            case CHROME: {
                final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", binPath);
                browser = new ChromeDriver();
                break;
            }
            case OPERA: {
                final String binPath = String.format("%s/bin/operadriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.opera.driver", binPath);
                browser = new OperaDriver();
                break;
            }
            default:
                throw new IllegalArgumentException("Have no such driver");//если арг через перечисление, то это не нужно?
        }
        sleep(200);
        /*browser.manage().window().setSize(new Dimension(500, 380));
        browser.manage().window().fullscreen();*/
        browser.manage().window().maximize();
        return browser;
    }
}
