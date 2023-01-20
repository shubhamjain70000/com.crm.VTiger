package com.crm.ObjectRepository;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.JavaUtility;

public class CreateVendorPage extends JavaUtility {
	
	
	
	@FindBy(name="vendorname")
	private WebElement vendorNameTextBox;
	
	@FindBy(name="email")
	private WebElement EmailTextBox;
	
	@FindBy(name="phone")
	private WebElement phoneTextBox;
	
	@FindBy(name="website")
	private WebElement websiteTextBox;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	

	

	//intaialization//
	public CreateVendorPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}

	
	
	
	public WebElement getVendorNameTextBox() {
		return vendorNameTextBox;
	}




	public WebElement getEmailTextBox() {
		return EmailTextBox;
	}




	public WebElement getPhoneTextBox() {
		return phoneTextBox;
	}




	public WebElement getWebsiteTextBox() {
		return websiteTextBox;
	}
	//Business Logic//
	public void vendorField(String name, String email,String phone, String website)
	{
		vendorNameTextBox.sendKeys(name);
		EmailTextBox.sendKeys(email);
		phoneTextBox.sendKeys(phone);
		websiteTextBox.sendKeys(website);
		
		
	}
	public void clickOnSave()
	{
		saveButton.click();
	}




	public void vendorField(HashMap<String, String> fields, WebDriver driver,JavaUtility jLib)
	{
		
		for(Entry<String, String> set:fields.entrySet())
		{
			if((set.getKey().contains("vendorname")))
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
