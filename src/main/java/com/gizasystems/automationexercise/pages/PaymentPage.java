package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/payment";

    // Locators
    private final By nameOnCardTxtInput_input = By.xpath("//input[@data-qa='name-on-card']");
    private final By cardNumberTxtInput_input = By.xpath("//input[@data-qa='card-number']");
    private final By cvcTxtInput_input = By.xpath("//input[@data-qa='cvc']");
    private final By expiryMonthTxtInput_input = By.xpath("//input[@data-qa='expiry-month']");
    private final By expiryYearTxtInput_input = By.xpath("//input[@data-qa='expiry-year']");
    private final By payAndConfirmOrder_button = By.xpath("//button[@data-qa='pay-button']");
    private final By orderConfirmed_h2 = By.xpath("//h2[@data-qa='order-placed']");
    private final By successPaymentMessage_text = By.id("success_message");
    private final By downloadInvoiceBtn_button = By.cssSelector(".btn.btn-default.check_out");
    private final By continueBtn_button = By.cssSelector(".btn.btn-primary");

    // Constructor
    public PaymentPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step(" Click On Continue Button")
    public PaymentPage clickContinueButton() {
        driver.element().click(continueBtn_button);
        return this;
    }

    @Step("Click On Pay And Confirm Order Button")
    public PaymentPage clickPayAndConfirmOrderButton() {
        driver.element().click(payAndConfirmOrder_button);
        return this;
    }

    @Step("Navigate To Payment Page")
    public PaymentPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Fill In Payment Information")
    public PaymentPage fillPaymentInformation(String userName, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        typeUserCardName(userName);
        typeCardNumber(cardNumber);
        typeCardCvc(cvc);
        typeExpiryMonth(expiryMonth);
        typeExpiryYear(expiryYear);
        return this;
    }

    @Step("Type Username On Card")
    public PaymentPage typeUserCardName(String userName) {
        driver.element().type(nameOnCardTxtInput_input, userName);
        return this;
    }

    @Step("Type User Card Number")
    public PaymentPage typeCardNumber(String cardNumber) {
        driver.element().type(cardNumberTxtInput_input, cardNumber);
        return this;
    }

    @Step("Type User Card CVC")
    public PaymentPage typeCardCvc(String cvc) {
        driver.element().type(cvcTxtInput_input, cvc);
        return this;
    }

    @Step("Type User Card Expiry Month")
    public PaymentPage typeExpiryMonth(String expiryMonth) {
        driver.element().type(expiryMonthTxtInput_input, expiryMonth);
        return this;
    }

    @Step("Type User Card Expiry Year ")
    public PaymentPage typeExpiryYear(String expiryYear) {
        driver.element().type(expiryYearTxtInput_input, expiryYear);
        return this;
    }

    @Step("Click On Pay And Confirm Order")
    public PaymentPage clickOnPayAndConfirmBtn() {
        driver.element().hover(payAndConfirmOrder_button);
        driver.element().click(payAndConfirmOrder_button);
        return this;
    }

    @Step("Click On Download Invoice Button")
    public PaymentPage clickOnDownloadInvoiceButton() {
        driver.element().hover(downloadInvoiceBtn_button);
        driver.element().click(downloadInvoiceBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step(" Validate On Payment Success Validation Message ")
    public PaymentPage validateOnPaymentSuccessValidationMessage(String message) {
        driver.verifyThat().element(successPaymentMessage_text).text().contains(message).perform();
        return this;
    }

    @Step(" Validate Invoice  Is downloaded  ")
    public PaymentPage validateInviceDownloaded(String filename) {
        Validations.verifyThat().file(SHAFT.Properties.paths.downloads(), filename).exists().perform();
        return this;
    }

    @Step("Verify The Success Message After Order Placed!")
    public PaymentPage verifySuccessMessage(String message) {
        driver.element().verifyThat(orderConfirmed_h2).text().isEqualTo(message);
        return this;
    }
}
