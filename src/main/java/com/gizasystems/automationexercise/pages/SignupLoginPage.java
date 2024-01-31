package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class SignupLoginPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    private String url = System.getProperty("baseUrl") + "/login";

    // Locators
    private final By signup_div = By.xpath("//div[@class='signup-form']");
    private final By signup_h2 = By.xpath("//div[@class='signup-form']//h2");
    private final By signupUserName_input = By.xpath("//input[@data-qa='signup-name']");
    private final By signupEmail_input = By.xpath("//input[@data-qa='signup-email']");
    private final By signup_button = By.xpath("//button[@data-qa='signup-button']");

    // Constructor
    public SignupLoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    public void navigate() {
        driver.browser().navigateToURL(url);
    }

    public SignupLoginPage newUserSignup(String name, String email) {
        driver.element()
                .type(signupUserName_input, name)
                .type(signupEmail_input, email)
                .click(signup_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    public SignupLoginPage validateOnSignUpVisibility(String expectedResult) {
        driver.verifyThat().element(signup_div).exists().perform();
        driver.verifyThat().element(signup_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }

}
