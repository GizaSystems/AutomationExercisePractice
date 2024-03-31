package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl") + "/view_cart";

    // Locators
    private final By proceedToCheckout_button = By.cssSelector(".btn.btn-default.check_out");
    private final By productName_h4 = By.xpath("//td[@class='cart_description']//h4");
    private final By viewCart_div = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");
    private final By subscriptionTxt_h2 = By.tagName("h2");
    private final By subscriptionEmail_input = By.id("susbscribe_email");
    private final By subscribeBtn_button = By.id("subscribe");
    private final By successAlert_div = By.xpath("//div[@class='alert-success alert']");
    private final By registerLoginPopup_link = By.cssSelector("p.text-center a[href='/login']");
    private By removeProduct_button(String productName) {
        return By.xpath("//h4[.='" + productName + "']//parent::td//parent::tr//a[@class='cart_quantity_delete']");
    }

    private By productName_link(String itemName) {
        return By.xpath("//a[normalize-space()='" + itemName + "']");
    }

    private By productPrice_link(String itemName) {
        return By.xpath("//a[text()='" + itemName + "']//ancestor::td[@class='cart_description']//following-sibling::td[@class='cart_price']//p");
    }

    private By productQuantity_link(String itemName) {
        return By.xpath("//a[text()='" + itemName + "']//ancestor::td[@class='cart_description']//following-sibling::td[@class='cart_quantity']//button[@class='disabled']");
    }

    private By productTotalPrice_link(String itemName) {
        return By.xpath("//a[text()='" + itemName + "']//ancestor::td[@class='cart_description']//following-sibling::td[@class='cart_total']//p");
    }

    // Constructor

    public CartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate To Cart Page")
    public CartPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    //Clicking using JS as fix for pipeline failure on safari (Click isn't happening even with ClickUsingJS Flag)
    @Step("Open Cart Page")
    public CartPage openCart() {
        driver.element().clickUsingJavascript(viewCart_div);
        return this;
    }

    @Step("Enter Subscription Email")
    public CartPage enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionEmail_input, email);
        return this;
    }

    @Step("Click On Subscribe Button")
    public CartPage clickOnSubscribeButton() {
        driver.element().click(subscribeBtn_button);
        return this;
    }

    @Step("Click X To Remove Product From Cart")
    public CartPage clickToRemoveProduct(String productName) {
        driver.element().click(removeProduct_button(productName));
        return this;
    }

    @Step("Click On Proceed To Checkout Button")
    public CartPage proceedToCheckOut() {
        driver.element().click(proceedToCheckout_button);
        return this;
    }

    @Step("Click on Register / Login Popup Link ")
    public CartPage clickOnRegisterLoginPopupLink() {
        driver.element().click(registerLoginPopup_link);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Cart Page Is Loaded")
    public CartPage verifyCartPageIsLoaded() {
        driver.verifyThat().element(proceedToCheckout_button).isVisible().perform();
        return this;
    }

    @Step("Validate On Product Added To Cart Page")
    public CartPage verifyProductAddedToCart(String addedProductName) {
        driver.verifyThat().element(productName_h4).text().isEqualTo(addedProductName).perform();
        return this;
    }

    @Step("Validate On Visibility Of The Subscription Text")
    public CartPage validateOnVisibilityOfSubscriptionText() {
        driver.verifyThat().element(subscriptionTxt_h2).text().isEqualTo("SUBSCRIPTION").perform();
        return this;
    }

    @Step("Validate On Success Message Of Subscription Email")
    public CartPage validateOnSuccessMessageOfSubscriptionEmail(String expectedText) {
        driver.verifyThat().element(successAlert_div).text().isEqualTo(expectedText).perform();
        return this;
    }

    @Step("Validate Products Are Added To Cart")
    public CartPage validateOnItemsAddedInCart(String ItemName) {
        driver.verifyThat().element(productName_link(ItemName)).textTrimmed().isEqualTo(ItemName).perform();
        return this;
    }

    @Step("Validate On The Price Of The Products")
    public CartPage validateOnProductPrices(String ItemName, String itemPrice) {
        driver.verifyThat().element(productPrice_link(ItemName)).text().isEqualTo(itemPrice).perform();
        return this;
    }

    @Step("Validate On The Quantity Of The Products")
    public CartPage validateOnProductQuantity(String ItemName, String itemQuantity) {
        driver.verifyThat().element(productQuantity_link(ItemName)).textTrimmed().isEqualTo(itemQuantity).perform();
        return this;
    }

    @Step("Validate On The Total Price Of The Products")
    public CartPage validateOnTotalPrice(String ItemName, String itemTotalPrice) {
        driver.verifyThat().element(productTotalPrice_link(ItemName)).textTrimmed().isEqualTo(itemTotalPrice).perform();
        return this;
    }

    @Step("Verify That Cart Does Not Contain The Removed Product")
    public CartPage validateOnRemovedProduct(String productName) {
        driver.verifyThat().element(removeProduct_button(productName)).doesNotExist().perform();
        return this;
    }
}
