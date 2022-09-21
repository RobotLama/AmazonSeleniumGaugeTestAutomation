package pages;

import com.thoughtworks.gauge.Step;
import methods.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class SelectedProductPage {

    By addToCartButtonLocator = By.xpath("//input[@id = 'add-to-cart-button']");
    By cartCountLocator = By.id("nav-cart-count");
    Methods methods;

    public SelectedProductPage() {

        methods = new Methods();
    }

    @Step("Add to cart")
    public void selectProductWithNumber() {

        Assertions.assertTrue(methods.isElementClickable(addToCartButtonLocator,10));
        methods.clickElement(addToCartButtonLocator);

        String cartCountString = methods.getText(cartCountLocator);
        Assertions.assertEquals("1",cartCountString);
    }

}
