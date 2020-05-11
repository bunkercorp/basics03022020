package JiraIssue;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;

public class JiraIssue {


    final static String urlConfluence = "https://confluence.hillel.it/pages/viewpage.action?spaceKey=DIMAB&title=Request+Body+TEST";
    final static String urlJira = "https://jira.hillel.it/rest/api/2/issue";

    public JiraIssue() throws IOException {
    }

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

    private static String[] parseResponseBody() throws IOException {
        String responseBody = getResponseBody().trim();
        String[] parts = responseBody.split("<tbody>");
        String shortenedResponse = "" + parts[1];
        String[] parts2 = shortenedResponse.split("</tbody>");
        String shortenedResponse2 = parts2[0] + "";
        String dataForRequest = shortenedResponse2.replace(" class=\"confluenceTh\"", "").
                replace(" class=\"confluenceTd\"", "").
                replace("<tr>", "").
                replace("</tr>", "").
                replace("<th colspan=\"2\">Request data<br/><br/></th>", "").
                replace("<td>", "").
                replace("</td>", "\n").
                replace(",", "");
        return dataForRequest.split("\n");
    }


    private String inProject() throws IOException {
        String[] keyValue = parseResponseBody();
        if (!keyValue[1].trim().equals("AQA220")) {
            keyValue[1] = "AQA220";
        }
        return keyValue[1].trim();
    }

    private String ofType() throws IOException {
        String[] keyValue = parseResponseBody();
        return keyValue[3].trim();
    }

    private String withPriority() throws IOException {
        String[] keyValue = parseResponseBody();
        return keyValue[9].trim();
    }

    private Collection<String> withLabels() throws IOException {

        String[] keyValue = parseResponseBody();
        String[] labels = keyValue[11].split(" ");
        Collection<String> collection = new ArrayList<String>();
        Collections.addAll(collection, labels);
        return collection;
    }

    private String withDescription() throws IOException {
        String[] keyValue = parseResponseBody();
        return keyValue[7];
    }

    private String withSummary() throws IOException {
        String[] keyValue = parseResponseBody();
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
        }
        if (responseCode != 201) {
            throw new IllegalArgumentException(String.valueOf(result));
        }
    }

    public static void main(String[] args) throws IOException {
        JiraIssue is = new JiraIssue();
        System.out.println(is.labels);
    }

}
