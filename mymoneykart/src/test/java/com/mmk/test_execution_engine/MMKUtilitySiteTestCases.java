package com.mmk.test_execution_engine;

import java.io.IOException;
 





import mmk.common.pages.CouponCodeAndWalletAmoutSelectionPage;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
 





import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.commonutils.UtilitySiteTestData;
import com.mmk.driversetup.DriverSetup;
import com.mmk.reader.LogWriter;
import com.mmk.reader.PropertyFileReader;
import com.mmkproject.pages.MyMoneyKartHomePage;
import com.utilityproject.pages.DTHRecharge;
import com.utilityproject.pages.MobileRecharge;
import com.utilityproject.pages.RechargeHomePage;
import com.utilityproject.pages.RechargeThankyouPage;
import com.utilityproject.pages.UtilityCheckUserLogin;

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
