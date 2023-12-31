package tests;

import caps.MobileCapabilityTypeEx;
import com.google.common.reflect.ClassPath;
import environments.PlatformType;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utils.TestUtils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main implements MobileCapabilityTypeEx {
//    public static void main(String[] args) {
//        // Press Alt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press Shift+F9 to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Ctrl+F8.
//            System.out.println("i = " + i);
//        }
//    }

    private static final String TEST_FOLDER = "test.";
    //    private static final String BASE_TEST_CLASS = "test.BaseTest";
//    private static final String MAIN_CLASS = "test.Main";
    private static final String AUTH = "test.authentication";
    private static final String FORM = "test.form";
    private static final String HOME = "test.home";
    private static final String SWIPE = "test.swipe";
    private static final String DEVICES_LIST = "devices/DevicesList.json";

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {
        /*
         * Set all classes that start with the prefix "test.", also
         * Ignore BaseTest.java and Main.java in Test folder
         */
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith(TEST_FOLDER)
                    && info.getName().startsWith(AUTH)
                    || info.getName().startsWith(FORM)
                    || info.getName().startsWith(HOME)
                    || info.getName().startsWith(SWIPE)
//                && !info.getName().equalsIgnoreCase(BASE_TEST_CLASS)
//                && !info.getName().equalsIgnoreCase(MAIN_CLASS)
            ) {
                testClasses.add(info.load());
            }
        }

        /* Get Test Platform */
//        String platformName = "android";
        String platformName = System.getProperty("platform");
        if (platformName == null) { throw new RuntimeException("Please provide platform"); }
        try {
            PlatformType.valueOf(platformName);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("[ERR] " + platformName + " is not supported right now, we only cover for " + Arrays.toString(PlatformType.values()));
        }

        /* Create a sub-list from test classes into device list,
         * Device list can be fetched from anywhere
         */
        TestUtils testUtils = new TestUtils();
        List<String> iPhoneDeviceList = testUtils.convertJsonToList(DEVICES_LIST, "ios");
        List<String> androidDeviceList = testUtils.convertJsonToList(DEVICES_LIST, "android");
        List<String> deviceList = platformName.equalsIgnoreCase(PlatformType.ios.getName()) ? iPhoneDeviceList : androidDeviceList;

        if (!deviceList.isEmpty()) {
            int testCasesPerDevice = testClasses.size() / deviceList.size();
            HashMap<String, List<Class<?>>> desiredCaps = new HashMap<>();
            for (int deviceIndex = 0 ; deviceIndex < deviceList.size() ; deviceIndex++) {
                int startIndex = deviceIndex * testCasesPerDevice;
                int endIndex = (deviceIndex == (deviceList.size() - 1)) ? testClasses.size() : (startIndex + testCasesPerDevice);
                List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
                desiredCaps.put(deviceList.get(deviceIndex), subTestList);
            }

            /* Build Test Suite Dynamically */
            TestNG testNG = new TestNG();
            XmlSuite suite = new XmlSuite();
            suite.setName("Regression");

            /*
             * Import Test Classes into test suit with
             * Each device including device's udid, platform, port, systemPort as parameters
             */
            List<XmlTest> allTests = new ArrayList<>();
            desiredCaps.keySet().forEach(deviceUdid -> {
                XmlTest test = new XmlTest(suite);
                test.setName("Test on " + deviceUdid);
                List<Class<?>> dedicatedClasses = desiredCaps.get(deviceUdid);
                List<XmlClass> xmlClasses;
                xmlClasses = dedicatedClasses.stream().map(dedicatedClass -> new XmlClass(dedicatedClass.getName())).collect(Collectors.toList());
                test.setXmlClasses(xmlClasses);
                test.addParameter(UDID, deviceUdid);
                test.addParameter(PLATFORM_NAME, platformName);
                test.addParameter(PLATFORM_VERSION, "test version");
                test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));
                test.addParameter(CHROME_DRIVER_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 10000));

                // Currently unused ~!!
                test.addParameter(PORT, "0");
                allTests.add(test);
            });

            /* Add all test into suite */
            suite.setTests(allTests);
            suite.setParallel(XmlSuite.ParallelMode.TESTS);
            suite.setThreadCount(10);

            /* Execute a group of test */
            String targetGroup = args.length != 0 ? args[0] : null;
            if (targetGroup != null) {
//            suite.addIncludedGroup("smoke");
                suite.addIncludedGroup(targetGroup);
            }

            System.out.println(suite.toXml());

            /* Add the test suite to the list of suites */
            List<XmlSuite> suites = new ArrayList<>();
            suites.add(suite);

            /* Invoke TestNG run() method */
            testNG.setXmlSuites(suites);
            testNG.run();
        } else {
            switch (PlatformType.valueOf(platformName)) {
                case ios:
                    throw new RuntimeException("iOS Device List is empty");
                case android:
                    throw new RuntimeException("Android Device List is empty");
            }
        }
    }

}