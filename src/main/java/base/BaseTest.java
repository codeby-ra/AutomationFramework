package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.ConfigReader;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected static Logger logger;


    @BeforeSuite
    public void setupSuite() {
        ConfigReader.loadProperties();
//        extent = ReportManager.createInstance();
        logger = LogManager.getLogger(BaseTest.class);
        logger.info("===== Test Suite Started =====");
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser, java.lang.reflect.Method method) {
        WebDriver webDriver = DriverFactory.initDriver(browser);
        driver.set(webDriver);

        test = extent.createTest(method.getName());
        logger.info("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(org.testng.ITestResult result) {
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            logger.error("Test Failed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test Passed: " + result.getName());
        }

        driver.get().quit();
        logger.info("Browser closed.");
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
        logger.info("===== Test Suite Finished =====");
    }


    public WebDriver getDriver() {
        return driver.get();
    }
}
