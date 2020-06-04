
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.List;


public class JiraIssue {
    private String projectKey;
    private String issueTypeDisplayName;
    private String priorityDisplayName;
    private List<String> labels;
    private String content;
    private String summary;
    private String jiraKey;


    // Jira key по хорошему следует пробрасывать сразу в конструктор, а не иметь для него отдельный сеттер.
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
           // по хорошему, ошибку создания тикета надо было бы обработать прямо где-то здесь. !issue.isIssueExist() похоже на ошибку проектирования
            JiraIssue issue = new JiraIssue(projectKey, issueTypeDisplayName, priorityDisplayName, labels, content, summary);

            if (projectKey==null || priorityDisplayName==null || summary==null || issueTypeDisplayName==null ||
                    content==null || !issue.isPriorityExist() || !issue.isProjectExist() || !issue.isIssueExist()) {
                throw new IOException("Invalid issue data: " + issue.toString());
            }
            // что мешало парсить джира кей сразу в postJiraIssue?
            String response = JiraIssueCreator.postJiraIssue(issue);
            String jiraKey = new JsonPath(response).getString("key");
            issue.setJiraKey(jiraKey);
            return issue;
        }
    }

    private boolean isPriorityExist() {
        // эта конструкция не имеет ни малейшего смысла
        new JiraIssueCreator();
        JsonPath jsonPath = new JsonPath(JiraIssueCreator.getPriority());
        return jsonPath.getList("name").contains(priorityDisplayName);
    }

    // ты серьезно? апи дока говорит что ответ придет с 200 кодом если проект существует, и 404 если нет.
    private boolean isProjectExist() {
        return !JiraIssueCreator.getProject(projectKey).contains("Error!");
    }

    // ??? апи дока говорит что при успешном создании апи ответит кодом 201 и 400 если что то пошло не так == тикет не создался. Этот метод похож на костыль.
    private boolean isIssueExist() {
        new JiraIssueCreator();
        JsonPath jsonPath = new JsonPath(JiraIssueCreator.getProject(projectKey));
        return jsonPath.getList("issueTypes.name").contains(issueTypeDisplayName);
    }

    @Override
    public String toString() {
      // джира кея и апи айди с головой хватает.
        return "JiraIssue{" +
                "projectKey='" + projectKey +
                ", issueTypeDisplayName='" + issueTypeDisplayName +
                ", priorityDisplayName='" + priorityDisplayName +
                ", labels=" + labels +
                ", description='" + content +
                ", summary='" + summary +
                ", jiraKey='" + jiraKey +
                '}';
    }
}
