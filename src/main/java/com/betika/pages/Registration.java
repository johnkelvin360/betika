package com.betika.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.betika.utils.BrowserManager;

public class Registration extends BrowserManager {
    
         public Registration() {
        PageFactory.initElements(driver, this);
    }
    //Identify web elements on registration page

    @FindBy (xpath = "//a[contains(text(),'Register')]")
    static WebElement regBtn;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/input[1]")
    WebElement phoneNumberField;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]")
    WebElement passwordField;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/input[1]")
    WebElement confirmPasswordField;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[5]/div[1]/span[1]")
    WebElement checkBox;

    @FindBy (xpath = "//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]")
    WebElement submitBtn;

    @FindBy (css = "#notifications-root")
    static WebElement promptMessage;


    public void getRegBtn(){
         regBtn.click();
    }

    public WebElement getPhoneNumberField(){
        return phoneNumberField;
    }

    public WebElement getPasswordField(){
        return passwordField;
    }

    public WebElement getConfirmPasswordField(){
        return confirmPasswordField;
    }

    public WebElement getCheckBox(){
        return checkBox;
    }

    public WebElement getSubmitBtn(){
        return submitBtn;
    }

      public static WebElement getPromptMessage(){
        return promptMessage;
    }
    public void registration() {
        String phoneNumber = dynamicNumber();
        String password = dynamicPassword();

        getPhoneNumberField().clear();
        getPhoneNumberField().sendKeys(phoneNumber);

        getPasswordField().clear();
        getPasswordField().sendKeys(password);

        getConfirmPasswordField().clear();
        getConfirmPasswordField().sendKeys(password);

        getCheckBox().click();
        getSubmitBtn().click();
    }
}
