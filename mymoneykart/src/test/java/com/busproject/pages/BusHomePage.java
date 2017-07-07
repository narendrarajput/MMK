package com.busproject.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
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
import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.mainsite.UserLogin;
import com.mmk.reader.LogWriter;
import com.mmkproject.pages.MyMoneyKartHomePage;


public class BusHomePage 
{
	@FindBy(id = "btnBusLoginUser")
	WebElement loginLink;
	
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
	public List<WebElement> usersname;
	
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
	
	@FindBy(xpath = "//div[@class='spinner']")
	public WebElement preferredCitySpinner;

	
	
		WebDriver driver;
	
		public BusHomePage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
		
		public void navigateToBusHomePage(String busSiteurl) throws IOException
		{
			if(!(driver.getCurrentUrl().equals(busSiteurl)))
			{
				driver.navigate().to(busSiteurl);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
		}
	
		public void busUserLoginCheck(String username, String password, String userName, String busSiteurl) throws IOException, InterruptedException
		{
				if(!(driver.getCurrentUrl().equals(busSiteurl)))
				{
					driver.navigate().to(busSiteurl);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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
					loginLink.click();
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					new MyMoneyKartHomePage(driver).doUserLogin(username, password);
					Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					Thread.sleep(1200);
					AssertJUnit.assertEquals(userName, usersname.get(0).getText());
					TakeScreenshot.passedScreenShot();
					LogWriter.logger.info("Login Method called from Bus Home Page");
				}

		}
		
		public void getUserPreferredCity() throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				 Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				 Thread.sleep(1500);
				 String cityName  = getPreferredCityDropdownValue();
				 LogWriter.logger.info("Selected Preferred city is : "+cityName);
				 TakeScreenshot.passedScreenShot();
				 
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to track preferred city");
					TakeScreenshot.passedScreenShot();
			}
		}
		
		public void setUserPreferredCity(String preferredCityName) throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(BusSiteTestData.busSiteURL)))
			{
				driver.navigate().to(BusSiteTestData.busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Navigated To Bus Site");
				TakeScreenshot.passedScreenShot();
			}
			if(usersname.size()>0)
			{
				 Common.wait.until(ExpectedConditions.invisibilityOf(preferredCitySpinner));
				 Select s = new Select(preferredCity);
				// String cityName  = s.getFirstSelectedOption().getText();
				 String cityName  = getPreferredCityDropdownValue();
				 if(cityName.equals(preferredCityName))
				 {
					 LogWriter.logger.info("Preferred City is already Selected");
					 TakeScreenshot.passedScreenShot();
				 }
				 else
				 {
					 //Common.wait.until(ExpectedConditions.invisibilityOf(loader));			
					 LogWriter.logger.info("Last Selected Preferred City is : " + getPreferredCityDropdownValue());
					 TakeScreenshot.passedScreenShot();
					 s.selectByVisibleText(preferredCityName);
					 setPreferenceButton.click();
					 Common.wait.until(ExpectedConditions.invisibilityOf(loader));
					 LogWriter.logger.info("New Selected Preferred City is : " + getPreferredCityDropdownValue());
					 TakeScreenshot.passedScreenShot();
				 }
		 
			}else
			{
					LogWriter.logger.info("User Not Logged in So Unable to Select preferred city");
					TakeScreenshot.passedScreenShot();
			}
		}
		
		public String getPreferredCityDropdownValue() 
		{   
		    return (String) Common.jsExecuter.executeScript("return $('#ddlPrefferedSourceCity').val();");
		}
		
		public void getRatings(String busSiteURL ) throws IOException, InterruptedException
		{
			if(!(driver.getCurrentUrl().equals(busSiteURL)))
			{
				driver.navigate().to(busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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
		
		public void submitRating(String busSiteURL) throws IOException
		{
			if(!(driver.getCurrentUrl().equals(busSiteURL)))
			{
				driver.navigate().to(busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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
					 Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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

		
		public void getRecentSearchData(String busSiteURL) throws IOException
		{
			if(!(driver.getCurrentUrl().equals(busSiteURL)))
			{
				driver.navigate().to(busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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
	
		public void doBusSearch(String sourcecityName, String destinationcityName, String date , String busSiteURL) throws IOException
		{
			
			if(!(driver.getCurrentUrl().equals(busSiteURL)))
			{
				driver.navigate().to(busSiteURL);
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
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

			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(NotificationMessage.getText().isEmpty())
			{
				//System.out.println("if"+NotificationMessage.getText());
				Common.wait.until(ExpectedConditions.invisibilityOf(gridLoader));
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
