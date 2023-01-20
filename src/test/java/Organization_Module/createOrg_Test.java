package Organization_Module;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class createOrg_Test {

	public static void main(String[] args) throws Throwable {
		
		Random ran=new Random();
		int random = ran.nextInt(1000);
		
		//Steps 1: get common data
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//Steps 2: Read data from excel sheet
		FileInputStream fi=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh = book.getSheet("Organization");
		String OrgName = sh.getRow(0).getCell(1).getStringCellValue()+random;
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		
		//Login to Appilication//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Create Organization//
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//driver.findElement(By.name("accountname")).sendKeys(OrgName);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("accountname");
		list.add("website");
		list.add("phone");
		list.add("tickersymbol");
		
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			driver.findElement(By.name(list.get(i))).sendKeys(value);
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
		
		//validation
		String actualdata = driver.findElement(By.className("dvHeaderText")).getText();
		if(actualdata.contains(OrgName))
		{
			System.out.println(OrgName+" Organization created successfully ");
			
		}
		else
		{
			System.out.println("Organiation not created");
		}
		WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		
		

	}

}
