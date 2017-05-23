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
	
	WebDriver driver;
	
	public BusListingUserLogin(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void listUserLogin() throws InterruptedException
	{
		SelectSeatButton.click();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		
		System.out.println("test");
		new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
	}
	
}
