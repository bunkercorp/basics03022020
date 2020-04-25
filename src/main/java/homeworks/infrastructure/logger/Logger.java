package homeworks.infrastructure.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Logger {
    private int counter = 0;

    // abstract
    public void log(String text){
        System.out.printf("%s%s\n", logPrefix(), text);
    }

    // private , раз на то пошло
    protected String logPrefix() {
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        String dateStr = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
        return String.format("%d) %s [%s]: ", ++counter, dateStr, methodName);
    }
}
