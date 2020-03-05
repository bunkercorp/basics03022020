package infrastructure;

import logger.StdLogger;

public enum OS {
    WINDOWS,
    LINUX,
    MAC;
    public static OS current(){
        String os = System.getProperty("os.name").toLowerCase();
//        StdLogger logger = StdLogger.getInstance();
//        logger.log("in OS current");
        if(os.contains("windows")) return WINDOWS;
        if(os.contains("linux")) return LINUX;
        if(os.contains("mac")) return MAC;
        return null;
    }
}
