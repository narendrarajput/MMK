package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InviteFriends 
{
	@FindBy(xpath ="//li/a[normalize-space()='Invite Friends']")
	public WebElement inviteFriendsLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(id = "Name")
	public WebElement personName;
	
	@FindBy(id = "Email")
	public WebElement personEmail;
}
