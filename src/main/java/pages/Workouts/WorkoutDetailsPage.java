package pages.Workouts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class WorkoutDetailsPage extends BasePage {

    private final Locator editButton = new Locator(How.XPATH, "//android.view.View[@content-desc='Edit']");
    private final Locator startWorkoutButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Start Workout']/following-sibling::android.widget.Button");
    private final Locator moreIcon = new Locator(How.XPATH, "//android.view.View[@index='3']/android.view.View[@content-desc='More']");
    private final Locator deleteButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Delete']");
    private final String workoutNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";
    public WorkoutDetailsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void clickOnEditWorkoutIcon(){
        clickElement(editButton.getBy());
    }

    public void clickOnStartWorkoutButton(){
        clickElement(startWorkoutButton.getBy());
    }

    public boolean doesWorkoutNameDisplayedOnWorkoutDetailsPage(String workoutName){
        return isElementPresent(By.xpath(workoutNameLabelLocator.replace("{{Item}}", workoutName)));
    }

    public void deleteWorkout(){
        clickElement(moreIcon.getBy());
        clickElement(deleteButton.getBy());
    }
}
