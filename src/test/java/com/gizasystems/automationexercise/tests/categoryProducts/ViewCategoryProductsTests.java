package com.gizasystems.automationexercise.tests.categoryProducts;

import com.gizasystems.automationexercise.pages.*;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Category Products")
@Story("View Category Products")
public class ViewCategoryProductsTests {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    @Test(description = "View Category Products")
    @Description("Given that I want to view Woman Category Products, When I click on Woman Category, Then I should be navigated to that category page")
    public void viewCategoryProductsTestGui() {
        new CategoriesBar(driver).clickOnCategoryLink(testData.getTestData("MainCategory.womanCategory")).clickOnSubCategoryLink(testData.getTestData("MainCategory.womanCategory"), testData.getTestData("SubCategories.womanSubCategory"));
        new HomePage(driver).validateOnVisibilityOfCategoryTitle(testData.getTestData("womanCategoryTitle"));
        new CategoriesBar(driver).clickOnCategoryLink(testData.getTestData("MainCategory.manCategory")).clickOnSubCategoryLink(testData.getTestData("MainCategory.manCategory"), testData.getTestData("SubCategories.manSubCategory"));
        new HomePage(driver).validateOnVisibilityOfCategoryTitle(testData.getTestData("manCategoryTitle"));
    }

    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\
    @BeforeClass
    public void beforeClass() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/CategoryProducts.json");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).navigate().validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}