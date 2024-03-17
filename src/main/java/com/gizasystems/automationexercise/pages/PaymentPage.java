package com.gizasystems.automationexercise.pages;

import com.shaft.cli.FileActions;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static org.codehaus.plexus.util.FileUtils.fileExists;


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
    public PaymentPage validateInviceDownloaded(String filePath, String filename) {
        Validations.assertThat().file(filePath, filename).exists().perform();
        return this;
    }
    @Step(" Delete invoice ")
    public PaymentPage deleteInvoice(String filePath, String filename) {
        FileActions fileActions = FileActions.getInstance();
        fileActions.deleteFolder(filePath + filename);
        return this;
    }


}
