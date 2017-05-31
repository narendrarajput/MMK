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

public class WalletTransactionHistoryPage 
{
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(id = "WalletTransHistoryGrids_processing")
	public WebElement ProcessingLoader;
	
	@FindBy(xpath = "//li/a[normalize-space()='Transaction']")
	public WebElement transactionList;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'Wallet Usage')]")
	public WebElement walletUsage;
	 
	@FindBy(xpath = "//tr/td")
	public List<WebElement> gridData;
	
	
	WebDriver driver;
	
	public WalletTransactionHistoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void getWalletUsageData()
	{
		driver.navigate().to(TestDataComman.baseURL);
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		Actions action = new Actions(driver);
		action.moveToElement(transactionList).clickAndHold(walletUsage).click().build().perform();
		Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
		//Comman.wait.until(ExpectedConditions.invisibilityOf(ProcessingLoader));
		
		for(WebElement e :gridData)
		{
			System.out.println(e.getText());
		}
		
		
	}
	
}
