import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultBooking {
    private final WebDriver browser;

    private By byHeader = By.cssSelector("div.sr_header");
    private By byBtnSort = By.xpath("//a[@data-category=\"price\"]");//cssSelector("li.sort_price a");

    public ResultBooking(WebDriver browser) {
        this.browser = browser;
    }

    public void sortDecrase() {

        browser.findElement(byBtnSort).click();
    }

    public String getHeaderText(){
        return browser.findElement(byHeader).getText();
    }
}
