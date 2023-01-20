package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class CreateNewProduct {
	
	@FindBy(name="productname")
	private WebElement proname;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement save;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement vendornameLookUpIcon;
	
	@FindBy(id="search_txt")
	private WebElement productSearchbox;
	
	@FindBy(name="search")
	private WebElement searchnowButton;
	
	
	
	
	
	public CreateNewProduct(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getproductSearchbox() {
		return productSearchbox;
	}
	
	public WebElement getsearchnowButton() {
		return searchnowButton;
	}
	
	public WebElement getvendornameLookUpIcon() {
		return vendornameLookUpIcon;
	}

	public WebElement getProname() {
		return proname;
	}
	
	public void createProductName(String pname)
	{
		proname.sendKeys(pname);
		save.click();
	}
	
	public void createProduct(String pname, WebDriverUtility wLib, WebDriver driver, String vendorName)
	{ 
		String mainid = driver.getWindowHandle();
		vendornameLookUpIcon.click();	
		wLib.getwindowHandles(driver, "Vendors&action");
		productSearchbox.sendKeys(vendorName);
		searchnowButton.click();
		driver.findElement(By.xpath("//a[.='"+vendorName+"']")).click();
		driver.switchTo().window(mainid);
		proname.sendKeys(pname);
		save.click();
	}

}
