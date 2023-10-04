package pages.Workouts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;
import utils.SwipeUtils;
import utils.TestUtils;

public class AddNewWorkoutTemplatePage extends BasePage {
    private String timeString;
    private final SwipeUtils swipeUtils;
    private final Locator addNewWorkoutTemplateTitle = new Locator(How.XPATH, "//android.widget.TextView[@text='Edit Workout']");
    private final Locator workoutNameField = new Locator(How.XPATH, "//android.widget.EditText[1]");
    private final Locator workoutNoteField = new Locator(How.XPATH, "//android.widget.EditText[2]");
    private final Locator addExerciseButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Add Exercise']");
    private final Locator addSetButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Add set']");
    private final Locator kgField = new Locator(How.XPATH, "//android.view.ViewGroup[1]");
    private final Locator repsField = new Locator(How.XPATH, "//android.view.ViewGroup[2]");
    private final Locator rpeField = new Locator(How.XPATH, "//android.view.ViewGroup[3]");
    private final Locator rpeSeekBar = new Locator(How.XPATH, "//android.widget.SeekBar");
    private final Locator finishButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Finish']");
    private final String numberLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String exerciseNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";

    public AddNewWorkoutTemplatePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        swipeUtils = new SwipeUtils(driver);
    }

    public boolean doesAddNewWorkoutTemplatePageDisplayed(){
        return isElementPresent(addNewWorkoutTemplateTitle.getBy());
    }

    public String fillInNewWorkoutTemplate(String workoutName, String workoutNote){
        this.timeString =  TestUtils.generateRandomTimeString();
        this.enterWorkoutName(workoutName);
        this.enterWorkoutNote(workoutNote);
        return this.timeString;
    }

    public void enterWorkoutName(String workoutName){
        if (!workoutName.isEmpty()){
            sendKeysToElement(workoutNameField.getBy(), workoutName + this.timeString);
        }
    }

    public void enterWorkoutNote(String workoutNote){
        if (!workoutNote.isEmpty()){
            sendKeysToElement(workoutNoteField.getBy(), workoutNote + this.timeString);
        }
    }

    public void clickOnAddExerciseButton(){
        clickElement(addExerciseButton.getBy());
    }

    public void selectExercise(String exerciseName){
        if (!exerciseName.isEmpty()){
            this.clickOnAddExerciseButton();
            clickElement(By.xpath(exerciseNameLabelLocator.replace("{{Item}}", exerciseName)));
        }
    }

    public void addNewExerciseSet(String kg, String reps, String rpe){
        this.clickOnAddSetButton();
        this.enterKG(kg);
        this.enterREPS(reps);
        this.enterRPE(rpe);
    }

    public void clickOnAddSetButton(){
        clickElement(addSetButton.getBy());
    }

    public void enterKG(String kg){
        if (!kg.isEmpty()){
            clickElement(kgField.getBy());
            this.enterNumber(kg);
        }
    }

    public void enterREPS(String reps){
        if (!reps.isEmpty()){
            clickElement(repsField.getBy());
            this.enterNumber(reps);
        }
    }

    public void enterRPE(String rpe){
        if (!rpe.isEmpty()){
            clickElement(rpeField.getBy());
            double percentage = Double.parseDouble(rpe)/100;
            System.out.println("Percentage: " + percentage);
            this.swipeRPE(percentage);
        }
    }

    public void enterNumber(String number){
         for (int i= 0; i<number.length(); i++){
             char currentChar = number.charAt(i);
             clickElement(By.xpath(numberLocator.replace("{{Item}}", String.valueOf(currentChar))));
         }
    }

    public void swipeRPE(double percentage){
        waitForSecond(3000);
        swipeUtils.swipeElementWithPercentage(rpeSeekBar.getBy(), percentage);
    }

    public void clickOnFinish(){
        clickElement(finishButton.getBy());
    }

    public void waitForStartWorkout(long milliSec){
        waitForSecond(milliSec);
    }
}
