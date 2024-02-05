package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By proceedToCheckoutBtn = By.cssSelector(".btn.btn-default.check_out");
    private final By productName = By.xpath("//td[@class='cart_description']//h4");
    private final By viewCartBtn = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");

    // Constructor
    public CartPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Open Cart Page")
    public CartPage openCart(){
        driver.element().click(viewCartBtn);
        return this;
    }
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Cart Page is Loaded")
    public CartPage verifyCartPageIsLoaded(){
        driver.verifyThat().element(proceedToCheckoutBtn).isVisible().perform();
        return this;
    }
    @Step("Validate on Product Added To Cart Page")
    public CartPage verifyProductAddedToCart(String addedProductName){
        driver.assertThat().element(productName).text().contains(addedProductName).perform();
        return this;
    }
}
