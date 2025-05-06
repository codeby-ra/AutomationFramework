package listeners;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends BaseTest implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);


    @Override
    public void onTestStart(ITestResult result) {
        logger.info("🔹 Test Started: " + result.getName());
        test.log(Status.INFO, "Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ Test Passed: " + result.getName());
        test.log(Status.PASS, "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ Test Failed: " + result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        WebDriver driver = getDriver();
        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ Test Skipped: " + result.getName());
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("🚀 Test Context Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("🏁 Test Context Finished: " + context.getName());
    }

    private String takeScreenshot(WebDriver driver, String testName) {
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "screenshots/";
        String screenshotPath = screenshotDir + testName + "_" + date + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            logger.info("📸 Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            logger.error("❗ Failed to save screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }
}
