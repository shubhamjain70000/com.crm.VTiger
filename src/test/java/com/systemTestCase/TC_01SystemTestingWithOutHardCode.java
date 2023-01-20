package com.systemTestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TC_01SystemTestingWithOutHardCode 
{
	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=null;
		
		// creating object of genericUtility//
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//calling Random class
		
		//Random r=new Random();
		//int random = r.nextInt(100);
		int random = jLib.getRandom();
		
		//calling common data
		
		//FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		//Properties pobj=new Properties();
		//pobj.load(fis);
		//String URL = pobj.getProperty("url");
		//String USERNAME = pobj.getProperty("username");
		//String PASSWORD = pobj.getProperty("password");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		String BROWSER = fLib.readDataFromPropertyFile("broswer");
		
		
				
		//calling data from excel sheet//
		
		//FileInputStream fi = new FileInputStream("./src/test/resources/TestData.xlsx");
		//Workbook book = WorkbookFactory.create(fi);
		//Sheet sh = book.getSheet("Lead1");
		//Sheet sh1 = book.getSheet("Lead11");
		//String salutation = sh.getRow(0).getCell(1).getStringCellValue();
		//String phone = sh.getRow(1).getCell(1).getStringCellValue();
		//String mobile = sh.getRow(2).getCell(1).getStringCellValue(); 
		//String firstname = sh.getRow(5).getCell(1).getStringCellValue()+random;
		
		String salutation = eLib.readDataFromExcelSheet("TC01ST2",0 ,1);
		String phone = eLib.readDataFromExcelSheet("TC01ST2",1 ,1);
		String mobile = eLib.readDataFromExcelSheet("TC01ST2",2 ,1);
		String firstname = eLib.readDataFromExcelSheet("TC01ST1",0 ,1);
		String In = eLib.readDataFromExcelSheet("TC01ST2",3 ,1);
		
		
		//launching the browser//
		
		//WebDriver driver=new ChromeDriver();
		if(BROWSER.equals("chrome"))
		{
			System.out.println("Launch the chrome Browser");
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firfox"))
		{
			System.out.println("Launch the Firefox Browser");
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser Server!!");
		}
		
		//driver.manage().window().maximize();
		wLib.maxiMizewindow(driver);
		
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wLib.waitForPageLoad(driver);
		
		driver.get(URL);
		
		//login to the applications//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//Click on lead module//
		driver.findElement(By.linkText("Leads")).click();
		//click on create module//
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		WebElement salute = driver.findElement(By.name("salutationtype"));
		
		//Select s=new Select(salute);
		//s.selectByVisibleText(salutation);
		wLib.select(salutation, salute);
		
		//create an object hashmap//
		
		/*HashMap<String,String> map=new HashMap<String,String>();
		for(int i=5;i<=sh.getLastRowNum();i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			map.put(key, value);
		}*/
		HashMap<String, String> fields = eLib.getList("TC01ST1",0 ,1);
		for(Map.Entry<String ,String> set:fields.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
		
		//click on edit//
 		driver.findElement(By.xpath("//input[@title='Edit [Alt+E]']")).click();
		
		//adding mobile and phone number//
		driver.findElement(By.name("phone")).sendKeys(phone);
		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Click on lead module//
	    driver.findElement(By.linkText("Leads")).click();
	    
	    //click on in//
	    WebElement dd = driver.findElement(By.name("search_field"));
	    
	    
	    //Select s1=new Select(dd);
	    //s1.selectByVisibleText("First Name");
	    wLib.select(dd, In);
	    
	    //click on search//
	    driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(firstname);
	    
	    //click on searchnow//
	    driver.findElement(By.name("submit")).click();
	    Thread.sleep(2000);
	    
	    //logout from the applications//
        WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		//Actions act=new Actions(driver);
		//act.moveToElement(signout).perform();
        wLib.mousehover(driver, signout);
        
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		//driver.close();
		wLib.close(driver);

	}

}
