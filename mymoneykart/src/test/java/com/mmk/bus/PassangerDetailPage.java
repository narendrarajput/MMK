package com.mmk.bus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pom.utils.Comman;

public class PassangerDetailPage 
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
	
	WebDriver driver;
	
	public PassangerDetailPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void fillPassengerDetailAndProceedToPay(String pFirstName, String pLastName, String pGender, String pAge, String pID, String pIDNo, String emgNo)
	{
		
		Comman.wait.until(ExpectedConditions.visibilityOf(psgFirstName));
		psgFirstName.sendKeys(pFirstName);
		psgLastName.sendKeys(pLastName);
		new Select(psgGender).selectByVisibleText(pGender);
		psgAge.sendKeys(pAge);
		new Select(psgIdProff).selectByVisibleText(pID);
		psgIdProffnumber.sendKeys(pIDNo);
		emergencyNumber.sendKeys(emgNo);
		proceedButton.click();
		Comman.wait.until(ExpectedConditions.visibilityOf(confirmation));
		confirmation.click();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
	}
}
