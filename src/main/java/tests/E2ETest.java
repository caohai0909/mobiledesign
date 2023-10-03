package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Exercises.AddNewExercisesPage;
import pages.Exercises.ExercisesPage;
import pages.Histories.HistoryPage;
import pages.Workouts.AddNewFolderPage;
import pages.Workouts.AddNewWorkoutTemplatePage;
import pages.Workouts.WorkoutDetailsPage;
import pages.Workouts.WorkoutPage;
import pages.component.CommonPage;

import java.net.MalformedURLException;
@Test
public class E2ETest extends BaseTest{
    AppiumDriver<MobileElement> appiumDriver;
    CommonPage commonPage;
    WorkoutPage workoutPage;
    WorkoutDetailsPage workoutDetailsPage;
    AddNewWorkoutTemplatePage addNewWorkoutTemplatePage;
    ExercisesPage exercisesPage;
    AddNewExercisesPage addNewExercisesPage;
    AddNewFolderPage addNewFolderPage;
    HistoryPage historyPage;

    @Test(description = "Verify that user can create new [Exercises] successfully for mandatory fields", dataProvider = "e2EData")
    public void TC006_VerifyTheApplicationCanProcessWorkoutFromCreateToFinishWithoutAnyError(String folderName, String exerciseName, String exerciseNotes, String kg, String reps, String rpe) throws MalformedURLException, InterruptedException {
        this.appiumDriver = getDriver();
        this.commonPage = new CommonPage(this.appiumDriver);
        this.workoutPage = new WorkoutPage(this.appiumDriver);
        this.addNewWorkoutTemplatePage = new AddNewWorkoutTemplatePage(appiumDriver);
        this.addNewFolderPage = new AddNewFolderPage(appiumDriver);
        this.historyPage = new HistoryPage(appiumDriver);

        log4J.info("Precondition: Existing one Exercise");
        String exerciseTime = this.preconditionForWorkout(exerciseName, exerciseNotes);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Workout] icon");
        commonPage.clickOnWorkoutIcon();
        log4J.info("Check point: Make sure [Workout] page will be displayed");
        Assert.assertTrue(workoutPage.doesWorkoutPageDisplayed());

        log4J.info("Step 3: Click on [Add New Folder]");
        workoutPage.clickOnAddNewFolder();
        log4J.info("Check point: Make sure [Add new Folder] popup will be displayed");
        Assert.assertTrue(addNewFolderPage.doesFolderPopupDisplayed());

        log4J.info("Step 4: Create new [Folder]");
        addNewFolderPage.createFolder(folderName);
        log4J.info("Check point: Make sure [Add new Folder] have been created");
        Assert.assertTrue(workoutPage.doesFolderNameDisplayedOnWorkoutPage(folderName));

        log4J.info("Step 3: Click on [Add New Empty Template]");
        workoutPage.clickOnAddNewEmptyInFolder(folderName);
        log4J.info("Check point: Make sure [Add New Workout] page will be displayed");
        Assert.assertTrue(addNewWorkoutTemplatePage.doesAddNewWorkoutTemplatePageDisplayed());

        log4J.info("Step 4: Fill-in new Exercise Information");
        String workoutTime = addNewWorkoutTemplatePage.fillInNewWorkoutTemplate(exerciseName, exerciseNotes);

        log4J.info("Step 5: Select Exercise");
        addNewWorkoutTemplatePage.selectExercise(exerciseName + exerciseTime);

        log4J.info("Step 6: add new exercise set");
        addNewWorkoutTemplatePage.addNewExerciseSet(kg, reps, rpe);
        commonPage.clickOnBackIcon();

        log4J.info("Check Point: Make sure that user can create new [Workout Template] successfully");
        Assert.assertTrue(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName + workoutTime));

        log4J.info("Step 7: Go to [Workout Details] > Click on [Start Workout]");
        workoutPage.clickOnStartWorkoutByName(exerciseName + workoutTime);

        log4J.info("Step 8: Wait 5s > Click on [Finish] button");
        addNewWorkoutTemplatePage.waitForStartWorkout(5000);
        addNewWorkoutTemplatePage.clickOnFinish();

        log4J.info("Step 9: Go to [History] Page");
        commonPage.clickOnHistoryIcon();

        log4J.info("Check point: Make sure [History] Page will be displayed");
        Assert.assertTrue(historyPage.doesHistoryPageDisplayed());
        log4J.info("Check point: Make sure [Workout] name will be displayed");
        Assert.assertTrue(historyPage.doesWorkoutNameDisplayed(exerciseName + workoutTime));

    }

    private String preconditionForWorkout(String exerciseName, String exerciseNotes){
        this.exercisesPage = new ExercisesPage(this.appiumDriver);
        this.addNewExercisesPage = new AddNewExercisesPage(this.appiumDriver);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Exercise] icon");
        commonPage.clickOnExercisesIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        exercisesPage.clickOnCreateExercise();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String exerciseTime = addNewExercisesPage.fillInNewExercise(exerciseName, exerciseNotes, "", "");

        return exerciseTime;
    }

    @DataProvider
    public Object[][] e2EData(){
        return new Object[][]{
                new Object[]{"New Folder", "push up", "Push up notes", "85", "50", "70"}
        };
    }
}
