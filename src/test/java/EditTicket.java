import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Pattern;

public class EditTicket extends Main {
    WebDriver browser = giveMeBrowser("chrome");

    public EditTicket() throws InterruptedException {
    }

    @Test
    public void testBasic2() throws InterruptedException {

        browser.get("https://jira.hillel.it/browse/AQA220-4");
        WebElement username = browser.findElement(By.id("login-form-username"));
        WebElement password = browser.findElement(By.id("login-form-password"));
        username.sendKeys(System.getProperty("username"));
        password.sendKeys(System.getProperty("password"));
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        Thread.sleep(5000);
        WebDriverWait wdWait = new WebDriverWait(browser,10);
//список приоритетов
        WebElement priority = wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priority-val")));
        priority.click();
        List<WebElement> listPrio = wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("option.imagebacked")));
        for (int i = 0; i < listPrio.size(); i++) {
            System.out.println(listPrio.get(i).getAttribute("label"));
        }
        browser.findElement(By.cssSelector("strong.name")).click();

//рандомные лейблы
        int random = (int) (Math.random()*10 + 5);
        String generatedLabel = RandomStringUtils.randomAlphanumeric(random);
//        browser.findElement(By.id("labels-72352-value")).click();
//        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("labels-textarea"))).sendKeys(generatedLabel, Keys.ENTER);
//        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.aui-button.submit"))).click();

            try {
                List<WebElement> LabelList = wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"labels-72352-value\"]/li/a/span")));
                Actions act1 = new Actions(browser);
                System.out.println("last label is: " + LabelList.get(LabelList.size() - 1).getAttribute("outerText"));
                act1.moveToElement(LabelList.get(LabelList.size() - 1)).moveToElement(browser.findElement(By.xpath("//*[@id=\"wrap-labels\"]/div/span"))).click().build().perform();

            } catch (TimeoutException ex) {
                System.out.println("no labels yet");
                browser.findElement(By.xpath("//*[@id=\"wrap-labels\"]/div")).click(); // for first label
            }

            WebElement labelInputField = wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"labels-textarea\"]")));
            labelInputField.sendKeys(generatedLabel,Keys.ENTER);
            labelInputField.submit();

//дескрипшн стереть и написать
        browser.findElement(By.id("description-val")).click();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mce_0_ifr")));
        browser.switchTo().frame("mce_0_ifr");
        String text = "Their saved linen downs tears son add music";
        browser.findElement(By.xpath("//*[@id=\"tinymce\"]")).clear();
        WebElement Description = wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tinymce\"]")));
        Description.sendKeys(text);
        browser.switchTo().defaultContent();
        browser.findElement(By.cssSelector("button.aui-button.aui-button-primary.submit")).click();
        Thread.sleep(2500);
        Assert.assertEquals(wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"description-val\"]/div/p"))).getText(), text);


//        Thread.sleep(5000);
//        browser.quit();
    }
}

