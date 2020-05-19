package homeWork25;
import homeWork25.controllers.ConfluenceController;
import homeWork25.credentials.Credentials;
import homeWork25.exeption.JiraExeption;
import homeWork25.setup.Setup;
import org.testng.annotations.Test;

public class Main extends Setup {

    @Test
    public void checkCreatedTask() throws JiraExeption {
        JiraIssue ticket = new JiraIssue.Builder().
                inProject(ConfluenceController.getProjectKey()).
                ofType(ConfluenceController.getType()).
                withPriority(ConfluenceController.getPriority()).
                withDescription(ConfluenceController.getDescription()).
                withSummary(ConfluenceController.getSummary()).
                withLabels(ConfluenceController.getLabels()).create();

        driver.get(Credentials.URL.getCredential() + ticket.getJiraKey());
        loginPage.login(Credentials.EMAIL.getCredential(),
                Credentials.PASSWORD.getCredential());
        softAssert.assertEquals(taskPage.getProjectKey(),ticket.getProjectKey(),
                "Incorrect project key");
        softAssert.assertEquals(taskPage.getIssueTypeDisplayName(),ticket.getIssueTypeDisplayName(),
                "Incorrect type");
        softAssert.assertEquals(taskPage.getPriorityDisplayName(),ticket.getPriorityDisplayName(),
                "Incorrect priority");
        softAssert.assertEquals(taskPage.getDescription(),ticket.getDescription(),
                "Incorrect description");
        softAssert.assertEquals(taskPage.getSummary(),ticket.getSummary(),
                "Incorrect summary");
        softAssert.assertAll();

    }

}
