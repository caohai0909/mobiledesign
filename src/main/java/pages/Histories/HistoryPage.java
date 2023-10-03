package pages.Histories;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class HistoryPage extends BasePage {
    private final Locator historyTitle = new Locator(How.XPATH, "//android.widget.TextView[@text='History' and @index='2']");
    private final String workoutLabelLocator = "//android.widget.TextView[@text='{{Item}}']";

    public HistoryPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean doesHistoryPageDisplayed(){
        return isElementPresent(historyTitle.getBy());
    }

    public boolean doesWorkoutNameDisplayed(String workoutName){
        return isElementPresent(By.xpath(workoutLabelLocator.replace("{{Item}}", workoutName)));
    }
}
