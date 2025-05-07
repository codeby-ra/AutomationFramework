package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extent;
    private static final String REPORT_DIR = "test-output/reports";
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    private static final String REPORT_NAME = "Automation_Report_" + TIMESTAMP + ".html";

    public static ExtentReports getInstance() {
        if (extent == null) createInstance();
        return extent;
    }

    private static void createInstance() {
        File reportDir = new File(REPORT_DIR);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        String reportPath = REPORT_DIR + "/" + REPORT_NAME;
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

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

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static String getReportPath() {
        return REPORT_DIR + "/" + REPORT_NAME;
    }

}
