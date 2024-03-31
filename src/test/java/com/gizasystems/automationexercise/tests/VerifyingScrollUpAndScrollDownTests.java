package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.FooterPage;
import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Navigation Verification")
@Story("Verify Scroll Up and Scroll Down functionality")
public class VerifyingScrollUpAndScrollDownTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    // Test Cases
    @TmsLink("55512524")
    @Test(description = "Verify scrolling functionality on the homepage with the arrow button")
    @Description("Given I open Automation Exercise home, When I scroll down the page to the bottom, Then I verify that 'SUBSCRIPTION' is visible on the screen, When I click on the arrow at the bottom right side to move upward, Then I verify that the page is scrolled up, And I verify that 'Full-Fledged practice website for Automation Engineers' text is visible on the screen")
    public void verifyScrollingUpUsingArrowButtonInHomePage() {
        new FooterPage(driver)
                .scrollToBottomPage()
                .verifySubscriptionText(testData.getTestData("subText"));
        new HomePage(driver)
                .clickOnScrollUpArrow()
                .validateOnVisibilityOfCarouselSlideText(testData.getTestData("slideHeadingText"));
    }

    @TmsLink("55512533")
    @Test(description = "Verify scrolling functionality on the homepage without the arrow button")
    @Description("Given I open Automation Exercise home, When I scroll down the page to the bottom, Then I verify that 'SUBSCRIPTION' is visible on the screen, When I scroll up the page to the top, Then I verify that the page is scrolled up, And I verify that 'Full-Fledged practice website for Automation Engineers' text is visible on the screen")
    public void verifyScrollingUpWithoutArrowButtonInHomePage() {
        new FooterPage(driver)
                .scrollToBottomPage()
                .verifySubscriptionText(testData.getTestData("subText"));
        new HomePage(driver)
                .scrollToTopPage()
                .validateOnVisibilityOfCarouselSlideText(testData.getTestData("slideHeadingText"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("VerifyingScrollUpAndScrollDownTestsTestData.json");
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
