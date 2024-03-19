package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeleteAccountPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By accountDeleted_b = By.xpath("//h2[@data-qa='account-deleted']");

    // Constructor
    public DeleteAccountPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate Account Deleted")
    public DeleteAccountPage validateAccountDeleted(String expectedMessage) {
        driver.verifyThat().element(accountDeleted_b).text().equalsIgnoringCaseSensitivity(expectedMessage).perform();
        return this;
    }

}
