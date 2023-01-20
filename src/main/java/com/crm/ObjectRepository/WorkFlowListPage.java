package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class WorkFlowListPage {
	
	@FindBy(id="new_workflow")
	private WebElement newWorkflowLink;
	
	@FindBy(id="module_list")
	private WebElement createWorkflowDropDown;
	
	@FindBy(id="new_workflow_popup_save")
	private WebElement createButton;
	
	public WorkFlowListPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilization//

	public WebElement getNewWorkflowLink() {
		return newWorkflowLink;
	}
	
	public WebElement getcreateWorkflowDropDown() {
		return createWorkflowDropDown;
	}
	//business logic//
	public void clickOnNewWorkFlowAndCreate(String WorkflowDD, WebDriverUtility wLib)
	{
		newWorkflowLink.click();
		createWorkflowDropDown.click();
		wLib.select(createWorkflowDropDown, WorkflowDD);
		createButton.click();
	}
	public void ValidationAndDeletion(WebDriver driver, String expectedvalue, WebDriverUtility wLib)
	{
		String expectedData = driver.findElement(By.xpath("//table[@id='expressionlist']/descendant::td[.='"+expectedvalue+"']")).getText();
		if(expectedData.contains("virat kohli"))
		{
			System.out.println("Test case is passed");
			System.out.println(expectedvalue+" created successfully!!!");
		}
		else
		{
			System.out.println("Testcase is failed!!!!!");
		}
		System.out.println("Description is created now which has to deleted!!!");
		if(expectedData.contains("virat kohli"))
		{
			driver.findElement(By.xpath("//table[@id='expressionlist']/descendant::td[.='"+expectedvalue+"']/../td[3]/a[2]/img[1]")).click();
			
			wLib.acceptAlert(driver);
			System.out.println("Deleted successfully!!");
		}
	}
	

}
