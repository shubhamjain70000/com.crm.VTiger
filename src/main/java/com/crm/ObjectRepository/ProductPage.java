package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement createProduct;
	
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateProduct() {
		return createProduct;
	}
	
	public void getProductpage()
	{
		createProduct.click();
	}

}
