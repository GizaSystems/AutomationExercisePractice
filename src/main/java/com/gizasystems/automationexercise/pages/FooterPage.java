package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FooterPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By subscriptionText_h2 = By.xpath("//div[@class='single-widget']/h2");
    private final By subscriptionEmail_input = By.id("susbscribe_email");
    private final By subscribeBtn_button = By.id("subscribe");
    private final By successMsg_div = By.id("success-subscribe");

    // Constructor
    public FooterPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Enter Subscription Email")
    public FooterPage enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionEmail_input, email);
        return this;
    }

    @Step("Click On Subscribe Button")
    public FooterPage clickOnSubscribeButton() {
        driver.element().click(subscribeBtn_button);
        return this;
    }

    @Step("Scroll To Bottom Page")
    public FooterPage scrollToBottomPage() {
        driver.element().scrollToElement(subscriptionText_h2);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Subscription Text Is Visible")
    public FooterPage verifySubscriptionText(String Text) {
        driver.verifyThat().element(subscriptionText_h2).text().equalsIgnoringCaseSensitivity(Text).perform();
        return this;
    }

    @Step("Validate On Success Message Of Subscription Email")
    public FooterPage validateOnSuccessMessageOfSubscriptionEmail(String successMessage) {
        driver.verifyThat().element(successMsg_div).text().equalsIgnoringCaseSensitivity(successMessage);
        return this;
    }
}
