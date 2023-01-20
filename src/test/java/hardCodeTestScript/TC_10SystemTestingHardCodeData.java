package hardCodeTestScript;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/*"1)Open the browser and enter the URL login page should be displayed.
2)Enter the UN and Password home page should be displayed.
3)Move the mouse cursor to setting and click on crm setting it shpuld display crm setting pages.
4)click on workflow module is should display workflow list page.
5)click on new workflow edit workflow page should be display .
6)enter the description and click on save.
7)click on workflow under other setting on left side pof the column it should display workflow list page.
8)Newly created workflow is displayed on workflow list page.
9) click on delete button confirm the alert popup button."*/

public class TC_10SystemTestingHardCodeData {
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		
		
		int random;
		Random r=new Random();
		random=r.nextInt(100);
		
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
		Sheet sh = book.getSheet("workflow");
		String dropdown = sh.getRow(1).getCell(0).getStringCellValue();
		
		//launching the browser//
	    WebDriver driver=new ChromeDriver();
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
			
		//login to the applications//
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//move the mouse cursor to crm setting//
		WebElement crmsetting = driver.findElement(By.xpath("//td[@class='genHeaderSmall']/../td[4]/img[1]"));
		Actions act=new Actions(driver);
		act.moveToElement(crmsetting).perform();
		driver.findElement(By.linkText("CRM Settings")).click();
		
		//scrolldown the screen//
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000);");
		
		//click on workflow//
		driver.findElement(By.linkText("Workflows")).click();
		
		//click on new workflow//
		driver.findElement(By.id("new_workflow")).click();
		
		//change the driver focus on child window//
		Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		while(it.hasNext())
		{
			String id = it.next();
			String title = driver.switchTo().window(id).getTitle();
			if(title.contains("Create workflow"))
			{
				break;
			}
		}
		
		//click on create workflow dropdown//
		WebElement dd = driver.findElement(By.id("module_list"));
		Select s=new Select(dd);
		s.selectByVisibleText(dropdown);
		
		//click on create//
		driver.findElement(By.id("new_workflow_popup_save")).click();
		
		//creating the object of hashmap//
		HashMap<String,String> map=new HashMap<String,String>();
		for(int i=3;i<=sh.getLastRowNum();i++)
		{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue()+random;
			map.put(key, value);
		}
		for(Entry<String,String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		
		//click on save button//
		driver.findElement(By.id("save_submit")).click();
		
		
		//click on workflow in left hand side//
		driver.findElement(By.linkText("Workflows")).click();
		
		//scrolldown the screen//
	    JavascriptExecutor jse1=(JavascriptExecutor)driver;
		jse1.executeScript("window.scrollBy(0,1000);");
		
		//validation//
		String expectedvalue = sh.getRow(3).getCell(1).getStringCellValue()+random;
		String actualvalue = driver.findElement(By.xpath("//td[@class='small settingsSelectedUI']/descendant::td[.='"+expectedvalue+"']")).getText();
		if(expectedvalue.equals(actualvalue))
		{
			
			driver.findElement(By.xpath("//td[@class='small settingsSelectedUI']/descendant::td[.='"+expectedvalue+"']/../td[3]/a[2]/img[1]")).click();
			System.out.println("Description is deleted successfully!!");
		
		}
		else
		{
			System.out.println("Description not deleted");
		}
		
		Thread.sleep(2000);
		//handling the alert popup//
		Alert alt = driver.switchTo().alert();
		String msg = alt.getText();
		System.out.println(msg);
		alt.accept();
		
		//scrolldown the screen//
	    JavascriptExecutor jse2=(JavascriptExecutor)driver;
		jse2.executeScript("window.scrollBy(0,1000);");
		
		
		Thread.sleep(5000);
		
		//logout from the applications//
        WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		//Actions act=new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		//driver.close();
		
	}


}
