package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RecommendedSection {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By productAddedToCartMessage_div = By.cssSelector("div.modal-content > div > h4");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");

    // Constructor
    public RecommendedSection(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //Workaround not to throw moveTargetOutOfBound Exception
    @Step("Navigate Recommended Section Tab")
    public RecommendedSection openRecommendedSection(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.getDriver();
        WebElement webElement = driver.getDriver().findElement(recommendedItems_div);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",webElement);
        return this;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Add Recommended Product To Cart")
    public RecommendedSection addToCart(String productName){
        driver.element().click(addToCartLocator(productName));
        return this;
    }

    private By addToCartLocator(String productName){
        return By.xpath("//div[@class='recommended_items']//child::p[text()='" + productName + "']//parent::div//a");
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of Recommended Section")
    public RecommendedSection verifyRecommendedSectionVisibility(){
        driver.assertThat().element(recommendedItems_div).isVisible().perform();
        return this;
    }

    @Step("Validate on Visibility of Successful Add To Cart Message")
    public RecommendedSection verifyProductAddedToCart(String message){
        driver.assertThat().element(productAddedToCartMessage_div).textTrimmed().isEqualTo(message).perform();
        return this;
    }
}
