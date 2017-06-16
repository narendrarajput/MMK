package com.mmk.mainsite;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;

public class AccountSubscription 
{
	@FindBy(xpath ="//li/a[normalize-space()='Account Subscription']")
	public WebElement accountSubscriptionLink;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public List<WebElement> profileLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//h3")
	public WebElement subscriptionMessage;
	
	@FindBy(id = "btnPay")
	public List<WebElement> payButton;
	
	@FindBy(xpath = "//div[@class='col-sm-12 divPersonalDetails accountDivMain']//div[@class='col-md-6 form-group']")
	public List<WebElement> paymentPendingDetails;
	
	WebDriver driver;
	
	public AccountSubscription(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void checkAccountScbscriptionStatus()
	{
		driver.navigate().to(TestDataComman.baseURL);
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		if(profileLink.size()>0)
		{
			Actions action = new Actions(driver);
			action.moveToElement(profileLink.get(0)).clickAndHold(accountSubscriptionLink).click().build().perform();
			LogWriter.logger.info("Account Subscription page Link Clicked");	
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
		else
		{
			LogWriter.logger.info("User may not login. Subscription page link not found");
		}
	}
}
