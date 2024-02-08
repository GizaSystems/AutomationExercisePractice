package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By proceedToCheckout_btn = By.cssSelector(".btn.btn-default.check_out");
    private final By productName_h4 = By.xpath("//td[@class='cart_description']//h4");
    private final By viewCart_a = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");

    private String url = System.getProperty("baseUrl") + "/view_cart";

    private final By firstProductName = By.xpath("//a[normalize-space()='Blue Top']");

    private final By secondProductName = By.xpath("//a[normalize-space()='Men Tshirt']");

    private final By firstProductPrice = By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 500')]");

    private final By secondProductPrice = By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 400')]");

    private final By firstProductQuantity = By.xpath("//tr[@id='product-1']//button[@class='disabled'][normalize-space()='1']");

    private final By secondProductQuantity = By.xpath("//tr[@id='product-2']//button[@class='disabled'][normalize-space()='1']");

    private final By firstProductTotal = By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 500']");

    private final By secondProductTotal = By.xpath("//p[@class='cart_total_price'][normalize-space()='Rs. 400']");

    // Constructor
    public CartPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //Clicking using JS as fix for pipeline failure on safari (Click isn't happening even with ClickUsingJS Flag)
    @Step("Open Cart Page")
    public CartPage openCart(){
        driver.element().clickUsingJavascript(viewCart_a);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Cart Page is Loaded")
    public CartPage verifyCartPageIsLoaded(){
        driver.verifyThat().element(proceedToCheckout_btn).isVisible().perform();
        return this;
    }

    @Step("Validate on Product Added To Cart Page")
    public CartPage verifyProductAddedToCart(String addedProductName){
        driver.assertThat().element(productName_h4).text().isEqualTo(addedProductName).perform();
        return this;
    }

    public CartPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("Validate both products are added to Cart")
    public CartPage validateOnItemsAddedInCart(String firstItemName, String secondItemName) {
        driver.verifyThat().element(firstProductName).text().isEqualTo(firstItemName).perform();
        driver.verifyThat().element(secondProductName).text().isEqualTo(secondItemName).perform();
        return this;
    }

    @Step("Validate on the price of the products")
    public CartPage validateOnProductPrices(String firstItemPrice, String secondItemPrice) {
        driver.verifyThat().element(firstProductPrice).text().isEqualTo(firstItemPrice).perform();
        driver.verifyThat().element(secondProductPrice).text().isEqualTo(secondItemPrice).perform();
        return this;
    }

    @Step("Validate on the Quantity of the products")
    public CartPage validateOnProductQuantity(String firstItemQuantity, String secondItemQuantity) {
        driver.verifyThat().element(firstProductQuantity).text().isEqualTo(firstItemQuantity).perform();
        driver.verifyThat().element(secondProductQuantity).text().isEqualTo(secondItemQuantity).perform();
        return this;
    }

    @Step("Validate on the Total Price of the Products")
    public CartPage validateOnTotalPrice(String firstItemTotalPrice, String secondItemTotalPrice) {
        driver.verifyThat().element(firstProductTotal).text().isEqualTo(firstItemTotalPrice).perform();
        driver.verifyThat().element(secondProductTotal).text().isEqualTo(secondItemTotalPrice).perform();
        return this;
    }
}
