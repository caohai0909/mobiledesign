package pages.Exercises;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;
import utils.SwipeUtils;
import utils.TestUtils;

public class AddNewExercisesPage extends BasePage {

    private String timeString;
    private final SwipeUtils swipeUtils;
    private final Locator newExercisePopup = new Locator(How.XPATH, "//android.widget.TextView[@text='New Exercise']");
    private final Locator nameField = new Locator(How.XPATH, "//android.widget.TextView[@text='Exercise name']/ancestor::android.widget.EditText");
    private final Locator notesField = new Locator(How.XPATH, "//android.widget.TextView[@text='Exercise notes']/ancestor::android.widget.EditText");
    private final Locator categoryButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Category']");
    private final Locator primaryMuscleButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Primary Muscle']");
    private final Locator saveExerciseButton =new Locator(How.XPATH, "//android.view.View[@content-desc='Create']");
    private final String categoryItemLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String primaryMuscleItemLocator = "//android.widget.TextView[@text='{{Item}}']";

    public AddNewExercisesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        swipeUtils = new SwipeUtils(driver);
    }

    public String fillInNewExercise(String exerciseName, String exerciseNotes, String category, String primaryMuscle){
        this.timeString =  TestUtils.generateRandomTimeString();
        this.enterExerciseName(exerciseName);
        this.enterExerciseNotes(exerciseNotes);
        this.selectCategory(category);
        this.selectPrimaryMuscle(primaryMuscle);
        this.clickOnSave();
        return this.timeString;
    }

    public void enterExerciseName(String exerciseName){
        if (!exerciseName.isEmpty()){
            sendKeysToElement(nameField.getBy(), exerciseName + this.timeString);
        }
    }

    public void enterExerciseNotes(String notes){
        if (!notes.isEmpty()){
            sendKeysToElement(notesField.getBy(), notes + timeString);
        }
    }

    public void selectCategory(String category){
        if (!category.isEmpty()) {
            swipeUtils.swipeUp(0.85);
            clickElement(categoryButton.getBy());
            clickElement(By.xpath(categoryItemLocator.replace("{{Item}}", category)));
        }
    }

    public void selectPrimaryMuscle(String primaryMuscle){
        if (!primaryMuscle.isEmpty()) {
            waitForSecond(2000);
            waitForVisibility(newExercisePopup.getBy());
            swipeUtils.swipeUp(0.85);
            clickElement(primaryMuscleButton.getBy());
            clickElement(By.xpath(primaryMuscleItemLocator.replace("{{Item}}", primaryMuscle)));
        }
    }

    public void clickOnSave(){
        clickElement(saveExerciseButton.getBy());
    }

}
