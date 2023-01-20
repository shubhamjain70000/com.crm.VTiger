package Practice_Package;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.WebDriverUtility;

public class Naukri_com {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=new ChromeDriver();
		
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get("https://www.naukri.com/");
		WebElement companies = driver.findElement(By.xpath("//div[.='Companies']"));
		wLib.mousehover(driver, companies);
		WebElement it = driver.findElement(By.xpath("//div[.='IT companies']"));
		wLib.clickAction(driver, it);
		WebElement element = driver.findElement(By.linkText("Connect with us"));
		wLib.scrollIntoView(driver, element);
		
		driver.findElement(By.xpath("//a[.='Connect with us']/../a[2]/img[1]")).click();
		driver.findElement(By.xpath("//a[.='Connect with us']/../a[3]/img[1]")).click();
		driver.findElement(By.xpath("//a[.='Connect with us']/../a[4]/img[1]")).click();
		driver.findElement(By.xpath("//a[.='Connect with us']/../a[5]/img[1]")).click();
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
//		for(String id:allid)
//		{
//			String title = driver.switchTo().window(id).getTitle();
//			if(!(title.contains("List of top companies hiring in India - Naukri.com")))
//			{
//				driver.close();
//			}
			
//			
//		}
//		
		Iterator<String> ite=allid.iterator();
		while(ite.hasNext())
		{
		String window = ite.next();
		String title=driver.switchTo().window(window).getTitle();
		if(!(title.equals("List of top companies hiring in India - Naukri.com")))
		{
			driver.close();
		}
		
		}
		System.out.println(driver.switchTo().window(mainid).getTitle());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("./screenshot/naukri.png");
		FileUtils.copyFile(src, trg);
		
		driver.findElement(By.linkText("Register")).click();
		
		ExcelUtility eLib=new ExcelUtility();
		HashMap<String, String> fields = eLib.getListWithoutRandom("naukri", 0, 1);
		for(Entry<String, String> set:fields.entrySet())
		{
			driver.findElement(By.id(set.getKey())).sendKeys(set.getValue());
		}
		
		WebElement uploadResume = driver.findElement(By.xpath("//button[.='Upload Resume']"));
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true)",uploadResume);
		uploadResume.click();
		StringSelection path=new StringSelection("Downloads\\Freshers_Resume 1.pdf");
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(path, null);
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		
		
		

	}

}
