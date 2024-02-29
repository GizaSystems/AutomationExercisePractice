package com.gizasystems.automationexercise.tests.test;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Brand Module")
@Story("Brand Products")
public class BrandProductsTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "Verify that Brand Products are displayed")
    public void verifyBrandPageDisplayed() {
        new BrandProductsPage(driver)
                .clickOnProducts()
                .verifyBrandsVisible()
                .clickOnBrandName()
                .verifyBrandPageVisible(testData.getTestData("firstBrand"))
                .verifyProductsVisible()
                .clickOnSecondBrandName()
                .verifysecondBrandPageVisible(testData.getTestData("secondBrand"))
                .verifyProductsOfSecondBrandVisible();
    }

    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/BrandPage.json");
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
