package recyclebin_module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TC_04IntegrationTestingWithOutHardCode {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		
		//creating Random class
		//Random r=new Random();
		//int random = r.nextInt(100);
		
		// creating object of genericUtility//
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//calling Random class
		
		//Random r=new Random();
		//int random = r.nextInt(100);
		int random = jLib.getRandom();
		
		//get common data
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
				
		//get data from excel sheet//
		//FileInputStream fi = new FileInputStream("./src/test/resources/TestData.xlsx");
		//Workbook book = WorkbookFactory.create(fi);
		//Sheet sh = book.getSheet("Recyclebin");
		//String orgname = sh.getRow(1).getCell(0).getStringCellValue()+random;
		String orgname =   eLib.readDataFromExcelSheet("TC04IT",1, 0)+random;
		
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
				
		//click on organization//
		driver.findElement(By.linkText("Organizations")).click();
		//click on lookup image anf fill all the details//
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//click on delete
		driver.findElement(By.xpath("//input[@title='Delete [Alt+D]']")).click();
		
		//handle the alert popup
		//Alert alt = driver.switchTo().alert();
		//alt.accept();
		wLib.acceptAlert(driver);
		
		//move the mouse cursor to more//
		WebElement more = driver.findElement(By.xpath("//a[text()='More']"));
		//Actions act=new Actions(driver);
		//act.moveToElement(more).perform();
		wLib.mousehover(driver, more);
		driver.findElement(By.name("Recycle Bin")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@class='searchUIName small']/../td[3]/input[1]")).sendKeys(orgname);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(5000); 
		
		WebElement noRecordFound=driver.findElement(By.xpath("//span[@class='genHeaderSmall']"));
		if(noRecordFound.isDisplayed())
		{
			System.out.println("Organizations is deleted successfully!!");
		}
		else
		{
			System.out.println("Organizations is not deleted successfully!!");
		}
		
		
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
