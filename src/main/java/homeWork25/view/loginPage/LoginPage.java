package homeWork25.view.loginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(css = "#login-form-username")
    WebElement userName;

    @FindBy(css = "#login-form-password")
    WebElement password;

    @FindBy(css = "#login-form-submit")
    WebElement login;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String userName, String password){
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        this.login.click();
    }
}
