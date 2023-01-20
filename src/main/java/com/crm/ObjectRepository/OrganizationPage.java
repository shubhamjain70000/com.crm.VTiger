package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	//declaration//
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrganization;
	
	//intialization//
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilizations//

	public WebElement getCreateOrganization() {
		return createOrganization;
	}
	
	//Business logic//
	public void getCreateOrganizationPage()
	{
		createOrganization.click();
	}
	
	
	

}
