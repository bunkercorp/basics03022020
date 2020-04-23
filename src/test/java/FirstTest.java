import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void main() throws InterruptedException {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        WebDriver browser = new ChromeDriver();
        Thread.sleep(2500);
        browser.get("https://jira.hillel.it/browse/AQA220-10");
        WebElement login = browser.findElement(By.id("login-form-username"));
        WebElement pass = browser.findElement(By.id("login-form-password"));
        login.sendKeys(System.getProperty("login"));
        pass.sendKeys(System.getProperty("pass"));
        browser.findElement(By.id("login-form-password")).submit();
        browser.findElement(By.id("assign-to-me")).click();
        WebDriverWait wdWait = new WebDriverWait(browser,10);
        WebElement button = wdWait.until(ExpectedConditions.attributeToBe(,""));

        String something = browser.findElement(By.cssSelector("span[id='assignee-val']"));
        System.out.println(something);

        //browser.close();
    }
    public void testBasic() {
        String str = System.getProperty("lorem");
        System.out.println(str);
       // Assert.assertEquals(str,"Ipsum");
    }
}
