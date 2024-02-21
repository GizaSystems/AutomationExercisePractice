package com.gizasystems.automationexercise.utils;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

@SuppressWarnings("InstantiationOfUtilityClass")
public class GoogleAlert {

    /**
     * This method is used to handle GoogleVignette Ads.
     * Note that you must provide a valid locator for the method to work, Target the element that will trigger the GoogleVignette Ads.
     *
     * @param driver
     * @param locator
     * @return instance of GoogleAlert for further chaining usage.
     */
    public static GoogleAlert dismissAlert(SHAFT.GUI.WebDriver driver, By locator) {
        driver.browser().refreshCurrentPage();
        try {
            driver.element().click(locator);
        } catch (Throwable throwableVar) {
            //empty (Expected to be caught in case if the Ad didn't get triggered)
            //Must be a throwable (Firefox case)
        }
        return new GoogleAlert();
    }

}
