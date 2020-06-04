package PageObjectSearchResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultSelectors {

    WebDriver driver;

    public SearchResultSelectors(WebDriver driver) {
        this.driver = driver;
    }

   public By breadCrumbMain = By.cssSelector(".bui-breadcrumb__text:nth-child(1) > .bui-link");
   public By breadCrumbCountry = By.cssSelector(".bui-breadcrumb__item:nth-child(2) .bui-link");
   public By breadCrumbRegion = By.cssSelector(".bui-breadcrumb__item:nth-child(3) .bui-link");
   public By breadCrumbCity = By.cssSelector(".bui-breadcrumb__item:nth-child(4) .bui-link");
   public By breadCrumbSearch = By.cssSelector(".hp-breadcrumb__item_masked_link > .bui-breadcrumb__text");

}
