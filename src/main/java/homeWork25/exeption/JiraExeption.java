package homeWork25.exeption;

public class JiraExeption extends Exception {
    public JiraExeption() {
        super();
    }

    public JiraExeption(String message) {
        super(message);
    }

    public JiraExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public JiraExeption(Throwable cause) {
        super(cause);
    }

    protected JiraExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
