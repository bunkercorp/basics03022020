package infrastructure;

public enum Browser {
    FIREFOX, CHROME;

    static Browser current() {
        // вижу дублированный код
        // и да, Browser сам не решает, решает конфиг менеджер
        if (System.getenv("CM_BROWSER").toLowerCase().contains("firefox")) {
            return FIREFOX;
        }
        if (System.getenv("CM_BROWSER").toLowerCase().contains("chrome"))
            return CHROME;
        return null;
    }
}