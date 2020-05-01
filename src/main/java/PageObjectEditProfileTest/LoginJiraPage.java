package PageObjectEditProfileTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginJiraPage {

    WebDriver driver;
    //String url = "https://jira.hillel.it/secure/Dashboard.jspa";
    private final By inputLogin = By.cssSelector("#login-form-username");
    private final By inputPassword = By.cssSelector("#login-form-password");
    private final By submitButton = By.cssSelector("#login");
    private final By dropdownMenuJira = By.cssSelector("span.aui-icon.aui-icon-small.aui-iconfont-appswitcher");
    private final By confluenceMenuItem = By.cssSelector("a[title='https://confluence.hillel.it/']");


    public LoginJiraPage(WebDriver driver) {
        this.driver = driver;
    }

    //public static String url = "https://jira.hillel.it/secure/Dashboard.jspa";


    public void login() {
        String login = "The_Boyev";
        String password = "160686bd";
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public void pathToConfluence() {
        driver.findElement(dropdownMenuJira).click();
        driver.findElement(confluenceMenuItem).click();
    }



}
