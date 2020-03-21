package infrastructure.logger;

public final class StdLogger extends Logger {
    private static StdLogger ourInstance = new StdLogger();

    public static StdLogger getInstance() {
        return ourInstance;
    }

    private StdLogger() {

    }
}
