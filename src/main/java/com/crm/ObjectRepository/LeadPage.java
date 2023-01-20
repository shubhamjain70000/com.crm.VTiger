package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage {
	
	//Declaration
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createLead;
	
	//intialization//
	public LeadPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilization//

	public WebElement getCreateLead() {
		return createLead;
	}
	
	//business Logic//
	public void getCreateLeadPage()
	{
		createLead.click();
	}
	
	
	

}
