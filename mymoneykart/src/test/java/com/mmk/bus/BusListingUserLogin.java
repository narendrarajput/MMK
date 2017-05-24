package com.mmk.bus;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pom.utils.Comman;
import pom.utils.TestDataComman;

import com.pages.UserLogin;

public class BusListingUserLogin 
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
	
	public BusListingUserLogin(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void listUserLogin() throws InterruptedException
	{
		
		/* Click select seat button */
		SelectSeatButton.click();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		
		try
		{
		if(usersname.isDisplayed())
		{
			System.out.println("Already Login");
		
		}}
		catch(Exception e)
		{
			/* user login from bus listing page by clicking Select Seat button */
			new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
		}
	}


	public void selectSeat()
	{
		
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		 
		for(WebElement e:seats)
		{
			if(e.findElement(By.tagName("div")).getAttribute("class").equals("available seat"))
			{
				try{
				Thread.sleep(2000);
				}
				catch(Exception err)
				{
					
				}
				//Comman.jsExecuter.executeScript("arguments[0].click();", e);
				e.click();
				break;
			}
		}
		
		new Select(boardingPoint).selectByIndex(1);
		proceedButton.click();
	}

}
