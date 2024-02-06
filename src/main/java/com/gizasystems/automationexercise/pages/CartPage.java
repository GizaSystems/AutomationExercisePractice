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
}
