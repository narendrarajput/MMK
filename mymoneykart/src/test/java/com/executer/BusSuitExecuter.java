package com.executer;

import org.testng.annotations.Test;
 







import com.mmk.bus.BusListingUserLogin;
import com.mmk.bus.BusSearchFromHomePage;
import com.mmk.bus.PassangerDetailPage;
import com.mmk.bus.WalletCheckOutPage;

import pom.utils.DriverSetup;
import pom.utils.TestDataComman;

public class BusSuitExecuter extends DriverSetup
{
	
	BusListingUserLogin buslist;
	@Test(priority=1)
	public void busSearch()
	{
		BusSearchFromHomePage bussearch = new BusSearchFromHomePage(driver);
		bussearch.doBusSearch(TestDataComman.sourceCity, TestDataComman.destinationCity, TestDataComman.journecyDate);
		
	}
	
	@Test(priority=2)
	public void busListPageLogin() throws InterruptedException
	{
		buslist = new BusListingUserLogin(driver);
		buslist.listUserLogin();
	}
	@Test(priority=3)
	public void selectABusSeat() 
	{
		buslist.selectSeat();
	}
	
	@Test(priority = 4)
	public void fillPassangerDetails() 
	{
		 PassangerDetailPage pdetails = new PassangerDetailPage(driver);
		 pdetails.fillPassengerDetailAndProceedToPay(TestDataComman.pFirstName, TestDataComman.pLastName, TestDataComman.pGender, TestDataComman.pAge, TestDataComman.pID, TestDataComman.pIDNumber, TestDataComman.username);
	}
	
	@Test(priority = 5)
	public void enterWalletAndCheckout() 
	{
		WalletCheckOutPage check = new WalletCheckOutPage(driver);
		check.doCheckout();
	}
}
