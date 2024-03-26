package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Footer {
    //Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By subscriptionText = By.xpath("//div[@class='single-widget']/h2");
    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By subscribeBtn = By.id("subscribe");
    private final By successMsg = By.id("success-subscribe");

    // Constructor
    public Footer(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Enter Subscription Email")
    public Footer enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionEmail, email);
        return this;
    }

    @Step("Click on Subscribe Button")
    public Footer clickOnSubscribeButton() {
        driver.element().click(subscribeBtn);
        return this;
    }

    @Step("Scroll to bottom page")
    public Footer scrollToBottomPage() {
        driver.element().scrollToElement(subscriptionText);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Subscription Text is Visible")
    public Footer verifySubscriptionText(String Text) {
        driver.verifyThat().element(subscriptionText).text().equalsIgnoringCaseSensitivity(Text).perform();
        return this;
    }

    @Step("Validate on Success Message of Subscription Email")
    public Footer validateOnSuccessMessageOfSubscriptionEmail(String successMessage) {
        driver.verifyThat().element(successMsg).text().equalsIgnoringCaseSensitivity(successMessage);
        return this;
    }
}
