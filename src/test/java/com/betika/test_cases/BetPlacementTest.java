package com.betika.test_cases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.betika.pages.BetPlacement;
import com.betika.pages.Login;
import com.betika.utils.BrowserManager;

public class BetPlacementTest extends BrowserManager {

    SoftAssert softAssert;
    Login login;
    LoginTest loginTest;
    BetPlacement betPlacement;

    @BeforeClass
    @Parameters({ "phoneNumber", "password" })
    public void init(String phoneNumber, String password) throws IOException, InterruptedException {
        login = new Login();
        loginTest = new LoginTest();
        betPlacement = new BetPlacement();
        softAssert = new SoftAssert();
        loginTest.SuccessfulLoginTest(phoneNumber, password);

    }

    @Test(priority = 1)
    public void betPlacementTest() throws InterruptedException {
        Thread.sleep(1500);
        betPlacement.verifyBetPlacement();
    }

    @Test(priority = 2)
    public void betSlipShareTest() throws InterruptedException {
        betPlacement.verifyBetSlipShare();
    }
}
