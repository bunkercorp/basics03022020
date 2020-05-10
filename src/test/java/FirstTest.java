import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Pattern;

public class FirstTest extends Main {
    WebDriver browser = giveMeBrowser("chrome");

    public FirstTest() throws InterruptedException {
    }

    @Test
    public void testBasic() throws InterruptedException {
//        String str = System.getProperty("lorem");
//        System.out.println(str);
//        Assert.assertEquals(str,"Ipsum");

        browser.get("https://jira.hillel.it/browse/AQA220-4");
        WebElement username = browser.findElement(By.id("login-form-username"));
        username.sendKeys(System.getProperty("username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        browser.findElement(By.xpath("//*[@id=\"assign-to-me\"]")).click();
        WebDriverWait wdWait = new WebDriverWait(browser,10);
//        WebElement someName = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));
        wdWait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"assignee-val\"]/span"), Pattern.compile("(.*)(\\s)(.*)")));
//        Assert.assertEquals(someName.getText(),"Kate Afanasieva");
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div"))).getText();
        Assert.assertEquals("AQA220-4 has been assigned.", wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div"))).getText());
        WebElement assignee = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));
        assignee.click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"assignee-field\"]\n"))).sendKeys(Keys.BACK_SPACE);
        browser.findElement(By.xpath("//*[@id=\"assignee-form\"]/div[2]/button[1]")).click();

//        Thread.sleep(5000);
//        browser.quit();
    }
}



