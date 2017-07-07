package com.mmkproject.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;

public class AccountSubscriptionPage 
{

	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;

	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//h3")
	public WebElement subscriptionMessage;
	
	@FindBy(id = "btnPay")
	public List<WebElement> payButton;
	
	@FindBy(xpath = "//div[@class='col-sm-12 divPersonalDetails accountDivMain']//div[@class='col-md-6 form-group']")
	public List<WebElement> paymentPendingDetails;
	
	@FindBy(id = "goBack")
	public  WebElement pgCancelButton;
	
	@FindBy(xpath = ".//*[@id='CancelTab']/div[1]/div/div[1]/label/input")
	public WebElement pgcancelReason;
	
	@FindBy(id = "fdbCancel")
	public  WebElement pgabortTransactionButton;
	
	@FindBy(xpath = "//h2 | //h3")
	public  List<WebElement> paymentStatus;
	
	
	
	
	
	WebDriver driver;
	
	public AccountSubscriptionPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void checkAccountScbscriptionStatus()
	{
			if(payButton.size()>0)
			{
				LogWriter.logger.info("User payment details are pending. Below are information :");
				for(WebElement e : paymentPendingDetails)
				{
					LogWriter.logger.info(e.getText());
				}
				
			}
			else
			{
				LogWriter.logger.info("Payment is done and Details are :"+subscriptionMessage.getText());
			}
	}
	
		public void cancelPaymentFromPaymentGateway() throws InterruptedException
		{
			if(payButton.size()>0)
			{
				payButton.get(0).click();
//				
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				Common.wait.until(ExpectedConditions.elementToBeClickable(pgCancelButton));
				pgCancelButton.click();
			
				Common.wait.until(ExpectedConditions.elementToBeClickable(pgcancelReason));
				pgcancelReason.click();
				
				Common.wait.until(ExpectedConditions.elementToBeClickable(pgabortTransactionButton));
				pgabortTransactionButton.click();
	
			}
			else
			{
				LogWriter.logger.info("Payment is already done ");
			}
			
		}
		
		public void verifyPaymentStatus()
		{
			for(WebElement e: paymentStatus)
			{
				LogWriter.logger.info(e.getText());
			}
		}
	
}
