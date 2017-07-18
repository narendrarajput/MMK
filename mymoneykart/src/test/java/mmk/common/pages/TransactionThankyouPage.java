package mmk.common.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

public class TransactionThankyouPage {
	
	@FindBy(className = "Popupiframe")
	WebElement iframe;
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(xpath = "//h3[@class='text-green']/label")
	WebElement ticketBookedMessage;
	
	@FindBy(id = "chkIsSMS")
	List<WebElement> smsCheckBox;
	
	@FindBy(id = "chkIsEmail")
	List<WebElement> emailCheckBox;
	
	@FindBy(id = "btnsend")
	WebElement smsAndEmalSendButton;
	
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;
	
	@FindBy(id = "btnRating")
	WebElement skipLink;
	
	@FindBy(id = "btnSkip")
	WebElement skipRatingButton;
	
	@FindBy(id = "shareBtn")
	List<WebElement> fbShareButton;
	
	@FindBy(xpath = "//a[@title='Share']")
	List<WebElement> twitterShareButton;

	@FindBy(tagName = "h3")
	List<WebElement> rechargeStatus;
	
	WebDriver driver;
	
	Boolean result = false;
	public TransactionThankyouPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public boolean verifyBookingDetails() throws IOException
	{
	
			
		if(driver.getCurrentUrl().contains("http://bus.mymoneykart.com/BusBooking"))
		{
				TakeScreenshot.passedScreenShot();
				List<WebElement> ticketDetails = driver.findElements(By.tagName("h3"));
				for(WebElement element : ticketDetails)
				{
					LogWriter.logger.info(element.getText());
				}
				result = true;
			}
			else
			{
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("There Might be some issue Occurred during payment. You are not on thankyou page");
				result =  false;
			}
		return result;
	}
	
	public boolean verifyRechargeStatus() throws IOException
	{
		if(driver.getCurrentUrl().contains("ThankYou"))
		{
			TakeScreenshot.passedScreenShot();
			for(WebElement e:rechargeStatus)
			{
				LogWriter.logger.info(e.getText());
			}
			
			if(rechargeStatus.get(0).getText().contains("Something went wrong"))
			{
				LogWriter.logger.info("--------Unsuccessfull Recharge--------------");
				result= false;
			}
			else
			{
				result= true;
			}
			
		}
		else
		{
			LogWriter.logger.info("Not on expected Page. Page URL is " + driver.getCurrentUrl());
		}
		return result;
	}
	public void checkSMS() throws IOException	
	{
		if(smsCheckBox.size()>0)
		{
			smsCheckBox.get(0).click();
			LogWriter.logger.info("SMS option Checked");
			smsAndEmalSendButton.click();
			LogWriter.logger.info("Send Ticket Button Clicked");
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(NotificationMessage.getText());
			TakeScreenshot.passedScreenShot();
		}
		else
		{
			LogWriter.logger.info("There is no SMS option available");
		}
	}
	public void checkEmail() throws IOException	
	{
		if(emailCheckBox.size()>0)
		{
			emailCheckBox.get(0).click();
			LogWriter.logger.info("Email Option Checked");
			smsAndEmalSendButton.click();
			LogWriter.logger.info("Send Ticket Button Clicked");
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(NotificationMessage.getText());
			TakeScreenshot.passedScreenShot();
		}
		else
		{
			LogWriter.logger.info("There is no Email option available");
		}
	}
		
	public void ratingOption() throws IOException
	{
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			skipLink.click();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Skip Link Clicked");
			Common.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("On Rating Popup");
			Common.wait.until(ExpectedConditions.visibilityOf(skipRatingButton));
			skipRatingButton.click();
			LogWriter.logger.info("Rating Skipped");
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("Ticket Booking Success");

	}
	
	public void facebookSharing() throws IOException
	{

			if(fbShareButton.size()>0)
			{
				TakeScreenshot.passedScreenShot();
				fbShareButton.get(0).click();
				LogWriter.logger.info("Facebook Sharing Button Clicked");
				String parentWindow = driver.getWindowHandle();
				for (String winHandle : driver.getWindowHandles()) 
				{
	 
					driver.switchTo().window(winHandle);
				    LogWriter.logger.info("Switched to Facebook window");
				    TakeScreenshot.passedScreenShot();
				}
				LogWriter.logger.info(driver.getCurrentUrl());
				TakeScreenshot.passedScreenShot();
				driver.close();
				LogWriter.logger.info("Window get closed");
				driver.switchTo().window(parentWindow);		
				LogWriter.logger.info("Back on parent window");
				TakeScreenshot.passedScreenShot();
			}
			else
			{
				LogWriter.logger.info("Transaction migh not successfull So there is no Facebook sharing option");
			}

	}
	
	public void twitterSharing() throws IOException
	{
			if(twitterShareButton.size()>0)
			{
				TakeScreenshot.passedScreenShot();
				twitterShareButton.get(0).click();
				
				String parentWindow = driver.getWindowHandle();
				for (String winHandle : driver.getWindowHandles()) 
				{
				    driver.switchTo().window(winHandle);
				    LogWriter.logger.info("Switched to Twitter window");
				    TakeScreenshot.passedScreenShot();
				}
				LogWriter.logger.info(driver.getCurrentUrl());
				TakeScreenshot.passedScreenShot();
				driver.close();
				LogWriter.logger.info("Window get closed");
				driver.switchTo().window(parentWindow);		
				LogWriter.logger.info("Back on parent window");
				TakeScreenshot.passedScreenShot();
			}
			else
			{
				LogWriter.logger.info("Transaction migh not successfull So there is no Twitter sharing option");
			}

	}

}
