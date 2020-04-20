package infrastructure;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private OS os;
    private Browser browser;

    private ConfigurationManager() {
        this.os = OS.current();
        this.browser = (System.getenv("CM_BROWSER").toLowerCase().contains("chrome")) ? Browser.CHROME :
                (System.getenv("CM_BROWSER").toLowerCase().contains("firefox")) ? Browser.FIREFOX :
                        null;
    }

    public static ConfigurationManager getConfigurationManager() {
        return instance = (instance == null) ? new ConfigurationManager() : instance;
    }

    public OS getOS() {
        return this.os;
    }

    public Browser getBrowser() {
        return this.browser;
    }

}//class ConfigurationManager

