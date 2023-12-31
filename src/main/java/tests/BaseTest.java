package tests;

import driver.DriverFactory;
import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BaseTest {
    private final static List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String deviceName;
    private String udid;
    private String systemPort;
    public static Logger log4J = Logger.getLogger(BaseTest.class);

    @BeforeTest(alwaysRun = true)
    @Parameters({"deviceName", "udid", "systemPort"})
    public void beforeTest(String deviceName, String udid, String systemPort) throws MalformedURLException, InterruptedException {
        System.out.println("Execution started..");
        this.deviceName = deviceName;
        this.udid = udid;
        this.systemPort = systemPort;

        System.out.println("Device Name: " + deviceName);
        System.out.println("UDId: " + udid);
        System.out.println("System Port: " + systemPort);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws MalformedURLException {
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
        getDriver().context(Context.NATIVE.getContext());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() throws MalformedURLException {
        driverThread.get().quitAppiumSession();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws MalformedURLException {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            // 1. Get the test Method name
//            String testMethodName = result.getName();
//
//            // 2. Declare the file location
//            TestUtils testUtils = new TestUtils();
//            String dateTaken = testUtils.getDateTime();
//            String fileLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + testMethodName + "_" + dateTaken + ".png";
//
//            // 3. Declare the file name
//
//            // 4. Save the screenshot to the system
////            File screenShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//            File screenShot = getDriver().getScreenshotAs(OutputType.FILE);
//
//            try {
//                FileUtils.copyFile(screenShot, new File(fileLocation.trim()));
//                Path content = Paths.get(fileLocation);
//                try (InputStream is = Files.newInputStream(content)) {
//                    Allure.addAttachment(testMethodName, is);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
    }

    public AppiumDriver<MobileElement> getDriver() throws MalformedURLException {
        return driverThread.get().getAppiumDriver(this.deviceName, this.udid, this.systemPort);
    }
}
