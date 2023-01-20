package Practice_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestScript2 {

	public static void main(String[] args) throws IOException, Throwable {
		
		Random r=new Random();
		int random=r.nextInt();
		String campaign_name="Laptop";
		String actual_campaign_name=campaign_name+random;
		String Organizations_name="HP";
		String actual_Organizations_name=Organizations_name+random;
		String Opportunities_name="Sales";
		String actual_Opportunities_name=Opportunities_name+random;
		
		
		
		//getting data from properties file//
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
	    String PASSWORD = pobj.getProperty("password");
	    
	    //launching the browser//
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8888/");
		
		
		//login to the applications//
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"admin",Keys.TAB,Keys.SPACE);
		
		//move the cursor to the More//
		WebElement More=driver.findElement(By.xpath("//td[@class='tabSelected']/..//td[2]/table/tbody/tr/td[22]/a"));
		Actions act=new Actions(driver);
		act.moveToElement(More).perform();
		
		//Click on campaign module
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		//creating campaign module//
		driver.findElement(By.name("campaignname")).sendKeys(actual_campaign_name);
		WebElement Campaignstatus=driver.findElement(By.name("campaignstatus"));
		Select s=new Select(Campaignstatus);
		s.selectByValue("Active");
		WebElement CampaignType = driver.findElement(By.name("campaigntype"));
		Select s2=new Select(CampaignType);
		s2.selectByValue("Advertisement");
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			String URL_parts="Products";
			String Url=driver.switchTo().window(id).getCurrentUrl();
			if(Url.contains(URL_parts))
			{
				driver.switchTo().window(id);
				System.out.println("hii");
				break;
			}
		}
		driver.findElement(By.xpath("//td[@class='searchAlph']/../td[8]")).click();
		driver.findElement(By.linkText("Hondavtec")).click();
		driver.switchTo().window(mainid);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//clciking on Organizations//
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//creating Organization//
		driver.findElement(By.name("accountname")).sendKeys(actual_Organizations_name);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//click on Opportunities//
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[.='Opportunities'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		
		
		//creayting Opportunities//
		driver.findElement(By.name("potentialname")).sendKeys("actual_Opportunities_name");
		driver.findElement(By.xpath("//input[@id='related_to_display']/../img")).click();
		String mainid1 = driver.getWindowHandle();
		Set<String> allid1 = driver.getWindowHandles();
		for(String id1:allid1)
		{
			String URL1=driver.switchTo().window(id1).getCurrentUrl();
			String url1="Accounts";
			if(URL1.contains(url1))
			{
				driver.switchTo().window(id1);
				break;
			}
		}
//		WebElement type=driver.findElement(By.name("opportunity_type"));
//		WebElement leadsources=driver.findElement(By.name("leadsource"));
//		WebElement salesstage=driver.findElement(By.name("sales_stage"));
//		Select s1=new Select(type);
//		Select s2=new Select(leadsources);
//		Select s3=new Select(salesstage);
//		
//		s1.selectByValue("Existing Business");
//		s2.selectByValue("Cold Call");
//		s3.selectByValue("Needs Analysis");
//		driver.findElement(By.xpath("//div[@id='basicTab']/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/div[1]/input[1]")).click();
//		

	}

}
