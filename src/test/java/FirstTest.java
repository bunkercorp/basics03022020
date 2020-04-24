import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void testBasic() throws InterruptedException {
//        String str = System.getProperty("lorem");
//        System.out.println(str);
//        Assert.assertEquals(str,"Ipsum");
//        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
//        System.setProperty("webdriver.chrome.driver", binPath);
//        WebDriver browser = new ChromeDriver();
        final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.gecko.driver", binPath);
        WebDriver browser = new FirefoxDriver();
        Thread.sleep(2500);
        browser.manage().window().maximize();
        browser.get("https://jira.hillel.it/browse/AQA220-4");
        WebElement username = browser.findElement(By.id("login-form-username"));
        username.sendKeys(System.getProperty("username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"assign-to-me\"]")).click();
        Thread.sleep(4000);
        WebElement someName = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));
        Assert.assertEquals(someName.getText(),"Kate Afanasieva");

        Thread.sleep(5000);
        browser.quit();
    }
}


