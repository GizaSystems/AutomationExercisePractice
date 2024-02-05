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
    public By getCategoryTitleLocator(String categoryTitle ){
        return  By.xpath("//h2[text()='"+ categoryTitle +"']");
    }

    private final By subscriptionText = By.xpath("//*[@id='footer']/div[1]/div/div/div[2]/div/h2");
    private final By subscriptionEmail = By.xpath("//*[@id=\"susbscribe_email\"]");
    private final By subscribeButton = By.xpath("//*[@id=\"subscribe\"]/i");
    private  final By successMsg = By.xpath("");

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

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Visibility of the Home Page")
    public HomePage validateOnVisibilityOfHomePage() {
        driver.verifyThat().element(featuredItems_div).exists().perform();
        driver.verifyThat().element(recommendedItems_div).exists().perform();
        return this;
    }
    @Step("Verify Subscription is on home page")
    public HomePage  VerifySubscriptionInHomePage() {
        driver.verifyThat().element(subscriptionText).text().isEqualTo("Subscription").perform();
        return this;
    }

    @Step("Enter Subscription Email")
    public HomePage enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionEmail,email);
        return this;
    }
    @Step("Click on Subscribe Button")
    public HomePage clickOnSubscribeButton() {
        driver.element().click(subscribeButton);
        return this;
    }
//
//    @Step("Validate on Success Message of Subscription Email")
//    public HomePage ValidateOnSuccessMessageOfSubscriptionEmail() {
//        driver.verifyThat().element(successMsg).text().isEqualTo("You have been successfully subscribed!");
//        return this;
//    }


    @Step("Validate on Visibility of the Category Title")
    public HomePage validateOnVisibilityOfCategoryTitle(String categoryTitle) {
        driver.verifyThat().element(getCategoryTitleLocator(categoryTitle)).exists().perform();
        return this;
    }

}
