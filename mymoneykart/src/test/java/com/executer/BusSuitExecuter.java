package com.executer;

import org.testng.annotations.Test;
 


import com.mmk.bus.BusSearchFromHomePage;

import pom.utils.DriverSetup;

public class BusSuitExecuter extends DriverSetup
{
	
	@Test
	public void busSearch()
	{
		BusSearchFromHomePage bussearch = new BusSearchFromHomePage(driver);
		bussearch.doBusSearch("Chennai", "Hyderabad", "2017-05-25");
		
	}
}
