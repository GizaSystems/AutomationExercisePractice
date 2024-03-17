package com.gizasystems.automationexercise.pages;

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
    private final By addressVerifiycationCountry = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By addressVerifiycationCity = By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
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
        driver.element().type(commentTextArea, text);
        driver.element().click(placeOrderBtn);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

    @Step("Verifiying address details")
    public CheckOutPage verifiyingAddressDetails(String firstName, String gender, String lastname, String address, String country, String city) {
        driver.verifyThat().element(addressVerificationFirstName).text().equalsIgnoringCaseSensitivity(gender + ". " + firstName + " " + lastname).perform();
        driver.verifyThat().element(addressVerificationAddress1).text().equalsIgnoringCaseSensitivity(address).perform();
        driver.verifyThat().element(addressVerifiycationCountry).text().equalsIgnoringCaseSensitivity(country).perform();
        driver.verifyThat().element(addressVerifiycationCity).text().contains(city).perform();
        return this;
    }
}
