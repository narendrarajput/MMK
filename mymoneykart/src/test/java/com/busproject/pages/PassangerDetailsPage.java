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
import com.mmk.reader.LogWriter;

public class PassangerDetailsPage 
{
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "BlockSeatPaxDetails_0__Name")
	WebElement psgFirstName;
	
	@FindBy(id = "BlockSeatPaxDetails_0__LastName")
	WebElement psgLastName;
	
	@FindBy(id = "BlockSeatPaxDetails_0__Sex")
	WebElement psgGender;
	
	@FindBy(name = "BlockSeatPaxDetails[0].Age")	
	WebElement psgAge;
	
	@FindBy(id = "BlockSeatPaxDetails_0__IdType")
	WebElement psgIdProff;
	
	@FindBy(id = "BlockSeatPaxDetails_0__IdNumber")
	WebElement psgIdProffnumber;
	
	@FindBy(id = "txtEmergencyMobile")
	WebElement emergencyNumber;
	
	@FindBy(css = "div.row>div>button.btn.btn-common.btnProceed")
	WebElement proceedButton;
	
	@FindBy(id = "btnYesConfirmYesNo")
	WebElement confirmation;
	
	@FindBy(xpath = "//input[contains(@name,'BlockSeatPaxDetails')][@type='text']")
	List<WebElement> passangerInfo;
	
	@FindBy(xpath = "//input[contains(@name,'BlockSeatPaxDetails')][@type='text']")
	List<WebElement> passangerInfoLabel;
	
	@FindBy(id = "IsSelf")
	WebElement isSelfCheckbox;
	
	
	
	WebDriver driver;
	
	public PassangerDetailsPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	public void fillPassengerDetails(String pFirstName, String pLastName, String pGender, String pAge, String pID, String pIDNo, String emgNo) throws IOException
	{
			Common.wait.until(ExpectedConditions.visibilityOf(psgFirstName));
			TakeScreenshot.passedScreenShot();
			psgFirstName.clear();
			psgFirstName.sendKeys(pFirstName);
			psgLastName.clear();
			psgLastName.sendKeys(pLastName);
			new Select(psgGender).selectByVisibleText(pGender);
			psgAge.click();
			psgAge.sendKeys(pAge);
			new Select(psgIdProff).selectByVisibleText(pID);
			psgIdProffnumber.sendKeys(pIDNo);
			emergencyNumber.sendKeys(emgNo);
			LogWriter.logger.info("Passanger Details has been filled");
			TakeScreenshot.passedScreenShot();
	}
	
	public void clickProceedToPayButtonAndAcceptConfirmations() throws IOException
	{
		proceedButton.click();
		Common.wait.until(ExpectedConditions.visibilityOf(confirmation));
		TakeScreenshot.passedScreenShot();
		confirmation.click();
		LogWriter.logger.info("Confirmation accepted");
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		TakeScreenshot.passedScreenShot();
	}
	
	public void checkIsSelfOption()
	{
		LogWriter.logger.info("------- Before isSelf Check click values are ---------");
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		for(WebElement e : passangerInfo)
		{
			LogWriter.logger.info(e.findElement(By.xpath("preceding::label[1]")).getText() +"  =  "+e.getAttribute("value") );
		}
		isSelfCheckbox.click();
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		LogWriter.logger.info("------- values After isSelf Checked ---------");
		for(WebElement e : passangerInfo)
		{
			LogWriter.logger.info(e.findElement(By.xpath("preceding::label[1]")).getText() +"  =  "+e.getAttribute("value") );
		}
		if(passangerInfo.get(0).findElement(By.xpath("preceding::label[1]")).getText().length()>0)
		{
			LogWriter.logger.info("-Info get filled-");
		}
		else
		{
			LogWriter.logger.info("Info Missing See the log for Tracing");
		}
		
	}

}
