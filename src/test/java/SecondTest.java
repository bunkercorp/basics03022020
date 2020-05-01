import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondTest {
    private By emailFld = By.xpath("//input[@id='login-form-username']");
    private By passwordFld = By.xpath("//input[@id='login-form-password']");
    private By loginBtn = By.xpath("//input[@id='login']");
    private By headerProfile = By.xpath("//a[contains(@id, 'header-details')]");
    private By hamburgerMenu = By.xpath("//a[@href='#app-switcher']");
    private By confluenceLnk = By.xpath("//a[@href='https://confluence.hillel.it/']");
    private By coEmail = By.xpath("//input[@id='os_username']");
    private By coPassword = By.xpath("//input[@id='os_password']");
    private By coLogin = By.xpath("//input[@id='loginButton']");
    private By coProfile = By.xpath("//a[@id='user-menu-link']");
    private By profile = By.xpath("//a[@href='/users/viewmyprofile.action']");
    private By editProfile = By.xpath("//a[@href='/users/editmyprofile.action']");
    private By editPhone = By.xpath("//input[@id='userparam-phone']");
    private By editIm = By.xpath("//input[@id='userparam-im']");
    private By editWebSite = By.xpath("//input[@id='userparam-website']");
    private By editInformation = By.xpath("//textarea[@id='personalInformation']");
    private By editPosition = By.xpath("//input[@id='userparam-position']");
    private By editDeparment = By.xpath("//input[@id='userparam-department']");
    private By editLocation = By.xpath("//input[@id='userparam-location']");
    private By editSave = By.xpath("//input[@name='confirm']");

    private By aboutMe = By.xpath("//div[@id='profile-about-me-content']");
    private By fullName = By.xpath("//span[@id='fullName']");
    private By email = By.xpath("//span[@id='email']");
    private By phone = By.xpath("//span[@id='userparam-phone']");
    private By im = By.xpath("//span[@id='userparam-im']");
    private By website = By.xpath("//span[@id='userparam-website']");
    private By position = By.xpath("//span[@id='userparam-position']");
    private By department = By.xpath("//span[@id='userparam-department']");
    private By location = By.xpath("//span[@id='userparam-location']");

    @Test
    public void testBasic() throws InterruptedException {
        final String binPath = String.format("%s/bin/chromedriver", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);

        WebDriver driver = new ChromeDriver();
        WebDriverWait waitElement = new WebDriverWait(driver, 20);

        driver.get("https://jira.hillel.it");

        waitElement.until(ExpectedConditions.presenceOfElementLocated(emailFld));

        driver.findElement(emailFld).sendKeys("shapovalovii2000");
        driver.findElement(passwordFld).sendKeys("Shapovalov2000");
        driver.findElement(loginBtn).click();

        String user = "Shapovalov Igor";
        waitElement.until(ExpectedConditions.presenceOfElementLocated(headerProfile));
        String checkUser = driver.findElement(headerProfile).getAttribute("title");
        Assert.assertTrue(checkUser.contains(user));

        driver.findElement(hamburgerMenu).click();
        waitElement.until(ExpectedConditions.elementToBeClickable(confluenceLnk));
        driver.findElement(confluenceLnk).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(coEmail));

        driver.findElement(coEmail).sendKeys("shapovalovii2000");
        driver.findElement(coPassword).sendKeys("Shapovalov2000");
        driver.findElement(coLogin).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(coProfile));
        driver.findElement(coProfile).click();
        waitElement.until(ExpectedConditions.elementToBeClickable(profile));
        driver.findElement(profile).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(editProfile));
        driver.findElement(editProfile).click();

        String phon = "380000000000";
        String im1 = "Im";
        String webSite = "https://google.com";
        String information = "Information";
        String position1 = "Odessa";
        String deparment1 = "Test";
        String location1 = "Test";

        waitElement.until(ExpectedConditions.presenceOfElementLocated(editPhone));
        driver.findElement(editPhone).clear();
        driver.findElement(editPhone).sendKeys(phon);

        driver.findElement(editIm).clear();
        driver.findElement(editIm).sendKeys(im1);

        driver.findElement(editWebSite).clear();
        driver.findElement(editWebSite).sendKeys(webSite);

        driver.findElement(editInformation).clear();
        driver.findElement(editInformation).sendKeys(information);

        driver.findElement(editPosition).clear();
        driver.findElement(editPosition).sendKeys(position1);

        driver.findElement(editDeparment).clear();
        driver.findElement(editDeparment).sendKeys(deparment1);

        driver.findElement(editLocation).clear();
        driver.findElement(editLocation).sendKeys(location1);
        driver.findElement(editSave).click();

        waitElement.until(ExpectedConditions.presenceOfElementLocated(aboutMe));

        String me = driver.findElement(aboutMe).getText();
        Assert.assertEquals(me, information);

        String fullname = driver.findElement(fullName).getText();
        Assert.assertEquals(fullname, "Shapovalov Igor");

        String e = driver.findElement(email).getText();
        Assert.assertEquals(e, "shapovalovii2000@gmail.com");

        String p = driver.findElement(phone).getText();
        Assert.assertEquals(p, phon);

        String i = driver.findElement(im).getText();
        Assert.assertEquals(i, im1);

        String w = driver.findElement(website).getText();
        Assert.assertEquals(w, webSite);

        String pos = driver.findElement(position).getText();
        Assert.assertEquals(pos, position1);

        String d = driver.findElement(department).getText();
        Assert.assertEquals(d, deparment1);

        String l = driver.findElement(location).getText();
        Assert.assertEquals(l, location1);


        Thread.sleep(3000);
        driver.quit();
    }
}
