package com.mmk.commonutils;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.testng.annotations.BeforeSuite;

public class LogWriter 

{
 
	public static final Logger logger = Logger.getLogger("MyLog");
	//public static final String Logger = null;  
    static FileHandler  fh;  
    
    @BeforeSuite
    public void setupLogger()
    {
    	try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("src/test/resources/MMK_Project_Log.log");  
	        
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	    } 
	    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 

    }

}
