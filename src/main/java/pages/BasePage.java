package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import utils.Constant;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

//    private WebDriver appiumDriver;
//    private Dimension mobileScreenSize = appiumDriver.manage().window().getSize();
//    private TouchAction touchAction = new TouchAction((PerformsTouchActions) appiumDriver);

    protected AppiumDriver<MobileElement> appiumDriver;

    private final int MID_POINT_FACTOR = 2;
    private final double SCREEN_SIZE_PERCENTAGE = 1.0D;

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

    protected void waitForSecond(long second){
        appiumDriver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
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
        MobileElement element = appiumDriver.findElement(locator);
        System.out.println("Element: " + element);
        clickElement(element);
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
        this.sendKeysToElement(element, input);
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
        waitForSecond(2);
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements.size() > 0;
    }



}