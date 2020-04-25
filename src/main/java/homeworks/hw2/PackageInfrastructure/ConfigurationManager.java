package homeworks.hw2.PackageInfrastructure;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Browser browser;
    private OS os;

    public static ConfigurationManager getInstance() {
        if (instance == null)
            // instance = new ConfigurationManager();
            return new ConfigurationManager();
        return instance;
    }

    public Browser getBrowser() {
        return browser;
    }

    public OS getOs() {
        return os;
    }

    private ConfigurationManager() {
        String browser = System.getenv("CM_Browser");
        if (browser.contains("chrome"))
            this.browser = Browser.CHROME;
        if (browser.contains("firefox"))
            this.browser = Browser.FIREFOX;
        // где обработка CM_OS?
    }

}




