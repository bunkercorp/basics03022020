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

public class FirstTest {
    @Test
    public void testBasic() throws InterruptedException {
//        String str = System.getProperty("lorem");
//        System.out.println(str);
//        Assert.assertEquals(str,"Ipsum");
        WebDriver browser = giveMeBrowser("chrome");
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

    @Test
    public void testBasic2() throws InterruptedException {
        WebDriver browser = giveMeBrowser("chrome");
        browser.get("https://jira.hillel.it/browse/AQA220-4");
        WebElement username = browser.findElement(By.id("login-form-username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        username.sendKeys(System.getProperty("username"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        WebDriverWait wdWait = new WebDriverWait(browser,10);

        WebElement priority = wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priority-val")));
        priority.click();
        List<WebElement> listPrio = wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option.imagebacked")));
        for (int i = 0; i < listPrio.size(); i++) {
            System.out.println(listPrio.get(i).getAttribute("label"));
        }
        browser.findElement(By.cssSelector("strong.name")).click();

        int random = (int) (Math.random()*10 + 5);
        String generatedLabel = RandomStringUtils.randomAlphanumeric(random);
        browser.findElement(By.id("labels-72352-value")).click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("labels-textarea"))).sendKeys(generatedLabel, Keys.ENTER);
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.aui-button.submit"))).click();

        browser.findElement(By.id("description-val")).click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mce_0_ifr")));
        browser.switchTo().frame("mce_0_ifr");
        WebElement Description = browser.findElement(By.xpath("//*[@id=\"tinymce\"]/p"));
        Description.sendKeys("Their saved linen downs tears son add music");
        browser.switchTo().defaultContent();
        browser.findElement(By.cssSelector("button.aui-button.aui-button-primary.submit")).click();


//        Thread.sleep(5000);
//        browser.quit();
    }
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
//        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("activity-stream-show-more")));
        Thread.sleep(5000);
        browser.findElement(By.xpath("//*[@id=\"header\"]/nav/div/div[1]/a/span")).click();
        browser.findElement(By.xpath("//*[@id=\"app-switcher\"]/div/ul/li[2]/a")).click();
        Thread.sleep(2500);
        WebElement username2 = browser.findElement(By.id("os_username"));
        WebElement password2 = browser.findElement(By.id("os_password"));
        username2.sendKeys("catharine.afanasyeva");
        password2.sendKeys("catharine");
        browser.findElement(By.id("loginButton")).click();
        Thread.sleep(2500);
        browser.findElement(By.id("user-menu-link")).click();
        browser.findElement(By.id("view-user-profile-link")).click();
        browser.findElement(By.cssSelector("span.aui-icon.aui-icon-small.aui-iconfont-edit")).click();





//        Thread.sleep(5000);
//        browser.quit();
    }




    static WebDriver giveMeBrowser(String browserName) throws InterruptedException {
        WebDriver browser;
        switch (browserName.toLowerCase().trim()) {
            case "firefox": {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                browser = new FirefoxDriver();
                break;
            }
            case "chrome": {
                final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", binPath);
                browser = new ChromeDriver();
                break;
            }
            case "opera": {
                final String binPath = String.format("%s/bin/operadriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.opera.driver", binPath);
                browser = new OperaDriver();
                break;
            }
            default:
                throw new IllegalArgumentException("Have no such driver");
        }
        Thread.sleep(200);
//        browser.manage().window().setSize(new Dimension(500, 380));
//        browser.manage().window().fullscreen();
        browser.manage().window().maximize();
        return browser;
    }
}



