package com.mmk.test_execution_engine;

import java.io.IOException;

import mmk.common.pages.CouponCodeAndWalletAmoutSelectionPage;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import mmk.common.pages.TransactionThankyouPage;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.driversetup.DriverSetup;
import com.mmk.reader.LogWriter;
import com.mmk.reader.PropertyFileReader;
import com.mmkproject.pages.MyMoneyKartHomePage;
import com.utilityproject.pages.RechargeHomePage;
import com.utilityproject.pages.RechargeThankyouPage;

public class MMKUtilitySiteTestCases extends DriverSetup 
{
	RechargeHomePage rechargeHome;
	Boolean result;
	
	@Test
	public void navigateAndValidateUtilitySiteAndValidateHomePage() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct page");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
	}
	
	@Test
	public void navigateToUtilityHomePageAndCheckWeatherUserIsLogigedIn() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct page");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 rechargeHome.verifyUserLoginInSystem();
	}
	
	@Test
	public void proceedWithPrepaidMobilerechargeWithWrongDetails() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct Utility HomePage");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 	rechargeHome.fillMobileRechargeDetails(PropertyFileReader.getProperty("prepaidRecharge"), "9422307801", PropertyFileReader.getProperty("operator"), PropertyFileReader.getProperty("circle"), PropertyFileReader.getProperty("amount"), PropertyFileReader.getProperty("utilitySiteURL"));
		 	rechargeHome.proceedRecharge();
		 	result =rechargeHome.verifyUserLoginInSystem();
		 	if(!(result))
		 	{
		 		MyMoneyKartHomePage mmkhome = new MyMoneyKartHomePage(driver);
		 		mmkhome.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("User already loggedin in the system");
		 	}
		 	CouponCodeAndWalletAmoutSelectionPage cppage = new CouponCodeAndWalletAmoutSelectionPage(driver);
		 	if(cppage.isLandingPageIsWalletSelectionPage())
		 	{
		 		cppage.getAvailableWalletAmount();
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("There is some issue for proceed further..");
		 	}
	}
	@Test
	public void proceedWithPrepaidMobilerecharge() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct Utility HomePage");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 	rechargeHome.fillMobileRechargeDetails(PropertyFileReader.getProperty("prepaidRecharge"), PropertyFileReader.getProperty("mobileNumber"), PropertyFileReader.getProperty("operator"), PropertyFileReader.getProperty("circle"), PropertyFileReader.getProperty("amount"), PropertyFileReader.getProperty("utilitySiteURL"));
		 	rechargeHome.proceedRecharge();
		 	result =rechargeHome.verifyUserLoginInSystem();
		 	if(!(result))
		 	{
		 		MyMoneyKartHomePage mmkhome = new MyMoneyKartHomePage(driver);
		 		mmkhome.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("User already loggedin in the system");
		 	}
		 	CouponCodeAndWalletAmoutSelectionPage cppage = new CouponCodeAndWalletAmoutSelectionPage(driver);
		 	if(cppage.isLandingPageIsWalletSelectionPage())
		 	{
		 		cppage.getAvailableWalletAmount();
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("There is some issue for proceed further..");
		 	}
	}
	
	@Test
	public void proceedRechargeAndApplyCoupon() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct Utility HomePage");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 	rechargeHome.fillMobileRechargeDetails(PropertyFileReader.getProperty("prepaidRecharge"), PropertyFileReader.getProperty("mobileNumber"), PropertyFileReader.getProperty("operator"), PropertyFileReader.getProperty("circle"), PropertyFileReader.getProperty("amount"), PropertyFileReader.getProperty("utilitySiteURL"));
		 	rechargeHome.proceedRecharge();
		 	result =rechargeHome.verifyUserLoginInSystem();
		 	if(!(result))
		 	{
		 		MyMoneyKartHomePage mmkhome = new MyMoneyKartHomePage(driver);
		 		mmkhome.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("User already loggedin in the system");
		 	}
		 	CouponCodeAndWalletAmoutSelectionPage cppage = new CouponCodeAndWalletAmoutSelectionPage(driver);
		 	if(cppage.isLandingPageIsWalletSelectionPage())
		 	{
		 		cppage.getAvailableWalletAmount();
		 		cppage.applyCoupon(PropertyFileReader.getProperty("couponcode"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("There is some issue for proceed further..");
		 	}
	}
	
	@Test
	public void proceedPrepaidMobileRecharge() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct Utility HomePage");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 	rechargeHome.fillMobileRechargeDetails(PropertyFileReader.getProperty("prepaidRecharge"), PropertyFileReader.getProperty("mobileNumber"), PropertyFileReader.getProperty("operator"), PropertyFileReader.getProperty("circle"), PropertyFileReader.getProperty("amount"), PropertyFileReader.getProperty("utilitySiteURL"));
		 	rechargeHome.proceedRecharge();
		 	result =rechargeHome.verifyUserLoginInSystem();
		 	if(!(result))
		 	{
		 		MyMoneyKartHomePage mmkhome = new MyMoneyKartHomePage(driver);
		 		mmkhome.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("User already loggedin in the system");
		 	}
		 	CouponCodeAndWalletAmoutSelectionPage cppage = new CouponCodeAndWalletAmoutSelectionPage(driver);
		 	if(cppage.isLandingPageIsWalletSelectionPage())
		 	{
		 		cppage.getAvailableWalletAmount();
		 		cppage.selectWalletAmount();
		 		cppage.proceedCheckout();
		 		cppage.verifyCheckoutPage();
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("There is some issue for proceed further..");
		 	}
	}
	
	@Test
	public void checkRechargeStatusOnRechargeThankyouPage() throws InterruptedException, IOException
	{
		 rechargeHome = new RechargeHomePage(driver);
		 rechargeHome.navigateToUtilitySiteHomePage(PropertyFileReader.getProperty("utilitySiteURL"));
		 result = rechargeHome.verifyHomePageNavigation(PropertyFileReader.getProperty("utilitySiteURL"));
		 if(result)			 
		 {
			 LogWriter.logger.info("Navigating to correct Utility HomePage");
		 }
		 else
		 {
			 LogWriter.logger.info("Unable to navigate on Utility site");
		 }
		 	rechargeHome.fillMobileRechargeDetails(PropertyFileReader.getProperty("prepaidRecharge"), PropertyFileReader.getProperty("mobileNumber"), PropertyFileReader.getProperty("operator"), PropertyFileReader.getProperty("circle"), PropertyFileReader.getProperty("amount"), PropertyFileReader.getProperty("utilitySiteURL"));
		 	rechargeHome.proceedRecharge();
		 	result =rechargeHome.verifyUserLoginInSystem();
		 	if(!(result))
		 	{
		 		MyMoneyKartHomePage mmkhome = new MyMoneyKartHomePage(driver);
		 		mmkhome.doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("User already loggedin in the system");
		 	}
		 	CouponCodeAndWalletAmoutSelectionPage cppage = new CouponCodeAndWalletAmoutSelectionPage(driver);
		 	if(cppage.isLandingPageIsWalletSelectionPage())
		 	{
		 		cppage.getAvailableWalletAmount();
		 		cppage.selectWalletAmount();
		 		cppage.proceedCheckout();
		 		cppage.verifyCheckoutPage();
		 		TransactionThankyouPage thank = new TransactionThankyouPage(driver);
		 		thank.verifyRechargeStatus();
		 		
		 	}
		 	else
		 	{
		 		 LogWriter.logger.info("There is some issue for proceed further..");
		 	}
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
