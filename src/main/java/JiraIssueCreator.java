import com.google.gson.Gson;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JiraTicketCreator {

    private static String jiraBody(JiraIssue issue){
        HashMap body = new HashMap();
        HashMap fields = new HashMap();
        fields.put("summary", issue.getSummary());
        HashMap issuetype = new HashMap();
        issuetype.put("id", issue.getIssueTypeDisplayName());
        fields.put("issuetype", issuetype);
        HashMap project = new HashMap();
        project.put("id", issue.getProjectKey());
        fields.put("project", project);
        HashMap priority = new HashMap();
        priority.put("id", issue.getPriorityDisplayName());
        fields.put("priority", priority);
        fields.put("labels", issue.getLabels());
        fields.put("description", issue.getContent());

        body.put("fields", fields);

        Gson gson = new Gson();
        return gson.toJson(body);
    }

    private static String postJiraTicket(JiraIssue issue) {

        String response = given().auth().preemptive().
                basic("knaimark", "1234567").
                header("Content-Type", "application/json").
                body(jiraBody(issue)).
                when().log().all().post("https://jira.hillel.it/rest/api/2/issue/").
                then().statusCode(201).extract().asString();
        return response;
    }
}
