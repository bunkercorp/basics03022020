

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.management.Attribute;

public class FirstTest {
    @Test
    public static void main() throws InterruptedException {

        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("https://jira.hillel.it/projects/AQA220/issues/AQA220-7?filter=allopenissues");
        browser.findElement(By.id("login-form-username")).sendKeys("Yemelianov");
        browser.findElement(By.id("login-form-password")).sendKeys("Yemelianov");
        browser.findElement(By.id("login-form-submit")).sendKeys(Keys.ENTER);
//        WebDriverWait wdWait = new WebDriverWait(browser, 10);
//        Alert popup = wdWait.until(ExpectedConditions.alertIsPresent());
        WebDriverWait wait = new WebDriverWait(browser, 10);
        String assign = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span[1]")).getText();
        Assert.assertEquals(assign, "Oleg Yemeкlianov");

        //Thread.sleep(2500);
       // browser.close();
    }
}
