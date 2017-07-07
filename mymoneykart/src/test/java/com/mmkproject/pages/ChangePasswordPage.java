package com.mmkproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;
import com.sun.media.jfxmedia.logging.Logger;

public class ChangePasswordPage 
{
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public WebElement profileLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'Change Password')]")
	public WebElement changePasswordLink;
	
	@FindBy(id = "oldPassword")
	public WebElement usroldPassword;
	
	@FindBy(id = "NewPassword")
	public WebElement usrnewPassword;
	
	@FindBy(id = "confirmPassword")
	public WebElement usrconfirmPassword;
	
	@FindBy(id = "btnSubmit")
	public WebElement submitButton;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath="//span[@class='field-validation-error']")
	List<WebElement> validationMessages;
	
	@FindBy(id = "btnCancel")
	public WebElement cancelButton;
	
	
	WebDriver driver;
	
	public ChangePasswordPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void validationCheck() throws IOException, InterruptedException
	{
		if(driver.getCurrentUrl().contains("ChangePassword"))
		{
			submitButton.click();
			if(validationMessages.size()>0)
			{
					LogWriter.logger.info("------Fullfill following criteria------");
					for(WebElement e:validationMessages)
					{
						LogWriter.logger.info(e.getText());
						TakeScreenshot.passedScreenShot();
					}
	
			}
		}
		else
		{
			LogWriter.logger.info("Not navigated to change password");
			new MyMoneyKartHomePage(driver).navigateToChangePassword("http://www.mymoneykart.com/mmkweb/");
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			submitButton.click();
			if(validationMessages.size()>0)
			{
					LogWriter.logger.info("------Fullfill following criteria------");
					for(WebElement e:validationMessages)
					{
						LogWriter.logger.info(e.getText());
						TakeScreenshot.passedScreenShot();
					}
	
			}
		}
	}
	
	
	public void changePassword(String oldPassword, String newPassword) throws IOException, InterruptedException
	{
		if(driver.getCurrentUrl().contains("ChangePassword"))
		{
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));	
				LogWriter.logger.info("Start filling Password Details..........");
				usroldPassword.sendKeys(oldPassword);				
				usrnewPassword.sendKeys(newPassword);				
				usrconfirmPassword.sendKeys(newPassword);
				TakeScreenshot.passedScreenShot();
				submitButton.click();
				
				LogWriter.logger.info("Password Submit button clicked...");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				
				Assert.assertEquals(notificationMessage.getText(), "Password changed successfully");
				LogWriter.logger.info(notificationMessage.getText());
				TakeScreenshot.passedScreenShot();
				Common.wait.until(ExpectedConditions.invisibilityOf(notificationMessage));	
			}
			else
			{
				LogWriter.logger.info("Not navigated to change password");
				new MyMoneyKartHomePage(driver).navigateToChangePassword("http://www.mymoneykart.com/mmkweb/");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("Start filling Password Details..........");
				usroldPassword.sendKeys(oldPassword);				
				usrnewPassword.sendKeys(newPassword);				
				usrconfirmPassword.sendKeys(newPassword);
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("Password Details has been filled.");
				submitButton.click();
				
				LogWriter.logger.info("Password submit button clicked ");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				
				Assert.assertEquals(notificationMessage.getText(), "Password changed successfully");
				LogWriter.logger.info(notificationMessage.getText());
				TakeScreenshot.passedScreenShot();
				Common.wait.until(ExpectedConditions.invisibilityOf(notificationMessage));
			}
		
	}
	
	public void cancelFunctionOFChangePassword() throws IOException, InterruptedException
	{
		if(driver.getCurrentUrl().contains("ChangePassword"))
		{
				Common.wait.until(ExpectedConditions.visibilityOf(cancelButton));
				cancelButton.click(); 
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Moved To : "+driver.getCurrentUrl()+" : on cancel Click");
		}
		else
		{
				LogWriter.logger.info("Not navigated to change password");
				new MyMoneyKartHomePage(driver).navigateToChangePassword("http://www.mymoneykart.com/mmkweb/");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				TakeScreenshot.passedScreenShot();
				Common.wait.until(ExpectedConditions.visibilityOf(cancelButton));
				cancelButton.click(); 
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Moved To : "+driver.getCurrentUrl()+" : on cancel Click");
		}
		
	}
	
}
