package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBarPage;
import com.gizasystems.automationexercise.pages.TestCasePage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Navigation Verification")
@Story("Access Test Cases Page")
public class TestCaseTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512358")
    @Test(description = "Verify navigation to the Test Cases page from the homepage")
    @Description("Given I open Automation Exercise home, When I click on the 'Test Cases' button, Then I verify that the user is navigated to the test cases page successfully")
    public void verifyTestCasePage() {
        new NavigationBarPage(driver)
                .clickOnTestCase();
        new TestCasePage(driver)
                .assertTestCasePageLoaded(testData.getTestData("TestCasePageTitle"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("TestCaseTestsTestData.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
