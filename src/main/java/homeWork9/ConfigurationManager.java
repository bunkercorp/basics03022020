package homeWork9;

import homeWork8.Browser;
import homeWork8.OS;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private Browser browser;
    private OS os;

    private ConfigurationManager() {
        String browser = System.getenv("CM_BROWSER")!= null ?
                System.getenv("CM_BROWSER"):"chrome";
        String os = System.getenv("CM_OS") != null ?
                System.getenv("CM_OS"):"windows";
        if (browser.contains("chrome"))
            this.browser = Browser.CHROME;
        else if (browser.contains("firefox"))
            this.browser = Browser.FIREFOX;
        if (os.contains("windows"))
            this.os = OS.WINDOWS;
        else if (browser.contains("linux"))
            this.os = OS.LINUX;
        else if (browser.contains("mac"))
            this.os = OS.MAC;
    }

    public static ConfigurationManager getConfigurationManager() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    public Browser getBrowser() {
        return browser;
    }

    public OS getOS() {
        return os;
    }
}
