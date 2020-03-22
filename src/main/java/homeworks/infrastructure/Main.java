package homeworks.infrastructure;

import infrastructure.logger.StdLogger;

public class Main {
    public static void main(String[] args){
        ConfigurationManager cfg = ConfigurationManager.getInstance();
        StdLogger.getInstance().log("текст");
        StdLogger.getInstance().log("текст2");
    }
}
