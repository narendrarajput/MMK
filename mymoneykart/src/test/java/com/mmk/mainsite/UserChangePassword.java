package com.mmk.mainsite;

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

public class UserChangePassword 
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
	
	WebDriver driver;
	
	public UserChangePassword(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}


	
	
	public void changePassword(String oldPassword, String newPassword) throws IOException
	{

		
			Common.wait.until(ExpectedConditions.visibilityOf(profileLink));
			
			driver.navigate().to(TestDataComman.baseURL);			
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(usersname.size()>0)
			{
				TakeScreenshot.passedScreenShot();
				Actions action = new Actions(driver);
				action.moveToElement(profileLink).clickAndHold(changePasswordLink).click().build().perform();
				LogWriter.logger.info("Change Password Link Clicked");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				TakeScreenshot.passedScreenShot();
				
				usroldPassword.sendKeys(oldPassword);
				
				usrnewPassword.sendKeys(newPassword);
				
				usrconfirmPassword.sendKeys(newPassword);
				TakeScreenshot.passedScreenShot();
				submitButton.click();
				
				LogWriter.logger.info(" submit button clicked ");
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				
				Assert.assertEquals(notificationMessage.getText(), "Password changed successfully");
				LogWriter.logger.info(notificationMessage.getText());
				TakeScreenshot.passedScreenShot();
			}
			else
			{
				LogWriter.logger.info("Note on right Page");
			}
		
	}
	
}
