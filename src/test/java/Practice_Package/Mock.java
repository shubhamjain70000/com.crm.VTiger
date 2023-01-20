package Practice_Package;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Mock {

	public static void main(String[] args) {
	
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"admin",Keys.TAB,Keys.SPACE);
		driver.findElement(By.linkText("Organizations")).click();
		//WebElement checkbox = driver.findElement(By.id("selectCurrentPageRec"));
		List<WebElement> checkbox = driver.findElements(By.xpath("//input[@name='selected_id']"));
		
		for(WebElement box:checkbox)
		{
			box.click();
		}    
		
		
		
		

	}

}
