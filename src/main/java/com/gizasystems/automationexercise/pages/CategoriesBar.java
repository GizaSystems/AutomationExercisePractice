package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CategoriesBar {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    public By getCategoryLinkLocator(String categoryType) {
        return By.xpath("//a[@href='#" + categoryType + "']");
    }

    public By getSubCategoryLinkLocator(String categoryType, String subCategoryType) {
        return By.xpath("//div[@id='" + categoryType + "']//a[contains(text(),'" + subCategoryType + "')]");
    }

    // Constructor
    public CategoriesBar(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("click on Category Link")
    public CategoriesBar clickOnCategoryLink(String categoryType) {
        driver.element().click(getCategoryLinkLocator(categoryType));
        return this;
    }

    @Step("click on SubCategory Link")
    public CategoriesBar clickOnSubCategoryLink(String categoryType, String subCategoryType) {
        driver.element().click(getSubCategoryLinkLocator(categoryType, subCategoryType));
        return this;
    }

}
