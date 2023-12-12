package com.betika.test_cases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.betika.pages.Registration;
import com.betika.pages.VerificationCode;
import com.betika.utils.BrowserManager;
import static com.betika.pages.Registration.getPromptMessage;
import Listeners.Parameterss;

public class RegistrationTest extends BrowserManager {

    SoftAssert softAssert;

    String PhoneNumber = dynamicNumber();
    String regPassword = dynamicPassword();
    String confirmPassword = dynamicPassword();

    Registration register;
    VerificationCode verifycode;

    @BeforeClass
    public void init() throws IOException, InterruptedException {
        register = new Registration();
        softAssert = new SoftAssert();
        verifycode = new VerificationCode();
    }

    @Test(priority = 1, dataProvider = "getInValidData", dataProviderClass = Parameterss.class)
    public void invalidPhoneNumber(HashMap<String, String> data) {
        register.getRegBtn();
        register.registration();
        Assert.assertTrue(register.getPhoneNumberField().isDisplayed());
    }

    @Test(priority = 2, dataProvider = "getExistingPhoneNumberData", dataProviderClass = Parameterss.class)
    public void existingPhoneNumber(String phoneNumber) {
        register.getRegBtn();
        register.registration();
        Assert.assertEquals(getPromptMessage().getText(), "Mobile already in use please login or reset password. ");
        wait.until(ExpectedConditions.invisibilityOf(getPromptMessage()));
    }

    @Test(priority = 3)
    public void successfulRegistration() throws InterruptedException {
        register.getRegBtn();
        register.registration();
        Thread.sleep(2000);

        // Assertions for the Verification Code page
        wait.until(ExpectedConditions.visibilityOf(verifycode.getVerificationInputText()));
        wait.until(ExpectedConditions.visibilityOf(verifycode.getResendCode()));
        wait.until(ExpectedConditions.visibilityOf(verifycode.getVerifyLogin()));

        // Add assertions for the elements on the Verification Code page
        softAssert.assertEquals(verifycode.getVerificationInputText().getText(), "Verification Code");
        softAssert.assertEquals(verifycode.getResendCode().getText(), "Resend Code");
        softAssert.assertEquals(verifycode.getVerifyLogin().getText(), "Verify and Log In");

        // Print the phone number and password on the console
        System.out.println("Phone Number: " + BrowserManager.dynamicNumber());
        System.out.println("Password: " + BrowserManager.dynamicPassword());

        softAssert.assertAll();
    }
}
