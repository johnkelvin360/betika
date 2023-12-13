package com.betika.test_cases;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.betika.pages.Login;
import com.betika.pages.Registration;
import com.betika.pages.VerificationCode;
import com.betika.utils.BrowserManager;

import Listeners.Parameterss;

import static com.betika.pages.Login.getErrorMsg;
public class LoginTest extends BrowserManager{
    SoftAssert softAssert;
    Login login;

    @BeforeClass
    public void init() throws IOException, InterruptedException {
        login = new Login();
        softAssert = new SoftAssert();
        
    }

    @Test(priority = 1)
    public void emptyCredentials(){
        login.getLoginBtn().click();
        login.getSubmitBtn().click();
        Assert.assertEquals(getErrorMsg().getText(), "Please enter a phone number");
        login.getPhoneNumberField().clear();


    }
    @Test(priority = 2, dataProvider = "getInvalidData", dataProviderClass = Parameterss.class)
    public void invalidLogin(HashMap<String, String> data){
        login.getLoginBtn().click();
        login.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND, "b"), data.get("invalidPhone"));
        login.getPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "b"), data.get("invalidPassword"));
        login.getSubmitBtn().click();
        Assert.assertTrue(login.getInvalidPhoneNumber().isDisplayed());
        
    }

    @Test(priority = 3)
    @Parameters({ "phoneNumber", "password" })
    public void SuccessfulLoginTest(String phoneNumber, String password) throws InterruptedException {
        login = new Login();
        login.getLoginBtn().click();
        login.getPhoneNumberField().sendKeys(Keys.chord(Keys.COMMAND, "a"), phoneNumber);
        login.getPasswordField().sendKeys(Keys.chord(Keys.COMMAND, "a"), password);
        login.getSubmitBtn().click();
        Assert.assertTrue(login.getDepositBtn().isDisplayed());
    }
    
}
