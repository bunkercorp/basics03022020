package homeworks.hw2;

import homeworks.hw2.PackageLogger.Logger;
import homeworks.hw2.PackageLogger.StdLogger;

public class Main {
    public static void main(String[] args) {
        StdLogger.getInstance().log("First");
        StdLogger.getInstance().log("Second");
        StdLogger.getInstance().log("Third");
        StdLogger.getInstance().log("Forth");
    }
}
