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

    private final By addressVerificationFirstName = By.xpath("//ul[@class = 'address item box']//li[@class='address_firstname address_lastname']");
    private final By addressVerificationAddress1 = By.xpath("//ul[@class = 'address item box']//li[@class='address_address1 address_address2'][2]");
    private final By addressVerificationCity = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By addressVerificationCountry = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By verificationCity = By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
    private final By placeOrderBtn = By.xpath("//div//a[@class='btn btn-default check_out']");
    private final By commentTextArea = By.className("form-control");

    // Constructor

    public CheckOutPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    public CheckOutPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step(" Enter description in comment text area and click 'Place Order'")
    public CheckOutPage enteringDescriptionInCommentArea(String text) {
        GoogleAlert.dismissAlert(driver, commentTextArea);
        driver.element().type(commentTextArea, text);
        GoogleAlert.dismissAlert(driver, placeOrderBtn);
        driver.element().click(placeOrderBtn);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

    @Step("Verifiying address details")
    public CheckOutPage verifyingAddressDetails(String firstName, String address, String city) {
        driver.verifyThat().element(addressVerificationFirstName).text().isEqualTo(firstName).perform();
        driver.verifyThat().element(addressVerificationAddress1).text().isEqualTo(address).perform();
        driver.verifyThat().element(addressVerificationCity).text().isEqualTo(city).perform();
        return this;
    }

    @Step("Verifiying address details All fileds")
    public CheckOutPage verifyingAddressDetails(String firstName, String gender, String lastname, String address, String country, String city) {
        driver.verifyThat().element(addressVerificationFirstName).text().equalsIgnoringCaseSensitivity(gender + ". " + firstName + " " + lastname).perform();
        driver.verifyThat().element(addressVerificationAddress1).text().equalsIgnoringCaseSensitivity(address).perform();
        driver.verifyThat().element(addressVerificationCountry).text().equalsIgnoringCaseSensitivity(country).perform();
        driver.verifyThat().element(verificationCity).text().contains(city).perform();
        return this;
    }
}
