package base;

import org.openqa.selenium.WebDriver;
import page_objects.*;

public class PageObjectWrapper {

    private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private ThreadLocal<BasePage> basePage = new ThreadLocal<>();
    private ThreadLocal<HomePage> homePage = new ThreadLocal<>();

    protected void setDriver(WebDriver driver) {this.threadLocalDriver.set(driver);}

    protected WebDriver driver() {return this.threadLocalDriver.get();}

    protected void initPages(){
        basePage.set(new BasePage(driver()));
        homePage.set(new HomePage(driver()));
    }

    protected BasePage basePage() { return basePage.get(); }
    protected HomePage homePage() { return homePage.get(); }
}
