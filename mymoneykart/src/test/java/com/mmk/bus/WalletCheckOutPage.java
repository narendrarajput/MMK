package com.mmk.bus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.utils.Comman;

public class WalletCheckOutPage {

	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "UsedWalletAmount")
	WebElement walletAmount;
	
	@FindBy(id = "CouponCode")
	WebElement couponcode;
	
	@FindBy(id = "btnCouponApply")
	WebElement couponApplyButton;
	
	@FindBy(id = "btnPayBillSubmit")
	WebElement payButton;
	
	@FindBy(id = "lblTotalAmount")
	WebElement payableAmount;
	
	WebDriver driver;
	
	public WalletCheckOutPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	
	public void doCheckout()
	{
		Comman.wait.until(ExpectedConditions.visibilityOf(walletAmount));
		walletAmount.clear();
		walletAmount.sendKeys(payableAmount.getText());
		payButton.click();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		
	}
	
}
