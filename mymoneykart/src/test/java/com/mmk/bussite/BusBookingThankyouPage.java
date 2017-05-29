package com.mmk.bussite;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.LogWriter;

public class BusBookingThankyouPage 
{
	@FindBy(className = "Popupiframe")
	WebElement iframe;
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(xpath = "//h3[@class='text-green']/label")
	WebElement ticketBookedMessage;
	
	@FindBy(id = "chkIsSMS")
	WebElement smsCheckBox;
	
	@FindBy(id = "chkIsEmail")
	WebElement emailCheckBox;
	
	@FindBy(id = "btnsend")
	WebElement smsAndEmalSendButton;
	
	@FindBy(id = "shareBtn")
	WebElement fbShareButton;
	
	@FindBy(xpath = "//a[@title='Share']")
	WebElement twitterShareButton;
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;
	
	@FindBy(id = "btnRating")
	WebElement skipLink;
	
	@FindBy(id = "btnSkip")
	WebElement skipRatingButton;

	
	WebDriver driver;
	
	public BusBookingThankyouPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public boolean checkBooking()
	{
		Boolean result = false;
		try
		{
			if(driver.getCurrentUrl().contains("http://bus.mymoneykart.com/BusBooking"))
			{
				
				List<WebElement> ticketDetails = driver.findElements(By.tagName("h3"));
				for(WebElement element : ticketDetails)
				{
					LogWriter.logger.info(element.getText());
				}
				result = true;
			}
			else
			{
				LogWriter.logger.info("There is some issue during payment");
				result =  false;
			}
			
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
			result=false;
		}
		return result;
	}
	public void checkSMS()	
	{
		try
		{
			smsCheckBox.click();
			LogWriter.logger.info("SMS option Checked");
			smsAndEmalSendButton.click();
			LogWriter.logger.info("Send Ticket Button Clicked");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(NotificationMessage.getText());
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	public void checkEmail()	
	{
		try
		{
			emailCheckBox.click();
			LogWriter.logger.info("Email Option Checked");
			smsAndEmalSendButton.click();
			LogWriter.logger.info("Send Ticket Button Clicked");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(NotificationMessage.getText());
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
	public void facebookSharingPage()	
	{
		try
		{
			fbShareButton.click();
			LogWriter.logger.info("Facebook Sharing Button Clicked");
			String parentWindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) 
			{
 
				driver.switchTo().window(winHandle);
			    LogWriter.logger.info("Switched in newly Opened window");
			}
			LogWriter.logger.info(driver.getCurrentUrl());
			
			driver.close();
			LogWriter.logger.info("New Window Close");
			driver.switchTo().window(parentWindow);		
			LogWriter.logger.info("Come Back In parent window");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	public void twitterShareingPage()	
	{
		try
		{
			twitterShareButton.click();
			
			String parentWindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle);
			    LogWriter.logger.info("Switched in newly Opened window");
			}
			LogWriter.logger.info(driver.getCurrentUrl());
			
			driver.close();
			LogWriter.logger.info("New Window Close");
			driver.switchTo().window(parentWindow);		
			LogWriter.logger.info("Come Back In parent window");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
	public void ratingOption()
	{
		try
		{
			skipLink.click();
			LogWriter.logger.info("Skip Link Clicked");
			Comman.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
			LogWriter.logger.info("On Rating Popup");
			skipRatingButton.click();
			LogWriter.logger.info("Rating Skipped");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Ticket Booking Success");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
}
