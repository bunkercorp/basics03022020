import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.Keys.*;

public class ThirdTest {
    @Test
    public void main() throws InterruptedException {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        WebDriver browser = new ChromeDriver();
        Thread.sleep(2500);
        browser.get("https://jira.hillel.it/browse/AQA220-11");
        WebElement login = browser.findElement(By.id("login-form-username"));
        WebElement pass = browser.findElement(By.id("login-form-password"));
        login.sendKeys(System.getProperty("login"));
        pass.sendKeys(System.getProperty("pass"));
        browser.findElement(By.id("login-form-password")).submit();
        Thread.sleep(1500);
        
     //   browser.findElement(By.id("priority-val")).click();
     //   WebDriverWait wdWait = new WebDriverWait(browser,10);
      //  List<WebElement> priorities = wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option.imagebacked")));
      //  priorities.forEach(h -> System.out.println(h.getAttribute("label")));

    //    new Actions(browser).sendKeys(ESCAPE).build().perform();
      //  new Actions(browser).sendKeys(ESCAPE).build().perform();
//        String generatedString = RandomStringUtils.randomAlphanumeric(5);
    //    browser.findElement(By.cssSelector("div#wrap-labels span")).click();
   //     WebElement label = (WebElement) wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("textarea#labels-textarea")));
    //    label.sendKeys("test");



        //System.out.println("test");
        //System.out.println(priorities.size());

    }
}