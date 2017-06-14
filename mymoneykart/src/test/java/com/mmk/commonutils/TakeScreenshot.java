package com.mmk.commonutils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.mmk.driversetup.DriverSetup;
import com.mmk.testexecuter.MMKMainSuitExecuter;


public class TakeScreenshot 
{
	
	
	
	
	public static void takeScreen(String text, Boolean addReport) 
	{
            // set file name and destination for screen shot
            File scrFile = ((TakesScreenshot) DriverSetup.driver).getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./surefire-reports/screenshots/";
            new File(destDir).mkdirs();
            String destFile = "mmk" + "_" + "test" + "_"  + dateFormat.format(new Date())+ ".png";

            // copy screen shot to directory for jenkins
            try 
            {
                FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }                    
    }	
	
	protected void reportLogScreenshot(File file) 
	{
	      System.setProperty("org.uncommons.reportng.escape-output", "false");
	  
	      String screenshotPath = System.getProperty("user.dir")+"\\surefire-reports\\screenshots\\"+file.getPath();
	  
	  
	      Reporter.log("<a href=\"" + screenshotPath + "\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
	      Reporter.log("<p><img width=\"1024\" src=" + screenshotPath  + " alt=\"screenshot at " + new Date()+ "\"/></p></a><br/>"); 
	}
	
	
	public static void passedScreenShot() throws IOException
	{
		Date d =new Date();
		File scrFile = ((TakesScreenshot)DriverSetup.driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("c:\\Temp\\PassedTest"+d.toString().replace(":", "_")+".png"));
		
		//new TakeScreenshot().reportLogScreenshot(scrFile);
	}
	 	
	public static void failedScreenShot() throws IOException
	{
		/*Date d =new Date();
		File scrFile = ((TakesScreenshot)DriverSetup.driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File("c:\\Temp\\FailedTest"+d.toString().replace(":", "_")+".png"));*/
		
        // set file name and destination for screen shot
		
        File scrFile = ((TakesScreenshot) DriverSetup.driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String destDir = "./surefire-reports/screenshots/";
        new File(destDir).mkdirs();
        String destFile = "mmk" + "_" + "test" + "_"  + dateFormat.format(new Date())+ ".png";
 
        try 
        {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
		new TakeScreenshot().reportLogScreenshot(new File(destFile));
	}

}
