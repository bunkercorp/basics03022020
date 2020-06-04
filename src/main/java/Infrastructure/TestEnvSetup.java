package infrastructure;

import PageObjectCalendar.TripDatePickerSelectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestEnvSetup {
    public WebDriver driver;
    public WebDriverWait wait;
    Infrastructure.InfrastructureSetup run = new Infrastructure.InfrastructureSetup();
    public TripDatePickerSelectors datePicker;

    public void initializeTestData() {
        driver = run.setDriver(Infrastructure.Browser.CHROME);
        wait = new WebDriverWait(driver, 15);
        datePicker = new TripDatePickerSelectors(driver);
    }

    public boolean waitForTextTobeCSS(String locator, String text){
        return wait.until(ExpectedConditions.textToBe(By.cssSelector(locator), text));
    }

    public boolean waitForTextTobeXPath(String locator, String text){
        return wait.until(ExpectedConditions.textToBe(By.xpath(locator), text));
    }

}
