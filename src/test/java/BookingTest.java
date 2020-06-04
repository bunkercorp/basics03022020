import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class BookingTest {
    private WebDriver browser;

    private By mainBr = By.cssSelector(".bui-breadcrumb__text:nth-child(1) > .bui-link");
    private By country = By.cssSelector(".bui-breadcrumb__item:nth-child(2) .bui-link");
    private By region = By.cssSelector(".bui-breadcrumb__item:nth-child(3) .bui-link");
    private By city = By.cssSelector(".bui-breadcrumb__item:nth-child(4) .bui-link");
    private By searchResults = By.cssSelector("nav#breadcrumb a > div");

    private void validateBreadcramps() {
        Assert.assertEquals("Главная", browser.findElement(mainBr).getText());
        Assert.assertEquals("Испания", browser.findElement(country).getText());
        Assert.assertEquals("Каталония", browser.findElement(region).getText());
        Assert.assertEquals("Барселона", browser.findElement(city).getText());
        Assert.assertEquals("Результаты поиска", browser.findElement(searchResults).getText());
    }


    private final By place = By.xpath("//*[@id=\"ss\"]");
    private final By checkinDate = By.xpath("//*[@id=\"frm\"]/div[3]/div/div[1]/div[1]/div/div/div[1]/div/div[2]");
    private final By checkoutDate = By.xpath("//*[@id=\"frm\"]/div[3]/div/div[1]/div[2]/div/div/div[1]/div/div[2]");
    private final By quantityNights = By.xpath("//*[@id=\"frm\"]/div[3]/div/div[2]/div");
    private final By quantityPeople = By.xpath("//form/div[4]//div[1]/div/select");
    private final By children = By.xpath("//div[2]/div/select");

    private void validateYellowForm(){
        Assert.assertEquals("Барселона", browser.findElement(place).getAttribute("value"));
        Assert.assertEquals("воскресенье, 30 августа 2020", browser.findElement(checkinDate).getText());
        Assert.assertEquals("суббота, 5 сентября 2020", browser.findElement(checkoutDate).getText());
        Assert.assertEquals("6 ночей", browser.findElement(quantityNights).getText());
/*
        Select select = new Select(browser.findElement(quantityPeople));
        WebElement option = select.getFirstSelectedOption();
        Assert.assertEquals("1 взрослый", option.getText());

        Select selectC = new Select(browser.findElement(children));
        WebElement option1 = selectC.getFirstSelectedOption();
        Assert.assertEquals("Без детей", option1.getText());
*/
    }


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
        //home.openCalendar();
        home.clickNextMonthCalendar();
        home.clickNextMonthCalendar();
        home.setDateFrom(8,30);
        home.setDateTo(9,5);
        assertTrue(home.isCalendarDisappear());

        assertTrue(home.dateInText().contains("30 авг."));
        assertTrue(home.dateOutText().contains("5 сент"));
       // assertTrue(home.calendarText().contains("вс, 30 авг. - сб, 5 сент. (6 ночей)"));
        home.openCalendar();
        assertTrue(home.checkedDate(8,30));
        assertTrue(home.checkedDate(9,5));

        home.openGuestMenu();
        home.reduceGuest();

        String  people_amount = browser.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/form/div[1]/div[3]/label/span[2]/span[1]")).getText();
        Assert.assertEquals("1 взрослый", people_amount);
        ResultBooking result = home.checkPrice();
        Thread.sleep(2000);
        validateBreadcramps();

        validateYellowForm();

        List<WebElement> oldnames = browser.findElements(By.cssSelector(
                ".sr_flex_layout > .sr_item_content_slider_wrapper .hotel_name_link > .sr-hotel__name"));
        for (int i = 0; i < oldnames.size(); i++)
            System.out.println(oldnames.get(i).getText().trim());

        browser.findElement(By.cssSelector("div#filter_hoteltype > div[role='group'] > a:nth-of-type(4) > .bui-checkbox > .filter_item")).click();
        Thread.sleep(2000);
        System.out.println("----------------------");

        List<WebElement> newnames = browser.findElements(By.cssSelector(
                ".sr_flex_layout > .sr_item_content_slider_wrapper .hotel_name_link > .sr-hotel__name"));
        for (int i = 0; i < newnames.size(); i++)
            System.out.println(newnames.get(i).getText().trim());

        Assert.assertNotEquals(oldnames, newnames);

        browser.findElement(By.xpath("//div[@id='hotellist_inner']/div[1]/div[2]//a[1]/span[1]")).click();

    }


}
