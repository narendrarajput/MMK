package com.mmk.testexecuter;

import java.io.IOException;

import mmk.comman.pages.CheckoutUsingWallet;
import mmk.comman.pages.SocialSharing;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.mmk.bus.BusHomePage;
import com.mmk.bussite.BusBookingThankyouPage;
import com.mmk.bussite.BusListingUserLoginAndSelectSeat;
import com.mmk.bussite.BusSearchFromHomePage;
import com.mmk.bussite.PassangerDetailPage;
import com.mmk.commonutils.BusSiteTestData;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.driversetup.DriverSetup;
import com.mmk.reader.LogWriter;

public class MMKBusSuitExecuter extends DriverSetup
{
	
	BusListingUserLoginAndSelectSeat buslist;
	BusBookingThankyouPage thankyou;
	Boolean result;
	
	@Test
	public void busSearch() throws InterruptedException, IOException
	{
		BusSearchFromHomePage bussearch = new BusSearchFromHomePage(driver);
		bussearch.doBusSearch(TestDataComman.sourceCity, TestDataComman.destinationCity, TestDataComman.journecyDate);
		
	}
	
	@Test
	public void busListPageLogin() throws InterruptedException, IOException
	{
		buslist = new BusListingUserLoginAndSelectSeat(driver);
		buslist.listUserLogin();
	}
	@Test
	public void selectABusSeat() throws IOException 
	{
		buslist.selectSeat();
	}
	
	@Test
	public void fillPassangerDetails() throws IOException 
	{
		 PassangerDetailPage pdetails = new PassangerDetailPage(driver);
		 pdetails.fillPassengerDetailAndProceedToPay(TestDataComman.pFirstName, TestDataComman.pLastName, TestDataComman.pGender, TestDataComman.pAge, TestDataComman.pID, TestDataComman.pIDNumber, TestDataComman.username);
	}
	
	@Test
	public void walletCheckout() throws IOException 
	{
		CheckoutUsingWallet check = new CheckoutUsingWallet(driver);
		check.doCheckout();
	}
	
	@Test
	public void ticketBookingStatus() throws IOException 
	{
		thankyou = new BusBookingThankyouPage(driver);
		result = thankyou.checkBooking();
		if(result==true)
		{
			//thankyou.checkSMS();
			thankyou.checkEmail();
		
			
		}
	}
	@Test
	public void checkSocialSharingAndSkipToHomePage() throws IOException
	{
		if(result==true)
		{			
			SocialSharing ss= new SocialSharing(driver);
			
			ss.facebookSharing();
			ss.twitterSharing();
			
			thankyou.ratingOption();

		}
		else
		{
			LogWriter.logger.info("There is some issue on Bus thank you page");
		}
	}
	
	@Test
	public void getUserReacentSearchHistory() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.getRecentSearchData();
		
	}
	
	@Test
	public void userLoginFromBusHomePage() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.busUserLoginCheck();
		
	}
	
	@Test
	public void getUserPreferredCity() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.getUserPreferredCity();
		
	}
	
	@Test
	public void setUserPreferredCity() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.setUserPreferredCity();
		
	}
	
	@Test
	public void getBusOperatorRating() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.getRatings();
		
	}
	
	@Test
	public void submitBusOperatorRating() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.submitRating();
		
	}
	
	@Test
	public void busSearchFromBusHomePage() throws InterruptedException, IOException
	{
		BusHomePage bushome = new BusHomePage(driver);
		bushome.doBusSearch(BusSiteTestData.sourceCity, BusSiteTestData.destinationCity, BusSiteTestData.journecyDate);
		
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
