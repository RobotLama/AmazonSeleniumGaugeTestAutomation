package methods;

import driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Methods {

    WebDriver driver;
    JavascriptExecutor jsDriver;
    FluentWait<WebDriver> fluentWait;
    private static final Logger logger = LogManager.getLogger(Driver.class);

    public Methods() {
        this.driver = Driver.driver;
        jsDriver = (JavascriptExecutor) driver;
        fluentWait = setFluentWait(30);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout) {
        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public WebElement findElementWait(By by) {
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void clickElement(By by) {

        findElementWait(by).click();
        logger.info(by.toString() + " element clicked");
    }

    public void clickElementJs(By by) {

        WebElement webElement = findElementWait(by);
        jsDriver.executeScript("arguments[0].click();", webElement);
        logger.info(by.toString() + " element clicked");
    }

    public void sendKeys(By by, String text) {
        findElementWait(by).sendKeys(text);
        logger.info("To " + by.toString() + " " + text + " is written");
    }

    public String getText(By by) {
        return findElementWait(by).getText();
    }

    public String getAttribute(By by, String attribute) {
        return findElementWait(by).getAttribute(attribute);
    }

    public void hoverElement(By by) {
        WebElement webElement = findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
        logger.info(by.toString() + " element hovered");
    }

    public String getValue(By by) {
        WebElement webElement = findElement(by);
        return jsDriver.executeScript("return arguments[0].value", webElement).toString();
    }

    public void scrollElementCenter(By by) {
        WebElement webElement = findElementWait(by);
        jsDriver.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'}];",
                webElement);
    }

    public void scrollElement(By by) {
        WebElement webElement = findElementWait(by);
        jsDriver.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void scrollElementIfNeeded(By by) {
        WebElement webElement = findElementWait(by);
        jsDriver.executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
    }

    public void clear(By by) {
        logger.info(by.toString() + "'s text cleaned");
    }

    public Select getSelect(By by) {
        return new Select(findElementWait(by));
    }

    public void selectByValue(By by, String value) {
        getSelect(by).selectByValue(value);
    }

    public boolean isElementVisible(By by, long timeout) {

        try {
            setFluentWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info(by.toString() + " element visible: true");
            return true;
        } catch (Exception e) {
            logger.info(by.toString() + " element visible: false ");
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean isElementClickable(By by, long timeout) {
        try {
            setFluentWait(timeout).until(ExpectedConditions.elementToBeClickable(by));
            logger.info(by.toString() + " true");
            return true;
        } catch (Exception e) {
            logger.info(by.toString() + " element clickable: false ");
            logger.error(e.getMessage());
            return false;
        }
    }

    public void waitBySeconds(long seconds) {
        waitByMilliSeconds(1000 * seconds);
        logger.info(seconds + " seconds waited");
    }

    public void waitByMilliSeconds(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (milliSeconds % 1000 != 0)
            logger.info(milliSeconds + " milliseconds waited");
    }

}
