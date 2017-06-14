package mmk.comman.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mmk.reader.LogWriter;

public class SocialSharing 
{
	
	@FindBy(className = "Popupiframe")
	WebElement iframe;
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "shareBtn")
	List<WebElement> fbShareButton;
	
	@FindBy(xpath = "//a[@title='Share']")
	List<WebElement> twitterShareButton;
	
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

			if(fbShareButton.size()>0)
			{
				fbShareButton.get(0).click();
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
			else
			{
				LogWriter.logger.info("Transaction migh not successfull So there is no Facebook sharing option");
			}

	}
	
	public void twitterSharing()
	{
			if(twitterShareButton.size()>0)
			{
				twitterShareButton.get(0).click();
				
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
			else
			{
				LogWriter.logger.info("Transaction migh not successfull So there is no Twitter sharing option");
			}

	}
}
