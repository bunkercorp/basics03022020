package homeworks.infrastructure;

public enum OS {
    WINDOWS, LINUX, MAC;

    static OS current() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.startsWith("windows")){
            return WINDOWS;
        } else if(os.startsWith("mac")){
            return MAC;
        }
        // freebsd ;)
        else {
            return LINUX;
        }

    }
}
