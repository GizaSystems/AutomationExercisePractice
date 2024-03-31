package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckOutPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/checkout";

    // Locators
    private final By addressVerificationFirstName_link = By.xpath("//ul[@class = 'address item box']//li[@class='address_firstname address_lastname']");
    private final By addressVerificationAddress1_link = By.xpath("//ul[@class = 'address item box']//li[@class='address_address1 address_address2'][2]");
    private final By addressVerificationCity_link = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By addressVerificationCountry_link = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By verificationCity_link = By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
    private final By deliveryFullAddress_div = By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
    private final By billingFullAddress_div = By.xpath("//ul[@class='address alternate_item box']//li[@class='address_city address_state_name address_postcode']");
    private final By placeOrderBtn_button = By.xpath("//div//a[@class='btn btn-default check_out']");
    private final By commentTextArea_input = By.className("form-control");

    // Constructor
    public CheckOutPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate To Checkout Page")
    public CheckOutPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Enter Description In Comment Text Area And Click 'Place Order'")
    public CheckOutPage enteringDescriptionInCommentArea(String text) {
        driver.element().type(commentTextArea_input, text);
        driver.element().click(placeOrderBtn_button);
        GoogleAlert.dismissAlert(driver, placeOrderBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verifying Address Details")
    public CheckOutPage verifyingAddressDetails(String firstName, String address, String city) {
        driver.verifyThat().element(addressVerificationFirstName_link).text().isEqualTo(firstName).perform();
        driver.verifyThat().element(addressVerificationAddress1_link).text().isEqualTo(address).perform();
        driver.verifyThat().element(addressVerificationCity_link).text().isEqualTo(city).perform();
        return this;
    }

    @Step("Verifying Address Details In All Fields")
    public CheckOutPage verifyingAddressDetails(String firstName, String gender, String lastname, String address, String country, String city) {
        driver.verifyThat().element(addressVerificationFirstName_link).text().equalsIgnoringCaseSensitivity(gender + ". " + firstName + " " + lastname).perform();
        driver.verifyThat().element(addressVerificationAddress1_link).text().equalsIgnoringCaseSensitivity(address).perform();
        driver.verifyThat().element(addressVerificationCountry_link).text().equalsIgnoringCaseSensitivity(country).perform();
        driver.verifyThat().element(verificationCity_link).text().contains(city).perform();
        return this;
    }

    @Step("Verify The Delivery Address")
    public CheckOutPage verifyDeliveryAddressDetails(String city, String state, String zipCode) {
        driver.verifyThat().element(deliveryFullAddress_div).text().isEqualTo(city + " " + state + " " + zipCode).perform();
        return this;
    }

    @Step("Verify The Billing Address Details")
    public CheckOutPage verifyBillingAddressDetails(String city, String state, String zipCode) {
        driver.assertThat().element(billingFullAddress_div).text().isEqualTo(city + " " + state + " " + zipCode).perform();
        return this;
    }
}
