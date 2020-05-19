package homeWork25.validation;
import java.util.List;
import homeWork25.JiraIssue;
import homeWork25.controllers.JiraControler;
import homeWork25.controllers.json.Json;
import homeWork25.credentials.Credentials;
import homeWork25.exeption.JiraExeption;
import io.restassured.path.json.JsonPath;

public class Validation {
    private String summary;
    private String issueTypeDisplayName;
    private String project;
    private String description;
    private String priority;

    public Validation(String summary, String issueTypeDisplayName, String project, String description, String priority) {
        this.summary = summary;
        this.issueTypeDisplayName = issueTypeDisplayName;
        this.project = project;
        this.description = description;
        this.priority = priority;
    }

    public boolean dataValidation() throws JiraExeption {
        if (project == null || priority == null || summary == null || issueTypeDisplayName == null ||
                description == null)
            throw new JiraExeption(String.format("Invalid jira parameters, %s, %s, %s, %s,%s",
                    project, priority,summary,description,priority));
        if (isExistingPriority(priority) && isExistingProject(project) && isExistingIssue(project,issueTypeDisplayName))
            return true;
        else {
            throw new JiraExeption(String.format("Invalid jira parameters, %s, %s, %s, %s,%s",
                    project, priority,summary,description,priority));
        }
    }

    private boolean isExistingProject(String project) {
        return !new JiraControler().getProject(project).contains("errorMessages");
    }

    private boolean isExistingPriority(String priority) {
        JsonPath jsonPath = new JsonPath(new JiraControler().getPriority());
        List<String> prList = jsonPath.getList("name");
        long quantity = prList.stream().filter(priority::equals).count();
        return quantity > 0;
    }

    public boolean isExistingIssue(String project, String issue){
        JsonPath jsonPath = new JsonPath(new JiraControler().getProject(project));
        List<String> prList = jsonPath.getList("issueTypes.name");
        long quantity = prList.stream().filter(issue::equals).count();
        return quantity > 0;
    }


}
