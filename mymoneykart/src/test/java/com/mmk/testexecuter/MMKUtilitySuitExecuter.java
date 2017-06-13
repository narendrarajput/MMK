package com.mmk.testexecuter;

import mmk.comman.pages.SocialSharing;

import org.testng.annotations.Test;

import com.mmk.bussite.BusSearchFromHomePage;
import com.mmk.bussite.WalletCheckOutPage;
import com.mmk.commonutils.TestDataComman;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.driversetup.DriverSetup;
import com.mmk.utility.DTHRecharge;
import com.mmk.utility.MobileRecharge;
import com.mmk.utility.RechargeThankyouPage;
import com.mmk.utility.UtilityCheckUserLogin;

public class MMKUtilitySuitExecuter extends DriverSetup 
{
	MobileRecharge recharge ;
	RechargeThankyouPage thankyou;
	WalletCheckOutPage wallet;
	DTHRecharge dth;
	UtilityCheckUserLogin login;
	
	@Test
	public void prepaidMobileRecharge() throws InterruptedException
	{
		recharge = new MobileRecharge(driver);
		recharge.prepaidRecharge(UtilitySiteTestData.preMobileNumber, UtilitySiteTestData.preOperator, UtilitySiteTestData.preCircle, UtilitySiteTestData.preRechargeAmount);
				
		login = new UtilityCheckUserLogin(driver);
		login.checkUserLogin();
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
		
		SocialSharing ss= new SocialSharing(driver);
		ss.facebookSharing();
		ss.twitterSharing();
		
	}
	
	@Test
	public void postpaidMobileRecharge() throws InterruptedException
	{
		recharge = new MobileRecharge(driver);
		recharge.postpaidRecharge(UtilitySiteTestData.postMobileNumber, UtilitySiteTestData.postOperator, UtilitySiteTestData.postCircle, UtilitySiteTestData.postRechargeAmount);
		
		login = new UtilityCheckUserLogin(driver);
		login.checkUserLogin();	
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
		
		SocialSharing ss= new SocialSharing(driver);
		ss.facebookSharing();
		ss.twitterSharing();
	}
	
	@Test
	public void dthRecharge() throws InterruptedException
	{
		dth = new DTHRecharge(driver);
		dth.dthRecharge(UtilitySiteTestData.dthSubscriberID, UtilitySiteTestData.dthOperator,  UtilitySiteTestData.dthRechargeAmount);
		
		login = new UtilityCheckUserLogin(driver);
		login.checkUserLogin();
		
		wallet = new WalletCheckOutPage(driver);
		wallet.doCheckout();
		
		thankyou = new RechargeThankyouPage(driver);
		thankyou.rechargeStatus();
		
		SocialSharing ss= new SocialSharing(driver);
		ss.facebookSharing();
		ss.twitterSharing();
	}
}
