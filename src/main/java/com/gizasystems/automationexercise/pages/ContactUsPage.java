package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {
    // Variables
    private SHAFT.GUI.WebDriver driver;

    // Locators
    private final By contactUsForm_div = By.xpath("//div[@class='contact-form']");
    private final By contactName_input = By.name("name");
    private final By email_input = By.name("email");
    private final By subject_input = By.name("subject");
    private final By message_input=By.id("message");
    private final By submitBtn_button = By.name("submit");
    private final By uploadBtn_button = By.xpath("//input[@type='file']");
    private final By successMsg_div= By.xpath("//div[@class='status alert alert-success']");

    // Constructor
    public ContactUsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\
    @Step("Enter ContactUs Information")
    public ContactUsPage enterContactUsInformation(String contactNameTxt, String emailTxt , String subjectTxt, String messageTxt, String filePath) {
        driver.element().type(contactName_input, contactNameTxt);
        driver.element().type(email_input,emailTxt);
        driver.element().type(subject_input,subjectTxt);
        driver.element().typeFileLocationForUpload(uploadBtn_button,filePath);
        driver.element().type(message_input,messageTxt);
        return this;
    }

    @Step("Click On Submit Button")
    public ContactUsPage clickOnSubmitButton() {
        driver.element().click(submitBtn_button);
        driver.alert().acceptAlert();
        return this;
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\
    @Step("Validate On Contact Us Page")
    public ContactUsPage validateOnContactUsPage() {
        driver.verifyThat().element(contactUsForm_div).exists().perform();
        return this;
    }

    @Step("Validate On Information Submitted")
    public ContactUsPage validateOnContactInfoSubmitted(String expectedResult) {
        driver.verifyThat().element(successMsg_div).text().isEqualTo(expectedResult).perform();
        return this;
    }
}
