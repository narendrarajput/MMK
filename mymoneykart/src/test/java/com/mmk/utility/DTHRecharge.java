package com.mmk.utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.reader.LogWriter;

public class DTHRecharge 
{
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(id = "dthlink")
	public WebElement dthLink; 	
	
	
	@FindBy(id = "Subscriber")
	public WebElement subscriberID; 	
	
	@FindBy(xpath = "//form[@id='frmDTHSubmit']//select[@id='OperatorCode']")
	public WebElement operatorCode; 	
	
	@FindBy(id = "dthAmount")
	public WebElement dthRechargeAmount; 
	
	
	@FindBy(id = "btnDTHSubmit")
	public WebElement dthRechargeProceedButton; 
	
	WebDriver driver;
	
	public DTHRecharge(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	
	public void dthRecharge(String subscriberNo, String dthoperator, String amount) throws IOException
	{
			LogWriter.logger.info("Navigating to.."+UtilitySiteTestData.utilitySiteUrl);
			driver.navigate().to(UtilitySiteTestData.utilitySiteUrl);
			TakeScreenshot.passedScreenShot();
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			
			dthLink.click();
			TakeScreenshot.passedScreenShot();
			subscriberID.sendKeys(subscriberNo);
			
			//Comman.wait.until(ExpectedConditions.elementToBeClickable(operatorCode));
			new Select(operatorCode).selectByVisibleText(dthoperator);
			dthRechargeAmount.sendKeys(amount);
			TakeScreenshot.passedScreenShot();
			dthRechargeProceedButton.click();
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			LogWriter.logger.info("Proceed To pay Clicked");	

	}
	
}
