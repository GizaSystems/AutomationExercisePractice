package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TestCasePage {

    private SHAFT.GUI.WebDriver driver;

    public TestCasePage(SHAFT.GUI.WebDriver driver)
    {
        this.driver= driver;
    }

    private  final By TestCasePageTitle = By.xpath("//h2//b");

    @Step
    public TestCasePage AssertTestCasePageLoaded()
    {
        driver.assertThat().element(TestCasePageTitle).textTrimmed().equalsIgnoringCaseSensitivity("Test Cases").perform();
        return this;
    }

}