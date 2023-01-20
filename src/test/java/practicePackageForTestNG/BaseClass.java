package practicePackageForTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseClass {
	WebDriver driver=null;
	
	@BeforeSuite
	public void configBS() {
		System.out.println("Connect to database server");
	}
	@Parameters("BROWSER")
	@BeforeClass
	public void configBC(String BROWSER) {
		if(BROWSER.equals("chrome")) {
			System.out.println("Chrome driver launch successfully!!!!");
		driver=new ChromeDriver();
		}
		else {
			System.out.println("Edge driver launch successfully!!!!");
			System.setProperty("webdriver.edge.driver", "C:\\Users\\admin\\Downloads\\selenium\\msedgedriver.exe");
			driver=new EdgeDriver();
			
		}
		
		
	}
	
	
//	@BeforeClass
//	public void configBC() 
//	{
//	       System.out.println("Chrome driver launch successfully!!!!");
//		   driver=new ChromeDriver();
//	}
		
	@BeforeMethod
	public void configBM() {
		System.out.println("Login to the application");
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("Logout from the applications");
	}
	@AfterClass
	public void configAC()
	{
		driver.close();
		System.out.println("close the browser");
	}
	@AfterSuite
	public void configAS()
	{
		System.out.println("Logout from database server");
	}

}
