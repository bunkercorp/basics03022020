package PageObjectEditProfileTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileConfluencePage {
    WebDriver driver;
    private final By editProfileButton = By.cssSelector("span.aui-iconfont-edit");
    private final By phoneInput = By.cssSelector("#userparam-phone");
    private final By webSiteInput = By.cssSelector("#userparam-website");
    private final By bioInput = By.cssSelector("#personalInformation");
    private final By positionInput = By.cssSelector("#userparam-position");
    private final By departmentInput = By.cssSelector("#userparam-department");
    private final By locationInput = By.cssSelector("#userparam-location");
    private final By saveChangesButton = By.cssSelector("#confirm");

    final String bio = "Боритесь та поборете";
    final String phone = "555-555-555";
    final String webSite = "http://taras.grygorovych.ua";
    final String position = "Проти журби у функцiях";
    final String department = "QA";
    final String location = "Батькiвщина";

    public ProfileConfluencePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEditProfileButton() {
        driver.findElement(editProfileButton).click();
    }

    public void editPhone(String phone) {
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void editWebSite(String webSite) {
        driver.findElement(webSiteInput).clear();
        driver.findElement(webSiteInput).sendKeys(webSite);
    }

    public void editBio(String bio) {
        driver.findElement(bioInput).clear();
        driver.findElement(bioInput).sendKeys(bio);
    }

    public void editPosition(String position) {
        driver.findElement(positionInput).clear();
        driver.findElement(positionInput).sendKeys(position);
    }

    public void editDepartment(String depatrment) {
        driver.findElement(departmentInput).clear();
        driver.findElement(departmentInput).sendKeys(depatrment);
    }

    public void editLocation(String location) {
        driver.findElement(locationInput).clear();
        driver.findElement(locationInput).sendKeys(location);
    }

    public void clickSaveChangesButton(){
        driver.findElement(saveChangesButton).click();
    }

    public String getBio() {
        return bio;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }


}
