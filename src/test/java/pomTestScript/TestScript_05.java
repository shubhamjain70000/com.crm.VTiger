package pomTestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.ObjectRepository.CreateLeadPage;
import com.crm.ObjectRepository.CreateNewProduct;
import com.crm.ObjectRepository.CreateVendorPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductPage;
import com.crm.ObjectRepository.VendorsPage;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;

public class TestScript_05 {

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
		String productName = eLib.readDataFromExcelSheet("Product", 1, 0)+jLib.getRandom();
		String VendorName = eLib.readDataFromExcelSheet("Vendor", 0, 1);
		
		//launch the browser//
		WebDriver driver=new ChromeDriver();
		wLib.maxiMizewindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
        //creating object for load page//
		LoginPage lp=new LoginPage(driver);
		lp.getLoginPage(USERNAME, PASSWORD);
		
		//click on vendors Module//
		HomePage hp=new HomePage(driver);
		hp.clickOnVendorsClick(wLib, driver);
		
		//click on lookup icon//
		VendorsPage vp=new VendorsPage(driver);
		vp.getVendorPage();
		
		//fill all the manadatory fields//
		CreateVendorPage cvp=new CreateVendorPage(driver);
		cvp.vendorField(eLib.getListWithoutRandom("Vendor", 0, 1), driver, jLib);
		cvp.clickOnSave();
		
		//click on productModule//
		hp.clickOnProductsClick();
		
		//click on lookup icon//
		ProductPage pp=new ProductPage(driver);
		pp.getProductpage();
		
		//creating product//
		CreateNewProduct cnp=new CreateNewProduct(driver);
		
		cnp.createProduct(productName, wLib, driver, VendorName);
		
		hp.clickOnHome();
		hp.clickOnSignOut(wLib, driver);
		wLib.close(driver);
		
		
		
				
				

	}

}
