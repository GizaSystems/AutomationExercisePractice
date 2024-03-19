package com.gizasystems.automationexercise.tests.register;

import com.gizasystems.automationexercise.apis.Apis;
import com.gizasystems.automationexercise.pages.HomePage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class RegisterWhileCheckoutTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.API api;
    private SHAFT.TestData.JSON testData;
    private String timeStamp;


    @BeforeClass
    public void beforeClass() {

    }

    @BeforeMethod
    public void beforeMethod() {
        api = new SHAFT.API(Apis.ApisBaseUrl);
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver)
                .navigate()
                .validateOnVisibilityOfHomePage();
         timeStamp = String.valueOf(System.currentTimeMillis());
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
