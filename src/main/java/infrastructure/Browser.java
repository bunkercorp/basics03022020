package infrastructure;

public enum Browser {
    FIREFOX, CHROME;

    static Browser current() {
        if (System.getenv("CM_BROWSER").toLowerCase().contains("firefox")) {
            return FIREFOX;
        }
        if (System.getenv("CM_BROWSER").toLowerCase().contains("chrome"))
            return CHROME;
        return null;
    }
}