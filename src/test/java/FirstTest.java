

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

    private static final WebDriver driver = new ChromeDriver();
    private static final WebDriverWait wait = new WebDriverWait(driver, 10);

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
        waitForElementVisibility("input#login-form-username").sendKeys(System.getenv("LOGIN"));
        findElement("input#login-form-password").sendKeys(System.getenv("PASSWORD"));
        findElement("input#login-form-submit").click();
        waitForElementVisibility("a#assign-to-me").click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span#assignee-val"), System.getenv("NAME")));
        String assignee = waitForElementVisibility("span#assignee-val").getText();
        Assert.assertEquals(assignee,System.getenv("NAME"));
        String textPopup = waitForElementVisibility("div.aui-message.closeable.aui-message-success.aui-will-close").getText();
        Assert.assertTrue(textPopup.contains("has been assigned"));
    }
}
