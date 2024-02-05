package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
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
    private final By login_div= By.xpath("//div[@class='login-form']");
    private final By login_h2 = By.xpath("//div[@class='login-form']//h2");
    private final By loginEmail_input = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword_input= By.xpath("//input[@data-qa='login-password']");
    private final By login_button = By.xpath("//button[@data-qa='login-button']");
    // Constructor
    public SignupLoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    public void navigate() {
        driver.browser().navigateToURL(url);
    }

    @Step("New User Sighup with Name: {name} and Email: {email}")
    public SignupLoginPage newUserSignup(String name, String email) {
        driver.element()
                .type(signupUserName_input, name)
                .type(signupEmail_input, email)
                .click(signup_button);
        return this;
    }
    @Step("User Login with Correct Email: {email} and Password: {password}")
    public SignupLoginPage registeredUserLogin(String email, String password){
        driver.element()
                .type(loginEmail_input, email)
                .type(loginPassword_input, password)
                .click(login_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate on Signup Visibility")
    public SignupLoginPage validateOnSignUpVisibility(String expectedResult) {
        driver.verifyThat().element(signup_div).exists().perform();
        driver.verifyThat().element(signup_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }
    @Step("Validate on Login Visibility")
    public SignupLoginPage validateOnLoginVisibility(String expectedResult){
        driver.verifyThat().element(login_div).exists().perform();
        driver.verifyThat().element(login_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }

}
