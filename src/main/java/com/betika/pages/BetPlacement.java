package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.betika.utils.BrowserManager;

public class BetPlacement extends BrowserManager {

    SoftAssert softAssert;

    public BetPlacement() {
        PageFactory.initElements(driver, this);
        softAssert = new SoftAssert(); 
    }

    @FindBy(css = "#notifications-root")
    WebElement successfulBet;

    @FindBy(css = "div.app:nth-child(4) div.desktop-layout:nth-child(1) div.desktop-layout__content:nth-child(2) div.desktop-layout__content__center div.contain-overflow div.matches__container div.matches div.prebet-match:nth-child(7) div.prebet-match__odd-market__container div.prebet-match__odds__container div.prebet-match__odds button.prebet-match__odd:nth-child(1) > span.prebet-match__odd__odd-value.bold")
    WebElement firstOdd;

    @FindBy(css = "div.app:nth-child(4) div.desktop-layout:nth-child(1) div.desktop-layout__content:nth-child(2) div.desktop-layout__content__center div.contain-overflow div.matches__container div.matches div.prebet-match:nth-child(8) div.prebet-match__odd-market__container div.prebet-match__odds__container div.prebet-match__odds button.prebet-match__odd:nth-child(3) > span.prebet-match__odd__odd-value.bold")
    WebElement secondOdd;

    @FindBy(css = "div.app:nth-child(4) div.desktop-layout:nth-child(1) div.desktop-layout__content:nth-child(2) div.desktop-layout__content__center div.contain-overflow div.matches__container div.matches div.prebet-match:nth-child(9) div.prebet-match__odd-market__container div.prebet-match__odds__container div.prebet-match__odds button.prebet-match__odd:nth-child(2) > span.prebet-match__odd__odd-value.bold")
    WebElement thirdOdd;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/div[2]/button[1]")
    WebElement placeBet;

    @FindBy(xpath = "//body/div[3]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/button[1]")
    WebElement shareBetSlip;

    @FindBy(xpath = "//body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/a[3]/*[1]")
    WebElement shareOnWhatsApp;

    @FindBy(xpath = "//button[contains(text(),'Copy Link')]")
    WebElement shareLink;

    @FindBy (xpath = "//a[contains(text(),'Home')]")
    WebElement homeBtn;


    public void verifyBetPlacement() throws InterruptedException {
        Thread.sleep(1200);
        waitAndClick(firstOdd);
        waitAndClick(secondOdd);
        waitAndClick(thirdOdd);
        Thread.sleep(1200);
        waitAndClick(placeBet);
        softAssert.assertTrue(getSuccessfulBet().isDisplayed());
        waitAndClick(homeBtn);
    }

    public void verifyBetSlipShare() throws InterruptedException {
        Thread.sleep(1200);
        waitAndClick(firstOdd);
        waitAndClick(secondOdd);
        waitAndClick(shareBetSlip);
        waitAndClick(thirdOdd);
        Thread.sleep(1200);
        waitAndClick(shareOnWhatsApp);
        Thread.sleep(1200);
        // softAssert.assertTrue(getShareLink().isDisplayed());
    }

   private void waitAndClick(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, durationInSeconds);
    try {
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(clickableElement).click().build().perform();
    } catch (TimeoutException e) {
        e.printStackTrace(); // Print the stack trace for debugging
    }
}


    private void scrollIntoView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public WebElement getSuccessfulBet() {
        return successfulBet;
    }

    public WebElement getShareLink() {
        return shareLink;
    }
}
