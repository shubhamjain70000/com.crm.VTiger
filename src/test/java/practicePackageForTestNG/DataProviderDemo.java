   package practicePackageForTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
	
	@Test(dataProvider="data")
	public void travel(String starting, String Ending)
	{
		System.out.println(starting+" --------> "+Ending);
	}

	 
		
		@DataProvider
		public Object[][] data() {
			Object[][] objarr = new Object[2][2];
			
			objarr[0][0]="banglore";
			objarr[0][1]="Bombay";
			
			objarr[1][0]="BomBay";
			objarr[1][1]="banglore";
			return objarr;
		

	}

}
