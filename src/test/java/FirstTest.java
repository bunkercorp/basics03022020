

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTest {
    /*@Test
    public void testBasic() {
        String str = System.getProperty("lorem");
        System.out.println(str);
        Assert.assertEquals(str,"Ipsum");
    }*/

    @Test
    public void testAssignMyTask() throws InterruptedException {
        final String binPath = String.format("%s/.idea/bin/chromedriver.exe",System.getProperty("user.dir"));

        System.setProperty("webdriver.chrome.driver",binPath);

        String strLogin = System.getProperty("login");
        String strPass = System.getProperty("pass");
        String strExpectedName = System.getProperty("expectedName").replace("\"","");

        WebDriver browser = new ChromeDriver();
        final Wait<WebDriver> wait = new WebDriverWait(browser, 5).withMessage("Element was not found");
        browser.manage().window().maximize();
        browser.get("https://jira.hillel.it/browse/AQA220-2");

        browser.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(strLogin);
        browser.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(strPass);
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"assign-to-me\"]")));
        browser.findElement(By.xpath("//*[@id=\"assign-to-me\"]")).click();
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"AQA220-2 has been assigned.\"]")));


       //Thread.sleep(3000);
        WebElement userName = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));


        Assert.assertEquals(userName.getText(),strExpectedName);


        //Thread.sleep(3000);
        browser.quit();
    }
    @Test
    public void test2703() throws InterruptedException {
        final String binPath = String.format("%s/.idea/bin/chromedriver.exe",System.getProperty("user.dir"));

        System.setProperty("webdriver.chrome.driver",binPath);

        String strLogin = "insane";//System.getProperty("login");
        String strPass = "SanSanich74";//System.getProperty("pass");
        //String strExpectedName = System.getProperty("expectedName").replace("\"","");

        WebDriver browser = new ChromeDriver();
        final Wait<WebDriver> wait = new WebDriverWait(browser, 5).withMessage("Element was not found");
        browser.manage().window().maximize();
        browser.get("https://jira.hillel.it/browse/AQA220-2");
        browser.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys(strLogin);
        browser.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys(strPass);
        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();
/*
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priority-val")));
        browser.findElement(By.id("priority-val")).click();

        List<WebElement> listPrioity = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@id=\"priority\"]/option")));

        for (WebElement elem:listPrioity) {
            System.out.println(elem.getAttribute("innerText").trim());
        }
*/

        int random = (int) (Math.random()*5 + 5);

        String generatedString = RandomStringUtils.randomAlphanumeric(random);

            browser.findElement(By.id("labels-72350-value")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("labels-textarea"))).sendKeys(generatedString);
            browser.findElement(By.id("labels-textarea")).sendKeys(Keys.ENTER);
    }
}
