import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class APITest {
    WebDriver browser;

    @Before
    public void before() throws InterruptedException {
        browser = Browser.getBrowser(Browser.BrowserName.CHROME);
        LogInPage loginPage = new LogInPage(browser);
        loginPage.login();
    }

    @After
    public void after() throws InterruptedException {
        browser.quit();
    }

    @Test
    public void test() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(browser, 10);
        ConfluenceGet conf = new ConfluenceGet();

        JiraIssue createIssue = new JiraIssue.Builder().
                inProject(conf.getProjectKey()).
                ofType(conf.getIssueTypeDisplayName()).
                withPriority(conf.getPriorityDisplayName()).
                withDescription(conf.getContent()).
                withSummary(conf.getSummary()).
                withLabels(conf.getLabels()).create();

        browser.get("https://jira.hillel.it/secure/Dashboard.jspa");
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickSearchInput\"]")));
        search.sendKeys("name_of_ticket", Keys.RETURN);
        Thread.sleep(3000);
        browser.findElement(By.xpath("//*[@id=\"key-val\"]")).click();
        Assert.assertEquals(createIssue.getIssueTypeDisplayName(), browser.findElement(By.xpath("//*[@id=\"type-val\"]")).getText());
        Assert.assertEquals(createIssue.getContent(), browser.findElement(By.xpath("//*[@id=\"description-val\"]/div/p")).getText());
        Assert.assertEquals(createIssue.getSummary(), browser.findElement(By.xpath("//*[@id=\"summary-val\"]")).getText());
        Assert.assertEquals(createIssue.getProjectKey(), browser.findElement(By.xpath("//*[@id=\"project-name-val\"]")).getText());
        Assert.assertEquals(createIssue.getPriorityDisplayName(), browser.findElement(By.xpath("//*[@id=\"priority-val\"]")).getText());

    }
}
