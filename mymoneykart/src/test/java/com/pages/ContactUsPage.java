package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.spi.Message;

import pom.utils.TakeScreenshot;

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
	
	WebDriver driver;
	
	public ContactUsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void submitContactUsForm(String name, String email, String mobile, String type, String msg) throws InterruptedException
	{
		personName.sendKeys(name);
		personEmail.sendKeys(email);
		personMobileNo.sendKeys(mobile);
		new Select(FeedbackType).selectByVisibleText(type);
		message.sendKeys(msg);
		Thread.sleep(2000);
		sendButton.click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result-content']/i")));		
		System.out.println(NotificationMessage.getText());
		TakeScreenshot.takeScreen("hello", true);
	}
}
