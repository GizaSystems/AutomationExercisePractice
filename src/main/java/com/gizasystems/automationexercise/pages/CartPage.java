package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private SHAFT.GUI.WebDriver driver;

    private String url = System.getProperty("baseUrl") + "/view_cart";

    By subscriptionTxt = By.tagName("h2");

    private final By subscription_input = By.id("susbscribe_email");
    private final By subscribeBtn = By.id("subscribe");

    private final By successAlert = By.xpath("//div[@class='alert-success alert']");

    public CartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public CartPage navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }
   @Step("Enter Subscription Email")
    public CartPage enterSubscriptionEmail(String email) {
        driver.element().type(subscription_input,email);
        return this;
    }
    @Step("Click on Subscribe Button")
    public CartPage clickOnSubscribeButton() {
        driver.element().click(subscribeBtn);
        return this;
    }
    @Step("Validate on Visibility of the subscription Text")
    public CartPage validateOnVisibilityOfSubscriptionText() {
        driver.verifyThat().element(subscriptionTxt).text().isEqualTo("SUBSCRIPTION").perform();
        return this;
    }
    @Step("Validate on Success Message of Subscription Email")
    public CartPage ValidateOnSuccessMessageOfSubscriptionEmail(String expectedText) {
        driver.verifyThat().element(successAlert).text().isEqualTo(expectedText).perform();
        return this;
    }

}
