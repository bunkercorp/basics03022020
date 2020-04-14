package homeworks.hw8.infrastructure;

public enum OS {
    WINDOWS, LINUX, MAC;

    public static OS current() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows"))
            return WINDOWS;
        if (os.contains("linux"))
            return LINUX;
        if (os.contains("mac"))
            return MAC;
        return null;
    }
}
