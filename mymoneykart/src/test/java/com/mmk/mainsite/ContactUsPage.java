package com.mmk.mainsite;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

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
	
	@FindBy(id = "MobileNonarendra")
	public WebElement personMobileNo;
	
	@FindBy(id = "TypeID")
	public WebElement FeedbackType;
	
	@FindBy(id = "Message")
	public WebElement message;
	
	@FindBy(id = "btnSends")
	public WebElement sendButton;
	
	@FindBy(id = "ResultBox")
	public WebElement NotificationMessage;
	
	@FindBy(id = "loginModal")
	public WebElement loginmodel;
	
	
	
	WebDriver driver;
	
	public ContactUsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void submitContactUsForm(String name, String email, String mobile, String type, String msg) throws InterruptedException, IOException
	{

			
		Common.wait.until(ExpectedConditions.invisibilityOf(loginmodel));
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));	
		contactUsLink.click();
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		LogWriter.logger.info("On Contact Us Page");
		TakeScreenshot.passedScreenShot();
		personName.sendKeys(name);
		personEmail.sendKeys(email);
		personMobileNo.sendKeys(mobile);
		new Select(FeedbackType).selectByVisibleText(type);
		message.sendKeys(msg);
		Thread.sleep(2000);
		TakeScreenshot.passedScreenShot();
		sendButton.click();
		LogWriter.logger.info("Contact Form Detail Filled and Submit clicked");
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='result-content']/i")));		
		LogWriter.logger.info(NotificationMessage.getText());
		TakeScreenshot.passedScreenShot();
	}
}
