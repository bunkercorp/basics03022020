

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("https://jira.hillel.it/browse/AQA220-2");
        WebElement login = browser.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        String strLogin = System.getProperty("login");
        login.sendKeys(strLogin);

        WebElement password = browser.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        String strPass = System.getProperty("pass");
        password.sendKeys(strPass);

        browser.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).click();


        Thread.sleep(3000);


        browser.findElement(By.xpath("//*[@id=\"assign-to-me\"]")).click();
        Thread.sleep(3000);
        WebElement userName = browser.findElement(By.xpath("//*[@id=\"assignee-val\"]/span"));


        Assert.assertEquals(userName.getText(),"Prykhodko Oleksandr");


        //Thread.sleep(3000);
        //browser.quit();
    }
}
