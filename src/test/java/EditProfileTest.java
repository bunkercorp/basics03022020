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

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EditProfileTest extends InfrastructureSetup {
    private WebDriver driver;
    InfrastructureSetup runTest = new InfrastructureSetup();
    LoginJiraPage loginInJira;
    LoginConfluencePage loginInConfluence;
    ConfluenceMainPage confluenceMain;

    @BeforeClass
    public void setUp() {
        driver = runTest.setDriver("chrome", "https://jira.hillel.it/");
        loginInJira = new LoginJiraPage(driver);
        loginInConfluence = new LoginConfluencePage(driver);
        confluenceMain = new ConfluenceMainPage(driver);
    }


    @Test
    public void editProfileTest() {
        ProfileConfluencePage profile = new ProfileConfluencePage(driver);
        loginInJira.pathToConfluence();
        loginInConfluence.loginInConfluence(System.getenv("LOGIN"), System.getenv("PASSWORD"));
        confluenceMain.accessProfile();
        profile.commenceEditProfile();
        profile.editBio(profile.getBio());
        profile.editDepartment(profile.getDepartment());
        profile.editLocation(profile.getLocation());
        profile.editPhone(profile.getPhone());
        profile.editPosition(profile.getPosition());
        profile.editWebSite(profile.getWebSite());
        profile.clickSaveChangesButton();

        final Map<String, Boolean> validations = new Hashtable<>();

        validations.put("Bio", driver.findElement(By.cssSelector("#profile-about-me-content")).getText().contentEquals("Basdfkresotvwhjort"));
        validations.put("Phone", driver.findElement(By.cssSelector("#userparam-phone")).getText().contentEquals(profile.getPhone()));

        List<String> failedValidations = validations
                .entrySet()
                .stream()
                .filter(e -> !e.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Assert.assertTrue(failedValidations.isEmpty(), String.join(", ", failedValidations));


//        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-website")).getText(), profile.getWebSite());
//        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-position")).getText(), profile.getPosition());
//        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-location")).getText(), profile.getLocation());
//        Assert.assertEquals(driver.findElement(By.cssSelector("#userparam-department")).getText(), profile.getDepartment());
    }
}
