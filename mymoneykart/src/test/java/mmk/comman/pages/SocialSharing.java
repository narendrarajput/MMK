package mmk.comman.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.reader.LogWriter;

public class SocialSharing 
{
	
	@FindBy(className = "Popupiframe")
	WebElement iframe;
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "shareBtn")
	WebElement fbShareButton;
	
	@FindBy(xpath = "//a[@title='Share']")
	WebElement twitterShareButton;
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;
	
	WebDriver driver;
	
	public SocialSharing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	public void facebookSharing()
	{
		try
		{
			Comman.wait.until(ExpectedConditions.visibilityOf(fbShareButton));
			fbShareButton.click();
			LogWriter.logger.info("Facebook Sharing Button Clicked");
			String parentWindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) 
			{
 
				driver.switchTo().window(winHandle);
			    LogWriter.logger.info("Switched to Facebook window");
			}
			LogWriter.logger.info(driver.getCurrentUrl());
			
			driver.close();
			LogWriter.logger.info("Window get closed");
			driver.switchTo().window(parentWindow);		
			LogWriter.logger.info("Back on parent window");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
	public void twitterSharing()
	{
		try
		{
			Comman.wait.until(ExpectedConditions.visibilityOf(twitterShareButton));
			twitterShareButton.click();
			
			String parentWindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle);
			    LogWriter.logger.info("Switched to Twitter window");
			}
			LogWriter.logger.info(driver.getCurrentUrl());
			
			driver.close();
			LogWriter.logger.info("Window get closed");
			driver.switchTo().window(parentWindow);		
			LogWriter.logger.info("Back on parent window");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
}
