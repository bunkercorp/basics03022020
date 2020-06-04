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
    public void beforeClass() {
        initializeTestData();
    }

    @Test
    public void checkPriceTest() {
        //Dima's code
        datePicker.inputCity(city);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@data-list='']/li)[1]"))).getText().contains("Барселона"));
        datePicker.pickDesiredCity();
        datePicker.pickUpDatesOfTrip();

        //Igor's code
        //Заезд
        String actualText = driver.findElement(By.xpath("(//div[contains(@class,'field__display')])[1]")).getText();
        String expectedText = "вс, 30 авг.";
        Assert.assertEquals(actualText, expectedText);

        //Igor's code
        //Выезд
        String actualText1 = driver.findElement(By.xpath("(//div[contains(@class,'field__display')])[2]")).getText();
        String expectedText1 = "сб, 5 сент.";
        Assert.assertEquals(actualText1, expectedText1);

        //Dima's code
        datePicker.setOnePersonAsGuest();
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='xp__guests__count']//span)[1]")).getText(), "1 взрослый");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='xp__guests__count']//span)[3]")).getText(), "без детей");
        Assert.assertEquals(driver.findElement(By.xpath("(//span[@class='xp__guests__count']//span)[5]")).getText(), "1 номер");

        //Igor's code
        //Календарь
        driver.findElement(By.xpath("(//div[contains(@class,'xp__dates')])[1]")).click();

        int range = 5;
        Assert.assertEquals(driver.findElements(By.xpath("//td[contains(@class, 'date--in-range')]")).size(), range);

        int selected = 2;
        Assert.assertEquals(driver.findElements(By.xpath("//td[contains(@class, 'date--selected')]")).size(), selected);


        String actualText2 = driver.findElement(By.xpath("//div[@class='bui-calendar__display']")).getText();
        String expectedTex2 = "вс, 30 авг. - сб, 5 сент. (6 ночей)";
        Assert.assertEquals(actualText2, expectedTex2);

        //Dima's code
        datePicker.clickSubmitButton();

        //Now we are on the search results page
        Assert.assertEquals(driver.findElement(searchResults.breadCrumbMain).getText(), "Главная");
        Assert.assertEquals(driver.findElement(searchResults.breadCrumbCountry).getText(), "Испания");
        Assert.assertEquals(driver.findElement(searchResults.breadCrumbRegion).getText(), "Каталония");
        Assert.assertEquals(driver.findElement(searchResults.breadCrumbCity).getText(), "Барселона");
        Assert.assertEquals(driver.findElement(searchResults.breadCrumbSearch).getText(), "Результаты поиска");

        //Search result Validation
        //City assert
        Assert.assertEquals(driver.findElement(By.cssSelector("input#ss")).getAttribute("value"), "Барселона");
        //Check in date assert
        Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='sb-date-field__display'])[1]")).getText(), "воскресенье, 30 августа 2020");
        //Checkout date assert
        Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='sb-date-field__display'])[2]")).getText(), "суббота, 5 сентября 2020");
        //Guest assert
        Assert.assertEquals(driver.findElement(By.cssSelector("select#group_adults option")).getText(), "1 взрослый");
        Assert.assertEquals(driver.findElement(By.cssSelector("select#group_children option")).getText(), "Без детей");
        Assert.assertEquals(driver.findElement(By.cssSelector("select#no_rooms option")).getText(), "1 номер");

        //Pick hostels filter
        String listBefore = driver.findElement(By.cssSelector(".sr_header > h2")).getText();
        searchResults.pickHostelsFilter();
        waitForLoaderInvisibility();
        searchResults.openFirstHostelInList();
        String listAfter = driver.findElement(By.cssSelector(".sr_header > h2")).getText();
        //Assert results filtered
        Assert.assertNotEquals(listAfter, listBefore);


    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
