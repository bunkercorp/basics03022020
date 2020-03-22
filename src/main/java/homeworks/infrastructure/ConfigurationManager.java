package homeworks.infrastructure;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    static {
        instance = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return instance;
    }

    private OS os = OS.current();
    private Browser browser;

    private ConfigurationManager() {
        String tmp = System.getenv("CM_BROWSER");
        if (tmp != null ) {
            tmp = tmp.toLowerCase();
            if (tmp.contains("chrome")) {
                browser = Browser.CHROME;
            } else if(tmp.contains("firefox")) {
                browser = Browser.FIREFOX;
            }
        }

        tmp = System.getenv("CM_OS");
        if (tmp != null ) {
            tmp = tmp.toLowerCase();
            if (tmp.contains("windows")){
                os = OS.WINDOWS;
            } else if(tmp.contains("mac")){
                os = OS.MAC;
            }else {
                os = OS.LINUX;
            }
        }
    }

    public Browser getBrowser() {
        return browser;
    }

    public OS getOS() {
        return os;
    }
}
