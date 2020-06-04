import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class BookingTest {
    private WebDriver browser;
    @BeforeClass
    public void setUpBrowser(){
        final String binPath = String.format("%s/bin/chromedriver.exe",System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver",binPath);
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        browser.get("https://booking.com");
    }
    @AfterClass
    public void tearDown(){
      //  browser.close();
    }

    @Test
    public void testBook() throws InterruptedException {
        HomeBooking home = new HomeBooking(browser);
        home.setWhereGoing("Barcelona");

        Thread.sleep(2000);
        home.selectFirstCity();
        Thread.sleep(2000);
        home.openCalendar();
        home.clickNextMonthCalendar();
        home.clickNextMonthCalendar();
        home.setDateFrom(8,30);
        home.setDateTo(9,5);
        home.openCalendar();
        assertTrue(home.checkedDate(8,30));
        assertTrue(home.checkedDate(9,5));
        /*
        ResultBooking result = home.checkPrice();
        assertTrue(result.getHeaderText().contains("Одесса"));
        Thread.sleep(2000);
        result.sortDecrase();
        */

    }
}
