package page_objects;

import config.GlobalConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private final String username = GlobalConfig.AEM_USER;
    private final String password = GlobalConfig.AEM_PASSWORD;

    /*
     * AEMLogin is the Page Object Model for the login page for AEM.
     * */
    public HomePage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    //Login
    @FindBy(xpath = "//h1[text()='Welcome to Adobe Experience Manager']")
    private WebElement aemWelcomeTitle;

    @FindBy(css = ".coral3-Accordion-label")
    private WebElement openAdminLogin;

    @FindBy(css = "#username")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#submit-button")
    private WebElement submitFormButton;

    public void goToAem() {
        driver.get(baseUrl);
    }

    public void login() {
        goToAem();
        Boolean adminDropdownExists  = seleniumUtilities.doesElementExistOnPageClassName(".coral3-Accordion-label");
        if( adminDropdownExists ) {
            openAdminLogin.click();
        }
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitFormButton.click();
    }
}
