package com.mmk.test_execution_engine;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mmk.common.pages.CouponCodeAndWalletAmoutSelectionPage;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.busproject.pages.BusHomePage;
import com.busproject.pages.BusListingPage;
import com.busproject.pages.BusThankyouPage;
import com.busproject.pages.PassangerDetailsPage;
import com.mmk.driversetup.DriverSetup;
import com.mmk.reader.PropertyFileReader;
import com.mmkproject.pages.MyMoneyKartHomePage;
 

public class MMKBusSiteTestCases  extends DriverSetup 
{
	BusHomePage bushome;
	
	static Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
	static String jDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	
	@Test
	public void navigateToBusHomePage() throws InterruptedException, IOException
	{
		
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	 
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
	}
	
	@Test
	public void userLoginCheckAndLoginIfNot() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
	}
	
	@Test
	public void getSelectedPreferredCity() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
	//	AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.getUserPreferredCity();
	}
	
	@Test
	public void setPreferredCity() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
	//	AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.setUserPreferredCity(PropertyFileReader.getProperty("preferredcity"));
	}
	
	@Test
	public void checkBusRatingOptionAvailability() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.getRatings(PropertyFileReader.getProperty("busSiteURL"));
	}
	
	@Test
	public void rateABusIfRatingOptionAvailable() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.submitRating(PropertyFileReader.getProperty("busSiteURL"));
	}
	
	@Test
	public void getRecentSearches() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.getRecentSearchData(PropertyFileReader.getProperty("busSiteURL"));
	}
	
	@Test
	public void doBusSearch() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		//bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
	}
	
	@Test
	public void modifySearchCheck() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("busSiteURL"), driver.getCurrentUrl());
		//bushome.busUserLoginCheck(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"), PropertyFileReader.getProperty("usersname"), PropertyFileReader.getProperty("busSiteURL"));
		//AssertJUnit.assertEquals(PropertyFileReader.getProperty("usersname"),bushome.usersname.get(0).getText());
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		bustlist.modifySearch(PropertyFileReader.getProperty("destinationcity"), PropertyFileReader.getProperty("sourcecity"), "2017-07-09");
	}
	
	@Test
	public void selectSeatAndProceed() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		if(result)
		{
			bustlist.selectSeat();
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
		}
	}
	
	@Test
	public void isSelfFunctionalityCheck() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		PassangerDetailsPage passdetails = new PassangerDetailsPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		if(result)
		{
			bustlist.selectSeat();			
			passdetails.checkIsSelfOption();
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
			passdetails.checkIsSelfOption();
		}
	}
	
	@Test
	public void fillPassangerDetailsAndProceedFurther() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		PassangerDetailsPage passdetails = new PassangerDetailsPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		if(result)
		{
			bustlist.selectSeat();			
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"),PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"), PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
		}
	}
	
	@Test
	public void applyCouponCode() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		PassangerDetailsPage passdetails = new PassangerDetailsPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		
		CouponCodeAndWalletAmoutSelectionPage wallet = new CouponCodeAndWalletAmoutSelectionPage(driver);
		if(result)
		{
			bustlist.selectSeat();			
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"),PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"), PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
		}
	}
	@Test
	public void applyWalletAmountAndCheckout() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		PassangerDetailsPage passdetails = new PassangerDetailsPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		
		CouponCodeAndWalletAmoutSelectionPage wallet = new CouponCodeAndWalletAmoutSelectionPage(driver);
		if(result)
		{
			bustlist.selectSeat();			
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"),PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
			wallet.getAvailableWalletAmount();
			wallet.selectWalletAmount();
			wallet.proceedCheckout();
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"), PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
			wallet.getAvailableWalletAmount();
			wallet.selectWalletAmount();
			wallet.proceedCheckout();
		}
	}
	
	@Test
	public void completeBusBookingAndOrderCheckout() throws IOException, InterruptedException
	{
		bushome = new BusHomePage(driver);
		bushome.navigateToBusHomePage(PropertyFileReader.getProperty("busSiteURL"));	
		bushome.doBusSearch(PropertyFileReader.getProperty("sourcecity"), PropertyFileReader.getProperty("destinationcity"), jDate, PropertyFileReader.getProperty("busSiteURL"));
		BusListingPage bustlist = new BusListingPage(driver);
		PassangerDetailsPage passdetails = new PassangerDetailsPage(driver);
		boolean result = bustlist.isUserAlreadyLogin();
		
		CouponCodeAndWalletAmoutSelectionPage wallet = new CouponCodeAndWalletAmoutSelectionPage(driver);
		BusThankyouPage thankyou = new  BusThankyouPage(driver);
		if(result)
		{
			bustlist.selectSeat();			
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"),PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
			wallet.getAvailableWalletAmount();
			wallet.selectWalletAmount();
			wallet.proceedCheckout();
			thankyou.verifyBookingDetails();
			thankyou.checkSMS();
			thankyou.checkEmail();
			thankyou.facebookSharing();
			thankyou.twitterSharing();
			thankyou.ratingOption();
		}
		else
		{
			new MyMoneyKartHomePage(driver).doUserLogin(PropertyFileReader.getProperty("username"), PropertyFileReader.getProperty("password"));
			bustlist.selectSeat();
			passdetails.fillPassengerDetails(PropertyFileReader.getProperty("firstname"), PropertyFileReader.getProperty("lastname"), PropertyFileReader.getProperty("gender"), PropertyFileReader.getProperty("age"), PropertyFileReader.getProperty("idproof"), PropertyFileReader.getProperty("idnumber"), PropertyFileReader.getProperty("emergencynumber"));
			passdetails.clickProceedToPayButtonAndAcceptConfirmations();
			wallet.applyCoupon(PropertyFileReader.getProperty("couponcode"));
			wallet.getAvailableWalletAmount();
			wallet.selectWalletAmount();
			wallet.proceedCheckout();
			thankyou.verifyBookingDetails();
			thankyou.checkSMS();
			thankyou.checkEmail();
			thankyou.facebookSharing();
			thankyou.twitterSharing();
			thankyou.ratingOption();
		}
	}
}
