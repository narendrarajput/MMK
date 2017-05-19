package com.mmk.bus;

import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOf(loader));
			
			driver.switchTo().frame(0);
			wait.until(ExpectedConditions.visibilityOf(loginPopupCloseButton));
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			try
			{		
					js.executeScript("arguments[0].click();", loginPopupCloseButton);
					driver.switchTo().defaultContent();

			}
			catch(Exception e)
			{
				System.out.println("There is no element for popup " + e);
			}
			
			sourceCity.sendKeys(sourcecityName);
			destinationCity.sendKeys(destinationcityName);
			journeyDate.sendKeys(date);
			searchButton.click();

			wait.until(ExpectedConditions.invisibilityOf(loader));
			if(NotificationMessage.getText().isEmpty())
			{
				//System.out.println("if"+NotificationMessage.getText());
				wait.until(ExpectedConditions.invisibilityOf(gridLoader));
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
				System.out.println(NotificationMessage.getText());
			}		
		}
}
