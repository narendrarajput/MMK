package com.mmk.bussite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

public class BusBookingThankyouPage 
{
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
	
	public boolean checkBooking() throws IOException
	{
		Boolean result = false;
			
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
	public void checkSMS() throws IOException	
	{
		if(smsCheckBox.size()>0)
		{
			smsCheckBox.get(0).click();
			LogWriter.logger.info("SMS option Checked");
			smsAndEmalSendButton.click();
			LogWriter.logger.info("Send Ticket Button Clicked");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
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
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
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
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			skipLink.click();
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Skip Link Clicked");
			Comman.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("On Rating Popup");
			Comman.wait.until(ExpectedConditions.visibilityOf(skipRatingButton));
			skipRatingButton.click();
			LogWriter.logger.info("Rating Skipped");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("Ticket Booking Success");

	}
	
}
