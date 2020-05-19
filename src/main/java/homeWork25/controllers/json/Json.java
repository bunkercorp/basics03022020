package homeWork25.controllers.json;

import java.util.List;

public class Json {

    public static String bodyForJiraTicket(String summary, String issueType,
                                           String project, String description,
                                           String priority, List<String> labels) {
        return String.format("{\n" +
                        "  \"fields\": {\n" +
                        "    \"summary\": \"%s\",\n" +
                        "   \n" +
                        "    \"issuetype\": {\n" +
                        "      \"name\": \"%s\"\n" +
                        "    },\n" +
                        "    \"project\": {\n" +
                        "       \"key\": \"%s\"\n" +
                        "    },\n" +
                        "    \n" +
                        "    \"description\": \"%s\",\n" +
                        "    \n" +
                        "    \"priority\": {\n" +
                        "      \"name\": \"%s\"\n" +
                        "    },\n" +
                        "    \"labels\": [\n" +
                        "            \"%s\",\n" +
                        "            \"%s\"\n" +
                        "        ]\n" +
                        "  }\n" +
                        "}",
                summary, issueType, project, description, priority,labels.get(0),
                labels.get(1));

    }
}
