package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage {
	
	@FindBy(xpath="//img[@title='Create Vendor...']")
	private WebElement vendorLookupIcon;
	

	//intaialization//
	public VendorsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getVendorLookupIcon() {
		return vendorLookupIcon;
	}
	
	public void getVendorPage()
	{
		vendorLookupIcon.click();
	}

}
