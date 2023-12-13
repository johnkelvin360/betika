package com.betika.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporter {
    private static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();

    public static ExtentReports getExtentReport() {
        if (extent.get() == null) {
            String path = System.getProperty("user.dir") + "//reports//index.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setDocumentTitle("Document title");
            spark.config().setReportName("Report name");

            ExtentReports extentInstance = new ExtentReports();
            extentInstance.attachReporter(spark);
            extentInstance.setSystemInfo("Tester", "John Kelvin");

            extent.set(extentInstance);
        }
        return extent.get();
    }
}
