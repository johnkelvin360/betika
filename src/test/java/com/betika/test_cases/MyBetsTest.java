package com.betika.test_cases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.betika.pages.BetPlacement;
import com.betika.pages.Login;
import com.betika.pages.MyBets;
import com.betika.utils.BrowserManager;

public class MyBetsTest extends BrowserManager {
    SoftAssert softAssert;
    Login login;
    LoginTest loginTest;
    MyBets myBets;

    @BeforeClass
    @Parameters({ "phoneNumber", "password" })
    public void init(String phoneNumber, String password) throws IOException, InterruptedException {
        login = new Login();
        loginTest = new LoginTest();
        myBets = new MyBets();
        softAssert = new SoftAssert();
        loginTest.SuccessfulLoginTest(phoneNumber, password);

    }

    @Test(priority = 1)
    public void rebetTest() throws InterruptedException {
        Thread.sleep(1500);
        myBets.verifyRebet();
    }

    @Test(priority = 2)
    public void betCashOutTest() throws InterruptedException {
        Thread.sleep(1500);
        myBets.verifyCashOut();
    }

    @Test(priority = 3)
    public void betCancelTest() throws InterruptedException {
        Thread.sleep(1500);
        myBets.verifyBetCancellation();
    }

}
