package homeworks.hw2.PackageLogger;

public class StdLogger extends Logger {
    private static StdLogger instance;

    private StdLogger() {

    }

    public static StdLogger getInstance() {
        if (instance == null)
            instance = new StdLogger();
        return instance;
    }
    
}
