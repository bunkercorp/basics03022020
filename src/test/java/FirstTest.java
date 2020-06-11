import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FirstTest {
    private WebDriverWait waitElement;

    private By inputGoogle = By.xpath("//input[@name='q']");

    @Test
    public void testBasic() throws MalformedURLException {
        waitElement = new WebDriverWait(Driver.getDriver(), 10);

        Driver.getDriver().get("https://www.google.com/");

        waitElement.until(ExpectedConditions.presenceOfElementLocated(inputGoogle));

        Driver.getDriver().findElement(inputGoogle).sendKeys("Test");

    }

    @AfterTest
    public void after() {
        Driver.quitDriver();
    }
}
