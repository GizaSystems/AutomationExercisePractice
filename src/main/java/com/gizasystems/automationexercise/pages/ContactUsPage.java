package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {

    private SHAFT.GUI.WebDriver driver;


    private final By contactUsForm = By.xpath("//div[@class='contact-form']");
    private final By contact_name = By.name("name");
    private final By email = By.name("email");
    private final By subject = By.name("subject");
    private final By message=By.id("message");
    private final By submitBtn = By.name("submit");
    private final By uploadBtn = By.xpath("//input[@type='file']");
    private final By success_msg= By.xpath("//div[@class='status alert alert-success']");

    public ContactUsPage(SHAFT.GUI.WebDriver driver) {this.driver = driver;}

    @Step("Validate on Contact us Page")
    public ContactUsPage validateOnContactUsPage() {
        driver.verifyThat().element(contactUsForm).exists().perform();
        return this;
    }

    @Step("Enter ContactUs Information")
    public ContactUsPage enterContactUsInformation(String contactNameTxt, String emailTxt , String subjectTxt, String messageTxt, String filePath) {
        driver.element().type(contact_name, contactNameTxt);
        driver.element().type(email,emailTxt);
        driver.element().type(subject,subjectTxt);
        driver.element().typeFileLocationForUpload(uploadBtn,filePath);
        driver.element().type(message,messageTxt);
        return this;
    }

    @Step("Click on submit Button")
    public ContactUsPage clickOnSubmitButton() {
        driver.element().click(submitBtn);
        driver.alert().acceptAlert();
        return this;
    }

    @Step("Validate On Information Submitted")
    public ContactUsPage validateOnContactInfoSubmitted(String expectedResult) {
        driver.assertThat().element(success_msg).text().isEqualTo(expectedResult).perform();
        return this;
    }
}
