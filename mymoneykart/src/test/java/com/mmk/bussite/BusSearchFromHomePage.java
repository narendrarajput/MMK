package com.mmk.bussite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.LogWriter;
import com.mmk.commonutils.TestDataComman;

public class BusSearchFromHomePage 
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

	WebDriver driver;
	
		public BusSearchFromHomePage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
	
		public void doBusSearch(String sourcecityName, String destinationcityName, String date)
		{	
			try
			{
				driver.navigate().to(TestDataComman.baseURL);
				LogWriter.logger.info("Navigated To Main MMK Site");
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				
				/* If user  not log in then it opens login popup default below code will check	
				 * If popup available then switch to popup and close it else do nothing because there 
				 * is no popup if user already login
				 */
				driver.switchTo().frame(0);
				Comman.wait.until(ExpectedConditions.visibilityOf(loginPopupCloseButton));
		
				Comman.jsExecuter.executeScript("arguments[0].click();", loginPopupCloseButton);
				LogWriter.logger.info("Login Popup Closed ");
				driver.switchTo().defaultContent();
				Thread.sleep(1400);	
			}
			catch(Exception e)
			{
				LogWriter.logger.info(e.toString());
			}
			
			sourceCity.sendKeys(sourcecityName);
			destinationCity.sendKeys(destinationcityName);
			journeyDate.sendKeys(date);
			searchButton.click();
			
			LogWriter.logger.info("Search Data entered and Search Buttom clicked from MMK HomePage");
			

			/* Comman.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ResultBox")));
			System.out.println(NotificationMessage.getText()); */
			
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
			if(!(driver.getCurrentUrl().equals("http://bus.mymoneykart.com/")))
			{
				//System.out.println("if"+NotificationMessage.getText());
				Comman.wait.until(ExpectedConditions.invisibilityOf(gridLoader));
				String currentURL = driver.getCurrentUrl();
				if(currentURL.contains("bus.mymoneykart.com"))
				{
					
					LogWriter.logger.info("There are total : "+busList.size()+" : buses Found");
					for(WebElement e : busList)
					{
						LogWriter.logger.info(e.getText());
					}
				
				}
			}
			else
			{
				//System.out.println(Comman.notificationMessage.getText());
				LogWriter.logger.info("No buses are available for the search");
			}		
		}
}
