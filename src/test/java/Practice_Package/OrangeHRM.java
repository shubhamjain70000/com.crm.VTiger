package Practice_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.ObjectRepository.OrangeHRMContactUsPage;
import com.crm.genericUtility.ExcelUtility;

public class OrangeHRM {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.orangehrm.com/contact-sales/");
		
		//reading the data from excel sheet//
//		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
//		Workbook book = WorkbookFactory.create(fis);
//		Sheet sh = book.getSheet("orangeHRM");
//		int lastrow = sh.getLastRowNum();
		
		
		//click on contact//
		//driver.findElement(By.xpath("//div[@class='d-flex']/../descendant::button[text()='Contact Sales']")).click();
		//WebElement element = driver.findElement(By.xpath("//h4[.='Let us know how we can help you!']"));
		//WebDriverWait wait=new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visibilityOf(element));
		//driver.findElement(By.xpath("(//button[.='Contact Sales'])[1]")).click();
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250);");
		//putting the value into the application//
		
		/*HashMap<String,String> map=new HashMap<String,String>();
		for(int i=0;i<=lastrow;i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
	
		for(Entry<String, String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}*/
		
		
		/*ArrayList<String> list=new ArrayList<String>();
		list.add("FullName");
		list.add("Contact");
		list.add("Email");
		list.add("Country");
		list.add("NoOfEmployees");
		list.add("JobTitle");
		list.add("Comment");
		
		for(int i=0;i<=lastrow;i++)
		{
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			driver.findElement(By.name(list.get(i))).sendKeys(value);
		}*/
		
		ExcelUtility elib=new ExcelUtility();
		/*HashMap<String, String> fields = elib.getListWithoutRandom("orangeHRM", 0, 1);
		for(Entry<String,String> set:fields.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}*/
		
		OrangeHRMContactUsPage HRM=new OrangeHRMContactUsPage(driver);
		HRM.fillthemandatoryFields(elib.getListWithoutRandom("orangeHRM", 0, 1), driver, elib);
		
	}
}
		
		
		
		
		


