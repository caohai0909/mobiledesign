package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Exercises.AddNewExercisesPage;
import pages.Exercises.ExerciseDetailsPage;
import pages.Exercises.ExercisesPage;
import pages.component.CommonPage;

import java.net.MalformedURLException;

public class ExercisesTest extends BaseTest{

    @Test(description = "Verify that user can create new [Exercises] successfully for mandatory fields", dataProvider = "exerciseMandatoryData")
    public void TC001_verifyThatUserCanCreateNewExerciseSuccessfullyForMandatoryFields(String exerciseName, String exerciseNotes, String category, String primaryMuscle) throws MalformedURLException {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        CommonPage commonPage = new CommonPage(appiumDriver);
        ExercisesPage exercisesPage = new ExercisesPage(appiumDriver);
        AddNewExercisesPage addNewExercisesPage = new AddNewExercisesPage(appiumDriver);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Exercise] icon");
        commonPage.clickOnExercisesIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        exercisesPage.clickOnCreateExercise();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String exerciseTime = addNewExercisesPage.fillInNewExercise(exerciseName, exerciseNotes, category, primaryMuscle);

        log4J.info("Check Point: Make sure that user can create new [Exercises] successfully for mandatory fields");
        Assert.assertTrue(exercisesPage.doesExerciseNameDisplayedOnExercisesPage(exerciseName+exerciseTime));
        Assert.assertTrue(exercisesPage.doesExercisePrimaryMuscleDisplayedOnExercisesPage("null"));

    }

    @Test(description = "Verify that user can create new [Exercises] successfully for full fields", dataProvider = "exerciseFullData")
    public void TC002_verifyThatUserCanCreateNewExerciseSuccessfullyForFullFields(String exerciseName, String exerciseNotes, String category, String primaryMuscle) throws MalformedURLException {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        CommonPage commonPage = new CommonPage(appiumDriver);
        ExercisesPage exercisesPage = new ExercisesPage(appiumDriver);
        AddNewExercisesPage addNewExercisesPage = new AddNewExercisesPage(appiumDriver);
        ExerciseDetailsPage exerciseDetailsPage = new ExerciseDetailsPage(appiumDriver);

        log4J.info("Step 1: Launch app");
        log4J.info("Step 2: Click on [Exercise] icon");
        commonPage.clickOnExercisesIcon();

        log4J.info("Step 3: Click on [Add New Exercise] icon");
        exercisesPage.clickOnCreateExercise();

        log4J.info("Step 4: Fill-in new Exercise Information");
        String exerciseTime = addNewExercisesPage.fillInNewExercise(exerciseName, exerciseNotes, category, primaryMuscle);

        log4J.info("Check Point: Make sure that user can create new [Exercises] successfully for mandatory fields");
        Assert.assertTrue(exercisesPage.doesExerciseNameDisplayedOnExercisesPage(exerciseName+exerciseTime));
        Assert.assertTrue(exercisesPage.doesExercisePrimaryMuscleDisplayedOnExercisesPage(primaryMuscle));

        log4J.info("Step 5: Go to Exercise Details Page");
        exercisesPage.goToExerciseByName(exerciseName + exerciseTime);

        log4J.info("Step 6: On [Exercise Details Page], click on [About] tab");
        exerciseDetailsPage.clickOnAboutTab();

        log4J.info("Check Point: Make sure that user can create new [Exercises] successfully for mandatory fields");
        Assert.assertTrue(exerciseDetailsPage.doesExerciseNameDisplayedOnExerciseDetailsPage(exerciseName+exerciseTime));
        Assert.assertTrue(exerciseDetailsPage.doesExerciseNotesDisplayedOnExerciseDetailsPage(exerciseNotes+exerciseTime));
        Assert.assertTrue(exerciseDetailsPage.doesCategoryDisplayedOnExerciseDetailsPage(category));
        Assert.assertTrue(exerciseDetailsPage.doesPrimaryMuscleDisplayedOnExerciseDetailsPage(primaryMuscle));

    }

    @DataProvider
    public Object[][] exerciseMandatoryData(){
        return new Object[][]{
                new Object[]{"AutomationName", "", "", ""}
        };
    }

    @DataProvider
    public Object[][] exerciseFullData(){
        return new Object[][]{
                new Object[]{"AutomationName", "Automation notes", "Bodyweight Reps", "Back"}
        };
    }
}
