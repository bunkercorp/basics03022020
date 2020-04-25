package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StdLogger implements Logger {
    static int counter = 0;
    private static StdLogger instance;

    private StdLogger() {
    }

    public static StdLogger getInstance() {
        if (instance == null) {
            instance = new StdLogger();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        // err?
        System.err.println(++counter + " " + dateStr + "[" + methodName + "]" + " " + message);
    }
}