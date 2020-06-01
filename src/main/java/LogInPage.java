import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    WebDriver browser;

    public LogInPage(WebDriver driver) {
        this.browser = driver;
    }

    private final String first = "https://jira.hillel.it";
    private final By toLogin = By.xpath("//*[@id=\"user-options\"]/a");
    private final By userName = By.id("login-form-username");
    private final By pass = By.id("login-form-password");
    private final By submitBtn = By.xpath("//*[@id=\"login-form-submit\"]");


    public void login() {
        //maven -> m -> comand line -> mvn clean install -D suits=suits.xml -D username=catharine.afanasyeva -D password=catharine
        browser.get(first);
        browser.findElement(toLogin).click();
        browser.findElement(userName).sendKeys("catharine.afanasyeva");//System.getProperty("username")
        browser.findElement(pass).sendKeys("catharine");//System.getProperty("password")
        browser.findElement(submitBtn).click();
    }
}
