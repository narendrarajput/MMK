package com.executer;

import org.testng.annotations.Test;
 





import com.mmk.bus.BusListingUserLogin;
import com.mmk.bus.BusSearchFromHomePage;

import pom.utils.DriverSetup;
import pom.utils.TestDataComman;

public class BusSuitExecuter extends DriverSetup
{
	
	@Test(priority=1)
	public void busSearch()
	{
		BusSearchFromHomePage bussearch = new BusSearchFromHomePage(driver);
		bussearch.doBusSearch(TestDataComman.sourceCity, TestDataComman.destinationCity, TestDataComman.journecyDate);
		
	}
	
	@Test(priority=2)
	public void busListPageLogin() throws InterruptedException
	{
		BusListingUserLogin buslist = new BusListingUserLogin(driver);
		buslist.listUserLogin();
	}
}
