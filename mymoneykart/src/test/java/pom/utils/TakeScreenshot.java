package pom.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.executer.ExecuteTest;


public class TakeScreenshot 
{
	public static void takeScreen(String text, Boolean addReport) 
	{
            // set file name and destination for screen shot
            File scrFile = ((TakesScreenshot) DriverSetup.driver).getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./surefire-reports/html/screenshots/";
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
            
            //log("screenShot: " + destDir + "/" + destFile, false);
            // Display screenshot to ReportNG
           /* if (addReport) {

                String userDirector = "./screenshots/";
                log("<u><b>||||||" + text + "</b></u><br><a href=\""
                        + userDirector + destFile + "\"><img src=\""
                        + userDirector + destFile + "\" alt=\"\""
                        + "height='100' width='100'/> " + "<br />", addReport);
            }*/
        
    }

}
