package com.pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pom.utils.TakeScreenshot;

public class UserLogin 
{
		@FindBy(className = "Popupiframe")
		WebElement iframe;
		
		@FindBy(id = "MobileNo")
		WebElement mobileNumber;
		
		@FindBy(id = "btnLogin")
		WebElement loginButton;
		
		@FindBy(id = "Password")
		WebElement password;
		
		@FindBy(id = "btnSubmit")
		WebElement submitButton;
		
		@FindBy(id = "divLoader")
		WebElement loader;
		
		@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
		WebElement usersname;
		
		WebDriver driver;
		
		public UserLogin(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
		
		public void doLogin(String username, String pasword)
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.switchTo().frame(0);
			mobileNumber.sendKeys(username);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
			loginButton.click();		
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
			password.sendKeys(pasword);
			submitButton.click();			
			//driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divLoader")));
			Assert.assertEquals("Narendra Rajput", usersname.getText());
			TakeScreenshot.takeScreen("hello", true);
			System.out.println("User Log in success");
			
		}
}
