package infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestEnvSetup {
    public WebDriver driver;
    public WebDriverWait wait;
    Infrastructure.InfrastructureSetup run = new Infrastructure.InfrastructureSetup();
   

    public void initializeTestData() {
        driver = run.setDriver(Infrastructure.Browser.CHROME);
        wait = new WebDriverWait(driver, 15);
    }


}
