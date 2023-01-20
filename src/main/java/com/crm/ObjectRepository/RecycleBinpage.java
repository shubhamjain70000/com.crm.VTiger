package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class RecycleBinpage {
	
	@FindBy(xpath="//input[@class='txtBox']")
	private WebElement searchBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement In;
	
	@FindBy(name="submit")
	private WebElement searchNow;

	
	public RecycleBinpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getIn() {
		return In;
	}
	public WebElement getSearch()
	{
		return searchNow;
	}
	
	//business logic/
    public void recycleBinPage(String orgname, WebDriverUtility wLib, String visibletext)
    {
    	searchBox.sendKeys(orgname);
    	wLib.select(visibletext, In);
    	searchNow.click();
    }
	
	
	

}
