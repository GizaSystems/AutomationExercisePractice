package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class PaymentPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    //Locators
    private final By nameOnCardTxtInput_div= By.xpath("//input[@data-qa='name-on-card']");
    private final By cardNumberTxtInput_div=By.xpath("//input[@data-qa='card-number']");
    private final By CvcTxtInput_div=By.xpath("//input[@data-qa='cvc']");
    private final By expiryMonthTxtInput_div=By.xpath("//input[@data-qa='expiry-month']");
    private final By expiryYearTxtInput_div=By.xpath("//input[@data-qa='expiry-year']");
    private final By payAndConfirmOrderBtn_div=By.xpath("//button[@data-qa='pay-button']");
    // Constructor
    public PaymentPage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;
    }

    //////////////////// Actions \\\\\\\\\\

    @Step("Type user name on card")
    public PaymentPage typeUserCardName(String userName){
        driver.element().type(nameOnCardTxtInput_div,userName);
        return this;
    }
    @Step("Type User Card number")
    public PaymentPage typeCardNumber(String cardNumber){
        driver.element().type(cardNumberTxtInput_div,cardNumber);
            return this;
    }
    @Step("Type User Card CVC")
    public PaymentPage typeCardCvc(String cvc){
        driver.element().type(CvcTxtInput_div,cvc);
        return this;
    }
    @Step("Type User card Expiry Month")
    public PaymentPage typeExpiryMonth(String expiryMonth){
        driver.element().type(expiryMonthTxtInput_div,expiryMonth);
        return this;
    }
    @Step("Type User card Expiry Year ")
    public PaymentPage typeExpiryYear(String expiryYear){
        driver.element().type(expiryYearTxtInput_div,expiryYear);
        return this;
    }
    @Step("Click on Pay and Confirm Order")
    public PaymentPage clickOnPayAndConfirmBtn(){
        driver.element().click(payAndConfirmOrderBtn_div);
        return this;
    }
    //////////////////// Validations \\\\\\\\\\
}
