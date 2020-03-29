package homeworks.hw3.infrastructure;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private static OS os;
    private static Browser br;

    private ConfigurationManager() {
        os = OS.current();
        br = Browser.getBrowser();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

    public OS getOS() {

        return os;
    }

    public Browser getBrowser() {

        return br;
    }

}
