package com.mmk.bus;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.LogWriter;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.UserLogin;

public class BusListingUserLoginAndSelectSeat 
{
	@FindBy(xpath = "//div/button[contains(.,'Select Seats')]")
	WebElement SelectSeatButton;
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	WebElement usersname;
	
	@FindBy(xpath = "//table[@class='buslayout-table']//td")
	List<WebElement> seats;
	@FindBy(xpath = "//div")
	By  seatdiv;
	
	@FindBy(css = "div>.form-group>select[id*='ddlBoardingPoint']")
	WebElement boardingPoint;
	
	@FindBy(id = "btn-proceed")
	WebElement proceedButton;
	
	WebDriver driver;
	
	public BusListingUserLoginAndSelectSeat(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void listUserLogin() throws InterruptedException
	{
		try
		{
		
			/* Click select seat button */		
			SelectSeatButton.click();		
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Select Seat button option selected");
			
			if(usersname.isDisplayed())
			{
				LogWriter.logger.info("User is already Logged in the system");
		
			}
		}
		catch(Exception e)
		{
			/* user login from bus listing page by clicking Select Seat button */
			new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
			LogWriter.logger.info("Login Method called From Bus Listing Page - Select Seat");
		}
	}

	public void selectSeat()
	{
		try
		{
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		 
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
				LogWriter.logger.info("Available Seat Has been Selected");
				break;
			}
		}
			new Select(boardingPoint).selectByIndex(1);
			LogWriter.logger.info("Boarding Point has been Selected");
			proceedButton.click();
			LogWriter.logger.info("Proceed Button Clicked");
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}

}
