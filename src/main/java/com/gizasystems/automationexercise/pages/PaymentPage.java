package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class PaymentPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/payment";
    // Locators
    private final By nameOnCard = By.xpath("//input[@class='form-control']");
    private final By cardNumber = By.xpath("//input[contains(@class, 'card-number')]");
    private final By cvc = By.xpath("//input[contains(@class, 'card-cvc')]");
    private final By cardExpirationMonth = By.xpath("//input[contains(@class, 'card-expiry-month')]");
    private final By cardExpirationYear = By.xpath("//input[contains(@class, 'card-expiry-year')]");
    private final By payAndConfirmOrderButton = By.xpath("//button[@id='submit']");
    private final By successPaymentMessage = By.id("success_message");
    private final By downloadInvoiceButton = By.cssSelector(".btn.btn-default.check_out");
    private final By continueButton = By.cssSelector(".btn.btn-primary");
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    //Locators
    private final By nameOnCardTxtInput_div = By.xpath("//input[@data-qa='name-on-card']");
    private final By cardNumberTxtInput_div = By.xpath("//input[@data-qa='card-number']");
    private final By CvcTxtInput_div = By.xpath("//input[@data-qa='cvc']");
    private final By expiryMonthTxtInput_div = By.xpath("//input[@data-qa='expiry-month']");
    private final By expiryYearTxtInput_div = By.xpath("//input[@data-qa='expiry-year']");
    private final By payAndConfirmOrderBtn_div = By.xpath("//button[@data-qa='pay-button']");
    private final By orderConfirmed_div = By.xpath("//h2[@data-qa='order-placed']");

    // Constructor
    public PaymentPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step(" Navigate to Payment Page")
    public PaymentPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step(" Enter Card Information Details for Payment")
    public PaymentPage cardDetailsEntryForPayment(String expectedCardName, String expectedCardNumber, String CardCvc, String expirationMonth, String expirationYear) {
        driver.element().type(nameOnCard, expectedCardName);
        driver.element().type(cardNumber, expectedCardNumber);
        driver.element().type(cvc, CardCvc);
        driver.element().type(cardExpirationMonth, expirationMonth);
        driver.element().type(cardExpirationYear, expirationYear);
        return this;
    }

    @Step("DownLoading Invoice ")
    public PaymentPage downloadInvoice() {
        driver.element().click(downloadInvoiceButton);
        return this;
    }

    @Step(" Click On Continue Button")
    public PaymentPage clickContinueButton() {
        driver.element().click(continueButton);
        return this;
    }

    @Step(" Click On Pay And Confirm Order Button")
    public PaymentPage clickPayAndConfirmOrderButton() {
        driver.element().click(payAndConfirmOrderButton);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step(" Validate on Payment Success Validation Message ")
    public PaymentPage validateOnPaymentSuccessValidationMessage(String message) {
        driver.verifyThat().element(successPaymentMessage).text().contains(message).perform();
        return this;
    }

    @Step(" Validate Invoice  Is downloaded  ")
    public PaymentPage validateInviceDownloaded(String filename) {
        Validations.assertThat().file(SHAFT.Properties.paths.downloads(), filename).exists().perform();
        return this;
    }

    //////////////////// Actions \\\\\\\\\\

    @Step("Type user name on card")
    public PaymentPage typeUserCardName(String userName) {
        driver.element().type(nameOnCardTxtInput_div, userName);
        return this;
    }

    @Step("Type User Card number")
    public PaymentPage typeCardNumber(String cardNumber) {
        driver.element().type(cardNumberTxtInput_div, cardNumber);
        return this;
    }

    @Step("Type User Card CVC")
    public PaymentPage typeCardCvc(String cvc) {
        driver.element().type(CvcTxtInput_div, cvc);
        return this;
    }

    @Step("Type User card Expiry Month")
    public PaymentPage typeExpiryMonth(String expiryMonth) {
        driver.element().type(expiryMonthTxtInput_div, expiryMonth);
        return this;
    }

    @Step("Type User card Expiry Year ")
    public PaymentPage typeExpiryYear(String expiryYear) {
        driver.element().type(expiryYearTxtInput_div, expiryYear);
        return this;
    }

    @Step("Click on Pay and Confirm Order")
    public PaymentPage clickOnPayAndConfirmBtn() {
        driver.element().hover(payAndConfirmOrderBtn_div);
        driver.element().click(payAndConfirmOrderBtn_div);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\
    @Step("Verify the success message after order placed !")
    public PaymentPage verifySuccessMessage(String message) {
        driver.element().assertThat(orderConfirmed_div).text().isEqualTo(message);
        return this;
    }
}
