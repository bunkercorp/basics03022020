package infrastructure;
import infrastructure.logger.Logger;
import infrastructure.logger.StdLogger;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        StdLogger stdLogger = StdLogger.getInstance();
        stdLogger.log("Error");
        stdLogger.log("Error");
        stdLogger.log("Error");
        stdLogger.log("Error");
    }
}
