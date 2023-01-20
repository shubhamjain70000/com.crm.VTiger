package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadEditOrDeletepage {
	
	@FindBy(xpath="//input[@title='Edit [Alt+E]']")
	private WebElement EditButton;
	
	//intaialization//
	public LeadEditOrDeletepage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}	

}
