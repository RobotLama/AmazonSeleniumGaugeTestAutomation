package pages;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import methods.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductPage extends Driver {

    By shippedByAmazonCheckboxLocator = By.xpath("//li[@aria-label = 'Amazon Tarafından Gönderilir']//div");
    Methods methods;

    public ProductPage() {

        methods = new Methods();
    }

    @Step("Click to the shipped by Amazon checkbox")
    public void clickShippedByAmazonCheckbox() {

        Assertions.assertTrue(methods.isElementClickable(shippedByAmazonCheckboxLocator,10));
        methods.clickElement(shippedByAmazonCheckboxLocator);
        methods.waitBySeconds(4);
    }


    @Step("Select products element <>")
    public void selectProductWithNumber(String elementNumber) {

        String selectedElement = "//div[@data-index=\"" + elementNumber + "\"]//div[2]//a";
        //String selectedElement = "//div[@data-index=\"" + elementNumber + "\"]//span[@data-component-type = \'s-product-image\']";
        Assertions.assertTrue(methods.isElementClickable(By.xpath(selectedElement),10));
        methods.clickElement(By.xpath(selectedElement));
    }

    @Step("Select random product")
    public void selectRandomProduct() {

        List<WebElement> productList = driver.findElements(By.cssSelector("div[data-index]"));

        Random random = new Random();
        int elementIndex = random.nextInt(productList.size() - 1);

        // Search results header's index is 0
        while (elementIndex == 0) {
            elementIndex = random.nextInt(productList.size() - 1);
        }

        // Procedure 1
        String randomElement = "//div[@data-index=\"" + elementIndex + "\"]//div[2]//a";
        Assertions.assertTrue(methods.isElementClickable(By.xpath(randomElement),10));
        methods.clickElement(By.xpath(randomElement));

        /* Procedure 2
        WebElement randomElement = productList.get(elementIndex);
        */
    }
}
