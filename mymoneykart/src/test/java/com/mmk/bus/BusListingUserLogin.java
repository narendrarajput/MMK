package com.mmk.bus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
