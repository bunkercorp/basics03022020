package JiraIssue;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JiraIssue {


    final static String urlConfluence = "https://confluence.hillel.it/pages/viewpage.action?spaceKey=DIMAB&title=Request+Body+TEST";
    final static String urlJira = "https://jira.hillel.it/rest/api/2/issue";

    public JiraIssue() throws IOException {
    }

    // это должно быть в отдельном классе, так как не относится к джире
    public static String getResponseBody() throws IOException {
        final String username = System.getenv("LOGIN");
        final String password = System.getenv("PASSWORD");
        final HttpsURLConnection httpCon = (HttpsURLConnection) new URL(urlConfluence).openConnection();
        String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        httpCon.setRequestProperty("Authorization", "Basic " + encoded);
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("GET");
        ArrayList<String> result = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Throwable t) {
            br = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }
        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        return String.join("", result);
    }

    // а вернуть объектик с нужными полями? Тебе же все равно известно, какой смысл несет каждая строка в конфле.
    private static String[] parseResponseBody() throws IOException {
        String responseBody = getResponseBody().trim();
        Document doc = Jsoup.parse(responseBody);
        Elements tableData = doc.select("td");
        String dataForRequest = tableData.toString().replace("<td class=\"confluenceTd\">", "").
                replace(",", "").
                replace("</td>", "");
        return dataForRequest.split("\n");
    }

    // это должно было произойти в самом тесте
    private final String[] keyValue = parseResponseBody();

    private String inProject() throws IOException {
        //проект по дефолту
        if (!keyValue[1].trim().equals("AQA220")) {
            keyValue[1] = "AQA220";
        }
        return keyValue[1].trim();
    }

    private String ofType() throws IOException {
        String taskType = keyValue[3];
        //фу-фу-фу. При запросе на api/project/%id% тебе вернут объект, где в том числе будет поле issueTypes. От него и надо отталкиваться.
        // все методы билдера, по факту, просто предварительно собирают информацию, и она должна быть провалидирована в методе create
        List<String> types = Arrays.asList("Bug", "Task", "Improvement", "New Feature", "Epic");
        if (!types.contains(taskType)) {
            throw new IllegalArgumentException("not valid type of ticket");
        }
        return taskType;
    }

    private String withPriority() throws IOException {
        String priority = keyValue[9];
        // фу-фу-фу. api/priority дает тебе список приоритетов.
        List<String> priorities = Arrays.asList("Lowest", "Low", "Medium", "Normal", "High", "Highest", "Blocker", "Critial", "Очень срочно");
        if (!priorities.contains(priority)) {
            throw new IllegalArgumentException("not valid type of ticket");
        }
        return priority;
    }

    private Collection<String> withLabels() throws IOException {
        String[] labels = keyValue[11].split(" ");
        Collection<String> collection = new ArrayList<String>();
        Collections.addAll(collection, labels);
        return collection;
    }

    private String withDescription() throws IOException {
        return keyValue[7];
    }

    private String withSummary() throws IOException {
        return keyValue[5];
    }

    final String projectKey = inProject();
    final String issueType = ofType();
    final String priority = withPriority();
    final String description = withDescription();
    final String summary = withSummary();
    final Collection<String> labels = withLabels();

    public String getSummary() {
        return summary;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public Collection<String> getLabels() {
        return labels;
    }


    private String setBody() {
        String label = labels.toString().replace("[", "[\"").
                replace(",", "\",").
                replace("]", "\"]").
                replace(" ", "\"").
                replace("<p>", "").replace("</p>", "");
        // посмотри в сторону JSONObject
        return String.format("{\"fields\":{\"project\":{\"key\": \"%s\"}," +
                "\"summary\":\"%s\"," +
                "\"description\": \"%s\"," +
                "\"issuetype\": {\"name\": \"%s\"}," +
                "\"labels\": %s ," +
                "\"priority\": {\"name\": \"%s\"}}}", projectKey, summary, description, issueType, label, priority);
    }

    public void create() throws IOException {
        final String username = System.getenv("LOGIN");
        final String password = System.getenv("PASSWORD");
        // хелпер бы. К getResponseBody тоже относится
        final HttpsURLConnection httpCon = (HttpsURLConnection) new URL(urlJira).openConnection();
        String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        httpCon.setRequestProperty("Authorization", "Basic " + encoded);
        httpCon.setDoOutput(true);
        final String body = setBody();
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Content-Length", "" + body.length());
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(body);
        out.close();
        final int responseCode = httpCon.getResponseCode();
        ArrayList<String> result = new ArrayList<String>();
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Throwable t) {
            br = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }

        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
            //для подстраховки
            if (responseCode != 201) {
                throw new IllegalArgumentException(String.valueOf(result));
            }
        }
    }

}
