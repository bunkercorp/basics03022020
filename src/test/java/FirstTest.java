import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

public class FirstTest {

    private static WebDriver driver = ((Supplier<WebDriver>) () -> {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        return new ChromeDriver();
    }).get();
    private static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static WebElement findElement(String css){
        return driver.findElement(By.cssSelector(css));
    }

    public static WebElement waitForElementVisibility(String css){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }

    @Test
    public void testBasic() throws InterruptedException {
        driver.get("https://jira.hillel.it/browse/AQA220-11");
        driver.manage().window().maximize();
        waitForElementVisibility("input#login-form-username").sendKeys("RaukoLord");
        findElement("input#login-form-password").sendKeys("T0uc4Me");
        findElement("input#login-form-submit").click();
        waitForElementVisibility("a#assign-to-me").click();
        Thread.sleep(2000);
        String assignee = waitForElementVisibility("span#assignee-val").getText();
        Assert.assertEquals(assignee, "Kat Shayakhmetova");
    }
}
