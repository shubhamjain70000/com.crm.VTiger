package com.LeadModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Lead_Edit_Module {

	public static void main(String[] args) throws Throwable {
		//creating random class//
		Random r=new Random();
		int ran = r.nextInt(500);
	
		
		//getting data from properties file//
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//getting data from excel sheet//
		FileInputStream fi=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh = book.getSheet("Lead");
		String value = sh.getRow(0).getCell(1).getStringCellValue()+ran;
		
		//launching the browser//
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		
		//login to the application//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement salutation=driver.findElement(By.name("salutationtype"));
		Select s=new Select(salutation);
		s.selectByValue("Mr.");
		
		//creating arraylist//
		ArrayList<String> list=new ArrayList<String>();
		list.add("firstname");
		list.add("lastname");
		list.add("phone");
		list.add("company");
		list.add("mobile");
		list.add("designation");
		
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			String value1 = sh.getRow(i).getCell(1).getStringCellValue();
			driver.findElement(By.name(list.get(i))).sendKeys(value1);
		}
		
		WebElement LeadSources=driver.findElement(By.name("leadsource"));
		Select s1=new Select(LeadSources);
		s1.selectByValue("Self Generated");
		WebElement LeadStatus = driver.findElement(By.name("leadstatus"));
		Select s2=new Select(LeadStatus);
		s2.selectByValue("Contacted");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//validation
		Thread.sleep(3000);
		String actualvalue=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(actualvalue.contains(value))
		{
			System.out.println("Lead Created Successfully!!");
		}
		else
		{
			System.out.println("Lead is not Created!!");
		}
		driver.findElement(By.xpath("//input[@title='Edit [Alt+E]']")).click();
		
		
		//logout from the applications//
        WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		

	}

}
