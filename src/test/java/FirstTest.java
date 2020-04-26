import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.regex.Pattern;

public class FirstTest {
    @Test
    public void testBasic() throws InterruptedException {
//        String str = System.getProperty("lorem");
//        System.out.println(str);
//        Assert.assertEquals(str,"Ipsum");
        WebDriver browser = giveMeBrowser("chrome");
        browser.get("https://jira.hillel.it/browse/AQA220-4");
        WebElement username = browser.findElement(By.id("login-form-username"));
        username.sendKeys(System.getProperty("username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"assign-to-me\"]")).click();
        WebDriverWait wdWait = new WebDriverWait(browser,10);
//        WebElement someName = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));
        wdWait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"assignee-val\"]/span"), Pattern.compile("(.*)(\\s)(.*)")));
//        Assert.assertEquals(someName.getText(),"Kate Afanasieva");
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div"))).getText();
        Assert.assertEquals("AQA220-4 has been assigned.", wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div"))).getText());



        Thread.sleep(5000);
        browser.quit();
    }
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



