package com.crm.ObjectRepository;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class CreateLeadPage {
	
	
	//declaration//
	@FindBy(name="salutationtype")
	private WebElement none;
	
	@FindBy(name="lastname")
	private WebElement lastname;
	
	@FindBy(name="company")
	private WebElement company;
	
	@FindBy(name="leadsource")
	private WebElement leadSources;
	
	@FindBy(name="industry")
	private WebElement industry;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	//intialization//
	public CreateLeadPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilization//

	public WebElement getNone() {
		return none;
	}

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getCompany() {
		return company;
	}

	public WebElement getLeadSources() {
		return leadSources;
	}

	public WebElement getIndustry() {
		return industry;
	}
	public WebElement getSave() {
		return save ;
	}
	
	
	//business logic//
	public void getmandatoryfileds(WebDriverUtility wLib,String salutation,String lname,String comp,String leadDD, String industryDD)
	{
		wLib.select(none,salutation);
		lastname.sendKeys(lname);
		company.sendKeys(comp);
		wLib.select(leadSources,leadDD);
		wLib.select(industry,industryDD);
		
	}
	public void Save()
	{
		save.click();
	}

	
	public void getmandatoryfileds(HashMap<String, String> fields, WebDriver driver, JavaUtility jLib)
	{
		for(Entry<String, String> set:fields.entrySet())
		{
			if((set.getKey().contains("lastname")))
					{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+jLib.getRandom());
					}
			else
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
			}
			
		}
	}
	
}
