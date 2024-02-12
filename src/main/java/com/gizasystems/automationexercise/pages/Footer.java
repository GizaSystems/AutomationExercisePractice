package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Footer {
    private SHAFT.GUI.WebDriver driver;
    private final By recommendedItems_div = By.cssSelector("div.recommended_items");

    private final By subscriptionText = By.xpath("//div[@class='single-widget']/h2");
    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private  final By successMsg = By.id("success-subscribe");

    public Footer(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    @Step("Verify Subscription Text is Visible")
    public Footer  VerifySubscriptionText(String Text) {
        driver.verifyThat().element(subscriptionText).text().isEqualTo(Text).perform();
        return this;
    }
    @Step("Enter Subscription Email")
    public Footer enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionEmail,email);
        return this;
    }
    @Step("Click on Subscribe Button")
    public Footer clickOnSubscribeButton() {
        driver.element().click(subscribeButton);
        return this;
    }
    @Step("Validate on Success Message of Subscription Email")
    public Footer ValidateOnSuccessMessageOfSubscriptionEmail(String successMessage) {
        driver.verifyThat().element(successMsg).text().isEqualTo(successMessage);
        return this;
    }
}
