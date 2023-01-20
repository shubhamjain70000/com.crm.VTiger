package practicePackageForTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Annotation_Practice extends BaseClass {
	
	
	@Test(invocationCount=2)
	public void createOrganization()
	{
		System.out.println("--Navigate to application---");
		System.out.println("--Organization created--");
	}
	@Test(priority=1)
	public void createContact()
	{
		System.out.println("---Navigate to applications");
		System.out.println("--Contacts Created--");
	}
	
	

}
