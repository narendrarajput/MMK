package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage 
{
	
	@FindBy(xpath ="//li/a[normalize-space()='Contact Us']")
	public WebElement contactUsLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(id = "Name")
	public WebElement personName;
	
	@FindBy(id = "Email")
	public WebElement personEmail;
	
	@FindBy(id = "MobileNo")
	public WebElement personMobileNo;
	
	@FindBy(id = "TypeID")
	public WebElement FeedbackType;
	
	@FindBy(id = "Message")
	public WebElement message;
	
	@FindBy(id = "btnSends")
	public WebElement sendButton;
	
	@FindBy(id = "ResultBox")
	public WebElement NotificationMessage;
	
	public ContactUsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void submitContactUsForm()
	{
		personName.sendKeys("Narendra");
		personEmail.sendKeys("narendra.h.rajput@trimantra.net");
		personMobileNo.sendKeys("9422307801");
		message.sendKeys("Hello");
		sendButton.click();
	}
}
