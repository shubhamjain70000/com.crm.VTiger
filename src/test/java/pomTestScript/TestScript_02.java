package pomTestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.CreateLeadPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LeadPage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TestScript_02 {

	public static void main(String[] args) throws Throwable {
		
		WebDriverUtility wLib=new WebDriverUtility();
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		
		//read data from properties file//
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//launch the browser//
		WebDriver driver=new ChromeDriver();
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
        //creating object for load page//
		LoginPage lp=new LoginPage(driver);
		lp.getLoginPage(USERNAME, PASSWORD);
		
		
		//click on lead Module//
		HomePage hp=new HomePage(driver);
		hp.clickOnLeadsClick();
		
		//click on lead module//
		LeadPage LP=new LeadPage(driver);
		LP.getCreateLeadPage();
		
		//fill all the manadatory fields//
		CreateLeadPage clp=new CreateLeadPage(driver);
		clp.getmandatoryfileds(eLib.getListWithoutRandom("Shubham", 0, 1), driver, jLib);
		clp.Save();
		
		
				

	}

}
