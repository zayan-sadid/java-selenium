package com.MyBank.pageObjects.listener;

import com.MyBank.pageObjects.Base.TestBase;
import com.MyBank.pageObjects.reports.ExtentReportManager;
import com.MyBank.pageObjects.reports.ExtentTestManager;
import com.MyBank.pageObjects.utils.TestUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestListener extends TestBase implements ITestListener {
        WebDriver driver;

    public TestListener() {
    }

    public TestListener(WebDriver driver) {
        super(driver);
    }
    //  Logger logger= Logger.getLogger(TestListener.class);

    public void onStart(ITestContext context) {
     //   System.out.println("*** Test Suite " + context.getName() + " started ***");

    }

    public void onFinish(ITestContext context) {
       // logger.info("*** Test Suite " + context.getName() + " ending ***");
        ExtentTestManager.endTest();
        ExtentReportManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
 //       System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
           TestBase testBase=new TestBase();
        try {
            String className=result.getTestClass().getRealClass().getSimpleName();
            String methodName=result.getName();
            ExtentTestManager.startTest("<h5 style=\"color:rgb(170,121,36);font-family:tahoma;font-size:105%;\"><i>"+className+" << >>"+methodName+"</h5></i>"
                            ,"<h5 style=\"color:rgb(72,210,179);font-family:monospace;font-size:105%;\">"+result.getMethod().getDescription()+"</h5></i>")
                    .assignCategory(testBase.getOSName()+" :: "+testBase.getOSVersion())
                    .assignAuthor(System.getProperty("user.name"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result) {
   //     System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS,"<h4 style=\"color:green\"><b><i><u>Test Passed!!!</u></i></b></h4>");
    }

    public void onTestSkipped(ITestResult result) {
   //     System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP,"<h4 style=\"color:orange\"><b><i><u>Test Skipped!!!</u></i></b></h4>");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    //    System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }


    public void onTestFailure(ITestResult result) {

     //   System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
       // ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
       // log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
     //   ExtentTestManager.getTest().log(Status.FAIL,(result.getMethod().getMethodName() + " failed!"));

       // ITestContext context = result.getTestContext();
      //  WebDriver driver = (WebDriver) context.getAttribute("driver");

        String targetLocation = null;

        String testClassName = result.getTestClass().getRealClass().getSimpleName().trim();
        String timeStamp = TestUtils.getCurrentTimeStamp(); // get timestamp
        String testMethodName = result.getMethod().getMethodName().trim();
        String screenShotName = testMethodName +"_"+ timeStamp + ".png";
        System.out.println("screenshot name: "+screenShotName);
        String fileSeperator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestError" + fileSeperator
                + "screenshots";
        System.out.println("Report dir name: "+reportsPath);

        try {
            File file = new File(reportsPath + fileSeperator + testClassName); // Set

            if (!file.exists()) {
                if (file.mkdirs()) {
                  //  log.info("Directory: " + file.getAbsolutePath() + " is created!");
                    System.out.println("Directory has been created");
                } else {
                  //  log.info("Failed to create directory: " + file.getAbsolutePath());
                    System.out.println("Not able to create directory created");
                }

            }else{
                System.out.println("Error directory is already exists.");
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;
            // define
            // location
            File targetFile = new File(targetLocation);
           // log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
          //  log.info("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
          //  log.info("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
          //  log.info("An exception occurred while taking screenshot " + e.getCause());
        }

        // attach screenshots to report
        try {
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
        } catch (Exception e) {
          //  log.info("An exception occured while taking screenshot " + e.getCause());
        }
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File file=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination=System.getProperty("user.dir")+System.getProperty("file.separator")+"sc"+dateName+".png";
        File finalDestination= new File(destination);
        try {
            FileHandler.copy(file, finalDestination);
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(destination).build());
        } catch (Exception e) {
            //  log.info("An exception occured while taking screenshot " + e.getCause());
        }
    }
}



