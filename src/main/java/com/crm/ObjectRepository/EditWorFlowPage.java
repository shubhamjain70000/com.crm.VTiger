package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class EditWorFlowPage {
	
	@FindBy(id="save_description")
	private WebElement DescriptionBox;
	
	@FindBy(id="save_submit")
	private WebElement saveButton;
	
	@FindBy(xpath="//a[contains(.,'Workflows')]")
	private WebElement workflowLink;
	
	public EditWorFlowPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getDescriptionBox() {
		return DescriptionBox;
	}
	public WebElement getworkflowLink() {
		return workflowLink;
	}
	
	public void sendTheMessageInDescrption(String description)
	{
		DescriptionBox.sendKeys(description);
		saveButton.click();
	}
	public void scrollDownAndClickOnWorkFlow(WebDriverUtility wLib, WebDriver driver)
	{
		wLib.scrollBarAction(driver);
		workflowLink.click();
	}
	

}
