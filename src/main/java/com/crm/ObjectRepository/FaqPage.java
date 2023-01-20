package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class FaqPage {
	
	@FindBy(xpath="//img[@title='Create FAQ...']")
	private WebElement createFaq;
	
	@FindBy(name="search_text")
	private WebElement searchBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement InDD;
	
	@FindBy(name="submit")
	private WebElement searchNowButton;
	
	public FaqPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateFaq() {
		return createFaq;
	}
	public void createFaq()
	{
		createFaq.click();
	}
	public void searchFaq(WebDriverUtility wLib,String inValue, String proname)
	{
		wLib.select(InDD, inValue);
		searchBox.sendKeys(proname);
		searchNowButton.click();
	}

}
