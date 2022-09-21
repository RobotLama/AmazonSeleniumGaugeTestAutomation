package driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    public static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(Driver.class);

    @BeforeSpec
    public static void beforeSpec(){
        logger.info("#BeforeSpec");
    }

    @BeforeScenario
    public void beforeScenario(){

        logger.info("#BeforeScenario");

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);

        //driver.manage().window().maximize();
        //driver.get("https://www.amazon.com.tr/");
    }

    @AfterScenario
    public void afterScenario(){

        logger.info("#AfterScenario");

        if (driver != null){
            driver.quit();
        }
    }

    @AfterSpec
    public static void afterSpec(){

        logger.info("#AfterSpec");
    }

}
