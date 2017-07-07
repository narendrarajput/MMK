package com.mmkproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

public class InviteFriendPage 
{
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath ="//li/a[normalize-space()='Invite Friends']")
	public WebElement inviteFriendsLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//input[@name='lstInviteFriendsModel[1].FriendName']")
	public List<WebElement> friendName;
	
	@FindBy(xpath = "//input[@name='lstInviteFriendsModel[1].FriendMobile']")
	public WebElement friendNumber;
	
	@FindBy(id = "btnInvite")
	public WebElement inviteButton; 
	
	@FindBy(id = "btnInviteMore")
	public WebElement addMoreButton; 
	
	@FindBy(xpath = "//div[@class='result-content']/i")
	public WebElement notificationMessageVisibility;
	
	@FindBy(xpath = "//span[@class='text-danger field-validation-error']/span")
	public List<WebElement> validationMessages;
	
	String submitMessage;
	
	WebDriver driver;
	
	public InviteFriendPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void fillFriendsDetail(String []friendName, String []friendMobile, int totalFriend) throws IOException
	{
			addMorefriend(totalFriend);
			for(int i=1;i<=3;i++)
			{
				driver.findElement(By.xpath("//input[@name='lstInviteFriendsModel["+i+"].FriendName']")).sendKeys(friendName[i-1]);
				driver.findElement(By.xpath("//input[@name='lstInviteFriendsModel["+i+"].FriendMobile']")).sendKeys(friendMobile[i-1]);			
			}
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			
	}		
	public void addMorefriend(int noOfFriend)
	{
		if(noOfFriend>3)
		{
			for(int i=1;i<=(noOfFriend-3);i++)
			{
				addMoreButton.click();
			}
		}
	}
	
	public void clickSubmitButton() throws IOException
	{
		inviteButton.click();
		try{
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(notificationMessage.getText());
			Common.wait.until(ExpectedConditions.visibilityOf(notificationMessageVisibility));		
			submitMessage = notificationMessage.getText();
			LogWriter.logger.info(notificationMessage.getText());
			TakeScreenshot.passedScreenShot();
		}
		catch(Exception e)
		{
			
		}

	}
	
	public void verifyFriendInvited(String message)
	{
		if(!(validationMessages.size()>0))
		{
			AssertJUnit.assertEquals(submitMessage, message);
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
