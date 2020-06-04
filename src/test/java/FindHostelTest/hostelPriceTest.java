package FindHostelTest;

import infrastructure.TestEnvSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class hostelPriceTest extends TestEnvSetup {

    String city = "Барселона";

    @BeforeClass
    public void beforeClass(){
        initializeTestData();
    }

    @Test
    public void checkPriceTest(){
        datePicker.inputCity(city);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@data-list='']/li)[1]"))).getText().contains("Барселона"));
        datePicker.pickDesiredCity();
        datePicker.pickUpDatesOfTrip();
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
