

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
        Assert.assertEquals(assignee, System.getenv("NAME"));
        String textPopup = waitForElementVisibility("div.aui-message.closeable.aui-message-success.aui-will-close").getText();
        Assert.assertTrue(textPopup.contains("has been assigned"));
    }

    @Test
    public void testTicketContent() throws InterruptedException {
        driver.get("https://jira.hillel.it/browse/AQA220-6");
        driver.manage().window().maximize();
        waitForElementVisibility("input#login-form-username").sendKeys(System.getenv("LOGIN"));
        findElement("input#login-form-password").sendKeys(System.getenv("PASSWORD"));
        findElement("input#login-form-submit").click();
        waitForElementVisibility("span#priority-val").click();
        List<WebElement> listOfPriority = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option.imagebacked")));
        findElement("span.icon.aui-ss-icon.noloading.drop-menu").click();
        for (WebElement webElement : listOfPriority) {
            System.out.println(webElement.getAttribute("innerText").trim());
        }

        String generatedString = RandomStringUtils.randomAlphanumeric(5);

        try {
            if (findElement("span#labels-72354-value").isDisplayed()) {
                waitForElementVisibility("span#labels-72354-value").click();
                waitForElementVisibility("textarea#labels-textarea").click();
                findElement("textarea#labels-textarea").sendKeys(generatedString, Keys.ENTER);
                //findElement("textarea#labels-textarea").sendKeys(Keys.ENTER);
                waitForElementVisibility("button.submit").click();
            }
        } catch (NoSuchElementException ignore) {
        }
        try {
            if (findElement("ul#labels-72354-value").isDisplayed()) {
                Actions action = new Actions(driver);
                action.moveToElement(waitForElementVisibility("ul.labels")).build().perform();
                waitForElementVisibility("div.labels-wrap span.overlay-icon").click();
                findElement("textarea#labels-textarea").sendKeys(generatedString, Keys.ENTER);
                waitForElementVisibility("button.submit").click();
            }
        } catch (org.openqa.selenium.NoSuchElementException ignore) {
        }

        List<WebElement> listOfLabels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.labels a span")));
        for (int i = 0; i < listOfLabels.size(); i++) {
            if (!listOfLabels.get(i).getText().equals(generatedString)) {
                i++;
            } else {
                Assert.assertEquals(generatedString, listOfLabels.get(i).getText());
            }

        }

        findElement("div em").click();
        waitForElementVisibility("iframe#mce_0_ifr");
        driver.switchTo().frame("mce_0_ifr");
        waitForElementVisibility("body#tinymce p").click();
        String description = "Мені тринадцятий минало. Я пас ягнята за селом.";
        waitForElementVisibility("body#tinymce p").sendKeys(description);
        driver.switchTo().defaultContent();
        findElement("button[type='submit']").click();
        Assert.assertEquals(waitForElementVisibility("div#description-val p").getText(), description);
    }
}

