package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class WorkFlowPage {
	
	@FindBy(xpath="//a[contains(.,'Workflows')]")
	private WebElement workflowLink;
	
	public WorkFlowPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getWorkflowLink() {
		return workflowLink;
	}
	
	public void clickOnWorkFlow(WebDriverUtility wLib,WebDriver driver)
	{
		wLib.scrollBarAction(driver);
		wLib.elementToBeVisible(driver, workflowLink);
		workflowLink.click();
	}

}
