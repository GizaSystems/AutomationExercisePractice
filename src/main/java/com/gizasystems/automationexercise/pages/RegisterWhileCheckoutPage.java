package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterWhileCheckoutPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    //Locators
    private final By checkoutBodyMessage_div = By.cssSelector("div.modal-content > div > h4");
    private final By registerLoginLink_div = By.xpath("//p[@class='text-center']/a[@href='/login']/u");
    private final By checkoutFullAddress_div = By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
    private final By deliveryAddressUserPhoneNumber_div = By.xpath("//ul[@class='address item box']//li[@class='address_phone']");
    private final By productDetails_div = By.cssSelector("td.cart_description > h4 > a");
    private final By productDescription_div = By.xpath("//tr[@class='cart_menu']//td[@class='description']");
    private final By commentSectionTextBox_div = By.xpath("//textarea[@class='form-control']");
    private final By placeOrderBtn_div = By.xpath("//a[@href='/payment']");

    // Constructor
    public RegisterWhileCheckoutPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Click On Register/Login Button")
    public RegisterWhileCheckoutPage clickOnRegisterLoginBtn() {
        driver.element().click(registerLoginLink_div);
        return this;
    }

    @Step("Navigate To Review Your Order Section")
    public RegisterWhileCheckoutPage scrollToReviewOrderSection() {
        driver.element().hover(productDescription_div);
        return this;
    }

    @Step("Type Order Comments")
    public RegisterWhileCheckoutPage typeOrderComments(String comment) {
        driver.element().hover(commentSectionTextBox_div);
        driver.element().type(commentSectionTextBox_div, comment);
        return this;
    }

    @Step("Click On Place Order Button")
    public RegisterWhileCheckoutPage clickOnPlaceOrderBtn() {
        driver.element().click(placeOrderBtn_div);
        GoogleAlert.dismissAlert(driver, placeOrderBtn_div);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\
    @Step("Verify The Checkout Pop Up Is Displayed")
    public RegisterWhileCheckoutPage verifyCheckoutPopUpDisplayed(String expectedMessage) {
        driver.element().verifyThat(checkoutBodyMessage_div).text().contains(expectedMessage).perform();
        return this;
    }

    @Step("Verify The Checkout Full Address At Delivery Address Details")
    public RegisterWhileCheckoutPage verifyCheckoutFullAddressDetails(String address, String state, String zipCode) {
        driver.element().verifyThat(checkoutFullAddress_div).text().equalsIgnoringCaseSensitivity(address + " " + state + " " + zipCode).perform();
        return this;
    }

    @Step("Verify The User Phone Number At Delivery Address Details")
    public RegisterWhileCheckoutPage verifyUserPhoneNumber(String phoneNumber) {
        driver.element().verifyThat(deliveryAddressUserPhoneNumber_div).text().isEqualTo(phoneNumber).perform();
        return this;
    }

    @Step("Review And Validate The Added Products")
    public RegisterWhileCheckoutPage reviewCartProducts(String productName) {
        driver.element().verifyThat(productDetails_div).text().isEqualTo(productName);
        return this;
    }
}
