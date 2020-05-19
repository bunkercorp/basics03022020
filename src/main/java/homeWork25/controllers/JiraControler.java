package homeWork25.controllers;
import homeWork25.controllers.json.Json;
import homeWork25.credentials.Credentials;
import io.restassured.path.json.JsonPath;

import java.util.List;
import static io.restassured.RestAssured.given;

public class JiraControler {
    private static final String JIRA_URL = "https://jira.hillel.it";

    public String postJiraTicket(String summary, String issueType,
                                 String project, String description,
                                 String priority, List<String> labels) {

       String response = given().auth().preemptive().
                basic(Credentials.EMAIL.getCredential(),
                        Credentials.PASSWORD.getCredential()).
                header("Content-Type", "application/json").
                body(Json.bodyForJiraTicket(summary, issueType, project, description, priority,labels)).
                when().log().all().post(JIRA_URL + "/rest/api/2/issue/").
                then().statusCode(201).extract().asString();
       return response;
    }

    public String getPriority(){
        return given().auth().preemptive().
                basic(Credentials.EMAIL.getCredential(),
                        Credentials.PASSWORD.getCredential()).
                when().log().all().get(  JIRA_URL + "/rest/api/2/priority").
                then().extract().response().asString();
    }

    public String getProject(String project){
        return given().auth().preemptive().
                basic(Credentials.EMAIL.getCredential(),
                        Credentials.PASSWORD.getCredential()).
                when().log().all().get(  JIRA_URL + "/rest/api/2/project/" + project).
                then().extract().response().asString();
    }

}
