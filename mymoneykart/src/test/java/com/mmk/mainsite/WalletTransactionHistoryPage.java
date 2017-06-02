package com.mmk.mainsite;

import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "//table[@id='WalletStatusGrids']/tbody//tr")
	public List<WebElement> gridData;
	
	@FindBy(xpath = "//li[@id='WalletStatusGrids_next']/a")
	public WebElement nextButton;
	
	@FindBy(id = "WalletStatusGrids_next")
	public WebElement nextLink;
	
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
		
		
		do
		{
			//Comman.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='WalletStatusGrids']/tbody/tr/td[1]")));
			for(WebElement e :gridData)
			{
				List<WebElement> cellData= e.findElements(By.tagName("td"));
				for(WebElement el :cellData)
				{
					System.out.print(el.getText()+"\t");
		
				}
				System.out.println();
			}
		
		nextButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		}
		while(!nextLink.getAttribute("class").contains("disabled"));
		
	}
	
}
