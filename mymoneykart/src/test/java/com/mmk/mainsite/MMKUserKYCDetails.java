package com.mmk.mainsite;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mmk.commonutils.Comman;
import com.mmk.commonutils.TakeScreenshot;
import com.mmk.commonutils.TestDataComman;
import com.mmk.reader.LogWriter;

public class MMKUserKYCDetails 
{
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	WebElement usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public List<WebElement> profileLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'KYC Details')]")
	public WebElement kycDetailsLink;
	
	@FindBy(id = "oldPassword")
	public WebElement usroldPassword;
	
	@FindBy(id = "NewPassword")
	public WebElement usrnewPassword;
	
	@FindBy(id = "confirmPassword")
	public WebElement usrconfirmPassword;
	
	@FindBy(id = "btnSubmit")
	public WebElement submitButton;
	
	@FindBy(id = "divLoader")
	public WebElement loader;
	
	@FindBy(xpath = "//input[not(@id='fuPanCardFilePath')][not(@type='hidden')][not(@id='fuIDProofFilePath')]")
	public List<WebElement> allTextFieldsDetails;
	
	@FindBy(xpath = "//a[@class='attachbaseURLpath'][@style='display:block']")
	public List<WebElement> documentPath;
	
	@FindBy(id = "IDProof")
	public WebElement idProofDetails;
	
	@FindBy(id="BranchAddress")
	public WebElement branchAdress;
	
	@FindBy(id="btnSubmit")
	public WebElement kycSubmitButton;
	
	@FindBy(xpath="//span[@class='field-validation-error']")
	List<WebElement> validationMessages;
	
		
	
	WebDriver driver;
	
	public MMKUserKYCDetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void getUserKYCDetails() throws IOException
	{
			if(profileLink.size()>0)
			{
				driver.navigate().to(TestDataComman.baseURL);			
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				TakeScreenshot.passedScreenShot();
				Actions action = new Actions(driver);
				action.moveToElement(profileLink.get(0)).clickAndHold(kycDetailsLink).click().build().perform();
				LogWriter.logger.info("KYC Details Page Link Clicked");
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				TakeScreenshot.passedScreenShot();
				if(driver.getCurrentUrl().contains("CompleteProfile"))
				{
					LogWriter.logger.info("User's All Details are missing");
				}
				else
				{
				
					LogWriter.logger.info("--User KYC details are----");
					for(WebElement e : allTextFieldsDetails)
					{
						LogWriter.logger.info(e.getAttribute("value"));
					}
					
					LogWriter.logger.info("Branch Address :" + branchAdress.getAttribute("value"));
					String id= new Select(idProofDetails).getFirstSelectedOption().getText();
					LogWriter.logger.info("Selected ID Proff is :" + id);
					
					if(documentPath.size()==2)
					{
						LogWriter.logger.info("IDProff and Pancard Attachment are available");
					}
					else
					{
						LogWriter.logger.info("Doc available as per Resident Status is " + documentPath.size());
					}
				}
			}
				else
				{
					LogWriter.logger.info("-----Not On Expected Page-----");
				}					
		}
	
		public void checkMissingKYCInfo() throws IOException
		{
				kycSubmitButton.click();
				Comman.wait.until(ExpectedConditions.invisibilityOf(loader));
				if(validationMessages.size()>0)
				{
					for(WebElement e:validationMessages)
					{
						LogWriter.logger.info(e.getText());
						TakeScreenshot.passedScreenShot();
					}
				}
				else
				{
					LogWriter.logger.info("All info Up to Date :" + notificationMessage.getText());
					TakeScreenshot.passedScreenShot();
				}

		}
}
