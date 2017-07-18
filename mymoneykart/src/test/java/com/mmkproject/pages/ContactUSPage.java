package com.mmkproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;
import com.mmk.reader.PropertyFileReader;

public class ContactUSPage 
{
	
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
	
	
	@FindBy(xpath = "//div[@class='result-content']/i")
	public WebElement notificationMessageVisibility;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	
	
	@FindBy(id = "loginModal")
	public WebElement loginmodel;
	
	@FindBy(xpath = "//span[@class='field-validation-error']/span")
	public List<WebElement> validationMessages;
	
	
	String submitMessage;
	
	WebDriver driver;
	
	public ContactUSPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void fillRequiredDetailsForContact(String name, String email, String mobile, String msg) throws InterruptedException, IOException
	{
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		LogWriter.logger.info("On Contact Us Page..");
		TakeScreenshot.passedScreenShot();
		personName.sendKeys(name);
		personEmail.sendKeys(email);
		personMobileNo.sendKeys(mobile);		
		message.sendKeys(msg);
		LogWriter.logger.info("All required data has been entered.....");
		Thread.sleep(2000);
		TakeScreenshot.passedScreenShot();
	}
	public void selectContactType(String type) throws IOException
	{
		new Select(FeedbackType).selectByVisibleText(type);
		LogWriter.logger.info("Feedback type selected..");
		TakeScreenshot.passedScreenShot();
	}
	public void clickSubmitButton() throws InterruptedException
	{
		Thread.sleep(1200);
		sendButton.click();
		LogWriter.logger.info("Contact submitting......");
		try{
			Common.wait.until(ExpectedConditions.visibilityOf(notificationMessageVisibility));		
			submitMessage = notificationMessage.getText();
			
		}
		catch(Exception e)
		{
			LogWriter.logger.info("Some exception during contact submit " +e );
		}
	}
	public void verifyContactSubmit(String message)
	{
		if(!(validationMessages.size()>0))
		{
			AssertJUnit.assertEquals(submitMessage, message);
			LogWriter.logger.info("Contact message : " +notificationMessage.getText());
		}
		else
		{
			LogWriter.logger.info("----Following Field must fill or need valid data-----");
			for(WebElement e: validationMessages)
			{
				LogWriter.logger.info(e.getText());
			}
		}
	}
}
