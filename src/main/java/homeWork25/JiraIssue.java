package homeWork25;

import homeWork25.controllers.ConfluenceController;
import homeWork25.controllers.JiraControler;
import homeWork25.exeption.JiraExeption;
import homeWork25.validation.Validation;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JiraIssue {
    private String projectKey;
    private String issueTypeDisplayName;
    private String priorityDisplayName;
    private List<String> labels;
    private String description;
    private String summary;
    private String jiraKey;

    // зачем это конструктор зависит от билдера?
    // Единственные поля, которые в JiraIssue имеют смысл это jiraKey и apiId,
    // которые тебе джира вернет как ответ на твой апи запрос при создании тикета.
    // может, айди проекта еще.
    private JiraIssue(Builder builder) {
        this.projectKey = builder.projectKey;
        this.issueTypeDisplayName = builder.issueTypeDisplayName;
        this.priorityDisplayName = builder.priorityDisplayName;
        this.labels = builder.labels;
        this.description = builder.description;
        this.summary = builder.summary;
        this.jiraKey = builder.jiraKey;
    }

    public static class Builder {
        private String projectKey;
        private String issueTypeDisplayName;
        private String priorityDisplayName;
        private List<String> labels;
        private String description;
        private String summary;
        private String jiraKey;

        public Builder() {
            // это должно было произойти в тесте,
            // так как именно там следовало пойти в конфлю и создать тикет
            this.projectKey = ConfluenceController.getProjectKey();
            this.issueTypeDisplayName = ConfluenceController.getType();
            this.summary = ConfluenceController.getSummary();
        }


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

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public JiraIssue create() throws JiraExeption {
            // незачем было плодить целый класс для валидаций. Им место прямо в этом методе.
            Validation validation = new Validation(summary, issueTypeDisplayName, projectKey, description, priorityDisplayName);
            validation.dataValidation();
            String response = new JiraControler().postJiraTicket(summary, issueTypeDisplayName,
                    projectKey, description, priorityDisplayName, labels);
            jiraKey = new JsonPath(response).getString("key");
            return new JiraIssue(this);
        }
    }

    public String getJiraKey() {
        return jiraKey;
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

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "JiraIssue{" +
                "projectKey='" + projectKey + '\'' +
                ", issueTypeDisplayName='" + issueTypeDisplayName + '\'' +
                ", priorityDisplayName='" + priorityDisplayName + '\'' +
                ", labels=" + labels +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", jiraKey='" + jiraKey + '\'' +
                '}';
    }
}
