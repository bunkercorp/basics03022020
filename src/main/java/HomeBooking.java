import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.Format;
import java.util.Collection;

public class HomeBooking {
    private final WebDriver browser;

    private By byCity = By.id("ss");
    private By byFirstCity = By.xpath("//li[@data-i=\"0\"]");
    private By byCalendar = By.cssSelector("span.sb-date-field__icon");
    private By byCalendarUi = By.cssSelector("div.bui-calendar");
    private By byCalendarNext = By.xpath("//div[@data-bui-ref=\"calendar-next\"]");
    private By byDataInText = By.xpath("//div[@data-placeholder=\"Заезд\"]");
    private By byDataOutText = By.xpath("//div[@data-placeholder=\"Отъезд\"]");
    private By byCalendarFooterText = By.cssSelector("div.bui-calendar__display");
    private By byGuest = By.id("xp__guests__toggle");
    private By byDecrGuest = By.xpath("//button[@aria-label=\"Взрослых: уменьшить количество\"]");
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

        if (result.equals("true")) return true;
        return false;
    }
    public void openCalendar() {
        browser.findElement(byCalendar).click();
    }
    public void clickNextMonthCalendar(){
        browser.findElement(byCalendarNext).click();
    }

    public boolean isCalendarDisappear() {
        String result = browser.findElement(byCalendarUi).getAttribute("style");
        if (result.contains("none")) return true;
        return false;
    }


    public String dateInText() {
        return browser.findElement(byDataInText).getText();
    }

    public String dateOutText() {
        return browser.findElement(byDataOutText).getText();
    }

    public void openGuestMenu() {
        browser.findElement(byGuest).click();
    }

    public void reduceGuest() {
        browser.findElement(byDecrGuest).click();
    }

    public String calendarText() {
        return browser.findElement(byCalendarFooterText).getText();
    }
}
