import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class FirstTest {
    private WebDriver driver;
    private WebDriverWait waitElement;

    private By emailFld = By.xpath("//input[@id='login-form-username']");
    private By passwordFld = By.xpath("//input[@id='login-form-password']");
    private By loginBtn = By.xpath("//input[@id='login-form-submit']");
    private By assignMeLnk = By.xpath("//a[@id='assign-to-me']");
    private By meAssign = By.xpath("//span[contains(@id, 'assignee_shapovalovii2000')]");


    private By openPrior = By.xpath("//span[@id='priority-val']");
    private By priorityes = By.cssSelector("option.imagebacked");
    private By menuPrior = By.xpath("//span[contains(@class, 'drop-menu')]");
    private By cancelPrior = By.xpath("//button[@type='cancel']");
    private By openlabels = By.xpath("(//span[contains(@class,'aui-iconfont-edit')])[5]");
    private By labels = By.cssSelector("a.lozenge");
    private By labelTextArea = By.xpath("(//textarea[contains(@class, 'text')])[1]");
    private By submitLabels = By.xpath("//button[@type='submit']");
    private By openDescription = By.xpath("//div[@id='description-val']");
    private By frameDescription = By.xpath("//iframe");
    private By bodyDescription = By.xpath("//body[@id='tinymce']");
    private By saveDescription = By.xpath("//button[text()='Save']");
    private By description = By.xpath("//div[@class='user-content-block']");


    @Test
    public void testBasic() {
        final String binPath = String.format("%s/bin/chromedriver", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);

        driver = new ChromeDriver();
        waitElement = new WebDriverWait(driver, 10);

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


    @Test
    public void testBasic2() {
        final String binPath = String.format("%s/bin/chromedriver", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);

        driver = new ChromeDriver();
        waitElement = new WebDriverWait(driver, 10);
        //driver.manage().window().maximize();

        driver.get("https://jira.hillel.it/browse/AQA220-5");

        waitElement.until(ExpectedConditions.presenceOfElementLocated(emailFld));

        driver.findElement(emailFld).sendKeys("shapovalovii2000");
        driver.findElement(passwordFld).sendKeys("Shapovalov2000");
        driver.findElement(loginBtn).click();

        waitElement.until(ExpectedConditions.elementToBeClickable(openPrior));
        driver.findElement(openPrior).click();

        List<WebElement> priority = waitElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(priorityes));
        System.out.println("Priority:");
        for (WebElement webElement : priority) System.out.println(webElement.getAttribute("label"));

        driver.findElement(menuPrior).click();
        driver.findElement(cancelPrior).click();

        waitElement.until(ExpectedConditions.elementToBeClickable(openPrior));
        driver.findElement(openlabels).click();

        Random rand = new Random();
        String generatedString = RandomStringUtils.random(
                5 + rand.nextInt(10 - 5),
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");

        waitElement.until(ExpectedConditions.elementToBeClickable(labelTextArea));
        driver.findElement(labelTextArea).sendKeys(generatedString);

        waitElement.until(ExpectedConditions.elementToBeClickable(submitLabels));
        driver.findElement(submitLabels).click();

        List<WebElement> searchLabels = waitElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(labels));
        System.out.println("Labels:");
        for (WebElement webElement : searchLabels) System.out.println(webElement.getAttribute("outerText"));

        waitElement.until(ExpectedConditions.elementToBeClickable(openDescription));
        driver.findElement(openDescription).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(frameDescription));
        driver.switchTo().frame(driver.findElement(frameDescription));

        String myDescription = "Description My";

        driver.findElement(bodyDescription).clear();
        driver.findElement(bodyDescription).sendKeys(myDescription);

        driver.switchTo().defaultContent();

        driver.findElement(saveDescription).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(description));
        driver.findElement(description).getText();
        Assert.assertEquals(myDescription, "Description My");

        driver.quit();
    }
}
