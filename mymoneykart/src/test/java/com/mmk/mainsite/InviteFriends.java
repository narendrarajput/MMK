package com.mmk.mainsite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class InviteFriends 
{
	@FindBy(xpath ="//li/a[normalize-space()='Invite Friends']")
	public WebElement inviteFriendsLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//input[@placeholder='Friend Name']")
	public List<WebElement> friendName;
	
	@FindBy(xpath = "//input[@placeholder='Mobile Number']")
	public WebElement friendNumber;

	WebDriver driver;
	
	public InviteFriends(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void inviteFriend()
	{
		//WebDriverWait wait = new WebDriverWait(driver, 60);
		//wait.until(ExpectedConditions.elementToBeClickable(inviteFriendsLink));

		// The link is directly not clickable so need to click using javascriptExecuter
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", inviteFriendsLink);
	//	friendName.sendKeys("My");
		friendNumber.sendKeys("8866225511");
	}
	
}
