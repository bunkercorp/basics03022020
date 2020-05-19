package homeWork25.setup;
import homeWork25.view.loginPage.LoginPage;
import homeWork25.view.taskPage.TaskPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Setup {
    protected WebDriver driver;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected LoginPage loginPage;
    protected TaskPage taskPage;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setupDriver() {
        final String path = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", path);
    }

    @BeforeMethod
    public void setupBrowser() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        loginPage = new LoginPage(driver);
        taskPage = new TaskPage(driver);
        softAssert = new SoftAssert();

    }

    @AfterMethod
    public void postCondition() {
        driver.quit();
    }

}
