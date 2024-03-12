package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckOutPage {

    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/checkout";

    // Locators

    private final By addressVerifiycation_FirstName = By.xpath("//ul[@class = 'address item box']//li[@class='address_firstname address_lastname']");
    private final By addressVerifiycation_address1 = By.xpath("//ul[@class = 'address item box']//li[@class='address_address1 address_address2']");
    private final By addressVerifiycation_city = By.xpath("//ul[@class='address item box']//li[@class='address_country_name']");
    private final By placeOrder_Btn = By.xpath("//div//a[@class='btn btn-default check_out']");
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
    public CheckOutPage enteringDescriptionInCommentArea(String Text) {
        driver.element().type(commentTextArea, Text);
        driver.element().click(placeOrder_Btn);
        return this;
    }
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verifiying address details")
    public CheckOutPage verififyingAddressDetails(String FirstName, String Address, String City) {
        driver.verifyThat().element(addressVerifiycation_FirstName).text().equalsIgnoringCaseSensitivity(FirstName).perform();
        driver.verifyThat().element(addressVerifiycation_address1).text().equalsIgnoringCaseSensitivity(Address).perform();
        driver.verifyThat().element(addressVerifiycation_city).text().equalsIgnoringCaseSensitivity(City).perform();
        return this;
    }
}
