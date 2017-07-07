package com.mmkproject.pages;

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

public class UserKYCDetailsPage 
{
	@FindBy(xpath = ".//*[@id='header']/div/div/div[5]/ul/li[1]/label")
	WebElement usersname;
	
	@FindBy(id = "ResultBox")
	public WebElement notificationMessage;
	
	@FindBy(xpath = "//li/a[contains(.,'Profile')]")
	public List<WebElement> profileLink;
	
	@FindBy(xpath = "//ul[@class='sub-menu']/li/a[contains(.,'KYC Details')]")
	public WebElement kycDetailsLink;
	
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
	
	@FindBy(id="btnTansfer")
	public WebElement transferButton;
	
	@FindBy(xpath = "//div[@class='result-content']/i")
	public WebElement notificationMessageVisibility;

	@FindBy(id="btnCancel")
	public WebElement cancelButton;
	
	@FindBy(xpath="//div[@class='modal fade in']//button[@type='button'][contains(text(),'Close')]")
	public WebElement popupCloseButton;
	
	@FindBy(xpath="//div[@id='IDProofImageShowModal'] | //div[@id='PanCardShowModal']")
	public WebElement documentShowPopup;
	
	
		
	
	WebDriver driver;
	
	public UserKYCDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void verifyKYCPageRedirect()
	{
		if(driver.getCurrentUrl().contains("CompleteProfile"))
		{
			LogWriter.logger.info("User's All Details are missing");
		}
		else
		{
			LogWriter.logger.info("User redirects on KYC page");
		}		
	}
	
	
	public void getUserKYCDetails() throws IOException
	{
			Common.wait.until(ExpectedConditions.invisibilityOf(loader));
			
			if(profileLink.size()>0)
			{
				
				if(driver.getCurrentUrl().contains("CompleteProfile"))
				{
					LogWriter.logger.info("User's All Details are missing");
				}
				else
				{
				
					LogWriter.logger.info("--User KYC details are----");
					for(WebElement e : allTextFieldsDetails)
					{
						LogWriter.logger.info(e.findElement(By.xpath("preceding::label[1]")).getText() +" --- "+e.getAttribute("value"));
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
			if(driver.getCurrentUrl().contains("KYCDetails"))
			{
				kycSubmitButton.click();
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				if(validationMessages.size()>0)
				{
					LogWriter.logger.info("------Following Details are Missing------");
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
		
		public void kycTransfer() throws IOException
		{
			if(driver.getCurrentUrl().contains("KYCDetails"))
			{
				transferButton.click();
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				
					try{
						//Comman.wait.until(ExpectedConditions.visibilityOf(notificationMessageVisibility));		
						LogWriter.logger.info(notificationMessage.getText());
						TakeScreenshot.passedScreenShot();
					}
					catch(Exception e)
					{
						
					}
				}
			if(validationMessages.size()>0)
			{
					LogWriter.logger.info("------Can't Transfer!! Following information are Missing------");
					for(WebElement e:validationMessages)
					{
						LogWriter.logger.info(e.getText());
						TakeScreenshot.passedScreenShot();
					}
			}

		}
		public void cancelOptionOfKYCForm() throws IOException
		{
			if(driver.getCurrentUrl().contains("KYCDetails"))
			{
				cancelButton.click();
				Common.wait.until(ExpectedConditions.invisibilityOf(loader));
				LogWriter.logger.info("Moved To : "+driver.getCurrentUrl()+" : on cancel Click");
			}
		}
		
		public void verifyUploadedDocument() throws IOException, InterruptedException
		{
			if(documentPath.size()==2)
			{
				LogWriter.logger.info("Both documents are available");
				
			}
			else if(documentPath.size()==1)
			{
				LogWriter.logger.info("1 document available");
			}
			else
			{
				LogWriter.logger.info("There is no document uploaded still");
			}
			if(documentPath.size()>0)
			{
				for(WebElement e : documentPath)
				{
					
					e.click();
					Common.wait.until(ExpectedConditions.elementToBeClickable(popupCloseButton));
					Thread.sleep(1500);
					TakeScreenshot.passedScreenShot();
					popupCloseButton.click();					
					Common.wait.until(ExpectedConditions.invisibilityOf(documentShowPopup));

				}
				LogWriter.logger.info("Document has been verified...");
			}
			
		}
		
}
