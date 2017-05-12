package com.executer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.utils.DriverSetup;
import pom.utils.TestDataComman;

import com.pages.ContactUsPage;
import com.pages.InviteFriends;
import com.pages.UserLogin;

public class ExecuteTest extends DriverSetup
{
	
	//WebDriver driver = new DriverSetup().getDriver();

	@Test(priority=2)
	public  void submitFeedback() throws InterruptedException
	{
		
		ContactUsPage conpage = new ContactUsPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));		
		conpage.contactUsLink.click();
		conpage.submitContactUsForm(TestDataComman.fName, TestDataComman.fEmail, TestDataComman.fMobile, TestDataComman.fType, TestDataComman.fMessage);
		
		

	}
	@Test(priority = 1)
	public void login()
	{
		UserLogin login = new UserLogin(driver);
		login.doLogin(TestDataComman.username, TestDataComman.password);
		
	}
	
	@Test(priority = 3)
	public void inviteFriend()
	{
		InviteFriends invitefriend = new InviteFriends(driver);
		invitefriend.inviteFriend();
		
		
	}

}
