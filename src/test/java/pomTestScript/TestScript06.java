package pomTestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.EditWorFlowPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.WorkFlowListPage;
import com.crm.ObjectRepository.WorkFlowPage;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TestScript06 {

	public static void main(String[] args) throws Throwable {
		
		WebDriverUtility wLib=new WebDriverUtility();
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		
		//read data from properties file//
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read data from excel sheet//
		String createWorkflow = eLib.readDataFromExcelSheet("workFlow", 1, 0);
		String description = eLib.readDataFromExcelSheet("workFlow", 3, 1)+jLib.getRandom();
		
		//launch the browser//
		WebDriver driver=new ChromeDriver();
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
        //creating object for load page//
		LoginPage lp=new LoginPage(driver);
		lp.getLoginPage(USERNAME, PASSWORD);
		
		//click on settings//
		HomePage hp=new HomePage(driver);
		hp.clickOnSetiing(wLib, driver);
		
		//click on workflow//
		WorkFlowPage wfp=new WorkFlowPage(driver);
		wfp.clickOnWorkFlow(wLib, driver);
		
		//click on new work flow//
		WorkFlowListPage wflp=new WorkFlowListPage(driver);
		wflp.clickOnNewWorkFlowAndCreate(createWorkflow, wLib);
		
		//description//
		EditWorFlowPage ewfp=new EditWorFlowPage(driver);
		ewfp.sendTheMessageInDescrption(description);
		
		//click on workflow//
		ewfp.scrollDownAndClickOnWorkFlow(wLib, driver);
		
		//validation//
		wflp.ValidationAndDeletion(driver, description, wLib);
		
		//signout//
		hp.clickOnSignOut(wLib, driver);
		
		//close//
		wLib.close(driver);

	}

}
