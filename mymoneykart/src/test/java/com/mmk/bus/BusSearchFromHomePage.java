package com.mmk.bus;

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

import pom.utils.Comman;
import pom.utils.TestDataComman;

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
			driver.navigate().to(TestDataComman.baseURL);
			Comman.wait.until(ExpectedConditions.invisibilityOf(loader));	
			
			try
			{
					driver.switchTo().frame(0);
					Comman.wait.until(ExpectedConditions.visibilityOf(loginPopupCloseButton));
		
					Comman.jsExecuter.executeScript("arguments[0].click();", loginPopupCloseButton);
					driver.switchTo().defaultContent();
					Thread.sleep(1400);	
			}
			catch(Exception e)
			{
				System.out.println("There is no popup element " + e);
			}
			
			sourceCity.sendKeys(sourcecityName);
			destinationCity.sendKeys(destinationcityName);
			journeyDate.sendKeys(date);
			searchButton.click();

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
					System.out.println("Total------Found Buses-------"+busList.size());
					for(WebElement e : busList)
					{
						System.out.println(e.getText());
					}
				
				}
			}
			else
			{
				//System.out.println(Comman.notificationMessage.getText());
				System.out.println("No buses found for this search");
			}		
		}
}
