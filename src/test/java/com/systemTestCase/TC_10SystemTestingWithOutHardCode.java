package com.systemTestCase;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TC_10SystemTestingWithOutHardCode {
	
	public static void main(String[] args) throws Throwable {
		WebDriver driver= null;
		
		
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		int random = jLib.getRandom();
		
		String dropdown = eLib.readDataFromExcelSheet("TC10ST2",1, 0);
		
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		String BROWSER = fLib.readDataFromPropertyFile("broswer");
		
		
		if(BROWSER.equals("chrome"))
		{	
			System.out.println("Chrome driver Launched Succesfully");
			driver= new ChromeDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		//login to vtiger
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//move to setting and click crm settings
		WebElement crmsetting = driver.findElement(By.xpath("//td[@class='genHeaderSmall']/../td[4]/img[1]"));
		wLib.mousehover(driver, crmsetting);
		driver.findElement(By.linkText("CRM Settings")).click();
		
		
		//scroll upto workflows
		WebElement workflows = driver.findElement(By.xpath("//a[contains(.,'Workflows')]"));
		wLib.scrollIntoView(driver, workflows);
		
		workflows.click();
		
		//module list dropdown and select list option and create
		driver.findElement(By.id("new_workflow")).click();
		WebElement moduledd = driver.findElement(By.id("module_list"));
		wLib.select(dropdown, moduledd);
		
		//click on create//
		driver.findElement(By.id("new_workflow_popup_save")).click();
	
		//Fetch data from excell
		String actualDescription=eLib.readDataFromExcelSheet("TC10ST2", 2, 1)+random;
		//write on description the data fetch from excell
		driver.findElement(By.id("save_description")).sendKeys(actualDescription);
		//click on save button
		driver.findElement(By.id("save_submit")).click();
		//again scroll down upto workflow
		wLib.scrollBarAction(driver);
		//click on workflows
		driver.findElement(By.xpath("//a[contains(.,'Workflows' )]")).click();
		//Again scroll down
		WebElement scrolllastoption = driver.findElement(By.xpath("//td[.='"+actualDescription+"']"));
		wLib.scrollIntoView(driver, scrolllastoption);
		//validation
		String expDescription=scrolllastoption.getText();
		if(actualDescription.equals(expDescription))
		{
			System.out.println("TC is pass");
			//delete that particular lead
			driver.findElement(By.xpath("//table[@id='expressionlist']/descendant::td[.='"+actualDescription+"']/../td[3]/a[2]")).click();
			wLib.acceptAlert(driver);
		}
		else
		{
			System.out.println("TC is fail");
		}
		
		
		

	}


}
