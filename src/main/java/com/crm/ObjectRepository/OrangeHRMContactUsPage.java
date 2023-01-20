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

public class OrangeHRMContactUsPage {
	
	//Declaration//
	
	@FindBy(id="Form_getForm_FullName")
	private WebElement FullName;
	
	@FindBy(id="Form_getForm_Contact")
	private WebElement PhoneNumber;
	
	@FindBy(id="Form_getForm_Email")
	private WebElement Email;
	
	@FindBy(id="Form_getForm_Country")
	private WebElement country;
	
	@FindBy(id="Form_getForm_NoOfEmployees")
	private WebElement noOfEmployee;
	
	@FindBy(id="Form_getForm_JobTitle")
	private WebElement jobTitle;
	
	@FindBy(id="Form_getForm_Comment")
	private WebElement comment;
	
	//intialization//
	
	public OrangeHRMContactUsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization//

	public WebElement getFullName() {
		return FullName;
	}

	public WebElement getPhoneNumber() {
		return PhoneNumber;
	}

	public WebElement getEmail() {
		return Email;
	}

	public WebElement getCountry() {
		return country;
	}

	public WebElement getNoOfEmployee() {
		return noOfEmployee;
	}

	public WebElement getJobTitle() {
		return jobTitle;
	}

	public WebElement getComment() {
		return comment;
	}
	
	//business logic//
	
	public void fillthemandatoryFields(String name, String phone, String email, WebDriverUtility wLib, String countryName,String employee,String jobtitle,String comm)
	{
		FullName.sendKeys(name);
		PhoneNumber.sendKeys(phone);
		Email.sendKeys(email);
		wLib.select(country, countryName);
		wLib.select(noOfEmployee, employee);
		jobTitle.sendKeys(jobtitle);
		comment.sendKeys(comm);
	}
	
	public void fillthemandatoryFields(HashMap<String,String> fields, WebDriver driver , JavaUtility jLib)
	{
		for(Entry<String,String> set:fields.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


