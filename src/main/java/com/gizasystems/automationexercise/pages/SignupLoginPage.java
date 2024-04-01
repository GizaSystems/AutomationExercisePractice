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
    private final By signupBtn_button = By.xpath("//button[@data-qa='signup-button']");
    private final By login_div= By.xpath("//div[@class='login-form']");
    private final By login_h2 = By.xpath("//div[@class='login-form']//h2");
    private final By loginEmail_input = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword_input= By.xpath("//input[@data-qa='login-password']");
    private final By loginBtn_button = By.xpath("//button[@data-qa='login-button']");
    private final By signupExistingEmail_text = By.xpath("//p[@style='color: red;']");

    // Constructor
    public SignupLoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Navigate To Login Page")
    public void navigate() {
        driver.browser().navigateToURL(url);
    }

    @Step("New User Sighup With Name: {name} And Email: {email}")
    public SignupLoginPage newUserSignup(String name, String email) {
        driver.element()
                .type(signupUserName_input, name)
                .type(signupEmail_input, email)
                .click(signupBtn_button);
        return this;
    }

    @Step("User Login With Correct Email: {email} And Password: {password}")
    public SignupLoginPage registeredUserLogin(String email, String password){
        driver.element()
                .type(loginEmail_input, email)
                .type(loginPassword_input, password)
                .click(loginBtn_button);
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Signup Visibility")
    public SignupLoginPage validateOnSignUpVisibility(String expectedResult) {
        driver.verifyThat().element(signup_div).exists().perform();
        driver.verifyThat().element(signup_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }

    @Step("Validate On Login Visibility")
    public SignupLoginPage validateOnLoginVisibility(String expectedResult){
        driver.verifyThat().element(login_div).exists().perform();
        driver.verifyThat().element(login_h2).text().isEqualTo(expectedResult).perform();
        return this;
    }

    @Step("Validate On Existing Email Error Message Visibility")
    public void validateOnErrorMessageVisibilityOfExistingEmail() {
        driver.verifyThat().element(signupExistingEmail_text).text().isEqualTo("Email Address already exist!");
    }
}
