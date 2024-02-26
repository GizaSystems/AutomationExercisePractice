package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    private String url = System.getProperty("baseUrl") + "/view_cart";


    // Locators
    private final By proceedToCheckout_btn = By.cssSelector(".btn.btn-default.check_out");
    private final By productName_h4 = By.xpath("//td[@class='cart_description']//h4");
    private final By viewCart_a = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");
    private final By subscriptionTxt_h2 = By.tagName("h2");
    private final By subscription_input = By.id("susbscribe_email");
    private final By subscribeBtn_button = By.id("subscribe");
    private final By successAlert_div = By.xpath("//div[@class='alert-success alert']");
    private By productName(String name) {
        return By.xpath("//a[normalize-space()='" + name + "']");
    }
    private By productPrice(Integer ID) {
        return By.xpath("//tr[@id=\"product-" + ID + "\"]//td[@class='cart_price']//p");
    }
    private By productQuantity(Integer ID) {
        return By.xpath("//tr[@id=\"product-" + ID + "\"]//button[@class='disabled']");
    }
    private By productTotalPrice(Integer ID) {
        return By.xpath("//tr[@id=\"product-" + ID + "\"]//p[@class='cart_total_price']");
    }

    // Constructor

    public CartPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    public CartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //Clicking using JS as fix for pipeline failure on safari (Click isn't happening even with ClickUsingJS Flag)
    @Step("Open Cart Page")
    public CartPage openCart() {
        driver.element().clickUsingJavascript(viewCart_a);
        return this;
    }

    @Step("Enter Subscription Email")
    public CartPage enterSubscriptionEmail(String email) {
        driver.element().type(subscription_input, email);
        return this;
    }

    @Step("Click on Subscribe Button")
    public CartPage clickOnSubscribeButton() {
        driver.element().click(subscribeBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Cart Page is Loaded")
    public CartPage verifyCartPageIsLoaded() {
        driver.verifyThat().element(proceedToCheckout_btn).isVisible().perform();
        return this;
    }

    @Step("Validate on Product Added To Cart Page")
    public CartPage verifyProductAddedToCart(String addedProductName) {
        driver.assertThat().element(productName_h4).text().isEqualTo(addedProductName).perform();
        return this;
    }

    @Step("Validate on Visibility of the subscription Text")
    public CartPage validateOnVisibilityOfSubscriptionText() {
        driver.verifyThat().element(subscriptionTxt_h2).text().isEqualTo("SUBSCRIPTION").perform();
        return this;
    }

    @Step("Validate on Success Message of Subscription Email")
    public CartPage validateOnSuccessMessageOfSubscriptionEmail(String expectedText) {
        driver.verifyThat().element(successAlert_div).text().isEqualTo(expectedText).perform();
        return this;
    }


    @Step("Validate products are added to Cart")
    public CartPage validateOnItemsAddedInCart(String ItemName) {
        driver.verifyThat().element(productName(ItemName)).textTrimmed().isEqualTo(ItemName).perform();
        return this;
    }

    @Step("Validate on the price of the products")
    public CartPage validateOnProductPrices(Integer id, String itemPrice) {
        driver.verifyThat().element(productPrice(id)).text().isEqualTo(itemPrice).perform();
        return this;
    }

    @Step("Validate on the Quantity of the products")
    public CartPage validateOnProductQuantity(Integer ID, String itemQuantity) {
        driver.verifyThat().element(productQuantity(ID)).textTrimmed().isEqualTo(itemQuantity).perform();
        return this;
    }

    @Step("Validate on the Total Price of the Products")
    public CartPage validateOnTotalPrice(Integer ID, String itemTotalPrice) {
        driver.verifyThat().element(productTotalPrice(ID)).textTrimmed().isEqualTo(itemTotalPrice).perform();
        return this;
    }


}
