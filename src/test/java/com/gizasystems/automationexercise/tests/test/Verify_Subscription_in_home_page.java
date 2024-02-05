package com.gizasystems.automationexercise.tests.test;


import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import  org.testng.annotations.BeforeClass;

@Epic("Automation Exercise")
@Feature("Home page verification")
@Story("Verify Subscription in home page")


public class Verify_Subscription_in_home_page {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testUser;
    private  String timeStamp = String.valueOf(System.currentTimeMillis());
    @BeforeClass
    public void beforeClass() {
        testUser = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/RegisterUser.json");
    }
    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
    }

    @Test(description = "Verify Sunscription is on Home Page")
    public void VerifySubscriptionInHomePage ()
        {
            driver = new SHAFT.GUI.WebDriver();

            new HomePage(driver)
                .VerifySubscriptionInHomePage()
                .enterSubscriptionEmail(testUser.getTestData("UserMail.GuiTimeStamp") + timeStamp + "@gizasystems.com")
                .clickOnSubscribeButton();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }


}
