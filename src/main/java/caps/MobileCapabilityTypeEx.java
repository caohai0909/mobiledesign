package caps;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {

    String PLATFORM_NAME = "platformName";
    String AUTOMATION_NAME = "automationName";
    String UDID = "udid";
    String DEVICE_NAME = "deviceName";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
    String SYSTEM_PORT = "systemPort";
    String PORT = "port";
    String CHROME_DRIVER_PORT = "chromedriverPort";

}
