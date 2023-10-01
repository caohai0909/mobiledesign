import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class example {

    @Test
    public void test1() throws InterruptedException{
        try {
            System.out.println("Execution started..");
            DesiredCapabilities capabilities = getDesiredCapabilities();

            WebDriver driver=new AndroidDriver<>(new URL("http://192.168.255.131:4723/wd/hub"), capabilities);

            Thread.sleep(5000);
            driver.findElement(By.xpath("//android.view.View[@content-desc=\"Exercises\"]/parent::android.view.View")).click();





            Thread.sleep(10000);
            driver.quit();
            System.out.println("Execution end..");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.ankitsuda.rebound.debug");
        capabilities.setCapability("appActivity", "com.ankitsuda.rebound.ui.MainActivity");
        capabilities.setCapability("udid", "987da754");
        capabilities.setCapability("deviceName", "OPPO Reno5");
        capabilities.setCapability("systemPort", "8201");
        return capabilities;
    }
}
