package reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTestThreadLocal.set(test);
    }

    public static ExtentTest getTest() {
        ExtentTest test = extentTestThreadLocal.get();
        if (test == null) {
            throw new RuntimeException("❗ ExtentTest not initialized for this thread.");
        }
        return test;
    }

    public static void removeTest() {
        extentTestThreadLocal.remove();
    }
}
