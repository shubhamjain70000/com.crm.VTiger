package vendor_Product_module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TC_08_IntegrationTestingWithOutHardCode {

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
		
		//Steps 2: Read data from excel sheet
		//FileInputStream fi=new FileInputStream("./src/test/resources/TestData.xlsx");
		//Workbook book = WorkbookFactory.create(fi);
		//Sheet sh = book.getSheet("Vendor");
		//String vendorname=sh.getRow(0).getCell(1).getStringCellValue()+random;
		String vendorname=eLib.readDataFromExcelSheet("TC08IT",0 ,1);
		
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
				
				
		
		//Login to Appilication//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		WebElement More=driver.findElement(By.xpath("//td[@class='tabSelected']/..//td[2]/table/tbody/tr/td[22]/a"));
		//Actions act=new Actions(driver);
		//act.moveToElement(More).perform();
		wLib.mousehover(driver, More);
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
		
		/*ArrayList<String> list = new ArrayList<String>();
		list.add("vendorname");
		list.add("email");
		list.add("phone");
		list.add("website");*/
		
		int lastrow = eLib.getLastRow("TC08IT");
		ArrayList<String> al = eLib.getArrayList("TC08IT", 0);
		for(int i=0;i<=lastrow;i++)
		{
			String value = eLib.readDataFromExcelSheet("TC08IT", i, 1)+random;
			driver.findElement(By.name(al.get(i))).sendKeys(value);
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//validation
		String actualdata = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(actualdata.contains(vendorname))
		{
			System.out.println(vendorname+"Vendor is created successfully ");
				
		}
		else
		{
			System.out.println("vendorname not created");
		}
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
		
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			String url= driver.switchTo().window(id).getCurrentUrl();
			String URL1="Vendors";
			if(url.contains(URL1))
			{
				driver.switchTo().window(id);
				System.out.println("Switch to child window successfully!!");
				break;
			}
					
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='searchAlph'])[1]")).click();

	}

}
