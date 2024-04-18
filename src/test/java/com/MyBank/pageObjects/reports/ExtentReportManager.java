package com.MyBank.pageObjects.reports;

import com.MyBank.pageObjects.utils.TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExtentReportManager {

    private static ExtentReports extentReport;
    private  static String extentReportPrefix;
    private static String reportFileName = "Test-Report";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFileDir = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFileDir +fileSeperator;

    public static ExtentReports getInstance() {
        if (extentReport == null)
            createInstance();
        return extentReport;
    }

    public  static ExtentReports createInstance(){
        String filePath=getReportPath(reportFileDir)+getExtentReportPrefixName(reportFileName);
        extentReport=new ExtentReports();
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(filePath+".html" ) ;
        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("Application Version",getValuesFromPropertiesFile("Application_Version"));
        extentReport.setSystemInfo("OS Name",getValuesFromPropertiesFile("Application_Version"));
        extentReport.setSystemInfo("OS Version",getValuesFromPropertiesFile("OS_Name"));
        extentReport.setSystemInfo("Environment",getValuesFromPropertiesFile("OS_Version"));
        extentReport.setSystemInfo("Tester",getValuesFromPropertiesFile(System.getProperty("user.name")));
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName(getValuesFromPropertiesFile("Report_Name"));
        sparkReporter.config().setDocumentTitle(getValuesFromPropertiesFile("Document_Title"));
        return extentReport;
    }

    public static String getExtentReportPrefixName(String reportFile){
        String date=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportPrefix=reportFile+"_"+date;
        return extentReportPrefix;
    }

    public static String getValuesFromPropertiesFile(String key){
        String propertyValue=null;
        try {
            String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
            Properties properties= TestUtils.getPropertiesFile(filePath);
            propertyValue=properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    //Create the report path
    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }

}
