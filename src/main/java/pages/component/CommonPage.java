package pages.component;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class CommonPage extends BasePage {
    private Locator homeButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"Home\"]/parent::android.view.View");
    private Locator historyButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"History\"]/parent::android.view.View");
    private Locator workoutButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"Workout\"]/parent::android.view.View");
    private Locator exercisesButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"Exercises\"]/parent::android.view.View");
    private Locator moreButton = new Locator(How.XPATH, "//android.view.View[@content-desc=\"More\"]/parent::android.view.View");

    public CommonPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void clickOnHomeIcon(){
        clickElement(homeButton.getBy());
    }

    public void clickOnHistoryIcon(){
        clickElement(historyButton.getBy());
    }

    public void clickOnWorkoutIcon(){
        clickElement(workoutButton.getBy());
    }

    public void clickOnExercisesIcon(){
        clickElement(exercisesButton.getBy());
    }

    public void clickOnMoreIcon(){
        clickElement(moreButton.getBy());
    }
}
