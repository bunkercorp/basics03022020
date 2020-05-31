package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    private By emailElement = By.cssSelector("input#login-form-username");
    private By passwordElement = By.cssSelector("input#login-form-password");
    private By signInButton = By.cssSelector("#login-form-submit");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void loginJira(){
        WebDriverWait waitElementVisible = new WebDriverWait(driver, 10);
        waitElementVisible.until(ExpectedConditions.presenceOfElementLocated(emailElement));
        driver.findElement(emailElement).sendKeys(System.getenv("login"));
        driver.findElement(passwordElement).sendKeys(System.getenv("password"));
        waitElementVisible.until(ExpectedConditions.presenceOfElementLocated(signInButton));
        driver.findElement(signInButton).click();
    }

}

