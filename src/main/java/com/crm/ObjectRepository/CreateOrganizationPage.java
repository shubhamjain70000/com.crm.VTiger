package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class CreateOrganizationPage {
	
	//declaration//
	@FindBy(name="accountname")
	private WebElement organizationName;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	//intialization//
	public CreateOrganizationPage(WebDriver driver)

	{
		PageFactory.initElements(driver, this);
	}
	
	//utilizations//
	
	public WebElement getSaveButton()
	{
		return saveButton;
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}
	
	//business logic//
	public void createOrganization(String orgname)
	{
		organizationName.sendKeys(orgname);
	}
	public void createOrganization(String orgname, WebDriverUtility wLib, String industryValue)
	{
		organizationName.sendKeys(orgname);
		wLib.select(industryDD,industryValue);
    }
	public void createOrganization(String orgname, String typeValue, WebDriverUtility wLib,String industryValue)
	{
		organizationName.sendKeys(orgname);
		wLib.select(typeDD,typeValue);
		wLib.select(industryDD, industryValue);
		saveButton.click();
    }
	
	
}
