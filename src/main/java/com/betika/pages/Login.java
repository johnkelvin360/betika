package com.betika.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.betika.utils.BrowserManager;

public class Login extends BrowserManager {

    public Login(){
    PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//a[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/input[1]")
    WebElement phoneNumberField;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")
    WebElement passwordField;

    @FindBy (xpath = "//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[4]/button[1]/span[1]")
    WebElement submitBtn;


	@FindBy (xpath = "//p[contains(text(),'Please enter a phone number')]")
	static WebElement errorMsg;

    @FindBy (css = "#notifications-root")
    static WebElement invalidPhoneNumber;

    @FindBy (xpath = "/html[1]/body[1]/div[3]/div[1]/header[1]/div[1]/div[2]/div[1]/a[2]/span[1]")
    WebElement profile;

    @FindBy (xpath = "//button[contains(text(),'Sign Out')]")
    WebElement signOutBtn;

    @FindBy (xpath = "//header/div[1]/div[1]/button[1]/div[1]")
    WebElement widget;

    @FindBy (xpath = "//button[contains(text(),'Logout')]")
    WebElement logoutBtn;

    @FindBy (xpath = "//header/div[1]/div[1]/img[1]")
    WebElement depositBtn;

    @FindBy (xpath = "//a[contains(text(),'Register')]")
    static WebElement regBtn;

    public WebElement getRegBtn(){
       return regBtn;
   }
    public WebElement getLoginBtn(){
        return loginBtn;
    }

    public WebElement getPhoneNumberField(){
        return phoneNumberField;
    }

    public WebElement getPasswordField(){
        return passwordField;
    }

     public static WebElement getErrorMsg(){
        return errorMsg;
    }

    public static WebElement getInvalidPhoneNumber(){
        return invalidPhoneNumber;
    }

     public WebElement getProfile(){
        return profile;
    }

    public WebElement getSignOutBtn(){
        return signOutBtn;
    }
    public WebElement getWidget() {
        return widget;
    }

    public void clickWidget() {
        widget.click();
    }

    public WebElement getLogoutBtn(){
        return logoutBtn;
    }

     public WebElement getDepositBtn(){
        return depositBtn;
    }

    public WebElement getSubmitBtn(){
        return submitBtn;
    }

    
}
