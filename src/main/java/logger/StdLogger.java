package logger;

import infrastructure.Browser;
import infrastructure.ConfigurationManager;
import infrastructure.OS;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class StdLogger extends Logger {

    private static StdLogger instance;
    private static int counter = 0;

    private StdLogger() {
    }

    public static StdLogger getInstance() {
        if (instance == null)
            instance = new StdLogger();
        return instance;
    }

    public void log(String str) {

        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));
        System.out.printf("%d) %s [%s]: %s\n", ++counter, dateStr, methodName, str);
    }
}

class Starter {

    public static void main(String[] args) {

        ConfigurationManager conf = ConfigurationManager.getInstance();
        OS os = conf.getOS();
        Browser browser = conf.getBrowser();
        StdLogger log = StdLogger.getInstance();
        log.log(os.name() + " " + browser.name());
        log.log(os.name() + " " + browser.name());
        log.log(os.name() + " " + browser.name());

    }

}