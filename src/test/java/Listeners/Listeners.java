package Listeners;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.betika.utils.extentReporter;
import com.betika.utils.BrowserManager;

public class Listeners extends BrowserManager implements ITestListener {
    private static ThreadLocal<ExtentReports> ext = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentReports extent = extentReporter.getExtentReport();
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        ext.set(extent);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "As Expected");
    }

    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        WebDriver driver = null;

        String methodName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            // Handle exception
        }
        try {
            extentTest.get().addScreenCaptureFromPath(getScreenshot(methodName, driver), result.getMethod().getMethodName());
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        ext.get().flush();
    }


    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    
}