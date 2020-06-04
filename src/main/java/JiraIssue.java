import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JiraIssue {
    private String projectKey;
    private String issueTypeDisplayName;
    private String priorityDisplayName;
    private List<String> labels;
    private String content;
    private String summary;
    private String jiraKey;

    public JiraIssue(String projectKey, String issueTypeDisplayName, String priorityDisplayName, List<String> labels, String content, String summary) {
        this.projectKey = projectKey;
        this.issueTypeDisplayName = issueTypeDisplayName;
        this.priorityDisplayName = priorityDisplayName;
        this.labels = labels;
        this.content = content;
        this.summary = summary;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getIssueTypeDisplayName() {
        return issueTypeDisplayName;
    }

    public String getPriorityDisplayName() {
        return priorityDisplayName;
    }

    public List<String> getLabels() {
        return labels;
    }

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String key) {
        jiraKey = key;
    }

    @Override
    public String toString() {
        return "JiraIssue{" +
                "projectKey='" + projectKey + '\'' +
                ", issueTypeDisplayName='" + issueTypeDisplayName + '\'' +
                ", priorityDisplayName='" + priorityDisplayName + '\'' +
                ", labels=" + labels +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", jiraKey='" + jiraKey + '\'' +
                '}';
    }

    public static class Builder {
        private String projectKey;
        private String issueTypeDisplayName;
        private String priorityDisplayName;
        private List<String> labels;
        private String content;
        private String summary;

        public Builder inProject(String projectKey) {
            this.projectKey = projectKey;
            return this;
        }

        public Builder ofType(String issueTypeDisplayName) {
            this.issueTypeDisplayName = issueTypeDisplayName;
            return this;
        }

        public Builder withPriority(String priorityDisplayName) {
            this.priorityDisplayName = priorityDisplayName;
            return this;
        }

        public Builder withLabels(List<String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder withDescription(String content) {
            this.content = content;
            return this;
        }

        public Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public JiraIssue create() throws IOException {
            /*System.out.println("ДОШЛИ");
            System.out.println(projectKey);
            System.out.println(issueTypeDisplayName);
            System.out.println(priorityDisplayName);
            System.out.println(labels);
            System.out.println(content);
            System.out.println(summary);*/

            JiraIssue issue = new JiraIssue(projectKey, issueTypeDisplayName, priorityDisplayName, labels, content, summary);
            // помимо нулловых значений,с полями могут быть и другие проблемы, например, несуществующий проджектКей
            if (projectKey == null || priorityDisplayName == null || summary == null || issueTypeDisplayName == null ||
                    content == null) {
                throw new IOException("not enough data: " + issue.toString());
            }
            postJiraIssue(issue);
            return issue;
        }
    }//Builder

    final static String urlJ = "https://jira.hillel.it/rest/api/2/issue";
    // тебе не хотелось иметь какой-нибудь http хелпер для такого случая?
    public static void postJiraIssue(JiraIssue issue) throws IOException {
        final HttpsURLConnection httpCon = (HttpsURLConnection) new URL(urlJ).openConnection();

        final String username = "catharine.afanasyeva";//System.getProperty("username");
        final String password = "catharine";//System.getProperty("password");
        final String creds = String.format("%s:%s", username, password);
        httpCon.setRequestProperty("Authorization", String.format("Basic %s", new Base64().encodeToString(creds.getBytes())));

        httpCon.setDoOutput(true);
        final String body = jiraBody(issue);
        httpCon.setRequestMethod("POST");

        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Content-Length", "" + body.length());
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(body);
        out.close();
        final int responseCode = httpCon.getResponseCode();
        ArrayList<String> result = new ArrayList<String>();
        BufferedReader br;
        // хмммм.... =)
        try {
            br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Throwable t) {
            br = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }
        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
            if (responseCode != 201) {
                // а если как-то интерпретировать сложившуюся ситуацию и сообщить о ней пользователью ,а не просто бросать исключение?
                throw new IllegalArgumentException(String.valueOf(result));
            }
        }

    }

    private static String jiraBody(JiraIssue issue) {
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

}//JiraIssue
