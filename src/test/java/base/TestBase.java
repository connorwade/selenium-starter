package base;

import config.GlobalConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase extends PageObjectWrapper {

    @BeforeSuite
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);
        EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);

        driver().manage().timeouts().setScriptTimeout(GlobalConfig.DEFAULT_SCRIPT_TIMEOUT, TimeUnit.SECONDS);
        driver().manage().timeouts().implicitlyWait(GlobalConfig.DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver().manage().timeouts().pageLoadTimeout(GlobalConfig.DEFAULT_LOAD_TIMEOUT, TimeUnit.SECONDS);

        setDriver(driver());
        initPages();
    }

    @AfterMethod
    public void teardown() {
        driver().close();
        driver().quit();
    }

}