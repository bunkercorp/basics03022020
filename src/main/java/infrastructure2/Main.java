package infrastructure2;
import infrastructure.logger.StdLogger;

public class Main {
    public static void main(String[] args) {
        StdLogger stdLogger = StdLogger.getInstance();
        stdLogger.log("Error");
        stdLogger.log("Error");
    }
}
