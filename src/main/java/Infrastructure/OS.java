package Infrastructure;

public enum OS {
    WINDOWS,
    LINUX,
    MAC;

    public static OS currentOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("linux")) {
            return OS.LINUX;
        } else if (osName.contains("mac")) {
            return OS.MAC;
        } else {
            return null;
        }
    }
}
