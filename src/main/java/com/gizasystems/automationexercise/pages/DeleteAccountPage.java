package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeleteAccountPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By accountDeleted_b = By.cssSelector("h2[data-qa='account-deleted'] > b");
    private final By continueButton =By.cssSelector(".btn.btn-primary");

    // Constructor
    public DeleteAccountPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Click On Continue Button")
    public DeleteAccountPage clickOnContinueButton() {
        driver.element().click(continueButton);
        return this;
    }
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate Account Deleted")
    public DeleteAccountPage validateAccountDeleted(String expectedMessage) {
        driver.verifyThat().element(accountDeleted_b).text().equalsIgnoringCaseSensitivity(expectedMessage).perform();
        return this;
    }

}
