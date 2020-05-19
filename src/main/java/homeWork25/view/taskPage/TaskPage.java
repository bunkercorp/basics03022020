package homeWork25.view.taskPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaskPage {
    WebDriver driver;

    @FindBy(css = ".issue-link")
    WebElement projectKey;

    @FindBy(id = "type-val")
    WebElement issueTypeDisplayName;

    @FindBy(id = "priority-val")
    WebElement priorityDisplayName;

    @FindBy(css = ".user-content-block")
    WebElement description;

    @FindBy(id = "summary-val")
    WebElement summary;

    public TaskPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProjectKey() {
        //todo: Хорошо ли везде ставить wait?
        return new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOf(projectKey)).getText();
    }

    public String getIssueTypeDisplayName(){
        return new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOf(issueTypeDisplayName)).getText();
    }

    public String getPriorityDisplayName(){
        return new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOf(priorityDisplayName)).getText();
    }

    public String getDescription(){
        return new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOf(description)).getText();
    }

    public String getSummary(){
        return  new WebDriverWait(driver,10).
                until(ExpectedConditions.visibilityOf(summary)).getText();
    }

}
