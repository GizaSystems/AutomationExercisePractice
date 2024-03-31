package com.gizasystems.automationexercise.pages;

import com.gizasystems.automationexercise.utils.GoogleAlert;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CategoriesBarPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    public By getCategoryLinkLocator_link(String categoryType) {
        return By.xpath("//a[@href='#" + categoryType + "']");
    }

    public By getSubCategoryLinkLocator_link(String categoryType, String subCategoryType) {
        return By.xpath("//div[@id='" + categoryType + "']//a[contains(text(),'" + subCategoryType + "')]");
    }

    // Constructor
    public CategoriesBarPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("click On Category Link")
    public CategoriesBarPage clickOnCategoryLink(String categoryType) {
        driver.element().click(getCategoryLinkLocator_link(categoryType));
        GoogleAlert.dismissAlert(driver,getCategoryLinkLocator_link(categoryType));
        return this;
    }

    @Step("click On SubCategory Link")
    public CategoriesBarPage clickOnSubCategoryLink(String categoryType, String subCategoryType) {
        driver.element().click(getSubCategoryLinkLocator_link(categoryType, subCategoryType));
        GoogleAlert.dismissAlert(driver,getSubCategoryLinkLocator_link(categoryType, subCategoryType));
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

}
