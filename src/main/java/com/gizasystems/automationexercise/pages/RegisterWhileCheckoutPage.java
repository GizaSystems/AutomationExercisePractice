package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterWhileCheckoutPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    //Locators
private final By checkoutBodyMessage_div= By.cssSelector("div.modal-content > div > h4");
private final By registerLoginLink_div=By.xpath("//p[@class='text-center']/a[@href='/login']/u");
private final By checkoutFullAddress_div=By.xpath("//ul[@class='address item box']//li[@class='address_city address_state_name address_postcode']");
private final By deliveryAddresUserPhoneNumber_div=By.xpath("//ul[@class='address item box']//li[@class='address_phone']");
private final By productDetails_div=By.cssSelector("td.cart_description > h4 > a");
private final By productDescription_div=By.xpath("//tr[@class='cart_menu']//td[@class='description']");
private final By commentSectionTextBox_div=By.xpath("//textarea[@class='form-control']");
private final By placeOrderBtn_div=By.xpath("//a[@href='/payment']");
    // Constructor
    public RegisterWhileCheckoutPage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;
    }
    //////////////////// Actions \\\\\\\\\\
    @Step("Click on Register/Login button")
    public RegisterWhileCheckoutPage clickOnRegisterLoginBtn(){
        driver.element().click(registerLoginLink_div);
        return this;
    }
    @Step("Navigate to Review your order section")
    public RegisterWhileCheckoutPage scrollToReviewOrderSection(){
        driver.element().hover(productDescription_div);
        return this;
    }
    @Step("Type order comments")
    public RegisterWhileCheckoutPage typeOrderComments(String comment){
        driver.element().hover(commentSectionTextBox_div);
        driver.element().type(commentSectionTextBox_div,comment);
        return this;
    }
    @Step("Click on Place order Button")
    public RegisterWhileCheckoutPage clickOnPlaceOrderBtn(){
        driver.element().click(placeOrderBtn_div);
        GoogleAlert.dismissAlert(driver,placeOrderBtn_div);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\
    @Step("Verify the checkout pop up is displayed")
    public RegisterWhileCheckoutPage verifyCheckoutPopUpDisplayed(String expectedMessage){
        driver.element().assertThat(checkoutBodyMessage_div).text().contains(expectedMessage).perform();
        return this;
    }
    @Step("Verify the checkout full address at delivery address details")
    public RegisterWhileCheckoutPage verifyCheckoutFullAddressDetails(String address,String state,String zipCode){
        driver.element().assertThat(checkoutFullAddress_div).text().equalsIgnoringCaseSensitivity(address +" "+ state + " "+ zipCode).perform();
        return this;
    }
    @Step("Verify the user Phone number at delivery address details")
    public RegisterWhileCheckoutPage verifyUserPhoneNumber(String phoneNumber){
        driver.element().assertThat(deliveryAddresUserPhoneNumber_div).text().isEqualTo(phoneNumber).perform();
        return this;
    }
    @Step("Review and validate the added products")
    public RegisterWhileCheckoutPage reviewCartProducts(String productName){
        driver.element().assertThat(productDetails_div).text().isEqualTo(productName);
        return this;
    }
}
