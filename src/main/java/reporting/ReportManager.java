package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;


public class ReportManager {


    private static ExtentReports extent;

    public static ExtentReports getInstance(String reportFileName) {
        if (extent == null) {
            initReports(reportFileName);
        }
        return extent;
    }

    private static void initReports(String reportFileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFileName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("Automation test Execution Report");
        sparkReporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", ConfigReader.get("browser"));
        extent.setSystemInfo("Base URL", ConfigReader.get("baseUrl"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public static ExtentTest createTest(String testName) {
        return getInstance(null).createTest(testName);  // Default report name
    }
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
