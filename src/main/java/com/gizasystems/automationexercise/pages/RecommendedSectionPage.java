package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RecommendedSectionPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By productAddedToCartMessage_div = By.cssSelector("div.modal-content > div > h4");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");
    private By addToCart_link(String productName){
        return By.xpath("//div[@class='recommended_items']//child::p[text()='" + productName + "']//parent::div//a");
    }

    // Constructor
    public RecommendedSectionPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    //Workaround not to throw moveTargetOutOfBound Exception
    @Step("Navigate Recommended Section Tab")
    public RecommendedSectionPage openRecommendedSection(){
        driver.element().hover(recommendedItems_div);
        return this;
    }

    @Step("Add Recommended Product To Cart")
    public RecommendedSectionPage addToCart(String productName){
        driver.element().click(addToCart_link(productName));
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Visibility Of Recommended Section")
    public RecommendedSectionPage verifyRecommendedSectionVisibility(){
        driver.verifyThat().element(recommendedItems_div).isVisible().perform();
        return this;
    }

    @Step("Validate On Visibility Of Successful Add To Cart Message")
    public RecommendedSectionPage verifyProductAddedToCart(String message){
        driver.verifyThat().element(productAddedToCartMessage_div).textTrimmed().isEqualTo(message).perform();
        return this;
    }
}
