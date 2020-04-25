package infrastructure;

public enum OS {
    WINDOWS, LINUX, MAC;

    static OS current() {
        // передизайнить бы сие под свич-кейс..
        // а вот что действительно смущает, так это троекратное System.getProperty("os.name").toLowerCase()
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