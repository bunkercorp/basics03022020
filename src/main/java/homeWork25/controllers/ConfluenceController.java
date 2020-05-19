package homeWork25.controllers;
import homeWork25.credentials.Credentials;
import io.restassured.path.json.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ConfluenceController {
    private static final String CONF_URL = "https://confluence.hillel.it";
    private static final String CONF_PATH = "/rest/api/content/";
    private static final int PAGE_ID = 38305988;

    // !!!! моя страница  https://confluence.hillel.it/display/OG/AQA+table

    public static String getConfluencePage(){
       return given().auth().preemptive().
                basic(Credentials.EMAIL.getCredential(),
                        Credentials.PASSWORD.getCredential()).
                queryParam("expand","body.storage").
                when().log().all().get(CONF_URL + CONF_PATH + PAGE_ID).
                then().extract().response().asString();
    }

    public static String getProjectKey(){
        Document doc = Jsoup.parse(getBodyValue());
        return doc.select("tr:nth-child(2) td:nth-child(2)").text();
    }

    public static String getType(){
        Document doc = Jsoup.parse(getBodyValue());
        return doc.select("tr:nth-child(3) td:nth-child(2)").text();
    }

    public static String getPriority(){
        Document doc = Jsoup.parse(getBodyValue());
        return doc.select("tr:nth-child(4) td:nth-child(2)").text();
    }

    public static String getDescription(){
        Document doc = Jsoup.parse(getBodyValue());
        return doc.select("tr:nth-child(5) td:nth-child(2)").text();
    }

    public static String getSummary(){
        Document doc = Jsoup.parse(getBodyValue());
        return doc.select("tr:nth-child(6) td:nth-child(2)").text();
    }

    public static List<String> getLabels(){
        Document doc = Jsoup.parse(getBodyValue());
        String labels = doc.select("tr:nth-child(7) td:nth-child(2)").text();
        return new ArrayList<>(Arrays.asList(labels.split(",")));
    }

    private static String getBodyValue(){
        return new JsonPath(getConfluencePage()).
                getString("body.storage.value");
    }
}
