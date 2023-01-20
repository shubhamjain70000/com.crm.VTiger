package practicePackageForTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.genericUtility.ExcelUtility;

public class Demodataprovider {
	
	@DataProvider
	public Object[][] data() throws Throwable
	{
		ExcelUtility eLib=new ExcelUtility();
		Object[][] data = eLib.readMultipleSetOfDataFromExcelUsingDataProviderAnnotation("DataProvider");
		return data;
		
	}
	@Test(dataProvider="data")
	public void readData(String starting, String ending)
	{
		System.out.println(starting+"----->"+ending);
	}
	

}
