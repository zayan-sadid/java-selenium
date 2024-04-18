package com.MyBank.pageObjects.pages;

import com.MyBank.pageObjects.Base.TestBase;
import com.MyBank.pageObjects.reports.ExtentTestManager;
import com.MyBank.pageObjects.utils.TestUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class LandingPage extends TestBase {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    TestUtils utils=new TestUtils(driver);
    public void getLandingPageTitle(){
        ExtentTestManager.getTest().log(Status.INFO,"Title test");
        utils.validatePageTitle("Welcome to Costco Wholesal");
    }
}
