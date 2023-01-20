package Practice_Package;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Testscript1 {

	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Downloads\\selenium\\New folder\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"admin",Keys.TAB,Keys.SPACE);
		WebElement More=driver.findElement(By.xpath("//td[@class='tabSelected']/..//td[2]/table/tbody/tr/td[22]/a"));
		Actions act=new Actions(driver);
		act.moveToElement(More).perform();
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
		driver.findElement(By.name("vendorname")).sendKeys("Accenture1",Keys.TAB,Keys.TAB,"accenture@gmail.com",Keys.TAB,"9424506619");
		driver.findElement(By.id("city")).sendKeys("Bengluru",Keys.TAB,"Karnataka",Keys.TAB,"560017",Keys.TAB,"India");
		driver.findElement(By.name("description")).sendKeys("It is a Famous Software Company");
		driver.findElement(By.name("button")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			String url= driver.switchTo().window(id).getCurrentUrl();
			String URL="Vendors";
			if(url.contains(URL))
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
