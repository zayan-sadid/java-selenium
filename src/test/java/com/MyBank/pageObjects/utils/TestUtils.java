package com.MyBank.pageObjects.utils;

import com.MyBank.pageObjects.Base.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class TestUtils extends TestBase {

    public TestUtils(WebDriver driver) {
        super(driver);
    }

    public static Properties getPropertiesFile(String fileName) throws IOException {

        FileInputStream fis=null;
        Properties properties=null;
        try {
           fis =new FileInputStream(fileName);
           properties =new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fis !=null){
                fis.close();
            }
        }
        return properties;
    }

    public static String  getCurrentTimeStamp(){
        Date date = new Date();
        String time=String.valueOf(new Timestamp(date.getTime()));
        return time;
    }

    public  void validatePageTitle(String expectedPageTitle){
        String title=driver.getTitle();
        Assert.assertEquals(title,expectedPageTitle);
    }

 public void takeScreenshot(WebDriver driver){

 }

}
