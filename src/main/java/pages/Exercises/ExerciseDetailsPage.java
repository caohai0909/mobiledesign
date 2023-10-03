package pages.Exercises;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class ExerciseDetailsPage extends BasePage {

    private final Locator statisticsTab = new Locator(How.XPATH, "//android.widget.TextView[@text='Statistics']");
    private final Locator historyTab = new Locator(How.XPATH, "(//android.widget.TextView[@text='History'])[1]");
    private final Locator aboutTab = new Locator(How.XPATH, "//android.widget.TextView[@text='About']");
    private final String exerciseNameLabelLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String notesLabelLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String primaryMuscleLabelLocator = "//android.widget.TextView[@text='{{Item}}']";
    private final String categoryLabelLocator = "//android.widget.TextView[@text='{{Item}}']";

    public ExerciseDetailsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void clickOnStatisticsTab(){
        clickElement(statisticsTab.getBy());
    }

    public void clickOnHistoryTab(){
        clickElement(historyTab.getBy());
    }

    public void clickOnAboutTab(){
        clickElement(aboutTab.getBy());
    }

    public boolean doesExerciseNameDisplayedOnExerciseDetailsPage(String exerciseName){
        return isElementPresent(By.xpath(exerciseNameLabelLocator.replace("{{Item}}", exerciseName)));
    }

    public boolean doesExerciseNotesDisplayedOnExerciseDetailsPage(String notes){
        return isElementPresent(By.xpath(notesLabelLocator.replace("{{Item}}", notes)));
    }

    public boolean doesPrimaryMuscleDisplayedOnExerciseDetailsPage(String primaryMuscle){

        return isElementPresent(By.xpath(primaryMuscleLabelLocator.replace("{{Item}}", primaryMuscle.toLowerCase())));
    }

    public boolean doesCategoryDisplayedOnExerciseDetailsPage(String category){
        return isElementPresent(By.xpath(categoryLabelLocator.replace("{{Item}}", category)));
    }
}
