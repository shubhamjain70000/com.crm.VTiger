package com.crm.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

public class BaseClass {
	
	public WebDriver driver=null;
	public static WebDriver Listenerdriver;
	public DatabaseUtility dLib=new DatabaseUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	
	//connection to the browser server
	@BeforeSuite(alwaysRun=true)
	public void configDB() throws Throwable
	{
		dLib.connectionToDB();
		System.out.println("--Connect to database--");
	
	}
	
	//launching the browser
	//@Parameters("BROWSER")
	@BeforeClass(alwaysRun=true)
	public void configBC( ) throws Throwable 
	{
		String BROWSER = fLib.readDataFromPropertyFile("broswer");
		String URL = fLib.readDataFromPropertyFile("url");
		if(BROWSER.equals("chrome"))
		{
			System.out.println("Launch the chrome browser");
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\admin\\Downloads\\selenium\\msedgedriver.exe");
			System.out.println("Launch the fire fox browser");
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid browser server");
		}
		Listenerdriver=driver; 
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		System.out.println("--Launch the browser--");
	}
	//login to the application//
	@BeforeMethod(alwaysRun=true)
	public void configBM() throws Throwable
	{
		
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		LoginPage lp=new LoginPage(driver);
		lp.getLoginPage(USERNAME, PASSWORD);
	}
	//logout from the application//
	@AfterMethod(alwaysRun=true)
	public void configAM()
	{
		HomePage hp=new HomePage(driver);
		hp.clickOnSignOut(wLib, driver);
	}
	//close the browser //
	@AfterClass(alwaysRun=true)
	public void configAC()
	{
		driver.close();
		System.out.println("Close the browser server");
	}
	//close the database connection//
	@AfterSuite(alwaysRun=true)
	public void configAS() throws Throwable
	{
		dLib.closeDB();
		System.out.println("database connection should be closed");
	}
	

}
