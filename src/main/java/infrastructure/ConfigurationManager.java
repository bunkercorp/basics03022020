package infrastructure;

public class ConfigurationManager {
    private static ConfigurationManager instance = null;

    private ConfigurationManager() {
        Browser browser = getBrowser();
        OS os = getOS();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            // обработка System.getenv("CM_BROWSER")\ System.getenv("CM_OS")
            // должна была происходить здесь.
            instance = new ConfigurationManager();
        }
        return instance;
    }


    private static Browser getBrowser() {
        // return browser;

        String browser = System.getenv("CM_BROWSER").toLowerCase();
        if (browser.contains("chrome")) {
            return Browser.CHROME;
        } else if (browser.contains("firefox")) {
            return Browser.FIREFOX;
        } else {
            return null;
        }
    }

    private static OS getOS() {
        // return os
        String os = System.getenv("CM_OS").toLowerCase();
        if (os.contains("win")) {
            return OS.WINDOWS;
        } else if (os.contains("ux")) {
            return OS.LINUX;
        } else if (os.contains("mac")) {
            return OS.MAC;
        } else {
            return null;
        }
    }

}
