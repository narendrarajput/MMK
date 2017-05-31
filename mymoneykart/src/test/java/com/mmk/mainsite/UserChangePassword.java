package com.mmk.mainsite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;

public class UserChangePassword 
{
	
	
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


	
	
	public void changePassword(String oldPassword, String newPassword)
	{
		try
		{
			driver.navigate().to(TestDataComman.baseURL);
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			Actions action = new Actions(driver);
			action.moveToElement(profileLink).clickAndHold(changePasswordLink).click().build().perform();
			LogWriter.logger.info("Change Password Link Clicked");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			
			usroldPassword.sendKeys(oldPassword);
			
			usrnewPassword.sendKeys(newPassword);
			
			usrconfirmPassword.sendKeys(newPassword);
			
			submitButton.click();
			
			LogWriter.logger.info(" submit button clicked ");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			
			Assert.assertEquals(notificationMessage.getText(), "Password changed successfully");
			LogWriter.logger.info(notificationMessage.getText());
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
		
	}
	
}
