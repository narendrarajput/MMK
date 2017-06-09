package com.mmk.testexecuter;

import org.testng.annotations.Test;

import com.mmk.bussite.BusSearchFromHomePage;
import com.mmk.bussite.WalletCheckOutPage;
import com.mmk.commonutils.TestDataComman;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.driversetup.DriverSetup;
import com.mmk.utility.DTHRecharge;
import com.mmk.utility.MobileRecharge;
import com.mmk.utility.RechargeThankyouPage;

public class MMKUtilitySuitExecuter extends DriverSetup 
{
	MobileRecharge recharge ;
	RechargeThankyouPage thankyou;
	WalletCheckOutPage wallet;
	DTHRecharge dth;
	
	@Test
	public void prepaidMobileRecharge() throws InterruptedException
	{
		recharge = new MobileRecharge(driver);
		recharge.prepaidRecharge(UtilitySiteTestData.preMobileNumber, UtilitySiteTestData.preOperator, UtilitySiteTestData.preCircle, UtilitySiteTestData.preRechargeAmount);
		recharge.checkUserLogin();
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
		
	}
	
	@Test
	public void postpaidMobileRecharge() throws InterruptedException
	{
		recharge = new MobileRecharge(driver);
		recharge.postpaidRecharge(UtilitySiteTestData.postMobileNumber, UtilitySiteTestData.postOperator, UtilitySiteTestData.postCircle, UtilitySiteTestData.postRechargeAmount);
		
		recharge.checkUserLogin();
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
	}
	
	@Test
	public void dthRecharge() throws InterruptedException
	{
		dth = new DTHRecharge(driver);
		dth.dthRecharge(UtilitySiteTestData.dthSubscriberID, UtilitySiteTestData.dthOperator,  UtilitySiteTestData.dthRechargeAmount);
		recharge = new MobileRecharge(driver);
		recharge.checkUserLogin();
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
	}
}
