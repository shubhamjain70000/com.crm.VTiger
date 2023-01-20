package hardCodeTestScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC_01SystemTestingHardCodeData {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//creating Random class
		Random r=new Random();
		int random = r.nextInt(100);
		
		//get common data
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
				
		//get data from excel sheet//
		FileInputStream fi = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh = book.getSheet("Lead1");
		//Sheet sh1 = book.getSheet("Lead11");
		String salutation = sh.getRow(0).getCell(1).getStringCellValue();
		String phone = sh.getRow(1).getCell(1).getStringCellValue();
		String mobile = sh.getRow(2).getCell(1).getStringCellValue();
		String firstname = sh.getRow(5).getCell(1).getStringCellValue()+random;
		String In = sh.getRow(3).getCell(1).getStringCellValue();
		
		//launching the browser//
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		
		//login to the applications//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//Click on lead module//
		driver.findElement(By.linkText("Leads")).click();
		//click on create module//
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		WebElement salute = driver.findElement(By.name("salutationtype"));
		Select s=new Select(salute);
		s.selectByVisibleText(salutation);
		
		//create an object hashmap//
		HashMap<String,String> map=new HashMap<String,String>();
		for(int i=5;i<=sh.getLastRowNum();i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			map.put(key, value);
		}
		for(Entry<String ,String> set:map.entrySet())
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
	    Select s1=new Select(dd);
	    s1.selectByValue(In);
	    
	    //click on search//
	    driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(firstname);
	    
	    //click on searchnow//
	    driver.findElement(By.name("submit")).click();
	    Thread.sleep(2000);
	    
	    //logout from the applications//
        WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

	}

}
