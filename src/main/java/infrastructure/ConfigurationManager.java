package infrastructure;


import logger.StdLogger;

public class ConfigurationManager {
    StdLogger log = StdLogger.getInstance();
    private static ConfigurationManager instance;

    private OS os;
    private Browser browser;

    private ConfigurationManager() {
        os = getOs();
        browser = getBrowser();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private OS getOs() {
        log.log("OS method");
        return OS.current();
    }

    private Browser getBrowser() {
        log.log("browser method");
        return Browser.current();
    }

    @Override
    public String toString() {
        return "ConfigurationManager{" +
                "log=" + log +
                ", os=" + os +
                ", browser=" + browser +
                '}';
    }
}
