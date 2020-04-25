package homeworks.infrastructure.logger;

public final class StdLogger extends Logger{
    private static StdLogger instance;

    static {
        instance = new StdLogger();
    }

    public static StdLogger getInstance() {
        return instance;
    }

    private StdLogger() {}

    // твоя архитектура позволяет StdLogger-у не знать о существовании logPrefix()
    @Override
    public void log(String text){
        System.out.printf("%s%s\n", logPrefix(), text);
    }
}
