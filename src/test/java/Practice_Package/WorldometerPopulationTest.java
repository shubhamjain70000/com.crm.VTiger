package Practice_Package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorldometerPopulationTest {

	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://www.worldometers.info/world-population/");
		String Worldpopulation = driver.findElement(By.xpath("//h1[text()=' Current World Population']/ancestor::div[@id='maincounter-wrap']/descendant::span[@rel='current_population']")).getText();
        System.out.println(Worldpopulation);
        driver.close(); 
	}

}
