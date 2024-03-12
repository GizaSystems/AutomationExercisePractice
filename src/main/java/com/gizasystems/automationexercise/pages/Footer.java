package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Footer {
    //Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By SubscriptionText = By.xpath("//div[@class='single-widget']/h2");
    private final By SubscriptionEmail = By.id("susbscribe_email");
    private final By Subscribe_Btn = By.id("subscribe");
    private  final By SuccessMsg = By.id("success-subscribe");

    // Constructor

    public Footer(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    @Step("Enter Subscription Email")
    public Footer enterSubscriptionEmail(String email) {
        driver.element().type(SubscriptionEmail,email);
        return this;
    }

    @Step("Click on Subscribe Button")
    public Footer clickOnSubscribeButton() {
        driver.element().click(Subscribe_Btn);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Verify Subscription Text is Visible")
    public Footer verifySubscriptionText(String Text) {
        driver.verifyThat().element(SubscriptionText).text().equalsIgnoringCaseSensitivity(Text).perform();
        return this;
    }
    @Step("Validate on Success Message of Subscription Email")
    public Footer validateOnSuccessMessageOfSubscriptionEmail(String successMessage) {
        driver.verifyThat().element(SuccessMsg).text().equalsIgnoringCaseSensitivity(successMessage);
        return this;
    }
}
