import infrastructure.ConfigurationManager;
import logger.StdLogger;

public class Main {

    public static void main(String[] args) {
        System.out.print("OS: ");
        System.out.println(ConfigurationManager.getConfigurationManager().getOS());
        System.out.print("Browser: ");
        System.out.println(ConfigurationManager.getConfigurationManager().getBrowser());
        StdLogger.getStdLogger().log("проверка связи 1. из мейн");

        toTestLogger();
    }

    public static void toTestLogger() {
        StdLogger.getStdLogger().log("проверка связи 2. из метода");
    }
}
