package JiraIssue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class JiraIssueUITestPage {
    WebDriver driver;

    public JiraIssueUITestPage(WebDriver driver) {
        this.driver = driver;
    }

    //
    public final By loginInput = By.cssSelector("#login-form-username");
    public final By passwordInput = By.cssSelector("#login-form-password");
    public final By submitButton = By.cssSelector("#login-form-submit");
    public final By filter = By.cssSelector("span[title='Sort By Created']");
    public final By search = By.cssSelector("#searcher-query");
    public final By moreButton = By.cssSelector("#opsbar-operations_more");
    public final By deleteIssueItem = By.cssSelector("#delete-issue");
    public final By confirmDeleteIssue = By.cssSelector("#delete-issue-submit");

    public void login(String login, String password) {
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public void searchForIssue(String summary){
        driver.findElement(search).clear();
        driver.findElement(search).sendKeys(summary, Keys.ENTER);
    }

    public void sortIssues(){
        driver.findElement(filter).click();
    }
    public void deleteIssue(){
        // апи разве не позволяет удалять тикеты?
        driver.findElement(moreButton).click();
        driver.findElement(deleteIssueItem).click();
        driver.findElement(confirmDeleteIssue).click();
    }

}
