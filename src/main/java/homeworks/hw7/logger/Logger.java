package homeworks.hw7.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

// abstract
public class Logger {
    private int counter = 1;
// abstract
    public void log(String content) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String dateStr = new SimpleDateFormat("HH:MM:ss:SSS").format(new Date());
        System.out.printf("%d) %s [%s]: %s\n", counter++, dateStr, methodName, content);
    }
}
