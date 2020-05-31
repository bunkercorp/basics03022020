import io.restassured.path.json.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ConfluenceTicketProvider {
    public static ConfluenceTicketProvider shared = new ConfluenceTicketProvider();

    public final String projectKey;
    public final String issueTypeDisplayName;
    public final String priorityDisplayName;
    public final List<String> labels;
    public final String content;
    public final String summary;

    private ConfluenceTicketProvider() {
        Document document = Jsoup.parse(getBodyStorageValue());
        projectKey = document.select("tr>td>p:nth-of-type(2)").text();
        issueTypeDisplayName = document.select("td:nth-child(2)").text();
        priorityDisplayName = document.select("td:nth-child(3)").text();
        labels = Arrays.asList(document.select("td:nth-child(4) > p").text().split(", "));
        content = document.select("td:nth-child(5)").text();
        summary = document.select("td:nth-child(6)").text();
    }

    //      https://confluence.hillel.it/display/ABJ/Kater
    private String getBodyStorageValue() {
        String body = given().auth().preemptive().basic("knaimark", "1234567").
                queryParam("expand", "body.storage").
                when().log().all().get("https://confluence.hillel.it/rest/api/content/40140810").
                then().extract().response().getBody().prettyPrint();
        return new JsonPath(body).getString("body.storage.value");
    }
}


