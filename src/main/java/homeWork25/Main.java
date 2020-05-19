package homeWork25;

import homeWork25.controllers.ConfluenceController;
import homeWork25.controllers.JiraControler;
import homeWork25.exeption.JiraExeption;
import homeWork25.validation.Validation;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) throws JiraExeption {

        /*JiraIssue jiraIssue = new JiraIssue.Builder().
                inProject(ConfluenceController.getProjectKey()).
                ofType(ConfluenceController.getType()).
                withPriority(ConfluenceController.getPriority()).
                withDescription(ConfluenceController.getDescription()).
                withSummary(ConfluenceController.getSummary()).
                withLabels(ConfluenceController.getLabels()).create();

       System.out.println(jiraIssue.toString());*/



        // List<String> body = jsonPath.getJsonObject();
        // System.out.println(body.get(0));

    }
}
