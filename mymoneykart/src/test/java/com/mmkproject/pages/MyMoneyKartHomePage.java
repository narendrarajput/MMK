package com.mmkproject.pages;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import com.mmk.commonutils.BusSiteTestData;
import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.reader.LogWriter;
import com.mmk.reader.PropertyFileReader;

public class MyMoneyKartHomePage 
{
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(id = "ResultBox")
	WebElement NotificationMessage;
	
	@FindBy(id = "btnCloseModal")
	WebElement loginPopupCloseButton;
	
	@FindBy(id = "txtFromStation")
	WebElement sourceCity;
	
	@FindBy(id = "txtToStation")
	WebElement destinationCity;
	
	@FindBy(id = "txtJourneyDate")
	WebElement journeyDate;
	
	@FindBy(xpath = ".//*[@id='frmPrimarySearch']/button")
	WebElement searchButton;
	
	@FindBy(xpath = "//div[@class='text-travels']/a")
	List<WebElement> busList;
	
	@FindBy(id = "dvListLoader")
	WebElement gridLoader;

	@FindBy(className = "Popupiframe")
	List<WebElement> iframe;

	@FindBy(id = "MobileNo")
	WebElement mobileNumber;
	
	@FindBy(id = "btnLogin")
	WebElement loginButton;
	
	@FindBy(id = "Password")
	WebElement password;
	
	@FindBy(id = "btnSubmit")
	WebElement submitButton;

	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "linkshowwallet")
	WebElement walletButton;		
	
	@FindBy(id = "WalletPartialView")
	WebElement walletPopup;
	
	@FindBy(xpath = "//div[@id='WalletPartialView']//li")
	List<WebElement> walletData;
	
	@FindBy(xpath ="//li/a[normalize-space()='Contact Us']")
	public WebElement contactUsLink;
	
	@FindBy(xpath ="//li/a[normalize-space()='Invite Friends']")
	public WebElement inviteFriendPageLink;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public List<WebElement> profileLink;
	
	@FindBy(xpath ="//li/a[normalize-space()='Account Subscription']")
	public WebElement accountSubscriptionLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'KYC Details')]")
	public WebElement kycDetailsLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'Change Password')]")
	public WebElement changePasswordLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'User Profile')]")
	public WebElement userProfileLink;
	
	@FindBy(xpath ="//li[@class='dropdown']/a[contains(.,'About Us')]")
	public WebElement aboutUsLink;
	
	@FindBy(id ="MobileNoError")
	public WebElement loginValidationsMessages;
	
	
	WebDriver driver;
	
		public MyMoneyKartHomePage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
		
		public void doUserLogin(String username, String pasword) throws IOException
		{	
			LogWriter.logger.info("Under UserLogin Method......");
			if(!(usersname.size()>0))
			{
				// Switch into popup opens in iframe and do login
				Common.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.get(0)));
				LogWriter.logger.info("On User Login Popup..");
				TakeScreenshot.passedScreenShot();
				//Thread.sleep(3000);
				//driver.switchTo().frame(iframe);
				mobileNumber.sendKeys(username);
				Common.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				loginButton.click();
				LogWriter.logger.info("Username Entered...as .."+username);
				Common.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				password.sendKeys(pasword);
				LogWriter.logger.info("Password Entered..");
				submitButton.click();	
				LogWriter.logger.info("Login in proccess...");
				driver.switchTo().defaultContent();
				Common.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				
				//TakeScreenshot.takeScreen("hello", true);
				//System.out.println("User Log in success");			
			//	LogWriter.logger.info("Username is : "+usersname.get(0).getText());
				TakeScreenshot.passedScreenShot();
			}
			else
			{
				LogWriter.logger.info("User Already Login. Username : " + usersname.get(0).getText());
			}

		}
		
		public void checkUserWalletStatus() throws IOException
		{
			LogWriter.logger.info("Under CheckUserWallet Method......");
			if(usersname.size()>0)
			{
				Common.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
				walletButton.click();
				Common.wait.until(ExpectedConditions.visibilityOf(walletPopup));				
				TakeScreenshot.passedScreenShot();
				LogWriter.logger.info("---------Wallet statistics are ---------");
				for(WebElement e: walletData)
				{
					LogWriter.logger.info(e.getText());
				}
			}
			else
			{
				LogWriter.logger.info("Opps..User Not Login !! So Unable to get Wallet details");
			}
		}
		public boolean validateUserLogin(String uname) throws IOException
		{
			AssertJUnit.assertEquals(uname, usersname.get(0).getText());
			TakeScreenshot.passedScreenShot();
			return true;
		}
		public void closeTheLoginPopupIfAvailable() throws IOException, InterruptedException
		{
			LogWriter.logger.info("Under loginPopupClose method.....");
			if(iframe.size()>0)
			{
				driver.switchTo().frame(iframe.get(0));
				Common.wait.until(ExpectedConditions.visibilityOf(loginPopupCloseButton));
	
				Common.jsExecuter.executeScript("arguments[0].click();", loginPopupCloseButton);
				LogWriter.logger.info("Login Popup Closed... ");
				driver.switchTo().defaultContent();
				Thread.sleep(1400);
				TakeScreenshot.passedScreenShot();
			}
			else
			{
				LogWriter.logger.info("There is no frame. User might be already logged in.. ");
			}
		}
		public void busSearchDetailsFillAndGo(String sourcecityName, String destinationcityName, String date) throws IOException
		{
			LogWriter.logger.info("Under busSearch method.....");
			sourceCity.sendKeys(sourcecityName);
			LogWriter.logger.info("Source city entered.....");
			destinationCity.sendKeys(destinationcityName);
			LogWriter.logger.info("Destination city entered.....");
			journeyDate.sendKeys(date);
			LogWriter.logger.info("Journey date selected....");
			TakeScreenshot.passedScreenShot();
			searchButton.click();
			LogWriter.logger.info("Search bus button clicked.....");
		}
		
		public boolean validateBusSearchResultRedirect(String mainURL)
		{
			AssertJUnit.assertNotSame(driver.getCurrentUrl(), mainURL);
			return true;
		}
		
		public void navigateToMMKHomePage(String mainURL) throws IOException
		{
			if(!(driver.getCurrentUrl().equals(mainURL)))
			{
				driver.navigate().to(mainURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated to MMK Homepage");
				TakeScreenshot.passedScreenShot();
			}
		}
		
		public void navigateToContactUsPage(String baseSiteURL) throws IOException, InterruptedException
		{
				LogWriter.logger.info("Under navigateToContactUsPage method.....");
				if(!(iframe.size()>0))
				{
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					TakeScreenshot.passedScreenShot();
					Common.wait.until(ExpectedConditions.visibilityOf(aboutUsLink));
					Actions action = new Actions(driver);
					action.moveToElement(aboutUsLink).clickAndHold(contactUsLink).click().build().perform();
					LogWriter.logger.info("Contact Us page Link Clicked..");
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					AssertJUnit.assertEquals(driver.getCurrentUrl(), PropertyFileReader.getProperty("contactUsPageURL"));
					LogWriter.logger.info("Lands on Contact us page...");
					TakeScreenshot.passedScreenShot();
				}
				else
				{
						TakeScreenshot.passedScreenShot();
						Common.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.get(0)));
						/*driver.switchTo().frame(iframe.get(0));
						Comman.wait.until(ExpectedConditions.visibilityOf(loginPopupCloseButton));*/
						Common.jsExecuter.executeScript("arguments[0].click();", loginPopupCloseButton);
						LogWriter.logger.info("Login popup closed........");
						driver.switchTo().defaultContent();
						Thread.sleep(1400);
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						Common.wait.until(ExpectedConditions.visibilityOf(aboutUsLink));
						TakeScreenshot.passedScreenShot();
						Actions action = new Actions(driver);
						action.moveToElement(aboutUsLink).clickAndHold(contactUsLink).click().build().perform();
						LogWriter.logger.info("Contact Us page Link Clicked..");
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						AssertJUnit.assertEquals(driver.getCurrentUrl(), PropertyFileReader.getProperty("contactUsPageURL"));
						LogWriter.logger.info("Lands on Contact us page...");
						TakeScreenshot.passedScreenShot();
	
				}
			}
		public void navigateToInviteFriendPage(String baseSiteURL) throws IOException, InterruptedException
		{
			LogWriter.logger.info("Under navigateToInviteFriendPage method..");
			if(!(driver.getCurrentUrl().contains(baseSiteURL)))
			{
					driver.navigate().to(baseSiteURL);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated to MMK Homepage..");
					TakeScreenshot.passedScreenShot();
					
					if(usersname.size()>0)
					{
						Common.jsExecuter.executeScript("arguments[0].click();", inviteFriendPageLink);
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						LogWriter.logger.info("Invite Friend link clicked..");
						TakeScreenshot.passedScreenShot();
						AssertJUnit.assertEquals(driver.getCurrentUrl(), PropertyFileReader.getProperty("inviteFriendPageURL"));
					}
					
					else
					{
						LogWriter.logger.info("OOPS user not login so unable to found InviteFriend link...");
					}
			}
			else
			{
				if(usersname.size()>0)
				{
					Common.jsExecuter.executeScript("arguments[0].click();", inviteFriendPageLink);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Invite Friend link clicked..");
					AssertJUnit.assertEquals(driver.getCurrentUrl(), PropertyFileReader.getProperty("inviteFriendPageURL"));
					TakeScreenshot.passedScreenShot();
				}
				
				else
				{
					LogWriter.logger.info("OOPS user not login so unable to found InviteFriend link...");
				}
			}
		}	
		
		public void navigateAccountSubscription(String baseSiteURL) throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().contains(baseSiteURL)))
			{
					driver.navigate().to(baseSiteURL);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated to MMK Homepage");
					TakeScreenshot.passedScreenShot();
					
					if(profileLink.size()>0)
					{
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						Actions action = new Actions(driver);
						action.moveToElement(profileLink.get(0)).clickAndHold(accountSubscriptionLink).click().build().perform();
						LogWriter.logger.info("Account Subscription page Link Clicked");
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					}
					
					else
					{
						LogWriter.logger.info("User Not Login");
					}
			}
			else
			{
				if(profileLink.size()>0)
				{
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					Actions action = new Actions(driver);
					action.moveToElement(profileLink.get(0)).clickAndHold(accountSubscriptionLink).click().build().perform();
					LogWriter.logger.info("Account Subscription page Link Clicked");
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				}
				
				else
				{
					LogWriter.logger.info("User Not Login");
				}
			}
		}	
		
		public void navigateToKYCDetails(String baseSiteURL) throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().contains(baseSiteURL)))
			{
					driver.navigate().to(baseSiteURL);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated to MMK Homepage");
					TakeScreenshot.passedScreenShot();
					
					if(profileLink.size()>0)
					{
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						Actions action = new Actions(driver);
						action.moveToElement(profileLink.get(0)).clickAndHold(kycDetailsLink).click().build().perform();
						LogWriter.logger.info("KYC Detail page Link Clicked");
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					}
					
					else
					{
						LogWriter.logger.info("User Not Login");
					}
			}
			else
			{
				if(profileLink.size()>0)
				{
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					
					Actions action = new Actions(driver);
					action.moveToElement(profileLink.get(0)).clickAndHold(kycDetailsLink).click().build().perform();
					LogWriter.logger.info("KYC Detail page Link Clicked");
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				}
				
				else
				{
					LogWriter.logger.info("User Not Login");
				}
			}
		}	
		
		public void navigateToChangePassword(String baseSiteURL) throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().contains(baseSiteURL)))
			{
					driver.navigate().to(baseSiteURL);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated to MMK Homepage");
					TakeScreenshot.passedScreenShot();
					
					if(profileLink.size()>0)
					{
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						Actions action = new Actions(driver);
						action.moveToElement(profileLink.get(0)).clickAndHold(changePasswordLink).click().build().perform();
						LogWriter.logger.info("Change Password page Link Clicked");
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					}
					
					else
					{
						LogWriter.logger.info("User Not Login");
					}
			}
			else
			{
				if(profileLink.size()>0)
				{
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					Actions action = new Actions(driver);
					action.moveToElement(profileLink.get(0)).clickAndHold(changePasswordLink).click().build().perform();
					LogWriter.logger.info("Change Password page Link Clicked");
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				}
				
				else
				{
					LogWriter.logger.info("User Not Login");
				}
			}
		}	
		public void navigateToUserProfile(String baseSiteURL) throws IOException, InterruptedException
		{
			LogWriter.logger.info("Under navigateToUserProfile Method");
			if(!(driver.getCurrentUrl().contains(baseSiteURL)))
			{
					driver.navigate().to(baseSiteURL);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated to MMK Homepage");
					TakeScreenshot.passedScreenShot();
					
					if(usersname.size()>0)
					{
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						Actions action = new Actions(driver);
						action.moveToElement(profileLink.get(0)).clickAndHold(userProfileLink).click().build().perform();
						LogWriter.logger.info("User Profile page Link Clicked..");
						Common.wait.until(ExpectedConditions.invisibilityOf(loader));
						TakeScreenshot.passedScreenShot();
					}
					
					else
					{
						LogWriter.logger.info("Opps. either user not login ..");
					}
			}
			else
			{
				if(usersname.size()>0)
				{
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					Actions action = new Actions(driver);
					action.moveToElement(profileLink.get(0)).clickAndHold(userProfileLink).click().build().perform();
					LogWriter.logger.info("User Profile page Link Clicked..");
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					TakeScreenshot.passedScreenShot();
				}
				
				else
				{
					LogWriter.logger.info("Opps. either user not login ..");
				}
			}
		}
}
