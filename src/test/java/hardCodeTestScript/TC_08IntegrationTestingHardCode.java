package hardCodeTestScript;

import java.io.FileInputStream;
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
import org.openqa.selenium.interactions.Actions;

public class TC_08IntegrationTestingHardCode {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Random r=new Random();
		int random = r.nextInt();
		
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
		Sheet sh = book.getSheet("Vendor");
		String vendorname=sh.getRow(0).getCell(1).getStringCellValue()+random;
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		
		//Login to Appilication//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		WebElement More=driver.findElement(By.xpath("//td[@class='tabSelected']/..//td[2]/table/tbody/tr/td[22]/a"));
		Actions act=new Actions(driver);
		act.moveToElement(More).perform();
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("vendorname");
		list.add("email");
		list.add("phone");
		list.add("website");
		
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			driver.findElement(By.name(list.get(i))).sendKeys(value);
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
