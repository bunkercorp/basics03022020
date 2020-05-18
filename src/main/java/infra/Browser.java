package infra;

public enum Browser {
    CHROME("chrome/chromedriver"),
    FIREFOX("firefox/geckodriver");

    public final String driverPathInBin;

    private Browser(String pathFragment) {
        this.driverPathInBin = pathFragment;
    }

}
