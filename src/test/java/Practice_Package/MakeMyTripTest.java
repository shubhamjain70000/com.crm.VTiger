package Practice_Package;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeMyTripTest {

	public static void main(String[] args) {
		//ChromeOptions opt=new ChromeOptions();
		//opt.addArguments("--disable-notifications");
		//opt.addArguments("use-fake-device-for-media-stream");
		//opt.addArguments("use-fake-ui-for-media-stream");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		//click on login
		driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		//click on buses//
		driver.findElement(By.xpath("//ul[@class='makeFlex font12']/li[6]/div[1]/a[1]/span[2]")).click();
		//from
		driver.findElement(By.xpath("//span[.='From']")).click();
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("raipur");
		driver.findElement(By.xpath("//span[.='Raipur (Raipur), Chhattisgarh']")).click();
		
		//to
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("jabalpur");
		driver.findElement(By.xpath("//span[.='Jabalpur, Madhya Pradesh']")).click();
		
		//Wed Jan 18 2023
		String day="Wed";
		String month="Jan";
		String date="18";
		String year="2023";
		String travellingDate=""+day+" "+month+" "+date+" "+year+"";
		driver.findElement(By.xpath("//div[@aria-label='"+travellingDate+"']")).click();
		
		//click on search//
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		//primo//
		driver.findElement(By.xpath("//img[@class='primoCloseIcon']")).click();
		
		//printing all the bus name// 
		
		List<WebElement> busname=driver.findElements(By.xpath("//div[@class='makeFlex column bus-view-left']/div[1]/div[1]/span[1]"));
		for(WebElement bn:busname)
		{
			System.out.println(bn.getText());
		}
		
		//close the applications//
		driver.close();
		

	}

}
