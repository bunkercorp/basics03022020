package infrastructure.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class StdLogger implements Logger {
    private static StdLogger instance = null;

    private StdLogger() {

    }

    public static StdLogger getInstance() {
        if (instance == null) {
            instance = new StdLogger();
        }
        return instance;
    }

    private static int counter = 1;

    public void log(String message) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        Date date = new Date();
        String dateNow = dateFormat.format(date);
        System.out.printf("%d) %s [%s]: %s\n", counter++, dateNow, methodName, message);
    }

    private static void toConsole(String msg) {
        StdLogger logToConsole = new StdLogger();
        logToConsole.log(msg);
    }

    public static void main(String[] args) {
        toConsole("loh");
        toConsole("lohnes");
        StdLogger kk = new StdLogger();
        kk.log("Lupito");
    }

}
