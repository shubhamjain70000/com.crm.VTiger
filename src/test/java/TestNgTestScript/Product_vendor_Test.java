package TestNgTestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.ObjectRepository.CreateNewProduct;
import com.crm.ObjectRepository.CreateVendorPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductPage;
import com.crm.ObjectRepository.VendorsPage;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
@Listeners(com.crm.genericUtility.ListenerImplementation.class)
public class Product_vendor_Test extends BaseClass {
	
	
	@Test(groups="regression")
	public void createVendorandProduct() throws Throwable
	{
//		WebDriverUtility wLib=new WebDriverUtility();
//		ExcelUtility eLib=new ExcelUtility();
//		FileUtility fLib=new FileUtility();
//		JavaUtility jLib=new JavaUtility();
//		
//		//read data from properties file//
//		String URL = fLib.readDataFromPropertyFile("url");
//		String USERNAME = fLib.readDataFromPropertyFile("username");
//		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read data from excel sheet//
		String productName = eLib.readDataFromExcelSheet("Product", 1, 0)+jLib.getRandom();
		String VendorName = eLib.readDataFromExcelSheet("Vendor", 0, 1)+jLib.getRandom();
		String email = eLib.readDataFromExcelSheet("Vendor", 1, 1);
		String phone = eLib.readDataFromExcelSheet("Vendor", 2, 1);
		String website = eLib.readDataFromExcelSheet("Vendor", 3, 1);
		
		
//		//launch the browser//
//		WebDriver driver=new ChromeDriver();
//		wLib.maxiMizewindow(driver);
//		wLib.waitForPageLoad(driver);
//		driver.get(URL);
//        //creating object for load page//
//		LoginPage lp=new LoginPage(driver);
//		lp.getLoginPage(USERNAME, PASSWORD);
		
		//click on vendors Module//
		HomePage hp=new HomePage(driver);
		hp.clickOnVendorsClick(wLib, driver);
		
		//click on lookup icon//
		VendorsPage vp=new VendorsPage(driver);
		vp.getVendorPage();
		
		//fill all the manadatory fields//
		CreateVendorPage cvp=new CreateVendorPage(driver);
		//cvp.vendorField(eLib.getListWithoutRandom("Vendor", 0, 1), driver, jLib);
		cvp.vendorField(VendorName, email, phone, website);
		cvp.clickOnSave();
		
		//click on productModule//
		hp.clickOnProductsClick();
		
		//click on lookup icon//
		ProductPage pp=new ProductPage(driver);
		pp.getProductpage();
		
		//creating product//
		CreateNewProduct cnp=new CreateNewProduct(driver);
		
		cnp.createProduct(productName, wLib, driver, VendorName);
		
//		hp.clickOnHome();
//		hp.clickOnSignOut(wLib, driver);
//		wLib.close(driver);
	}

}
