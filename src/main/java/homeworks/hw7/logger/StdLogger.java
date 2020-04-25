package homeworks.hw7.logger;

// final
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
