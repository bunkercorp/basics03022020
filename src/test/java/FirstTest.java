import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
    private By emailFld = By.xpath("//input[@id='login-form-username']");
    private By passwordFld = By.xpath("//input[@id='login-form-password']");
    private By loginBtn = By.xpath("//input[@id='login-form-submit']");
    private By assignMeLnk = By.xpath("//a[@id='assign-to-me']");
    private By meAssign = By.xpath("//span[contains(@id, 'assignee_shapovalovii2000')]");

    @Test
    public void testBasic() {
        final String binPath = String.format("%s/bin/chromedriver", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);

        WebDriver driver = new ChromeDriver();
        WebDriverWait waitElement = new WebDriverWait(driver, 10);

        driver.get("https://jira.hillel.it/browse/AQA220-5");

        waitElement.until(ExpectedConditions.presenceOfElementLocated(emailFld));

        driver.findElement(emailFld).sendKeys("shapovalovii2000");
        driver.findElement(passwordFld).sendKeys("Shapovalov2000");
        driver.findElement(loginBtn).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(assignMeLnk));
        driver.findElement(assignMeLnk).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(meAssign));
        String str = driver.findElement(meAssign).getText();
        Assert.assertEquals(str, "Shapovalov Igor");

        driver.quit();
    }
}
