package com.mmk.test_execution_engine;

import java.io.IOException;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.mmkproject.pages.AccountSubscriptionPage;
import com.mmkproject.pages.ContactUSPage;
import com.mmkproject.pages.InviteFriendPage;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.driversetup.DriverSetup;
import com.mmk.reader.LogWriter;
import com.mmk.reader.PropertyFileReader;
import com.mmkproject.pages.ChangePasswordPage;
import com.mmkproject.pages.MyMoneyKartHomePage;
import com.mmkproject.pages.UserKYCDetailsPage;
import com.mmkproject.pages.UserProfilePage;

public class MMKMainSiteTestCases extends DriverSetup 
{
	MyMoneyKartHomePage homepage;
	
	@Test
	public void userLogin() throws InterruptedException, IOException
	{
		
		homepage = new MyMoneyKartHomePage(driver);
		homepage.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		Boolean result = homepage.validateUserLogin(PropertyFileReader.getProperty("usersname"));
		if(result)
		{
			LogWriter.logger.info("Login Success......");
		}
	}
	
	@Test
	public void userWalletDetails() throws InterruptedException, IOException
	{
		
		homepage = new MyMoneyKartHomePage(driver);
		homepage.checkUserWalletStatus();

	}
	
	@Test
	public void busSearchFromMMKHomePageWithUserLogin() throws InterruptedException, IOException
	{
		homepage.doUserLogin(TestDataComman.username, TestDataComman.password);
		homepage = new MyMoneyKartHomePage(driver);
		homepage.busSearchDetailsFillAndGo(TestDataComman.sourceCity, TestDataComman.destinationCity, TestDataComman.journecyDate);
		Boolean result= homepage.validateBusSearchResultRedirect(TestDataComman.baseURL);
		if(result)
		{
			LogWriter.logger.info("Buses are available for given search..");
		}
		else
		{
			LogWriter.logger.info("There is no bus for given Search");
		}

	}
	
	@Test
	public void navigateOnContactUsPage() throws InterruptedException, IOException
	{
		 
		homepage = new MyMoneyKartHomePage(driver);
		homepage.navigateToContactUsPage(PropertyFileReader.getProperty("mainSiteURL"));

	}
	@Test
	public void navigateOnInviteFriend() throws InterruptedException, IOException
	{
		 
		homepage = new MyMoneyKartHomePage(driver);
		homepage.navigateToInviteFriendPage(TestDataComman.baseURL);

	}
	@Test
	public void submitContactByEndUser() throws InterruptedException, IOException
	{
		 
		homepage = new MyMoneyKartHomePage(driver);
		homepage.navigateToContactUsPage(PropertyFileReader.getProperty("mainSiteURL"));
		ContactUSPage contactpage = new ContactUSPage(driver);
		contactpage.fillRequiredDetailsForContact(PropertyFileReader.getProperty("contactName"), PropertyFileReader.getProperty("contactEmail"), PropertyFileReader.getProperty("contactMobile"), PropertyFileReader.getProperty("contatcMessage"));
		contactpage.selectContactType(PropertyFileReader.getProperty("contactType"));
		contactpage.clickSubmitButton();
		contactpage.verifyContactSubmit(PropertyFileReader.getProperty("contactSubmitMessage"));
	}
	
	@Test
	public void checkContactUsPageValidations() throws InterruptedException, IOException
	{
		 
		homepage = new MyMoneyKartHomePage(driver);
		homepage.navigateToContactUsPage(PropertyFileReader.getProperty("mainSiteURL"));
		
		ContactUSPage contactpage = new ContactUSPage(driver);
		contactpage.fillRequiredDetailsForContact("", "","", PropertyFileReader.getProperty("contatcMessage"));
		contactpage.selectContactType(PropertyFileReader.getProperty("contactType"));
		contactpage.clickSubmitButton();
		contactpage.verifyContactSubmit(PropertyFileReader.getProperty("contactSubmitMessage"));
	}
	
	@Test
	public void inviteFriends() throws InterruptedException, IOException
	{
		
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.doUserLogin(TestDataComman.username, TestDataComman.password);
		homepage.navigateToInviteFriendPage(PropertyFileReader.getProperty("mainSiteURL"));
		InviteFriendPage invitefriend = new InviteFriendPage(driver);
		invitefriend.fillFriendsDetail(PropertyFileReader.getProperty("friendName").split(","), PropertyFileReader.getProperty("friendNumber").split(","), 3);
		invitefriend.clickSubmitButton();
		invitefriend.verifyFriendInvited(PropertyFileReader.getProperty("inviteFriendMessage"));		
	}
	
	@Test
	public void navigateToUserAccountScbscriptionLink() throws InterruptedException, IOException
	{
		 
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateAccountSubscription(PropertyFileReader.getProperty("mainSiteURL"));		
	}
	
	@Test
	public void navigateToUserAccountScbscriptionLinkAndCheckScbscriptionStatus() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateAccountSubscription(PropertyFileReader.getProperty("mainSiteURL"));
		AccountSubscriptionPage subscription  = new AccountSubscriptionPage(driver);
		subscription.checkAccountScbscriptionStatus();
	}

	@Test
	public void failSubscriptionFeePaymentFromPG() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateAccountSubscription(PropertyFileReader.getProperty("mainSiteURL"));
		AccountSubscriptionPage subscription  = new AccountSubscriptionPage(driver);
		subscription.checkAccountScbscriptionStatus();
		subscription.cancelPaymentFromPaymentGateway();
		subscription.verifyPaymentStatus();
	}
	
	@Test
	public void navigateToKycDetailsPageAndVerifyCorrectRedirect() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.verifyKYCPageRedirect();
	}
	
	@Test
	public void navigateKYCPageAndGetDetails() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.getUserKYCDetails();
	}
	
	@Test
	public void verifyPancardAndIdProffDocOnKycForm() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.verifyUploadedDocument();
	}
	
	@Test
	public void navigateKYCPageAndCheckMissingKYCDetails() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.checkMissingKYCInfo();
	}
	
	@Test
	public void navigateKYCPageAndTransferTheKYC() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.kycTransfer();
	}
	
	@Test
	public void navigateKYCPageAndCancelClick() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToKYCDetails(PropertyFileReader.getProperty("mainSiteURL"));
		UserKYCDetailsPage kyc  = new UserKYCDetailsPage(driver);
		kyc.cancelOptionOfKYCForm();
	}
	
	@Test
	public void navigateToChangePasswordPage() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToChangePassword(PropertyFileReader.getProperty("mainSiteURL"));
		 
	}
	
	@Test
	public void navigateToChangePasswordPageAndCheckBlankFieldValidation() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToChangePassword(PropertyFileReader.getProperty("mainSiteURL"));
		ChangePasswordPage chpass= new ChangePasswordPage(driver);
		chpass.validationCheck();
	}
	
	@Test
	public void navigateToChangePasswordPageAnddoChangePassword() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToChangePassword(PropertyFileReader.getProperty("mainSiteURL"));
		ChangePasswordPage chpass= new ChangePasswordPage(driver);
		chpass.changePassword(PropertyFileReader.getProperty("oldpass"), PropertyFileReader.getProperty("newpass"));
	}
	
	@Test
	public void navigateToChangePasswordPageAndCheckCancelRedirect() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToChangePassword(PropertyFileReader.getProperty("mainSiteURL"));
		ChangePasswordPage chpass= new ChangePasswordPage(driver);
		chpass.cancelFunctionOFChangePassword();
	}
	
	@Test
	public void navigateToUserProfile() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToUserProfile(PropertyFileReader.getProperty("mainSiteURL"));

	}
	@Test
	public void navigateToUserProfileAndCheckValidRedirect() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToUserProfile(PropertyFileReader.getProperty("mainSiteURL"));
		
		UserProfilePage profile = new UserProfilePage(driver);
		profile.verifyUserProfilePageRedirect();

	}
	
	@Test
	public void navigateToUserProfileAndGetProfileDetails() throws InterruptedException, IOException
	{
		homepage = new MyMoneyKartHomePage(driver);	
		homepage.navigateToUserProfile(PropertyFileReader.getProperty("mainSiteURL"));
		
		UserProfilePage profile = new UserProfilePage(driver);
		profile.getUserProfileDetails();

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
