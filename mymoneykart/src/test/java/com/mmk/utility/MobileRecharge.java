package com.mmk.utility;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TestDataComman;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.mainsite.UserLogin;
import com.mmk.reader.LogWriter;

public class MobileRecharge 
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
	
	WebDriver driver;
	
	public MobileRecharge(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	
	public void prepaidRecharge(String mobile, String operator, String Circle, String amount)
	{
		try
		{
			LogWriter.logger.info("Navigating to.."+UtilitySiteTestData.utilitySiteUrl);
			driver.navigate().to(UtilitySiteTestData.utilitySiteUrl);
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(!(prepaidServiceProvider.isSelected()))
			{
				prepaidServiceProvider.click();
			}
			LogWriter.logger.info("Filling Prepaid recharge details..");
			prepaidMobileNumber.sendKeys(mobile);
			new Select(prepaidCircle).selectByVisibleText(Circle);
			new Select(prepaidOperator).selectByVisibleText(operator);
			prepaidAmount.sendKeys(amount);
			proceedToPayButton.click();
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Proceed To pay Clicked");	
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
	public void postpaidRecharge(String mobile, String operator, String Circle, String amount)
	{
		try
		{
			LogWriter.logger.info("Navigating to.."+UtilitySiteTestData.utilitySiteUrl);
			driver.navigate().to(UtilitySiteTestData.utilitySiteUrl);
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(!(postpaidServiceProvider.isSelected()))
			{
				postpaidServiceProvider.click();
			}
			LogWriter.logger.info("Filling Prepaid recharge details..");
			prepaidMobileNumber.sendKeys(mobile);
			new Select(prepaidCircle).selectByVisibleText(Circle);
			new Select(prepaidOperator).selectByVisibleText(operator);
			prepaidAmount.sendKeys(amount);
			proceedToPayButton.click();
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Proceed To pay Clicked");	
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}
	
	public void checkUserLogin() throws InterruptedException
	{
		try
		{					
			if(usersname.size()>0)
			{
				LogWriter.logger.info("User is already Logged in the system");
		
			}
			else
			{
				new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
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
		}
		catch(Exception e)
		{
			LogWriter.logger.info(NotificationMessage.getText());
		}
			if(driver.getCurrentUrl().contains("Payment?TransactionID"))
		{
			LogWriter.logger.info("On Wallet Page");
		}
		else
		{
			LogWriter.logger.info("Some thing went wrong in payment Proceeding ");
		}
	}
}
