package com.mmk.utility;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.UserLogin;
import com.mmk.reader.LogWriter;

public class UtilityCheckUserLogin 
{
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "UsedWalletAmount")
	WebElement walletAmount;
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;

	WebDriver driver;
	
	public UtilityCheckUserLogin(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void checkUserLogin()
	{
		try
		{					
			if(usersname.size()>0)
			{
				LogWriter.logger.info("User is already Logged in the system");
		
			}
			else
			{
				TakeScreenshot.passedScreenShot();
				new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("Login Method called from Utility");
			}
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
		
		try
		{
			Comman.wait.until(ExpectedConditions.visibilityOf(walletAmount));
			TakeScreenshot.passedScreenShot();
			if(driver.getCurrentUrl().contains("Payment?TransactionID"))
			{
				LogWriter.logger.info("On Wallet Page");
			}
			else
			{
				LogWriter.logger.info("Some thing went wrong in payment Proceeding ");
			}

		}
		catch(Exception e)
		{
			LogWriter.logger.info(NotificationMessage.getText());
		}
	}
}
