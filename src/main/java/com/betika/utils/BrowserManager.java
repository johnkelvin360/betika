package com.betika.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.RequestId;
import org.openqa.selenium.devtools.v118.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

@SuppressWarnings("deprecation")
public class BrowserManager {

    // Declare driver variable
    public static WebDriver driver;

    public static Properties prop;

    public static int timeOut = 20;
    public static Duration durationInSeconds = Duration.ofSeconds(timeOut);

    public static WebDriverWait wait;

    public String Number = dynamicNumber();

    public String password = dynamicPassword();

    public static SoftAssert softAssert;

    public static JavascriptExecutor executor;

    public BrowserManager() {
        PageFactory.initElements(driver, this);
    }

    @BeforeClass
    @Parameters({ "url", "browser" })
    public void launchBrowser(String url, String browserType) throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src/main//" +
                "java//com//betika//utils//data.properties");
        prop.load(fis);
        try {
            if (browserType.toLowerCase().contains("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("use-fake-ui-for-media-stream");
                options.addArguments("enable-automation");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                if (browserType.toLowerCase().contains("headless")) {
                    options.addArguments("headless");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            } else if (browserType.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                org.testng.Assert.fail("Invalid Browser Type: " + browserType);
            }

            driver.manage().window().setSize(new Dimension(1920, 1080));

            // Manage driver properties
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);

            Thread.sleep(3000);

            System.out.println(browserType);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, durationInSeconds);
        softAssert = new SoftAssert();
        executor = (JavascriptExecutor) driver;
    }

    public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String DestinationName = System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png";
        FileUtils.copyFile(source, new File(DestinationName));
        return DestinationName;
    }

    public static String random(String[] s) {
        Random r = new Random();
        return s[r.nextInt(s.length)];
    }

    public static String dynamicNumber() {
        long min = 1000000L; 
        long max = 9999999L; 
    
        long randomValue = min + (long) (Math.random() * (max - min + 1));
        String dynamicNumber = "071" + String.format("%07d", randomValue);
        return dynamicNumber;
        
    }
    
    public static String dynamicPassword() {
        int min = 0;
        int max = 1000000000;

        int a = (int) (Math.random() * (max - min + 1) + min);

        String dynamicPassword = "Pass" + a + "@test";

        return dynamicPassword;
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @AfterClass
    public void terminate() {
        driver.quit();
    }
}
