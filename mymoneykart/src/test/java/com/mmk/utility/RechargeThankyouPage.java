package com.mmk.utility;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmk.reader.LogWriter;

public class RechargeThankyouPage {
	
	@FindBy(id = "divLoader")
	WebElement loader;
	
	@FindBy(tagName = "h3")
	List<WebElement> rechargeStatus;

	WebDriver driver;
	
	public RechargeThankyouPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void rechargeStatus()
	{
		try
		{
			if(driver.getCurrentUrl().contains("ThankYou"))
			{
				for(WebElement e:rechargeStatus)
				{
					LogWriter.logger.info(e.getText());
					
					if(e.getText().contains("Something went wrong"))
					{
						LogWriter.logger.info("--------Unsuccessfull Recharge--------------");
					}
				}
				
			}
			else
			{
				LogWriter.logger.info("Not on expected Page. Page URl is"+driver.getCurrentUrl());
			}
		}
		catch(Exception e)
		{
			LogWriter.logger.info(e.toString());
		}
	}

}
