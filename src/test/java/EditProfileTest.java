import PageObjectEditProfileTest.ConfluenceMainPage;
import PageObjectEditProfileTest.LoginConfluencePage;
import PageObjectEditProfileTest.LoginJiraPage;
import PageObjectEditProfileTest.ProfileConfluencePage;
import infrastructure.InfrastructureSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class EditProfileTest extends InfrastructureSetup {
    private WebDriver driver;
    InfrastructureSetup runTest = new InfrastructureSetup();
    final String bio = "Боритесь та поборете";
    final String phone = "555-555-555";
    final String webSite = "http://taras.grygorovych.ua";
    final String position = "Проти журби у функцiях";
    final String department = "QA";
    final String location = "Батькiвщина";

    @BeforeClass
    public void setUp() {
        driver = runTest.setDriver("firefox", "https://jira.hillel.it/secure/Dashboard.jspa");
    }


    @Test
    public void editProfileTest() {
        LoginJiraPage loginInJira = new LoginJiraPage(driver);
        loginInJira.pathToConfluence();
        LoginConfluencePage loginInConfluence = new LoginConfluencePage(driver);
        loginInConfluence.loginInConfluence(System.getenv("LOGIN"), System.getenv("PASSWORD"));
        ConfluenceMainPage confluenceMain = new ConfluenceMainPage(driver);
        confluenceMain.accessProfile();
        ProfileConfluencePage profile = new ProfileConfluencePage(driver);
        profile.clickEditProfileButton();
        profile.editBio(bio);
        profile.editDepartment(department);
        profile.editLocation(location);
        profile.editPhone(phone);
        profile.editPosition(position);
        profile.editWebSite(webSite);
        profile.clickSaveChangesButton();
        Assert.assertEquals(driver.findElement(By.cssSelector("#profile-about-me-content")).getText(), bio);
        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-phone")).getText(), phone);
        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-website")).getText(), webSite);
        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-position")).getText(), position);
        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-location")).getText(), location);
        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-department")).getText(), department);
    }
}
