import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EditConfluence extends Main {

    @Test
    public void testBasic3() throws InterruptedException {
        WebDriver browser = giveMeBrowser("chrome");
        browser.get("https://jira.hillel.it/secure/Dashboard.jspa");
        WebDriverWait wdWait = new WebDriverWait(browser,10);
        WebElement username = browser.findElement(By.id("login-form-username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        username.sendKeys(System.getProperty("username"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.id("login")).click();
        Thread.sleep(5000);
        browser.findElement(By.xpath("//*[@id=\"header\"]/nav/div/div[1]/a/span")).click();
        browser.findElement(By.xpath("//*[@id=\"app-switcher\"]/div/ul/li[2]/a")).click();
        Thread.sleep(2500);
        WebElement username2 = browser.findElement(By.id("os_username"));
        WebElement password2 = browser.findElement(By.id("os_password"));
        username2.sendKeys(System.getProperty("username"));
        password2.sendKeys(System.getProperty("password"));
        browser.findElement(By.id("loginButton")).click();
        Thread.sleep(2500);
        browser.findElement(By.id("user-menu-link")).click();
        browser.findElement(By.id("view-user-profile-link")).click();
        browser.findElement(By.cssSelector("span.aui-icon.aui-icon-small.aui-iconfont-edit")).click();
        browser.findElement(By.id("userparam-phone")).clear();
        browser.findElement(By.id("userparam-phone")).sendKeys("066-000-00-00");
        browser.findElement(By.id("userparam-im")).clear();
        browser.findElement(By.id("userparam-im")).sendKeys("blabla");
        browser.findElement(By.id("userparam-website")).clear();
        browser.findElement(By.id("userparam-website")).sendKeys("hillel.com");
        browser.findElement(By.id("personalInformation")).clear();
        browser.findElement(By.id("personalInformation")).sendKeys("laugh out loud");
        browser.findElement(By.id("userparam-position")).clear();
        browser.findElement(By.id("userparam-position")).sendKeys("position");
        browser.findElement(By.id("userparam-department")).clear();
        browser.findElement(By.id("userparam-department")).sendKeys("department");
        browser.findElement(By.id("userparam-location")).clear();
        browser.findElement(By.id("userparam-location")).sendKeys("location");
        browser.findElement(By.id("confirm")).click();

        Assert.assertEquals(browser.findElement(By.id("userparam-phone")).getText(),"066-000-00-00");
        Assert.assertEquals(browser.findElement(By.id("userparam-im")).getText(),"blabla");
        Assert.assertEquals(browser.findElement(By.id("userparam-website")).getText(),"http://hillel.com");
        Assert.assertEquals(browser.findElement(By.id("profile-about-me-content")).getText(),"laugh out loud");
        Assert.assertEquals(browser.findElement(By.id("userparam-position")).getText(),"position");
        Assert.assertEquals(browser.findElement(By.id("userparam-department")).getText(),"department");
        Assert.assertEquals(browser.findElement(By.id("userparam-location")).getText(),"location");


//        Thread.sleep(5000);
//        browser.quit();

}}
