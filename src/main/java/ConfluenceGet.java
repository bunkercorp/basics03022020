import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class ConfluenceGet {
    public static void main(String[] args) throws IOException {
        ConfluenceGet inst = new ConfluenceGet();
        System.out.println(inst.getProjectKey());
        System.out.println(inst.getIssueTypeDisplayName());
        System.out.println(inst.getPriorityDisplayName());
        System.out.println(inst.getLabels());
        System.out.println(inst.getContent());
        System.out.println(inst.getSummary());
    }

    final static String urlС = "https://confluence.hillel.it/pages/viewpage.action?spaceKey=CAT&title=for_hw10";

    public Map<String, String> confData = new HashMap<String, String>();

    public ConfluenceGet() throws IOException {
        Document document = Jsoup.parse(getResponse());
        confData.put("projectKey", document.select(" tr:nth-child(2) > td:nth-child(2)").text());
        confData.put("issueTypeDisplayName", document.select(" tr:nth-child(3) > td:nth-child(2)").text());
        confData.put("priorityDisplayName", document.select(" tr:nth-child(4) > td:nth-child(2)").text());
        confData.put("labels", document.select(" tr:nth-child(5) > td:nth-child(2)").text());
        confData.put("content", document.select(" tr:nth-child(6) > td:nth-child(2)").text());
        confData.put("summary", document.select(" tr:nth-child(7) > td:nth-child(2)").text());
    }

    public String getProjectKey(){
        return confData.get("projectKey");
    }
    public String getIssueTypeDisplayName(){
        return confData.get("issueTypeDisplayName");
    }
    public String getPriorityDisplayName(){
        return confData.get("priorityDisplayName");
    }
    public List<String> getLabels(){
        List<String> labels = Arrays.asList(confData.get("labels").split(", "));
        return labels;
    }
    public String getContent(){
        return confData.get("content");
    }
    public String getSummary(){
        return confData.get("summary");
    }

    public static String getResponse() throws IOException {
        final HttpsURLConnection httpCon = (HttpsURLConnection) new URL(urlС).openConnection();

        final String username = "catharine.afanasyeva";//System.getProperty("username");
        final String password = "catharine";//System.getProperty("password");
        final String creds = String.format("%s:%s", username, password);
        httpCon.setRequestProperty("Authorization", String.format("Basic %s", new Base64().encodeToString(creds.getBytes())));

        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("GET");

        ArrayList<String> result = new ArrayList<String>();
        BufferedReader bd;

        try {
            bd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Throwable t) {
            bd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }
        String line;
        while ((line = bd.readLine()) != null) {
            result.add(line);
        }
        return String.join("", result);
    }

}
