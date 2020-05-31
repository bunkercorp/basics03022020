import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BrowserManager;
import setup.LoginPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class HelloRestApi {
                WebDriver driver;


//    WebDriverWait waitElementVisible = new WebDriverWait(driver, 10);

                @BeforeMethod(description = "Class Level Setup!", alwaysRun = true)
                public void setup() {
                        driver = BrowserManager.getInstance().getDriver(BrowserManager.BrowserType.CHROME, "https://jira.hillel.it/login.jsp");
                        LoginPage loginPage = new LoginPage(driver);
                        loginPage.loginJira();
                }

                @AfterMethod(description = "Class Level Teardown!", alwaysRun = true)
                public void teardown() {
                        //Write a Log when Tests are ending
                        driver.quit();
                        driver = null;
                }
                @Test

                public void isIssueCreated() throws IOException {

                        JiraIssue ticket = new JiraIssue.Builder().
                                inProject(ConfluenceTicketProvider.shared.projectKey).
                                ofType(ConfluenceTicketProvider.shared.issueTypeDisplayName).
                                withPriority(ConfluenceTicketProvider.shared.priorityDisplayName).
                                withDescription(ConfluenceTicketProvider.shared.content).
                                withSummary(ConfluenceTicketProvider.shared.summary).
                                withLabels(ConfluenceTicketProvider.shared.labels).create();

                        WebDriverWait waitElementVisible = new WebDriverWait(driver, 20);
                        driver.get("https://jira.hillel.it/browse/" + ticket.getJiraKey());
                        Assert.assertEquals(waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#project-name-val"))).getText(),ticket.getProjectKey(), "Incorrect value!");
                        Assert.assertEquals(waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#type-val"))).getText(), ticket.getIssueTypeDisplayName(), "Incorrect value!");
                        Assert.assertEquals(waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#priority-val"))).getText(), ticket.getPriorityDisplayName(), "Incorrect value!");
                        Assert.assertEquals(waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#description-val>div>p"))).getText(), ticket.getContent(), "Incorrect value!");

                        String[] labelsArray = waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#wrap-labels>div"))).getText().split(" ");
                        List<String> labels = Arrays.asList(labelsArray);
                        Assert.assertTrue(labels.containsAll(ticket.getLabels()) && ticket.getLabels().size() == labels.size(), "Incorrect value!");
                        Assert.assertEquals(waitElementVisible.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#summary-val"))).getText(), ticket.getSummary(), "Incorrect value!");
                }

        }
