package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeleteAccountPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By accountDeleted_b = By.cssSelector("h2[data-qa='account-deleted'] > b");
    private final By continueBtn_div=By.cssSelector("a[data-qa='continue-button']");

    // Constructor
    public DeleteAccountPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Click on Continue Button")
    public DeleteAccountPage clickOnContinueBtn(){
        driver.element().click(continueBtn_div);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate Account Deleted")
    public DeleteAccountPage validateAccountDeleted(String expectedMessage) {
        driver.verifyThat().element(accountDeleted_b).text().equalsIgnoringCaseSensitivity(expectedMessage).perform();
        return this;
    }

}
