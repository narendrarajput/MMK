package com.busproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.UserLogin;
import com.mmk.reader.LogWriter;

public class BusListingPage 
{
	
	@FindBy(id = "dvListLoader")
	 WebElement gridLoader;
	
	@FindBy(id = "modify-search")
	WebElement modifySearchButton;
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;

	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "txtFromStation")
	WebElement fromStation;
	
	@FindBy(id = "txtToStation")
	WebElement toStation;
	
	@FindBy(id = "txtJourneyDate")
	WebElement  travelDate;
	
	@FindBy(xpath = "//button[@type='submit'][text()='SEARCH']")
	WebElement searchButton;
	
	@FindBy(xpath = "//input[@id='hdDate']")
	WebElement modifiedDate;
	
	@FindBy(xpath = "//div/button[contains(.,'Select Seats')]")
	WebElement SelectSeatButton;
	
	@FindBy(xpath = "//table[@class='buslayout-table']//td")
	List<WebElement> seats;
	
	@FindBy(xpath = "//div")
	By  seatdiv;
	
	@FindBy(css = "div>.form-group>select[id*='ddlBoardingPoint']")
	WebElement boardingPoint;
	
	@FindBy(id = "btn-proceed")
	WebElement proceedButton;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	WebDriver driver;
	
	public BusListingPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void modifySearch(String source, String destination, String journeyDate)
	{
		LogWriter.logger.info("In modifySearch.... ");
		if(driver.getCurrentUrl().contains("BusList"))
		{
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Modifying Search.... ");
			modifySearchButton.click();
			fromStation.clear();
			toStation.clear();
			travelDate.clear();
			fromStation.sendKeys(source);
			toStation.sendKeys(destination);
			travelDate.sendKeys(journeyDate);
			LogWriter.logger.info("Modified Data has been set.... ");
			searchButton.click();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			
			if(modifiedDate.getAttribute("value").equals(journeyDate))
			{
				LogWriter.logger.info("Search has been modified.....");
			}
			else
			{
				LogWriter.logger.info("Unable to modify the search......");
			}
		}
	}
	
	public boolean isUserAlreadyLogin() throws InterruptedException, IOException
	{
		
		if(usersname.size()>0)
		{
			SelectSeatButton.click();
			return true;
			
		}
		else
		{
			SelectSeatButton.click();
			return false;
		}
	}
	
	public void selectSeat() throws IOException
	{
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		TakeScreenshot.passedScreenShot();
		Common.wait.until(ExpectedConditions.visibilityOfAllElements(seats));
		for(WebElement e:seats)
		{
			if(e.findElement(By.tagName("div")).getAttribute("class").equals("available seat"))
			{
				try
				{
					Thread.sleep(2000);
				}
				catch(Exception err)
				{
					
				}				
				//Comman.jsExecuter.executeScript("arguments[0].click();", e);
				e.click();
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("Available Seat Has been Selected");
				break;
			}
			
		}
			new Select(boardingPoint).selectByIndex(1);
			LogWriter.logger.info("Boarding Point has been Selected");
			TakeScreenshot.passedScreenShot();
			proceedButton.click();
			LogWriter.logger.info("Proceed Button Clicked");
	}

}
