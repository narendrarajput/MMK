package com.mmk.commonutils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmk.driversetup.DriverSetup;

public class Common extends DriverSetup

{
	public static WebElement mainloader = driver.findElement(By.id("divLoader"));
	public static WebElement notificationMessage = driver.findElement(By.id("ResultBox"));
	
	public static WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public static JavascriptExecutor jsExecuter = (JavascriptExecutor)driver;
}
