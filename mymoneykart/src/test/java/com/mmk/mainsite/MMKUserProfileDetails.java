package com.mmk.mainsite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Common;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;

public class MMKUserProfileDetails {
	
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	WebElement usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public List<WebElement> profileLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'User Profile')]")
	public WebElement userProfileLink;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//input[not(@type='hidden')][not(@id='txtChangePhone' or @id='txtOTP')]")
	public List<WebElement> allTextFieldsDetails;
	
	
	@FindBy(xpath = "//select[not(@class='clsLanguage')]")
	public List<WebElement> allDropdownValue;
	
	@FindBy(id = "Address")
	public WebElement address;
	
	
	WebDriver driver;
	
	public MMKUserProfileDetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	public void getUserProfileDetails() throws IOException
	{
		if(profileLink.size()>0)
		{
			driver.navigate().to(TestDataComman.baseURL);			
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			Actions action = new Actions(driver);
			action.moveToElement(profileLink.get(0)).clickAndHold(userProfileLink).click().build().perform();
			LogWriter.logger.info("User Profile Page Link Clicked");
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			TakeScreenshot.passedScreenShot();
			if(driver.getCurrentUrl().contains("CompleteProfile"))
			{
				LogWriter.logger.info("User's All Details are missing");
			}
			else
			{
				LogWriter.logger.info("--User's details are----");
				for(WebElement e : allTextFieldsDetails)
				{
/*					try
					{*/
					LogWriter.logger.info(e.findElement(By.xpath("preceding::label[1]")).getText() +" --- "+e.getAttribute("value"));
/*					}
					catch(Exception err)
					{
						LogWriter.logger.info(e.findElement(By.xpath("../preceding-sibling::label")).getText() +" --- "+e.getAttribute("value"));
					}*/
					
				}
				
				for(WebElement e2 : allDropdownValue)
				{
					LogWriter.logger.info(e2.findElement(By.xpath("preceding-sibling::label")).getText() +" --- "+new Select(e2).getFirstSelectedOption().getText());
				}
				
				
				LogWriter.logger.info("Branch Address :" + address.getAttribute("value"));
 
			}
		}
		else
		{
			LogWriter.logger.info("-----Not User Profile Page-----");
		}
	}
}
