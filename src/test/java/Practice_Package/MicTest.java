package Practice_Package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MicTest {

	public static void main(String[] args) {
		ChromeOptions opt=new ChromeOptions();
		//opt.addArguments("--disable-notifications");
		opt.addArguments("use-fake-device-for-media-stream");
		opt.addArguments("use-fake-ui-for-media-stream");
		
		WebDriver driver=new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get("https://mictests.com/");
		
		//click on my mic test//
		driver.findElement(By.id("mic-launcher")).click();
		driver.findElement(By.id("mic-launcher")).click();
		
	    //WebElement text = driver.findElement(By.xpath("//li[@class='notice-done done_testingComplete']"));
		
		
		
		
		
		

	}

}
