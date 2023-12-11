package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.betika.utils.BrowserManager;

public class VerificationCode extends BrowserManager {
    
    public VerificationCode(){
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//label[contains(text(),'Verification Code')]")
    WebElement verificationInputText;

    @FindBy (xpath = "//span[contains(text(),'Resend Code')]")
    WebElement resendCode;

    @FindBy (xpath = "//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/button[1]/span[1]")
    WebElement verifyLogin;


    public WebElement getVerificationInputText(){
        return verificationInputText;
    }

    public WebElement getResendCode(){
        return resendCode;
    }

    public WebElement getVerifyLogin(){
        return verifyLogin;
    }

}
