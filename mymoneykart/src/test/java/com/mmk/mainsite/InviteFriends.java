package com.mmk.mainsite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.LogWriter;

public class InviteFriends 
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
	WebDriver driver;
	
	public InviteFriends(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void inviteFriend(String []friendName, String []friendMobile)
	{
		
		// The link is directly not clickable so need to click using javascriptExecuter
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", inviteFriendsLink);
		LogWriter.logger.info("Invite Friend Tab Link Clicked");
		for(int i=1;i<=3;i++)
		{
			driver.findElement(By.xpath("//input[@name='lstInviteFriendsModel["+i+"].FriendName']")).sendKeys(friendName[i-1]);
			driver.findElement(By.xpath("//input[@name='lstInviteFriendsModel["+i+"].FriendMobile']")).sendKeys(friendMobile[i-1]);			
		}
		inviteButton.click();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		LogWriter.logger.info(notificationMessage.getText());
		Assert.assertEquals(notificationMessage.getText(), "Your friends have been invited successfully");
	}			
}
