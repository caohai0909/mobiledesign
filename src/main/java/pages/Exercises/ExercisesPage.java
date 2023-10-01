package pages.Exercises;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import pages.BasePage;
import utils.SwipeUtils;
import utils.TestUtils;

public class ExercisesPage extends BasePage {
    private final TestUtils testUtils = new TestUtils();
    private String timeString;
    private final SwipeUtils swipeUtils;

    private final Locator addNewExerciseButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"Create Exercise\"]");
    private String exerciseNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";;
    private String exercisePrimaryMuscleLabelLocator = "//android.widget.TextView[@text='{{Item}}']";;

    public ExercisesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        swipeUtils = new SwipeUtils(driver);
    }

    public void goToExerciseByName(String exerciseName){
        clickElement(By.xpath(exerciseNameLabelLocator.replace("{{Item}}", exerciseName)));
    }

    public void clickOnCreateExercise(){
        clickElement(addNewExerciseButton.getBy());
    }

    public boolean doesExerciseNameDisplayedOnExercisesPage(String exerciseName){
        return isElementPresent(By.xpath(exerciseNameLabelLocator.replace("{{Item}}", exerciseName)));
    }

    public boolean doesExercisePrimaryMuscleDisplayedOnExercisesPage(String primaryMuscle){
        return isElementPresent(By.xpath(exercisePrimaryMuscleLabelLocator.replace("{{Item}}", primaryMuscle)));
    }
}
