package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LogoutUserPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By logoutBtn_button = By.xpath("//a[contains(text(),\" Logout\")]");

    // Constructor
    public LogoutUserPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate To Home Page")
    public LogoutUserPage logOut() {
        driver.element().click(logoutBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

}
