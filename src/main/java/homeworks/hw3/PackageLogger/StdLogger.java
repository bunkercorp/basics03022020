package homeworks.hw3.PackageLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

// final
public class StdLogger implements Logger {
    private static StdLogger instance;

    private StdLogger() {
    }

    public static StdLogger getInstance() {
        if (instance == null)
            instance = new StdLogger();
        return instance;
    }

    private int counter = 1;
    public void log(String content) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        System.out.printf("%d) %s [%s]: %s\n", counter++, dateStr, methodName, content);
    }
}




