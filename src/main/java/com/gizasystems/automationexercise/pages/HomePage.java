package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private String url = System.getProperty("baseUrl");

    // Locators
    private final By featuredItems_div = By.cssSelector("div.features_items");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private final By viewCartBtn = By.xpath("//div[contains(@class,'confirm')]//a[@href='/view_cart']");
    private final By productAddedToCartMessage = By.cssSelector("div.modal-content > div > h4");
    // Constructor
    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public HomePage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }
    @Step("Navigate Recommended Section Tab")
    public HomePage openRecommendedSection(){
        driver.element().hover(recommendedItems_div);
        return this;
    }
    @Step("Add Recommended Product To Cart")
    public HomePage addToCart(String productName){
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
    @Step("Validate on Visibility of the Home Page")
    public HomePage validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }
    @Step("Validate on Visibility of Recommended Section")
    public HomePage isRecommendedSectionVisible(){
        driver.verifyThat().element(recommendedItems_div).isVisible().perform();
        return this;
    }
    @Step("Validate on Visibility of Successful Add To Cart Message")
    public HomePage isProductAddedSuccessfullyToCart(String message){
        driver.verifyThat().element(productAddedToCartMessage).textTrimmed().isEqualTo(message).perform();
        return this;
    }

}
