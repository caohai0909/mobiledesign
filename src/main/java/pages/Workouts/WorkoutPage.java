package pages.Workouts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class WorkoutPage extends BasePage {
    private final Locator workoutTitle = new Locator(How.XPATH, "//android.widget.TextView[@text='Workout' and @index='1']");
    private final Locator addNewFolderButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Folder']/following-sibling::android.widget.Button");
    private final Locator addNewTemplateButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Template']/following-sibling::android.widget.Button");
    private final Locator addNewEmptyWorkoutButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Empty Workout']/following-sibling::android.widget.Button");
    private final Locator addNewTemplateInFolderButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Add Template']/following-sibling::android.widget.Button");
    private final String startWorkFromTemplateLocator = "//android.widget.TextView[@text='{{Item}}']/parent::android.view.View//android.view.View[@content-desc='Start workout from template']";
    private final String workoutNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String folderNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";

    public WorkoutPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean doesWorkoutPageDisplayed(){
        return isElementPresent(workoutTitle.getBy());
    }

    public void clickOnAddNewFolder(){
        clickElement(addNewFolderButton.getBy());
    }

    public void clickOnAddNewTemplate(){
        clickElement(addNewTemplateButton.getBy());
    }

    public void clickOnAddNewEmptyInFolder(String folderName){
        clickElement(By.xpath(folderNameLabelLocator.replace("{{Item}}", folderName)));
        clickElement(addNewTemplateInFolderButton.getBy());
    }

    public void goToWorkoutDetailsByName(String workoutName){
        clickElement(By.xpath(workoutNameLabelLocator.replace("{{Item}}", workoutName)));
    }

    public boolean doesWorkoutNameDisplayedOnWorkoutPage(String workoutName){
        return isElementPresent(By.xpath(workoutNameLabelLocator.replace("{{Item}}", workoutName)));
    }

    public boolean doesFolderNameDisplayedOnWorkoutPage(String folderName){
        return isElementPresent(By.xpath(folderNameLabelLocator.replace("{{Item}}", folderName)));
    }

    public void clickOnStartWorkoutByName(String templateName){
        clickElement(By.xpath(startWorkFromTemplateLocator.replace("{{Item}}", templateName)));
    }

}
