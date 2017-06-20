package com.mmk.bus;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import com.mmk.commonutils.BusSiteTestData;
import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.UserLogin;
import com.mmk.reader.LogWriter;


public class BusHomePage 
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
	
	@FindBy(xpath = ".//*[@id='frmPrimarySearch']/div[2]/div/button")
	WebElement searchButton;
	
	
	@FindBy(xpath = ".//*[@id='dvBusSearchHistory']/div/a")
	List<WebElement> recentSearchesLink ;
	
	@FindBy(xpath = "//div[@class='popover-content']/ul/li/a")
	List<WebElement> recentSearchesData ;
	
	
	@FindBy(xpath = "//div[@class='text-travels']/a")
	List<WebElement> busList;
	
	@FindBy(id = "dvListLoader")
	 WebElement gridLoader;
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	List<WebElement> usersname;
	
	@FindBy(id = "ddlPrefferedSourceCity")
	WebElement preferredCity;
	
	@FindBy(id = "btnSetPreference")
	WebElement setPreferenceButton;
	
	@FindBy(xpath = ".//*[@id='busRating']/div")
	List<WebElement> ratings;
	
	@FindBy(xpath = ".//*[@id='busRating']/div//label")
	List<WebElement> ratingInfo;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//div[@id='stars']/span[5]")
	WebElement ratingStar;
	
	@FindBy(id = "btnRating")
	public WebElement ratingSubmitButton;
	
	
	WebDriver driver;
	
	public BusHomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
		public void busUserLoginCheck() throws IOException, InterruptedException
		{
				if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
				{
					driver.navigate().to(BusSiteTestData.busSiteURL);
					Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
					LogWriter.logger.info("Navigated To Bus Site");
					TakeScreenshot.passedScreenShot();
				}
		 				
				if(usersname.size()>0)
				{
					LogWriter.logger.info("User is already Logged in the system");
			
				}
				else
				{
					TakeScreenshot.passedScreenShot();
					new UserLogin(driver).doLogin(TestDataComman.username, TestDataComman.password);
					Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
					AssertJUnit.assertEquals(TestDataComman.usersname, usersname.get(0).getText());
					TakeScreenshot.passedScreenShot();
					LogWriter.logger.info("Login Method called from Bus Home Page");
				}

		}
		
		public void getUserPreferredCity() throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				 String cityName  = new Select(preferredCity).getFirstSelectedOption().getText();
				 LogWriter.logger.info("Selected Preferred city is : "+cityName);
				 TakeScreenshot.passedScreenShot();
				 
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to track preferred city");
					TakeScreenshot.passedScreenShot();
			}
		}
		
		public void setUserPreferredCity() throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				 Select s = new Select(preferredCity);
				 
				 String cityName  = s.getFirstSelectedOption().getText();
				 if(cityName.equals(BusSiteTestData.preferredCity))
				 {
					 LogWriter.logger.info("Preferred City is already Selected");
					 TakeScreenshot.passedScreenShot();
				 }
				 else
				 {
					 LogWriter.logger.info("Last Selected Preferred City is : " + s.getFirstSelectedOption().getText());
					 TakeScreenshot.passedScreenShot();
					 s.selectByVisibleText(BusSiteTestData.preferredCity);
					 setPreferenceButton.click();
					 Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
					 LogWriter.logger.info("New Selected Preferred City is : " + s.getFirstSelectedOption().getText());
					 TakeScreenshot.passedScreenShot();
				 }
		 
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to Select preferred city");
					TakeScreenshot.passedScreenShot();
			}
		}
		
		public void getRatings() throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				 if(ratings.size()>0)
				 {
					 LogWriter.logger.info("Rating Available : " + ratingInfo.get(0).getText());
					 TakeScreenshot.passedScreenShot();
				 }
				 else 
				 {
					 LogWriter.logger.info("No Rating found !!");
					 TakeScreenshot.passedScreenShot();
				 }
				 
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to track Ratings");
					TakeScreenshot.passedScreenShot();
			}
		}
		
		public void submitRating() throws IOException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			
			if(usersname.size()>0)
			{			
				 if(ratings.size()>0)
				 {
					 LogWriter.logger.info("Rating Available : " + ratingInfo.get(0).getText());
					 TakeScreenshot.passedScreenShot();
					 ratingStar.click();
					 ratingSubmitButton.click();
					 Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
					 TakeScreenshot.passedScreenShot();
					 LogWriter.logger.info(notificationMessage.getText());
				 }
				 else 
				 {
					 LogWriter.logger.info("No Rating found !!");
					 TakeScreenshot.passedScreenShot();
				 }
			} 
			else
			{
				LogWriter.logger.info("User Not Logged in So Unable to Submit Ratings");
				TakeScreenshot.passedScreenShot();
			}
		}

		
		public void getRecentSearchData() throws IOException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				recentSearchesLink.get(0).click();
				TakeScreenshot.passedScreenShot();
				if(recentSearchesData.size()>0)
				{
					LogWriter.logger.info("User did search for following journey");
					for(WebElement e : recentSearchesData)
					{
						LogWriter.logger.info(e.getText());
					}
					
				}
				else
				{
					LogWriter.logger.info("No Search Found For This User");
					TakeScreenshot.passedScreenShot();
				}
					recentSearchesLink.get(0).click();
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to track recent Searches");
					TakeScreenshot.passedScreenShot();
			}
		}
	
		public void doBusSearch(String sourcecityName, String destinationcityName, String date) throws IOException
		{
			
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			TakeScreenshot.passedScreenShot();
			sourceCity.clear();
			sourceCity.sendKeys(sourcecityName);
			destinationCity.sendKeys(destinationcityName);
			journeyDate.sendKeys(date);
			TakeScreenshot.passedScreenShot();
			searchButton.click();

			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(NotificationMessage.getText().isEmpty())
			{
				//System.out.println("if"+NotificationMessage.getText());
				Comman.wait.until(ExpectedConditions.invisibilityOf(gridLoader));
				String currentURL = driver.getCurrentUrl();
				if(currentURL.contains("bus.mymoneykart.com"))
				{
					TakeScreenshot.passedScreenShot();
					LogWriter.logger.info("Total------Found Buses-------"+busList.size());
					for(WebElement e : busList)
					{
						LogWriter.logger.info(e.getText());
					}
				
				}
			}
			else
			{
				LogWriter.logger.info(notificationMessage.getText());
				TakeScreenshot.passedScreenShot();
			}		
		}
}
