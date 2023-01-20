package hardCodeTestScript;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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

/*1)Open the browser and pass the Url login page should be displayed.
2) enter the valid username and password,home page should be displayed.
3)Click on organization module click on create organization it should display create new Organization page.
4)Fill all the mandatory field (Organization name,phone,email etc) and click on save.
5) click on contact module click on create contact it should display create new contact page.
6)go to organization name and click on its lookup icon new window should with display new created organization.*/

public class TC_06IntegrationTestingHardCodeData {
	
public static void main(String[] args) throws Throwable {
		
		//creating Random class
		Random r=new Random();
		int random = r.nextInt(500);
		
		//get common data
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//get data from excel shett//
		FileInputStream fi = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh = book.getSheet("Contact");
		String organization_name=sh.getRow(1).getCell(1).getStringCellValue()+random;
		String lastname = sh.getRow(1).getCell(0).getStringCellValue()+random;
		String lead = sh.getRow(1).getCell(2).getStringCellValue();
		
		//launching the browser//
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//click on organization//
		driver.findElement(By.linkText("Organizations")).click();
		//click on lookup image anf fill all the details//
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organization_name);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//validation//
		String created_organization = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(created_organization.contains(organization_name))
		{
			System.out.println(organization_name+" organization created successfully!");
			
		}
		else
		{
			System.out.println("Organization is not created!!");
		}
		
		//click on contact module//
		driver.findElement(By.linkText("Contacts")).click();
		//click on lookup icon//
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		HashMap<String, String> map = new HashMap<String,String>();
		for(int i=4;i<=sh.getLastRowNum();i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			map.put(key, value);
		}
		for(Entry<String, String> set:map.entrySet())
		{
			Thread.sleep(2000);
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		
		
		
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		String mainid = driver.getWindowHandle();
		/*Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			String title = driver.switchTo().window(id).getTitle();
			String title1="Accounts&action";
			if(title.contains(title1))
			{
				driver.switchTo().window(id);
				break;
			}
		}*/
		
		Set<String> allwindow= driver.getWindowHandles();
		Iterator<String> it = allwindow.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			String current_title = driver.switchTo().window(win).getTitle();
			if(current_title.contains("Accounts&action"))
			{
				driver.switchTo().window(win);
				break;
			}
		}
		driver.findElement(By.id("search_txt")).sendKeys(organization_name);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
		
		/*String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			String title = driver.switchTo().window(id).getTitle();
			String title1="Contacts&action";
			if(title.contains(title1))
			{
				driver.switchTo().window(id);
				break;
			}
		}*/
		
		driver.switchTo().window(mainid);
		
		WebElement lead_sources = driver.findElement(By.name("leadsource"));
		Select s=new Select(lead_sources);
		s.selectByVisibleText(lead);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//logout from the applications//
        WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions act=new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
	}

}
