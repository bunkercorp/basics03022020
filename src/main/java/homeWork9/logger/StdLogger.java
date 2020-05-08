package homeWork9.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class StdLogger implements Logger {
    private int counter = 0;
    private static StdLogger ourInstance = new StdLogger();

    public static StdLogger getInstance() {
        return ourInstance;
    }

    private StdLogger() {

    }

    @Override
    public void log(String text){
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        System.out.printf("%d) %s [%s]: %s\n", ++counter, dateStr, methodName, text);
    }
}
