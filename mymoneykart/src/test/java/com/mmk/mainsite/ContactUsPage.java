package com.mmk.mainsite;

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
import com.mmk.commonutils.LogWriter;
import com.mmk.commonutils.TakeScreenshot;

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
		LogWriter.logger.info("On Contact Us Page");
		personName.sendKeys(name);
		personEmail.sendKeys(email);
		personMobileNo.sendKeys(mobile);
		new Select(FeedbackType).selectByVisibleText(type);
		message.sendKeys(msg);
		Thread.sleep(2000);
		sendButton.click();
		LogWriter.logger.info("Contact Form Detail Filled and Submit clicked");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result-content']/i")));		
		LogWriter.logger.info(NotificationMessage.getText());
		TakeScreenshot.takeScreen("hello", true);
	}
}
