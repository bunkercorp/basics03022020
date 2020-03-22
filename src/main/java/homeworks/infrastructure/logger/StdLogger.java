package homeworks.infrastructure.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class StdLogger implements Logger{
    private static StdLogger instance;

    static {
        instance = new StdLogger();
    }

    public static StdLogger getInstance() {
        return instance;
    }

    private int counter = 0;

    private StdLogger() {}

    @Override
    public void log(String text){
        System.out.printf("%s%s\n", logPrefix(), text);
    }

    private String logPrefix() {
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        String dateStr = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
        return String.format("%d) %s [%s]: ", ++counter, dateStr, methodName);
    }
}
