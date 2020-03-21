package infrastructure;

import java.util.regex.Pattern;

public enum OS {
    WINDOWS,
    LINUX,
    MAC;

    public static OS current() {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.contains("windows")){
            return WINDOWS;
        } else if (system.contains("linux")){
            return LINUX;
        } else if (system.contains("mac")){
            return MAC;
        }
        return null;
    }
}
