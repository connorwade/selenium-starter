package page_objects;

import config.GlobalConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

public class BasePage {
    protected String baseUrl;

    protected WebDriver driver;
    protected SeleniumUtilities seleniumUtilities;

    public BasePage (WebDriver driver){
        this.driver = driver;
        baseUrl = GlobalConfig.AEM_AUTHOR_URL;
        seleniumUtilities = new SeleniumUtilities(driver, GlobalConfig.DEFAULT_WAIT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }
}
