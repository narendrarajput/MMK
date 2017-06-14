package mmk.comman.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;

public class CheckoutUsingWallet {

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
	
	@FindBy(id = "ResultBox")
	public WebElement NotificationMessage;
	
	WebDriver driver;
	
	public CheckoutUsingWallet(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	
	public void doCheckout() throws IOException
	{

			Comman.wait.until(ExpectedConditions.visibilityOf(walletAmount));
			TakeScreenshot.passedScreenShot();
			walletAmount.clear();
			walletAmount.sendKeys(payableAmount.getText());
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info("Payment Amount has been selected");
			TakeScreenshot.passedScreenShot();
			payButton.click();
			LogWriter.logger.info("Wallet Payment Started.........");
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			LogWriter.logger.info(NotificationMessage.getText());
			LogWriter.logger.info("Payment Done.");
			TakeScreenshot.passedScreenShot();

	}
	
}
