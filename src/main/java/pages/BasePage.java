package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import io.appium.java_client.MobileElement;
import utils.Constant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected AppiumDriver<MobileElement> appiumDriver;
    protected BasePage(AppiumDriver<MobileElement>  driver){
        this.appiumDriver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    protected void waitForVisibility(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch (Exception err) {
            System.out.println("Error waitForElement : \r\n" + err);
        }
    }

    protected void waitForVisibility(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
    }

    protected void waitForSecond(long milliSec) {
        appiumDriver.manage().timeouts().implicitlyWait(milliSec, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e){
            System.out.println("Failed to Waiting: " + milliSec + "millisecond");
        }
    }

    /* Get element's attribute */
    protected String getElementAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    /* Get element's attribute by locator */
    protected String getElementAttribute(By locator, String attribute) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementAttribute(element, attribute);
    }

    /* Perform click action on an element */
    protected void clickElement(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    /* Perform click action on an element by its locator */
    protected void clickElement(By locator) {
        try {
            MobileElement element = appiumDriver.findElement(locator);
            System.out.println("Element: " + element);
            clickElement(element);
        }catch (Exception e){
            System.out.println("Failed to Click Element: " + locator);
        }
    }

    /* Send keys to an input-field element */
    protected void sendKeysToElement(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    /* Send keys to an input-field element by its locator */
    protected void sendKeysToElement(By locator, String input) {
        MobileElement element = appiumDriver.findElement(locator);
        System.out.println("Element: " + element);
        this.clearElementInputField(element);
        this.sendKeysToElement(element, input);
    }

    protected void pressBack(){
        appiumDriver.navigate().back();
    }

    /* Clear text in an input-field element */
    protected void clearElementInputField(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    /* Clear text in an input-field element by its locator */
    protected void clearElementInputField(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        clearElementInputField(element);
    }

    /* Directly get inner-text from an element */
    protected String getElementText(MobileElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    /* Directly get inner-text from an element by its locator */
    protected String getElementText(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementText(element);
    }

    /* Check if an element is existed by its locator */
    protected boolean isElementPresent(By locator) {
        waitForSecond(3000);
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements.size() > 0;
    }

    public MobileElement fluentWait(By locator) {
        Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(appiumDriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(driver -> appiumDriver.findElement(locator));
    }

}