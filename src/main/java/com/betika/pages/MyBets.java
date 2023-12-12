package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.betika.utils.BrowserManager;

public class MyBets extends BrowserManager{
    
    SoftAssert softAssert;

        public MyBets() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//span[contains(text(),'My Bets')]")
    WebElement myBetPage;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[2]/span[1]")
    WebElement openBetSlip;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[4]/button[2]")
    WebElement rebetBtn;

    @FindBy (xpath = "//span[contains(text(),'Continue')]")
    WebElement continueBtn;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/button[1]")
    WebElement placeBet;

    @FindBy (css = "#notifications-root")
    WebElement betAlert;

    @FindBy (xpath = "//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[3]/button[1]/span[1]")
    WebElement cashOut;

    @FindBy (xpath = "//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/button[1]")
    WebElement requestCashout;

    @FindBy (xpath = "//body/div[3]/div[4]")
    WebElement confirmCashOut;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[4]/button[1]")
    WebElement cancelBet;

    @FindBy (xpath = "//span[contains(text(),'Continue')]")
    WebElement confirmCancel;

    @FindBy(xpath = "//div[@id='notifications-root']")
    WebElement cancelAlert;

    
    public void verifyRebet() throws InterruptedException{
        softAssert = new SoftAssert();
        myBetPage.click();
        Thread.sleep(1200);
        openBetSlip.click();
        cancelBet.click();
        confirmCancel.click();
        softAssert.assertTrue(cancelAlert().isDisplayed());

    }

    public void verifyBetCancellation() throws InterruptedException{
        softAssert = new SoftAssert();
        myBetPage.click();
        Thread.sleep(1200);
        openBetSlip.click();
        rebetBtn.click();
        continueBtn.click();
        placeBet.click();
        softAssert.assertTrue(betAlert().isDisplayed());

    }

        public void verifyCashOut() throws InterruptedException{
        softAssert = new SoftAssert();
        myBetPage.click();
        Thread.sleep(1200);
        cashOut.click();
        requestCashout.click();
        confirmCashOut.click();

    }


    public WebElement betAlert() {
        return betAlert;
    }

    public WebElement cancelAlert() {
        return cancelAlert;
    }

}
