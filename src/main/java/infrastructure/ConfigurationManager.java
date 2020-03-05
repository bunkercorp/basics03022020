package infrastructure;

import logger.StdLogger;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private OS os;
    private Browser browser;
    public static ConfigurationManager getInstance(){
        if(instance==null)
            instance = new ConfigurationManager();
        return instance;
    }
    private ConfigurationManager(){

        String invOs = (System.getenv("CM_OS") != null ? System.getenv("CM_OS") : "linux").toLowerCase();
        if(invOs.contains("windows")) os = OS.WINDOWS;
        if(invOs.contains("linux")) os = OS.LINUX;
        if(invOs.contains("mac")) os = OS.MAC;

        String invBrowser = (System.getenv("CM_BROWSER") != null ? System.getenv("CM_BROWSER") : "firefox").toLowerCase();
        if(invBrowser.contains("chrome")){
            browser = Browser.CHROME;
        }else {
            browser = Browser.FIREFOX;
        }

//        StdLogger logger = StdLogger.getInstance();
//        logger.log("in costructor CM");
    }

    public OS getOS(){
//        StdLogger logger = StdLogger.getInstance();
//        logger.log("in getOS");
        return os;
    }

    public Browser getBrowser(){
//        StdLogger logger = StdLogger.getInstance();
//        logger.log("in getBrowser");
        return browser;
    }
    public void setBrowser(Browser browser){
        this.browser = browser;
    }

}
