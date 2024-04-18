package com.MyBank.pageObjects.Base;

import com.MyBank.pageObjects.pages.DealsPage;
import com.MyBank.pageObjects.pages.GroceryPage;
import com.MyBank.pageObjects.pages.LandingPage;
import com.MyBank.pageObjects.pages.ServicesPage;
import org.openqa.selenium.WebDriver;

public class BankApplication extends TestBase{

    public LandingPage landingPage;
    public GroceryPage groceryPage;
    public DealsPage dealsPage;
    public ServicesPage servicesPage;

    public BankApplication(WebDriver driver) {
        super(driver);
    }

    public LandingPage getLandingPage(){
        if(landingPage==null){
            landingPage=new LandingPage(driver);
        }return landingPage;
    }
    public GroceryPage getGroceryPage(){
        if(groceryPage==null){
            groceryPage=new GroceryPage(driver);
        }return groceryPage;
    }
    public DealsPage getDealsPage(){
        if(dealsPage==null){
            dealsPage=new DealsPage(driver);
        }return dealsPage;
    }
    public ServicesPage getServicesPage(){
        if(servicesPage==null){
            servicesPage=new ServicesPage(driver);
        }return servicesPage;
    }
}
