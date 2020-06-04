package Infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class TestEnvSetup {
    public WebDriver driver;
    public WebDriverWait wait;
    InfrastructureSetup run = new InfrastructureSetup();
   

    public void initializeTestData() {
        driver = run.setDriver(Browser.CHROME);
        wait = new WebDriverWait(driver, 15);
    }

    public String randomString(){
        String generatedString;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return generatedString = buffer.toString();
    }
}
