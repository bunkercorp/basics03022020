import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.Format;

public class HomeBooking {
    private final WebDriver browser;

    private By byCity = By.id("ss");
    private By byFirstCity = By.xpath("//li[@data-i=\"0\"]");
    private By byCalendar = By.cssSelector("span.sb-date-field__icon");
    private By byCalendarNext = By.xpath("//div[@data-bui-ref=\"calendar-next\"]");
    private By byBtnSearch = By.className("sb-searchbox__button");
    public HomeBooking(WebDriver browser) {
        this.browser = browser;
    }

    public ResultBooking checkPrice() {
        browser.findElement(byBtnSearch).click();
        return new ResultBooking(browser);
    }

    public void setWhereGoing(String city) {
        browser.findElement(byCity).sendKeys(city);
    }

    public void selectFirstCity(){
        browser.findElement(byFirstCity).click();
    }




    private String dataForCalendar(int m, int d){
        String date = "";
        if (d<10 & m<10)
            date= String.format("2020-0%s-0%s",m,d);
        else if (d<10 & m>=10)
            date = String.format("2020-%s-0%s",m,d);
        else if (d>=10 & m<10)
            date = String.format("2020-0%s-%s",m,d);
        else
            date = String.format("2020-%s-%s",m,d);
        return date;
    }

    public void setDateFrom(int m,int d) {

        browser.findElement(By.xpath(String.format("//td[@data-date=\"%s\"]",dataForCalendar(m,d)))).click();
    }


    public void setDateTo(int m, int d) {
        browser.findElement(By.xpath(String.format("//td[@data-date=\"%s\"]",dataForCalendar(m,d)))).click();
    }

    public boolean checkedDate(int m, int d){
        String result =  browser.findElement(By.xpath(String.format("//td[@data-date=\"%s\"]",dataForCalendar(m,d)))).getAttribute("aria-selected");
        System.out.println(result);
        if (result.equals("true")) return true;
        return false;
    }
    public void openCalendar() {
        browser.findElement(byCalendar).click();
    }
    public void clickNextMonthCalendar(){
        browser.findElement(byCalendarNext).click();
    }
}
