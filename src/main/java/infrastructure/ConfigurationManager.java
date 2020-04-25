package infrastructure;


import logger.StdLogger;

public class ConfigurationManager {
    StdLogger log = StdLogger.getInstance();
    private static ConfigurationManager instance;

    private OS os;
    private Browser browser;

    private ConfigurationManager() {
    // CM_OS, CM_BROWSER должны были анализироваться где-то тут, по логике этого класса
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
        // this.os , не?
        return OS.current();
    }

    private Browser getBrowser() {
        log.log("browser method");
        // this.browser , не?
        return Browser.current();
    }

    // надо полагать, это для дебага
    @Override
    public String toString() {
        return "ConfigurationManager{" +
                "log=" + log +
                ", os=" + os +
                ", browser=" + browser +
                '}';
    }
}
