package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//declaration//
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	
	//Intialization//

	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//utilization//


	public WebElement getUsernameEdit() {
		return usernameEdit;
	}


	public WebElement getPasswordEdit() {
		return passwordEdit;
	}


	public WebElement getLoginButton() {
		return loginButton;
	}
	
	//business logic//
	public void getLoginPage(String username,String pwd)
	{
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(pwd);
		loginButton.click();
	}
	
	
	
	
	
	
	
	

}
