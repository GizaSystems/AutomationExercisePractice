package com.gizasystems.automationexercise.tests.test;
import com.gizasystems.automationexercise.pages.HomePage;
import com.gizasystems.automationexercise.pages.NavigationBar;
import com.gizasystems.automationexercise.pages.TestCasePage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCaseTest {

    private SHAFT.GUI.WebDriver driver;






    @Test(description = "Verify test cases page")

    public void VerifyTestCasePage() {

        new NavigationBar(driver).ClickOnTestCase();
        new TestCasePage(driver).AssertTestCasePageLoaded();

    }



    //////////////////// Configurations \\\\\\\\\\\\\\\\\\\\


    @BeforeMethod
    public void beforeMethod() {

        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).navigate().validateOnVisibilityOfHomePage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
