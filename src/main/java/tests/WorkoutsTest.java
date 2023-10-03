package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Exercises.AddNewExercisesPage;
import pages.Exercises.ExercisesPage;
import pages.Workouts.AddNewWorkoutTemplatePage;
import pages.Workouts.WorkoutDetailsPage;
import pages.Workouts.WorkoutPage;
import pages.component.CommonPage;

import java.net.MalformedURLException;

public class WorkoutsTest extends BaseTest{
    AppiumDriver<MobileElement> appiumDriver;
    CommonPage commonPage;
    WorkoutPage workoutPage;
    WorkoutDetailsPage workoutDetailsPage;
    AddNewWorkoutTemplatePage addNewWorkoutTemplatePage;
    ExercisesPage exercisesPage;
    AddNewExercisesPage addNewExercisesPage;

    @Test(description = "Verify that user can create new [Exercises] successfully", dataProvider = "workoutData")
    public void TC003_verifyThatUserCanCreateNewTemplateSuccessfully(String exerciseName, String exerciseNotes, String kg, String reps, String rpe) throws MalformedURLException {
        this.appiumDriver = getDriver();
        this.commonPage = new CommonPage(this.appiumDriver);
        this.workoutPage = new WorkoutPage(this.appiumDriver);
        this.addNewWorkoutTemplatePage = new AddNewWorkoutTemplatePage(appiumDriver);

        log4J.info("Precondition: Existing one Exercise");
        String exerciseTime = this.preconditionForWorkout(exerciseName, exerciseNotes);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Workout] icon");
        commonPage.clickOnWorkoutIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        workoutPage.clickOnAddNewTemplate();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String workoutTime = addNewWorkoutTemplatePage.fillInNewWorkoutTemplate(exerciseName, exerciseNotes);

        log4J.info("Step 5: Select Exercise");
        addNewWorkoutTemplatePage.selectExercise(exerciseName+exerciseTime);

        log4J.info("Step 6: add new exercise set");
        addNewWorkoutTemplatePage.addNewExerciseSet(kg, reps, rpe);

        log4J.info("Check Point: Make sure that workout name changed on [Workout] page");
        commonPage.clickOnBackIcon();
        Assert.assertTrue(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName+workoutTime));

    }

    @Test(description = "Verify that user can edit existing [Exercises] successfully", dataProvider = "crunchData")
    public void TC004_verifyThatUserCanEditExistingTemplateSuccessfully(String exerciseName, String exerciseNotes, String kg, String reps, String rpe) throws MalformedURLException {
        this.appiumDriver = getDriver();
        this.commonPage = new CommonPage(this.appiumDriver);
        this.workoutPage = new WorkoutPage(this.appiumDriver);
        this.addNewWorkoutTemplatePage = new AddNewWorkoutTemplatePage(appiumDriver);
        this.workoutDetailsPage = new WorkoutDetailsPage(appiumDriver);

        log4J.info("Precondition: Existing one Exercise");
        String exerciseTime = this.preconditionForWorkout(exerciseName, exerciseNotes);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Workout] icon");
        commonPage.clickOnWorkoutIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        workoutPage.clickOnAddNewTemplate();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String workoutTime = addNewWorkoutTemplatePage.fillInNewWorkoutTemplate(exerciseName, exerciseNotes);

        log4J.info("Step 5: Select Exercise");
        addNewWorkoutTemplatePage.selectExercise(exerciseName+exerciseTime);

        log4J.info("Step 6: add new exercise set");
        addNewWorkoutTemplatePage.addNewExerciseSet(kg, reps, rpe);

        log4J.info("Check Point: Make sure that user can create new [Exercises] successfully");
        commonPage.clickOnBackIcon();
        Assert.assertTrue(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName + workoutTime));

        log4J.info("Step 7: Go to [Workout Details] > Click on [Edit] icon");
        workoutPage.goToWorkoutDetailsByName(exerciseName + workoutTime);
        workoutDetailsPage.clickOnEditWorkoutIcon();

        log4J.info("Step 4: Update existing Exercise Information and [Back] to Workout Page");
        workoutTime = addNewWorkoutTemplatePage.fillInNewWorkoutTemplate(exerciseName, exerciseNotes);

        log4J.info("Check Point: Make sure that workout name changed on [Workout Details] page");
        commonPage.clickOnBackIcon();
        Assert.assertTrue(workoutDetailsPage.doesWorkoutNameDisplayedOnWorkoutDetailsPage(exerciseName + workoutTime));

        log4J.info("Check Point: Make sure that workout name changed on [Workout] page");
        commonPage.clickOnBackIcon();
        Assert.assertTrue(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName + workoutTime));

    }

    @Test(description = "Verify that user can delete existing [Exercises] successfully", dataProvider = "pushUpData")
    public void TC005_verifyThatUserCanDeleteExistingTemplateSuccessfully(String exerciseName, String exerciseNotes, String kg, String reps, String rpe) throws MalformedURLException {
        this.appiumDriver = getDriver();
        this.commonPage = new CommonPage(this.appiumDriver);
        this.workoutPage = new WorkoutPage(this.appiumDriver);
        this.addNewWorkoutTemplatePage = new AddNewWorkoutTemplatePage(appiumDriver);
        this.workoutDetailsPage = new WorkoutDetailsPage(appiumDriver);

        log4J.info("Precondition: Existing one Exercise");
        String exerciseTime = this.preconditionForWorkout(exerciseName, exerciseNotes);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Workout] icon");
        commonPage.clickOnWorkoutIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        workoutPage.clickOnAddNewTemplate();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String workoutTime = addNewWorkoutTemplatePage.fillInNewWorkoutTemplate(exerciseName, exerciseNotes);

        log4J.info("Step 5: Select Exercise");
        addNewWorkoutTemplatePage.selectExercise(exerciseName+exerciseTime);

        log4J.info("Step 6: add new exercise set");
        addNewWorkoutTemplatePage.addNewExerciseSet(kg, reps, rpe);
        commonPage.clickOnBackIcon();

        log4J.info("Check Point: Make sure that user can create new [Exercises] successfully");
        Assert.assertTrue(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName + workoutTime));

        log4J.info("Step 7: Go to [Workout Details]");
        workoutPage.goToWorkoutDetailsByName(exerciseName + workoutTime);

        log4J.info("Step 8: Click on [More] icon > Click [Delete] button");
        workoutDetailsPage.deleteWorkout();

        log4J.info("Check Point: Make sure that workout name changed on [Workout] page");
        Assert.assertFalse(workoutPage.doesWorkoutNameDisplayedOnWorkoutPage(exerciseName + workoutTime));

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
    public Object[][] workoutData(){
        return new Object[][]{
                new Object[]{"AutomationName", "Notes", "89", "45", "30"}
        };
    }

    @DataProvider
    public Object[][] pushUpData(){
        return new Object[][]{
                new Object[]{"push up", "Push up notes", "85", "50", "80"}
        };
    }

    @DataProvider
    public Object[][] crunchData(){
        return new Object[][]{
                new Object[]{"crunch", "crunch notes", "70", "90", "60"}
        };
    }
}
