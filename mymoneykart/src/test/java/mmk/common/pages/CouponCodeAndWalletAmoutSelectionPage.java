package mmk.common.pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

public class CouponCodeAndWalletAmoutSelectionPage {

	@FindBy(id = "divLoader")
	WebElement loader;
	
	
	@FindBy(id = "lblWalletAmount")
	WebElement availableWalletBalance;
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
	
	@FindBy(id = "ResultBox")
	public WebElement NotificationMessage;
	
	@FindBy(id = "CouponCode")
	public WebElement couponCodeField;
	
	@FindBy(id = "btnCouponApply")
	public WebElement applyCouponButton;
	
	@FindBy(id = "btnRemoveCoupon")
	public WebElement removeCouponButton;
	
	@FindBy(id = "EMsgCouponCode")
	public WebElement validationMessage;
 
	
	@FindBy(id = "spinnerCoupon")
	public WebElement spinner;
 
	WebDriver driver;
	
	public CouponCodeAndWalletAmoutSelectionPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	
	public void getAvailableWalletAmount()
	{
		Common.wait.until(ExpectedConditions.visibilityOf(walletAmount));
		LogWriter.logger.info("Order  Amount is : "+ payableAmount.getText()); 
		LogWriter.logger.info("Available Wallet Amount is : "+ availableWalletBalance.getText());
	}
	
	public void selectWalletAmount() throws IOException
	{

		
			Common.wait.until(ExpectedConditions.visibilityOf(walletAmount));
			TakeScreenshot.passedScreenShot();
			walletAmount.clear();
			walletAmount.sendKeys(payableAmount.getText());
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Payment Amount has been selected");
			TakeScreenshot.passedScreenShot();
			

	}
	public void proceedCheckout() throws IOException
	{
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		TakeScreenshot.passedScreenShot();
		payButton.click();
		LogWriter.logger.info("Wallet Payment Started.........");
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));
		LogWriter.logger.info(NotificationMessage.getText());		
		LogWriter.logger.info("Payment Done.");
		TakeScreenshot.passedScreenShot();
	}
	
	public void verifyCheckoutPage() throws IOException
	{
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));	
		if(driver.getCurrentUrl().contains("ThankYou"))
		{
			LogWriter.logger.info("Moved To Thankyou page");
			TakeScreenshot.passedScreenShot();
		}
		else
		{
			LogWriter.logger.info("Not On Thankyou page");
			TakeScreenshot.passedScreenShot();
		}
	}
	
	public void applyCoupon(String couponCode) throws IOException
	{
		Common.wait.until(ExpectedConditions.invisibilityOf(loader));		
		if(driver.getCurrentUrl().contains("Payment"))
		{
			TakeScreenshot.passedScreenShot();
			couponcode.sendKeys(couponCode);
			applyCouponButton.click();
			Common.wait.until(ExpectedConditions.invisibilityOf(spinner));
			if(NotificationMessage.getText().length()>0)
			{
				LogWriter.logger.info(NotificationMessage.getText());
				TakeScreenshot.passedScreenShot();
			}
			else if(removeCouponButton.isDisplayed())
			{
				LogWriter.logger.info("Coupon code Applied");
				TakeScreenshot.passedScreenShot();
			}
			else if(validationMessage.getText().length()>0)
			{
				LogWriter.logger.info(validationMessage.getText());
				TakeScreenshot.passedScreenShot();
			}	
			
		}
		else
		{
			LogWriter.logger.info("You are not On Coupon Page");
		}
	}
	
	public boolean isLandingPageIsWalletSelectionPage() throws IOException
	{
		try
		{
			Common.wait.until(ExpectedConditions.visibilityOf(walletAmount));
		}
		catch(Exception e)
		{
			LogWriter.logger.info("Message"+NotificationMessage.getText());
			TakeScreenshot.passedScreenShot();
		}
		if(driver.getCurrentUrl().contains("Payment"))
		{
			LogWriter.logger.info("You are On Coupon Page URL : "+driver.getCurrentUrl());
			TakeScreenshot.passedScreenShot();
			return true;
			
		}
		else
		{
			LogWriter.logger.info("You are not on Coupon Page URL : "+driver.getCurrentUrl());
			return false;
		}
	}
}
