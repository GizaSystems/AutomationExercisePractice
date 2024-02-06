package com.gizasystems.automationexercise.utils;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

@SuppressWarnings("InstantiationOfUtilityClass")
public class GoogleAlert {

    private static final By headerElement_header = By.tagName("header");

    //To Handle Google_Vignette Alerts (double-clicks on the header Element to trigger the action to dismiss the alert)
    public static GoogleAlert dismissAlert(SHAFT.GUI.WebDriver driver){
        driver.element().doubleClick(headerElement_header);
        return new GoogleAlert();
    }

}
