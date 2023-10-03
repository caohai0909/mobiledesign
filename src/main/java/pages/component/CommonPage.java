package pages.component;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class CommonPage extends BasePage {
    private final Locator homeButton = new Locator(How.XPATH, "//android.view.View[@content-desc='Home']/parent::android.view.View");
    private final Locator historyButton = new Locator(How.XPATH, "//android.view.View[@content-desc='History']/parent::android.view.View");
    private final Locator workoutButton = new Locator(How.XPATH, "//android.view.View[@content-desc='Workout']/parent::android.view.View");
    private final Locator exercisesButton = new Locator(How.XPATH, "//android.view.View[@content-desc='Exercises']/parent::android.view.View");
    private final Locator moreButton = new Locator(How.XPATH, "//android.view.View[@content-desc='More']/parent::android.view.View");
    private final Locator backButton = new Locator(How.XPATH, "//android.view.View[@content-desc='Back']");

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
        waitForSecond(2000);
        clickElement(workoutButton.getBy());
    }

    public void clickOnExercisesIcon(){
        waitForSecond(2000);
        clickElement(exercisesButton.getBy());
    }

    public void clickOnMoreIcon(){
        clickElement(moreButton.getBy());
    }

    public void clickOnBackIcon(){
        fluentWait(backButton.getBy());
        clickElement(backButton.getBy());
    }
}
