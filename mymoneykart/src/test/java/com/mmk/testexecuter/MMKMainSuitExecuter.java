package com.mmk.testexecuter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.driversetup.DriverSetup;
import com.mmk.mainsite.AccountSubscription;
import com.mmk.mainsite.ContactUsPage;
import com.mmk.mainsite.InviteFriends;
import com.mmk.mainsite.MMKUserKYCDetails;
import com.mmk.mainsite.MMKUserProfileDetails;
import com.mmk.mainsite.UserChangePassword;
import com.mmk.mainsite.UserLogin;
import com.mmk.mainsite.WalletTransactionHistoryPage;
import com.mmk.reader.ReadExcel;

public class MMKMainSuitExecuter extends DriverSetup
{
	
	//WebDriver driver = new DriverSetup().getDriver();

	 @Test
	public  void submitFeedback() throws InterruptedException, IOException
	{
		
		ContactUsPage conpage = new ContactUsPage(driver);
		conpage.submitContactUsForm(TestDataComman.fName, TestDataComman.fEmail, TestDataComman.fMobile, TestDataComman.fType, TestDataComman.fMessage);

	}
	@Test
	public void login() throws IOException, InterruptedException
	{
		UserLogin login = new UserLogin(driver);
		ReadExcel read = new ReadExcel();
		String uname = read.getCellData(1, 0);
		String passwd = read.getCellData(1, 1);
		login.doLogin(uname, passwd);
		
	}
	
	@Test
	public void checkUserWallet() throws IOException, InterruptedException
	{
		UserLogin login = new UserLogin(driver);
		
		login.checkWallet();
		
	}
	
	 @Test(dependsOnMethods={"login"})
	public void inviteFriend() throws IOException
	{
		InviteFriends invitefriend = new InviteFriends(driver);
		invitefriend.inviteFriend(TestDataComman.friendName,TestDataComman.friendNumber);
			
	}
	
	@Test(dependsOnMethods={"login"})
	public void changePassword() throws IOException
	{
		UserChangePassword changep = new UserChangePassword(driver);
		changep.changePassword(TestDataComman.oldPass, TestDataComman.newPass);			
	}
	
	@Test()
	public void walletTransactionHistory() throws IOException
	{
		WalletTransactionHistoryPage history = new WalletTransactionHistoryPage(driver);
		history.getWalletUsageData();
	}
	
	@Test(dependsOnMethods={"login"})
	public void getUserKYCDetails() throws IOException
	{
		MMKUserKYCDetails kyc= new MMKUserKYCDetails(driver);
		kyc.getUserKYCDetails();
	}
	
	@Test(dependsOnMethods={"getUserKYCDetails"})
	public void checkMissingKYCDetails() throws IOException
	{
		MMKUserKYCDetails kyc= new MMKUserKYCDetails(driver);
		kyc.checkMissingKYCInfo();
	}
	
	@Test(dependsOnMethods={"login"})
	public void getSubscriptionFeePaymentStatus() throws IOException
	{
		AccountSubscription ac= new AccountSubscription(driver);
		ac.checkAccountScbscriptionStatus();
	}
	
	@Test(dependsOnMethods={"login"})
	public void getUserProfileDetails() throws IOException
	{
		MMKUserProfileDetails up= new MMKUserProfileDetails(driver);
		up.getUserProfileDetails();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				TakeScreenshot.failedScreenShot(result.getMethod().getMethodName());
			} 
			catch (Exception e)
			{
				
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
	}
}
