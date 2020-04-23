

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class FirstTest {
    //final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
    //System.setProperty("webdriver.chrome.driver",binPath);

    private static WebDriver driver = new ChromeDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static WebElement findElement(String ccs) {
        return driver.findElement(By.cssSelector(ccs));
    }

    public static WebElement waitForElementVisibility(String css) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }

    @Test
    public void testBasic() throws InterruptedException {
        driver.get("https://jira.hillel.it/browse/AQA220-6");
        driver.manage().window().maximize();
        waitForElementVisibility("input#login-form-username").sendKeys("The_Boyev");
        findElement("input#login-form-password").sendKeys("160686bd");
        findElement("input#login-form-submit").click();
        waitForElementVisibility("a#assign-to-me").click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span#assignee-val"), "Dmitriy Boyev"));
        String assignee = waitForElementVisibility("span#assignee-val").getText();
        Assert.assertEquals(assignee, "Dmitriy Boyev");
    }
}
