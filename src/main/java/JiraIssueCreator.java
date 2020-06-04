import com.google.gson.Gson;
import java.util.HashMap;


import static io.restassured.RestAssured.given;

public class JiraIssueCreator {

    private static String jiraBody(JiraIssue issue){
        HashMap body = new HashMap();
        HashMap fields = new HashMap();
        fields.put("summary", issue.getSummary());
        HashMap issuetype = new HashMap();
        issuetype.put("name", issue.getIssueTypeDisplayName());
        fields.put("issuetype", issuetype);
        HashMap project = new HashMap();
        project.put("key", issue.getProjectKey());
        fields.put("project", project);
        HashMap priority = new HashMap();
        priority.put("name", issue.getPriorityDisplayName());
        fields.put("priority", priority);
        fields.put("labels", issue.getLabels());
        fields.put("description", issue.getContent());

        body.put("fields", fields);

        Gson gson = new Gson();
        return gson.toJson(body);
    }

    public static String postJiraIssue(JiraIssue issue) {
        String response = given().auth().preemptive().
                basic("knaimark", "1234567").
                header("Content-Type", "application/json").
                body(jiraBody(issue)).
                // вынести "https://jira.hillel.it/rest/api/2/" в какую-нибудь константу?
                when().log().all().post("https://jira.hillel.it/rest/api/2/issue/").
                then().statusCode(201).extract().asString();
        return response;
    }

    public static String getPriority(){
        return given().auth().preemptive().
                basic("knaimark", "1234567").
                when().log().all().get(  "https://jira.hillel.it/rest/api/2/priority").
                // а если запрос упадет?
                then().extract().response().asString();
    }

    public static String getProject(String project){
        return given().auth().preemptive().
                basic("knaimark", "1234567").
                when().log().all().get(  "https://jira.hillel.it/rest/api/2/project/" + project).
                then().extract().response().asString();
    }

}

