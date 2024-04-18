package com.MyBank.testCases.FunctionalTest;

import com.MyBank.pageObjects.Base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPagesUITest extends TestBase {

    @Test(enabled = true,description = "validate landing page title")
    public void validateTitle(){
        getBankApplication().getLandingPage().getLandingPageTitle();
    }

    @Test(enabled = false ,description = "this test will verify title")
    public void validateTitle_2(){
        System.out.println("hi");
    }
}
