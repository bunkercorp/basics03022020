import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Main {
    static WebDriver giveMeBrowser(String browserName) throws InterruptedException {
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
        Thread.sleep(200);
//        browser.manage().window().setSize(new Dimension(500, 380));
//        browser.manage().window().fullscreen();
        browser.manage().window().maximize();
        return browser;
    }
}
