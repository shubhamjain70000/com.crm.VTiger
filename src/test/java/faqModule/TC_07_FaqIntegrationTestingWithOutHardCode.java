package faqModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
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

public class TC_07_FaqIntegrationTestingWithOutHardCode {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=null;
		
		// creating object of genericUtility//
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
				
        //int random;
		//Random r=new Random();
		//random=r.nextInt(100);
		int random = jLib.getRandom();
		
		//get common data
	  //  FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		//Properties pobj=new Properties();
		//pobj.load(fis);
		//String URL = pobj.getProperty("url");
		//String USERNAME = pobj.getProperty("username");
		//String PASSWORD = pobj.getProperty("password");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		String BROWSER = fLib.readDataFromPropertyFile("broswer");
				
		//get data from excel sheet//
		//FileInputStream fi = new FileInputStream("./src/test/resources/TestData.xlsx");
		//Workbook book = WorkbookFactory.create(fi);
		//Sheet sh = book.getSheet("Product");
		//String productname = sh.getRow(1).getCell(0).getStringCellValue()+random;
		//String productcategory = sh.getRow(1).getCell(1).getStringCellValue();
		//String question = sh.getRow(5).getCell(1).getStringCellValue();
		String productname=eLib.readDataFromExcelSheet("TC07IT2",1,0)+random;
		String productcategory=eLib.readDataFromExcelSheet("TC07IT2",1,1);
		
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

		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//click on products//
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(productname);
		WebElement procat = driver.findElement(By.name("productcategory"));
		
		//Select s=new Select(procat);
		//s.selectByValue(productcategory);
		wLib.select(procat, productcategory);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//move the mouse cursor to more//
		WebElement more = driver.findElement(By.xpath("//a[.='More']"));
		
		//Actions act=new Actions(driver);
		//act.moveToElement(more).perform();
		wLib.mousehover(driver, more);
		
		//click on FAQ
		driver.findElement(By.name("FAQ")).click();
		driver.findElement(By.xpath("//img[@title='Create FAQ...']")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		
		String mainid = driver.getWindowHandle();
		
		/*Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		while(it.hasNext())
		{
			String id = it.next();
			String title = driver.switchTo().window(id).getTitle();
			String title1="Products&action";
			if(title.contains(title1)) {
				break;
			}
		}*/
		wLib.getwindowHandles(driver, "Products&action");
		
		driver.findElement(By.id("search_txt")).sendKeys(productname);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
		
		//switching to main window//
		driver.switchTo().window(mainid);
		
		//creating object of hashmap//
		/*HashedMap<String,String> map=new HashedMap<String,String>();
		for(int i=4;i<=sh.getLastRowNum();i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}*/
		HashMap<String, String> fields = eLib.getListWithoutRandom("TC07IT1",0 ,1);
		for(Entry<String,String> set:fields.entrySet())
		{
			
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//click on home page//
		driver.findElement(By.xpath("//img[@src='themes/softed/images/Home.PNG']")).click();
		
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
