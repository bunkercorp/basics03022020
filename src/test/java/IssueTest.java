import JiraIssue.JiraIssue;
import JiraIssue.JiraIssueUITestPage;
import infrastructure.InfrastructureSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class IssueTest {
    WebDriver driver;
    WebDriverWait wait;
    InfrastructureSetup runTest = new InfrastructureSetup();
    JiraIssueUITestPage jira;
    JiraIssue createIssue = new JiraIssue();


    public IssueTest() throws IOException {
    }


    @BeforeClass
    public void setUp() {
        driver = runTest.setDriver("chrome", "https://jira.hillel.it/login.jsp");
        jira = new JiraIssueUITestPage(driver);
        wait = new WebDriverWait(driver, 15);
    }

    @Test
    public void issueAvailableTest() throws IOException, InterruptedException {
        createIssue.create();
        jira.login(System.getenv("LOGIN"), System.getenv("PASSWORD"));
        jira.searchForIssue(createIssue.getSummary());
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("td.summary p a")).click();
        Assert.assertEquals(createIssue.getIssueType(), driver.findElement(By.cssSelector("#type-val")).getText());
        Assert.assertEquals(createIssue.getDescription(), driver.findElement(By.cssSelector("#description-val p")).getText());
        Assert.assertEquals(createIssue.getSummary(), driver.findElement(By.cssSelector("#summary-val")).getText());
        Assert.assertEquals(createIssue.getProjectKey(), driver.findElement(By.cssSelector("#project-name-val")).getText());
        Assert.assertEquals(createIssue.getPriority(), driver.findElement(By.cssSelector("#priority-val")).getText());
        List<WebElement> labels = driver.findElements(By.cssSelector("ul.labels li a"));
        String label = "";
        for (WebElement webElement : labels) {
            label = webElement.getText();
            Assert.assertTrue(createIssue.getLabels().toString().contains(label));
        }
    }

    @AfterClass
    public void afterMethod() {
        jira.deleteIssue();
        driver.close();
    }


}

