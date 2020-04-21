package infrastructure;

public enum OS {

    WINDOWS, LINUX, MAC;

    public static OS current() {
        String str = System.getProperty("os.name").toLowerCase();
        if (isWindows(str)) {
            return WINDOWS;
        } else if (isMac(str)) {
            return MAC;
        } else if (isUnix(str)) {
            return LINUX;
        }
        return WINDOWS;
    }

    private static boolean isWindows(String str) {

        return (str.contains("win"));
    }

    private static boolean isMac(String str) {
        return (str.contains("mac"));

    }

    private static boolean isUnix(String str) {

        return (str.contains("nix") || str.contains("nux") || str.contains("aix"));
    }
}

