package com.mmk.commonutils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.sun.media.jfxmedia.logging.Logger;

public class DriverSetup 
{
	public static WebDriver driver;
	
	@BeforeSuite
	@Parameters("browser")
	public void setUp( @Optional String browser) throws IOException
	{
		try
		{
			switch(browser.toLowerCase())
			{
					case "chrome":
					
						System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");	
						LogWriter.logger.info("Chrome Browser Started..");
						ChromeOptions options = new ChromeOptions(); 
						
						// To disable  "chrome controlled by automates software" infor bar
						options.addArguments("disable-infobars"); 
						
						// To hide "save password dislog"
						Map<String, Object> prefs = new HashMap<String, Object>();
					    prefs.put("credentials_enable_service", false);
					    prefs.put("profile.password_manager_enabled", false);
					    options.setExperimentalOption("prefs", prefs);
						driver = new ChromeDriver(options);	
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						TakeScreenshot.takeScreen("hello", true);
						
					break;
						
					case "firefox":
						System.out.println("Starting Firefox........");
						System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
						//ProfilesIni allProfiles = new ProfilesIni();
						//FirefoxProfile profile = allProfiles.getProfile("AutoTest");
						driver = new FirefoxDriver();		
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);				
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						//TakeScreenshot.takeScreen("hello", true);
					break;
					
					case "ie":
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();	  
						capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);					
						System.out.println("Starting InternetExplorer........");
						System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
						driver = new InternetExplorerDriver(capabilities);		
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						//TakeScreenshot.takeScreen("hello", true);
					break;	
						
					default:
						System.out.println("No parameter match found so Starting Firefox Browser Default.......");
						System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
						driver = new FirefoxDriver();		
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						TakeScreenshot.takeScreen("hello", true);
					break;			
			
			}
		}
		catch(Exception e)
		{
			LogWriter.logger.info("Exception In : "+Thread.currentThread().getStackTrace()[1].getClassName()+"-->"+Thread.currentThread().getStackTrace()[1].getMethodName()+" "+e);
			TakeScreenshot.takeScreen("hello", true);		
			System.out.println(e);
		}
		
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}
}
