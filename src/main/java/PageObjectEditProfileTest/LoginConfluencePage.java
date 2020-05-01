package PageObjectEditProfileTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginConfluencePage {
    WebDriver driver;
    private final By inputLoginConfl = By.cssSelector("input#os_username");
    private final By inputPasswordConfl = By.cssSelector("input#os_password");
    private final By signInButtonConfl = By.cssSelector("input#loginButton");


    public LoginConfluencePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginInConfluence() {
        String login = "The_Boyev";
        String password = "160686bd";
        driver.findElement(inputLoginConfl).sendKeys(login);
        driver.findElement(inputPasswordConfl).sendKeys(password);
        driver.findElement(signInButtonConfl).click();
    }
}
