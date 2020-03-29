package homeworks.hw2.infrastructure;

public enum Browser {
    CHROME, FIREFOX;

    public static Browser getBrowser() {
        String str = System.getenv("CM_BROWSER").toUpperCase();
        if (isChrome(str)) {
            return CHROME;
        } else if (isFF(str)) {
            return FIREFOX;
        }
        return CHROME;
    }

    private static boolean isChrome(String str) {

        return (str.contains("CHROME"));
    }

    private static boolean isFF(String str) {
        return (str.contains("FF"));

    }

}
