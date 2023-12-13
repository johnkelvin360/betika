package com.betika.test_cases;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.betika.pages.Login;
import com.betika.utils.BrowserManager;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginDataProvider extends BrowserManager {

    Login login;
    SoftAssert softAssert;
    DataFormatter formatter = new DataFormatter();

    @BeforeClass
    public void init() throws IOException, InterruptedException {
        login = new Login();
        softAssert = new SoftAssert();
    }

   @Test(priority = 1, dataProvider = "getValidData")
    public void validLogin(String phone, String password) throws IOException, InterruptedException {
        login.getLoginBtn().click();
        login.getPhoneNumberField().sendKeys(phone);
        login.getPasswordField().sendKeys(password);
        login.getSubmitBtn().click();
        Thread.sleep(3000);
        
       
        wait.until(ExpectedConditions.visibilityOf(login.getDepositBtn()));

        Assert.assertTrue(login.getDepositBtn().isDisplayed());

        if (isValidLogin(phone, password)) {
            // Click on the widget
            login.clickWidget();

            wait.until(ExpectedConditions.visibilityOf(login.getWidget()));
            WebElement sideWindow = login.getWidget();
            scrollTowardsEnd(sideWindow, 0.10);

            wait.until(ExpectedConditions.elementToBeClickable(login.getLogoutBtn()));

            // Updated: Click using JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", login.getLogoutBtn());

            wait.until(ExpectedConditions.visibilityOf(login.getLoginBtn()));

            Assert.assertTrue(login.getLoginBtn().isDisplayed());
        } else {
            Assert.fail("Invalid login, but a valid login was received");
        }
    }

    @Test(priority = 2, dataProvider = "getInvalidData")
    public void invalidLogin(String phone, String password) throws IOException, InterruptedException {
        login.getPhoneNumberField().clear();
        login.getPasswordField().clear();

        // Enter new data
        login.getLoginBtn().click();
        login.getPhoneNumberField().sendKeys(phone);
        login.getPasswordField().sendKeys(password);
        login.getSubmitBtn().click();
        Thread.sleep(3000);
        
        wait.until(ExpectedConditions.visibilityOf(login.getInvalidPhoneNumber()));

        Assert.assertTrue(login.getInvalidPhoneNumber().isDisplayed());
    }

    @DataProvider(name = "getValidData")
    public Object[][] getValidData() throws IOException {
        return getData(1, 2); 
    }

    @DataProvider(name = "getInvalidData")
    public Object[][] getInvalidData() throws IOException {
        return getData(2, 4); 
    }

    private Object[][] getData(int startRow, int numColumns) throws IOException {
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "//src//test//java//excel//loginDataProvider.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);

        Object[][] data = new Object[rowCount - startRow][numColumns];

        for (int i = 0; i < rowCount - startRow; i++) {
            row = sheet.getRow(startRow + i);
            for (int j = 0; j < numColumns; j++) {
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;
    }

    private boolean isValidLogin(String phone, String password) {
        return !(phone.equals("invalid_phone") && password.equals("invalid_password"));
    }

    private void scrollTowardsEnd(WebElement element, double scrollPercentage) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long windowHeight = (long) js.executeScript("return arguments[0].clientHeight", element);
        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", element);
        long scrollAmount = (long) (scrollHeight * scrollPercentage);

        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", element);
    }

}
