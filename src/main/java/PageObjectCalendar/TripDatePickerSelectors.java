package PageObjectCalendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TripDatePickerSelectors {
    WebDriver driver;

    public TripDatePickerSelectors(WebDriver driver) {
        this.driver = driver;
    }

    final By searchCityInput = By.xpath("//input[@id='ss']");
    final By searchResult = By.xpath("(//ul[@data-list='']/li)[1]");
    final By calendarNextButton = By.xpath("//div[@data-bui-ref='calendar-next']");
    final By checkInDate = By.xpath("//td[@data-date='2020-08-30']");
    final By checkoutDate = By.xpath("//td[@data-date='2020-09-05']");

    public void inputCity(String city){
        driver.findElement(searchCityInput).sendKeys(city);
    }

    public void pickDesiredCity(){
        driver.findElement(searchResult).click();
    }

    public void pickUpDatesOfTrip(){
        driver.findElement(calendarNextButton).click();
        driver.findElement(calendarNextButton).click();
        driver.findElement(checkInDate).click();
        driver.findElement(checkoutDate).click();
    }

}
