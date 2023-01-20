package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class CreateFaq {
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement productNameLookupIcon;
	
	@FindBy(id="search_txt")
	private WebElement productSearchbox;
	
	@FindBy(name="search")
	private WebElement searchnowButton;
	
	@FindBy(name="faqstatus")
	private WebElement faqStatus;
	
	@FindBy(name="question")
	private WebElement que;
	
	@FindBy(name="faq_answer")
	private WebElement answer;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	
	public CreateFaq(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	


	public WebElement getProductNameLookupIcon() {
		return productNameLookupIcon;
	}


	public WebElement getProductSearchbox() {
		return productSearchbox;
	}


	public WebElement getSearchnowButton() {
		return searchnowButton;
	}


	public WebElement getFaqStatus() {
		return faqStatus;
	}


	public WebElement getQue() {
		return que;
	}


	public WebElement getAnswer() {
		return answer;
	}

	//business logic//
	public void fillFaqFields(String question, String ans, WebDriverUtility wLib, String StatusDD,WebDriver driver,String productname) throws Throwable
	{
		
	
		wLib.select(StatusDD, faqStatus);
		que.sendKeys(question);
		answer.sendKeys(ans);
		productNameLookupIcon.click();
		String mainid=driver.getWindowHandle();
		wLib.getwindowHandles(driver, "Products&action");
		productSearchbox.sendKeys(productname);
		searchnowButton.click();
		
		driver.findElement(By.xpath("//a[text()='"+productname+"']")).click();
		driver.switchTo().window(mainid);
		Thread.sleep(3000);
		saveButton.click();
		
		
	}
	

}
