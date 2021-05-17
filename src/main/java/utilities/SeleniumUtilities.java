package utilities;

import config.GlobalConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumUtilities {
    private WebDriver driver;
    private int defaultTimeOutInSeconds;

    public SeleniumUtilities(WebDriver driver, int defaultTimeOutInSeconds) {
        this.driver = driver;
        this.defaultTimeOutInSeconds = defaultTimeOutInSeconds;
    }

    public void waitUntilClickable(WebElement element) throws Error{
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitUntilLocated(WebElement element) throws Error {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated((By) element));
    }

    public void waitUntilVisible(WebElement element) throws Error {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilUrlMatches(WebElement element, String url) throws Error {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.urlMatches(url));
    }

    public void waitThenClick(WebElement element) throws Error {
        waitUntilClickable(element);
        element.click();
    }

    public void waitUntilInvisible(WebElement element) throws Error {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilAttributeHasValue(WebElement element, String attribute, String value) {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitUntilAttributeHasEitherValue(WebElement element, String attribute, String value, String otherValue) {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(
                        ExpectedConditions.or(
                                ExpectedConditions.attributeContains(element, attribute, value),
                                ExpectedConditions.attributeContains(element, attribute, otherValue)
                        )
                );
    }

    public void waitUntilElementContainsText(WebElement element, String text) {
        new WebDriverWait(driver, defaultTimeOutInSeconds)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void catchElementThenClick(By by) {
        WebElement element = driver.findElement(by);
        waitUntilVisible(element);
        waitUntilClickable(element);
        element.click();
    }

    public void clearElementThenAddValue(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public Boolean doesElementExistOnPageXpath(String xpath) {
        return doesElementExistOnPageBy(By.xpath(xpath));
    }

    public Boolean doesElementExistOnPageClassName(String className) {
        return doesElementExistOnPageBy(By.className(className));
    }

    public Boolean doesElementExistOnPageBy(By by) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Boolean elementDoesExist = driver.findElements(by).size() != 0;
        driver.manage().timeouts().implicitlyWait(GlobalConfig.DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        return elementDoesExist;
    }

    public void actionHoverClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.perform();
    }

    /*
    Not strictly needed, but very helpful for debugging the iframe situation on WF
     */
    public void refreshWFPage(){
        driver.navigate().refresh();
        if(GlobalConfig.DEBUG_MODE) {
            System.out.println("I have refreshed the page");
            JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
            String currentFrame;
            currentFrame = (String) jsExecutor.executeScript("let frame = self.WFBuildConfig ? self.WFBuildConfig.kaminoFile : 'DOM';return frame");
            System.out.println("In frame - " + currentFrame);
            System.out.println("In URL: " + driver.getCurrentUrl());
        }
    }

    public void aemDropdownsSelectItem(WebElement btnEl, List<WebElement> listEls, String desiredSelection) {
        btnEl.click();
        WebElement selectionItem = listEls.stream()
                .filter(status -> status.getAttribute("textContent").contains(desiredSelection)).findAny().orElse(null);
        selectionItem.click();
    }

    public String getValueAttributeOfElement(WebElement element) {
        return element.getAttribute("value");
    }

    public Boolean doesElementHaveAttribute(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if(value != null) {
                result = true;
            }
        } catch(Exception e) {}
        return result;
    }

    public void jsClicker(WebElement el) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", el);
    }
}
