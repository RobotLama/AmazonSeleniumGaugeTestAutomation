package pages;

import com.thoughtworks.gauge.Step;
import driver.Driver;

import methods.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class GuestPage extends Driver {

    By logoLocator = By.id("nav-logo-sprites");
    By searchBoxLocator = By.id("twotabsearchtextbox");
    By searchButtonLocator = By.id("nav-search-submit-button");
    By cookieButtonLocator = By.id("sp-cc-customize");
    By cookieSaveButtonLocator = By.xpath("//input[@name = 'accept']");

    Methods methods;

    public GuestPage(){

        methods = new Methods();
    }

    @Step("Go to the Amazon homepage")
    public void goSite(){
        driver.get("https://www.amazon.com.tr/");
        Assertions.assertTrue(methods.isElementVisible(logoLocator,10));
    }

    @Step("Accept cookies")
    public void acceptCookies(){

        Assertions.assertTrue(methods.isElementClickable(cookieButtonLocator,10));
        methods.clickElement(cookieButtonLocator);
        Assertions.assertTrue(methods.isElementClickable(cookieSaveButtonLocator,10));
        methods.clickElement(cookieSaveButtonLocator);

    }

    @Step("Search <>")
    public void search(String searchKey){

        Assertions.assertTrue(methods.isElementClickable(searchBoxLocator,10));
        methods.sendKeys(searchBoxLocator,searchKey);
        Assertions.assertTrue(methods.isElementClickable(searchButtonLocator,10));
        methods.clickElement(searchButtonLocator);
    }


}
