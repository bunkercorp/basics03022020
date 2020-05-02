package infrastructure.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Logger {
    private int counter = 0;

    // abstract
    public void log(String text){
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        System.out.printf("%d) %s [%s]: %s\n", ++counter, dateStr, methodName, text);
    }
}
