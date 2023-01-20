package TestNgTestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.ObjectRepository.CreateFaq;
import com.crm.ObjectRepository.CreateNewProduct;
import com.crm.ObjectRepository.FaqPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductPage;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
@Listeners(com.crm.genericUtility.ListenerImplementation.class)
public class CreateFaqWithProductAndValidateOnHomePageTest extends BaseClass {
	
	@Test(groups="regression")
	public void createFaqandValidateOnHomePage() throws Throwable
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
		String pname = eLib.readDataFromExcelSheet("product1", 1, 0)+jLib.getRandom();
		String faqStatus = eLib.readDataFromExcelSheet("product1", 4, 1);
		String faqQuestion = eLib.readDataFromExcelSheet("product1", 5, 1);
		String faqAnswer = eLib.readDataFromExcelSheet("product1", 6, 1);
		String InDD = eLib.readDataFromExcelSheet("product1", 8, 1);
		
		
//		//launch the browser//
//		WebDriver driver=new ChromeDriver();
//		wLib.maxiMizewindow(driver);
//		wLib.waitForPageLoad(driver);
//		driver.get(URL);
//        //creating object for load page//
//		LoginPage lp=new LoginPage(driver);
//		lp.getLoginPage(USERNAME, PASSWORD);
		
		//click on products//
		HomePage hp=new HomePage(driver);
		hp.clickOnProductsClick();
		
		//product page//
		ProductPage pp=new ProductPage(driver);
		pp.getProductpage();
		
		//fill the manadatory fields//
		CreateNewProduct cnp=new CreateNewProduct(driver);
		cnp.createProductName(pname);
		Assert.fail();
		
		//click on FAQ//
		hp.clickOnFaq(wLib, driver);
		
		//click on faq plus icon//
		FaqPage fp=new FaqPage(driver);
		fp.createFaq();
		
		//fill all the manadatory fileds//
		
		CreateFaq cf=new CreateFaq(driver);
		cf.fillFaqFields(faqQuestion, faqAnswer, wLib, faqStatus, driver, pname);
		
		//click on home icon//
		hp.clickOnHome();
		
//		//click on faqore//
//		hp.clickOnFaqMore();
//		
//		//search faq//
//		fp.searchFaq(wLib,InDD , pname);
//		
//		//validate//
//		WebElement productname = driver.findElement(By.xpath("//a[text()='"+pname+"']"));
//		if(productname.isDisplayed()) {
//			System.out.println("product is created and displayed in faq!!"+productname);
//		}
//		else {
//			System.out.println("product is not displayed in faq");
//		}
//		
//		hp.clickOnSignOut(wLib, driver);
//		wLib.close(driver);
	}

}
