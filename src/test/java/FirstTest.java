import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstTest {
    private WebDriver driver;
    private WebDriverWait waitElement;

    private By inputGoogle = By.xpath("//input[@name='q']");

    @Test
    public void testBasic() {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);

        driver = new ChromeDriver();
        waitElement = new WebDriverWait(driver, 10);

        driver.get("https://www.google.com/");

        waitElement.until(ExpectedConditions.presenceOfElementLocated(inputGoogle));

        driver.findElement(inputGoogle).sendKeys("Test");

        driver.quit();
    }
}
