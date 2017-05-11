package pom.utils;
import java.io.IOException;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class DriverSetup 
{
	
	
	public static WebDriver driver;
	@BeforeTest
	@Parameters("browser")
	public void setUp( @Optional String browser) throws IOException
	{
		try
		{
			switch(browser)
			{
					case "chrome":
					
						System.out.println("Starting chrome........");
						System.setProperty("webdriver.chrome.driver","D:/Application/chromedriver.exe");
						ChromeOptions options = new ChromeOptions(); 
						options.addArguments("disable-infobars"); 
						driver = new ChromeDriver(options);	
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						TakeScreenshot.takeScreen("hello", true);
					break;
						
					case "firefox":
						System.out.println("Starting Firefox........");
						System.setProperty("webdriver.gecko.driver","D:/Application/geckodriver.exe");
						ProfilesIni allProfiles = new ProfilesIni();
						FirefoxProfile profile = allProfiles.getProfile("AutoTest");
						driver = new FirefoxDriver(profile);		
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);				
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						TakeScreenshot.takeScreen("hello", true);
					break;
					
					case "ie":
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();	  
						capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);					
						System.out.println("Starting InternetExplorer........");
						System.setProperty("webdriver.ie.driver","D:/Application/IEDriverServer.exe");
						driver = new InternetExplorerDriver(capabilities);		
						driver.manage().window().maximize();
						driver.get(TestDataComman.baseURL);
						driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
						TakeScreenshot.takeScreen("hello", true);
					break;	
						
					default:
						System.out.println("No parameter match found so Starting Firefox Browser Default.......");
						System.setProperty("webdriver.gecko.driver","D:/Application/geckodriver.exe");
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
			System.out.println("Exception In : "+Thread.currentThread().getStackTrace()[1].getClassName()+"-->"+Thread.currentThread().getStackTrace()[1].getMethodName());
			TakeScreenshot.takeScreen("hello", true);
		}
		
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}
}
