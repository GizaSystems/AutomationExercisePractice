package com.gizasystems.automationexercise.tests;

import com.gizasystems.automationexercise.pages.Footer;
import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyingScrollUpWithoutArrowButtonAndScrollDownTests {
    // Variables
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testUser;

    // Test Cases
    @Test(description = "Verify scrolling functionality on the homepage without the arrow button")
    @Description("Given I open Automation Exercise home, When I scroll down the page to the bottom, Then I verify that 'SUBSCRIPTION' is visible on the screen, When I scroll up the page to the top, Then I verify that the page is scrolled up, And I verify that 'Full-Fledged practice website for Automation Engineers' text is visible on the screen")
    @TmsLink("55512533")
    public void verifyScrollingUpWithoutArrowButtonInHomePage() {
        new Footer(driver)
                .scrollToBottomPage()
                .verifySubscriptionText(testUser.getTestData("subText"));
        new HomePage(driver)
                .scrollToTopPage()
                .validateOnVisibilityOfCarouselSlideText(testUser.getTestData("slideHeadingText"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testUser = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/ScrollingTestData.json");
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
