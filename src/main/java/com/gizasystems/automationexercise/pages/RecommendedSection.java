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
    private final By productAddedToCartMessage = By.cssSelector("div.modal-content > div > h4");
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");

    // Constructor
    public RecommendedSection(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }
    @Step("Navigate Recommended Section Tab")
    public RecommendedSection openRecommendedSection(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.getDriver();
        WebElement webElement = driver.getDriver().findElement(recommendedItems_div);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",webElement);
        Actions actions = new Actions(driver.getDriver());
        actions.pause(2000).moveToElement(webElement).perform();
        return this;
    }
    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Add Recommended Product To Cart")
    public RecommendedSection addToCart(String productName){
        String productNameValue = "//div[@class='recommended_items']//child::p[text()='" + productName + "']//parent::div//a";
        driver.element().click(By.xpath(productNameValue));
        return this;
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
