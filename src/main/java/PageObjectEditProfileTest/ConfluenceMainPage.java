package PageObjectEditProfileTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfluenceMainPage {
    WebDriver driver;
    private final By dropdownMenu = By.cssSelector("a#user-menu-link");
    private final By profileMenuItem = By.cssSelector("a#view-user-profile-link");

    public ConfluenceMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void accessProfile() {
        driver.findElement(dropdownMenu).click();
        driver.findElement(profileMenuItem).click();
    }

}
