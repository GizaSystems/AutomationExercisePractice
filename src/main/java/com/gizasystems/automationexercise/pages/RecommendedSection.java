package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RecommendedSection {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By productAddedToCartMessage = By.cssSelector("div.modal-content > div > h4");
    private final By viewCartBtn = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");

    // Constructor
    public RecommendedSection(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Add Recommended Product To Cart")
    public RecommendedSection addToCart(String productName){
        String productNameValue = "//div[@class='recommended_items']//child::p[text()='" + productName + "']//parent::div//a";
        driver.element().click(By.xpath(productNameValue));
        return this;
    }
    @Step("Open Cart Page")
    public CartPage openCart(){
        driver.element().click(viewCartBtn);
        return new CartPage(driver);
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of Recommended Section")
    public RecommendedSection isRecommendedSectionVisible(){
        driver.verifyThat().element(recommendedItems_div).isVisible().perform();
        return this;
    }
    @Step("Validate on Visibility of Successful Add To Cart Message")
    public RecommendedSection isProductAddedSuccessfullyToCart(String message){
        driver.verifyThat().element(productAddedToCartMessage).textTrimmed().isEqualTo(message).perform();
        return this;
    }
}
