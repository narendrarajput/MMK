package com.mmk.testexecuter;

import mmk.comman.pages.SocialSharing;

import org.testng.annotations.Test;

import com.mmk.bussite.BusBookingThankyouPage;
import com.mmk.bussite.BusListingUserLoginAndSelectSeat;
import com.mmk.bussite.BusSearchFromHomePage;
import com.mmk.bussite.PassangerDetailPage;
import com.mmk.bussite.WalletCheckOutPage;
import com.mmk.commonutils.TestDataComman;
import com.mmk.driversetup.DriverSetup;

public class MMKBusSuitExecuter extends DriverSetup
{
	
	BusListingUserLoginAndSelectSeat buslist;
	
	@Test
	public void busSearch()
	{
		BusSearchFromHomePage bussearch = new BusSearchFromHomePage(driver);
		bussearch.doBusSearch(TestDataComman.sourceCity, TestDataComman.destinationCity, TestDataComman.journecyDate);
		
	}
	
	@Test
	public void busListPageLogin() throws InterruptedException
	{
		buslist = new BusListingUserLoginAndSelectSeat(driver);
		buslist.listUserLogin();
	}
	@Test
	public void selectABusSeat() 
	{
		buslist.selectSeat();
	}
	
	@Test
	public void fillPassangerDetails() 
	{
		 PassangerDetailPage pdetails = new PassangerDetailPage(driver);
		 pdetails.fillPassengerDetailAndProceedToPay(TestDataComman.pFirstName, TestDataComman.pLastName, TestDataComman.pGender, TestDataComman.pAge, TestDataComman.pID, TestDataComman.pIDNumber, TestDataComman.username);
	}
	
	@Test
	public void enterWalletAndCheckout() 
	{
		WalletCheckOutPage check = new WalletCheckOutPage(driver);
		check.doCheckout();
	}
	
	@Test
	public void ticketBookingStatus() 
	{
		BusBookingThankyouPage thankyou = new BusBookingThankyouPage(driver);
		Boolean result = thankyou.checkBooking();
		if(result==true)
		{
			thankyou.checkSMS();
			thankyou.checkEmail();
			
			
			SocialSharing ss= new SocialSharing(driver);
			
			ss.facebookSharing();
			ss.twitterSharing();
			
			thankyou.ratingOption();
		}
	}
}
