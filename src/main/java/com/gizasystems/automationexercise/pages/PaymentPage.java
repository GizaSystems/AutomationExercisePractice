package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    //Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/payment";

    // Locators
    private final By cardNameInput = By.xpath("//div//input[@class='form-control']");
    private final By cardNumberInput = By.xpath("//div//input[@class='form-control card-number']");
    private final By cvcInput = By.xpath("//div//input[@class='form-control card-cvc']");
    private final By cardExpMonthInput = By.xpath("//div//input[@class='form-control card-expiry-month']");
    private final By cardExpYearInput = By.xpath("//div//input[@class='form-control card-expiry-year']");
    private final By payOrderBtn = By.xpath("//button[@class='form-control btn btn-primary submit-button']");
    private final By orderPlacedSuccessMessage = By.xpath("//p[@style='font-size: 20px; font-family: garamond;']");

    // Constructors
    public PaymentPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    public PaymentPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Enter payment details: Name on Card, Card Number, CVC, Expiration date")
    public PaymentPage enterPaymentDetails(String user, String number, String cvc, String expMonth, String expYear) {
        driver.element().type(cardNameInput, user);
        driver.element().type(cardNumberInput, number);
        driver.element().type(cvcInput, cvc);
        driver.element().type(cardExpMonthInput, expMonth);
        driver.element().type(cardExpYearInput, expYear);
        return this;
    }

    @Step("Click 'Pay and Confirm Order' button")
    public PaymentPage clickOnPayOrder() {
        driver.element().click(payOrderBtn);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

    @Step("Verify Order Placement Success Message")
    public PaymentPage verifyOrderPlacementSuccessMessage(String message) {
        driver.verifyThat().element(orderPlacedSuccessMessage).text().isEqualTo(message).perform();
        return this;
    }
}