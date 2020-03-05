package infrastructure;

public enum OS {
    WINDOWS, LINUX, MAC;

    static OS current() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            return WINDOWS;
        }
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            return LINUX;
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            return MAC;
        return null;
    }
}