package com.utilityproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.reader.LogWriter;

public class RechargeHomePage 
{
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(id = "MR")
	public WebElement prepaidServiceProvider; 	
	
	@FindBy(xpath = "//label[@for='PP']")
	public WebElement postpaidServiceProvider; 	
	
	@FindBy(id = "MobileNumber")
	public WebElement prepaidMobileNumber; 
	
	@FindBy(id = "OperatorCode")
	public WebElement prepaidOperator; 
	
	@FindBy(id = "CircleCode")
	public WebElement prepaidCircle; 
	
	@FindBy(id = "Amount")
	public WebElement prepaidAmount;
	
	@FindBy(id = "btnMobileSubmit")
	public WebElement proceedToPayButton;
	
	@FindBy(id = "UsedWalletAmount")
	WebElement walletAmount;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	
	@FindBy(id = "dthlink")
	public WebElement dthLink; 	
	
	
	@FindBy(id = "Subscriber")
	public WebElement dthSubscriberID; 	
	
	@FindBy(xpath = "//form[@id='frmDTHSubmit']//select[@id='OperatorCode']")
	public WebElement dthOperatorCode; 	
	
	@FindBy(id = "dthAmount")
	public WebElement dthRechargeAmount; 
	
	
	@FindBy(id = "btnDTHSubmit")
	public WebElement dthRechargeProceedButton; 
 
	
	WebDriver driver;
	
	public RechargeHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void navigateToUtilitySiteHomePage(String url)
	{
		if(!(driver.getCurrentUrl().contains(url)))
		{
			driver.navigate().to(url);
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		}
	}
	
	public boolean verifyHomePageNavigation(String url)
	{
		if((driver.getCurrentUrl().contains(url)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyUserLoginInSystem() throws IOException
	{
		if(!(usersname.size()>0))
		{
			LogWriter.logger.info("Logged in user not available..");
			return false;
		}
		else
		{
			LogWriter.logger.info("User is already login in system and username is "+ usersname.get(0).getText());
			TakeScreenshot.passedScreenShot();
			return true;
		}
	}
	
	public void fillMobileRechargeDetails(String rechargeType, String mobile, String operator, String Circle, String amount, String url) throws IOException
	{
		if(!(driver.getCurrentUrl().equals(url)))
		{
			LogWriter.logger.info("Navigating to.." +url);
			driver.navigate().to(url);
			TakeScreenshot.passedScreenShot();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		}
		
		if(rechargeType.equalsIgnoreCase("prepaid"))
		{
			if(!(prepaidServiceProvider.isSelected()))
			{
				prepaidServiceProvider.click();
			}
		}
		else if(rechargeType.equalsIgnoreCase("postpaid"))
		{
			if(!(postpaidServiceProvider.isSelected()))
			{
				postpaidServiceProvider.click();
			}
		}
		TakeScreenshot.passedScreenShot();
		LogWriter.logger.info("Filling Prepaid recharge details..");
		prepaidMobileNumber.sendKeys(mobile);
		new Select(prepaidCircle).selectByVisibleText(Circle);
		new Select(prepaidOperator).selectByVisibleText(operator);
		prepaidAmount.sendKeys(amount);
		TakeScreenshot.passedScreenShot();
		LogWriter.logger.info("Details has been filled . ");
		
	}

	public void proceedRecharge(String rechargeType) throws IOException
	{
		if(rechargeType.equalsIgnoreCase("dth"))
		{
			dthRechargeProceedButton.click();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("Proceed To pay Clicked");	
		}
		else
		{
			proceedToPayButton.click();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("Proceed To pay Clicked");	
		}
	}
	
	public void selectDTHRechargeOption(String url) throws IOException
	{
		if(!(driver.getCurrentUrl().equals(url)))
		{
			LogWriter.logger.info("Navigating to.." +url);
			driver.navigate().to(url);
			TakeScreenshot.passedScreenShot();
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		}
		
		dthLink.click();
		if(dthSubscriberID.isDisplayed())
		{
			LogWriter.logger.info("Switched to DTH Option");
		}
		else
		{
			LogWriter.logger.info("Unable to switch on  DTH Option");
		}
	}
	
	public void fillDTHRechargeDetails(String subscriberID, String dthOperator, String dthRechargeAmt) throws IOException
	{
		dthSubscriberID.sendKeys(subscriberID);
		new Select(dthOperatorCode).selectByVisibleText(dthOperator);
		dthRechargeAmount.sendKeys(dthRechargeAmt);
		TakeScreenshot.passedScreenShot();
	}
	
}
