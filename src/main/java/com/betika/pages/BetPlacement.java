package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.util.Assert; // Make sure to import the correct Assert class
import com.betika.utils.BrowserManager;

public class BetPlacement extends BrowserManager {

   SoftAssert softAssert;
    
    public BetPlacement() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[7]/div[2]/div[2]/div[1]/button[3]")
    WebElement firstOdd;

    @FindBy(xpath = "//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[8]/div[2]/div[2]/div[1]/button[1]")
    WebElement secondOdd;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[10]/div[2]/div[2]/div[1]/button[2]")
    WebElement thirdOdd;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/div[2]/button[1]")
    WebElement placeBet;

    @FindBy(xpath = "//div[contains(text(),'Bet Placement Successful')]")
    WebElement successfulBet;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/button[1]")
    WebElement shareBetSlip;

    @FindBy(xpath = "//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/a[3]/*[1]")
    WebElement shareOnWhatApp;

    @FindBy (xpath = "//button[contains(text(),'Copy Link')]")
    WebElement shareLink;

     public WebElement getSuccessfulBet() {
        return successfulBet;
    }

     public WebElement getShareLink() {
        return shareLink;
    }
    public void verifyBetPlacement() throws InterruptedException {
        softAssert = new SoftAssert();
        firstOdd.click();
        secondOdd.click();
        thirdOdd.click();
        Thread.sleep(1200);
        placeBet.click();
        softAssert.assertEquals(getSuccessfulBet().getText(), "Bet Placement Successful");
    }

       public void verifyBetSlipShare() throws InterruptedException {
        Thread.sleep(1200);
        firstOdd.click();
        secondOdd.click();
        shareBetSlip.click();
        thirdOdd.click();
        Thread.sleep(1200);
        shareOnWhatApp.click();
        softAssert.assertEquals(getShareLink().getText(), "Copy Link");
    }
}
