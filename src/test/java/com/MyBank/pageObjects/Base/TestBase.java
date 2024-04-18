package com.MyBank.pageObjects.Base;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

public class TestBase  {

    public  WebDriver driver;

    public TestBase() {
    }

    public TestBase(WebDriver driver) {
      this.driver=driver;
    }

    public BankApplication bankApplication;
    public BankApplication getBankApplication(){
        if (bankApplication==null){
            bankApplication=new BankApplication(driver);
        }
        return bankApplication;
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.navigate().to("https://www.costco.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(3000);
        getBankApplication();
    }

    @AfterClass(alwaysRun = true)
    public void close(){
        if(bankApplication!=null){
            bankApplication=null;
        }
        if(driver!=null){
            driver.close();
            driver.quit();
        }

    }

    public  WebDriver getDriver(){
        return driver;
    }


    public String getOSName() {
        String osName=null;
        return osName;
    }

    public String getOSVersion() {
        String osVersion=null;
        return osVersion;
    }

    /*@AfterMethod(alwaysRun = true)
    public void captureScreenshot(ITestResult result){

        String date=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String path="Error"+"_"+date;
        //Take the Screenshot Only, If the Test is failed.
        // Change the condition , If the screenshot needs to be taken for other status as well
        if(ITestResult.FAILURE==result.getStatus()){
            ExtentTestManager.getTest().log(Status.FAIL,"Test Failed and Screenshoot capture");
            System.out.println("Failed Status Check");

            File temp= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = path+".png";
            try{
                FileUtils.copyFile(temp,new File(dest));
            }
            catch(Exception e){
                System.out.println(e.getStackTrace());
            }
        }
    }*/
}
