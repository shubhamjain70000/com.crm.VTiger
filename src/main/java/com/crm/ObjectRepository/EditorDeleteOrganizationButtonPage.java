package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class EditorDeleteOrganizationButtonPage {
	
	@FindBy(xpath="(//input[@title='Delete [Alt+D]'])[1]")
	private WebElement deleteButton;
	
	public EditorDeleteOrganizationButtonPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}
	public void clickOnDelete(WebDriverUtility wLib,WebDriver driver)
	{
		deleteButton.click();
		wLib.acceptAlert(driver);
		System.out.println("deleted orgn");
	}

}
