package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TestCasePage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private  final By testCasePageTitle_h2 = By.cssSelector("h2 > b");

    // Constructor
    public TestCasePage(SHAFT.GUI.WebDriver driver)
    {
        this.driver= driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Test Cases Page Is Loaded")
    public TestCasePage assertTestCasePageLoaded(String testCasePageTitle)
    {
        driver.verifyThat().element(testCasePageTitle_h2).textTrimmed().equalsIgnoringCaseSensitivity(testCasePageTitle).perform();
        return this;
    }
}
