package com.gizasystems.automationexercise.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;

public class LogoutUser{

    //Variables
    private SHAFT.GUI.WebDriver driver;
    private final String url = System.getProperty("baseUrl");



    //Locators


    private final By Logout_button = By.xpath("//header//ul//li[4]/a");




    //Constructors
    public LogoutUser(SHAFT.GUI.WebDriver driver){
        this.driver = driver ;
    }



    //////////////////////////Actions\\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to Home Page")
    public LogoutUser logOut() {
        driver.element().click(Logout_button);
        return this;
    }



    ///////////////////////Validations\\\\\\\\\\\\\\\\\\\\




}
