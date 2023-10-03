package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Constant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private AppiumDriver<MobileElement> appiumDriver;
    private AppiumDriverLocalService appiumServer;
    private String udid;

    public String getUdid() {
        return udid;
    }
    
    public AppiumDriver<MobileElement> getAppiumDriver(String deviceName, String udid, String systemPort) throws MalformedURLException {
        if (appiumDriver == null) {
            appiumDriver = initAppiumDriver(deviceName, udid, systemPort);
            this.udid = udid;
        }
        return appiumDriver;
    }

    private AppiumDriver<MobileElement> initAppiumDriver(String deviceName, String udid, String systemPort) throws MalformedURLException {
        AppiumServiceBuilder appiumServerBuilder = new AppiumServiceBuilder();
        appiumServerBuilder.withIPAddress("192.168.255.131").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServerBuilder);
        appiumServer.start();

        DesiredCapabilities capabilities = getDesiredCapabilities(deviceName, udid, systemPort);
        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        return appiumDriver;
    }

    private static DesiredCapabilities getDesiredCapabilities(String deviceName, String udid, String systemPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.ankitsuda.rebound.debug");
        capabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.ankitsuda.rebound.ui.MainActivity");
        capabilities.setCapability(MobileCapabilityTypeEx.UDID, udid);
        capabilities.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
        capabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
        return capabilities;
    }

    public void quitAppiumSession() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
