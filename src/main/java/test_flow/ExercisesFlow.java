package test_flow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import pages.Exercises.ExercisesPage;

public class ExercisesFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;
    private ExercisesPage exercisesPage;

    public ExercisesFlow(AppiumDriver<MobileElement> appiumDriver){
        this.appiumDriver = appiumDriver;
    }

//    public ExercisesFlow initExercisesPage(){
//        exercisesPage = new ExercisesPage(appiumDriver);
//        return this;
//    }
//    public ExercisesFlow navigateToExercisePage(){
//        if (exercisesPage == null){
////            initExercisesPage();
//        }
//        BottomNavBarComponent bottomNavBarComponent = exercisesPage.bottomNavBarComponent();
//        bottomNavBarComponent.clickOnExercisesIcon();
//        return this;
//    }
//
//    public ExercisesFlow createNewExercise(String name, String notes, String category, String primaryMuscle){
//        exercisesPage.createNewExercise(name,name,category,primaryMuscle);
//        return this;
//    }
}
