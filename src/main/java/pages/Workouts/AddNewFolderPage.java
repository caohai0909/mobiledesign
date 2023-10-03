package pages.Workouts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.Locator;
import org.openqa.selenium.support.How;
import pages.BasePage;

public class AddNewFolderPage extends BasePage {
    private final Locator folderPopup = new Locator(How.XPATH, "//android.widget.TextView[@text='Folder']");
    private final Locator addNewFolderField = new Locator(How.XPATH, "//android.widget.TextView[@text='Folder']/parent::android.view.View/following-sibling::android.widget.EditText");
    private final Locator addButton = new Locator(How.XPATH, "//android.widget.TextView[@text='Add']/following-sibling::android.widget.Button");

    public AddNewFolderPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean doesFolderPopupDisplayed(){
        return isElementPresent(folderPopup.getBy());
    }

    public void createFolder(String folderName){
        clickElement(addNewFolderField.getBy());
        sendKeysToElement(addNewFolderField.getBy(), folderName);
        pressBack();
        clickElement(addButton.getBy());
    }
}
